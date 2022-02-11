package com.h2.dogrooming.reservation;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Getter
@NoArgsConstructor
public class ReservationDto {
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