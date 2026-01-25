package com.mini.resultvo;

import io.swagger.v3.oas.annotations.media.Schema;

public interface StatusCode {
    @Schema(name = "SUCCESS",description = "状态码为0表示执行成功")
    Integer SUCCESS = 0;;
    @Schema(name = "FAIL",description = "状态码为500为执行失败")
    Integer FAIL = 500;
}
