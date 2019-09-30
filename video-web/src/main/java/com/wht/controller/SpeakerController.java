package com.wht.controller;


import com.github.pagehelper.PageInfo;
import com.wht.entry.Speaker;
import com.wht.service.SpeakerService;
import com.wht.utils.InfoUtils;
import com.wht.utils.UUIDUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/speaker")
public class SpeakerController {
    @Autowired
    SpeakerService speakerService;
    @RequestMapping("/showList")
    public String speakerPage(Model model, @RequestParam(name = "page",defaultValue = "1")Integer page, @RequestParam(name = "pageSize",defaultValue = "5") Integer pageSize) {
        List<Speaker> speakers = speakerService.showList(page, pageSize);
        PageInfo<Speaker> pageInfo = new PageInfo<Speaker>(speakers);
        model.addAttribute("pageInfo",pageInfo);
        return "behind/speakerList";
    }
    @RequestMapping("/addspeaker")
    public String addspeaker() {
        return "behind/addSpeaker";
    }

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
            System.out.println(imgUrl);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return imgUrl;
    }

    @RequestMapping("/saveOrUpdate")
    public String saveOrUpdate(Speaker speaker) {
        System.out.println(speaker);
        if (speaker.getId() != 0) {
            speakerService.updateVideo(speaker);
        } else {
            speakerService.addVideo(speaker);
        }
        return "behind/speakerList";

    }

    @RequestMapping("/edit")
    public String edit(int id,Model model) {
        Speaker speaker = speakerService.selectSpeakerById(id);
        model.addAttribute("speaker",speaker);
        return "behind/addSpeaker";
    }

    @RequestMapping("/deleteSpeakerById")
    @ResponseBody
    public String deleteSpeakerById(int id) {
        System.out.println(id);
        int result = speakerService.deleteSpeakerById(id);
        System.out.println("result" + result);
        return result > 0 ? "success" : "error";
    }


}
