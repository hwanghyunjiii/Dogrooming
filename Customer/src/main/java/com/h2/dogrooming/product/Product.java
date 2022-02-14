package com.h2.dogrooming.product;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
public class Product {

    @Id
    @GeneratedValue
    private long productId;

    @Column(length = 40, nullable = false)
    private String adminId;

    @Column(length = 10, nullable = false)
    private String name;

    @Column(length = 256)
    private String description;

    @Column(length = 1, nullable = false)
    private Integer dogType;

    @Column(nullable = false)
    private Double amount;

    @Column(length = 1, nullable = false)
    private Integer useState;

    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    private Date registerDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date updateDate;

    public Product(){}

    public Product(Integer productId, String adminId, String name, String description, Integer dogType, Double amount, Integer useState, Date registerDate, Date updateDate){
        this.productId = productId;
        this.adminId = adminId;
        this.name = name;
        this.description = description;
        this.dogType = dogType;
        this.amount = amount;
        this.useState = useState;
        this.registerDate = registerDate;
        this.updateDate = updateDate;
    }

}
