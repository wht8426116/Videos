package com.wht.service;

import com.wht.entry.User;

public interface UserService {
    int insertUser(User user);

    int validateEmail(String email);

    int loginUser(User user);

    User selectUserByEmail(String userAccount);

    void updateUserById(User user);

    void updateUserImgUrlByEmail(String imgUrl, String userAccount);

    void updateUserPasswordByEmail(String email, String password);
}
