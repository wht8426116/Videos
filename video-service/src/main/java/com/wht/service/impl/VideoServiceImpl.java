package com.wht.service.impl;

import com.wht.entry.Course;
import com.wht.entry.Speaker;
import com.wht.entry.Video;
import com.wht.entry.VideoQueryVo;
import com.wht.mapper.VideoMapper;
import com.wht.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class VideoServiceImpl implements VideoService {
    @Autowired
    VideoMapper mapper;

    public List<Video> selectAll() {
        List<Video> videos = mapper.selectAll();
        return videos;
    }


    public List<Course> getCourseTitle() {
        List<Course> courseTitle = mapper.getCourseTitle();
        return courseTitle;
    }


    public List<Speaker> getSpeakerName() {
        List<Speaker> speakerName = mapper.getSpeakerName();
        return speakerName;
    }


    public List<Video> conditionQuery(VideoQueryVo videoQueryVo) {
        List<Video> list = mapper.conditionQuery(videoQueryVo);
        return list;
    }


    public int numberQuery(VideoQueryVo videoQueryVo) {
        int i = mapper.numberQuery(videoQueryVo);
        return i;
    }


    public Video selectVideoById(int id) {
        Video video = mapper.selectVideoById(id);
        return video;
    }


    public Video selectVideoByIdd(int id) {
        Video video = mapper.selectVideoByIdd(id);
        return video;
    }


    public void updatePlayNum(Video video) {
        mapper.updatePlayNum(video);
    }



    public void addVideo(Video video) {
        mapper.addVideo(video);
    }


    public void updateVideo(Video video) {
        mapper.updateVideo(video);
    }


    public int deleteById(int id) {
        int result = mapper.deleteById(id);
        return result;
    }


    public int delBatchVideos(VideoQueryVo videoQueryVo) {
        int i = mapper.delBatchVideos(videoQueryVo);
        return i;
    }


}
