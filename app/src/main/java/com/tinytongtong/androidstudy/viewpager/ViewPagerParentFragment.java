package com.tinytongtong.androidstudy.viewpager;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.tinytongtong.androidstudy.R;
import com.tinytongtong.androidstudy.viewpager.adapter.Viewpager2FragmentPagerAdapter;
import com.tinytongtong.androidstudy.viewpager.model.TabBean;
import com.tinytongtong.androidstudy.viewpager.model.TabBeanFactory;

import java.util.List;


/**
 * @Description: Fragment中使用ViewPager
 * <p>
 * A simple {@link Fragment} subclass.
 * Use the {@link ViewPagerParentFragment#newInstance} factory method to
 * create an instance of this fragment.
 * @Author tinytongtong
 * @Date 2021/1/21 8:42 PM
 * @Version
 */
public class ViewPagerParentFragment extends Fragment {
    public static final String TAG = "ViewPagerTest";

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private TabLayout tabLayout;
    private ImageView ivSetting;
    private ViewPager viewPager;
    private Viewpager2FragmentPagerAdapter pagerAdapter;

    public ViewPagerParentFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ViewPagerParentFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ViewPagerParentFragment newInstance(String param1, String param2) {
        ViewPagerParentFragment fragment = new ViewPagerParentFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "Parent#onCreate");
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d(TAG, "Parent#onCreateView");
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_view_pager_parent, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.d(TAG, "Parent#onViewCreated");
        tabLayout = view.findViewById(R.id.tabLayout);
        ivSetting = view.findViewById(R.id.iv_setting);
        viewPager = view.findViewById(R.id.viewPager);
        pagerAdapter = new Viewpager2FragmentPagerAdapter(getChildFragmentManager());
        List<TabBean> tabBeanList = TabBeanFactory.getTabData();
        pagerAdapter.setList(tabBeanList);
        viewPager.setOffscreenPageLimit(tabBeanList.size() - 1);
        viewPager.setAdapter(pagerAdapter);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                Log.d(TAG, String.format("Parent#onPageSelected: %s", position));
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        tabLayout.setTag(R.id.tag_vp2_list, tabBeanList);
        tabLayout.setupWithViewPager(viewPager);
        ivSetting.setOnClickListener(v -> {
                    Log.d(TAG, "Parent# 点击设置");
                    refreshTabFragment();
                }
        );
    }

    private void refreshTabFragment() {
        List<TabBean> tabBeanList = TabBeanFactory.getTabData();

        // 设置tab的角标。同步设置Fragment
        int origIndex = tabLayout.getSelectedTabPosition();
        if (origIndex >= tabBeanList.size()) {
            viewPager.setCurrentItem(0);
        }

        pagerAdapter.setList(tabBeanList);
        pagerAdapter.notifyDataSetChanged();
        viewPager.setOffscreenPageLimit(tabBeanList.size() - 1);
    }

    @Override
    public void onDestroyView() {
        Log.d(TAG, "Parent#onDestroyView");
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        Log.d(TAG, "Parent#onDestroy");
        super.onDestroy();
    }
}