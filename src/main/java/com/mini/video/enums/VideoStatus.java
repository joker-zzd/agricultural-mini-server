package com.mini.video.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.mini.common.constants.BaseEnum;
import lombok.Getter;

@Getter
public enum VideoStatus implements BaseEnum {
    OFFLINE(0, "下线"),
    ONLINE(1, "上线");

    @JsonValue
    @EnumValue
    int value;
    String desc;

    VideoStatus(int value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    public static VideoStatus of(Integer value) {
        for (VideoStatus item : values()) {
            if (item.value == value) {
                return item;
            }
        }
        return null;
    }
}
