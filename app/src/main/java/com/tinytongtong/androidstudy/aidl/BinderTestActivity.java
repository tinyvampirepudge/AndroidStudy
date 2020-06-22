package com.tinytongtong.androidstudy.aidl;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.tinytongtong.androidstudy.R;
import com.tinytongtong.bindertest.IMyAidlInterface;

public class BinderTestActivity extends AppCompatActivity {
    public static final String TAG = BinderTestActivity.class.getSimpleName();

    private ServiceConnection conn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.e(TAG, "onServiceConnected");
            IMyAidlInterface iMyAidlInterface = IMyAidlInterface.Stub.asInterface(service);
            new Thread(() -> {
                try {
                    iMyAidlInterface.basicTypes(1, 2, true, 3, 4, "abc");
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }).start();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.e(TAG, "onServiceDisconnected");
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_binder_test);
        Intent intent = new Intent();
        // 这两行
//        intent.setAction("com.tinytongtong.bindertest.aidl.action.AIDL_SERVICE");
//        intent.setPackage("com.tinytongtong.bindertest");
        // 或者这行
        intent.setComponent(new ComponentName("com.tinytongtong.bindertest", "com.tinytongtong.bindertest.MyService"));

        findViewById(R.id.btn_binder_start).setOnClickListener(v -> {
            // 启动远程的服务
            ComponentName componentName = startService(intent);
            Log.e(TAG, "start componentName:" + componentName);
        });

        findViewById(R.id.btn_binder_bind).setOnClickListener(v -> {
            // 绑定远程的服务
            bindService(intent, conn, Service.BIND_AUTO_CREATE);
        });

        findViewById(R.id.btn_binder_unbind).setOnClickListener(v -> {
            // 解除绑定远程的服务
            unbindService(conn);
        });

        findViewById(R.id.btn_binder_stop).setOnClickListener(v -> {
            // 结束远程的服务
            boolean result = stopService(intent);
            Log.e(TAG, "stop result:" + result);
        });
    }
}