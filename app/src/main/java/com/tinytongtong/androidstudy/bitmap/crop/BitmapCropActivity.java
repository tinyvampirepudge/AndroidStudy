package com.tinytongtong.androidstudy.bitmap.crop;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.Transformation;
import com.bumptech.glide.load.engine.Resource;
import com.bumptech.glide.request.RequestOptions;
import com.tinytongtong.androidstudy.R;

import java.security.MessageDigest;

import jp.wasabeef.glide.transformations.BlurTransformation;
import jp.wasabeef.glide.transformations.CropCircleWithBorderTransformation;

/**
 * @Description: Glide裁剪图片，从下往上裁剪
 * @Author tinytongtong
 * @Date 3/16/21 11:07 AM
 * @Version
 */
public class BitmapCropActivity extends AppCompatActivity {
    public static final String TAG = BitmapCropActivity.class.getSimpleName();

    public static void actionStart(Context context) {
        Intent starter = new Intent(context, BitmapCropActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bitmap_crop);

        ImageView ivCrop = findViewById(R.id.ivCrop);

        Glide.with(ivCrop)
                .load(R.drawable.icon_long)
                .apply(RequestOptions.bitmapTransform(new CropCircleWithBorderTransformation()))
                .transform(new Transformation<Bitmap>() {
                    @NonNull
                    @Override
                    public Resource<Bitmap> transform(@NonNull Context context, @NonNull Resource<Bitmap> resource, int outWidth, int outHeight) {

                        return resource;
                    }

                    @Override
                    public void updateDiskCacheKey(@NonNull MessageDigest messageDigest) {

                    }
                })
                .into(ivCrop);
    }
}