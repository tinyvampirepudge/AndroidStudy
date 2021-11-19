package com.tinytongtong.androidstudy.viewpager;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.tinytongtong.androidstudy.R;
import com.tinytongtong.androidstudy.viewpager.adapter.Viewpager2FragmentStateAdapter;
import com.tinytongtong.androidstudy.viewpager.model.TabBean;
import com.tinytongtong.androidstudy.viewpager.model.TabBeanFactory;

import java.util.List;

/**
 * @Description: Fragment中使用ViewPager2
 * A simple {@link Fragment} subclass.
 * Use the {@link ViewPager2ParentFragment#newInstance} factory method to
 * create an instance of this fragment.
 * @Author tinytongtong
 * @Date 2021/1/22 11:30 AM
 * @Version
 */
public class ViewPager2ParentFragment extends Fragment {
    public static final String TAG = "ViewPagerTest";

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private Button btnInfo;
    private Button btnTopInfo;
    private TabLayout tabLayout;
    private ImageView ivSetting;
    private ViewPager2 viewPager2;
    private Viewpager2FragmentStateAdapter adapter;

    private int selectedPos = -1;

    public ViewPager2ParentFragment() {
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
    public static ViewPager2ParentFragment newInstance(String param1, String param2) {
        ViewPager2ParentFragment fragment = new ViewPager2ParentFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "Parent2#onCreate");
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d(TAG, "Parent2#onCreateView");
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_view_pager2_parent, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.d(TAG, "Parent2#onViewCreated");
        btnInfo = view.findViewById(R.id.btn_info);
        btnTopInfo = view.findViewById(R.id.btn_top_info);
        tabLayout = view.findViewById(R.id.tabLayout);
        ivSetting = view.findViewById(R.id.iv_setting);
        viewPager2 = view.findViewById(R.id.viewPager2);
        adapter = new Viewpager2FragmentStateAdapter(this);
        List<TabBean> tabBeanList = TabBeanFactory.getTabData();
        adapter.setList(tabBeanList);
        viewPager2.setOffscreenPageLimit(tabBeanList.size() - 1);
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
                Log.d(TAG, String.format("Parent2#onPageSelected: %s", position));
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

        tabLayout.setTag(R.id.tag_vp2_list, tabBeanList);
        // viewpager2 和 tablayout联动
        new TabLayoutMediator(tabLayout, viewPager2, (tab, position) -> {
            List<TabBean> list = (List<TabBean>) tabLayout.getTag(R.id.tag_vp2_list);
            tab.setText(list.get(position).getTitle());
        }).attach();
        ivSetting.setOnClickListener(v -> {
                    Log.d(TAG, "Parent2# 点击设置");
                    refreshTabFragment();
                }
        );
        btnInfo.setOnClickListener(v -> {
            if (adapter != null) {
                adapter.logFragments();
            }
        });

        btnTopInfo.setOnClickListener(v -> {
            if (adapter != null) {
                ViewPagerItemFragment fragment = adapter.getFragmentByPosition(viewPager2.getCurrentItem());
                Log.d(ViewPagerParentFragment.TAG, String.format("当前Fragment viewPager2.getCurrentItem(): %s, fragment.getPosition(): %s, hashCode: %s", viewPager2.getCurrentItem(), fragment.getPosition(), fragment.hashCode()));
            }
        });
    }

    private void refreshTabFragment() {
        List<TabBean> tabBeanList = TabBeanFactory.getTabData();
        tabLayout.setTag(R.id.tag_vp2_list, tabBeanList);

        // 设置tab的角标。同步设置Fragment
        int origIndex = tabLayout.getSelectedTabPosition();
        if (origIndex >= tabBeanList.size()) {
            viewPager2.setCurrentItem(0);
        }

        adapter.setList(tabBeanList);
        adapter.notifyDataSetChanged();
        viewPager2.setOffscreenPageLimit(tabBeanList.size() - 1);
        System.out.println(tabBeanList);
    }


    @Override
    public void onStart() {
        Log.d(ViewPagerParentFragment.TAG, String.format("Parent2#%s, onStart", hashCode()));
        super.onStart();
    }

    @Override
    public void onResume() {
        Log.d(ViewPagerParentFragment.TAG, String.format("Parent2#%s, onResume", hashCode()));
        super.onResume();
    }

    @Override
    public void onPause() {
        Log.d(ViewPagerParentFragment.TAG, String.format("Parent2#%s, onPause", hashCode()));
        super.onPause();
    }

    @Override
    public void onStop() {
        Log.d(ViewPagerParentFragment.TAG, String.format("Parent2#%s, onStop", hashCode()));
        super.onStop();
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        Log.d(ViewPagerParentFragment.TAG, String.format("Parent2#%s, setUserVisibleHint: %s", hashCode(), isVisibleToUser));
        super.setUserVisibleHint(isVisibleToUser);
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        Log.d(ViewPagerParentFragment.TAG, String.format("Parent2#%s, onHiddenChanged: %s", hashCode(), hidden));
        super.onHiddenChanged(hidden);
    }


    @Override
    public void onDestroyView() {
        Log.d(TAG, "Parent2#onDestroyView");
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        Log.d(TAG, "Parent2#onDestroy");
        super.onDestroy();
    }
}