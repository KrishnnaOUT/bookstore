<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">

<link rel="stylesheet" type="text/css"  href="<c:url value='/front/css/payWay.css'></c:url>">
<title>选择支付通道页面</title>
</head>
<body>
	<div id="head">
		<div id="userInfo">
			<c:choose>
				<c:when test="${empty sessionScope.user}">
					<span>
						<a href="<c:url value='/front/jsp/login.jsp'></c:url>" >登录</a>
					</span>
					&nbsp;&nbsp; 
					<span>
						<a href="#">注册</a>
					</span>
				</c:when>
				
				<c:when test="${not empty sessionScope.user}">
					<span>
						<a href="<c:url value='/front/jsp/login.jsp'></c:url>"><s:property value="#session.user.username"/></a>
					</span>
				</c:when>
			</c:choose>
			&nbsp;&nbsp;
		</div>
	</div>
    <div class="bar">
            <div id="logoDiv">
                <img src="<c:url value='/front/images/logo2.png'/>" />
            </div>
            <div>
                <img src="<c:url value='/front/images/order_pay.png'/>" />
            </div>
    </div>
    <div id="detail">
    <form action="<c:url value='/payAction'/>" method="post">
    	<input type="hidden" name="oid" value="${order.oid }"/>
    	<span class="remind">确认订单信息</span>
        <div id="good">
        	<c:forEach items="${order.orderItemList }" var="orderItem">
	        	<div class="goodDetail">
		            <span><img class="goodPicture" src="<c:url value='${orderItem.good.picture }'/>"/></span>
		            <span class="goodName">${orderItem.good.gname }</span>
		            <span class="subprice">￥&nbsp;${orderItem.subtotal }</span>
	            </div>
            </c:forEach>
        </div>
        
        <br />
        <div id="payWay">
            <span class="payWay">支付方式</span><br /><br />
            <div class="bankImg">
                <input type="radio" name="bank" value="ABC-NET"/><img class="bank" src="<c:url value='/front/images/bank/abc.bmp'/>"/>&nbsp;&nbsp;&nbsp;
                <input type="radio" name="bank" value="BOC-NET"/><img class="bank" src="<c:url value='/front/images/bank/bc.bmp'/>"/>&nbsp;&nbsp;&nbsp;
                <input type="radio" name="bank" value="BOCO-NET"/><img class="bank" src="<c:url value='/front/images/bank/bcc.bmp'/>"/><br /><br />
                <input type="radio" name="bank" value="CCB-NET" checked="checked"/><img class="bank" src="<c:url value='/front/images/bank/ccb.bmp'/>"/>&nbsp;&nbsp;&nbsp;
                <input type="radio" name="bank" value="CMBCHINA-NET"/><img class="bank" src="<c:url value='/front/images/bank/cmb.bmp'/>"/>&nbsp;&nbsp;&nbsp;
                <input type="radio" name="bank" value="ICBC-NET"/><img class="bank" src="<c:url value='/front/images/bank/icbc.bmp'/>"/><br /><br />
                <input type="radio" name="bank" value="POST-NET"/><img class="bank" src="<c:url value='/front/images/bank/post.bmp'/>"/>&nbsp;&nbsp;&nbsp;
            </div>
        </div>
        <br /><br />
        
        <div id="importInfo">
        	<span class="ar">￥&nbsp;</span>
        	<span class="price">${order.total }</span><br/><br/>
        	
        	<span class="ar">寄送至：</span><span class="address">${order.address }</span><br/>
        	<span class="ar">收货人：</span><span class="receiver">${user.username }&nbsp;${user.phone }</span><br/>
        </div>
        
        <input id="payButton" class="selectButton" type="submit" value="确认付款"/>
    </form>
    </div>
</body>
</html>