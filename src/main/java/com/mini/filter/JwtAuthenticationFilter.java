package com.mini.filter;

import com.mini.businessuser.domain.UserDO;
import com.mini.businessuser.mapper.UserMapper;
import com.mini.utils.JwtUtils;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;

public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final UserMapper userMapper;

    public JwtAuthenticationFilter(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        String token = request.getHeader("Authorization");

        if (token != null && !token.isEmpty()) {
            try {
                Long userId = JwtUtils.getUserIdFromToken(token);

                //  查数据库获取用户信息（你可以用 UserService 或 Mapper）
                UserDO user = userMapper.selectById(userId); // 你自己项目里的 user 表

                if (user != null) {
//                    LoginUser loginUser = new LoginUser(userId,user.getType());
                    LoginUser loginUser=new LoginUser(userId,user.getUserType());

                    UsernamePasswordAuthenticationToken authentication =
                            new UsernamePasswordAuthenticationToken(
                                    loginUser,
                                    null,
                                    Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"))
                            );

                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }

            } catch (Exception e) {
                // token 无效，不设置认证对象，不返回 403
                // 清理上下文，确保白名单接口可以正常访问
                SecurityContextHolder.clearContext();
                System.err.println("requestURI = " + request.getRequestURI());

            }
        }

        filterChain.doFilter(request, response);
    }

}
