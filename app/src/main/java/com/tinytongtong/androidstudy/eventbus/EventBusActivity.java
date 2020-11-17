package com.tinytongtong.androidstudy.eventbus;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.tencent.mmkv.MMKV;
import com.tinytongtong.androidstudy.R;

/**
 * @Description: EventBus源码阅读
 * @Author tinytongtong
 * @Date 2020/11/16 3:10 PM
 * @Version
 */
public class EventBusActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_bus);

        MMKV mv = MMKV.defaultMMKV();
        mv.encode("abc", "def");
        System.out.println(mv.decodeString("abc"));
    }

}