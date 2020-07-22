package com.example.Quoter.domain;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    USER,
    ADMIN;

    @Override
    public String getAuthority() {
        // the name() function represents a String value of USER in the enum Role.
        return name();
    }
}