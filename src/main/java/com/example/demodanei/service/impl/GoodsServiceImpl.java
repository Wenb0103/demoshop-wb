package com.example.demodanei.service.impl;

import com.example.demodanei.entity.Goods;
import com.example.demodanei.mapper.GoodsMapper;
import com.example.demodanei.service.IGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GoodsServiceImpl implements IGoodsService {
    @Autowired
    private GoodsMapper goodsMapper;

    @Override
    public List<Goods> getHotGoods() {
        return findHotGoods();
    }

    @Override
    public Goods getById(long id) {
        return findById(id);
    }


    /**
     * 获取热销商品列表
     * @return 热销商品列表
     */
    private List<Goods> findHotGoods() {
        return goodsMapper.findHotGoods();
    }

    /**
     * 根据id查询商品详情
     * @param id 商品的id
     * @return 匹配的商品的详情，如果没有匹配的数据，则返回null
     */
    private Goods findById(Long id) {
        return goodsMapper.findById(id);
    }


}
