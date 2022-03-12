package com.tinytongtong.androidstudy.viewpager.viewpager2.adapter;

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
 * @Author wangjianzhou
 * @Date 2021/11/25 2:12 PM
 */
public class ViewPager2ViewAdapter extends RecyclerView.Adapter<ViewPager2ViewAdapter.VH> {
    private static final String TAG = "ViewPager2-View";

    private Context mContext;
    private List<String> mList;

    public ViewPager2ViewAdapter(Context context, List<String> list) {
        this.mContext = context;
        this.mList = list;
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.d(TAG, String.format("onCreateViewHolder"));
        View view = LayoutInflater.from(mContext).inflate(R.layout.adapter_vp2_view, parent, false);
        return new VH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
        Log.d(TAG, String.format("onBindViewHolder position:%s", position));
        holder.tv.setText(mList.get(position));
    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    static class VH extends RecyclerView.ViewHolder {
        private final TextView tv;

        public VH(@NonNull View itemView) {
            super(itemView);
            tv = itemView.findViewById(R.id.tv);
        }
    }
}
