package com.tinytongtong.androidstudy.toast;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.tinytongtong.androidstudy.R;

/**
 * @Description: Toast取消测试
 * @Author tinytongtong
 * @Date 2021/1/4 9:55 AM
 * @Version
 */
public class ToastTestActivity extends AppCompatActivity {

    private int index;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toast_test);
        findViewById(R.id.btn_show_toast).setOnClickListener(v -> showToast());
        findViewById(R.id.btn_cancel_toast).setOnClickListener(v -> ToastHelper.cancelAllToast());
    }

    private void showToast() {
        ToastHelper.showToast(this, String.format("展示Toast:%d", index++));
    }
}