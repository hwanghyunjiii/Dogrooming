package com.h2.dogrooming.admin;


import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Role {
    CUSTOMER("ROLE_CUSTOMER");

    private String value;
}