package com.tinytongtong.androidstudy.viewpager.adapter;

import android.util.Log;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.tinytongtong.androidstudy.viewpager.ViewPagerItemFragment;
import com.tinytongtong.androidstudy.viewpager.ViewPagerParentFragment;
import com.tinytongtong.androidstudy.viewpager.model.TabBean;

import java.util.List;

/**
 * @Description:
 * @Author tinytongtong
 * @Date 2021/1/22 10:15 AM
 * @Version
 */
public class DemoCollectionPagerAdapter extends FragmentPagerAdapter {
    private List<TabBean> list;

    public DemoCollectionPagerAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }


    public void setList(List<TabBean> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        Log.d(ViewPagerParentFragment.TAG, "Adapter#instantiateItem");
        return super.instantiateItem(container, position);
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        Log.d(ViewPagerParentFragment.TAG, "Adapter#destroyItem");
        super.destroyItem(container, position, object);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        Log.d(ViewPagerParentFragment.TAG, "Adapter#getItem: " + position);
        return ViewPagerItemFragment.newInstance(list.get(position).getType() + "", list.get(position).getTitle());
    }

    @Override
    public int getCount() {
        return list == null ? 0 : list.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return list.get(position).getTitle();
    }
}
