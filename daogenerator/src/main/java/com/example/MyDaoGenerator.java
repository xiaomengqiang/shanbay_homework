package com.example;

import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Schema;
/**
 * Created by wangqiang on 2016/3/8.
 */
public class MyDaoGenerator {
    public static void main(String[] args) throws Exception {
        Schema schema = new Schema(1, "com.wangqiang.greendao");
        addWordInfo(schema);
        addLessonInfo(schema);
        new DaoGenerator().generateAll(schema, "app/src/main/java-gen");
    }

    private static void addWordInfo(Schema schema){
        Entity wordInfo = schema.addEntity("WordInfo");
        wordInfo.setTableName("WordCache");
        wordInfo.addIdProperty().primaryKey().autoincrement();
        wordInfo.addStringProperty("lessonNo").notNull();//课程归属
        wordInfo.addStringProperty("content").notNull();//单词内容
        wordInfo.addStringProperty("level").notNull();//单词等级
        wordInfo.addStringProperty("meaning").notNull();//单词意思
    }

    private static void addLessonInfo(Schema schema){
        Entity lessonInfo = schema.addEntity("LessonInfo");
        lessonInfo.setTableName("LessonCache");
        lessonInfo.addIdProperty().primaryKey().autoincrement();
        lessonInfo.addStringProperty("UnitNo").notNull();
        lessonInfo.addStringProperty("LessonNo").notNull();
        lessonInfo.addStringProperty("content").notNull();
        lessonInfo.addStringProperty("title").notNull();
        lessonInfo.addStringProperty("meaning").notNull();
    }
}
