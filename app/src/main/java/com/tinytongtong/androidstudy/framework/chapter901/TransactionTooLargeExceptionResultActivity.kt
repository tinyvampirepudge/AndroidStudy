package com.tinytongtong.androidstudy.framework.chapter901

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.tinytongtong.androidstudy.R
import com.tinytongtong.androidstudy.aidl.ILargeBitmapCallback
import kotlinx.android.synthetic.main.activity_transaction_too_large_exception_result.*

/**
 * @Description: TransactionTooLargeException结果
 * 通过Bundle#putBinder传递大图
 *
 * @Author tinytongtong
 * @Date 2020/10/3 8:48 PM
 * @Version
 */
class TransactionTooLargeExceptionResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_transaction_too_large_exception_result)

        val result = intent
        val bundle = result.extras
        val binder = bundle?.getBinder("bitmap-binder")
        binder?.let {
            if (binder is ILargeBitmapCallback) {
                ivLargeBitmap.setImageBitmap(binder.bitmap)
            }
        }
    }
}