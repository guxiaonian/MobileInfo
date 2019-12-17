package com.mobile.mobileinfo.app;


import android.app.Application;
import android.content.Context;

import com.bun.miitmdid.core.JLibrary;


public class MyApplication extends Application {

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        JLibrary.InitEntry(base);
    }

}
