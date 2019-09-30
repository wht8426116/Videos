package com.wht.mapper;

import com.wht.entry.Speaker;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface SpeakerMapper {
    @Select("select * from speaker")
    @Results(id = "speakerMap", value = {
            @Result(id = true, column = "id", property = "id"),
            @Result(column = "speaker_name", property = "speakerName"),
            @Result(column = "speaker_desc", property = "speakerDesc"),
            @Result(column = "speaker_job", property = "speakerJob"),
            @Result(column = "head_img_url", property = "headImgUrl")
    })
    List<Speaker> showList();

    @Update("update speaker set speaker_name=#{speakerName},speaker_desc=#{speakerDesc},speaker_job=#{speakerJob},head_img_url=#{headImgUrl} where id=#{id}")
    void updateSpeaker(Speaker speaker);

    @Insert("insert into speaker(speaker_name,speaker_desc,speaker_job,head_img_url) values(#{speakerName},#{speakerDesc},#{speakerJob},#{headImgUrl})")
    void addSpeaker(Speaker speaker);

    @Select("select * from speaker where id=#{id}")
    @ResultMap("speakerMap")
    Speaker selectSpeakerById(int id);

    @Delete("delete from speaker where id=#{id}")
    int deleteSpeakerById(int id);
}
