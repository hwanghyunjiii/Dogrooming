package com.h2.dogrooming.admin;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
public class Admin {

    @Id
    @Column(length = 40, unique = true, nullable = false)
    private String AdminID; // 아이디

    @Column(length = 256, nullable = false)
    private String Password; // 비밀번호

    @Column(length = 1, nullable = false)
    private Integer Type; // 타입

    @Column(length = 50, nullable = false)
    private String Name; // 이름

    @Column(length = 50, nullable = false)
    private String Email; // 이메일

    @Column(length = 20, nullable = false)
    private String Phone;

    @Column(length = 1, nullable = false)
    private Integer UseState;

    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    private Date registerDate; //등록날짜

    @Temporal(TemporalType.TIMESTAMP)
    private Date updateDate; //수정날짜

    @Builder
    public Admin(String adminID, String password, Integer type, String email, String phone, Integer useState, Date registerDate, Date updateDate) {
        AdminID = adminID;
        Password = password;
        Type = type;
        Email = email;
        Phone = phone;
        UseState = useState;
        this.registerDate = registerDate;
        this.updateDate = updateDate;
    }

    public Admin() {

    }
}