package com.wangqiang.shanbayhomework;

import android.app.Application;
import android.content.Context;
import android.os.Environment;

/**
 * Created by wangqiang on 2016/3/9.
 */
public class ShanbayApplication extends Application{
    private static ShanbayApplication instance;
    public static Context context;
    //APP的SD路径
    public static final String APP_PATH = Environment
            .getExternalStorageDirectory().getAbsolutePath() + "/ShanBayHomeWork/";
    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        instance = this;
    }

    public static ShanbayApplication getInstance() {
        return instance;
    }
}
