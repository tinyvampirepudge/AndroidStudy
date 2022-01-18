package com.tinytongtong.androidstudy.recyclerview;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.tinytongtong.androidstudy.R;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: RecyclerView嵌套RecyclerView测试，内部的是否复用
 * @Author tinytongtong
 * @Date 2021/11/25 9:43 AM
 * @Version
 */
public class RecyclerViewNestTestActivity extends AppCompatActivity {
    private static final String TAG = RecyclerViewNestTestActivity.class.getSimpleName();

    private RecyclerView mRecyclerView;
    private NestRecyclerViewAdapter mAdapter;
    Handler mHandler = new Handler(Looper.getMainLooper());

    public static void actionStart(Context context) {
        Intent starter = new Intent(context, RecyclerViewNestTestActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view_nest_test);

        mAdapter = new NestRecyclerViewAdapter(this);
        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mAdapter);

        findViewById(R.id.btn_show_rv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean show = mRecyclerView.getVisibility() == View.VISIBLE;
                if (show) {
                    mRecyclerView.setVisibility(View.GONE);
                } else {
                    mRecyclerView.setVisibility(View.VISIBLE);
                }
            }
        });
        findViewById(R.id.btn_refresh_rv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAdapter.updateData(genListData(20));
            }
        });
    }

    private List<List<PersonBean>> genListData(int size) {
        List<List<PersonBean>> result = new ArrayList<>();
        for (int m = 0; m < size; m++) {
            List<PersonBean> list = new ArrayList<>();
            for (int n = 0; n < 10; n++) {
                PersonBean pb = new PersonBean(String.format("王蛋蛋,第%s个Item的第%s个元素", m, n), m + n);
                list.add(pb);
            }
            result.add(list);
        }
        return result;
    }

}