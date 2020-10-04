package com.tinytongtong.androidstudy.framework.chapter901

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.tinytongtong.androidstudy.R
import com.tinytongtong.androidstudy.aidl.ILargeBitmapCallback
import com.tinytongtong.androidstudy.aidl.ILargeBitmapListCallback
import kotlinx.android.synthetic.main.activity_transaction_too_large_exception.*

/**
 * @Description: TransactionTooLargeException校验
 *
 * @Author tinytongtong
 * @Date 2020/10/3 8:47 PM
 * @Version
 */
class TransactionTooLargeExceptionActivity : AppCompatActivity() {
    companion object {
        val TAG = TransactionTooLargeExceptionActivity::class.simpleName
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_transaction_too_large_exception)

        val bitmap = BitmapFactory.decodeResource(resources, R.drawable.large_image)
        ivLargeBitmap.setImageBitmap(bitmap)

        // Caused by: android.os.TransactionTooLargeException: data parcel size 37189932 bytes
        btnLargeBitmapByBundle.setOnClickListener {
            val i = Intent(this, TransactionTooLargeExceptionResultActivity::class.java)
            i.putExtra("bitmap", bitmap)
            Log.e(TAG, "bitmap.allocationByteCount:" + bitmap.allocationByteCount)
            startActivity(i)
        }

        btnLargeBitmapByBinder.setOnClickListener {
            val i = Intent(this, TransactionTooLargeExceptionResultActivity::class.java)
            val bundle = Bundle()
            bundle.putBinder("bitmap-binder", object : ILargeBitmapCallback.Stub() {
                override fun getBitmap(): Bitmap {
                    return bitmap
                }
            })
            Log.e(TAG, "bitmap.allocationByteCount:" + bitmap.allocationByteCount)
            i.putExtras(bundle)
            startActivity(i)
        }

        // 跨进程传递列表的Bitmap，是可以的
        btnLargeListBitmapByBinder.setOnClickListener {
            val bmpList = mutableListOf<Bitmap>()
            for (x in 0 until 2) {
                val bmp = BitmapFactory.decodeResource(resources, R.drawable.large_image)
                bmpList.add(bmp)
            }
            val i = Intent(this, TransactLargeListBitmapActivity::class.java)
            val bundle = Bundle()
            bundle.putBinder("bitmap-list-binder", object : ILargeBitmapListCallback.Stub() {
                override fun getBitmapList(): MutableList<Bitmap> {
                    return bmpList
                }

            })
            i.putExtras(bundle)
            startActivity(i)
        }
    }
}