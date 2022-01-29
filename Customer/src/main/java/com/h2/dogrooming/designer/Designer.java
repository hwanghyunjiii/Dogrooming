package com.h2.dogrooming.designer;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
public class Designer {

    @Id
    @Column(nullable = false)
    private Integer adminNo; // 번호

    @Column(length = 40, unique = true, nullable = false)
    private String adminId; // 아이디

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

    public Designer(String adminId, String profile, String title, String content, String name, Integer useState, Date registerDate, Date updateDate){
        this.adminId = adminId;
        this.profile = profile;
        this.title = title;
        this.content = content;
        this.name = name;
        this.useState = useState;
        this.registerDate = registerDate;
        this.updateDate = updateDate;
    }
}
