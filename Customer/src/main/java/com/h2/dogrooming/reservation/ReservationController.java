package com.h2.dogrooming.reservation;

import com.h2.dogrooming.admin.Admin;
import com.h2.dogrooming.admin.AdminService;
import com.sun.org.apache.xpath.internal.operations.Mod;
import lombok.extern.slf4j.Slf4j;
import net.bytebuddy.TypeCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Slf4j
@Controller
public class ReservationController {

    @Autowired
    ReservationService reservationService;

    @Autowired
    AdminService adminService;

    @GetMapping(value = "/reservation")
    public String goReservationList(Authentication authentication, Model model
            ,@PageableDefault(page = 0, size = 5, sort = "id", direction = Sort.Direction.DESC) Pageable pageable)
    {
        // 로그인 여부 확인
        if(authentication == null)
        {
            return "redirect:/login";
        }

        // 현재 로그인된 사용사 정보 조회
        UserDetails userDetails = (UserDetails)authentication.getPrincipal();
        Admin currentAdmin = adminService.getAdmin(userDetails.getUsername());

        // 로그인된 사용자의 예약 정보 조회
        Page<Reservation> reservationList = reservationService.getReservationList(currentAdmin.getAdminID(), pageable);

        //add data to view
        model.addAttribute("reservationList", reservationList);

        return "page/reservation";
    }
}
