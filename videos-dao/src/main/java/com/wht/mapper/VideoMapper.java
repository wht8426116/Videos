package com.wht.mapper;

import com.wht.entry.Course;
import com.wht.entry.Speaker;
import com.wht.entry.Video;
import com.wht.entry.VideoQueryVo;

import java.util.List;

public interface VideoMapper {
    List<Video> selectAll();
    List<Course> getCourseTitle();
    List<Speaker> getSpeakerName();

    List<Video> conditionQuery(VideoQueryVo videoQueryVo);

    int numberQuery(VideoQueryVo videoQueryVo);

    Video selectVideoById(int id);

    /**
     * video和speaker一对一
     * @param id
     * @return
     */
    Video selectVideoByIdd(int id);

    void addVideo(Video video);

    void updateVideo(Video video);

    int deleteById(int id);

    int delBatchVideos(VideoQueryVo videoQueryVo);

    void updatePlayNum(Video video);
}
