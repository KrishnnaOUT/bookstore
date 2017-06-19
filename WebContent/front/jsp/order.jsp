<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">

<link rel="stylesheet" type="text/css"  href="<c:url value='/front/css/cart.css'></c:url>">

<title>订单页面</title>

</head>
<body>
	
	<c:import url="/front/jsp/state.jsp"></c:import>

	<div class="bar">
		<div id="logoDiv">
			<img src="<c:url value='/front/images/logo2.png'></c:url>" />
		</div>
		<div>
			<img src="<c:url value='/front/images/order_confirm.png'></c:url>" />
		</div>
	</div>

	<div id="main">
	
		<div class="table_head">
			<div class="text_goodInfo_div goodInfo">
				<div class="text_goodInfo">
					<span>商品信息</span>
				</div>
			</div>
			<div class="text_goodInfo_div">
				<div class="text_goodInfo">
					<span>单价</span>
				</div>
			</div>
			<div class="text_goodInfo_div">
				<div class="text_goodInfo">
					<span>数量</span>
				</div>
			</div>
			<div class="text_goodInfo_div">
				<div class="text_goodInfo">
					<span>金额</span>
				</div>
			</div>
			<div class="text_goodInfo_div">
				<div class="text_goodInfo">
					<span>操作</span>
				</div>
			</div>
		</div>
		
		<c:forEach items="${orderList }" var="order">
			<div class="table_head">
				<div class="text_orderInfo_div text_orderId">
	            	<div class="text_orderInfo">
	            	<span>订单编号：${order.oid }</span>
	                </div>
	            </div>
				<div class="text_orderInfo_div text_ordertime">
	            	<div class="text_orderInfo">
	            	<span>下订单时间：${order.ordertime }</span>
	                </div>
	            </div>
	            <div class="text_orderInfo_div">
	            	<div class="text_orderInfo">
	            	<span>订单金额：￥ <span class="price">${order.total }</span>
	                </div>
	            </div>
	            <div class="text_orderInfo_div">
	            	<div class="text_orderInfo">
	            	<span>
	            		<c:choose>
							<c:when test="${order.state eq 1 }">
								<a href="<c:url value='/chooseWayForPayAction?oid=${order.oid }'/>" target="blank" color="red">未付款</a>
							</c:when>
							<c:when test="${order.state eq 2 }">
								<span>等待发货</span>
							</c:when>
							<c:when test="${order.state eq 3 }">
								<a href="<c:url value='/updateOrderStateAction?oid=${order.oid }&state=4'/>" target="blank">确认收货</a>
							</c:when>
							<c:when test="${order.state eq 4 }">
								<span>交易成功</span>
							</c:when>
						</c:choose>
	            	</span>
	                </div>
	            </div>
			</div>
		
			<!-- 循环开始 -->
			<c:forEach items="${order.orderItemList }" var="orderItem">
	            
	        <div class="good_detail">
				<div>
					<ul>
	                	<div class="text_goodDetail_div goodDetail">
	                        <li class="good_checkbox_li">
	                            <div class="good_checkbox_div">
	                                <input type="checkbox" name="good_checkbox">
	                            </div>
	                        </li>
	                        
	                        <li class="good_mainInfo_li">
	                            <div class="good_picture_div">
	                                <a href="<c:url value='/goodDetailAction?gid=${orderItem.good.gid }'/>">
	                                    <img class="good_picture" alt="商品图片" src="<c:url value='${orderItem.good.picture }'/>"/>
	                                </a>
	                            </div>
	                            <div class="good_gname_rights">
	                                <div class="good_gname_div">
	                                    <span>${orderItem.good.gname }</span>
	                                </div>
	                                <br />
	                            	<div class="good_rights_div_div">
	                                    <img class="store_logo" src="<c:url value='/front/images/seven.png'></c:url>"/>
	                                    <img class="store_logo" src="<c:url value='/front/images/protect.png'></c:url>"/>
	                                </div>
	                            </div>
	                            
	                            <div class="good_size_div">
	                            	<div class="good_size_div_div">
	                                	<span class="description">${orderItem.good.briefing }</span>
	                                </div>
	                            </div>
	                        </li>
	                    </div>
	                    
	                    <div class="text_goodDetail_div price_div">
							<li class="price_li">
								<div class="price_div">
									<span class="price">￥${orderItem.good.price }</span>
								</div>
							</li>
	                    </div>
	                    
	                    <div class="text_goodDetail_div">
							<li class="count_li">
								<div class="count_div">
									<a class="adjust_count add_count" href="#">-</a>
									<input class="count" type="text" value="${orderItem.count }"/>
									<a class="adjust_count sub_count" href="#">+</a>
								</div>
							</li>
	                    </div>
	                    
	                    <div class="text_goodDetail_div">
							<li class="subtotal_li">
								<div class="subtotal_div">
									<span class="price">￥${orderItem.subtotal }</span>
								</div>
							</li>
	                    </div>
	                    
	                    <div class="text_goodDetail_div">
						<li class="operator_li">
							<div class="operator_div">
								<a href="#">加入收藏夹</a><br>
								<a href="#">删除</a>
							</div>
						</li>
	                    </div>
					</ul>
				</div>
			</div>
			</c:forEach>
			<!-- 循环结束 -->
		</c:forEach>

		<div class="buyButton">
			<input type="submit" class="selectButton"
				src="<c:url value='/commitOrderAction&inCartPay=1'/>" value="确认购买"></input>
		</div>
	</div>

</body>
</html>