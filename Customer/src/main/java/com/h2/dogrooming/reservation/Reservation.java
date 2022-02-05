package com.h2.dogrooming.reservation;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.h2.dogrooming.admin.Admin;
import com.h2.dogrooming.designer.Designer;
import com.h2.dogrooming.product.Product;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

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
    private Integer reservationState;

    @Column(nullable = false)
    private Integer useState;

    @Column(nullable = false)
    @JsonFormat(pattern = "yyyy년 MM월 dd일 HH시 mm분")
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sellerNo")
    private Admin seller_Admin;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "buyerNo")
    @JsonIgnore
    private Admin buyer_Admin;

    @ManyToOne
    @JoinColumn(name = "productId")
    private Product product;

    public Reservation(){}

    @Builder
    public Reservation(Integer reservationId, Integer reservationState, Integer useState, Date reservationDate, String postcode, String address, String addressDtl, String ymd, Date registerDate, Date updateDate, Admin seller_Admin, Admin buyer_Admin, Product product) {
        this.reservationId = reservationId;
        this.reservationState = reservationState;
        this.useState = useState;
        this.reservationDate = reservationDate;
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
}
