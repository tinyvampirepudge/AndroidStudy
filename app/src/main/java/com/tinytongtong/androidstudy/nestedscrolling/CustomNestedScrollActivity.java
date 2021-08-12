package com.tinytongtong.androidstudy.nestedscrolling;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.ViewTreeObserver;

import com.tinytongtong.androidstudy.R;
import com.tinytongtong.tinyutils.LogUtils;
import com.tinytongtong.tinyutils.ScreenUtils;

/**
 * @Description: NestedScrollingParent和NestedScrollingChild实现横向的抽屉效果
 * @Author wangjianzhou
 * @Date 2021/8/12 11:44 AM
 * @Version
 */
public class CustomNestedScrollActivity extends AppCompatActivity {
    private static final String TAG = CustomNestedScrollActivity.class.getSimpleName();
    private CustomNestedScrollParent nested_scroll_parent;
    private CustomNestedScrollChildFrameLayout nested_scroll_child;

    public static void actionStart(Context context) {
        Intent starter = new Intent(context, CustomNestedScrollActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_nested_scroll);

        nested_scroll_parent = findViewById(R.id.nested_scroll_parent);
        nested_scroll_child = findViewById(R.id.nested_scroll_child);


        nested_scroll_parent.addPanelSlideListener(new CustomNestedScrollParent.SlideListener() {
            @Override
            public void onStateChanged(int oldState, int newState) {
                if (newState == CustomNestedScrollParent.STATE_COLLAPSED) {
                    log("onStateChanged STATE_COLLAPSED");
                } else if (newState == CustomNestedScrollParent.STATE_EXPANDED) {
                    log("onStateChanged STATE_EXPANDED");
                } else if (newState == CustomNestedScrollParent.STATE_ANCHORED) {
                    log("onStateChanged STATE_ANCHORED");
                } else if (newState == CustomNestedScrollParent.STATE_SCROLLING) {
                    log("onStateChanged STATE_SCROLLING");
                }
            }

            @Override
            public void onSlideReleased(float xvel, float yvel, int state) {
                log(String.format("onSlideReleased state:%s", state));
            }

            @Override
            public void onScrollOffset(float slideOffset) {
                log(String.format("onScrollOffset slideOffset:%s", slideOffset));
            }
        });

        nested_scroll_parent.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                nested_scroll_parent.getViewTreeObserver().removeOnGlobalLayoutListener(this);

//                final float height = nested_scroll_parent.getMeasuredHeight();
//                final float point = (height - ScreenUtils.dip2px(CustomNestedScrollActivity.this, 347F)) / height;
//                // 需要使用中间态的point当做默认值
//                nested_scroll_parent.setAnchorPoint(point);

                log(String.format("onGlobalLayout setPanelState(CustomNestedScrollParent.STATE_ANCHORED, 0)"));
                // 设置收起状态Panel的高度。
//                nested_scroll_parent.setPanelHeight(ScreenUtils.dip2px(CustomNestedScrollActivity.this, 100));
//                nested_scroll_parent.setPanelState(CustomNestedScrollParent.STATE_ANCHORED, 0);
//                nested_scroll_parent.setPanelState(CustomNestedScrollParent.STATE_EXPANDED, 0);
            }
        });

//        resetPanelState();
    }

    private void resetPanelState() {
        log(String.format("resetPanelState"));
        nested_scroll_parent.setPanelStatePrivate(CustomNestedScrollParent.STATE_ANCHORED);
        nested_scroll_parent.setPanelState(CustomNestedScrollParent.STATE_ANCHORED, 0);
    }

    private void log(String s) {
        LogUtils.INSTANCE.d("NestedScroll-Activity", s);
    }
}