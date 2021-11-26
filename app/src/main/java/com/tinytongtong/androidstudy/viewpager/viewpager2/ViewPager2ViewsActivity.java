package com.tinytongtong.androidstudy.viewpager.viewpager2;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.tinytongtong.androidstudy.R;
import com.tinytongtong.androidstudy.viewpager.viewpager2.adapter.ViewPager2ViewAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: ViewPager2 + RecyclerView.Adapter + View
 * @Author tinytongtong
 * @Date 2021/11/25 2:08 PM
 * @Version
 */
public class ViewPager2ViewsActivity extends AppCompatActivity {
    private static final String TAG = "ViewPager2-Activity";

    private TabLayout tabLayout;
    private ImageView ivSetting;
    private ViewPager2 viewPager2;
    private ViewPager2ViewAdapter adapter;

    private int selectedPos = -1;

    public static void actionStart(Context context) {
        Intent starter = new Intent(context, ViewPager2ViewsActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager2_views);

        tabLayout = findViewById(R.id.tabLayout);
        ivSetting = findViewById(R.id.iv_setting);
        viewPager2 = findViewById(R.id.viewPager2);
        List<String> list = getData();
        adapter = new ViewPager2ViewAdapter(this, list);
        viewPager2.setOffscreenPageLimit(list.size() - 1);
        viewPager2.setAdapter(adapter);
        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);
            }

            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                selectedPos = position;
                Log.d(TAG, String.format("onPageSelected: %s", position));
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                super.onPageScrollStateChanged(state);
            }
        });
        viewPager2.addOnAttachStateChangeListener(new View.OnAttachStateChangeListener() {
            @Override
            public void onViewAttachedToWindow(View v) {

            }

            @Override
            public void onViewDetachedFromWindow(View v) {

            }
        });

        tabLayout.setTag(R.id.tag_vp2_list, list);
        // viewpager2 和 tablayout联动
        new TabLayoutMediator(tabLayout, viewPager2, (tab, position) -> {
            List<String> listTemp = (List<String>) tabLayout.getTag(R.id.tag_vp2_list);
            tab.setText(listTemp.get(position));
        }).attach();
        ivSetting.setOnClickListener(v -> {
                    Log.d(TAG, "Parent2# 点击设置");
//                    refreshTabFragment();
                }
        );
    }

    private List<String> getData() {
        List<String> result = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            result.add(String.format("我是第%s个页面", i));
        }
        return result;
    }
}