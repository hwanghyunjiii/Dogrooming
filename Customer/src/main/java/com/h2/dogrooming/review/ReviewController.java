package com.h2.dogrooming.review;

import com.h2.dogrooming.common.FileDto;
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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.File;
import java.util.Arrays;
import java.util.UUID;

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


    @PostMapping("/registerReview")
    public String registerReview(@RequestParam("file") MultipartFile file,
                                 @Valid @ModelAttribute Review review) {

        // 파일 업로드
        FileDto dto = new FileDto(UUID.randomUUID().toString(),
                                  file.getOriginalFilename(), file.getContentType() );
        File newFileName = new File(dto.getFileName());

        // 확장자 유효성 체크
        String[] EXTENSION = { "jpg", "png", "jpeg", "gif"};
        if(!Arrays.asList(EXTENSION).contains(file.getContentType())){
            // 오류 발생
            return "redirect:/";
        }

        try {
            file.transferTo(newFileName);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 파일 이름 저장
        review.setFile(dto.getFileName());
        review.setUseState(0);

        // 리뷰 등록
        reviewService.registerReview(review);
        return "redirect:/";
    }
}
