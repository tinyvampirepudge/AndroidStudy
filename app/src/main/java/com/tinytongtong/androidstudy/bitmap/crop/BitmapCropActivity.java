package com.tinytongtong.androidstudy.bitmap.crop;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.Transformation;
import com.bumptech.glide.load.engine.Resource;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.request.Request;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.CustomViewTarget;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.target.SizeReadyCallback;
import com.bumptech.glide.request.target.Target;
import com.bumptech.glide.request.transition.Transition;
import com.tinytongtong.androidstudy.R;
import com.tinytongtong.tinyutils.ScreenUtils;

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

        ivCrop.post(() -> Log.d(TAG, String.format("screenW:%s, screenH:%s", ScreenUtils.INSTANCE.getScreenW(this), ScreenUtils.INSTANCE.getScreenH(this))));
//        RequestOptions options = new RequestOptions();
//        options.centerCrop();
//        Glide.with(ivCrop)
//                .load(R.drawable.icon_long)
////                .apply(options)
////                .transform(new CenterCrop())
//                .transform(new BitmapTransformation() {
//                    @Override
//                    protected Bitmap transform(@NonNull BitmapPool pool, @NonNull Bitmap toTransform, int outWidth, int outHeight) {
//                        Log.d(TAG, String.format("Transformation#transform toTransform.getWidth():%s, toTransform.getHeight():%s, outWidth:%s, outHeight:%s",
//                                toTransform.getWidth(), toTransform.getHeight(), outWidth, outHeight));
//                        return toTransform;
//                    }
//
//                    @Override
//                    public void updateDiskCacheKey(@NonNull MessageDigest messageDigest) {
//
//                    }
//                })
//                .into(ivCrop);

//        Glide.with(this)
//                .asBitmap()
//                .load("https://img-blog.csdnimg.cn/20210319145440751.png")
//                .into(new SimpleTarget<Bitmap>() {
//
//                    @Override
//                    public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
//                        Log.d(TAG, String.format("SimpleTarget<Bitmap>#onResourceReady resource.getWidth():%s, resource.getHeight():%s", resource.getWidth(), resource.getHeight()));
//                        ivCrop.setImageBitmap(resource);
//                    }
//                });

        Glide.with(this)
                .asBitmap()
                .load("https://img-blog.csdnimg.cn/20210319145440751.png")
                .into(new CustomViewTarget<ImageView, Bitmap>(ivCrop) {

                    @Override
                    protected void onResourceCleared(@Nullable Drawable placeholder) {

                    }

                    @Override
                    public void onLoadFailed(@Nullable Drawable errorDrawable) {

                    }

                    @Override
                    public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                        Log.d(TAG, String.format("CustomViewTarget<Bitmap>#onResourceReady resource.getWidth():%s, resource.getHeight():%s", resource.getWidth(), resource.getHeight()));
                        ivCrop.setImageBitmap(resource);
                    }
                });

//        Glide.with(this)
//                .asBitmap()
//                .load("https://img-blog.csdnimg.cn/20210319145440751.png")
//                .into(new Target<Bitmap>() {
//
//                    @Override
//                    public void onStart() {
//
//                    }
//
//                    @Override
//                    public void onStop() {
//
//                    }
//
//                    @Override
//                    public void onDestroy() {
//
//                    }
//
//                    @Override
//                    public void onLoadStarted(@Nullable Drawable placeholder) {
//
//                    }
//
//                    @Override
//                    public void onLoadFailed(@Nullable Drawable errorDrawable) {
//
//                    }
//
//                    @Override
//                    public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
//                        Log.d(TAG, String.format("Target<Bitmap>#onResourceReady resource.getWidth():%s, resource.getHeight():%s", resource.getWidth(), resource.getHeight()));
//                        ivCrop.setImageBitmap(resource);
//                    }
//
//                    @Override
//                    public void onLoadCleared(@Nullable Drawable placeholder) {
//
//                    }
//
//                    @Override
//                    public void getSize(@NonNull SizeReadyCallback cb) {
//
//                    }
//
//                    @Override
//                    public void removeCallback(@NonNull SizeReadyCallback cb) {
//
//                    }
//
//                    @Override
//                    public void setRequest(@Nullable Request request) {
//
//                    }
//
//                    @Nullable
//                    @Override
//                    public Request getRequest() {
//                        return null;
//                    }
//                });
    }
}