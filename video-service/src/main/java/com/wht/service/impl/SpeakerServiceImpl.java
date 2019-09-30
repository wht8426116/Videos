package com.wht.service.impl;

import com.github.pagehelper.PageHelper;
import com.wht.entry.Speaker;
import com.wht.mapper.SpeakerMapper;
import com.wht.service.SpeakerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class SpeakerServiceImpl implements SpeakerService {
    @Autowired
    SpeakerMapper mapper;

    public List<Speaker> showList(Integer page, Integer pageSize) {
        PageHelper.startPage(page,pageSize);
        List<Speaker> speakers = mapper.showList();
        return speakers;
    }


    public void updateVideo(Speaker speaker) {
        mapper.updateSpeaker(speaker);
    }


    public void addVideo(Speaker speaker) {
        mapper.addSpeaker(speaker);
    }


    public Speaker selectSpeakerById(int id) {
        return mapper.selectSpeakerById(id);
    }


    public int deleteSpeakerById(int id) {
        return mapper.deleteSpeakerById(id);
    }
}
