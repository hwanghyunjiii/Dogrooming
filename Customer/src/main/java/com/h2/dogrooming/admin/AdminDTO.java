package com.h2.dogrooming.admin;

import lombok.*;

import java.io.Serializable;

@Data
@Getter
@NoArgsConstructor
public class AdminDTO implements Serializable {
    private String adminId;     // 아이디
    private String password;    // 비밀번호
    private String name;        // 이름
    private String email;       // 이메일
    private String phone;       // 번호

    public AdminDTO(String adminId, String password, String name, String email, String phone) {
        this.adminId = adminId;
        this.password = password;
        this.name = name;
        this.email = email;
        this.phone = phone;
    }

    public AdminDTO(Admin admin){
        adminId = admin.getAdminId();
        password = admin.getPassword();
        name = admin.getName();
        email = admin.getEmail();
        phone = admin.getPhone();
    }
}