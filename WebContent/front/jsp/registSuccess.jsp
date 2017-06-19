<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>网上书吧-注册成功</title>
</head>
<body>
<h1>注册成功</h1>
<hr>
<span id=“pageError”></span>
	<br>
	<s:debug />
	<span>请前往您的邮箱</span>
			<a href="#"><s:property value="email"/></a>
			<span>进行验证。</span>
	<br>
	<span id = "info"  name = "info" ><s:property value="u.code"/></span>
</body>
</html>