package com.tinytongtong.androidstudy.recyclerview;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.tinytongtong.androidstudy.R;

import java.util.List;

/**
 * @Description:
 * @Author tinytongtong
 * @Date 2021/2/28 9:14 PM
 * @Version
 */
public class OnCreateViewHolderAdapter extends RecyclerView.Adapter<OnCreateViewHolderAdapter.ViewHolder> {
    private static final String TAG = "Adapter-Adapter";

    private Context mContext;
    private List<PersonBean> mList;

    public OnCreateViewHolderAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public OnCreateViewHolderAdapter(Context mContext, List<PersonBean> mList) {
        this.mContext = mContext;
        this.mList = mList;
    }

    public void updateData(List<PersonBean> mList) {
        this.mList = mList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(mContext).inflate(R.layout.adapter_recyclerview, parent, false);
        ViewHolder vh = new ViewHolder(itemView);
        Log.e(TAG, String.format("onCreateViewHolder vh:%h", vh.hashCode()));
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Log.e(TAG, String.format("onBindViewHolder position:%s, holder:%h", position, holder.hashCode()));
        PersonBean bean = mList.get(position);
        holder.tvName.setText(bean.getName());
        holder.tvAge.setText(String.valueOf(bean.getAge()));
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

        private final TextView tvName;
        private final TextView tvAge;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tv_name);
            tvAge = itemView.findViewById(R.id.tv_age);
        }
    }
}
