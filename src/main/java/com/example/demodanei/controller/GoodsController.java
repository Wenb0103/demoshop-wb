package com.example.demodanei.controller;

import com.example.demodanei.entity.Goods;
import com.example.demodanei.service.IGoodsService;
import com.example.demodanei.util.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/goods")
public class GoodsController {

    @Autowired
    private IGoodsService goodsService;

    @GetMapping("/hot")
    public ResponseResult<List<Goods>> getHotGoods() {
        // 获取数据
        List<Goods> data = goodsService.getHotGoods();
        // 返回
        ResponseResult<List<Goods>> rr = new ResponseResult<List<Goods>>();
        rr.setState(200);

        rr.setData(data);
        return rr;


    }

    @GetMapping("/{id}/details")
    public ResponseResult<Goods> getById(
            @PathVariable("id") Long id) {
        // 执行
        Goods data = goodsService.getById(id);
        // 返回

        ResponseResult<Goods> rr = new ResponseResult<Goods>();
        rr.setState(200);

        rr.setData(data);
        return rr;
    }
}
