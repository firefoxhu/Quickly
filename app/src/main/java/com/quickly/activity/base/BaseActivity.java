package com.quickly.activity.base;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
/**
 * 所有Activity的基类
 */
public abstract class BaseActivity extends AppCompatActivity {


    public String TAG;

    /**
     * 获取当前页面的布局
     *
     * @return
     */
    public abstract int getContentViewResId();

    /**
     * 初始化页面
     *
     * @param savedInstanceState
     */
    public abstract void init(Bundle savedInstanceState);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TAG = getComponentName().getShortClassName();

        setContentView(getContentViewResId());
        //ButterKnife.bind(this);

        init(savedInstanceState);
    }

}
