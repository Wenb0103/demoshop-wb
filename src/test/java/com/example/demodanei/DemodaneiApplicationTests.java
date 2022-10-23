package com.example.demodanei;


import com.example.demodanei.entity.Cart;
import com.example.demodanei.mapper.CartMapper;
import com.example.demodanei.service.ICartService;
import com.example.demodanei.vo.CartVO.CartVO;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
class DemodaneiApplicationTests {

    @Autowired
    private CartMapper mapper;

    @Test
    public void findByCid() {
        Integer cid = 6;
        Cart cart = mapper.findByCid(cid);
        System.err.println(cart);
    }
}


