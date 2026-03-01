package com.mini.points.service.impl;

import com.mini.points.constant.RedisConstant;
import com.mini.points.domain.vo.SignResultVO;
import com.mini.points.service.SignRecordService;
import com.mini.utils.SecurityUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Service
public class SignRecordServiceImpl implements SignRecordService {
    @Override
    public SignResultVO addSignRecords() {
        // 1.获取当前用户
        Long userId = SecurityUtils.getUserId();

        // 2.签到
        // 2.1获取当前日期作为key
        LocalDate now = LocalDate.now();
        String format = now.format(DateTimeFormatter.ofPattern(":yyyyMM"));
        String key = RedisConstant.SIGN_RECORD_KEY_PREFIX + userId + format;
        // 2.2使用bitmap尝试进行签到

        return null;
    }
}
