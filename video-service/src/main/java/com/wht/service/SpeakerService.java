package com.wht.service;

import com.wht.entry.Speaker;

import java.util.List;

public interface SpeakerService {
    List<Speaker> showList(Integer page, Integer pageSize);

    void updateVideo(Speaker speaker);

    void addVideo(Speaker speaker);

    Speaker selectSpeakerById(int id);

    int deleteSpeakerById(int id);
}
