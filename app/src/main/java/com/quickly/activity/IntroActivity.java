package com.quickly.activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.github.paolorotolo.appintro.AppIntro2;
import com.github.paolorotolo.appintro.AppIntroFragment;
import com.quickly.MainActivity;
import com.quickly.R;

public class IntroActivity extends AppIntro2 {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        addSlide(AppIntroFragment.newInstance("哈哈哈哈", "wo家发生了纠纷大厦发顺丰的说法大师傅大三饭", R.mipmap.intro_01, Color.BLUE));

        addSlide(AppIntroFragment.newInstance("哈哈哈哈", "wo家发生了纠纷大厦发顺丰的说法大师傅大三饭", R.mipmap.intro_02, Color.RED));

        addSlide(AppIntroFragment.newInstance("哈哈哈哈", "wo家发生了纠纷大厦发顺丰的说法大师傅大三饭", R.mipmap.intro_03, Color.BLUE));

        showSkipButton(false);
    }

    @Override
    public void onDonePressed(Fragment currentFragment) {
        super.onDonePressed(currentFragment);
        // Do something when users tap on Done button.

        Intent intent = new Intent();

        intent.setClass(IntroActivity.this,MainActivity.class);
        startActivity(intent);

        finish();


    }

}