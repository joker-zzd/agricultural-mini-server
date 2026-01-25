package com.mini.businessuser.controller;

import com.mini.businessuser.domain.dto.UpdatePasswordDTO;
import com.mini.businessuser.domain.dto.UserEmailRegisterDTO;
import com.mini.businessuser.domain.dto.UserLoginDTO;
import com.mini.businessuser.service.UserService;
import com.mini.resultvo.ResultVO;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    ResultVO<Map<String, Object>> login(@RequestBody UserLoginDTO userLoginDTO) {
        return userService.login(userLoginDTO);
    }

    @PutMapping("/updateNickname")
    public ResultVO<Void> updateNickname(@RequestParam String nickname){
        return this.userService.updateNickname(nickname);
    }
    @PutMapping("/updateSex")
    public ResultVO<Void> updateSex(@RequestParam String sex){
        return this.userService.updateSex(sex);
    }
    @PutMapping("/updateAvatar")
    public ResultVO<Void> updateAvatar(@RequestParam String avatar){
        return this.userService.updateAvatar(avatar);
    }

    @PostMapping("/updatePassword")
    public ResultVO<Void> updatePassword(@RequestBody @Valid UpdatePasswordDTO dto) {
        return userService.updatePassword(dto);
    }
    @PostMapping("/sendEmailCode")
    public ResultVO<Void> sendEmailCode(@RequestParam String email){
       return this.userService.sendEmailCode(email);
    }

    @PostMapping("/emailRegister")
    public ResultVO<Void> register(@RequestBody UserEmailRegisterDTO dto) {
        return userService.EmailRegister(dto);
    }
}
