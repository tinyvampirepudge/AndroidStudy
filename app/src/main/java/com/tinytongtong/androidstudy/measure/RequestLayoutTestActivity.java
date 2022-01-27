package com.tinytongtong.androidstudy.measure;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.tinytongtong.androidstudy.R;

import java.util.Random;

public class RequestLayoutTestActivity extends AppCompatActivity {
    public static void actionStart(Context context) {
        Intent starter = new Intent(context, RequestLayoutTestActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_layout_test);

        View view1 = findViewById(R.id.view1);
        TextView view2 = findViewById(R.id.view2);
        Button view3 = findViewById(R.id.view3);

        view1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                view1.requestLayout();
            }
        });

        view2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                view2.setText("猫了个咪" + new Random().nextInt(100));
                view2.requestLayout();
            }
        });

        view3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                ViewGroup.MarginLayoutParams mlp = (ViewGroup.MarginLayoutParams) view1.getLayoutParams();
//                mlp.leftMargin += new Random().nextInt(100);
//                view1.setLayoutParams(mlp);
                view3.requestLayout();
            }
        });
    }
}