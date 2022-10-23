package com.example.demodanei.controller;

import com.example.demodanei.entity.Cart;
import com.example.demodanei.service.ICartService;
import com.example.demodanei.util.ResponseResult;
import com.example.demodanei.vo.CartVO.CartVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;


@RestController
@RequestMapping("/carts")
public class CartController {

    @Autowired
    private ICartService cartService;

    @RequestMapping("/add")
    public ResponseResult<Void> addToCart(Cart cart, HttpSession session) throws Exception {
        // 注意：客户端提交的cart只会包含gid, num
        // 从session中获取uid
        // 从session中获取username
        Integer uid = getUidFromSession(session);
        cart.setUid(uid);
        String username = session.getAttribute("username").toString();

        System.out.println("uid="+uid+"username="+username);

        // 将uid封装到cart中
        // 执行：service.addToCart(username, cart);
        // 返回
        cartService.addToCart(username, cart);
        ResponseResult<Void> rr = new ResponseResult<Void>();
        rr.setState(200);
// 返回成功
        return rr;
    }

    private Integer getUidFromSession(HttpSession session) {
            return Integer.valueOf(session.getAttribute("uid").toString());
    }

    @RequestMapping("/{id}/add_num")
    public ResponseResult<Void> addNum(
            @PathVariable("id") Integer cid,
            HttpSession session) throws Exception {

        // 从session中获取uid和username

        Integer uid = getUidFromSession(session);
        String username = session.getAttribute("username").toString();
        // 执行
        cartService.addNum(uid,username,cid);
        ResponseResult<Void> rr = new ResponseResult<Void>();
        rr.setState(200);
// 返回成功
        return rr;
        // 返回
    }

    @RequestMapping("/{id}/reduce_Num")
    public ResponseResult<Void> reduceNum(
            @PathVariable("id") Integer cid,
            HttpSession session) throws Exception {

        // 从session中获取uid和username

        Integer uid = getUidFromSession(session);
        String username = session.getAttribute("username").toString();
        // 执行
        cartService.addNum(uid,username,cid);
        ResponseResult<Void> rr = new ResponseResult<Void>();
        rr.setState(200);
// 返回成功
        return rr;
        // 返回
    }

    @GetMapping("/")
    public ResponseResult<List<CartVO>> getByUid(
            HttpSession session) {
        // 从session中获取uid
        Integer uid = getUidFromSession(session);
        // 执行：service.addToCart(username, cart);
        List<CartVO> data = cartService.getByUid(uid);
        // 返回
        ResponseResult<List<CartVO>> rr = new ResponseResult<List<CartVO>>();
        rr.setState(200);
        rr.setData(data);
        return rr;
    }

    @GetMapping("/checked_list")
    public ResponseResult<List<CartVO>> getByCids(
            Integer[] cids) {
        // 执行
        List<CartVO> data = cartService.getByCids(cids);
        // 返回
        ResponseResult<List<CartVO>> rr = new ResponseResult<List<CartVO>>();
        rr.setState(200);
        rr.setData(data);
        return rr;
    }



}
