package com.wht.entry;

import lombok.Data;

@Data
public class Video {
    private int id;
    private String title;
    private String detail;
    private int speakerId;
    private int courseId;

    private String videoUrl;
    private String imageUrl;
    private String speakerName;
    private int time;
    private int playNum;
    private Speaker speaker;

    public String getShowTime() {
        int first = time/3600;
        int second = time % 3600 / 60;
        int ending = time % 3600 % 60 % 60;

        String showTime = first + ":" + second + ":" + ending;
        return showTime;
    }
}
