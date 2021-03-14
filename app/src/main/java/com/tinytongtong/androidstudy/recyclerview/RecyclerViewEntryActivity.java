package com.tinytongtong.androidstudy.recyclerview;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.tinytongtong.androidstudy.R;

/**
 * @Description: RecyclerView
 * @Author tinytongtong
 * @Date 2021/2/28 6:09 PM
 * @Version
 */
public class RecyclerViewEntryActivity extends AppCompatActivity {

    public static void actionStart(Context context) {
        Intent starter = new Intent(context, RecyclerViewEntryActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view_entry);

        findViewById(R.id.btn_adapter_onCreateViewHolder).setOnClickListener(v -> AdapterOnCreateViewHolderTestActivity.actionStart(RecyclerViewEntryActivity.this));
    }
}