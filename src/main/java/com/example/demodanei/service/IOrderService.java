package com.example.demodanei.service;

import com.example.demodanei.entity.Order;
import com.example.demodanei.entity.OrderItem;

import java.util.List;

public interface IOrderService {
    Order createOrder(Integer uid, String username,
                      Integer aid, Integer[] cids)
            throws Exception;

    List<Order> getOrders(Integer uid);

    List<OrderItem> getItem(Integer oid);

}
