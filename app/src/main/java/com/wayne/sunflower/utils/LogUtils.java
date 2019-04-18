package com.wayne.sunflower.utils;

import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.FormatStrategy;
import com.orhanobut.logger.Logger;
import com.orhanobut.logger.PrettyFormatStrategy;

public class LogUtils {

    private static final String TAG = "Sunflower";

    public static void init(){
        FormatStrategy formatStrategy = PrettyFormatStrategy.newBuilder()
                .showThreadInfo(false)
                .methodCount(1)
                .methodOffset(1)
                .tag(TAG)
                .build();
        Logger.addLogAdapter(new AndroidLogAdapter(formatStrategy));
    }

    public static void i(String msg){
        Logger.i(msg);
    }

    public static void e(String msg){
        Logger.e(msg);
    }

    public static void w(String msg){
        Logger.w(msg);
    }

}
