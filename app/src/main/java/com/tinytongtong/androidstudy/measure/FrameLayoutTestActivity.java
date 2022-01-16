package com.tinytongtong.androidstudy.measure;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.tinytongtong.androidstudy.R;

public class FrameLayoutTestActivity extends AppCompatActivity {
    public static void actionStart(Context context) {
        Intent starter = new Intent(context, FrameLayoutTestActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frame_layout_test);
    }
}