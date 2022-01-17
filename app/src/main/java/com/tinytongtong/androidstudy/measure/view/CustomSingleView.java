package com.tinytongtong.androidstudy.measure.view;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;

import androidx.annotation.Nullable;

public class CustomSingleView extends View {
    private static final String TAG = "CustomLayout-View";


    public CustomSingleView(Context context) {
        super(context);
    }

    public CustomSingleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomSingleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public CustomSingleView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthSpecSize = MeasureSpec.getSize(widthMeasureSpec);
        int widthSpecMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightSpecSize = MeasureSpec.getSize(heightMeasureSpec);
        int heightSpecMode = MeasureSpec.getMode(heightMeasureSpec);
        Log.e(TAG, String.format("onMeasure widthSpecSize:%s, widthSpecMode:%s, heightSpecSize:%s, heightSpecMode:%s",
                widthSpecSize, widthSpecMode, heightSpecSize, heightSpecMode));
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        Log.e(TAG, String.format("onLayout changed:%s, l:%s, t:%s, r:%s, b:%s",
                changed, l, t, r, b));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Log.e(TAG, "onDraw");
    }
}
