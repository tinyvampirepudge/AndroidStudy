package com.tinytongtong.androidstudy.bitmap.options

import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import com.tinytongtong.tinyutils.LogUtils

/**
 * @Description:
 *
 * @Author wangjianzhou@qding.me
 * @Date 2020/6/3 12:15 PM
 * @Version
 */
object BitmapOptionsUtil {
    /**
     * 通过采样率可以有效的加载图片。获取采样率的流程如下：
     * 1、将BitmapFactory.Options的inJustDecodeBounds参数设为true并加载图片
     * 2、从BitmapFactory.Options中取出图片的原始宽高信息，它们对应于outWidth和outHeight参数。
     * 3、根据采样管了的规则并结合目标View的大小计算出采样率inSampleSize。
     * 4、将BitmapFactory.Options的inJustDecodeBoounds参数设为false，然后重新加载图片。
     */
    fun decodeSampledBitmapFromRes(
        res: Resources,
        resId: Int,
        reqWidth: Int,
        reqHeight: Int
    ): Bitmap {
        // 获取图片宽高
        val options = BitmapFactory.Options()
        options.inJustDecodeBounds = true
        BitmapFactory.decodeResource(res, resId, options);
        // 计算采样率
        options.inSampleSize = calcInSampleSize(options, reqWidth, reqHeight)
        options.inJustDecodeBounds = false
        return BitmapFactory.decodeResource(res, resId, options)
    }

    /**
     * 计算合适的采样率，采样率必须是2的倍数
     */
    fun calcInSampleSize(options: BitmapFactory.Options, reqWidth: Int, reqHeight: Int): Int {
        // 获取image的宽高
        val height = options.outHeight
        val width = options.outWidth

        // 计算合适的采样率
        var inSampleSize = 1
        LogUtils.e("BitmapOptionsUtil", "default inSampleSize:$inSampleSize")
        if (height > reqHeight || width > reqWidth) {
            val halfHeight = height / 2
            val halfWidth = width / 2
            while (halfHeight / inSampleSize >= reqHeight && halfWidth / inSampleSize >= reqWidth) {
                LogUtils.e("BitmapOptionsUtil", "changing inSampleSize:$inSampleSize")
                inSampleSize *= 2;
            }
        }
        LogUtils.e("BitmapOptionsUtil", "result inSampleSize:$inSampleSize")
        return inSampleSize
    }
}