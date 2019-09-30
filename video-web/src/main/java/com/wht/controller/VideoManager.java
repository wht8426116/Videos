package com.wht.controller;

import com.wht.entry.Course;
import com.wht.entry.Speaker;
import com.wht.entry.Video;
import com.wht.entry.VideoQueryVo;
import com.wht.service.CourseService;
import com.wht.service.VideoService;
import com.wht.utils.InfoUtils;
import com.wht.utils.Page;
import com.wht.utils.UUIDUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;


@Controller
@RequestMapping("/video")
public class VideoManager {
    @Autowired
    VideoService videoService;
    @Autowired
    CourseService courseService;

    /**
     * 条件查询video页面
     * @param videoQueryVo
     * @param model
     * @return
     */
    @RequestMapping("/list")
    public String List(VideoQueryVo videoQueryVo, Model model) {
        List<Course> course = videoService.getCourseTitle();
        List<Speaker> speaker = videoService.getSpeakerName();
        videoQueryVo.setBegin((videoQueryVo.getPage()-1)*videoQueryVo.getRows());
        System.out.println("videoQueryVo" + videoQueryVo);
        List<Video> videos = videoService.conditionQuery(videoQueryVo);
        System.out.println("+++" + videos);
        Page<Video> page = new Page<Video>();
        page.setPage(videoQueryVo.getPage());
        page.setSize(videoQueryVo.getRows());
        page.setRows(videos);
        page.setTotal(videoService.numberQuery(videoQueryVo));
        model.addAttribute("page",page);
        model.addAttribute("courseList", course);
        model.addAttribute("speakerList", speaker);
        return "behind/videoList";
    }

    /**
     * 添加视频
     * @param model
     * @return
     */
    @RequestMapping("/addVideo")
    public String addVideo(Model model) {
        List<Course> course = videoService.getCourseTitle();
        List<Speaker> speaker = videoService.getSpeakerName();
        model.addAttribute("courseList", course);
        model.addAttribute("speakerList", speaker);
        return "behind/addVideo";
    }

    /**
     * 保存新增或者修改的数据
     *
     * @param video 传回来的数据
     */
    @RequestMapping("/saveOrUpdate")
    public String saveOrUpdate(Video video) {
        System.out.println(video);
        if (video.getId() != 0) {
            videoService.updateVideo(video);
        } else {
            videoService.addVideo(video);
        }
        return "behind/videoList";
    }

    /**
     * 跳转到添加页面
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("/edit")
    public String edit(int id,Model model) {
        Video video = videoService.selectVideoById(id);
        model.addAttribute("video",video);
        List<Course> course = videoService.getCourseTitle();
        List<Speaker> speaker = videoService.getSpeakerName();
        model.addAttribute("courseList", course);
        model.addAttribute("speakerList", speaker);
        return "behind/addVideo";
    }

    /**
     * 通过id值删除video
     * @param id
     * @return
     */
    @RequestMapping("/videoDel.action")
    @ResponseBody
    public String deleteById(int id) {
        int i = videoService.deleteById(id);
        return i > 0 ? "success" : "error";

    }

    /**
     * ajax图片上传
     * @param imageUrl1
     * @return
     */
    @RequestMapping("/upload")
    @ResponseBody
    public String upload(MultipartFile imageUrl1) {
        String oldUrl = imageUrl1.getOriginalFilename();
        String suffix = oldUrl.substring(oldUrl.lastIndexOf("."));
        String newUrl = UUIDUtils.getUUID() + suffix;
        //获取包括虚拟路径的完整图片路径
        String imgUrl = InfoUtils.getProperties("IMG_PATH") + newUrl;
        try {
            //图片上传
            imageUrl1.transferTo(new File(InfoUtils.getProperties("UPLOAD_LOC"),newUrl));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return imgUrl;
    }

    /**
     * 批量删除
     * @param videoQueryVo 使用对象中的list接受单选框中的id值
     * @return
     */
    @RequestMapping("/delBatchVideos")
    public String delBatchVideos(VideoQueryVo videoQueryVo) {
        int i = videoService.delBatchVideos(videoQueryVo);
        return "redirect:/video/list";

    }

    /**
     * 前端页面
     * @return
     */
    @RequestMapping("/showVideo")
    public String showVideo(int videoId,String subjectName,Model model) {
        Video video = videoService.selectVideoByIdd(videoId);
        int courseId = video.getCourseId();
        List<Video> videoList = courseService.getVideoList(courseId);
        Course course = courseService.getCourseDescById(courseId);
        course.setVideoList(videoList);
        System.out.println(course);
        model.addAttribute("course",course);
        model.addAttribute("video",video);
        model.addAttribute("subjectName",subjectName);
        return "before/section";
    }

    @RequestMapping("/updatePlayNum")
    public void updatePlayNum(Video video) {
        video.setPlayNum(video.getPlayNum() + 1);
        videoService.updatePlayNum(video);

    }

}
