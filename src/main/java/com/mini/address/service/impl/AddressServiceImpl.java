package com.mini.address.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mini.address.domain.AddressDO;
import com.mini.address.domain.dto.AddAddressDTO;
import com.mini.address.domain.dto.UpdateAddressDTO;
import com.mini.address.domain.vo.AddressListVO;
import com.mini.address.service.AddressService;
import com.mini.address.mapper.AddressMapper;
import com.mini.filter.LoginUser;
import com.mini.resultvo.ResultVO;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Set;

/**
 * @author 19256
 * @description 针对表【address(地址表)】的数据库操作Service实现
 * @createDate 2025-04-17 22:57:14
 */
@Service
public class AddressServiceImpl extends ServiceImpl<AddressMapper, AddressDO>
        implements AddressService {

    private final AddressMapper addressMapper;

    public AddressServiceImpl(AddressMapper addressMapper) {
        this.addressMapper = addressMapper;
    }

    @Override
    public ResultVO<List<AddressListVO>> findByPage(Integer currentPage, Integer pageSize) {
        Page<AddressDO> page = new Page<>(currentPage, pageSize);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        Long userId = loginUser.getUserId();

        LambdaQueryWrapper<AddressDO> wrapper = new LambdaQueryWrapper<>();

        wrapper.orderByDesc(AddressDO::getCreateAt)
                .eq(userId != null, AddressDO::getUserId, userId);

        this.addressMapper.selectPage(page, wrapper);

        List<AddressDO> addressDOList = page.getRecords();

        List<AddressListVO> addressListVOList = addressDOList.stream().map(addressDO -> {
            AddressListVO vo = new AddressListVO();

            vo.setId(addressDO.getId());
            vo.setName(addressDO.getName());
            vo.setAddressDetail(addressDO.getAddressDetail());
            vo.setCity(addressDO.getCity());
            vo.setCounty(addressDO.getCounty());
            vo.setTel(addressDO.getTel());
            vo.setProvince(addressDO.getProvince());
            vo.setDefaulted(addressDO.getDefaulted());

            return vo;
        }).toList();


        return ResultVO.success(addressListVOList, page.getTotal());
    }

    @Override
    public ResultVO<Void> deleteAllById(Set<Long> ids) {
        if (CollectionUtils.isEmpty(ids)){
            return ResultVO.fail("ID 不能为空");
        }
        boolean result=this.addressMapper.deleteBatchIds(ids)>0;
        if (result){
            return ResultVO.success();
        }else{
            return ResultVO.fail();
        }
    }

    @Override
    public ResultVO<Long> addAddress(AddAddressDTO addAddressDTO) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        Long userId = loginUser.getUserId();

        AddressDO address = new AddressDO();
        address.setUserId(userId);
        address.setName(addAddressDTO.getName());
        address.setTel(addAddressDTO.getTel());
        address.setProvince(addAddressDTO.getProvince());
        address.setCity(addAddressDTO.getCity());
        address.setCounty(addAddressDTO.getCounty());
        address.setAddressDetail(addAddressDTO.getAddressDetail());
        address.setDefaulted(addAddressDTO.getDefaulted());

        boolean result=this.addressMapper.insert(address)>0;

        if (result) {
            Long newId= address.getId();
            return ResultVO.success(newId);
        } else {
            return ResultVO.fail("添加地址失败");
        }
    }

    @Override
    public ResultVO<Void> updateDefaulted(Long id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        Long userId = loginUser.getUserId();

        // 1. 先取消当前用户的默认地址
        UpdateWrapper<AddressDO> resetWrapper=new UpdateWrapper<>();
        resetWrapper.eq("user_id",userId).set("defaulted",false);
        addressMapper.update(null,resetWrapper);

        // 2. 设置指定地址为默认
        AddressDO addressDO=new AddressDO();
        addressDO.setId(id);
        addressDO.setDefaulted(true);

        addressMapper.updateById(addressDO);

        return ResultVO.success("默认地址设置成功");
    }

    @Override
    public ResultVO<Void> updateAddress(UpdateAddressDTO updateAddressDTO) {
        try {
            AddressDO address = addressMapper.selectById(updateAddressDTO.getId());

            if (address == null) {
                return ResultVO.fail("地址不存在");
            }

            address.setName(updateAddressDTO.getName());
            address.setTel(updateAddressDTO.getTel());
            address.setProvince(updateAddressDTO.getProvince());
            address.setCity(updateAddressDTO.getCity());
            address.setCounty(updateAddressDTO.getCounty());
            address.setAddressDetail(updateAddressDTO.getAddressDetail());

            addressMapper.updateById(address);

            return ResultVO.success();
        } catch (Exception e) {
            return ResultVO.fail("更新地址失败：" + e.getMessage());
        }
    }
}




