package com.h2.dogrooming.review;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
public class Review {

    @Id
    @GeneratedValue
    private long reviewId;

    @Column(nullable = false)
    private long reservationId;

    @Column(length = 256)
    private String content;

    @Column(length = 256)
    private String file;

    @Column(length = 1)
    private Integer useState;

    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    private Date registerDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date updateDate;

    public Review(){}

    public Review(long reviewId, long reservationId, String content, String file, Integer useState, Date registerDate, Date updateDate){
        this.reviewId = reviewId;
        this.reservationId = reservationId;
        this.content = content;
        this.file = file;
        this.useState = useState;
        this.registerDate = registerDate;
        this.updateDate = updateDate;
    }
}
