package com.tinytongtong.androidstudy

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.github.lzyzsd.jsbridge.BridgeHandler
import com.github.lzyzsd.jsbridge.BridgeWebView
import com.github.lzyzsd.jsbridge.CallBackFunction
import com.tinytongtong.androidstudy.activity.lifecycle.ActivityLifecycleActivity
import com.tinytongtong.androidstudy.aidl.BinderTestActivity
import com.tinytongtong.androidstudy.anim.ActivityAnimActivity
import com.tinytongtong.androidstudy.anim.breath.BreathAnimActivity
import com.tinytongtong.androidstudy.bitmap.BitmapEntryActivity
import com.tinytongtong.androidstudy.componentcallbacks.ComponentCallbacksActivity
import com.tinytongtong.androidstudy.constraintlayout.ConstraintLayoutTestActivity
import com.tinytongtong.androidstudy.dialog.DialogWithVariousCtxActivity
import com.tinytongtong.androidstudy.draw.CustomViewGroupActivity
import com.tinytongtong.androidstudy.drawflow.ViewFlowActivity
import com.tinytongtong.androidstudy.eventbus.EventBusActivity
import com.tinytongtong.androidstudy.framework.chapter901.TransactionTooLargeExceptionActivity
import com.tinytongtong.androidstudy.glide.GlideTestActivity
import com.tinytongtong.androidstudy.handler.HandlerTestActivity
import com.tinytongtong.androidstudy.jsonparse.JsonParseTestActivity
import com.tinytongtong.androidstudy.nestedscrolling.CustomNestedScrollActivity
import com.tinytongtong.androidstudy.recyclerview.RecyclerViewEntryActivity
import com.tinytongtong.androidstudy.rxjava.RxJavaActivity
import com.tinytongtong.androidstudy.screenadapt.ScreenAdaptActivity
import com.tinytongtong.androidstudy.templates.adaptertemplate.TemplatesTestActivity
import com.tinytongtong.androidstudy.textview.TextViewTestActivity
import com.tinytongtong.androidstudy.toast.ToastTestActivity
import com.tinytongtong.androidstudy.validateui.ValidateUiInSubThreadActivity
import com.tinytongtong.androidstudy.viewpager.ViewPagerEntryActivity
import com.tinytongtong.mylibrary.MyLibraryActivity
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

        // BridgeWebView源码查看
        val wv: BridgeWebView = BridgeWebView(this)
        wv.setDefaultHandler(object : BridgeHandler {
            override fun handler(data: String?, function: CallBackFunction?) {
                TODO("Not yet implemented")
            }
        })

        btnScreenAdapt.setOnClickListener {
            startActivity(Intent(this, ScreenAdaptActivity::class.java))
        }

        btnHandlerTest.setOnClickListener {
            startActivity(Intent(this, HandlerTestActivity::class.java))
        }

        btnGlideStudy.setOnClickListener {
            startActivity(Intent(this, GlideTestActivity::class.java))
        }

        btnRxJavaTest.setOnClickListener {
            startActivity(Intent(this, RxJavaActivity::class.java))
        }

        btnTemplateTest.setOnClickListener {
            startActivity(Intent(this, TemplatesTestActivity::class.java))
        }

        btnEventBusTest.setOnClickListener {
            startActivity(Intent(this, EventBusActivity::class.java))
        }

        btnMyLibraryTest.setOnClickListener {
            startActivity(Intent(this, MyLibraryActivity::class.java))
        }

        btnToastTest.setOnClickListener {
            startActivity(Intent(this, ToastTestActivity::class.java))
        }

        btnViewPagerTest.setOnClickListener {
            ViewPagerEntryActivity.actionStart(this)
        }

        btnRecyclerViewTest.setOnClickListener {
            RecyclerViewEntryActivity.actionStart(this)
        }

        // 呼吸动画
        btn_breath_anim.setOnClickListener {
            startActivity(Intent(this, BreathAnimActivity::class.java))
        }

        // 添加ComponentCallbacks监听
        btn_component_callback.setOnClickListener {
            startActivity(Intent(this, ComponentCallbacksActivity::class.java))
        }

        // NestedScroll组件实现横向抽屉效果
        btn_nested_scroll.setOnClickListener {
            CustomNestedScrollActivity.actionStart(this)
        }

        // ConstraintLayout
        btn_constraint_layout.setOnClickListener {
            startActivity(Intent(this, ConstraintLayoutTestActivity::class.java))
        }
    }
}