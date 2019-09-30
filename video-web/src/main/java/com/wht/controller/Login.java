package com.wht.controller;

import com.wht.entry.Admin;
import com.wht.service.AdminService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@Api(value = "后台页面的跳转和账户名密码验证")
public class Login {
    @Autowired
    AdminService adminService;
    @RequestMapping("/login")
    @ResponseBody

    @ApiOperation(value = "验证用户登陆")
    public String isLogin(@ApiParam(value = "包含用户名userName和密码password") Admin admin) {
        int login = adminService.isLogin(admin);
        return login > 0 ? "success" : "error";
    }
    @RequestMapping("/behindLogin")
    public String BehindLogin() {
        return "behind/login";
    }


}