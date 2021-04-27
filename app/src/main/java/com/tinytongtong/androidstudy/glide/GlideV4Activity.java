package com.tinytongtong.androidstudy.glide;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.tinytongtong.androidstudy.R;


/**
 * @Description: glide v4 测试
 * @Author tinytongtong
 * @Date 2021/1/20 10:04 AM
 * @Version
 */
public class GlideV4Activity extends AppCompatActivity {
    public static final String TAG = GlideV4Activity.class.getSimpleName();

    private RelativeLayout imgContainer;
    private ImageView iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_glide_v4);

        Button btn = findViewById(R.id.btnLoad);
        imgContainer = findViewById(R.id.rl_img_container);
        iv = findViewById(R.id.iv);

        btn.setOnClickListener(v -> loadImage());
    }

    private void loadImage() {
        //4.9.0：使用into(target)时GONE不会走回调，INVISIBLE会走
        // 使用submit时会走回调。
//        iv.setVisibility(View.GONE);
////        iv.setVisibility(View.INVISIBLE);
//        Glide.with(getContext())
//                .load(R.drawable.wallpure)
//                .error(R.drawable.maomi)
//                .listener(new RequestListener<Drawable>() {
//                    @Override
//                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
//                        Log.e(TAG, "RequestListener#onLoadFailed");
//                        return false;
//                    }
//
//                    @Override
//                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
//                        Log.e(TAG, "RequestListener#onResourceReady");
//                        runOnUiThread(() -> {
//                            iv.setVisibility(View.VISIBLE);
//                            iv.setImageDrawable(resource);
//                        });
//                        return false;
//                    }
//
//                })
////                .into(iv);
//                .submit();

        //3.7.0：GONE不会走回调，INVISIBLE会走
//        iv.setVisibility(View.GONE);
////        iv.setVisibility(View.INVISIBLE);
//        Glide.with(getContext())
//                .load(R.drawable.wallpure)
//                .error(R.drawable.maomi)
//                .listener(new RequestListener<Integer, GlideDrawable>() {
//                    @Override
//                    public boolean onException(Exception e, Integer model, Target<GlideDrawable> target, boolean isFirstResource) {
//                        Log.e(TAG, "RequestListener#onLoadFailed");
//                        return false;
//                    }
//
//                    @Override
//                    public boolean onResourceReady(GlideDrawable resource, Integer model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
//                        Log.e(TAG, "RequestListener#onResourceReady");
//                        iv.setVisibility(View.VISIBLE);
//                        return false;
//                    }
//                })
//                .into(iv);


        /**
         * 查看源码
         */
        RequestOptions options = new RequestOptions();
        options.fitCenter();
        Glide.with(getContext())
                .load(R.drawable.wallpure)
                .into(iv);
    }

    private Context getContext() {
        return this;
    }
}