package com.krishnna.action.user;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.krishnna.entity.Cart;
import com.krishnna.entity.User;
import com.krishnna.service.user.UserService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.util.ValueStack;

/**
 * 用于验证登录和注册功能
 * @author Ningkui
 *
 */
public class UserAction extends ActionSupport implements ModelDriven<User>{

	private static final long serialVersionUID = 1L;

	/**
	 * 得到request对象
	 */
	HttpServletRequest request = ServletActionContext.getRequest();
	/**
	 * 得到response对象
	 */
	HttpServletResponse response = ServletActionContext.getResponse();
	
	/**
	 * 得到UserService对象(Spring配置文件+Struts核心配置文件实现)
	 */
	private UserService userService;
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	/**
	 * 得到User对象 ，同时获取前端页面表单所得的值（模型封装）----用于获得从前端页面传进来的值
	 */
	private User user = new User();
	public User getModel() {
		return user;
	}
	
	//定义验证码
	String code;
	/**
	 * 封装User对象到值栈中 ---用于把值封装起来被前端页面取用
	 */
	private User u = new User();
	public User getU() {
		u.setUsername(user.getUsername());
		if(u.getCode()!=null && u.getCode().length()>0){
			u.setCode(code);
		}
		return u;
	}

	/**
	 * 对登录过程的用户名和密码进行验证
	 */
	@Override
	public String execute() throws Exception {
		
		System.out.println("进入登录验证方法");
		/*
		 * 获取值栈对象
		 */
		ActionContext context = ActionContext.getContext();
		ValueStack stack = context.getValueStack();		
		
		/*调用Service层login方法。访问数据库，并返回布尔值。
		*		值为true则代表数据库存在该用户名。值为false则代表不存在。
		*/
		user = userService.login(user);
		/* 
		 * 若existUser为true,即存在该用户名。则记录错误信息到request域，并返回到登录页面
		 * 否则返回到成功登陆页面，即主页面main.jsp
		 */
		if(user == null) {
			request.setAttribute("passwordError", "密码错误");
			return "ERROR";
		}
		
		/*
		 *  将user对象存入session中。为了前端判断用户是否在登录状态
		 */
		request.getSession().setAttribute("user", user);
		/*
		 * 发送购物车
		 */
		Cart cart = new Cart();
		request.getSession().setAttribute("cart", cart);
		
		//System.out.println(user.toString());
		//System.out.println(user.getAddressSet().toString());
		
		return "SUCCESS";
	}

	
	/**
	 *  完成注册功能
	 */
	public String regist(){
	
		System.out.println("进入registAction.....");
		/**
		 * 调用Service层regist方法。访问数据库，存入新用户到数据库中。
		 * 同时得到返回值--验证码
		*/
		/*
		 * 若user中的用户名、密码、邮箱、手机任一为空值。向request域中存储错误信息。并且返回NONE。
		 */
		if("".equals(user.getUsername()) || "".equals(user.getPassword()) 
				|| "".equals(user.getPhone()) || "".equals(user.getEmail())){
			System.out.println("存在空字符");
			request.setAttribute("pageError", "请完整填写以下信息。");
			return "ERROR";
		}
		/*
		 * 若全局变量usernameError、phoneError、emailError任一不为空。则说明不满足操作条件，不会存入数据库
		 */
		if("1".equals(usernameError) || "1".equals(phoneError) || "1".equals(emailError)){
			System.out.println("存在不正确字符");
			request.setAttribute("pageError", "请正确填写以下信息。");
			return "ERROR";
		}
		
		code = userService.regist(user);
		//将验证码存入request域
		
		//跳转到注册成功页面。
		return "SUCCESS";
	}
	
	/**
	 * 定义全局变量
	 * usernameError
	 * emailError
	 * phoneError
	 * 用于regist方法盘判断是否有输入错误问题。初始为空，若有，则赋值为1
	 */
	private String usernameError,emailError,phoneError;
	
	/**
	 * 验证注册过程用户名是否为空
	 * @return
	 */
	public String confirmUsername(){
		/*
		 * 获得前端传进域内的值----用户名
		 */
		String username=request.getParameter("usernameAjax");
		
		User user1 = new User();
		if(username != null){
			user1.setUsername(username);
		}else{
			return NONE;
		}
		
		//若flag为username，则对应任意的值已被注册。
		String flag = userService.registConfirm(user1);
		//若flag不为空。则往域中传值
		if (!flag.isEmpty()) {
			if ("1".equals(flag)) {
				try {
					usernameError = "1";
					response.getWriter().print("usernameError");
					System.out.println("web层已经将验证结果返回域中了");
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return NONE;
	}
	
	/**
	 * 验证注册过程邮箱是否为空
	 * @return
	 */
	public String confirmEmail(){
		/*
		 * 获得前端传进域内的值-----邮箱
		 */
		String email=request.getParameter("emailAjax");
		
		User user1 = new User();
		if(email != null) {
			user1.setEmail(email);
		}else{
			return NONE;
		}
		
		//若flag为email，则对应任意的值已被注册。
		String flag = userService.registConfirm(user1);
		if(!flag.isEmpty()){
			if("1".equals(flag)) {
				try {
					emailError = "1";
					response.getWriter().print("emailError");
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return NONE;
	}
	
	/**
	 * 验证注册过程手机号是否为空
	 * @return
	 */
	public String confirmPhone(){
		/*
		 * 获得前端传进域内的值-----手机号
		 */
		String phone=request.getParameter("phoneAjax");
		
		User user1 = new User();
		if(phone != null) {
			user1.setPhone(phone);
		}else{
			return NONE;
		}
		
		//若flag为phone，则对应任意的值已被注册。
		String flag = userService.registConfirm(user1);
		if(!flag.isEmpty()){
			if("1".equals(flag)) {
				try {
					phoneError = "1";
					response.getWriter().print("phoneError");
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return NONE;
	}
}
