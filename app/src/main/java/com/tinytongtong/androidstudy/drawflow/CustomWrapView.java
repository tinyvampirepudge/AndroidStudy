package com.tinytongtong.androidstudy.drawflow;

import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.tinytongtong.tinyutils.ScreenUtils;

/**
 * @Description: 直接继承View的自定义控件需要重写onMeasure方法并设置wrap_content时的自身大小，否则在布局中使用wrap_content就相当于使用match_parent。
 * 具体解决方式是给View指定一个默认的内部宽/高（mWidth或mHeight），并在wrap_content时设置此宽高。
 * @Author tinytongtong
 * @Date 2020/10/26 9:53 AM
 * @Version
 */
public class CustomWrapView extends View {
    private static final String TAG = CustomWrapView.class.getSimpleName();

    private Context mContext;
    private int mWidth;
    private int mHeight;

    public CustomWrapView(Context context) {
        super(context);
        init(context);
    }

    public CustomWrapView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public CustomWrapView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public CustomWrapView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }

    private void init(Context context) {
        mContext = context;
        mWidth = ScreenUtils.dip2px(mContext, 100);
        mHeight = ScreenUtils.dip2px(mContext, 200);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        Log.e(TAG, "onMeasure");
        // 其他情况调用
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        // 处理宽或高是wrap_content的情况
        int widthSpecMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSpecSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSpecMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSpecSize = MeasureSpec.getSize(heightMeasureSpec);
        if (widthSpecMode == MeasureSpec.AT_MOST && heightSpecMode == MeasureSpec.AT_MOST) {
            setMeasuredDimension(mWidth, mHeight);
        } else if (widthSpecMode == MeasureSpec.AT_MOST) {
            setMeasuredDimension(mWidth, heightSpecSize);
        } else if (heightSpecMode == MeasureSpec.AT_MOST) {
            setMeasuredDimension(widthSpecSize, mHeight);
        }
    }
}
