package com.tinytongtong.androidstudy.recyclerview;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.tinytongtong.androidstudy.R;

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

    public NestRecyclerViewAdapter(Context mContext, List<List<PersonBean>> mList) {
        this.mContext = mContext;
        this.mList = mList;
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
        holder.mAdapter.updateData(list);
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

        private final RecyclerView nestRv;
        private OnCreateViewHolderAdapter mAdapter;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nestRv = itemView.findViewById(R.id.nest_recyclerView);
            mAdapter = new OnCreateViewHolderAdapter(itemView.getContext());
            nestRv.setLayoutManager(new LinearLayoutManager(itemView.getContext()));
            nestRv.setAdapter(mAdapter);
        }
    }
}
