package com.wht.entry;

import java.util.List;

public class VideoQueryVo {
    private int page = 1;
    private int rows = 5;
    private int begin = 0;

    private String title;
    private int speakerId;
    private int courseId;

    private List<Integer> ids;

    public VideoQueryVo() {
    }

    public VideoQueryVo(int page, int rows, int begin, String title, int speakerId, int courseId) {
        this.page = page;
        this.rows = rows;
        this.begin = begin;
        this.title = title;
        this.speakerId = speakerId;
        this.courseId = courseId;
    }

    public List<Integer> getIds() {
        return ids;
    }

    public void setIds(List<Integer> ids) {
        this.ids = ids;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public int getBegin() {
        return begin;
    }

    public void setBegin(int begin) {
        this.begin = begin;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getSpeakerId() {
        return speakerId;
    }

    public void setSpeakerId(int speakerId) {
        this.speakerId = speakerId;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    @Override
    public String toString() {
        return "VideoQueryVo{" +
                "page=" + page +
                ", rows=" + rows +
                ", begin=" + begin +
                ", title='" + title + '\'' +
                ", speakerId=" + speakerId +
                ", courseId=" + courseId +
                ", getIds=" + ids +
                '}';
    }
}
