package com.tinytongtong.androidstudy.textview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.widget.TextView;

import com.tinytongtong.androidstudy.R;

/**
 * @Description:
 * @Author tinytongtong
 * @Date 2020/10/29 1:58 PM
 * @Version
 */
public class TextViewTestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_view_test);

        TextView tv = (TextView) findViewById(R.id.tv);
        String str1 = "感谢您是选择吉利汽车App\n\n";
        String str2 = "我们非常重视您的个人信息和隐私保护。为了更好的保证您的个人权益，在您使用我们的产品钱，请务必审慎阅读";
        String str3 = "《用户协议》";
        String str4 = "与";
        String str5 = "《隐私政策》";
        String str6 = "内的是所有条款。";
        txtAppendSsb(tv,str1,R.color.font_assist_1);
        txtAppendSsb(tv,str2,R.color.font_assist_1);
        txtAppendSsb(tv,str3,R.color.txt_red_f2233b);
        txtAppendSsb(tv,str4,R.color.font_assist_1);
        txtAppendSsb(tv,str5,R.color.txt_red_f2233b);
        txtAppendSsb(tv,str6,R.color.font_assist_1);

    }

    private void txtAppendSsb(TextView txt, String str, int color) {
        SpannableString ssb = new SpannableString(str);
        ssb.setSpan(new ForegroundColorSpan(ContextCompat.getColor(this, color)), 0, str.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        txt.append(ssb);
    }
}