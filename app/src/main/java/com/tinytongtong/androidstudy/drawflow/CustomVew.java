package com.tinytongtong.androidstudy.drawflow;

import android.content.Context;
import android.graphics.Canvas;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

/**
 * @Description: onMeasure和onLayout多次调用
 * @Author tinytongtong
 * @Date 2020/10/26 4:53 PM
 * @Version
 */
public class CustomVew extends View {
    private static final String TAG = CustomVew.class.getSimpleName();

    public CustomVew(Context context) {
        super(context);
    }

    public CustomVew(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomVew(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public CustomVew(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        Log.e(TAG, "onMeasure");
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        Log.e(TAG, "onLayout");
        super.onLayout(changed, left, top, right, bottom);
    }

    @Override
    public void draw(Canvas canvas) {
        Log.e(TAG, "draw");
        super.draw(canvas);
    }
}
