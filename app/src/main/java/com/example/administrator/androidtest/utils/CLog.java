package com.example.administrator.androidtest.utils;

import android.util.Log;

/**
 * Created by Administrator on 2016/8/18.
 */
public class CLog {

    private final boolean DEBUG;

    public CLog() {
        this(false);
    }

    public CLog(boolean isDebug){
        this.DEBUG = isDebug;
    }

    public void v(String TAG,String msg){
        if(DEBUG){
            Log.v(TAG, msg);
        }
    }

    public void d(String TAG,String msg){
        if(DEBUG){
            Log.d(TAG, msg);
        }
    }

    public void i(String TAG,String msg){
        if(DEBUG){
            Log.i(TAG, msg);
        }
    }

    public void w(String TAG,String msg){
        if(DEBUG){
            Log.w(TAG, msg);
        }
    }

    public void e(String TAG,String msg){
        if(DEBUG){
            Log.e(TAG, msg);
        }
    }

}
