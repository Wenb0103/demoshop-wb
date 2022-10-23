package com.example.demodanei.mapper;

import com.example.demodanei.entity.Order;
import com.example.demodanei.entity.OrderItem;

import java.util.List;

public interface OrderMapper {
    /**
     * 插入订单数据
     * @param order 订单数据
     * @return 受影响的行数
     */
    Integer insertOrder(Order order);

    /**
     * 插入订单商品数据
     * @param orderItem 订单商品数据
     * @return 受影响的行数
     */
    Integer insertOrderItem(OrderItem orderItem);

    List<Order> getOrders(Integer uid);

    List<OrderItem> getItem(Integer oid);
}
