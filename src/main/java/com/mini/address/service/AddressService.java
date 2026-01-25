package com.mini.address.service;

import com.mini.address.domain.AddressDO;
import com.baomidou.mybatisplus.extension.service.IService;
import com.mini.address.domain.dto.AddAddressDTO;
import com.mini.address.domain.dto.UpdateAddressDTO;
import com.mini.address.domain.vo.AddressListVO;
import com.mini.resultvo.ResultVO;

import javax.validation.constraints.NotEmpty;
import java.util.List;
import java.util.Set;

/**
* @author 19256
* @description 针对表【address(地址表)】的数据库操作Service
* @createDate 2025-04-17 22:57:14
*/
public interface AddressService extends IService<AddressDO> {

    ResultVO<List<AddressListVO>> findByPage(Integer currentPage,Integer pageSize);

    ResultVO<Void> deleteAllById(@NotEmpty(message = "ids不能为空") Set<Long> ids);

    ResultVO<Long> addAddress(AddAddressDTO addAddressDTO);

    ResultVO<Void> updateDefaulted(@NotEmpty(message = "默认地址不能为空") Long id);

    ResultVO<Void> updateAddress(UpdateAddressDTO updateAddressDTO);


}
