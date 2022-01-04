package com.dogrooming.admin.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {

    // 테스트 페이지 (추후 삭제 예정)
    @GetMapping("/admin/test")
    public String test(){
        return "admin/test";
    }

    //로그인
    @GetMapping("/admin/login")
    public String login(){
        return "admin/login";
    }

    //회원가입
    @GetMapping("/admin/register")
    public String register(){
        return "admin/register";
    }
}
