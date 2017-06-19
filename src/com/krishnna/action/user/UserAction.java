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
 * ������֤��¼��ע�Ṧ��
 * @author Ningkui
 *
 */
public class UserAction extends ActionSupport implements ModelDriven<User>{

	private static final long serialVersionUID = 1L;

	/**
	 * �õ�request����
	 */
	HttpServletRequest request = ServletActionContext.getRequest();
	/**
	 * �õ�response����
	 */
	HttpServletResponse response = ServletActionContext.getResponse();
	
	/**
	 * �õ�UserService����(Spring�����ļ�+Struts���������ļ�ʵ��)
	 */
	private UserService userService;
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	/**
	 * �õ�User���� ��ͬʱ��ȡǰ��ҳ������õ�ֵ��ģ�ͷ�װ��----���ڻ�ô�ǰ��ҳ�洫������ֵ
	 */
	private User user = new User();
	public User getModel() {
		return user;
	}
	
	//������֤��
	String code;
	/**
	 * ��װUser����ֵջ�� ---���ڰ�ֵ��װ������ǰ��ҳ��ȡ��
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
	 * �Ե�¼���̵��û��������������֤
	 */
	@Override
	public String execute() throws Exception {
		
		System.out.println("�����¼��֤����");
		/*
		 * ��ȡֵջ����
		 */
		ActionContext context = ActionContext.getContext();
		ValueStack stack = context.getValueStack();		
		
		/*����Service��login�������������ݿ⣬�����ز���ֵ��
		*		ֵΪtrue��������ݿ���ڸ��û�����ֵΪfalse��������ڡ�
		*/
		user = userService.login(user);
		/* 
		 * ��existUserΪtrue,�����ڸ��û��������¼������Ϣ��request�򣬲����ص���¼ҳ��
		 * ���򷵻ص��ɹ���½ҳ�棬����ҳ��main.jsp
		 */
		if(user == null) {
			request.setAttribute("passwordError", "�������");
			return "ERROR";
		}
		
		/*
		 *  ��user�������session�С�Ϊ��ǰ���ж��û��Ƿ��ڵ�¼״̬
		 */
		request.getSession().setAttribute("user", user);
		/*
		 * ���͹��ﳵ
		 */
		Cart cart = new Cart();
		request.getSession().setAttribute("cart", cart);
		
		//System.out.println(user.toString());
		//System.out.println(user.getAddressSet().toString());
		
		return "SUCCESS";
	}

	
	/**
	 *  ���ע�Ṧ��
	 */
	public String regist(){
	
		System.out.println("����registAction.....");
		/**
		 * ����Service��regist�������������ݿ⣬�������û������ݿ��С�
		 * ͬʱ�õ�����ֵ--��֤��
		*/
		/*
		 * ��user�е��û��������롢���䡢�ֻ���һΪ��ֵ����request���д洢������Ϣ�����ҷ���NONE��
		 */
		if("".equals(user.getUsername()) || "".equals(user.getPassword()) 
				|| "".equals(user.getPhone()) || "".equals(user.getEmail())){
			System.out.println("���ڿ��ַ�");
			request.setAttribute("pageError", "��������д������Ϣ��");
			return "ERROR";
		}
		/*
		 * ��ȫ�ֱ���usernameError��phoneError��emailError��һ��Ϊ�ա���˵���������������������������ݿ�
		 */
		if("1".equals(usernameError) || "1".equals(phoneError) || "1".equals(emailError)){
			System.out.println("���ڲ���ȷ�ַ�");
			request.setAttribute("pageError", "����ȷ��д������Ϣ��");
			return "ERROR";
		}
		
		code = userService.regist(user);
		//����֤�����request��
		
		//��ת��ע��ɹ�ҳ�档
		return "SUCCESS";
	}
	
	/**
	 * ����ȫ�ֱ���
	 * usernameError
	 * emailError
	 * phoneError
	 * ����regist�������ж��Ƿ�������������⡣��ʼΪ�գ����У���ֵΪ1
	 */
	private String usernameError,emailError,phoneError;
	
	/**
	 * ��֤ע������û����Ƿ�Ϊ��
	 * @return
	 */
	public String confirmUsername(){
		/*
		 * ���ǰ�˴������ڵ�ֵ----�û���
		 */
		String username=request.getParameter("usernameAjax");
		
		User user1 = new User();
		if(username != null){
			user1.setUsername(username);
		}else{
			return NONE;
		}
		
		//��flagΪusername�����Ӧ�����ֵ�ѱ�ע�ᡣ
		String flag = userService.registConfirm(user1);
		//��flag��Ϊ�ա��������д�ֵ
		if (!flag.isEmpty()) {
			if ("1".equals(flag)) {
				try {
					usernameError = "1";
					response.getWriter().print("usernameError");
					System.out.println("web���Ѿ�����֤�������������");
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return NONE;
	}
	
	/**
	 * ��֤ע����������Ƿ�Ϊ��
	 * @return
	 */
	public String confirmEmail(){
		/*
		 * ���ǰ�˴������ڵ�ֵ-----����
		 */
		String email=request.getParameter("emailAjax");
		
		User user1 = new User();
		if(email != null) {
			user1.setEmail(email);
		}else{
			return NONE;
		}
		
		//��flagΪemail�����Ӧ�����ֵ�ѱ�ע�ᡣ
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
	 * ��֤ע������ֻ����Ƿ�Ϊ��
	 * @return
	 */
	public String confirmPhone(){
		/*
		 * ���ǰ�˴������ڵ�ֵ-----�ֻ���
		 */
		String phone=request.getParameter("phoneAjax");
		
		User user1 = new User();
		if(phone != null) {
			user1.setPhone(phone);
		}else{
			return NONE;
		}
		
		//��flagΪphone�����Ӧ�����ֵ�ѱ�ע�ᡣ
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
