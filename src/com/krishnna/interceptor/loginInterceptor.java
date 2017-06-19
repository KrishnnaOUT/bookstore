package com.krishnna.interceptor;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;


/**
 * 自定义拦截器类
 * -->实现登录拦截功能
 * @author Ningkui
 *
 */
public class loginInterceptor extends MethodFilterInterceptor {

	@Override
	protected String doIntercept(ActionInvocation Invocation) throws Exception {
		
		//获得Request对象
		HttpServletRequest request = ServletActionContext.getRequest();
		
		//若session域中没有user对象，则返回到登录界面
		if(request.getSession().getAttribute("user") == null){
			return "LOGIN";
		}
		
		//否则就放行
		return Invocation.invoke();
	}

}
