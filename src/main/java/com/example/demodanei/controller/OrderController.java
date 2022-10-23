package com.example.demodanei.controller;

import com.example.demodanei.entity.Order;
import com.example.demodanei.entity.OrderItem;
import com.example.demodanei.service.IOrderService;
import com.example.demodanei.util.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private IOrderService orderService;

    @RequestMapping("/create")
    public ResponseResult<Order> createOrder(
            Integer aid, Integer[] cids, HttpSession session) throws Exception {
        // 从session中获取uid
        Integer uid = getUidFromSession(session);
        // 从session中获取username
        String username = session.getAttribute("username").toString();
        // 执行
        Order data = orderService.createOrder(uid, username, aid, cids);
        // 返回
        ResponseResult<Order> rr = new ResponseResult<Order>();
        rr.setState(200);
        rr.setData(data);
        return rr;
    }



    private Integer getUidFromSession(HttpSession session) {
        return Integer.valueOf(session.getAttribute("uid").toString());
    }

    @GetMapping("/show")
    public ResponseResult<List<Order>> showOrders(HttpSession session) {
        // 从session中获取uid
        Integer uid = getUidFromSession(session);
        // 执行
        List<Order> orders  = orderService.getOrders(uid);
        ResponseResult<List<Order>> rr = new ResponseResult<List<Order>>();
        rr.setState(200);
        rr.setData(orders);
        return rr;
    }

    @GetMapping("/getItem")
    public ResponseResult<List<OrderItem>> getItem(Integer oid) {
        List<OrderItem> item  = orderService.getItem(oid);
        ResponseResult<List<OrderItem>> rr = new ResponseResult<List<OrderItem>>();
        rr.setState(200);
        rr.setData(item);
        return rr;
    }

}

