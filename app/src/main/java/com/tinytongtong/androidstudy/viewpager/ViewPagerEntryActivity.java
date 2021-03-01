package com.tinytongtong.androidstudy.viewpager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.tinytongtong.androidstudy.R;

/**
 * @Description: viewpager和viewpager2
 * @Author tinytongtong
 * @Date 2021/1/21 8:27 PM
 * @Version
 */
public class ViewPagerEntryActivity extends AppCompatActivity {

    public static void actionStart(Context context) {
        Intent starter = new Intent(context, ViewPagerEntryActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager_entry);

        findViewById(R.id.btnViewPagerTest).setOnClickListener(
                v -> ViewPagerFragmentPagerAdapterActivity.actionStart(this));

        findViewById(R.id.btnViewPager2Test).setOnClickListener(
                v -> ViewPager2FragmentPagerAdapterActivity.actionStart(this));
    }
}