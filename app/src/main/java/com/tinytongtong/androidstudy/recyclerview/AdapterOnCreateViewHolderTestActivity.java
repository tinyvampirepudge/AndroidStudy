package com.tinytongtong.androidstudy.recyclerview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
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
        findViewById(R.id.btn_notifyItemChanged).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mAdapter != null) {
                    mAdapter.notifyItemChanged(4);
                }
            }
        });
        mAdapter = new OnCreateViewHolderAdapter(this, genListData(20));
//        mAdapter.setHasStableIds(true);
        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mAdapter);

        /**
         * 既然是动画，就会有时间，我们把动画执行时间变大一点来看一看效果
         */
        DefaultItemAnimator defaultItemAnimator = new DefaultItemAnimator();
        defaultItemAnimator.setAddDuration(1000);
        defaultItemAnimator.setRemoveDuration(1000);
        mRecyclerView.setItemAnimator(defaultItemAnimator);

        findViewById(R.id.btn_add).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mAdapter != null) {
                    Log.e(TAG, "模拟两次刷新页面");
                    mAdapter.addData(1,new PersonBean("我是动态添加数据",10086));
                }
            }
        });

        findViewById(R.id.btn_remove).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mAdapter != null) {
                    Log.e(TAG, "模拟两次刷新页面");
                    mAdapter.removeData(1);
                }
            }
        });
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