package com.tinytongtong.androidstudy.classloader;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.tinytongtong.androidstudy.R;

/**
 * @Description: ClassLoader学习
 * @Author wangjianzhou
 * @Date 2022/3/21 2:43 PM
 */
public class ClassLoaderTestActivity extends AppCompatActivity {
    private static final String TAG = ClassLoaderTestActivity.class.getSimpleName();

    public static void actionStart(Context context) {
        Intent starter = new Intent(context, ClassLoaderTestActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class_loadet_test);
        // dalvik.system.PathClassLoader
        ClassLoader cl = ClassLoaderTestActivity.class.getClassLoader();
        Log.e(TAG, "cl: " + cl.toString());
        /**
         * 模拟器: x86
         * E/ClassLoaderTestActivity: cl:
         * dalvik.system.PathClassLoader[DexPathList[
         [zip file "/data/app/~~HI9c5wkyi0mdmoLKxQAR_g==/com.tinytongtong.androidstudy--5Ng5zb6Fs4CJnPk61kNIw==/base.apk"],
         * nativeLibraryDirectories=[/data/app/~~HI9c5wkyi0mdmoLKxQAR_g==/com.tinytongtong.androidstudy--5Ng5zb6Fs4CJnPk61kNIw==/lib/x86, /data/app/~~HI9c5wkyi0mdmoLKxQAR_g==/com.tinytongtong.androidstudy--5Ng5zb6Fs4CJnPk61kNIw==/base.apk!/lib/x86, /system/lib, /system_ext/lib]]]
         */

        /**
         * vivo V2001A: arm64-v8a
         * E/ClassLoaderTestActivity: cl:
         * dalvik.system.PathClassLoader[DexPathList[[zip file "/data/app/com.tinytongtong.androidstudy-FFKXUM_gePHqr-gnuQJ5WQ==/base.apk"],
         * nativeLibraryDirectories=[/data/app/com.tinytongtong.androidstudy-FFKXUM_gePHqr-gnuQJ5WQ==/lib/arm64, /data/app/com.tinytongtong.androidstudy-FFKXUM_gePHqr-gnuQJ5WQ==/base.apk!/lib/arm64-v8a, /system/lib64, /system/product/lib64]]]
         */
    }
}