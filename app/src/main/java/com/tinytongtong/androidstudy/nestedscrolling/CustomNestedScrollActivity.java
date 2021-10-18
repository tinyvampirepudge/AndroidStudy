package com.tinytongtong.androidstudy.nestedscrolling;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Space;

import androidx.appcompat.app.AppCompatActivity;

import com.tinytongtong.androidstudy.R;
import com.tinytongtong.tinyutils.LogUtils;

/**
 * @Description: NestedScrollingParent和NestedScrollingChild实现横向的抽屉效果
 * @Author wangjianzhou
 * @Date 2021/8/12 11:44 AM
 * @Version
 */
public class CustomNestedScrollActivity extends AppCompatActivity {
    private static final String TAG = CustomNestedScrollActivity.class.getSimpleName();
    private CustomDrawerLayoutParent nested_scroll_parent;
    private CustomDrawerLayoutChild nested_scroll_child;
    private Space space;

    public static void actionStart(Context context) {
        Intent starter = new Intent(context, CustomNestedScrollActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_nested_scroll);

        space = findViewById(R.id.space);

        nested_scroll_parent = findViewById(R.id.nested_scroll_parent);
        nested_scroll_child = findViewById(R.id.nested_scroll_child);

        findViewById(R.id.btn_expand).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nested_scroll_parent.setExpandState();
            }
        });
        findViewById(R.id.btn_collapse).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nested_scroll_parent.setCollapseState();
            }
        });
        findViewById(R.id.btn_width_match_parent).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                space.setVisibility(View.GONE);
                nested_scroll_parent.post(() -> refreshState());
            }
        });
        findViewById(R.id.btn_width_match_half).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                space.setVisibility(View.VISIBLE);
                nested_scroll_parent.post(() -> refreshState());
            }
        });

        nested_scroll_parent.addPanelSlideListener(new CustomDrawerLayoutParent.DrawerListener() {
            @Override
            public void onStateChanged(int oldState, int newState) {
                if (newState == 0) {
                    log("onStateChanged STATE_COLLAPSED");
                } else if (newState == 1) {
                    log("onStateChanged STATE_EXPANDED");
                } else if (newState == 4) {
                    log("onStateChanged STATE_SCROLLING");
                } else if (newState == 5) {
                    log("onStateChanged STATE_SCROLL_DOWN_BOUND");
                } else if (newState == 6) {
                    log("onStateChanged STATE_SCROLL_TO_EXPANDED");
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

//                final float height = mDrawerParent.getMeasuredHeight();
//                final float point = (height - DGCScreenUtil.dip2px(getContext(), 347F)) / height;

                // 默认收起状态
//                nested_scroll_parent.setCollapseState();
            }
        });
    }

    private void refreshState() {
        log(String.format("refreshState current panelState:%s", nested_scroll_parent.getPanelState()));
        nested_scroll_parent.setPanelState(nested_scroll_parent.getPanelState(), 0);
    }

    private void log(String s) {
        LogUtils.INSTANCE.d("NestedScroll-Activity", s);
    }
}