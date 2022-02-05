package com.h2.dogrooming.reservation;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@AllArgsConstructor
public class ReservationService {

    @Autowired
    ReservationRepository reservationRepository;

    // 예약 리스트 조회
    public Page<Reservation> getReservationList(String buyerId, Date dateFrom, Date dateTo, Pageable pageable) {
        return reservationRepository.findAllByBuyerId(buyerId, dateFrom, dateTo, pageable);
    }

    // 에약 금액 조회
    public Integer getReservationSummary(String buyerId, Date dateFrom, Date dateTo) {
        return reservationRepository.findAllByBuyerIdSummary(buyerId, dateFrom, dateTo);
    }

    // 예약 상세 조회
    public Reservation getReservationDetail(Integer reservationId){
        return reservationRepository.findReservationByReservationId(reservationId);
    }
}
