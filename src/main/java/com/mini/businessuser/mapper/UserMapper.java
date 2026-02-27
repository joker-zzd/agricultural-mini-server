package com.mini.businessuser.mapper;

import com.mini.businessuser.domain.UserDO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mini.businessuser.domain.dto.UserDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @author 19256
* @description 针对表【user(用户表)】的数据库操作Mapper
* @createDate 2025-04-15 16:19:51
* @Entity com.mini.businessuser.domain.User
*/
@Mapper
public interface UserMapper extends BaseMapper<UserDO> {
    List<UserDTO> selectUserBatchIds(@Param("ids") List<Long> ids);
}




