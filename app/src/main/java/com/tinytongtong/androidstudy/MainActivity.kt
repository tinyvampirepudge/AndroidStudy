package com.tinytongtong.androidstudy

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.tinytongtong.androidstudy.activity.lifecycle.ActivityLifecycleActivity
import com.tinytongtong.androidstudy.aidl.BinderTestActivity
import com.tinytongtong.androidstudy.anim.ActivityAnimActivity
import com.tinytongtong.androidstudy.bitmap.BitmapEntryActivity
import com.tinytongtong.androidstudy.dialog.DialogWithVariousCtxActivity
import com.tinytongtong.androidstudy.draw.CustomViewGroupActivity
import com.tinytongtong.androidstudy.drawflow.ViewFlowActivity
import com.tinytongtong.androidstudy.framework.chapter901.TransactionTooLargeExceptionActivity
import com.tinytongtong.androidstudy.jsonparse.JsonParseTestActivity
import com.tinytongtong.androidstudy.textview.TextViewTestActivity
import com.tinytongtong.androidstudy.validateui.ValidateUiInSubThreadActivity
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_bitmap.setOnClickListener {
            startActivity(Intent(this, BitmapEntryActivity::class.java))
        }

        btn_activity_anim.setOnClickListener {
            startActivity(Intent(this, ActivityAnimActivity::class.java))
            // 进入，从下往上动画
            overridePendingTransition(R.anim.slide_in_down, R.anim.slide_out_down)
        }

        btn_activity_validate_ui_in_sub_thread.setOnClickListener {
            startActivity(Intent(this, ValidateUiInSubThreadActivity::class.java))
        }
        btn_activity_binder_test.setOnClickListener {
            startActivity(Intent(this, BinderTestActivity::class.java))
        }

        btn_activity_parse_json_test.setOnClickListener {
            startActivity(Intent(this, JsonParseTestActivity::class.java))
        }

        btn_activity_custom_view_roup.setOnClickListener {
            startActivity(Intent(this, CustomViewGroupActivity::class.java))
        }

        btnTransactionTooLargeException.setOnClickListener {
            startActivity(Intent(this, TransactionTooLargeExceptionActivity::class.java))
        }

        btnDialog.setOnClickListener {
            startActivity(Intent(this, DialogWithVariousCtxActivity::class.java))
        }

        btnViewFlow.setOnClickListener {
            startActivity(Intent(this, ViewFlowActivity::class.java))
        }

        btnActLifecycle.setOnClickListener {
            startActivity(Intent(this, ActivityLifecycleActivity::class.java))
        }

        btnTextViewTest.setOnClickListener {
            startActivity(Intent(this, TextViewTestActivity::class.java))
        }
    }
}