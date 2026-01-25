package com.mini.filter;

import com.mini.businessuser.constant.Type;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginUser implements Serializable {
    private Long userId;
    private Type type; // MEMBER / MERCHANT / ADMINISTRATOR
}
