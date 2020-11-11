package com.tinytongtong.androidstudy.templates.adaptertemplate

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.tinytongtong.androidstudy.R
import com.tinytongtong.androidstudy.templates.adapter.TemplateKtAdapter
import kotlinx.android.synthetic.main.activity_templates_test.*

class TemplatesTestActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_templates_test)

        recyclerView.layoutManager = LinearLayoutManager(this)
        val list = mutableListOf<PersonListBean>()
        for (x in 0..100) {
            var bean = PersonListBean("猫了个咪" + x)
            list.add(bean)
        }
//        val adapter = AdapterTemplate(list, this)
        val adapter = TemplateKtAdapter(list, this)
        recyclerView.adapter = adapter
    }
}