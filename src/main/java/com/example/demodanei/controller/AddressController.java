package com.example.demodanei.controller;


import com.example.demodanei.entity.Address;
import com.example.demodanei.service.IAddressService;
import com.example.demodanei.util.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("/addresses")
public class AddressController {
    @Autowired
    private IAddressService addressService;
    @RequestMapping("/addnew")
    public ResponseResult<Void> addnew(Address address, HttpSession session) throws Exception {
// 从session中获取uid
        Integer uid = Integer.valueOf(
                session.getAttribute("uid").toString());
// 将uid封装到address中
        address.setUid(uid);
// 从session中获取username
        String username = session.getAttribute("username").toString();
// 调用业务层对象执行：addressService.addnew(address, username);
        addressService.addnew(address, username);
        ResponseResult<Void> rr
                = new ResponseResult<Void>();
        rr.setState(200);
// 返回成功
        return rr;
    }
    @GetMapping("/")
    public ResponseResult<List<Address>> getByUid(HttpSession session) {
// 从session中获取uid
        Integer uid = Integer.valueOf(
                session.getAttribute("uid").toString());
// 调用业务层对象执行
        List<Address> data = addressService.getByUid(uid);
        ResponseResult<List<Address>> rr
                = new ResponseResult<List<Address>>();
        rr.setState(200);
        rr.setData(data);
// 返回
        return rr;
    }



    @RequestMapping("/{aid}/set_default")
    public ResponseResult<Void> setDefault(@PathVariable("aid") Integer aid,
            HttpSession session) throws Exception {
        // 从session中获取uid
        Integer uid = getUidFromSession(session);
        // 调用业务层对象执行
        addressService.setDefault(uid, aid);
        // 返回
        ResponseResult<Void> rr
                = new ResponseResult<Void>();
        rr.setState(200);
// 返回成功
        return rr;
    }

    protected final Integer getUidFromSession(HttpSession session) {
        return Integer.valueOf(session.getAttribute("uid").toString());
    }

    @RequestMapping("/{aid}/delete")
    public ResponseResult<Void> delete(
            @PathVariable("aid") Integer aid, HttpSession session) throws Exception {
// 获取uid
        Integer uid = Integer.valueOf(
                session.getAttribute("uid").toString());
// 执行
        addressService.delete(uid, aid);
        ResponseResult<Void> rr
                = new ResponseResult<Void>();
        rr.setState(200);
// 返回
        return rr;
    }


}
