package com.tinytongtong.androidstudy.componentcallbacks;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentCallbacks2;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.tinytongtong.androidstudy.MyApplication;
import com.tinytongtong.androidstudy.R;

public class ComponentCallbacksActivity extends AppCompatActivity {
    private static final String TAG = ComponentCallbacksActivity.class.getSimpleName();

    private ComponentCallbacks2 componentCallbacks2 = new ComponentCallbacks2() {
        @Override
        public void onTrimMemory(int level) {
            Log.e(TAG, String.format("onTrimMemory level:%s", level));
        }

        @Override
        public void onConfigurationChanged(@NonNull Configuration newConfig) {
            Log.e(TAG, String.format("onConfigurationChanged newConfig:%s", newConfig));
        }

        @Override
        public void onLowMemory() {
            Log.e(TAG, String.format("onLowMemory"));
        }
    };

    public static void actionStart(Context context) {
        Intent starter = new Intent(context, ComponentCallbacksActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_component_callbacks);
        Log.e(TAG, String.format("onCreate"));

        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ComponentCallbacks2Manager.getInstance().init(MyApplication.Companion.getInstance());

                ComponentCallbacks2Manager.getInstance().register(componentCallbacks2);
            }
        });
    }

    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ComponentCallbacks2Manager.getInstance().unregister(componentCallbacks2);
    }
}