package com.mobile.mobileinfo;


import android.app.Application;

import com.squareup.leakcanary.LeakCanary;


public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        //内存泄漏检测
        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return;
        }
        LeakCanary.install(this);


    }



}
