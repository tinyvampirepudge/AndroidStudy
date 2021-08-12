package com.tinytongtong.androidstudy.toast;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.util.SparseArray;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.tinytongtong.androidstudy.R;

import java.lang.ref.WeakReference;
import java.lang.reflect.Field;


/**
 * @Description: 切换到后台之后，不再展示应用的Toast
 * 思路：
 * ——使用List将Toast对象存储起来，使用软引用
 * ——通过hook方式，获取Toast消失的时机。
 * ——通过反射获取Toast#TN中的Handler对象，hook住Handler#dispatchMessage方法。toast的展示、隐藏和取消
 * 都是通过给Handler发送消息来实现的。
 * ——将过期的Toast从List中清除。
 * @Author tinytongtong
 * @Date 2021/1/12 11:37 AM
 */
public class ToastHelper {
    private static final String TAG = ToastHelper.class.getSimpleName();

    /**
     * Toast#TN的中定义的三个常量
     */
    private static final int SHOW = 0;
    private static final int HIDE = 1;
    private static final int CANCEL = 2;

    private static Field sField_tn;
    private static Field sField_tn_handler;

    private static OnToastFinishCallback onToastFinishCallback;

    private static int index = 0;

    private static SparseArray<WeakReference<Toast>> toastArray = new SparseArray<>();

    private ToastHelper() {
        onToastFinishCallback = tag -> {
            // 清理List中过期的Toast
            if (toastArray != null && toastArray.size() > 0) {
                Log.e(TAG, "onToastFinishCallback before remove, toastArray:" + toastArray.size());
                toastArray.remove(tag);
                Log.e(TAG, "onToastFinishCallback before after, toastArray:" + toastArray.size());
            }
        };
        try {
            sField_tn = Toast.class.getDeclaredField("mTN");
            sField_tn.setAccessible(true);
            sField_tn_handler = sField_tn.getType().getDeclaredField("mHandler");
            sField_tn_handler.setAccessible(true);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

    private static Toast createTextViewToast(Context context, String msg) {
//        if (toast != null) {
//            toast.cancel();
//            toast = null;
//        }
        Toast toast = new Toast(context);
        android.view.View view = LayoutInflater.from(context).inflate(R.layout.common_toast_view, null, false);
        android.widget.TextView tvContent = view.findViewById(R.id.tv_content);
        tvContent.setText(msg);
        toast.setView(view);
        toast.setGravity(Gravity.CENTER, 0, 0);
        return toast;
    }

    public static void showToast(Context context, String msg) {
        Toast toast = createTextViewToast(context, msg);
        int tag = index++;
        hook(toast, tag);
        WeakReference<Toast> toastWR = new WeakReference<>(toast);
        toastArray.put(tag, toastWR);
        toast.show();
    }

    public static void cancelAllToast() {
        Log.e(TAG, "cancelAllToast");
        if (toastArray != null && toastArray.size() > 0) {
            Log.e(TAG, "cancelAllToast, toastArray:" + toastArray.size());
            for (int i = 0; i < toastArray.size(); i++) {
                WeakReference<Toast> toastWR = toastArray.valueAt(i);
                if (toastWR != null && toastWR.get() != null) {
                    toastWR.get().cancel();
                    Log.e(TAG, String.format("cancelAllToast, toast:%s cancel", toastWR.get()));
                }
            }
            toastArray.clear();
        }
    }

    private static void hook(Toast toast, int tag) {
        if (toast == null) {
            return;
        }
        try {
            Object tn = sField_tn.get(toast);
            Handler preHandler = (Handler) sField_tn_handler.get(tn);
            SafelyHandlerWrapper safelyHandlerWrapper = new SafelyHandlerWrapper(preHandler, onToastFinishCallback, tag);
            Log.e(TAG, String.format("hook, toast:%s, SafelyHandlerWrapper:%s", toast, safelyHandlerWrapper));
            sField_tn_handler.set(tn, safelyHandlerWrapper);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static class SafelyHandlerWrapper extends Handler {
        private Handler impl;
        private OnToastFinishCallback onToastFinishCallback;
        private int tag;

        public SafelyHandlerWrapper(Handler impl, OnToastFinishCallback onToastFinishCallback, int tag) {
            this.impl = impl;
            this.onToastFinishCallback = onToastFinishCallback;
            this.tag = tag;
        }

        @Override
        public void dispatchMessage(@NonNull Message msg) {
            /**
             * 正常情况下，toast执行了{@link Toast#show()}方法后，会发送两个Message，TN#SHOW和TN#HIDE
             *  ——{@link Toast#TN#SHOW}
             *  ——{@link Toast#TN#HIDE}
             *  ——NMS会先执行TN#show()方法，然后根据显示时间，发送一个延时消息用来执行TN#hide()方法。
             * 如果执行了{@link Toast#cancel()}方法，会发送三个Message，这里分两种情况：
             *  ——Toast已开始展示：TN#SHOW、TN#CANCEL、TN#HIDE。
             *  ——Toast未开始展示：TN#CANCEL、TN#SHOW、TN#HIDE。
             *
             *  结论：最终都会发送HIDE消息。
             *
             *  这里存在一个问题，HIDE消息会被重复
             */
            try {
//                Log.e(TAG, "dispatchMessage, msg:" + msg);
                switch (msg.what) {
                    case SHOW:
                        Log.e(TAG, String.format("dispatchMessage, msg.what:%s, SafelyHandlerWrapper:%s, show", msg.what, this.toString()));
                        break;
                    case HIDE:
                        Log.e(TAG, String.format("dispatchMessage, msg.what:%s, SafelyHandlerWrapper:%s, hide", msg.what, this.toString()));
                        if (onToastFinishCallback != null) {
                            onToastFinishCallback.onFinish(tag);
                        }
                        break;
                    case CANCEL:
                        Log.e(TAG, String.format("dispatchMessage, msg.what:%s, SafelyHandlerWrapper:%s, cancel", msg.what, this.toString()));
                        break;
                    default:
                        break;
                }
                super.dispatchMessage(msg);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @Override
        public void handleMessage(@NonNull Message msg) {
//            Log.e(TAG, "handleMessage, msg:" + msg);
            impl.handleMessage(msg);
        }
    }

    private interface OnToastFinishCallback {
        void onFinish(int tag);
    }
}