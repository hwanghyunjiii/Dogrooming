package com.h2.dogrooming.reservation;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
public class ReservationService {

    @Autowired
    ReservationRepository reservationRepository;

    // 예약 리스트 조회
    public Page<Reservation> getReservationList(String buyerId, Pageable pageable) {
        return reservationRepository.findAllByBuyerID(buyerId, pageable);
    }

}
