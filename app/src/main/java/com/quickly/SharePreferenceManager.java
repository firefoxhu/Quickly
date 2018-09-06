package com.quickly;

import android.content.Context;
import android.content.SharedPreferences;

import com.quickly.application.SyApplication;

public class SharePreferenceManager  {



    private static  SharePreferenceManager mInstance;


    private   static SharedPreferences sp;

    private static SharedPreferences.Editor editor;


    private static final  String SHARE_PREFERENCE_NAME = "quick.pre";


    public   static final String  IS_FIRST_COME_APP = "isFirst";


    public static SharePreferenceManager getmInstance(){

        if(mInstance == null) {


            synchronized (SharePreferenceManager.class){

                if(mInstance == null) {
                    mInstance = new SharePreferenceManager();
                }
            }
        }
        return mInstance;
    }


    private SharePreferenceManager(){
        sp = SyApplication.context.getSharedPreferences(SHARE_PREFERENCE_NAME,Context.MODE_PRIVATE);
        editor = sp.edit();
    }




    public  void putInt(String key,int value){
        editor.putInt(key,value);
        editor.apply();
    }


    public  int getInt(String key,int defaultValue){
        return sp.getInt(key,defaultValue);
    }


    public  void putBoolean(String key,boolean value){
        editor.putBoolean(key,value);
        editor.apply();
    }


    public  boolean getBoolean(String key,boolean defaultValue){
        return sp.getBoolean(key,defaultValue);
    }






}
