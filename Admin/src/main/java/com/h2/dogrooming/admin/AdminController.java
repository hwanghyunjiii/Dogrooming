package com.h2.dogrooming.admin;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AdminController {

    @Autowired
    AdminService adminService;

    //로그인
    @GetMapping("/admin/login")
    public String login(){
        return "page/admin/login";
    }

    //회원가입
    @GetMapping("/admin/register")
    public String register(){
        return "page/admin/register"; // page/register.html
    }

    @PostMapping("/admin/register")
    public String register(Admin admin){
        adminService.registerAdmin(admin);

        return "redirect:/admin/login";
    }

    @PostMapping("/admin/login")
    public String login(String adminId, String password){
        Admin admin = adminService.getAdmin(adminId, password);

        if(admin != null){
            return "page/index";
        }else{
            return "page/test";
        }

    }


}
