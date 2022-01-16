package com.h2.dogrooming.admin;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.GetMapping;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Getter
@Setter
@Entity
public class Admin {

    @Id
    @Column(length = 40, unique = true, nullable = false)
    public String adminId;

    @Column
    public String password;

    @Column(length = 256/*, nullable = false*/)
    public String name;

    @Column(length = 1/*, nullable = false*/)
    public Integer type;

    @Column
    public String email;

    @Column
    public String phone;

    @Column
    public String state;

    @Column
    public Date registerDate;

    @Column
    public Date updateDate;

    public Admin(){}

    @Builder
    public Admin(String adminId, String password, Integer type, String email, Date registerDate, Date updateDate) {
        this.adminId = adminId;
        this.password = password;
        this.type = type;
        this.email = email;
        this.registerDate = registerDate;
        this.updateDate = updateDate;
    }
}
