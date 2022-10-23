package com.example.demodanei.mapper;

import com.example.demodanei.entity.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;

//用户接口   //持久层
@Repository
public interface UserMapper {
	//添加方法
	Integer insert(User user);
    //获取用户对象方法
	User findByUsername(@Param("username") String username);

	//修改密码
	Integer updatePassword(
			@Param("uid") Integer uid,
			@Param("password") String password,
			@Param("modifiedUser") String modifiedUser,
			@Param("modifiedTime") Date modifiedTime);

	//根据uid查询用户对象
	User findByUid(Integer uid);

	//修改个人信息
	Integer updateInfo(User user);

//	User getByUid(Integer uid) throws Exception;

	//添加图片
	Integer updateAvatar(
			@Param("uid") Integer uid,
			@Param("avatar") String avatar,
			@Param("modifiedUser") String modifiedUser,
			@Param("modifiedTime") Date modifiedTime);




}
