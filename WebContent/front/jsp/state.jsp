<%@ page language="java" contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>状态栏</title>
<link rel="stylesheet" type="text/css" href="<c:url value='/front/css/init.css'></c:url>" /> 
<link rel="stylesheet" type="text/css"  href="<c:url value='/front/css/state.css'></c:url>">
<script type="text/javascript" src="<c:url value='/front/js/state.js'></c:url>"></script>
</head>
<body>
	<div id="status">
		<div id="status_text">
			<c:choose>
				<c:when test="${empty sessionScope.user}">
					<a href="#">首页</a>
		            <span>&nbsp;|&nbsp;</span>
					<a href="<c:url value='/front/jsp/login.jsp'></c:url>" >登录</a>
					<span>&nbsp;|&nbsp; </span>
					<a href="<c:url value='/front/jsp/regist.jsp'></c:url>">注册</a>
				</c:when>
				
				<c:when test="${not empty sessionScope.user}">
					<a href="#">首页</a>
		            <span>&nbsp;|&nbsp;</span>
		            <a href="<c:url value='/runToCartAction'/>" target="blank">购物车</a>
		            <span>&nbsp;|&nbsp;</span>
		            <a id="personal" href="#" onmousemove="ShowToop();">个人中心</a>
				</c:when>
			</c:choose>
			&nbsp;&nbsp;
		</div>
	</div>
	
    <div id="personDiv" onmousemove="ShowToop();" onmouseout="CloseToop();">
    	<div class="touxiangDiv">
    		<img class="touxiang" src="<c:url value='/front/images/touxiang.jpg'/>"/>
        </div>
        <div class="usernameDiv">
        	<span class="username">
        		<a href="<c:url value='/front/jsp/login.jsp'></c:url>">
        			<s:property value="#session.user.username"/>
        		</a>
        	</span>
        </div>
        
        <div class="buttonDiv">
        	<span class="button">
        		<a href="<c:url value='/findOrderAction'/>" target="blank">我的订单</a>
        	</span>
        	&nbsp;&nbsp;
        	<span class="button">
        		<a>我的收藏</a>
        	</span>
        	<br /><br />
            <span class="button">
            	<a>我的足迹</a>
            </span>
            <br /><br />
            <span class="button">
            	<a>已购买的宝贝</a>
            </span>
        </div>
    </div> 
	
</body>
</html>