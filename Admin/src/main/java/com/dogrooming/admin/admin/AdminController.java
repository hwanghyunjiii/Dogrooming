package com.dogrooming.admin.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {

    //로그인
    @GetMapping("/admin/login")
    public String login(){
        return "admin/login.html";
    }

    //회원가입
    @GetMapping("/admin/register")
    public String register(){
        return "admin/register.html";
    }
}
