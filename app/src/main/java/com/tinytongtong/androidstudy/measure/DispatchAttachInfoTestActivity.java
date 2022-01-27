package com.tinytongtong.androidstudy.measure;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.tinytongtong.androidstudy.R;

public class DispatchAttachInfoTestActivity extends AppCompatActivity {

    public static void actionStart(Context context) {
        Intent starter = new Intent(context, DispatchAttachInfoTestActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dispatch_attach_info_test);


        LinearLayout root = findViewById(R.id.root);
        Button btn = findViewById(R.id.btn_add);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View view = new View(DispatchAttachInfoTestActivity.this);
                view.setBackgroundColor(getResources().getColor(R.color.background_info));
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(300, 300);
                root.addView(view, lp);
            }
        });
    }
}