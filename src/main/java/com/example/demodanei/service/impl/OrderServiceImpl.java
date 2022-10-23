package com.example.demodanei.service.impl;

import com.example.demodanei.entity.Address;
import com.example.demodanei.entity.Order;
import com.example.demodanei.entity.OrderItem;
import com.example.demodanei.mapper.OrderMapper;
import com.example.demodanei.service.IAddressService;
import com.example.demodanei.service.ICartService;
import com.example.demodanei.service.IOrderService;
import com.example.demodanei.vo.CartVO.CartVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class OrderServiceImpl implements IOrderService {
    @Resource
    private OrderMapper orderMapper;
    @Autowired
    private IAddressService addressService;
    @Autowired
    private ICartService cartService;

    @Override
    public Order createOrder(Integer uid, String username, Integer aid, Integer[] cids) throws Exception {
        // 创建当前时间对象now
        Date now = new Date();

        // 调用cartService方法根据cids获取数据：List<CartVO> getByCids(Integer[] cids);
        List<CartVO> carts = cartService.getByCids(cids);
        // 遍历计算商品总价
        Long price = 0L;
        for (CartVO cartVO : carts) {
            price += cartVO.getPrice() * cartVO.getNum();
        }

        // 创建订单数据对象：new Order()
        Order order = new Order();
        // 封装订单数据的属性：uid
        order.setUid(uid);
        // 调用addressService获取收货地址数据：getByAid(Integer aid)
        Address address = addressService.getByAid(aid);
        // 封装订单数据的属性：name, phone, address
        if (address == null) {
            throw new Exception();
        }
        if (!address.getUid().equals(uid)) {
            throw new Exception();
        }
        order.setName(address.getName());
        order.setPhone(address.getPhone());
        order.setAddress(address.getDistrict() + " " + address.getAddress());
        // 封装订单数据的属性：status:0
        order.setStatus(0);
        // 封装订单数据的属性：price
        order.setPrice(price);
        // 封装订单数据的属性：orderTime:now
        order.setOrderTime(now);
        // 封装订单数据的属性：payTime:null
        order.setPayTime(null);
        // 封装4项日志属性
        order.setCreatedUser(username);
        order.setCreatedTime(now);
        order.setModifiedUser(username);
        order.setModifiedTime(now);
        // 插入订单数据：insertOrder(Order order)
        insertOrder(order);

        // 遍历以上查询结果
        for (CartVO cart : carts) {
            // -- 创建订单商品数据对象：new OrderItem()
            OrderItem item = new OrderItem();
            // -- 封装订单商品数据的属性：oid
            item.setOid(order.getOid());
            // -- 封装订单商品数据的属性：gid,title,image,price,num
            item.setGid(cart.getGid());
            item.setTitle(cart.getTitle());
            item.setImage(cart.getImage());
            item.setPrice(cart.getPrice());
            item.setNum(cart.getNum());
            // -- 封装4项日志属性
            item.setCreatedUser(username);
            item.setCreatedTime(now);
            item.setModifiedUser(username);
            item.setModifiedTime(now);
            // -- 插入订单商品数据：insertOrderItem(OrderItem orderItem)
            insertOrderItem(item);
        }
        return order;
    }

    @Override
    public List<Order> getOrders(Integer uid) {
        return orderMapper.getOrders(uid);
    }

    @Override
    public List<OrderItem> getItem(Integer oid) {
        return orderMapper.getItem(oid);
    }

    /**
     * 插入订单数据
     * @param order 订单数据
     */
    private void insertOrder(Order order) throws Exception {
        Integer rows = orderMapper.insertOrder(order);
        if (rows != 1) {
            throw new Exception();
        }
    }

    /**
     * 插入订单商品数据
     * @param orderItem 订单商品数据
     */
    private void insertOrderItem(OrderItem orderItem) throws Exception {
        Integer rows = orderMapper.insertOrderItem(orderItem);
        if (rows != 1) {
            throw new Exception();
        }
    }

}



