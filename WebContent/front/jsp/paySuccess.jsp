<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">

<link rel="stylesheet" type="text/css"  href="<c:url value='/front/css/payWay.css'></c:url>">
<title>支付成功</title>
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
                <img src="<c:url value='/front/images/order_success.png'/>" />
            </div>
    </div>
    <div id="detail">
		<span class="paySuccess">支付成功！</span>&nbsp;&nbsp;&nbsp;
		<span class="backOrder"><a href="<c:url value='/findOrderAction'/>">点击这里</a>返回到订单页面</span>
    </div>
</body>
</html>