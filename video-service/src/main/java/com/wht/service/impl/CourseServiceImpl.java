package com.wht.service.impl;


import com.wht.entry.Course;
import com.wht.entry.Subject;
import com.wht.entry.Video;
import com.wht.mapper.CourseMapper;
import com.wht.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    CourseMapper mapper;
    @Override
    public List<Course> showList(int subjectId) {
        return mapper.showList(subjectId);
    }

    @Override
    public List<Video> getVideoList(int id) {
        return mapper.getVideoList(id);
    }

    @Override
    public Subject showSubjectList(int subjectId) {
        return mapper.showSubjectList(subjectId);
    }

    @Override
    public Course getCourseDescById(int id) {
        return mapper.getCourseDescById(id);
    }


}
