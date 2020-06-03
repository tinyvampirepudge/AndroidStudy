package com.tinytongtong.androidstudy.bitmap.large

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.davemorrissey.labs.subscaleview.ImageSource
import com.tinytongtong.androidstudy.R
import kotlinx.android.synthetic.main.activity_subsampling_scale_image_view.*

/**
 * @Description: 大图加载
 * https://github.com/davemorrissey/subsampling-scale-image-view
 * @Author wangjianzhou
 * @Date 2020/6/2 10:59 PM
 * @Version
 */
class SubsamplingScaleImageViewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_subsampling_scale_image_view)

//        imageView.setImage(ImageSource.resource(R.drawable.world_map))
        imageView.setImage(ImageSource.asset("world_map.jpg"))
//        imageView.setImage(ImageSource.uri("/sdcard/DCIM/DSCM00123.JPG"));
    }
}