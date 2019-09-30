package com.wht.controller;

import com.wht.entry.User;
import com.wht.service.UserService;
import com.wht.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.Random;


@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    @RequestMapping("/insertUser")
    @ResponseBody
    public String insertUser(User user, HttpSession session) {
        user.setPassword(Md5Utils.getMd5Str(user.getPassword()));
        int result = userService.insertUser(user);
        session.setAttribute("userAccount",user.getEmail());
        return result > 0 ? "success" : "error";
    }

    @RequestMapping("/validateEmail")
    @ResponseBody
    public String validateEmail(String email) {
        int result = userService.validateEmail(email);
        return result > 0 ? "error" : "success";
    }

    @RequestMapping("/loginOut")
    public String loginOut(HttpSession session) {
        session.removeAttribute("userAccount");
        return "redirect:/index.jsp";

    }

    @RequestMapping("/loginUser")
    @ResponseBody
    public String loginUser(User user, HttpSession session) {
        user.setPassword(Md5Utils.getMd5Str(user.getPassword()));
        int result = userService.loginUser(user);
        session.setAttribute("userAccount",user.getEmail());
        return result > 0 ? "success" : "error";
    }

    @RequestMapping("/showMyProfile")
    public String showMyProfile(HttpSession session, Model model) {
        String userAccount = (String)session.getAttribute("userAccount");
        User user = userService.selectUserByEmail(userAccount);
        model.addAttribute("user",user);
        return "before/my_profile";
    }

    @RequestMapping("/changeProfile")
    public String changeProfile(HttpSession session,Model model) {
        String userAccount = (String)session.getAttribute("userAccount");
        User user = userService.selectUserByEmail(userAccount);
        model.addAttribute("user",user);
        return "before/change_profile";
    }

    @RequestMapping("/updateUser")
    public String updateUser(User user) {
        userService.updateUserById(user);
        return "redirect:/user/showMyProfile";
    }

    @RequestMapping("/upLoadImage")
    public String upLoadImage(MultipartFile image_file,String x1,String x2,String y1,String y2,HttpSession session) {
        String oldName = image_file.getOriginalFilename();
        String suffix = oldName.substring(oldName.lastIndexOf("."));
        String newName = UUIDUtils.getUUID() + suffix;
        String uploadUrl = InfoUtils.getProperties("UPLOAD_LOC");
        System.out.println(uploadUrl);
        String imgUrl = InfoUtils.getProperties("IMG_PATH") + newName ;
        try {
            image_file.transferTo(new File(uploadUrl,newName));
            float x11 = Float.valueOf(x1);
            float x22 = Float.valueOf(x2);
            float y11 = Float.valueOf(y1);
            float y22 = Float.valueOf(y2);
            ImageCut imageCut = new ImageCut();
            imageCut.cutImage(uploadUrl + "\\" + newName,(int)x11,(int)y11,(int)(x22-x11),(int)(y22-y11));
            String userAccount = (String)session.getAttribute("userAccount");
            userService.updateUserImgUrlByEmail(imgUrl,userAccount);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "redirect:/user/showMyProfile";
    }

    @RequestMapping("/forgetPassword")
    public String forgetPassword() {
        return "before/forget_password";
    }

    @RequestMapping("/changeAvatar")
    public String changeAvatar(Model model,HttpSession session) {
        String userAccount = (String)session.getAttribute("userAccount");
        User user = userService.selectUserByEmail(userAccount);
        model.addAttribute("user",user);
        return "before/change_avatar";
    }

    @RequestMapping("/passwordSafe")
    public String passwordSafe(HttpSession session,Model model) {
        String userAccount = (String)session.getAttribute("userAccount");
        User user = userService.selectUserByEmail(userAccount);
        model.addAttribute("user",user);
        return "before/password_safe";
    }

    @RequestMapping("/resetPassword")
    public String resetPassword(String email,String password,HttpSession session) {
        password = Md5Utils.getMd5Str(password);
        userService.updateUserPasswordByEmail(email,password);
        session.removeAttribute("userAccount");
        return "redirect:/index.jsp";
    }

    @RequestMapping("/sendEmail")
    @ResponseBody
    private String sendEmail(String email,HttpSession session) {
        Random random = new Random();
        int i = random.nextInt(899999)+100000;
        String s = String.valueOf(i);
        session.setAttribute("yzm",s);
        MailUtils.sendMail(email,s,"验证码");
        return "success";

    }

    @RequestMapping("/updatePassword")
    public String updatePassword(String newPassword,HttpSession session) {
        String password = Md5Utils.getMd5Str(newPassword);
        String email = (String)session.getAttribute("userAccount");
        userService.updateUserPasswordByEmail(email,password);
        return "redirect:/user/showMyProfile";

    }

    @RequestMapping("/validateEmailCode")
    public String validateEmailCode(HttpSession session,Model model,String email,String code) {
        String yzm = (String)session.getAttribute("yzm");
        if (yzm.equals(code)) {
            model.addAttribute("email",email);
            return "before/reset_password";
        }
        return "error";

    }

}
