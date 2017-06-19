<%@ page language="java" contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>头部</title>

<link rel="stylesheet" type="text/css"  href="<c:url value='/front/css/head.css'></c:url>">
</head>
<body>
	<div id="head">
		<form action="<c:url value='/searchAction'/>" method="post">
			<div id="logo">
				<img src="<c:url value='/front/images/logo2.png'></c:url>" />
			</div>
			<div id="search">
				<input id="searchBox" name="keyword" type="text" value="请输入书名/作者" /> 
				<input id="searchButton" type="submit" value="搜索"/>
			</div>
		</form>
	</div>
	 <br />
	<div id="bar">
        	<ul>
            	<li id="firstLi">全部图书分类</li>
                <li><a href="#">今日精选</a></li>
                <li><a href="#">天天特价</a></li>
                <li><a href="#">新书速递</a></li>
                <li><a href="#">图书漂流</a></li>
            </ul>
    </div>
</body>
</html>