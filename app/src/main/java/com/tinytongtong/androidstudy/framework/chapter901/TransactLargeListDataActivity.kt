package com.tinytongtong.androidstudy.framework.chapter901

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.tinytongtong.androidstudy.R
import com.tinytongtong.androidstudy.aidl.ILargeDataListCallback

/**
 * @Description: 通过Binder传递大量列表数据
 *
 * @Author tinytongtong
 * @Date 2020/10/4 7:26 PM
 * @Version
 */
class TransactLargeListDataActivity : AppCompatActivity() {
    companion object {
        val TAG = TransactLargeListDataActivity::class.simpleName
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_transact_large_list_data)

        val result = intent
        val bundle = result.extras
        val binder = bundle?.getBinder("data-list-binder")
        binder?.let {
            if (binder is ILargeDataListCallback) {
                var dataList = binder.dataList
                for (x in 0 until dataList.size) {
                    Log.e(
                        TAG,
                        "index:${x}, dataList:${dataList.get(x)}"
                    )
                }
            }
        }
    }
}