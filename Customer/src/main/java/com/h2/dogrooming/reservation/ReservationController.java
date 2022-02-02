package com.h2.dogrooming.reservation;

import com.h2.dogrooming.admin.Admin;
import com.h2.dogrooming.admin.AdminService;
import lombok.extern.slf4j.Slf4j;
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
import org.springframework.web.bind.annotation.RequestParam;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Slf4j
@Controller
public class ReservationController {

    @Autowired
    ReservationService reservationService;

    @Autowired
    AdminService adminService;

    @RequestMapping(value = "/reservation")
    public String goReservationList( Authentication authentication
                                    ,Model model
                                    ,@RequestParam(name = "dateFrom", required = false) String dateFrom
                                    ,@RequestParam(name = "dateTo", required = false) String dateTo
                                    ,@PageableDefault(page = 0, size = 5, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) throws ParseException {
        // 로그인 여부 확인
        if(authentication == null)
        {
            return "redirect:/login";
        }

        // 현재 로그인된 사용사 정보 조회
        UserDetails userDetails = (UserDetails)authentication.getPrincipal();
        Admin currentAdmin = adminService.getAdmin(userDetails.getUsername());

        log.info("dateFrom: " + dateFrom);
        log.info("dateTo: " + dateTo);

        if(dateFrom == null || dateFrom == "") {
            // 일년전 날짜 구하기
            Calendar mon = Calendar.getInstance();
            mon.add(Calendar.YEAR , -1);
            dateFrom = new java.text.SimpleDateFormat("yyyy-MM-dd").format(mon.getTime());
        }

        if(dateTo == null || dateTo == "") {
            // 현재 날짜 구하기
            Date today = new Date();
            SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
            dateTo = date.format(today);
        }

        SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd");

        log.info("dateFrom: " + transFormat.parse(dateFrom));
        log.info("dateTo: " + transFormat.parse(dateTo));

        // 로그인된 사용자의 예약 정보 조회
        Page<Reservation> reservationList = reservationService.getReservationList(currentAdmin.getAdminId(), transFormat.parse(dateFrom), transFormat.parse(dateTo), pageable);

        //add data to view
        model.addAttribute("reservationList", reservationList);
        model.addAttribute("dateFrom", dateFrom);
        model.addAttribute("dateTo", dateTo);

        return "page/reservation";
    }
}
