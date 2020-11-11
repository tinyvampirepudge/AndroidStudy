package com.tinytongtong.androidstudy.templates.show;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.tinytongtong.androidstudy.R;
import com.tinytongtong.androidstudy.templates.adaptertemplate.PersonListBean;

import java.util.List;

public class CustomTemplateAdapter extends RecyclerView.Adapter<CustomTemplateAdapter.ViewHolder> {

    private List<PersonListBean> list;
    private Context mContext;

    public CustomTemplateAdapter(List<PersonListBean> list, Context mContext) {
        this.list = list;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.adapter_custom_template, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        PersonListBean bean = list.get(position);
        // TODO: set data to holder
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
