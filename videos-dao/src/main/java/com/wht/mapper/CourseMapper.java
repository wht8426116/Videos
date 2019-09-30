package com.wht.mapper;

import com.wht.entry.Course;
import com.wht.entry.Subject;
import com.wht.entry.Video;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface CourseMapper {
    @Select("select * from course where subject_id=#{subjectId}")
    @Results(id="courseMap",value = {
            @Result(id = true,column = "id", property = "id"),
            @Result(column = "course_title",property = "courseTitle"),
            @Result(column = "course_desc",property = "courseDesc"),
            @Result(column = "subject_id",property = "subjectId")
    })
    List<Course> showList(int subjectId);

    @Select("select v.id,v.title,v.time,v.play_num as playNum,v.image_url as imageUrl,v.video_url as videoUrl,s.speaker_name as speakerName from Video v,speaker s where v.speaker_id=s.id and v.course_id=#{id}")
    List<Video> getVideoList(int id);

    @Select("select subject_name as subjectName from subject where id=#{subjectId}")
    Subject showSubjectList(int subjectId);

    @Select("select id,course_desc as courseDesc from course where id=#{id}")
    Course getCourseDescById(int id);
}
