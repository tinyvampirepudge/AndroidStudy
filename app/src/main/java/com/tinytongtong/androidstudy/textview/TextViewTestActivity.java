package com.tinytongtong.androidstudy.textview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;

import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.util.TypedValue;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tinytongtong.androidstudy.R;
import com.tinytongtong.tinyutils.ScreenUtils;

/**
 * @Description:
 * @Author tinytongtong
 * @Date 2020/10/29 1:58 PM
 * @Version
 */
public class TextViewTestActivity extends AppCompatActivity {

    private ColorStateList mLabelColor;
    private int mLabelTextSize;
    private LinearLayout mLL;

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
        txtAppendSsb(tv, str1, R.color.font_assist_1);
        txtAppendSsb(tv, str2, R.color.font_assist_1);
        txtAppendSsb(tv, str3, R.color.txt_red_f2233b);
        txtAppendSsb(tv, str4, R.color.font_assist_1);
        txtAppendSsb(tv, str5, R.color.txt_red_f2233b);
        txtAppendSsb(tv, str6, R.color.font_assist_1);

        mLL = findViewById(R.id.ll);

        Resources resources = getResources();
        mLabelColor = ResourcesCompat
                .getColorStateList(resources, R.color.item_label_color, null);
        mLabelTextSize = resources.getDimensionPixelSize(R.dimen.sp_12);

        findViewById(R.id.tv_xml).setSelected(false);
        findViewById(R.id.tv_xml1).setSelected(true);

        addText("猫了个咪-selected-false", false);
        addText("猫了个咪-selected-true", true);
    }

    private void addText(String content, boolean selected) {
        TextView tv = new TextView(TextViewTestActivity.this);
        int padding = ScreenUtils.dip2px(this, 10);
        tv.setPadding(padding, padding, padding, padding);
        tv.setTextColor(mLabelColor);
//        tv.setBackground(mBgStateListDrawable);
//        tv.setBackground(mBgDrawable);
        tv.setBackgroundResource(R.drawable.item_selector);
        tv.setTextSize(TypedValue.COMPLEX_UNIT_PX, mLabelTextSize);
        tv.setText(content);
        tv.setSelected(selected);
        ViewGroup.MarginLayoutParams mlp = new ViewGroup.MarginLayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        mlp.topMargin = ScreenUtils.dip2px(this, 10);
        mLL.addView(tv, mlp);
    }

    /**
     * 动态生成背景Drawable
     *
     * @param bgColor
     * @return
     */
    private GradientDrawable genDrawable(float radius, int bgColor) {
        GradientDrawable drawable = new GradientDrawable();
        drawable.setShape(GradientDrawable.RECTANGLE);
        drawable.setColor(bgColor);
        drawable.setCornerRadius(radius);
        return drawable;
    }

    private void txtAppendSsb(TextView txt, String str, int color) {
        SpannableString ssb = new SpannableString(str);
        ssb.setSpan(new ForegroundColorSpan(ContextCompat.getColor(this, color)), 0, str.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        txt.append(ssb);
    }
}