package com.h2.dogrooming.admin;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.Date;

@Entity
@Getter
@Setter
public class Admin {

    @Id
    @GeneratedValue
    private long adminNo; // id

    @NotBlank(message = "아이디를 입력해주세요.")
    @Column(length = 40, unique = true, nullable = false)
    private String adminID; // 아이디

    @NotBlank(message = "비밀번호를 입력해주세요.")
    @Column(length = 256, nullable = false)
    private String password; // 비밀번호

    @Column(length = 1, nullable = false)
    private Integer type; // 타입 (1: 디자이너 2:유저)

    @NotBlank(message = "이름을 입력해주세요.")
    @Column(length = 50, nullable = false)
    private String name; // 이름

    @Email
    @NotBlank(message = "이메일을 입력해주세요.")
    @Column(length = 50, nullable = false)
    private String email; // 이메일

    @NotBlank(message = "번호를 입력해주세요.")
    @Pattern(regexp = "(01[016789])(\\d{3,4})(\\d{4})", message = "올바른 번호를 입력해주세요.")
    @Column(length = 20, nullable = false)
    private String phone;   // 번호

    @Column(length = 1, nullable = false)
    private Integer useState;   // 상태 (0: 정상  1: 비정상)

    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    private Date registerDate; // 등록날짜

    @Temporal(TemporalType.TIMESTAMP)
    private Date updateDate; //수정날짜

    public Admin(long adminNo, String adminID, String password, Integer type, String name, String email, String phone, Integer useState, Date registerDate, Date updateDate) {
        this.adminNo = adminNo;
        this.adminID = adminID;
        this.password = password;
        this.type = type;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.useState = useState;
        this.registerDate = registerDate;
        this.updateDate = updateDate;
    }

    public Admin() {

    }

}