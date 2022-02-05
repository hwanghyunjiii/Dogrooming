package com.h2.dogrooming.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import lombok.extern.slf4j.Slf4j;

import javax.validation.Valid;
import java.util.Date;

@Slf4j
@Controller
public class AdminController {

    @Autowired
    private AdminService adminService;

    @RequestMapping(value = "/login")
    public String goLogin(Authentication authentication)
    {
        // 로그인 여부 확인
        if(authentication != null)
        {
            return "redirect:/";
        }

        return "page/login";
    }

    @GetMapping(value = "/mypage")
    public String goMyPage(Model model, Authentication authentication)
    {
        // 로그인 여부 확인
        if(authentication == null)
        {
            return "redirect:/login";
        }

        UserDetails userDetails = (UserDetails)authentication.getPrincipal();
        Admin admin = adminService.getAdmin(userDetails.getUsername());

        model.addAttribute("admin", admin);
        return "page/mypage";
    }

    @GetMapping(value = "/signup")
    public String goSignUp(Authentication authentication)
    {
        // 로그인 여부 확인
        if(authentication != null)
        {
            return "redirect:/";
        }

        return "page/signup";
    }

    @PostMapping(value = "/mypage")
    public String modifyAdmin(Admin admin, Authentication authentication)
    {
        // 로그인 여부 확인
        if(authentication == null)
        {
            return "redirect:/login";
        }

        // 현재 로그인된 사용사 정보 조회
        UserDetails userDetails = (UserDetails)authentication.getPrincipal();
        Admin currentAdmin = adminService.getAdmin(userDetails.getUsername());

        // 사용자와 전달받은 ID가 동일한지 확인
        if(!currentAdmin.getAdminId().equals(admin.getAdminId()))
        {
            return "redirect:/login";
        }

        currentAdmin.setName(admin.getName());
        currentAdmin.setEmail(admin.getEmail());
        currentAdmin.setPhone(admin.getPhone());
        currentAdmin.setUpdateDate(new Date());

        adminService.modifyAdmin(currentAdmin);
        return "redirect:/";
    }

    @PostMapping(value = "/signup")
    public String registerAdmin(@Valid @ModelAttribute Admin admin, Errors errors)
    {
        // @Valid : 어노테이션들의 조건을 만족하는지 확인
        if (errors.hasErrors()) {
            log.info(errors.getAllErrors().toString());
            return "page/signup";
        }
        adminService.registerAdmin(admin);
        return "redirect:/login";
    }

    // 아이디 체크
    @PostMapping("/check_adminId")
    @ResponseBody
    public boolean checkAdminID(@RequestParam("adminId") String adminId){
        boolean result = adminService.checkAdminId(adminId);
        return result;
    }

    // 아이디 찾기
    @PostMapping("/search_adminId")
    @ResponseBody
    public String checkAdminID(@RequestParam("name") String name,
                               @RequestParam("email") String email)
    {
        String adminID = adminService.findAdminId(name, email);
        return adminID;
    }
}
