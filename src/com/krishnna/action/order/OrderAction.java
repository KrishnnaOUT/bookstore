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
 * 订单的web层
 * @author Ningkui
 *
 */
public class OrderAction extends ActionSupport implements ModelDriven<Good> {

	/**
	 * 模型驱动封装
	 */
	private Good good = new Good();
	public Good getModel() {
		return good;
	}
	
	/**
	 * 将myOrder集合的实例化对象放入值栈中
	 */
	private List<myOrder> orderList = new ArrayList<myOrder>();
	public List<myOrder> getOrderList() {
		return orderList;
	}
	
	/**
	 * 将myOrder的实例化对象放入值栈中
	 */
	private myOrder order = new myOrder();
	public myOrder getOrder() {
		return order;
	}

	/**
	 * 将查询用户所拥有订单的全部总金额
	 */
	double total = 0;
	public double getTotal() {
		return total;
	}

	/**
	 * 得到request对象
	 */
	HttpServletRequest request = ServletActionContext.getRequest();
	
	/**
	 * 得到response对象
	 */
	HttpServletResponse response = ServletActionContext.getResponse();
	
	/**
	 * 获得OrderService对象
	 */
	private OrderService orderService;
	public void setOrderService(OrderService orderService) {
		this.orderService = orderService;
	}
	
	/**
	 * 提交订单。生成订单
	 * @return
	 */
	public String CommitOrder(){
		
		/**
		 * 1.模型驱动封装获得good信息
		 * 2.在request域中获得购买数量count信息
		 * 3.在session域中获得用户user信息
		 * 	-->若session域中不存在用户信息，则跳转到登录页面
		 * 4.生成订单条目对象。订单对象
		 * 4.调用Service层，更新数据库，添加订单条目和订单。
		 * 5.将order值放入值栈中.方便前端页面跳转
		 * 6.跳转到订单页面
		 */
		
		//2.获得count
		String count = request.getParameter("count");
		//获取directPay判断参数，(直接支付页面提交所带参数)
		String directPay = request.getParameter("directPay");
		//获取inCartPay判断参数，(购物车页面提交所带参数)
		String inCartPay = request.getParameter("inCartPay");
		
		//3.获得user
		User owner = (User) request.getSession().getAttribute("user");
	
		//创建订单实例化对象
		myOrder order = new myOrder();
		
		/*
		 * 4.1 生成订单条目
		 * 	并放入订单条目集合中
		 */
		//创建订单条目集合。
		Set<OrderItem> orderItemList = new HashSet<OrderItem>();

		if(directPay != null && directPay.equals("1")){
			/*
			 * 若directPay其不为空且值为1则为直接支付，即订单直接生成，不通过购物车生成，
			 */
			OrderItem orderItem = new OrderItem();
			orderItem.setIid(commonUtil.getCode());//设置订单条目编号
			orderItem.setCount(Integer.parseInt(count));//设置购买数量
			orderItem.setGood(good);//设置对应购买的图书
			orderItem.setOrder(order);//设置对应的订单
			orderItem.setSubtotal();//设置该订单条目的小计
			
			//放入集合中
			orderItemList.add(orderItem);
		} else if(inCartPay != null && inCartPay.equals("1")){
			/*
			 * 若inCartPay其不为空且值为1则为购物车提交订单，即订单通过购物车生成，
			 */
			//获得车
			Cart cart = (Cart) request.getSession().getAttribute("cart");
			for (CartItem cartItem : cart.getCartItems()) {
				OrderItem orderItem = new OrderItem();
				
				orderItem.setIid(commonUtil.getCode());//设置订单条目编号
				orderItem.setCount(cartItem.getCount());//设置购买数量
				orderItem.setGood(cartItem.getGood());//设置对应购买的图书
				orderItem.setOrder(order);//设置对应的订单
				orderItem.setSubtotal();//设置该订单条目的小计
				
				//放入集合中
				
				orderItemList.add(orderItem);
			}
			
			//清空购物车
			cart.clear();
		} else{
			/*
			 * 若inCartPay和directPay都为空，则说明不是由直接支付页面和购物车页面提交进来的，
			 * 返回空。
			 */
			return "ORDER";
		}
		
		
		/*
		 * 4.2 生成订单 
		 */
		order.setOid(commonUtil.getCode());//设置订单编号
		order.setOrdertime(new Date());//设置订单生成时间
		order.setOwner(owner);//设置订单所有人
		//添加订单条目
		order.setOrderItemList(orderItemList);
		order.setTotal();//设置订单总金额
		
		/*
		 * 将order放入owner中去
		 */
		owner.getOrderSet().add(order);

		//5.调用service。存储进数据库
		orderService.commitOrder(order);
		
		//6.跳转
		return "SUCCESS";
	}
	
	/**
	 * 根据当前登录用户查询其所有订单
	 */
	public String findOrder(){
		
		//1.获得user
		User owner = (User) request.getSession().getAttribute("user");
		
		//2.查询当前登录用户的所有订单，并且将订单集合orderList放入值栈中
		orderList = orderService.findAllOrderByUser(owner);
		
		//计算当前orderList中所有order的总金额
		for (myOrder mo : orderList) {
			total = total + mo.getTotal();
		}
		
		//将total放入值栈中

		return "SUCCESS";
	}
	
	/**
	 * 跳转到选择支付通道页面
	 */
	public String chooseWayForPay(){
		
		/*
		 * 从前端页面获得oid。然后访问数据库，获取该订单的所有信息，并放入值栈中
 		 */
		String oid = request.getParameter("oid");
		
		//调用service层
		order = orderService.findOrderByOid(oid);
		
		System.out.println("order的值："+order.toString());
		
		//将order放入值栈中
		
		return "SUCCESS";
	}
	
	/**
	 * 单个订单付款Action，
	 * 13+1跳转到易宝页面。
	 */
	public String pay() {
		
		/**
		 * 创建变量用于获取文件中的参数。
		 */
		Properties props = new Properties();
		InputStream input = this.getClass().getClassLoader().getResourceAsStream("pay.properties");
		try {
			props.load(input);
		} catch (IOException e) {
			e.printStackTrace();
		}		
		
		//访问第三方平台的网址
		String url = props.getProperty("url");
		/*
		 * 获得13+1参数
		 */
		//业务类型
		String p0_Cmd = props.getProperty("p0_Cmd");
		//商户编号
		String p1_MerId = props.getProperty("p1_MerId");
		//我的订单号
		String p2_Order = request.getParameter("oid");
		//支付金额
		String p3_Amt = "0.01";
		//支付币种
		String p4_Cur = props.getProperty("p4_Cur");
		//商品名称
		String p5_Pid = "";
		//商品种类
		String p6_Pcat = "";
		//商品描述
		String p7_Pdesc = "";
		//商户接受支付成功数据的地址
		String p8_Url = props.getProperty("p8_Url");
		//送货地址
		String p9_SAF = "";
		//商户扩展 
		String pa_MP = "";
		//支付通道
		String pd_FrpId = "CCB-NET";
		//应答机制
		String pr_NeedResponse = props.getProperty("pr_NeedResponse");
		//密钥
		String keyValue = props.getProperty("keyValue");
		
		//密钥+13+1参数生成值
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
		 * 重定向到易宝支付网页
		 */
		try {
			response.sendRedirect(sb.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return NONE;
	}
	
	/**
	 * 易宝支付完成回调方法
	 * 11+1参数
	 */
	public String confirmPayBack(){
		
		//商户编号
		String p1_MerId = request.getParameter("p1_MerId");
		//业务类型
		String r0_Cmd = request.getParameter("r0_Cmd");
		//支付结果
		String r1_Code = request.getParameter("r1_Code");
		//易宝支付交易流水号
		String r2_TrxId = request.getParameter("r2_TrxId");
		//支付金额
		String r3_Amt = request.getParameter("r3_Amt");
		//交易币种
		String r4_Cur = request.getParameter("r4_Cur");
		//商品名称
		String r5_Pid = request.getParameter("r5_Pid");
		//商户订单号
		String r6_Order = request.getParameter("r6_Order");
		//易宝支付会员号ID
		String r7_Uid = request.getParameter("r7_Uid");
		//商户扩展信息
		String r8_MP = request.getParameter("r8_MP");
		//交易结果返回类型
		String r9_BType = request.getParameter("r9_BType");
		//签名数据
		String hmac = request.getParameter("hmac");
		
		/**
		 * 校验是否为易宝支付平台访问
		 */
		Properties props = new Properties();
		InputStream input = this.getClass().getClassLoader().getResourceAsStream("pay.properties");
		try {
			props.load(input);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		String keyValue = props.getProperty("keyValue");
		
		//校验hmac是否相同
		boolean bool = PaymentUtil.verifyCallback(hmac, p1_MerId, r0_Cmd,
				r1_Code, r2_TrxId, r3_Amt, r4_Cur, r5_Pid, r6_Order, 
				r7_Uid, r8_MP, r9_BType, keyValue);
		
		if(!bool){
			//如果校验失败
			return "FAILED";
		}
		
		/*
		 * 获取状态订单。修改订单状态。或者修改积分操作等等
		 */
		//修改订单状态
		orderService.updateOrderState(r6_Order, 2);
		
		/*
		 * 4. 判断当前回调方式
		 *   如果为点对点，需要回馈以success开头的字符串
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
	 * 修改订单状态。并且跳转到对应页面
	 * @return
	 */
	public String updateOrderState(){
		
		/*
		 * 一. 若为商家操作，即将“等待发货”状态修改为“已发货”状态。
		 * 	-->则跳转到发货界面delivery.jsp
		 * 二. 若为买家操作。即将“确认收货”状态修改为“交易成功”状态
		 *  -->则跳转到评分界面giveScore.jsp
		 */
		
		/*
		 * 1. 获取前端传入的订单编号oid
		 */
		String oid = request.getParameter("oid");
		
		/*
		 * 2. 获取并判断前端传入的state值
		 */
		String state = request.getParameter("state");
		if(state != null && "4".equals(state)){
			/*若state为4，即说明为买家操作。*/
			//修改订单状态为“交易成功”
			orderService.updateOrderState(oid, Integer.parseInt(state));
			//返回到评分页面
			return "GIVESCORE";
		}else if(state != null && "4".equals(state)){
			/*若state为3，即说明为卖家操作。*/
			//跳转到发货页面
			return "DELIVERY";
		}
		
		return NONE;
	}
}
