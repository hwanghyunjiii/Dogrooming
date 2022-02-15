package com.h2.dogrooming.admin;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.h2.dogrooming.designer.Designer;
import com.h2.dogrooming.reservation.Reservation;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class)
public class Admin implements Serializable {

    @Id
    @GeneratedValue
    private Integer adminNo;

    @NotBlank(message = "아이디를 입력해주세요.")
    @Column(length = 40, unique = true, nullable = false)
    private String adminId; // 아이디

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

    @Builder
    public Admin(Integer adminNo, String adminId, String password, Integer type, String name, String email, String phone, Integer useState, Date registerDate, Date updateDate, Designer designer) {
        this.adminNo = adminNo;
        this.adminId = adminId;
        this.password = password;
        this.type = type;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.useState = useState;
        this.registerDate = registerDate;
        this.updateDate = updateDate;
        this.designer = designer;
    }

    public Admin() {

    }

    // 회원가입 비번, 상태, 타입 설정
    public void signup(String password, Integer useState, Integer type){
        this.password = password;
        this.useState = useState;
        this.type = type;
    }

    // 개인 정보 수정
    public void mypage(String name, String email, String phone, Date updateDate){
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.updateDate = updateDate;
    }

    // DTO
    public Admin(AdminDTO adminDTO){
        adminId = adminDTO.getAdminId();
        password = adminDTO.getPassword();
        name = adminDTO.getName();
        email = adminDTO.getEmail();
        phone = adminDTO.getPhone();
    }

    @OneToOne (mappedBy = "admin", fetch = FetchType.LAZY)
    private Designer designer;

    @OneToMany(mappedBy = "buyer_Admin")
    private List<Reservation> buyer_reservationList = new ArrayList<>();

    @OneToMany(mappedBy = "seller_Admin")
    private List<Reservation> seller_reservationList = new ArrayList<>();
}