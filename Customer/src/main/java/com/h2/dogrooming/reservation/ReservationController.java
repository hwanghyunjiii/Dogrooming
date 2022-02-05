package com.h2.dogrooming.reservation;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.h2.dogrooming.admin.Admin;
import com.h2.dogrooming.admin.AdminService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.DecimalFormat;
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

    ObjectMapper mapper;

    @RequestMapping(value = "/reservation")
    public String goReservationList( Authentication authentication
                                    ,Model model
                                    ,@RequestParam(name = "dateFrom", required = false) String dateFrom
                                    ,@RequestParam(name = "dateTo", required = false) String dateTo
                                    ,@PageableDefault(page = 0, size = 5, sort = "reservationDate", direction = Sort.Direction.DESC) Pageable pageable) throws ParseException {
        // 로그인 여부 확인
        if(authentication == null)
        {
            return "redirect:/login";
        }

        // 현재 로그인된 사용사 정보 조회
        UserDetails userDetails = (UserDetails)authentication.getPrincipal();
        Admin currentAdmin = adminService.getAdmin(userDetails.getUsername());

        if(dateFrom == null || dateFrom == "") {
            // 일년전 날짜 구하기
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.YEAR , -1);
            dateFrom = new java.text.SimpleDateFormat("yyyy-MM-dd").format(calendar.getTime());
        }

        if(dateTo == null || dateTo == "") {
            // 현재 날짜 구하기
            Date today = new Date();
            SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
            dateTo = date.format(today);
        }

        // 로그인된 사용자의 예약 정보 조회
        Page<Reservation> reservationList = reservationService.getReservationList(currentAdmin.getAdminId(), dateFrom.replaceAll("-", ""), dateTo.replaceAll("-", ""), pageable);

        // 로그인된 사용자의 예약 정보 금액 조회
        Integer sumAmount = reservationService.getReservationSummary(currentAdmin.getAdminId(), dateFrom.replaceAll("-", ""), dateTo.replaceAll("-", ""));
        DecimalFormat decimalFormat = new DecimalFormat("###,###");

        //add data to view
        model.addAttribute("reservationList", reservationList);
        model.addAttribute("dateFrom", dateFrom);
        model.addAttribute("dateTo", dateTo);
        model.addAttribute("sumAmount", sumAmount != null ? decimalFormat.format(sumAmount) : 0);
        model.addAttribute("adminName", currentAdmin.getName());

        return "page/reservation";
    }

    // 예약 상세내역 조회
    @PostMapping("/reservationDetail")
    @ResponseBody
    public Reservation getReservationDetail(@RequestParam("reservationId") Integer reservationId){
        Reservation reservation = reservationService.getReservationDetail(reservationId);
        return reservation;
    }

    // 취소 처리
    @PostMapping("/reservationCancel")
    @ResponseBody
    public Map<String, Object> cancelReservation(@RequestParam("reservationId") Integer reservationId) throws Exception{
        Map<String, Object> map = new HashMap<String, Object>();

        try {
            reservationService.cancelReservation(reservationId);
            map.put("code", 0);
            map.put("message", "예약을 취소했습니다.");
        }
        catch (Exception e){
            map.put("code", 0);
            map.put("message", e.getMessage());
        }
        return map;
    }
}
