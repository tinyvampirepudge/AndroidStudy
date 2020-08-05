package com.tinytongtong.androidstudy.jsonparse;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.google.gson.Gson;
import com.tinytongtong.androidstudy.R;

/**
 * @Description: json解析
 * @Author wangjianzhou
 * @Date 2020/8/3 1:37 PM
 * @Version TODO
 */
public class JsonParseTestActivity extends AppCompatActivity {
    private static final String TAG = JsonParseTestActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_json_parse_test);
        Gson gson = new Gson();
        String jsonStr = JsonUtil.Companion.getInstance().getJsonFromAssets(this, "json.json");
        JsonBean jsonBean = gson.fromJson(jsonStr, JsonBean.class);

        Log.e(TAG, jsonBean.toString());
        Log.e(TAG, "————————————————————————————————————————————————————————————————————————————————————————");

        // int类型数据，默认支持字符串个数，比如"1"会成功解析
        Gson gson1 = new Gson();
        String jsonStr1 = JsonUtil.Companion.getInstance().getJsonFromAssets(this, "json1.json");
        JsonBean jsonBean1 = gson1.fromJson(jsonStr1, JsonBean.class);
        Log.e(TAG, jsonBean1.toString());
        Log.e(TAG, "————————————————————————————————————————————————————————————————————————————————————————");
    }
}