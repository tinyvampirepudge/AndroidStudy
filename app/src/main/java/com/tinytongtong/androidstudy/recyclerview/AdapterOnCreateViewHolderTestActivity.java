package com.tinytongtong.androidstudy.recyclerview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;

import com.tinytongtong.androidstudy.R;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: RecyclerView复用测试
 * @Author tinytongtong
 * @Date 2021/2/28 6:34 PM
 * @Version
 */
public class AdapterOnCreateViewHolderTestActivity extends AppCompatActivity {
    private static final String TAG = "Adapter-Activity";

    private RecyclerView mRecyclerView;
    private OnCreateViewHolderAdapter mAdapter;
    Handler mHandler = new Handler(Looper.getMainLooper());

    public static void actionStart(Context context) {
        Intent starter = new Intent(context, AdapterOnCreateViewHolderTestActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view_on_create_view_holder_test);

        findViewById(R.id.btn_notifyDataChanged).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mAdapter != null) {
                    Log.e(TAG, "模拟两次刷新页面");
                    // 模拟两次刷新页面
                    mAdapter.notifyDataSetChanged();
//                    mHandler.postDelayed(() -> mAdapter.notifyDataSetChanged(), 200);
                }
            }
        });
        mAdapter = new OnCreateViewHolderAdapter(this, genListData(20));
//        mAdapter.setHasStableIds(true);
        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mAdapter);
    }

    private List<PersonBean> genListData(int size) {
        List<PersonBean> result = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            PersonBean pb = new PersonBean("王蛋蛋" + i, i);
            result.add(pb);
        }
        return result;
    }

}