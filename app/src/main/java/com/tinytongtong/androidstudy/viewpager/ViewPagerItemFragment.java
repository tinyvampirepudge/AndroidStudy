package com.tinytongtong.androidstudy.viewpager;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleEventObserver;
import androidx.lifecycle.LifecycleOwner;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.tinytongtong.androidstudy.R;
import com.tinytongtong.androidstudy.toast.ToastTestActivity;

/**
 *
 */

/**
 * @Description: FragmentPagerAdapter中的Fragment
 *
 * A simple {@link Fragment} subclass.
 * Use the {@link ViewPagerItemFragment#newInstance} factory method to
 * create an instance of this fragment.
 *
 * @Author tinytongtong
 * @Date 2021/1/21 8:43 PM
 * @Version
 */
public class ViewPagerItemFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private int position = -1;

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public ViewPagerItemFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ViewPagerItemFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ViewPagerItemFragment newInstance(String param1, String param2) {
        ViewPagerItemFragment fragment = new ViewPagerItemFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(ViewPagerParentFragment.TAG, String.format("Child#%s, onCreate", hashCode()));
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d(ViewPagerParentFragment.TAG, String.format("Child#%s, onCreateView", hashCode()));
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_view_pager_item, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.d(ViewPagerParentFragment.TAG, String.format("Child#%s, onViewCreated", hashCode()));
        Button btn = view.findViewById(R.id.btn);
        btn.setText(String.format("type: %s, title: %s", mParam1, mParam2));
        btn.setOnClickListener(v -> ToastTestActivity.actionStart(requireContext()));

        view.findViewById(R.id.btn_frag).setOnClickListener(v -> {
            FragmentTransaction transaction = ((FragmentActivity) requireContext())
                    .getSupportFragmentManager()
                    .beginTransaction();
            Fragment fragment = BlankFragment.newInstance();
            transaction
//                    .replace(android.R.id.content, fragment)
                    .add(android.R.id.content, fragment)
//                    .show(fragment)
                    .addToBackStack("BlankFragment")
                    .commitAllowingStateLoss();
        });
    }

    @Override
    public void onStart() {
        Log.d(ViewPagerParentFragment.TAG, String.format("Child#%s, onStart", hashCode()));
        super.onStart();
    }

    @Override
    public void onResume() {
        Log.d(ViewPagerParentFragment.TAG, String.format("Child#%s, onResume", hashCode()));
        super.onResume();
    }

    @Override
    public void onPause() {
        Log.d(ViewPagerParentFragment.TAG, String.format("Child#%s, onPause", hashCode()));
        super.onPause();
    }

    @Override
    public void onStop() {
        Log.d(ViewPagerParentFragment.TAG, String.format("Child#%s, onStop", hashCode()));
        super.onStop();
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        Log.d(ViewPagerParentFragment.TAG, String.format("Child#%s, setUserVisibleHint: %s", hashCode(), isVisibleToUser));
        super.setUserVisibleHint(isVisibleToUser);
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        Log.d(ViewPagerParentFragment.TAG, String.format("Child#%s, onHiddenChanged: %s", hashCode(), hidden));
        super.onHiddenChanged(hidden);
    }

    @Override
    public void onDestroyView() {
        Log.d(ViewPagerParentFragment.TAG, String.format("Child#%s, onDestroyView", hashCode()));
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        Log.d(ViewPagerParentFragment.TAG, String.format("Child#%s, onDestroy", hashCode()));
        super.onDestroy();
    }

}