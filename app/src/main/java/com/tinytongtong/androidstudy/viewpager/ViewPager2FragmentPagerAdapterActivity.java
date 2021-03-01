package com.tinytongtong.androidstudy.viewpager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.tinytongtong.androidstudy.R;

/**
 * @Description: ViewPager2 + FragmentPagerAdapter
 * @Author tinytongtong
 * @Date 2021/1/21 8:39 PM
 * @Version
 */
public class ViewPager2FragmentPagerAdapterActivity extends AppCompatActivity {

    public static void actionStart(Context context) {
        Intent starter = new Intent(context, ViewPager2FragmentPagerAdapterActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager2_fragment_pager_adapter);

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        Fragment fragment = ViewPager2ParentFragment.newInstance("ViewPager2", "");
        transaction
                .add(R.id.fl_container, fragment)
                .show(fragment)
                .commitAllowingStateLoss();
    }
}