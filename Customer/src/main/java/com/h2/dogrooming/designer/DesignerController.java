package com.h2.dogrooming.designer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
public class DesignerController {
    @RequestMapping(value = "/designer_search")
    public String goLogin(Authentication authentication)
    {
        return "page/designer_search";
    }
}
