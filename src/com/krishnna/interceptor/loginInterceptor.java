package com.krishnna.interceptor;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;


/**
 * �Զ�����������
 * -->ʵ�ֵ�¼���ع���
 * @author Ningkui
 *
 */
public class loginInterceptor extends MethodFilterInterceptor {

	@Override
	protected String doIntercept(ActionInvocation Invocation) throws Exception {
		
		//���Request����
		HttpServletRequest request = ServletActionContext.getRequest();
		
		//��session����û��user�����򷵻ص���¼����
		if(request.getSession().getAttribute("user") == null){
			return "LOGIN";
		}
		
		//����ͷ���
		return Invocation.invoke();
	}

}
