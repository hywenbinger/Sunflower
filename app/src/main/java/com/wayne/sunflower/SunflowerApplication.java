package com.wayne.sunflower;

import android.app.Application;

import com.wayne.sunflower.utils.LogUtils;

public class SunflowerApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        LogUtils.init();
    }
}
