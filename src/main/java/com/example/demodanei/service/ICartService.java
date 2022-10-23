package com.example.demodanei.service;

import com.example.demodanei.entity.Cart;
import com.example.demodanei.vo.CartVO.CartVO;

import java.util.List;

public interface ICartService {
    /**
     * 将用户选中的商品添加到购物车
     * @param username 当前登录的用户的用户名
     * @param cart 购物车数据
     * @throws Exception 插入数据异常
     * @throws Exception 更新数据异常
     */
    void addToCart(String username, Cart cart) throws Exception;

//    增加数量
    void addNum(Integer uid,String username,Integer cid) throws Exception;




    void reduceNum(Integer uid,String username,Integer cid) throws Exception;
    /**
     * 获取某用户的购物车数据列表
     * @param uid 用户的id
     * @return 用户的购物车数据列表
     */
    List<CartVO> getByUid(Integer uid);

    /**
     * 获取指定的某些id的购物车数据列表
     * @param cids 指定的一系列购物车数据id
     * @return 购物车数据列表
     */
    List<CartVO> getByCids(Integer[] cids);
}
