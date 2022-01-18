package com.tinytongtong.androidstudy.recyclerview;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.tinytongtong.androidstudy.R;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: RecyclerView嵌套RecyclerView测试
 * @Author tinytongtong
 * @Date 2021/11/25 9:44 AM
 * @Version
 */
public class NestRecyclerViewAdapter extends RecyclerView.Adapter<NestRecyclerViewAdapter.ViewHolder> {
    private static final String TAG = "Adapter-Nest";

    private Context mContext;
    private List<List<PersonBean>> mList;

    public NestRecyclerViewAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public void updateData(List<List<PersonBean>> list) {
        if (mList == null) {
            mList = new ArrayList<>();
        }
        if (list == null) {
            return;
        }
        mList.addAll(list);

        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(mContext).inflate(R.layout.adapter_recyclerview_nest, parent, false);
        ViewHolder vh = new ViewHolder(itemView);
        Log.e(TAG, String.format("onCreateViewHolder vh:%h", vh.hashCode()));
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Log.e(TAG, String.format("onBindViewHolder position:%s, holder:%h", position, holder.hashCode()));
        List<PersonBean> list = mList.get(position);

        holder.btn_show_view1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.container1.setVisibility(View.VISIBLE);
                holder.container2.setVisibility(View.GONE);
            }
        });
        holder.btn_show_view2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.container1.setVisibility(View.GONE);
                holder.container2.setVisibility(View.VISIBLE);
            }
        });
        holder.btn_refresh_view1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.mAdapter1.updateData(list);
            }
        });
        holder.btn_refresh_view2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.mAdapter2.updateData(list);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    @Override
    public long getItemId(int position) {
        return mList.get(position).hashCode();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        private final RecyclerView nestRv1;
        private OnCreateViewHolderAdapter mAdapter1;
        private final RecyclerView nestRv2;
        private OnCreateViewHolderAdapter mAdapter2;
        private Button btn_show_view1;
        private Button btn_show_view2;
        private Button btn_refresh_view1;
        private Button btn_refresh_view2;
        private View container1;
        private View container2;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            btn_show_view1 = itemView.findViewById(R.id.btn_show_view1);
            btn_show_view2 = itemView.findViewById(R.id.btn_show_view2);

            btn_refresh_view1 = itemView.findViewById(R.id.btn_refresh_view1);
            btn_refresh_view2 = itemView.findViewById(R.id.btn_refresh_view2);

            container1 = itemView.findViewById(R.id.nest_container1);
            container2 = itemView.findViewById(R.id.nest_container2);

            nestRv1 = itemView.findViewById(R.id.nest_recyclerView1);
            mAdapter1 = new OnCreateViewHolderAdapter(itemView.getContext());
            nestRv1.setLayoutManager(new LinearLayoutManager(itemView.getContext()));
            nestRv1.setAdapter(mAdapter1);

            nestRv2 = itemView.findViewById(R.id.nest_recyclerView2);
            mAdapter2 = new OnCreateViewHolderAdapter(itemView.getContext());
            nestRv2.setLayoutManager(new LinearLayoutManager(itemView.getContext()));
            nestRv2.setAdapter(mAdapter2);

        }
    }
}
