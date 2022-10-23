package com.example.demodanei.service.impl;


import com.example.demodanei.entity.User;
import com.example.demodanei.mapper.UserMapper;
import com.example.demodanei.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
//业务逻辑层注解
@Service
public class UserServiceImpl implements IUserService {
    
	@Autowired
	private UserMapper userMapper;
	@Override
	//注册
	public void reg(User user) throws Exception {
		//获取用户名称
		String username = user.getUsername();
		//用传进来用户名称查询数据库是否存在该用户
		User result = findByUsername(username);
		if (result == null) {//全等与空代表该用户没注册
			// 设置is_delete
	        user.setIsDelete(0);
	        // 设置4项日志
	        Date now = new Date();
	        user.setCreatedUser(username);
	        user.setCreatedTime(now);
	        user.setModifiedUser(username);
	        user.setModifiedTime(now);
	        // 插入用户
	        insert(user);
	    } else {
	        throw new Exception();
	    }	
	}

	//添加数据
	private void insert(User user) throws Exception {
		//调用持久层添加方法
		Integer rows = userMapper.insert(user);
		if (rows != 1) {
			throw new Exception();
		}
	}

	//查询用户
	private User findByUsername(String username) {
		//调用接口查询方法
		return userMapper.findByUsername(username);
	}


	@Override
	public User login(String username, String password) throws Exception {

		// 根据参数username查询用户：User findByUsername(String username)
		User result = findByUsername(username);
		// 判断查询结果是否为null
		if (result == null) {
			// 是：抛出UserNotFoundException
			throw new Exception("登录失败！尝试登录的用户不存在！");
		}

		// 判断is_delete是否标记为已删除：isDelete属性值是否为1
		if (result.getIsDelete().equals(1)) {
			// 是：抛出UserNotFoundException
			throw new Exception("登录失败！尝试登录的用户不存在！");
		}
		// 从查询结果中获取盐值
//		String salt = result.getSalt();
//		// 对参数password执行加密
//		String md5Password = getMd5Password(password, salt);
		// 判断查询结果中的密码与刚加密结果是否一致
//		if (result.getPassword().equals(md5Password))
		if (result.getPassword().equals(password)) {
			// 是：准备返回结果，先去除部分不需要对外使用的数据
//			result.setPassword(null);
//			result.setIsDelete(null);
			// 返回查询结果
			return result;
		}
		else {
			// 否：抛出PasswordNotMatchException

			throw new Exception("登录失败！错误密码！");
		}

	}

	@Override
	public void changePassword(Integer uid, String username, String oldPassword, String newPassword) throws Exception {

		// 根据uid查询用户数据
		User result = findByUid(uid);
		// 判断查询结果是否为null
		if (result == null) {
			// 是：抛出UserNotFoundException
			throw new Exception(
					"修改密码失败！尝试访问的用户不存在！");
		}

		// 判断查询结果中isDelete是否为1
		if (result.getIsDelete().equals(1)) {
			// 是：抛出UserNotFoundException
			throw new Exception(
					"修改密码失败！尝试访问的用户不存在！");
		}

		// 从查询结果中获取盐值
//		String salt = result.getSalt();
		// 将oldPassword结合盐值加密，得到oldMd5Password
//		String oldMd5Password = getMd5Password(oldPassword, salt);
		// 判断查询结果中的密码（用户当前的真实密码）与oldMd5Password是否不匹配
		if (!result.getPassword().equals(oldPassword)) {
			// 是：抛出PasswordNotMatchException
			throw new Exception("修改密码失败！原密码错误！");
		}

		// 将newPassword结合盐值加密，得到newMd5Password
//		String newMd5Password = getMd5Password(newPassword, salt);
		// 创建时间对象now
		Date now = new Date();
		// 执行修改密码：updatePassword(uid, newMd5Password, username, now)
		updatePassword(uid, newPassword, username, now);
	}

	/**
	 * 更新用户密码
	 * @param uid 用户的id
	 * @param password 新密码
	 * @param modifiedUser 修改执行人
	 * @param modifiedTime 修改时间
	 */
	private void updatePassword(Integer uid, String password, String modifiedUser, Date modifiedTime) throws Exception {
		Integer rows = userMapper.updatePassword(uid, password, modifiedUser, modifiedTime);
		if (rows != 1) {
			throw new Exception("修改用户数据时出现未知错误！");
		}
	}

	/**
	 * 根据用户id查询用户数据
	 * @param uid 用户id
	 * @return 匹配的用户数据，如果没有匹配的数据，则返回null
	 */
	private User findByUid(Integer uid) {
		return userMapper.findByUid(uid);
	}

	//根据用户uid查询用户信息
	public User getByUid(Integer uid) throws Exception {
		User result = findByUid(uid);
		if (result == null) {
			throw new Exception();
		}
		if (result.getIsDelete().equals(1)) {
			throw new Exception();
		}
		result.setPassword(null);
		result.setSalt(null);
		result.setIsDelete(null);
		return result;
	}

	@Override
	public void changeInfo(User user) throws Exception {
		User result = findByUid(user.getUid());
		if (result == null) {
			throw new Exception();
		}
		if (result.getIsDelete().equals(1)) {
			throw new Exception();
		}
		user.setModifiedUser(result.getUsername());
		user.setModifiedTime(new Date());
// 执行更新
 		updateInfo(user);
	}
	private void updateInfo(User user) throws Exception {
			Integer rows = userMapper.updateInfo(user);
			if (rows != 1) {
				throw new Exception();
			}
	}



	@Override
	public void changeAvatar(Integer uid, String avatar) throws Exception {
		// 根据uid查询用户数据
		User result = findByUid(uid);
		// 判断查询结果是否为null
		if (result == null) {// 是：抛出UserNotFoundException
			throw new Exception();
		}
		// 判断查询结果中isDelete是否为1
		if (result.getIsDelete().equals(1)) {// 是：
			throw new Exception();
		}
		// 向user中封装modifiedUser和modifiedTime
		String modifiedUser = result.getUsername();
		Date modifiedTime = new Date();
		// 执行更新
		updateAvatar(uid, avatar, modifiedUser, modifiedTime);
	}
	private void updateAvatar(Integer uid, String avatar, String modifiedUser, Date modifiedTime) throws Exception {
		Integer rows = userMapper.updateAvatar(uid, avatar, modifiedUser,modifiedTime);
		if (rows != 1) {
			throw new Exception();
		}
	}
}



