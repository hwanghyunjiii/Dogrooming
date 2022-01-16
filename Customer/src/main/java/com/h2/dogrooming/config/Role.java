package com.h2.dogrooming.config;


import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Role {
    CUSTOMER("ROLE_CUSTOMER");

    private String value;
}