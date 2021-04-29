package com.tinytongtong.androidstudy.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.tinytongtong.androidstudy.R;

/**
 * @Description: 如果  acttivityA.startActivityForResult() 开启了activityB。
 * 如果activityB在销毁的时候，不调用 setResult 方法。那 activityA 的 onActivityResult 方法还会执行吗？
 * @Author tinytongtong
 * @Date 4/29/21 3:30 PM
 * @Version
 */
public class BActivity extends AppCompatActivity {
    public static void actionStart(Context context) {
        Intent starter = new Intent(context, BActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_b);

        findViewById(R.id.btn1).setOnClickListener(v -> {
            setResult(RESULT_OK);
            finish();
        });

        findViewById(R.id.btn2).setOnClickListener(v -> {
            finish();
        });
    }
}