package com.example.demodanei.service;

import com.example.demodanei.entity.User;

public interface IUserService {
//	注册
	void reg(User user) throws Exception ;

//	登录
	User login(String username,String password) throws Exception;

//	修改密码
	void changePassword(Integer uid, String username, String oldPassword, String newPassword) throws Exception;

	//根据uid获取用户信息
	User getByUid(Integer uid) throws Exception;

	//修改个人信息业务逻辑层
	void changeInfo(User user) throws Exception;

	//修改图片
	void changeAvatar(Integer uid, String avatar) throws Exception;


}
