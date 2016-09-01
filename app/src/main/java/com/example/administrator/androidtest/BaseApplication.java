package com.example.administrator.androidtest;

import android.app.Application;

import com.example.administrator.androidtest.utils.SharePreferenceUtils;
import com.squareup.leakcanary.LeakCanary;

/**
 * Created by Administrator on 2016/8/9.
 */
public class BaseApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        SharePreferenceUtils.init(this);
        LeakCanary.install(this);
    }

}
