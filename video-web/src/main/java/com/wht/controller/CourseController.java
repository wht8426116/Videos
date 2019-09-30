package com.wht.controller;

import com.wht.entry.Course;
import com.wht.entry.Subject;
import com.wht.entry.Video;
import com.wht.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/course")
public class CourseController {

    @Autowired
    CourseService service;
    @RequestMapping("/list")
    public String showList(int subjectId, Model model) {
        Subject subject = service.showSubjectList(subjectId);

        List<Course> list = service.showList(subjectId);
        for (Course course : list) {
            List<Video> list1 = service.getVideoList(course.getId());
            course.setVideoList(list1);
        }
        subject.setCourseList(list);
        subject.setId(subjectId);
        model.addAttribute("subject",subject);
        System.out.println(subject);
        return "before/course";
    }
}
