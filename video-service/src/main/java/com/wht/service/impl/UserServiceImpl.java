package com.wht.service.impl;

import com.wht.entry.User;
import com.wht.mapper.UserMapper;
import com.wht.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper mapper;

    public int insertUser(User user) {
        return mapper.insertUser(user);
    }


    public int validateEmail(String email) {
        return mapper.validateEmail(email);
    }


    public int loginUser(User user) {
        return mapper.loginUser(user);
    }


    public User selectUserByEmail(String userAccount) {
        return mapper.selectUserByEmail(userAccount);
    }


    public void updateUserById(User user) {
        mapper.updateUserById(user);
    }


    public void updateUserImgUrlByEmail(String imgUrl,String userAccount) {
        mapper.updateUserImgUrlByEmail(imgUrl,userAccount);
    }


    public void updateUserPasswordByEmail(String email, String password) {
        mapper.updateUserPasswordByEmail(email,password);
    }

}
