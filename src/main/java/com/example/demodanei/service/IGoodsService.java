package com.example.demodanei.service;

import com.example.demodanei.entity.Goods;

import java.util.List;

public interface IGoodsService {
    List<Goods> getHotGoods();

    Goods getById(long id);
}
