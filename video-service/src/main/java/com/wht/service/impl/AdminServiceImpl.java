package com.wht.service.impl;

import com.wht.entry.Admin;
import com.wht.mapper.AdminMapper;
import com.wht.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    AdminMapper mapper;

    public int isLogin(Admin admin) {
        int login = mapper.isLogin(admin);
        return login;
    }
}
