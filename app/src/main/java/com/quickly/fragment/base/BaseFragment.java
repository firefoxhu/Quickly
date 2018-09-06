package com.quickly.fragment.base;

import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public abstract class BaseFragment extends Fragment {

    private  View mRootView;

    /**
     * 获取页面布局文件
     *
     * @return
     */
    public abstract int getLayoutResId();

    /**
     * 初始化
     *
     * @param savedInstanceState
     */
    public abstract void init(Bundle savedInstanceState);

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mRootView = inflater.inflate(getLayoutResId(), container, false);
        init(savedInstanceState);
        return mRootView;
    }

    public View getmRootView() {
        return mRootView;
    }


    public static final  int EXTERNAL_STORAGE_PERMISSION = 0x01;

    public static final  int CAMERA_PERMISSION = 0x02;


    //判断是否有权限
    public boolean hasPermission(String... permissions){

        for(String permission: permissions){

            if(ContextCompat.checkSelfPermission(getActivity(),permission)
                    != PackageManager.PERMISSION_GRANTED)
                return false;
        }

        return true;
    }

    // 申请权限
    public void requestPermission(int requestCode,String...permissions){


        if(Build.VERSION.SDK_INT >= 23) {
            requestPermissions(permissions,requestCode);
        }

    }


    //  响应权限的申请
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        switch (requestCode){
            case EXTERNAL_STORAGE_PERMISSION:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    //授予权
                    doSDCard();

                }
                break;
            case CAMERA_PERMISSION:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    //授予权
                    doCamera();

                }
                break;
        }
    }

    public void doSDCard(){}

    public void doCamera(){}


}
