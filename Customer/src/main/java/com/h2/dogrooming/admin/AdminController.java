package com.h2.dogrooming.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import lombok.extern.slf4j.Slf4j;

import javax.validation.Valid;
import java.sql.Connection;
import java.sql.DriverManager;

@Slf4j
@Controller
public class AdminController {

    @Autowired
    private AdminService adminService;

    @GetMapping(value = "/login")
    public String goLogin()
    {
        return "page/login";
    }

    @GetMapping(value = "/signup")
    public String goSignUp()
    {
        return "page/signup";
    }

    @PostMapping(value = "/signup")
    public String Join(@Valid @ModelAttribute Admin admin, Errors errors)
    {
        // @Valid : 어노테이션들의 조건을 만족하는지 확인
        if (errors.hasErrors()) {
            log.info(errors.getAllErrors().toString());
            return "page/signup";
        }

        adminService.signUpAdmin(admin);
        return "redirect:/login";
    }


    // 아이디 체크
    @PostMapping("/checkadminid")
    public boolean CheckAdminID(@RequestParam("adminID") String AdminID){
        log.info("전달받은 id:"+AdminID);
        boolean result = adminService.CheckAdminID(AdminID);
        log.info("확인 결과:"+result);
        return result;
    }
}
