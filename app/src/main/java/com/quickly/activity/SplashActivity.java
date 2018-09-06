package com.quickly.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.quickly.MainActivity;
import com.quickly.R;
import com.quickly.SharePreferenceManager;

public class SplashActivity extends AppCompatActivity {


    private Handler mHandler = new Handler();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_layout);


        boolean isFirst = SharePreferenceManager.getmInstance().getBoolean(SharePreferenceManager.IS_FIRST_COME_APP,true);

        Intent intent = new Intent();
        // 如果是第一次启动，则先进入功能引导页
        if (isFirst) {
            // 修改进入状态
            SharePreferenceManager.getmInstance().putBoolean(SharePreferenceManager.IS_FIRST_COME_APP,false);
            intent.setClass(SplashActivity.this, IntroActivity.class);
            startActivity(intent);
            finish();
        }else {
            mHandler.postDelayed(()->{
                startActivity(new Intent(this,MainActivity.class));
                finish();
            },200);
        }
    }
}
