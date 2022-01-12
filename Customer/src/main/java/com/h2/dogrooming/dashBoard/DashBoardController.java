package com.h2.dogrooming.dashBoard;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class DashBoardController {

    @GetMapping(value = "/")
    public String DashBoard(){
        return "page/dashboard";
    }

    @ResponseBody
    @GetMapping("/dash-value")
    public String DashBoardValueTest()
    {
        return "test haram!!!";
    }
}
