package com.example.demodanei.controller;

import com.example.demodanei.entity.User;
import com.example.demodanei.service.IUserService;
import com.example.demodanei.util.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

@RestController   //控制器注解
@RequestMapping("/users")   //html表单form提交位置Action=".."
public class UserController {
	@Autowired
	private IUserService userService;//调用用户业务逻辑层接口

	@PostMapping("/reg")  //form  method="get/post"
	public ResponseResult<Void> reg(User user) {
		ResponseResult<Void> rr = new ResponseResult<Void>();
		try {
			userService.reg(user);//注册
			rr.setState(200);//注册成功
		} catch (Exception e) {
			rr.setState(100);//注册失败
			rr.setMessage("注册失败");
		}
		return rr;
	}

	@RequestMapping("/login")
	public ResponseResult<User> login(String username, String password, HttpSession session) throws Exception {
		ResponseResult<User> rr = new ResponseResult<User>();

		try {
			User login = userService.login(username, password);

			session.setAttribute("uid", login.getUid()); //方便后面功能使用 在login中，无作用
			session.setAttribute("username", login.getUsername());
			rr.setState(200);
			rr.setData(login);

		} catch (Exception e) {

			rr.setMessage("登录失败！尝试登录的用户不存在！");
		}

		return rr;


	}

	@RequestMapping("/change_password")
	public ResponseResult<Void> changePassword(@RequestParam("old_password") String oldPassword, @RequestParam("new_password") String newPassword, HttpSession session) throws Exception {

		ResponseResult<Void> rr = new ResponseResult<Void>();


		// 从session中获取uid和username
		Integer uid = getUidFromSession(session);

		String username = session.getAttribute("username").toString();

		// 执行修改密码：service.changePassword(uid,username,oldPassword,newPassword)
		userService.changePassword(uid, username, oldPassword, newPassword);
		rr.setState(200);//注册成功

		// 返回结果
		return rr;
	}

	/**
	 * 从Session获取当前登录的用户id
	 *
	 * @param session HttpSession对象
	 * @return 当前登录的用户id
	 */
	protected final Integer getUidFromSession(HttpSession session) {
		return Integer.valueOf(session.getAttribute("uid").toString());
	}

	@GetMapping("/info")
	public ResponseResult<User> getByUid(HttpSession session) throws Exception {
// 获取uid
		Integer uid = Integer.valueOf(session.getAttribute("uid").toString());
// 查询用户数据
		User data = userService.getByUid(uid);
		ResponseResult<User> rr = new ResponseResult<User>();
		rr.setState(200);
		rr.setData(data);
// 返回
		return rr;

	}


	@RequestMapping("/change_info")
	public ResponseResult<Void> changeInfo(User user, HttpSession session) throws Exception {
// 封装uid
		Integer uid = Integer.valueOf(session.getAttribute("uid").toString());
		user.setUid(uid);
// 执行修改个人资料
		userService.changeInfo(user);
		ResponseResult<Void> rr = new ResponseResult<Void>();
		rr.setState(200);
// 返回
		return rr;
	}

	/**
	 * 确定上传文件的名称
	 */
	private static final String UPLOAD_DIR = "upload";
	/**
	 * 确定上传文件的最大大小
	 */
	private static final long UPLOAD_MAX_SIZE = 1 * 1024 * 1024;
	/**
	 * 确定允许上传的类型的列表
	 */
	private static final List<String> UPLOAD_CONTENT_TYPES = new ArrayList<>();
	static {
		UPLOAD_CONTENT_TYPES.add("image/jpeg");
		UPLOAD_CONTENT_TYPES.add("image/png");
		UPLOAD_CONTENT_TYPES.add("image/gif");
		UPLOAD_CONTENT_TYPES.add("image/bmp");
	}
	@PostMapping("/change_avatar")
	public ResponseResult<String> changeAvatar(HttpServletRequest request, @RequestParam("file") MultipartFile file) throws Exception {
		// 检查文件是否为空
		if (file.isEmpty()) {
// 为空：抛出异常：FileEmptyException
			throw new Exception();
		}
// 检查文件大小
		if (file.getSize() > UPLOAD_MAX_SIZE) {
// 超出范围(> UPLOAD_MAX_SIZE)：抛出异常：FileSizeException
			throw new Exception();
		}
// 检查文件类型
		String contentType = file.getContentType();
		if (!UPLOAD_CONTENT_TYPES.contains(contentType)) {
// 类型不符(contains()为false)：抛出异常：
			throw new Exception();
		}
// 确定文件夹路径：
		request.getServletContext().getRealPath(UPLOAD_DIR);
		String parentPath = request.getServletContext().getRealPath(UPLOAD_DIR);
		System.err.println(parentPath+"=====================");
// 创建上传文件夹的File对象parent
		File parent = new File(parentPath);
// 检查文件夹是否存在，如果不存在，则创建
		if (!parent.exists()) {
			parent.mkdirs();
		}
// 获取原文件名：file.getOriginalFilename()
		String originalFilename = file.getOriginalFilename();
		System.err.println(originalFilename+"=====++++++===========");
// 从原文件名中得到扩展名
		String suffix = "";
		int beginIndex = originalFilename.lastIndexOf(".");
		System.err.println(beginIndex+"===--------------======");
		if (beginIndex > 0) {
			suffix = originalFilename.substring(beginIndex);
		}
// 确定文件名：uuid/nanoTime/...
		String filename = System.nanoTime() + suffix;
		System.err.println(filename+"===-----+++++-----======");
// 创建dest对象：new File(parent, filename);
		File dest = new File(parent, filename);
		System.err.println(filename+"--------------------------");
		try {
			file.transferTo(dest);
		} catch (Exception e) {
			throw new Exception();
		}
// 获取uid：getUidFromSession(request.getSession());
		Integer uid = Integer.valueOf(request.getSession().getAttribute("uid").toString());
// 生成avatar：/UPLOAD_DIR/文件名.扩展名
		String avatar = "/" + UPLOAD_DIR + "/" + filename;
		System.err.println(avatar+"!11111111111111111111111");
// 执行更新：userService.changeAvatar(uid, avatar);
		userService.changeAvatar(uid, avatar);
		ResponseResult<String> rr = new ResponseResult<String>();
		rr.setState(200);
		rr.setData(avatar);
// 返回成功

		return rr;
	}
}






