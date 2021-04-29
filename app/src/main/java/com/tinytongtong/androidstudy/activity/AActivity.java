package com.tinytongtong.androidstudy.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.tinytongtong.androidstudy.R;

/**
 * @Description: 如果  acttivityA.startActivityForResult() 开启了activityB。
 * 如果activityB在销毁的时候，不调用 setResult 方法。那 activityA 的 onActivityResult 方法还会执行吗？
 * @Author tinytongtong
 * @Date 4/29/21 3:29 PM
 * @Version
 */
public class AActivity extends AppCompatActivity {
    private static final String TAG = AActivity.class.getSimpleName();
    public static final int REQUEST_CODE = 10086;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a);
        findViewById(R.id.btn).setOnClickListener(v -> {
            Intent starter = new Intent(this, BActivity.class);
            startActivityForResult(starter, REQUEST_CODE);
        });
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.e(TAG, "onRestart");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.e(TAG, "onActivityResult");
        if (REQUEST_CODE == requestCode && resultCode == RESULT_OK) {
            Log.e(TAG, "onActivityResult RESULT_OK");
        }
    }
}