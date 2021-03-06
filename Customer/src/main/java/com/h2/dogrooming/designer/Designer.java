package com.h2.dogrooming.designer;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.h2.dogrooming.admin.Admin;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
public class Designer {

    @Id
    @Column(nullable = false)
    @GeneratedValue
    private long designerId;

    @OneToOne
    @JoinColumn(name = "adminNo")
    @JsonBackReference
    private Admin admin; // 아이디

    @Column(length = 256)
    private String profile; // 프로필

    @Column(length = 100)
    private String title; // 제목

    @Column(length = 256)
    private String content; // 내용

    @Column(length = 50)
    private String name; // 이름

    @Column(length = 256)
    private String region; // 서비스지역

    @Column(length = 1, nullable = false)
    private Integer useState; // 상태 (0: 정상  1: 비정상)

    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    private Date registerDate; // 등록일

    @Temporal(TemporalType.TIMESTAMP)
    private Date updateDate; //수정일

    public Designer(){}

    @Builder
    public Designer(long designerId, Admin admin, String profile, String title, String content, String name, String region, Integer useState, Date registerDate, Date updateDate) {
        this.designerId = designerId;
        this.admin = admin;
        this.profile = profile;
        this.title = title;
        this.content = content;
        this.name = name;
        this.region = region;
        this.useState = useState;
        this.registerDate = registerDate;
        this.updateDate = updateDate;
    }

    public Designer(DesignerDTO designerDTO){
        designerId = designerDTO.getDesignerId();
        profile = designerDTO.getProfile();
        title = designerDTO.getTitle();
        content = designerDTO.getContent();
        name = designerDTO.getName();
        useState = designerDTO.getUseState();
    }
}