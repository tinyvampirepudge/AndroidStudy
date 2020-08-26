package com.tinytongtong.androidstudy.draw;

import android.content.Context;
import android.graphics.Canvas;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.ViewGroup;

import androidx.annotation.RequiresApi;

/**
 * @Description: 一个acticity中有一个自定义的ViewGroup 重写这个ViewGroup的onDraw()方法 在里面打印一条数据 activity 启动后 这个log会打印吗
 * @Author tinytongtong
 * @Date 2020/8/26 4:12 PM
 * @Version
 */
public class CustomViewGroup extends ViewGroup {
    private static final String TAG = CustomViewGroup.class.getSimpleName();

    public CustomViewGroup(Context context) {
        super(context);
        Log.e(TAG, "CustomViewGroup#constructor111");
    }

    public CustomViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
        Log.e(TAG, "CustomViewGroup#constructor222");
    }

    public CustomViewGroup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        Log.e(TAG, "CustomViewGroup#constructor333");
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public CustomViewGroup(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        Log.e(TAG, "CustomViewGroup#constructor444");
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        Log.e(TAG, "CustomViewGroup#onLayout");
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Log.e(TAG, "CustomViewGroup#onDraw");
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
        Log.e(TAG, "CustomViewGroup#dispatchDraw");
    }
}
