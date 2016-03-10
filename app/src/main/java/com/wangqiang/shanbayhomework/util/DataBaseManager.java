package com.wangqiang.shanbayhomework.util;

import android.database.sqlite.SQLiteDatabase;

import com.wangqiang.greendao.DaoMaster;
import com.wangqiang.greendao.DaoSession;
import com.wangqiang.shanbayhomework.ShanbayApplication;

/**
 * 数据库管理类
 *
 */
public class DataBaseManager {
    /**
     * 数据库名称
     */
    public static final String DB_NAME = "shanbayhomework-db";
    public static DataBaseManager INSTANCE = null;
    private DaoSession daoSession;

    private DataBaseManager() {
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(ShanbayApplication.getInstance().getApplicationContext(), DB_NAME, null);
        SQLiteDatabase db = helper.getWritableDatabase();
        DaoMaster daoMaster = new DaoMaster(db);
        daoSession = daoMaster.newSession();
    }

    public synchronized static DataBaseManager getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new DataBaseManager();
        }
        return INSTANCE;
    }

    public DaoSession getDaoSession() {
        return daoSession;
    }
}
