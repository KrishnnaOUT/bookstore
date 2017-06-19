package com.krishnna.action.order;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.krishnna.entity.Cart;
import com.krishnna.entity.CartItem;
import com.krishnna.entity.Good;
import com.krishnna.entity.OrderItem;
import com.krishnna.entity.User;
import com.krishnna.entity.myOrder;
import com.krishnna.service.order.OrderService;
import com.krishnna.util.commonUtil;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

/**
 * ������web��
 * @author Ningkui
 *
 */
public class OrderAction extends ActionSupport implements ModelDriven<Good> {

	/**
	 * ģ��������װ
	 */
	private Good good = new Good();
	public Good getModel() {
		return good;
	}
	
	/**
	 * ��myOrder���ϵ�ʵ�����������ֵջ��
	 */
	private List<myOrder> orderList = new ArrayList<myOrder>();
	public List<myOrder> getOrderList() {
		return orderList;
	}
	
	/**
	 * ��myOrder��ʵ�����������ֵջ��
	 */
	private myOrder order = new myOrder();
	public myOrder getOrder() {
		return order;
	}

	/**
	 * ����ѯ�û���ӵ�ж�����ȫ���ܽ��
	 */
	double total = 0;
	public double getTotal() {
		return total;
	}

	/**
	 * �õ�request����
	 */
	HttpServletRequest request = ServletActionContext.getRequest();
	
	/**
	 * �õ�response����
	 */
	HttpServletResponse response = ServletActionContext.getResponse();
	
	/**
	 * ���OrderService����
	 */
	private OrderService orderService;
	public void setOrderService(OrderService orderService) {
		this.orderService = orderService;
	}
	
	/**
	 * �ύ���������ɶ���
	 * @return
	 */
	public String CommitOrder(){
		
		/**
		 * 1.ģ��������װ���good��Ϣ
		 * 2.��request���л�ù�������count��Ϣ
		 * 3.��session���л���û�user��Ϣ
		 * 	-->��session���в������û���Ϣ������ת����¼ҳ��
		 * 4.���ɶ�����Ŀ���󡣶�������
		 * 4.����Service�㣬�������ݿ⣬��Ӷ�����Ŀ�Ͷ�����
		 * 5.��orderֵ����ֵջ��.����ǰ��ҳ����ת
		 * 6.��ת������ҳ��
		 */
		
		//2.���count
		String count = request.getParameter("count");
		//��ȡdirectPay�жϲ�����(ֱ��֧��ҳ���ύ��������)
		String directPay = request.getParameter("directPay");
		//��ȡinCartPay�жϲ�����(���ﳵҳ���ύ��������)
		String inCartPay = request.getParameter("inCartPay");
		
		//3.���user
		User owner = (User) request.getSession().getAttribute("user");
	
		//��������ʵ��������
		myOrder order = new myOrder();
		
		/*
		 * 4.1 ���ɶ�����Ŀ
		 * 	�����붩����Ŀ������
		 */
		//����������Ŀ���ϡ�
		Set<OrderItem> orderItemList = new HashSet<OrderItem>();

		if(directPay != null && directPay.equals("1")){
			/*
			 * ��directPay�䲻Ϊ����ֵΪ1��Ϊֱ��֧����������ֱ�����ɣ���ͨ�����ﳵ���ɣ�
			 */
			OrderItem orderItem = new OrderItem();
			orderItem.setIid(commonUtil.getCode());//���ö�����Ŀ���
			orderItem.setCount(Integer.parseInt(count));//���ù�������
			orderItem.setGood(good);//���ö�Ӧ�����ͼ��
			orderItem.setOrder(order);//���ö�Ӧ�Ķ���
			orderItem.setSubtotal();//���øö�����Ŀ��С��
			
			//���뼯����
			orderItemList.add(orderItem);
		} else if(inCartPay != null && inCartPay.equals("1")){
			/*
			 * ��inCartPay�䲻Ϊ����ֵΪ1��Ϊ���ﳵ�ύ������������ͨ�����ﳵ���ɣ�
			 */
			//��ó�
			Cart cart = (Cart) request.getSession().getAttribute("cart");
			for (CartItem cartItem : cart.getCartItems()) {
				OrderItem orderItem = new OrderItem();
				
				orderItem.setIid(commonUtil.getCode());//���ö�����Ŀ���
				orderItem.setCount(cartItem.getCount());//���ù�������
				orderItem.setGood(cartItem.getGood());//���ö�Ӧ�����ͼ��
				orderItem.setOrder(order);//���ö�Ӧ�Ķ���
				orderItem.setSubtotal();//���øö�����Ŀ��С��
				
				//���뼯����
				
				orderItemList.add(orderItem);
			}
			
			//��չ��ﳵ
			cart.clear();
		} else{
			/*
			 * ��inCartPay��directPay��Ϊ�գ���˵��������ֱ��֧��ҳ��͹��ﳵҳ���ύ�����ģ�
			 * ���ؿա�
			 */
			return "ORDER";
		}
		
		
		/*
		 * 4.2 ���ɶ��� 
		 */
		order.setOid(commonUtil.getCode());//���ö������
		order.setOrdertime(new Date());//���ö�������ʱ��
		order.setOwner(owner);//���ö���������
		//��Ӷ�����Ŀ
		order.setOrderItemList(orderItemList);
		order.setTotal();//���ö����ܽ��
		
		/*
		 * ��order����owner��ȥ
		 */
		owner.getOrderSet().add(order);

		//5.����service���洢�����ݿ�
		orderService.commitOrder(order);
		
		//6.��ת
		return "SUCCESS";
	}
	
	/**
	 * ���ݵ�ǰ��¼�û���ѯ�����ж���
	 */
	public String findOrder(){
		
		//1.���user
		User owner = (User) request.getSession().getAttribute("user");
		
		//2.��ѯ��ǰ��¼�û������ж��������ҽ���������orderList����ֵջ��
		orderList = orderService.findAllOrderByUser(owner);
		
		//���㵱ǰorderList������order���ܽ��
		for (myOrder mo : orderList) {
			total = total + mo.getTotal();
		}
		
		//��total����ֵջ��

		return "SUCCESS";
	}
	
	/**
	 * ��ת��ѡ��֧��ͨ��ҳ��
	 */
	public String chooseWayForPay(){
		
		/*
		 * ��ǰ��ҳ����oid��Ȼ��������ݿ⣬��ȡ�ö�����������Ϣ��������ֵջ��
 		 */
		String oid = request.getParameter("oid");
		
		//����service��
		order = orderService.findOrderByOid(oid);
		
		System.out.println("order��ֵ��"+order.toString());
		
		//��order����ֵջ��
		
		return "SUCCESS";
	}
	
	/**
	 * ������������Action��
	 * 13+1��ת���ױ�ҳ�档
	 */
	public String pay() {
		
		/**
		 * �����������ڻ�ȡ�ļ��еĲ�����
		 */
		Properties props = new Properties();
		InputStream input = this.getClass().getClassLoader().getResourceAsStream("pay.properties");
		try {
			props.load(input);
		} catch (IOException e) {
			e.printStackTrace();
		}		
		
		//���ʵ�����ƽ̨����ַ
		String url = props.getProperty("url");
		/*
		 * ���13+1����
		 */
		//ҵ������
		String p0_Cmd = props.getProperty("p0_Cmd");
		//�̻����
		String p1_MerId = props.getProperty("p1_MerId");
		//�ҵĶ�����
		String p2_Order = request.getParameter("oid");
		//֧�����
		String p3_Amt = "0.01";
		//֧������
		String p4_Cur = props.getProperty("p4_Cur");
		//��Ʒ����
		String p5_Pid = "";
		//��Ʒ����
		String p6_Pcat = "";
		//��Ʒ����
		String p7_Pdesc = "";
		//�̻�����֧���ɹ����ݵĵ�ַ
		String p8_Url = props.getProperty("p8_Url");
		//�ͻ���ַ
		String p9_SAF = "";
		//�̻���չ 
		String pa_MP = "";
		//֧��ͨ��
		String pd_FrpId = "CCB-NET";
		//Ӧ�����
		String pr_NeedResponse = props.getProperty("pr_NeedResponse");
		//��Կ
		String keyValue = props.getProperty("keyValue");
		
		//��Կ+13+1��������ֵ
		String hmac = PaymentUtil.buildHmac(p0_Cmd, p1_MerId, p2_Order, p3_Amt, 
				p4_Cur, p5_Pid, p6_Pcat, p7_Pdesc, p8_Url, p9_SAF,
				pa_MP, pd_FrpId, pr_NeedResponse, keyValue);
		
		StringBuilder sb = new StringBuilder(url);
		sb.append("?p0_Cmd=").append(p0_Cmd);
		sb.append("&p1_MerId=").append(p1_MerId);
		sb.append("&p2_Order=").append(p2_Order);
		sb.append("&p3_Amt=").append(p3_Amt);
		sb.append("&p4_Cur=").append(p4_Cur);
		sb.append("&p5_Pid=").append(p5_Pid);
		sb.append("&p6_Pcat=").append(p6_Pcat);
		sb.append("&p7_Pdesc=").append(p7_Pdesc);
		sb.append("&p8_Url=").append(p8_Url);
		sb.append("&p9_SAF=").append(p9_SAF);
		sb.append("&pa_MP=").append(pa_MP);
		sb.append("&pd_FrpId=").append(pd_FrpId);
		sb.append("&pr_NeedResponse=").append(pr_NeedResponse);
		sb.append("&hmac=").append(hmac);
		
		/*
		 * �ض����ױ�֧����ҳ
		 */
		try {
			response.sendRedirect(sb.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return NONE;
	}
	
	/**
	 * �ױ�֧����ɻص�����
	 * 11+1����
	 */
	public String confirmPayBack(){
		
		//�̻����
		String p1_MerId = request.getParameter("p1_MerId");
		//ҵ������
		String r0_Cmd = request.getParameter("r0_Cmd");
		//֧�����
		String r1_Code = request.getParameter("r1_Code");
		//�ױ�֧��������ˮ��
		String r2_TrxId = request.getParameter("r2_TrxId");
		//֧�����
		String r3_Amt = request.getParameter("r3_Amt");
		//���ױ���
		String r4_Cur = request.getParameter("r4_Cur");
		//��Ʒ����
		String r5_Pid = request.getParameter("r5_Pid");
		//�̻�������
		String r6_Order = request.getParameter("r6_Order");
		//�ױ�֧����Ա��ID
		String r7_Uid = request.getParameter("r7_Uid");
		//�̻���չ��Ϣ
		String r8_MP = request.getParameter("r8_MP");
		//���׽����������
		String r9_BType = request.getParameter("r9_BType");
		//ǩ������
		String hmac = request.getParameter("hmac");
		
		/**
		 * У���Ƿ�Ϊ�ױ�֧��ƽ̨����
		 */
		Properties props = new Properties();
		InputStream input = this.getClass().getClassLoader().getResourceAsStream("pay.properties");
		try {
			props.load(input);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		String keyValue = props.getProperty("keyValue");
		
		//У��hmac�Ƿ���ͬ
		boolean bool = PaymentUtil.verifyCallback(hmac, p1_MerId, r0_Cmd,
				r1_Code, r2_TrxId, r3_Amt, r4_Cur, r5_Pid, r6_Order, 
				r7_Uid, r8_MP, r9_BType, keyValue);
		
		if(!bool){
			//���У��ʧ��
			return "FAILED";
		}
		
		/*
		 * ��ȡ״̬�������޸Ķ���״̬�������޸Ļ��ֲ����ȵ�
		 */
		//�޸Ķ���״̬
		orderService.updateOrderState(r6_Order, 2);
		
		/*
		 * 4. �жϵ�ǰ�ص���ʽ
		 *   ���Ϊ��Ե㣬��Ҫ������success��ͷ���ַ���
		 */
		if(r9_BType.equals("2")) {
			try {
				response.getWriter().print("success");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return "SUCCESS";
	}
	
	/**
	 * �޸Ķ���״̬��������ת����Ӧҳ��
	 * @return
	 */
	public String updateOrderState(){
		
		/*
		 * һ. ��Ϊ�̼Ҳ������������ȴ�������״̬�޸�Ϊ���ѷ�����״̬��
		 * 	-->����ת����������delivery.jsp
		 * ��. ��Ϊ��Ҳ�����������ȷ���ջ���״̬�޸�Ϊ�����׳ɹ���״̬
		 *  -->����ת�����ֽ���giveScore.jsp
		 */
		
		/*
		 * 1. ��ȡǰ�˴���Ķ������oid
		 */
		String oid = request.getParameter("oid");
		
		/*
		 * 2. ��ȡ���ж�ǰ�˴����stateֵ
		 */
		String state = request.getParameter("state");
		if(state != null && "4".equals(state)){
			/*��stateΪ4����˵��Ϊ��Ҳ�����*/
			//�޸Ķ���״̬Ϊ�����׳ɹ���
			orderService.updateOrderState(oid, Integer.parseInt(state));
			//���ص�����ҳ��
			return "GIVESCORE";
		}else if(state != null && "4".equals(state)){
			/*��stateΪ3����˵��Ϊ���Ҳ�����*/
			//��ת������ҳ��
			return "DELIVERY";
		}
		
		return NONE;
	}
}
