package com.wht.service;

import com.wht.entry.Course;
import com.wht.entry.Subject;
import com.wht.entry.Video;

import java.util.List;

public interface CourseService {

    List<Course> showList(int subjectId);

    List<Video> getVideoList(int id);

    Subject showSubjectList(int subjectId);

    Course getCourseDescById(int id);
}
