<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>网上书吧-登录</title>
</head>
<body>
<h1>登录</h1>
<hr>
<span id=“pageError”></span>
	<form action="<c:url value='/loginAction'></c:url>"  method="post">
			<span>用户名：</span><input type="text" id="username"  name="username" value="<s:property value='u.username'/>"/><span id="usernameError"></span><br><br>
			<span>密&nbsp;码：</span><input type="password" id="password" name="password"/>&nbsp;<s:property value="#request.passwordError"/><span id="passwordError"></span><br><br>
			<input type="submit" value="登录"/>&nbsp;&nbsp;&nbsp;<input type="reset" value="重置"/>
	</form>
	<br>
	<input type="button" value="注册" src="<c:url value='/registAction'></c:url>"/>	

</body>
</html>