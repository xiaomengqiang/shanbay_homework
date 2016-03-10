package com.wangqiang.greendao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT. Enable "keep" sections if you want to edit. 
/**
 * Entity mapped to table "LessonCache".
 */
public class LessonInfo {

    private Long id;
    /** Not-null value. */
    private String UnitNo;
    /** Not-null value. */
    private String LessonNo;
    /** Not-null value. */
    private String content;
    /** Not-null value. */
    private String title;
    /** Not-null value. */
    private String meaning;

    public LessonInfo() {
    }

    public LessonInfo(Long id) {
        this.id = id;
    }

    public LessonInfo(Long id, String UnitNo, String LessonNo, String content, String title, String meaning) {
        this.id = id;
        this.UnitNo = UnitNo;
        this.LessonNo = LessonNo;
        this.content = content;
        this.title = title;
        this.meaning = meaning;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    /** Not-null value. */
    public String getUnitNo() {
        return UnitNo;
    }

    /** Not-null value; ensure this value is available before it is saved to the database. */
    public void setUnitNo(String UnitNo) {
        this.UnitNo = UnitNo;
    }

    /** Not-null value. */
    public String getLessonNo() {
        return LessonNo;
    }

    /** Not-null value; ensure this value is available before it is saved to the database. */
    public void setLessonNo(String LessonNo) {
        this.LessonNo = LessonNo;
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
    public String getTitle() {
        return title;
    }

    /** Not-null value; ensure this value is available before it is saved to the database. */
    public void setTitle(String title) {
        this.title = title;
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
