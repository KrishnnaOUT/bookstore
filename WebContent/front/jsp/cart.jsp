<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">

<link rel="stylesheet" type="text/css"  href="<c:url value='/front/css/cart.css'></c:url>">

<title>购物车页面</title>

<script type="text/javascript">
window.onload=function(){
	
	var commitOrderButton = document.getElementById("commitOrder");
	commitOrderButton.onclick=function(){
		document.model.action="<c:url value='/commitOrderAction&inCartPay=1'/>"
	};
}
</script>

</head>
<body>
	
	<c:import url="/front/jsp/state.jsp"></c:import>

	<div class="bar">
		<div id="logoDiv">
			<img src="<c:url value='/front/images/logo2.png'></c:url>" />
		</div>
		<div>
			<img src="<c:url value='/front/images/addCart.png'></c:url>" />
		</div>
	</div>
	
	<div id="main">
		
		<form action="<c:url value='/commitOrderAction'/>" method="post">
			<input type="hidden" name="inCartPay" value="1">
		
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

		<!-- 循环开始 -->
		<c:forEach items="${sessionScope.cart.cartItems }" var="cartItem">
		<div class="text_storeName_div">
            <div><img class="store_logo" src="<c:url value='/front/images/storeLogo/store_logo.jpg'></c:url>"/></div>
           	<div class="text_storeName"><span>${cartItem.good.storeName }</span></div>
        </div>
            
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
                                <a href="<c:url value='goodDetailAction?gid=${cartItem.good.gid }'/>">
                                    <img class="good_picture" alt="商品图片" src="<c:url value='${cartItem.good.picture }'></c:url>"/>
                                </a>
                            </div>
                            <div class="good_gname_rights">
                                <div class="good_gname_div">
                                    <span>${cartItem.good.gname }</span>
                                </div>
                                <br />
                            	<div class="good_rights_div_div">
                                    <img class="store_logo" src="<c:url value='/front/images/seven.png'></c:url>"/>
                                    <img class="store_logo" src="<c:url value='/front/images/protect.png'></c:url>"/>
                                </div>
                            </div>
                            
                            <div class="good_size_div">
                            	<div class="good_size_div_div">
                                	<span class="description">${cartItem.good.briefing }</span>
                                </div>
                            </div>
                        </li>
                    </div>
                    
                    <div class="text_goodDetail_div">
						<li class="price_li">
							<div class="price_div">
								<span class="price">￥${cartItem.good.price }</span>
							</div>
						</li>
                    </div>
                    
                    <div class="text_goodDetail_div">
						<li class="count_li">
							<div class="count_div">
								<a class="adjust_count add_count" href="#">-</a>
								<input class="count" type="text" value="${cartItem.count }"/>
								<a class="adjust_count sub_count" href="#">+</a>
							</div>
						</li>
                    </div>
                    
                    <div class="text_goodDetail_div">
						<li class="subtotal_li">
							<div class="subtotal_div">
								<span class="price">￥<fmt:formatNumber value="${cartItem.good.price*cartItem.count }" pattern="#0.00"/></span>
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

		<div class="buyButton">
			<input type="submit" id="commitOrder" class="selectButton" value="确认购买"></input>
		</div>
		
		</form>
	</div>
</body>
</html>