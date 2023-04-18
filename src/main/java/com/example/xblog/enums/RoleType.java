package com.example.xblog.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.Getter;

@Getter
public enum RoleType {
    ROLE_ADMIN(1),
    ROLE_USER(0);

    @EnumValue
    private Integer code;

    RoleType(Integer code) {
        this.code = code;
    }
    @Override
    public String toString(){
        return this.code.toString();
    }
}