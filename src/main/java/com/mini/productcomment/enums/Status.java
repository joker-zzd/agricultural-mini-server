package com.mini.productcomment.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.mini.common.constants.BaseEnum;
import lombok.Getter;

@Getter
public enum Status implements BaseEnum {
    UNVIEWED(0, "未查看"),
    VIEWED(1, "已查看");

    @JsonValue
    @EnumValue
    final int value;
    final String desc;

    Status(int value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    public static Status of(Integer value) {
        for (Status item : values()) {
            if (item.value == value) {
                return item;
            }
        }
        return null;
    }
}
