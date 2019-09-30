package com.wht.mapper;

import com.wht.entry.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface UserMapper {

    @Insert("insert into user(email,password) values(#{email},#{password})")
    int insertUser(User user);


    @Select("select count(1) from user where email=#{email}")
    int validateEmail(String email);

    @Select("select count(1) from user where email=#{email} and password=#{password}")
    int loginUser(User user);

    @Select("select * from user where email=#{userAccount}")
    User selectUserByEmail(String userAccount);

    @Update("update user set nickName=#{nickName},sex=#{sex},birthday=#{birthday},address=#{address} where id=#{id}")
    void updateUserById(User user);

    @Update("update user set imgUrl=#{imgUrl} where email=#{userAccount}")
    void updateUserImgUrlByEmail(@Param("imgUrl") String imgUrl, @Param("userAccount") String userAccount);

    @Update("update user set password=#{password} where email=#{email}")
    void updateUserPasswordByEmail(@Param("email") String email, @Param("password") String password);
}
