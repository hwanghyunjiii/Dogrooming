package com.h2.dogrooming.reservation;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
public class Reservation {

    @Id
    @GeneratedValue
    private Integer reservationId;

    @Column(nullable = false)
    private Integer productId;

    @Column(length = 40, nullable = false)
    private String buyerId;

    @Column(length = 40, nullable = false)
    private String sellerId;

    @Column(length = 10, nullable = false)
    private String name;

    @Column(length = 20, nullable = false)
    private String phone;

    @Column(nullable = false)
    private Double amount;

    @Column(nullable = false)
    private Integer reservationState;

    @Column(nullable = false)
    private Integer useState;

    @Column(nullable = false)
    private Date reservationDate;

    @Column(length = 10)
    private String postcode;

    @Column(length = 256)
    private String address;

    @Column(length = 256)
    private String addressDtl;

    @Column(length = 8, nullable = false)
    private String ymd;

    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    private Date registerDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date updateDate;

    public Reservation(){}

    public Reservation(Integer reservationId, Integer productId, String buyerId, String sellerId, String name, String phone, Double amount, Integer reservationState, Integer useState, Date reservationDate, String postcode, String address, String addressDtl, String ymd, Date registerDate, Date updateDate){
        this.reservationId = reservationId;
        this.productId = productId;
        this.buyerId = buyerId;
        this.sellerId = sellerId;
        this.name = name;
        this.phone = phone;
        this.amount = amount;
        this.reservationState = reservationState;
        this.useState = useState;
        this.reservationDate = reservationDate;
        this.postcode = postcode;
        this.address = address;
        this.addressDtl = addressDtl;
        this.ymd = ymd;
        this.registerDate = registerDate;
        this.updateDate = updateDate;
    }
}
