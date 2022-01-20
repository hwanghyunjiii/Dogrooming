package com.h2.dogrooming.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import lombok.extern.slf4j.Slf4j;

import javax.validation.Valid;

@Slf4j
@Controller
public class AdminController {

    @Autowired
    private AdminService adminService;

    @RequestMapping(value = "/login")
    public String goLogin()
    {
        return "page/login";
    }

    @GetMapping(value = "/mypage")
    public String goMyPage(Model model, Authentication authentication)
    {
        if(authentication == null)
        {
            return "redirect:/login";
        }

        UserDetails userDetails = (UserDetails)authentication.getPrincipal();
        Admin admin = adminService.adminInfo(userDetails.getUsername());


        model.addAttribute("haram", admin.getAdminID());
        model.addAttribute("admin", admin);
        return "page/mypage";
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
    @ResponseBody
    public boolean CheckAdminID(@RequestParam("adminID") String AdminID){
        boolean result = adminService.CheckAdminID(AdminID);
        return result;
    }
}
