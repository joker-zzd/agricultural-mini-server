package com.mini.address.mapper;

import com.mini.address.domain.AddressDO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author 19256
* @description 针对表【address(地址表)】的数据库操作Mapper
* @createDate 2025-04-17 22:57:14
* @Entity com.mini.address.domain.Address
*/

@Mapper
public interface AddressMapper extends BaseMapper<AddressDO> {

}




