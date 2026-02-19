package com.mini.filter;

import com.mini.businessuser.constant.UserType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginUser implements Serializable {
    private Long userId;
    private UserType userType; // MEMBER / MERCHANT / ADMINISTRATOR
}
