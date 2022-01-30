package com.h2.dogrooming.designer;

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

    @Column(length = 40, nullable = false)
    public String adminId;

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

    public DesignerAddress(Integer designerAddrId, String adminId, String address, String postcode, Integer useState, Date registerDate, Date updateDate){
        this.designerAddrId = designerAddrId;
        this.adminId = adminId;
        this.address = address;
        this.postcode = postcode;
        this.useState = useState;
        this.registerDate = registerDate;
        this.updateDate = updateDate;
    }
}
