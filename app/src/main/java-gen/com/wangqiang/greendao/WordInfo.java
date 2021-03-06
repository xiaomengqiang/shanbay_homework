package com.wangqiang.greendao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT. Enable "keep" sections if you want to edit. 
/**
 * Entity mapped to table "WordCache".
 */
public class WordInfo {

    private Long id;
    /** Not-null value. */
    private String lessonNo;
    /** Not-null value. */
    private String content;
    /** Not-null value. */
    private String level;
    /** Not-null value. */
    private String meaning;

    public WordInfo() {
    }

    public WordInfo(Long id) {
        this.id = id;
    }

    public WordInfo(Long id, String lessonNo, String content, String level, String meaning) {
        this.id = id;
        this.lessonNo = lessonNo;
        this.content = content;
        this.level = level;
        this.meaning = meaning;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    /** Not-null value. */
    public String getLessonNo() {
        return lessonNo;
    }

    /** Not-null value; ensure this value is available before it is saved to the database. */
    public void setLessonNo(String lessonNo) {
        this.lessonNo = lessonNo;
    }

    /** Not-null value. */
    public String getContent() {
        return content;
    }

    /** Not-null value; ensure this value is available before it is saved to the database. */
    public void setContent(String content) {
        this.content = content;
    }

    /** Not-null value. */
    public String getLevel() {
        return level;
    }

    /** Not-null value; ensure this value is available before it is saved to the database. */
    public void setLevel(String level) {
        this.level = level;
    }

    /** Not-null value. */
    public String getMeaning() {
        return meaning;
    }

    /** Not-null value; ensure this value is available before it is saved to the database. */
    public void setMeaning(String meaning) {
        this.meaning = meaning;
    }

}
