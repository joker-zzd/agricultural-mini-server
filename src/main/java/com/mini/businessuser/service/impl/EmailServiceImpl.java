package com.mini.businessuser.service.impl;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class EmailServiceImpl {
    @Resource
    private JavaMailSender javaMailSender;

    @Async
    public void sendEmailAsync(SimpleMailMessage message) {
        try {
            javaMailSender.send(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
