package com.tinytongtong.androidstudy.measure;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.tinytongtong.androidstudy.R;

public class InvalidateTestActivity extends AppCompatActivity {
    public static void actionStart(Context context) {
        Intent starter = new Intent(context, InvalidateTestActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invalidate_test);

        View view1 = findViewById(R.id.view1);
        TextView view2 = findViewById(R.id.view2);
        Button view3 = findViewById(R.id.view3);

        view1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                view1.invalidate();
            }
        });

        view2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                view2.invalidate();
            }
        });

        view3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                view3.invalidate();
            }
        });
    }
}