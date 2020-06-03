package com.tinytongtong.androidstudy.bitmap.options

import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.tinytongtong.androidstudy.R
import com.tinytongtong.tinyutils.ScreenUtils
import kotlinx.android.synthetic.main.activity_bitmap_factory_options.*

/**
 * @Description: Android开发艺术探索 chapter12 关于Bitmap采样率压缩图片
 *
 * @Author wangjianzhou
 * @Date 2020/6/2 11:17 PM
 * @Version
 */
class BitmapFactoryOptionsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bitmap_factory_options)

        tvDisplayMetricInfo.text = ScreenUtils.getDisplayMetricsInfo(this)

        val originBitmap = BitmapFactory.decodeResource(resources, R.drawable.maomi)
        ivOrigin.setImageBitmap(originBitmap)
        tvOriginInfo.text = String.format(
            "byteCount大小：%dKB,allocationByteCount大小：%dKB",
            originBitmap.byteCount / 1024,
            originBitmap.allocationByteCount / 1024
        )

        val dstBitmap = BitmapOptionsUtil.decodeSampledBitmapFromRes(
            resources, R.drawable.maomi,
            ScreenUtils.dip2px(this, 60F), ScreenUtils.dip2px(this, 45F)
        )
        ivDst.setImageBitmap(dstBitmap)
        tvDstInfo.text = String.format(
            "byteCount大小：%dKB,allocationByteCount大小：%dKB",
            dstBitmap.byteCount / 1024,
            dstBitmap.allocationByteCount / 1024
        )
    }
}