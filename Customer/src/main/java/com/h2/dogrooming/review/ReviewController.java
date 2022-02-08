package com.h2.dogrooming.review;

import com.h2.dogrooming.admin.Admin;
import com.h2.dogrooming.admin.AdminService;
import com.h2.dogrooming.reservation.Reservation;
import com.h2.dogrooming.reservation.ReservationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Slf4j
@Controller
public class ReviewController {

    @Autowired
    ReviewService reviewService;

    @Autowired
    AdminService adminService;

    @Autowired
    ReservationService reservationService;

    // 리뷰 등록 페이지
    @RequestMapping(value = "/review")
    public String goRegisterReview(Authentication authentication, Model model, @RequestParam(name = "review_reservationId") Integer reservationId){

        // 로그인 여부 확인
        if(authentication == null)
        {
            return "redirect:/login";
        }

        // 에약 정보 유효성 검사
        if(reservationId == null)
        {
            log.info("예약 정보가 없습니다.");
            return "redirect:/reservation";
        }

        // 현재 로그인된 사용사 정보 조회
        UserDetails userDetails = (UserDetails)authentication.getPrincipal();
        Admin currentAdmin = adminService.getAdmin(userDetails.getUsername());

        // 예약 정보 조회
        Reservation reservation = reservationService.getReservationDetail(reservationId);

        // 예약한 유저와 로그인한 유저가 같은지 확인
        if(!reservation.getBuyer_Admin().getAdminId().equals(currentAdmin.getAdminId()))
        {
             log.info("유저가 다릅니다.");
            return "redirect:/reservation";
        }

        //add data to view
        model.addAttribute("reservation", reservation);

        return "page/review";
    }


    public
}
