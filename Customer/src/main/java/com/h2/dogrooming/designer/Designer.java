package com.h2.dogrooming.designer;

import com.h2.dogrooming.admin.Admin;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
public class Designer{

    @Id
    @Column(nullable = false)
    @GeneratedValue
    private Integer designerId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "adminNo")
    private Admin admin; // 아이디

    @Column(length = 256)
    private String profile; // 프로필

    @Column(length = 100)
    private String title; // 제목

    @Column(length = 256)
    private String content; // 내용

    @Column(length = 50)
    private String name; // 이름

    @Column(length = 1, nullable = false)
    private Integer useState; // 상태 (0: 정상  1: 비정상)

    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    private Date registerDate; // 등록일

    @Temporal(TemporalType.TIMESTAMP)
    private Date updateDate; //수정일

    public Designer(){}

    public Designer(Integer designerId, Admin admin, String profile, String title, String content, String name, Integer useState, Date registerDate, Date updateDate) {
        this.designerId = designerId;
        this.admin = admin;
        this.profile = profile;
        this.title = title;
        this.content = content;
        this.name = name;
        this.useState = useState;
        this.registerDate = registerDate;
        this.updateDate = updateDate;
    }
}
