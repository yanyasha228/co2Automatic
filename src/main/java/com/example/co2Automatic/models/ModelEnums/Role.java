package com.example.co2Automatic.models.ModelEnums;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {

    SALES_MANAGER, GUNSMITH, ADMIN , SUPER_ADMIN;

    @Override
    public String getAuthority() {
        return name();
    }


}
