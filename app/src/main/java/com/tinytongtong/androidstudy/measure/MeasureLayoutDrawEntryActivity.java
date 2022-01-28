package com.tinytongtong.androidstudy.measure;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.tinytongtong.androidstudy.R;

/**
 * @Description: 绘制流程相关
 * @Author wangjianzhou
 * @Date 1/16/22 10:11 AM
 * @Version
 */
public class MeasureLayoutDrawEntryActivity extends AppCompatActivity {
    public static void actionStart(Context context) {
        Intent starter = new Intent(context, MeasureLayoutDrawEntryActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_measure_layout_draw_entry);

        findViewById(R.id.btn_linear_layout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LinearLayoutTestActivity.actionStart(MeasureLayoutDrawEntryActivity.this);
            }
        });

        findViewById(R.id.btn_relative_layout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RelativeLayoutTestActivity.actionStart(MeasureLayoutDrawEntryActivity.this);
            }
        });

        findViewById(R.id.btn_frame_layout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FrameLayoutTestActivity.actionStart(MeasureLayoutDrawEntryActivity.this);
            }
        });

        findViewById(R.id.btn_constraint_layout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ConstraintLayoutTest1Activity.actionStart(MeasureLayoutDrawEntryActivity.this);
            }
        });

        findViewById(R.id.btn_dispatch_attach_info).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DispatchAttachInfoTestActivity.actionStart(MeasureLayoutDrawEntryActivity.this);
            }
        });

        findViewById(R.id.btn_request_layout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RequestLayoutTestActivity.actionStart(MeasureLayoutDrawEntryActivity.this);
            }
        });

        findViewById(R.id.btn_invalidate).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InvalidateTestActivity.actionStart(MeasureLayoutDrawEntryActivity.this);
            }
        });
    }
}