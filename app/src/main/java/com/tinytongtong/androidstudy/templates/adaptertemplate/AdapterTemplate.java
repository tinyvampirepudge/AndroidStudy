package com.tinytongtong.androidstudy.templates.adaptertemplate;

import android.content.Context;
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
 * @Date 2020/11/10 1:41 PM
 * @Version
 */
public class AdapterTemplate extends RecyclerView.Adapter<AdapterTemplate.ViewHolder> {

    private List<PersonListBean> list;
    private Context mContext;

    public AdapterTemplate(List<PersonListBean> list, Context mContext) {
        this.list = list;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.adapter_template, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        PersonListBean bean = list.get(position);
        // TODO: set data to holder
        holder.tvName.setText(bean.getName());
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvName);
        }
    }
}
