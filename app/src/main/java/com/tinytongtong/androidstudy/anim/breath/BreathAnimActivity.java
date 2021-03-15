package com.tinytongtong.androidstudy.anim.breath;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

import com.tinytongtong.androidstudy.R;

/**
 * @Description: 呼吸动画
 * @Author tinytongtong
 * @Date 3/14/21 9:33 PM
 * @Version
 */
public class BreathAnimActivity extends AppCompatActivity {

    private View mFavoriteBtn;
    private BreathAnimHelper mFavoriteBtnAnimHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_breath_anim);

        Button btnStart = findViewById(R.id.btn_start_breath_anim);
        Button btnStop = findViewById(R.id.btn_stop_breath_anim);
        mFavoriteBtn = findViewById(R.id.btn_favorite);

        Animation hyperspaceJumpAnimation = AnimationUtils.loadAnimation(this, R.anim.hyperspace_jump);


        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mFavoriteBtnAnimHelper == null) {
                    mFavoriteBtnAnimHelper = new BreathAnimHelper(BreathAnimActivity.this, mFavoriteBtn, R.anim.breath, 1200);
                }
                mFavoriteBtnAnimHelper.setRepeatCount(2);
                mFavoriteBtnAnimHelper.startAnim();

//                HeartBeatAnimationUtil.with(mFavoriteBtn)
//                        .scaleFrom(1.0f)
//                        .scaleTo(0.8f)
//                        .in(100)
//                        .after(1200)
//                        .start();

//                mFavoriteBtn.startAnimation(hyperspaceJumpAnimation);
            }
        });

        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mFavoriteBtnAnimHelper != null) {
                    mFavoriteBtnAnimHelper.stopAnim();
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        if (mFavoriteBtnAnimHelper != null) {
            mFavoriteBtnAnimHelper.release();
        }
        super.onBackPressed();
    }
}