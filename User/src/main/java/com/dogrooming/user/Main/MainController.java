package com.dogrooming.user.Main;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user/")
public class MainController {

    @RequestMapping(value = "/main")
    public String Login(){
        return "login.html";
    }
}
