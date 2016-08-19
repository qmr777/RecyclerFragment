package com.example.administrator.androidtest.utils;

import android.util.Log;

/**
 * Created by Administrator on 2016/8/18.
 */
public class MyLog {

    private static final boolean DEBUG = true;

    private final boolean isDebug;

    public MyLog() {
        this(false);
    }

    public MyLog(boolean isDebug){
        this.isDebug = isDebug;
    }

    public static void v(String TAG,String msg){
        if(DEBUG){
            Log.v(TAG, msg);
        }
    }

    public static void d(String TAG,String msg){
        if(DEBUG){
            Log.d(TAG, msg);
        }
    }

    public static void i(String TAG,String msg){
        if(DEBUG){
            Log.i(TAG, msg);
        }
    }

    public static void w(String TAG,String msg){
        if(DEBUG){
            Log.w(TAG, msg);
        }
    }

    public static void e(String TAG,String msg){
        if(DEBUG){
            Log.e(TAG, msg);
        }
    }

}
