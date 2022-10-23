package com.example.demodanei.mapper;

import com.example.demodanei.entity.Goods;

import java.util.List;

/**
 * @author zwb
 */
public interface GoodsMapper {

    List<Goods> findHotGoods();

    Goods findById(long id);
}
