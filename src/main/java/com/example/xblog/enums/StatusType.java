package com.example.xblog.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

@Getter
public enum StatusType {
    NO(0, "禁用"),
    YES(1, "启用");

    StatusType(Integer key, String name) {
        this.key = key;
        this.name = name;
    }

    @EnumValue //标记数据库存的值
    private final Integer key;

    @JsonValue // 作用：写在方法上，告诉Jackson，Jackson不应该尝试序列化对象本身，而应在对象上调用将对象序列化为JSON字符串的方法
    private final String name;

    @Override
    public String toString() {
        return this.name;
    }
}
