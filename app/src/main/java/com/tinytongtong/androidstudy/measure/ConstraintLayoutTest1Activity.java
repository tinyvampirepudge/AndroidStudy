package com.tinytongtong.androidstudy.measure;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.tinytongtong.androidstudy.R;

public class ConstraintLayoutTest1Activity extends AppCompatActivity {
    public static void actionStart(Context context) {
        Intent starter = new Intent(context, ConstraintLayoutTest1Activity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_constraint_layout_test1);
    }
}