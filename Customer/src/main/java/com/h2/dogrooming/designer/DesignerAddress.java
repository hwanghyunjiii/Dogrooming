package com.h2.dogrooming.designer;

import com.h2.dogrooming.admin.Admin;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
public class DesignerAddress {

    @Id
    @GeneratedValue
    public Integer designerAddrId;

    @ManyToOne
    @JoinColumn(name = "adminId")
    public Admin admin;

    @Column(length = 256)
    public String address;

    @Column(length = 10)
    public String postcode;

    @Column(nullable = false)
    public Integer useState;

    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    public Date registerDate;

    @Temporal(TemporalType.TIMESTAMP)
    public Date updateDate;

    public DesignerAddress(){}

    @Builder
    public DesignerAddress(Integer designerAddrId, Admin admin, String address, String postcode, Integer useState, Date registerDate, Date updateDate) {
        this.designerAddrId = designerAddrId;
        this.admin = admin;
        this.address = address;
        this.postcode = postcode;
        this.useState = useState;
        this.registerDate = registerDate;
        this.updateDate = updateDate;
    }

}
