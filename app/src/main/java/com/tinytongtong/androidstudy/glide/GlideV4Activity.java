package com.tinytongtong.androidstudy.glide;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
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

    // 静态图片资源
    private static String URL = "https://i03piccdn.sogoucdn.com/fea552dd430408fa";
    // Gif资源
    private static String GIF_URL = "http://wx4.sinaimg.cn/mw690/006HJgYYgy1fsfx84unifg305k05k7ug.gif";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e(TAG, "onCreate");
        setContentView(R.layout.activity_glide_v4);

        // 111

        // 222

        // 333

        // 我是revert之后的提交

        Button btn = findViewById(R.id.btnLoad);
        imgContainer = findViewById(R.id.rl_img_container);
        iv = findViewById(R.id.iv);

        iv.postDelayed(new Runnable() {
            @Override
            public void run() {
                Log.e(TAG, "我是延时消息");
            }
        }, 10000);

        btn.setOnClickListener(v -> loadImage());
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.e(TAG, "onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.e(TAG, "onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e(TAG, "onDestroy");
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
        RequestOptions options = new RequestOptions()
                .fallback(new ColorDrawable(Color.CYAN))
                .placeholder(new ColorDrawable(Color.GRAY))
                .error(new ColorDrawable(Color.RED))
                .skipMemoryCache(true)
                .diskCacheStrategy(DiskCacheStrategy.NONE);
        Glide.with(getContext())
                .load(URL)
                .apply(options)
                .into(iv);
    }

    private Context getContext() {
        return this;
    }
}