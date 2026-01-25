package com.mini.address.controller;

import com.mini.address.domain.dto.AddAddressDTO;
import com.mini.address.domain.dto.UpdateAddressDTO;
import com.mini.address.domain.vo.AddressListVO;
import com.mini.address.service.AddressService;
import com.mini.resultvo.ResultVO;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@CrossOrigin
@RequestMapping("/api/address")
public class AddressController {

    private final AddressService addressService;

    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @GetMapping("/findByPage")
    public ResultVO<List<AddressListVO>> findByPage(
            @RequestParam(name = "currentPage", defaultValue = "1", required = false) Integer currentPage,
            @RequestParam(name = "pageSize", defaultValue = "10", required = false) Integer pageSize) {

        return this.addressService.findByPage(currentPage, pageSize);
    }

    @PostMapping("add")
    public ResultVO<Long> AddAddress(@RequestBody AddAddressDTO addAddressDTO) {
        return this.addressService.addAddress(addAddressDTO);
    }

    @DeleteMapping("/delete")
    public ResultVO<Void> deleteAllById(@RequestBody Set<Long> ids) {
        return this.addressService.deleteAllById(ids);
    }

    @PutMapping("/updateDefaulted")
    public ResultVO<Void> updateDefaulted(@RequestParam Long id) {
        return this.addressService.updateDefaulted(id);
    }

    @PutMapping("/update")
    public ResultVO<Void> updateAddress(@RequestBody UpdateAddressDTO updateAddressDTO) {
        return addressService.updateAddress(updateAddressDTO);
    }
}
