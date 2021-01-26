package com.tinytongtong.androidstudy.viewpager.adapter;

import android.util.Log;
import android.util.SparseArray;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleEventObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.tinytongtong.androidstudy.viewpager.ViewPagerItemFragment;
import com.tinytongtong.androidstudy.viewpager.ViewPagerParentFragment;
import com.tinytongtong.androidstudy.viewpager.model.TabBean;

import java.util.List;

/**
 * @Description: viewpager2的Adapter
 * @Author tinytongtong
 * @Date 2021/1/22 11:40 AM
 * @Version
 */
public class DemoCollectionAdapter extends FragmentStateAdapter {
    private List<TabBean> list;

    private SparseArray<ViewPagerItemFragment> mFragments = new SparseArray<>();

    public DemoCollectionAdapter(@NonNull Fragment fragment) {
        super(fragment);
    }

    public void setList(List<TabBean> list) {
        this.list = list;
    }

    public SparseArray<ViewPagerItemFragment> getFragments() {
        return mFragments;
    }

    public ViewPagerItemFragment getFragmentByPosition(int position) {
        return position >= 0 && position < mFragments.size() ? mFragments.get(position) : null;
    }

    public SparseArray<ViewPagerItemFragment> logFragments() {
        Log.d(ViewPagerParentFragment.TAG, "Adapter2#logFragments size: " + mFragments.size());
        for (int i = 0; i < mFragments.size(); i++) {
            ViewPagerItemFragment frag = mFragments.get(i);
            Log.d(ViewPagerParentFragment.TAG, String.format("Adapter2#logFragments key: %s, hashCode: %s", i, frag.hashCode()));
        }
        return mFragments;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        Log.d(ViewPagerParentFragment.TAG, "Adapter2#createFragment: " + position);

        ViewPagerItemFragment fragment = ViewPagerItemFragment.newInstance(list.get(position).getType() + "", list.get(position).getTitle());
        fragment.setPosition(position);
        fragment.getLifecycle().addObserver(new LifecycleEventObserver() {
            @Override
            public void onStateChanged(@NonNull LifecycleOwner source, @NonNull Lifecycle.Event event) {
                Log.d(ViewPagerParentFragment.TAG, String.format("Adapter2#onStateChanged source: %s, event: %s", source.hashCode(), event));
                // 销毁时从列表中移除掉
                if (source instanceof ViewPagerItemFragment) {
                    ViewPagerItemFragment sourceFrag = (ViewPagerItemFragment) source;
                    if (Lifecycle.Event.ON_DESTROY == event) {
                        if (mFragments != null && mFragments.size() > 0) {
                            mFragments.remove(sourceFrag.getPosition());
                        }
                    }
                }

            }
        });
        mFragments.put(position, fragment);
        return fragment;
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

}
