package com.tinytongtong.androidstudy.bitmap.compress

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Matrix
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.tinytongtong.androidstudy.R
import com.tinytongtong.androidstudy.bitmap.options.BitmapOptionsUtil
import com.tinytongtong.tinyutils.ScreenUtils
import kotlinx.android.synthetic.main.activity_bitmap_compress.*
import java.io.ByteArrayOutputStream

/**
 * @Description: bitmap压缩
 * https://blog.csdn.net/HarryWeasley/article/details/51955467
 * https://www.jianshu.com/p/eef3daeeecbc
 * https://juejin.im/post/5ec7302c518825434062f497#heading-7
 * @Author wangjianzhou
 * @Date 2020/6/5 10:06 AM
 * @Version
 */
class BitmapCompressActivity : AppCompatActivity() {

    companion object {
        val kb_size = 1024
        val mb_size = 1024 * 1024
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bitmap_compress)

        // 展示原图
        val jpgIs = assets.open("maomi.jpg")
        val bmpOriginJpg = BitmapFactory.decodeStream(jpgIs)
        val descOriginJpg =
            "height:${bmpOriginJpg.height},\nwidth:${bmpOriginJpg.width},\nallocationByteCount:${bmpOriginJpg.allocationByteCount}byte,\n" +
                    "byteCount:${bmpOriginJpg.byteCount}byte,\nrowBytes:${bmpOriginJpg.rowBytes}byte,\ndensity:${bmpOriginJpg.density}"
        tvOriginJpgInfo.text = "${tvOriginJpgInfo.text}$descOriginJpg"
        ivOriginJpg.setImageBitmap(bmpOriginJpg)

        var pngIs = assets.open("PersistentCookieStore1.png")
        val bmpOriginPng = BitmapFactory.decodeStream(pngIs)
        val descOriginPng =
            "height:${bmpOriginPng.height},\nwidth:${bmpOriginPng.width},\nallocationByteCount:${bmpOriginPng.allocationByteCount}byte,\n" +
                    "byteCount:${bmpOriginPng.byteCount}byte,\nrowBytes:${bmpOriginPng.rowBytes}byte,\ndensity:${bmpOriginPng.density}"
        tvOriginPngInfo.text = "${tvOriginPngInfo.text}$descOriginPng"
        ivOriginPng.setImageBitmap(bmpOriginPng)

        /**
         * jpg
         */
        // 1、通过quality压缩，质量压缩。
        // 质量压缩不会减少图片的像素，它是在保持像素的前提下改变图片的位深及透明度等，来达到压缩图片的目的。图片的长，宽，像素都不变，那么bitmap所占内存大小是不会变的。
        // 这里改变的是bitmap对应的字节数组的大小，适合去传递二进制的图片数据，比如微信分享。
        // https://blog.csdn.net/HarryWeasley/article/details/51955467

        btnQualityJpg.setOnClickListener {
            val bytearray = getBytesFromCompressBitmap(bmpOriginJpg, 32 * 1024)
            val bmpQualityJpg = BitmapFactory.decodeByteArray(bytearray, 0, bytearray.size)
            val descQualityJpg =
                "height:${bmpQualityJpg.height},\nwidth:${bmpQualityJpg.width},\nallocationByteCount:${bmpQualityJpg.allocationByteCount}byte,\n" +
                        "byteCount:${bmpQualityJpg.byteCount}byte,\nrowBytes:${bmpQualityJpg.rowBytes}byte,\ndensity:${bmpQualityJpg.density},\n" +
                        "bytearray:${bytearray.size}"
            tvQualityJpgInfo.text = "Jpg进行Quality压缩后的信息：$descQualityJpg"
            ivQualityJpg.setImageBitmap(bmpQualityJpg)
        }

        // 2、更改Bitmap.Config格式
        btnBmpConfigJpg.setOnClickListener {
            val option = BitmapFactory.Options()
            option.inPreferredConfig = Bitmap.Config.ARGB_4444
//            option.inPreferredConfig = Bitmap.Config.RGB_565 // 对透明度没要求的话可以试一下rgb_565
            val bmpBmpConfigJpg = BitmapFactory.decodeStream(assets.open("maomi.jpg"), null, option)
            val descBmpConfigJpg =
                "height:${bmpBmpConfigJpg?.height},\nwidth:${bmpBmpConfigJpg?.width},\nallocationByteCount:${bmpBmpConfigJpg?.allocationByteCount}byte,\n" +
                        "byteCount:${bmpBmpConfigJpg?.byteCount}byte,\nrowBytes:${bmpBmpConfigJpg?.rowBytes}byte,\ndensity:${bmpBmpConfigJpg?.density}"
            tvBmpConfigJpgInfo.text = "Jpg通过Bitmap.Config压缩后的信息：$descBmpConfigJpg"
            ivBmpConfigJpg.setImageBitmap(bmpBmpConfigJpg)
        }

        // 3、采样率压缩，改变Bitmap大小。已知目标ImageView大小
        btnInSampleSizeJpg.setOnClickListener {
            val assetFileDescriptor = assets.openFd("maomi.jpg")
            val fileInputStream = assetFileDescriptor.createInputStream()
            val bmpInSampleSizeJpg = BitmapOptionsUtil.decodeSampledBitmapFromFileStream(
                fileInputStream,
                ScreenUtils.dip2px(this, 60f),
                ScreenUtils.dip2px(this, 45f)
            )
            val descBmpConfigJpg =
                "height:${bmpInSampleSizeJpg?.height},\nwidth:${bmpInSampleSizeJpg?.width},\nallocationByteCount:${bmpInSampleSizeJpg?.allocationByteCount}byte,\n" +
                        "byteCount:${bmpInSampleSizeJpg?.byteCount}byte,\nrowBytes:${bmpInSampleSizeJpg?.rowBytes}byte,\ndensity:${bmpInSampleSizeJpg?.density}"
            tvInSampleSizeJpgInfo.text = "Jpg通过inSampleSize压缩后的信息：$descBmpConfigJpg"
            ivInSampleSizeJpg.setImageBitmap(bmpInSampleSizeJpg)
        }

        // 4、通过martix进行压缩。Bitmap.createBitmap或者Bitmap.createScaledBitmap方法
        btnMatrixJpg.setOnClickListener {
            val matrix = Matrix()
            matrix.setScale(0.1f, 0.1f)
            val bmpMatrixJpg = Bitmap.createBitmap(
                bmpOriginJpg, 0, 0, bmpOriginJpg.getWidth(),
                bmpOriginJpg.getHeight(), matrix, true
            )
            // Bitmap.createScaledBitmap内部也会使用Matrix进行缩放
//            val bmpMatrixJpg = Bitmap.createScaledBitmap(
//                bmpOriginJpg,
//                ScreenUtils.dip2px(this, 60f),
//                ScreenUtils.dip2px(this, 45f),
//                true
//            )
            val descBmpConfigJpg =
                "height:${bmpMatrixJpg?.height},\nwidth:${bmpMatrixJpg?.width},\nallocationByteCount:${bmpMatrixJpg?.allocationByteCount}byte,\n" +
                        "byteCount:${bmpMatrixJpg?.byteCount}byte,\nrowBytes:${bmpMatrixJpg?.rowBytes}byte,\ndensity:${bmpMatrixJpg?.density}"
            tvMatrixJpgInfo.text = "Jpg通过Matrix压缩后的信息：$descBmpConfigJpg"
            ivMatrixJpg.setImageBitmap(bmpMatrixJpg)
        }

        /**
         * png
         */
        // 1、通过quality压缩，质量压缩。
        // 质量压缩不会减少图片的像素，它是在保持像素的前提下改变图片的位深及透明度等，来达到压缩图片的目的。图片的长，宽，像素都不变，那么bitmap所占内存大小是不会变的。
        // 这里改变的是bitmap对应的字节数组的大小，适合去传递二进制的图片数据，比如微信分享。
        // https://blog.csdn.net/HarryWeasley/article/details/51955467

        btnQualityPng.setOnClickListener {
            val bytearray = getBytesFromCompressBitmap(bmpOriginPng, 32 * 1024)
            val bmpQualityPng = BitmapFactory.decodeByteArray(bytearray, 0, bytearray.size)
            val descQualityPng =
                "height:${bmpQualityPng.height},\nwidth:${bmpQualityPng.width},\nallocationByteCount:${bmpQualityPng.allocationByteCount}byte,\n" +
                        "byteCount:${bmpQualityPng.byteCount}byte,\nrowBytes:${bmpQualityPng.rowBytes}byte,\ndensity:${bmpQualityPng.density},\n" +
                        "bytearray:${bytearray.size}"
            tvQualityPngInfo.text = "Png进行Quality压缩后的信息：$descQualityPng"
            ivQualityPng.setImageBitmap(bmpQualityPng)
        }

        // 2、更改Bitmap.Config格式
        btnBmpConfigPng.setOnClickListener {
            val option = BitmapFactory.Options()
            option.inPreferredConfig = Bitmap.Config.ARGB_4444
//            option.inPreferredConfig = Bitmap.Config.RGB_565 // 对透明度没要求的话可以试一下rgb_565
            val bmpBmpConfigPng =
                BitmapFactory.decodeStream(assets.open("PersistentCookieStore1.png"), null, option)
            val descBmpConfigPng =
                "height:${bmpBmpConfigPng?.height},\nwidth:${bmpBmpConfigPng?.width},\nallocationByteCount:${bmpBmpConfigPng?.allocationByteCount}byte,\n" +
                        "byteCount:${bmpBmpConfigPng?.byteCount}byte,\nrowBytes:${bmpBmpConfigPng?.rowBytes}byte,\ndensity:${bmpBmpConfigPng?.density}"
            tvBmpConfigPngInfo.text = "Png通过Bitmap.Config压缩后的信息：$descBmpConfigPng"
            ivBmpConfigPng.setImageBitmap(bmpBmpConfigPng)
        }

        // 3、采样率压缩，改变Bitmap大小。已知目标ImageView大小
        btnInSampleSizePng.setOnClickListener {
            val assetFileDescriptor = assets.openFd("PersistentCookieStore1.png")
            val fileInputStream = assetFileDescriptor.createInputStream()
            val bmpInSampleSizePng = BitmapOptionsUtil.decodeSampledBitmapFromFileStream(
                fileInputStream,
                87,
                75
            )
            val descBmpConfigPng =
                "height:${bmpInSampleSizePng?.height},\nwidth:${bmpInSampleSizePng?.width},\nallocationByteCount:${bmpInSampleSizePng?.allocationByteCount}byte,\n" +
                        "byteCount:${bmpInSampleSizePng?.byteCount}byte,\nrowBytes:${bmpInSampleSizePng?.rowBytes}byte,\ndensity:${bmpInSampleSizePng?.density}"
            tvInSampleSizePngInfo.text = "Png通过inSampleSize压缩后的信息：$descBmpConfigPng"
            ivInSampleSizePng.setImageBitmap(bmpInSampleSizePng)
        }

        // 4、通过martix进行压缩。Bitmap.createBitmap或者Bitmap.createScaledBitmap方法
        btnMatrixPng.setOnClickListener {
            val matrix = Matrix()
            matrix.setScale(0.2f, 0.2f)
            val bmpMatrixPng = Bitmap.createBitmap(
                bmpOriginPng, 0, 0, bmpOriginPng.getWidth(),
                bmpOriginPng.getHeight(), matrix, true
            )
            // Bitmap.createScaledBitmap内部也会使用Matrix进行缩放
//            val bmpMatrixPng = Bitmap.createScaledBitmap(
//                bmpOriginPng,
//                ScreenUtils.dip2px(this, 60f),
//                ScreenUtils.dip2px(this, 45f),
//                true
//            )
            val descBmpConfigPng =
                "height:${bmpMatrixPng?.height},\nwidth:${bmpMatrixPng?.width},\nallocationByteCount:${bmpMatrixPng?.allocationByteCount}byte,\n" +
                        "byteCount:${bmpMatrixPng?.byteCount}byte,\nrowBytes:${bmpMatrixPng?.rowBytes}byte,\ndensity:${bmpMatrixPng?.density}"
            tvMatrixPngInfo.text = "Png通过Matrix压缩后的信息：$descBmpConfigPng"
            ivMatrixPng.setImageBitmap(bmpMatrixPng)
        }

    }

    /**
     * 将Bitmap的字节流压缩为目标大小
     * @targetSize  单位为Byte
     */
    private fun getBytesFromCompressBitmap(
        bitmap: Bitmap,
        targetSize: Int
    ): ByteArray {
        val baos = ByteArrayOutputStream()
        var quality = 100
        bitmap.compress(Bitmap.CompressFormat.PNG, quality, baos)
        var bytes = baos.toByteArray()
        while (bytes.size > targetSize && quality >= 5) {
            quality -= 5
            if (quality < 0) {
                quality = 0
            }
            // 重置，不然会累加
            baos.reset()
            bitmap.compress(Bitmap.CompressFormat.JPEG, quality, baos)
            bytes = baos.toByteArray()
        }
        try {
            baos.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return bytes
    }
}