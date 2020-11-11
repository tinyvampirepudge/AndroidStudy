package com.tinytongtong.androidstudy.templates.adaptertemplate

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.tinytongtong.androidstudy.R

class AdapterTemplateKt(
    private val list: List<PersonListBean>?,
    private val mContext: Context
) : RecyclerView.Adapter<AdapterTemplateKt.ViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val view =
            LayoutInflater.from(mContext).inflate(R.layout.adapter_template, parent, false)
        return ViewHolder(
            view
        )
    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) {
        val bean = list!![position]
        // TODO: set data to holder
        holder.tvName.text = bean.name
    }

    override fun getItemCount(): Int {
        return list?.size ?: 0
    }

    class ViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        var tvName: TextView

        init {
            tvName = itemView.findViewById(R.id.tvName)
        }
    }

}