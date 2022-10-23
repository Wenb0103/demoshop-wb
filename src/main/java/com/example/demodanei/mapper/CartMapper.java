package com.example.demodanei.mapper;

import com.example.demodanei.entity.Cart;
import com.example.demodanei.vo.CartVO.CartVO;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * 处理购物车数据的持久层接口
 */
public interface CartMapper {
    /**
     * 插入购物车数据
     * @param cart 购物车数据
     * @return 受影响的行数
     */
    Integer insert(Cart cart);
    /**
     * 修改购物车数据中商品的数量
     * @param cid 购物车数据的id
     * @param num 新的数量
     * @param modifiedUser 修改执行人
     * @param modifiedTime 修改时间
     * @return 受影响的行数
     */
    Integer updateNum(
            @Param("cid") Integer cid,
            @Param("num") Integer num,
            @Param("modifiedUser") String modifiedUser,
            @Param("modifiedTime") Date modifiedTime);

    /**
     * 获取某用户在购物车中添加的指定商品的数据
     * @param uid 用户的id
     * @param gid 商品的id
     * @return 匹配的购物车数据，如果没有匹配的数据，则返回null
     */
    Cart findByUidAndGid(
            @Param("uid") Integer uid,
            @Param("gid") Long gid);

//    购物车列表
    List<CartVO> findByUid(Integer uid);

//    增加数量
    Cart findByCid(Integer cid);

//    显示订单页
    List<CartVO> findByCids(Integer[] cids);


}
