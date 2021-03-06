package com.h2.dogrooming.reservation;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.h2.dogrooming.admin.Admin;
import com.h2.dogrooming.product.Product;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Getter
@Setter
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class)
public class Reservation implements Serializable {

    @Id
    @GeneratedValue
    private Integer reservationId;

    // 예약 진행 상태 변경 (1: 진행 전, 2: 진행 중, 3: 진행 완료)
    @Column(nullable = false)
    private Integer reservationState;

    // 예약 상태 (0: 정상, 1: 취소)
    @Column(nullable = false)
    private Integer useState;

    @Column(nullable = false)
    @JsonFormat(pattern = "yyyy년 MM월 dd일 HH시 mm분")
    private Date reservationDate;

    @Column(length = 8, nullable = false)
    private String reservationYmd;

    @Column(length = 10)
    private String postcode;

    @Column(length = 256)
    private String address;

    @Column(length = 256)
    private String addressDtl;

    @Column(length = 8, nullable = false)
    private String ymd;;

    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    private Date registerDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date updateDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sellerNo")
    private Admin seller_Admin;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "buyerNo")
    private Admin buyer_Admin;

    @ManyToOne
    @JoinColumn(name = "productId")
    private Product product;

    public Reservation(){}

    @Builder
    public Reservation(Integer reservationId, Integer reservationState, Integer useState, Date reservationDate, String reservationYmd, String postcode, String address, String addressDtl, String ymd, Date registerDate, Date updateDate, Admin seller_Admin, Admin buyer_Admin, Product product) {
        this.reservationId = reservationId;
        this.reservationState = reservationState;
        this.useState = useState;
        this.reservationDate = reservationDate;
        this.reservationYmd = reservationYmd;
        this.postcode = postcode;
        this.address = address;
        this.addressDtl = addressDtl;
        this.ymd = ymd;
        this.registerDate = registerDate;
        this.updateDate = updateDate;
        this.seller_Admin = seller_Admin;
        this.buyer_Admin = buyer_Admin;
        this.product = product;
    }

    // 예약 상태 변경
    public void modifyReservationState(Integer reservationState, Integer useState, Date reservationDate){
        this.reservationState = reservationState;
        this.useState = useState;
        this.reservationDate = reservationDate;
    }
}
