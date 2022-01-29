package com.h2.dogrooming.reservation;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
public class ReservationController {

    @RequestMapping(value = "/reservation")
    public String goReservationList(Authentication authentication)
    {
        return "page/reservation";
    }
}
