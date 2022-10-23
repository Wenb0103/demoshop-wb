package com.example.demodanei.service;

import com.example.demodanei.entity.Address;

import java.util.List;

public interface IAddressService {
    //获取用户地址
    List<Address> getByUid(Integer uid);
    void addnew(Address address, String username) throws Exception;
    void delete(Integer uid, Integer aid)
            throws Exception;


    void setDefault(Integer uid, Integer aid)
            throws Exception;
    /**
     * 根据收货地址id查询收货地址数据
     * @param aid 收货地址id
     * @return 匹配的收货地址数据，如果没有匹配的数据，则返回null
     */
    Address getByAid(Integer aid);
}


