package com.example.administrator.androidtest.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by qmr on 2016/8/31.
 *
 */
public class SharePreferenceUtils {

    public static final String DAY_NIGHT_MODE = "DayNightMode";

    public static final String TAG = SharePreferenceUtils.class.getSimpleName();

    static Context context;

    static SharePreferenceUtils sharePreferenceUtils;

    SharedPreferences sp;

    public static void init(Context c){
        context = c;
    }

    private SharePreferenceUtils(){
        if(context == null)
            throw new RuntimeException("未初始化");
        sp = context.getSharedPreferences(TAG,Context.MODE_PRIVATE);
    }

    public static SharePreferenceUtils getInstance(){
        if(sharePreferenceUtils == null){
            synchronized (SharePreferenceUtils.class) {
                sharePreferenceUtils = new SharePreferenceUtils();
            }
        }
        return sharePreferenceUtils;
    }

    public void setDayNightMode(boolean m){
        sp.edit().putBoolean(DAY_NIGHT_MODE,m).apply();
    }

    public boolean getMode(){
        return sp.getBoolean(DAY_NIGHT_MODE,true);
    }

}
