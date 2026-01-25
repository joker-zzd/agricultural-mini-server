package com.mini.businessuser.service;

import com.mini.businessuser.domain.UserDO;
import com.baomidou.mybatisplus.extension.service.IService;
import com.mini.businessuser.domain.dto.UpdatePasswordDTO;
import com.mini.businessuser.domain.dto.UserEmailRegisterDTO;
import com.mini.businessuser.domain.dto.UserLoginDTO;
import com.mini.resultvo.ResultVO;

import javax.validation.constraints.NotEmpty;
import java.util.Map;

/**
 * @author 19256
 * @description 针对表【user(用户表)】的数据库操作Service
 * @createDate 2025-04-15 16:19:51
 */
public interface UserService extends IService<UserDO> {

    ResultVO<Map<String, Object>> login(UserLoginDTO userLoginDTO);

    ResultVO<Void> updateNickname(@NotEmpty(message = "昵称不能为空") String nickname);

    ResultVO<Void> updateSex(@NotEmpty(message = "性别不能为空") String sex);

    ResultVO<Void> updateAvatar(@NotEmpty(message = "头像不能为空") String avatar);

    ResultVO<Void> updatePassword(UpdatePasswordDTO dto);

    ResultVO<Void> sendEmailCode(String email);

    ResultVO<Void> EmailRegister(UserEmailRegisterDTO emailRegisterDTO);


}
