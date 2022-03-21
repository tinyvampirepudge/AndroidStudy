package com.tinytongtong.androidstudy.classloader;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.tinytongtong.androidstudy.MainActivity;
import com.tinytongtong.androidstudy.R;

import java.io.File;

import dalvik.system.DexClassLoader;

/**
 * @Description: ClassLoader学习
 * @Author wangjianzhou
 * @Date 2022/3/21 2:43 PM
 */
public class ClassLoaderTestActivity extends AppCompatActivity {
    private static final String TAG = ClassLoaderTestActivity.class.getSimpleName();
    private ISay iSay;
    private Context mContext;
    private static final int REQUEST_CODE = 100;

    public static void actionStart(Context context) {
        Intent starter = new Intent(context, ClassLoaderTestActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        setContentView(R.layout.activity_class_loadet_test);
        // dalvik.system.PathClassLoader
        ClassLoader cl = ClassLoaderTestActivity.class.getClassLoader();
        Log.e(TAG, "cl: " + cl.toString());
        // output:
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

        Button btn = findViewById(R.id.btn_say);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ContextCompat.checkSelfPermission(mContext, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                    process();
                } else {
                    ActivityCompat.requestPermissions((Activity) mContext,
                            new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                            REQUEST_CODE);
                }
            }
        });
    }

    private void process() {
        // 获取hotfix的jar包文件
        final File jarFile = new File(Environment.getExternalStorageDirectory().getPath()
                + File.separator + "say_something_hotfix.jar");
        if (!jarFile.exists()) {
            iSay = new SayException();
            Toast.makeText(ClassLoaderTestActivity.this, iSay.saySth(), Toast.LENGTH_SHORT).show();
        } else {
            // 只要有读写权限的路径均可
            DexClassLoader dexClassLoader = new DexClassLoader(jarFile.getAbsolutePath(),
                    getExternalCacheDir().getAbsolutePath(), null, getClassLoader());
            try {
                // 加载 SayHotFix 类
                Class clazz = dexClassLoader.loadClass("com.tinytongtong.androidstudy.classloader.SayHotFix");
                // 强转成 ISay，注意 ISay 的包名要和hotfix jar包中的一致
                ISay say = (ISay) clazz.newInstance();
                Toast.makeText(ClassLoaderTestActivity.this, say.saySth(), Toast.LENGTH_SHORT).show();
            } catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CODE) {
            if (grantResults.length > 0
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                process();
            } else {
                Toast.makeText(mContext, "Storage Permission Denied", Toast.LENGTH_SHORT).show();
            }
        }
    }
}