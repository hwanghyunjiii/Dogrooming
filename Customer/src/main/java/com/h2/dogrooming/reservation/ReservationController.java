package com.h2.dogrooming.reservation;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.h2.dogrooming.admin.Admin;
import com.h2.dogrooming.admin.AdminService;
import lombok.Data;
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
    public ReservationDto getReservationDetail(@RequestParam("reservationId") Integer reservationId){
        Reservation reservation = reservationService.getReservationDetail(reservationId);
        ReservationDto reservationDto = new ReservationDto(reservation);

        return reservationDto;
    }

    // 취소 처리
    @PostMapping("/reservationCancel")
    @ResponseBody
    public Map<String, Object> cancelReservation(@RequestParam("reservationId") Integer reservationId){
        Map<String, Object> map = new HashMap<String, Object>();

        try {
            // 예약 정보 조회
            Reservation reservation = reservationService.getReservationDetail(reservationId);

            // 진행 중이 아니거나, 취소 상태인 경우
            if(reservation.getReservationState() != 1 || reservation.getUseState() == 2)
            {
                map.put("code", 0);
                map.put("message", "취소 처리를 할 수 없습니다. 상태를 확인해주세요.");
                return map;
            }

            // 취소 상태로 업데이트
            reservationService.modifyReservation(reservation, 1, reservation.getReservationState());

            map.put("code", 0);
            map.put("message", "예약을 취소했습니다.");
        }
        catch (Exception e){
            map.put("code", 0);
            map.put("message", e.getMessage());
        }
        return map;
    }

    // 예약 완료 처리
    @PostMapping("/reservationComplete")
    @ResponseBody
    public Map<String, Object> reservationComplete(@RequestParam("reservationId") Integer reservationId){
        Map<String, Object> map = new HashMap<String, Object>();

        try {
            // 예약 정보 조회
            Reservation reservation = reservationService.getReservationDetail(reservationId);

            // 진행 중이 아니거나, 취소 상태인 경우
            if(reservation.getReservationState() != 2 || reservation.getUseState() == 1)
            {
                map.put("code", 0);
                map.put("message", "예약 완료 처리를 할 수 없습니다. 상태를 확인해주세요.");
                return map;
            }

            // 진행 상황 업데이트
            reservationService.modifyReservation(reservation, reservation.getUseState(),3);

            map.put("code", 0);
            map.put("message", "완료 처리하였습니다.");
        }
        catch (Exception e){
            map.put("code", 0);
            map.put("message", e.getMessage());
        }

        return map;
    }


    @Data
    static class ReservationDto {
        private String designerName;
        private Integer useState;
        private Integer reservationState;
        private Double amount;

        @JsonFormat(pattern = "yyyy년 MM월 dd일 HH시 mm분")
        private Date reservationDate;
        private String productName;
        private String postcode;
        private String address;
        private String addressDtl;

        public ReservationDto (Reservation reservation){
            designerName = reservation.getSeller_Admin().getDesigner().getName();
            useState = reservation.getUseState();
            reservationState = reservation.getReservationState();
            amount = reservation.getProduct().getAmount();
            reservationDate = reservation.getReservationDate();
            productName = reservation.getProduct().getName();
            postcode = reservation.getPostcode();
            address = reservation.getAddress();
            addressDtl = reservation.getAddressDtl();
        }
    }
}
