package com.backdev.happy.wblserver.global.config.security;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@AllArgsConstructor
@Getter
public enum UserRole {
    USER("ROLE_USER"),
    ADMIN("ROLE_ADMIN"),
    CUSTOMER("ROLE_CUSTOMER"),
    SELLER("ROLE_SELLER");

    private String value;

    public static UserRole findByString(String val) {
        return Arrays.stream(values())
                .filter(v -> val.equals(v.value))
                .findFirst()
                .orElseThrow(() -> new RuntimeException(val + " is not supported"));
    }

}
