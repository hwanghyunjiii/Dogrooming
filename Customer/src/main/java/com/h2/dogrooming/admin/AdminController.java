package com.h2.dogrooming.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.DriverManager;

@Slf4j
@Controller
public class AdminController {

    @Autowired
    private AdminService adminService;

    @Autowired
    private AdminRepository adminRepository;

    @GetMapping(value = "/login")
    public String goLogin()
    {
        return "page/login";
    }


    @GetMapping(value = "/signup")
    public String goSignUp()
    {
        log.info("count : " +  adminRepository.count());
        log.info("count : " +  adminRepository.findAll());

        return "page/signup";
    }


    @PostMapping(value = "/signup")
    public String Join(Admin admin)
    {
        log.info("AdminID : " + admin.getAdminID());
        log.info("Email : " + admin.getEmail());
        log.info("PW : " + admin.getPassword());
        log.info("Phone : " + admin.getPhone());
        log.info("Name : " + admin.getName());

        adminService.signUpAdmin(admin);
        log.info("signupadmin 후");

        return "redirect:/login";
    }

}
