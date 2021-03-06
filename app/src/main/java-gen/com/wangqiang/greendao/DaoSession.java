package com.wangqiang.greendao;

import android.database.sqlite.SQLiteDatabase;

import java.util.Map;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.AbstractDaoSession;
import de.greenrobot.dao.identityscope.IdentityScopeType;
import de.greenrobot.dao.internal.DaoConfig;

import com.wangqiang.greendao.WordInfo;
import com.wangqiang.greendao.LessonInfo;

import com.wangqiang.greendao.WordInfoDao;
import com.wangqiang.greendao.LessonInfoDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see de.greenrobot.dao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig wordInfoDaoConfig;
    private final DaoConfig lessonInfoDaoConfig;

    private final WordInfoDao wordInfoDao;
    private final LessonInfoDao lessonInfoDao;

    public DaoSession(SQLiteDatabase db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        wordInfoDaoConfig = daoConfigMap.get(WordInfoDao.class).clone();
        wordInfoDaoConfig.initIdentityScope(type);

        lessonInfoDaoConfig = daoConfigMap.get(LessonInfoDao.class).clone();
        lessonInfoDaoConfig.initIdentityScope(type);

        wordInfoDao = new WordInfoDao(wordInfoDaoConfig, this);
        lessonInfoDao = new LessonInfoDao(lessonInfoDaoConfig, this);

        registerDao(WordInfo.class, wordInfoDao);
        registerDao(LessonInfo.class, lessonInfoDao);
    }
    
    public void clear() {
        wordInfoDaoConfig.getIdentityScope().clear();
        lessonInfoDaoConfig.getIdentityScope().clear();
    }

    public WordInfoDao getWordInfoDao() {
        return wordInfoDao;
    }

    public LessonInfoDao getLessonInfoDao() {
        return lessonInfoDao;
    }

}
