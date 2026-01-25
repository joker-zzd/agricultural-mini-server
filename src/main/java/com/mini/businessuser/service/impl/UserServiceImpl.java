package com.mini.businessuser.service.impl;

import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mini.businessuser.constant.Type;
import com.mini.businessuser.domain.UserDO;
import com.mini.businessuser.domain.dto.UpdatePasswordDTO;
import com.mini.businessuser.domain.dto.UserEmailRegisterDTO;
import com.mini.businessuser.domain.dto.UserLoginDTO;
import com.mini.businessuser.service.UserService;
import com.mini.businessuser.mapper.UserMapper;
import com.mini.filter.LoginUser;
import com.mini.resultvo.ResultVO;
import com.mini.utils.JwtUtils;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * @author 19256
 * @description 针对表【user(用户表)】的数据库操作Service实现
 * @createDate 2025-04-15 16:19:51
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, UserDO>
        implements UserService {

    private final UserMapper userMapper;
    private final EmailServiceImpl emailService;
    private final StringRedisTemplate redisTemplate;
    private final String form = "1925638055@qq.com";

    public UserServiceImpl(UserMapper userMapper,
                           StringRedisTemplate redisTemplate,
                           EmailServiceImpl emailService) {
        this.userMapper = userMapper;
        this.redisTemplate = redisTemplate;
        this.emailService=emailService;
    }

    @Override
    public ResultVO<Map<String, Object>> login(UserLoginDTO userLoginDTO) {

        String identifier = userLoginDTO.getUsername();
        String password = userLoginDTO.getPassword();
        LambdaQueryWrapper<UserDO> wrapper = new LambdaQueryWrapper<>();
        wrapper.nested(w -> w.eq(UserDO::getUsername, identifier).or().eq(UserDO::getEmail, identifier))
                .eq(UserDO::getPassword, SecureUtil.md5(password));

        // 查询用户
        UserDO user = userMapper.selectOne(wrapper);
        if (user == null) {
            return ResultVO.fail("账号或密码错误");
        }

        if (!Type.MEMBER.equals(user.getType())) {
            return ResultVO.fail("管理员及商家账号无法登录");
        }

        // 生成 JWT Token
        String token = JwtUtils.generateToken(user.getId());
        System.err.println(token);

        // 构建响应数据（不直接返回完整 UserDO）
        Map<String, Object> data = new HashMap<>();
        data.put("token", token);
        Map<String, Object> userMap = new HashMap<>();
        userMap.put("id", user.getId());
        userMap.put("username", user.getUsername());
        userMap.put("password", user.getPassword());
        userMap.put("nickname", user.getNickname());
        userMap.put("sex", user.getSex());
        userMap.put("telephone", user.getTelephone());
        userMap.put("avatar", user.getAvatar());
        userMap.put("type", user.getType());

        data.put("user", userMap);

        return ResultVO.success(data);
    }

    @Override
    public ResultVO<Void> updateNickname(String nickname) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        Long userId = loginUser.getUserId();

        UserDO userDO = new UserDO();
        userDO.setId(userId);
        userDO.setNickname(nickname);

        boolean success = userMapper.updateById(userDO) > 0;
        if (success) {
            return ResultVO.success();
        } else {
            return ResultVO.fail();
        }

    }

    @Override
    public ResultVO<Void> updateSex(String sex) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        Long userId = loginUser.getUserId();

        UserDO userDO = new UserDO();
        userDO.setId(userId);
        userDO.setSex(sex);

        boolean success = userMapper.updateById(userDO) > 0;
        if (success) {
            return ResultVO.success();
        } else {
            return ResultVO.fail();
        }
    }

    @Override
    public ResultVO<Void> updateAvatar(String avatar) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        Long userId = loginUser.getUserId();

        UserDO userDO = new UserDO();
        userDO.setId(userId);
        userDO.setAvatar(avatar);

        boolean success = userMapper.updateById(userDO) > 0;
        if (success) {
            return ResultVO.success();
        } else {
            return ResultVO.fail();
        }
    }

    @Override
    public ResultVO<Void> updatePassword(UpdatePasswordDTO dto) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        Long userId = loginUser.getUserId();

        UserDO userDO = userMapper.selectById(userId);
        if (userDO == null) {
            return ResultVO.fail("用户不存在");
        }

        // 对比旧密码（假设存储的是 md5 加密后的密码）
        String encryptedOld = DigestUtils.md5DigestAsHex(dto.getOldPassword().getBytes(StandardCharsets.UTF_8));
        if (!encryptedOld.equals(userDO.getPassword())) {
            return ResultVO.fail("原密码不正确");
        }

        // 加密新密码
        String encryptedNew = DigestUtils.md5DigestAsHex(dto.getNewPassword().getBytes(StandardCharsets.UTF_8));

        // 更新数据库
        userDO.setPassword(encryptedNew);
        userMapper.updateById(userDO);

        return ResultVO.success();
    }

    @Override
    public ResultVO<Void> sendEmailCode(String email) {
        String code = String.valueOf(new Random().nextInt(900000) + 100000);
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(form);
        message.setTo(email);
        message.setSubject("注册验证码");
        message.setText("您的验证码是：" + code + "，有效期为5分钟。");

        try {
            emailService.sendEmailAsync(message);
            redisTemplate.opsForValue().set("email:code:" + email, code, 5, TimeUnit.MINUTES);
            return ResultVO.success("验证码发送成功");
        } catch (Exception e) {
            e.printStackTrace();
            return ResultVO.fail("验证码发送失败，请稍后重试");
        }
    }

    @Override
    @Transactional
    public ResultVO<Void> EmailRegister(UserEmailRegisterDTO emailRegisterDTO) {
        //1. 校验验证码
        String cacheCode = redisTemplate.opsForValue().get("email:code:" + emailRegisterDTO.getEmail());
        if (cacheCode == null) {
            return ResultVO.fail("验证码已过期，请重新获取");
        }
        if (!cacheCode.equals(emailRegisterDTO.getEmailCode())) {
            return ResultVO.fail("验证码错误");
        }

        // 校验用户名是否已存在
        LambdaQueryWrapper<UserDO> usernameWrapper = new LambdaQueryWrapper<>();
        usernameWrapper.eq(UserDO::getUsername, emailRegisterDTO.getUsername());
        UserDO userByUsername = userMapper.selectOne(usernameWrapper);
        if (userByUsername != null) {
            return ResultVO.fail("用户名已存在");
        }

        // 校验邮箱是否已存在
        LambdaQueryWrapper<UserDO> emailWrapper = new LambdaQueryWrapper<>();
        emailWrapper.eq(UserDO::getEmail, emailRegisterDTO.getEmail());
        UserDO userByEmail = userMapper.selectOne(emailWrapper);
        if (userByEmail != null) {
            return ResultVO.fail("该邮箱已被注册");
        }

        // 3. 保存用户（密码加密存储）
        UserDO userDO = new UserDO();
        userDO.setUsername(emailRegisterDTO.getUsername());
        userDO.setEmail(emailRegisterDTO.getEmail());
        userDO.setPassword(DigestUtils.md5DigestAsHex(emailRegisterDTO.getPassword().getBytes(StandardCharsets.UTF_8)));
        userDO.setType(Type.MEMBER); //默认普通用户

        boolean result = userMapper.insert(userDO) > 0;
        if (result) {
            //注册成功之后删除验验证码
            redisTemplate.delete("email:code:" + emailRegisterDTO.getEmail());
            return ResultVO.success("注册成功");
        }
        {
            return ResultVO.fail("注册失败，请稍后重试");
        }
    }

}




