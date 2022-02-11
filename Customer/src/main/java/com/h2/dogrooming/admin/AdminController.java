package com.h2.dogrooming.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

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

        model.addAttribute("admin", new AdminDTO(admin));
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

    @PostMapping(value = "/modifyAdmin")
    @ResponseBody
    public Map<String, Object> modifyAdmin(Authentication authentication,
                                           AdminDTO adminDTO)
    {
        Map<String, Object> map = new HashMap<String, Object>();

        // 로그인 여부 확인
        if(authentication == null)
        {
            map.put("code", 997);
            map.put("message", "로그인을 해주세요.");
            return map;
        }

        // 현재 로그인된 사용사 정보 조회
        UserDetails userDetails = (UserDetails)authentication.getPrincipal();
        Admin currentAdmin = adminService.getAdmin(userDetails.getUsername());

        // 사용자와 전달받은 ID가 동일한지 확인
        if(!currentAdmin.getAdminId().equals(adminDTO.getAdminId()))
        {
            map.put("code", 998);
            map.put("message", "로그인을 해주세요.");
            return map;
        }

        // 회원 정보 수정
        currentAdmin.mypage(adminDTO.getName(), adminDTO.getEmail(), adminDTO.getPhone(), new Date());
        adminService.modifyAdmin(currentAdmin);

        map.put("code", 0);
        map.put("message", "수정 완료했습니다.");
        return map;
    }

    @PostMapping(value = "/signup")
    @ResponseBody
    public Map<String, Object> registerAdmin(AdminDTO adminDTO, Errors errors)
    {
        Map<String, Object> map = new HashMap<String, Object>();

        // @Valid : 어노테이션들의 조건을 만족하는지 확인
        if (errors.hasErrors()) {
            log.info(errors.getAllErrors().toString());
            map.put("code", 998);
            map.put("message", "오류가 발생했습니다.");
        }

        Admin admin = new Admin(adminDTO);

        // 비밀번호 암호화
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        admin.signup(passwordEncoder.encode(admin.getPassword()), 0, 1);

        adminService.registerAdmin(admin);

        map.put("code", 0);
        map.put("message", "회원 가입을 완료했습니다.");
        return map;
    }

    // 아이디 체크
    @PostMapping("/check_adminId")
    @ResponseBody
    public boolean checkAdminID(AdminDTO adminDTO){
        boolean result = adminService.checkAdminId(adminDTO.getAdminId());
        return result;
    }

    // 아이디 찾기
    @PostMapping("/search_adminId")
    @ResponseBody
    public String searchAdminID(AdminDTO adminDTO)
    {
        String adminID = adminService.findAdminId(adminDTO);
        return adminID;
    }
}
