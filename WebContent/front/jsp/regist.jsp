<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>网上书吧-注册</title>

<link rel="stylesheet" type="text/css"  href="<c:url value='/front/css/regist.css'></c:url>">

<script type="text/javascript">
//创建异步对象
function createXMLHttpRequest() {
	try {
		return new XMLHttpRequest();//大多数浏览器
	} catch (e) {
		try {
			return ActvieXObject("Msxml2.XMLHTTP");//IE6.0
		} catch (e) {
			try {
				return ActvieXObject("Microsoft.XMLHTTP");//IE5.5及更早版本	
			} catch (e) {
				alert("哥们儿，您用的是什么浏览器啊？");
				throw e;
			}
		}
	}
}

window.onload = function(){
	
	/**
	 * 验证用户名
	 */
	//获取用户名框对象。
	var usernameEle = document.getElementById("username");
	//给该用户名文本框添加失去焦点监听
	usernameEle.onblur = function(){
		
		/*
		 *将id为usernameError的value清空
		 */
		//获得span的DOM对象
		var span = document.getElementById("usernameError");
		span.innerHTML = "";
		/**
		 * 若id为username的节点value为空。则输出“用户名不能为空”
		 */
		if(usernameEle.value == ""){
			span.innerHTML = "用户名不能为空";
		}
		
		/*四步*/
		//获取XMLHttpRequest对象s
		var xmlHttp = createXMLHttpRequest();
		
		//获得服务器的连接
		xmlHttp.open("POST","<c:url value='/confirmUsernameAction' />",true);
		
		//设置请求头
		xmlHttp.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
		
		//发送请求
		xmlHttp.send("usernameAjax="+usernameEle.value);
		
		//为xmlHttp注册onreadystatechange监听
		xmlHttp.onreadystatechange = function(){
			if(xmlHttp.readyState == 4 && xmlHttp.status == 200){
				//双重判断
				//获得响应内容
				var text = xmlHttp.responseText;
				
				if(text == "usernameError")
				{
					span.innerHTML = "用户名已被注册";
				}
			}
		};
	};
	
	/**
	 * 验证密码
	 */
	//获取密码框对象。
	var passwordEle = document.getElementById("password");
	//给该密码文本框添加失去焦点监听
	passwordEle.onblur = function(){
		
		/*
		 *将id为passwordError的value清空
		 */
		//获得span的DOM对象
		var span = document.getElementById("passwordError");
		span.innerHTML = "";
		/**
		 * 若id为password的节点value为空。则输出“密码不能为空”
		 */
		if(passwordEle.value == ""){
			span.innerHTML = "密码不能为空";
		}
		
		/**
		 * 若id为password的节点value长度低于8位。则输出“密码长度应为  8~16位数字，下划线和字母组成”
		 */
		if(passwordEle.value.length < 8 || passwordEle.value.length > 16){
			span.innerHTML = "密码长度应为  8~16位 由数字、下划线和字母组成";
		}
	};
	
	/**
	 * 验证两次输入密码是否一致
	 */
	//获取密码框对象。
	var passwordConfirmEle = document.getElementById("passwordConfirm");
	//给该密码文本框添加失去焦点监听
	passwordConfirmEle.onblur = function(){
		
		/*
		 *将id为passwordConfirmError的value清空
		 */
		//获得span的DOM对象
		var span = document.getElementById("passwordConfirmError");
		span.innerHTML = "";
		/**
		 * 若id为password的节点value与id为passwordConfirmd额节点value值不一样。
		 * 则输出“两次密码输入不一致”
		 */
		if(passwordEle.value != passwordConfirmEle.value){
			span.innerHTML = "两次密码输入不一致";
		}
	};
	
	/**
	 * 验证邮箱
	 */
	//获取邮箱框对象。
	var emailEle = document.getElementById("email");
	//给该用户名文本框添加失去焦点监听
	emailEle.onblur = function(){
		
		/*
		 *将id为emailError的value清空
		 */
		//获得span的DOM对象
		var span = document.getElementById("emailError");
		span.innerHTML = "";
		/**
		 * 若id为email的节点value为空。则输出“邮箱不能为空”
		 */
		if(emailEle.value == ""){
			span.innerHTML = "邮箱不能为空";
		}
		
		/*四步*/
		//获取XMLHttpRequest对象s
		var xmlHttp = createXMLHttpRequest();
		
		//获得服务器的连接
		xmlHttp.open("POST","<c:url value='/confirmEmailAction' />",true);
		
		//设置请求头
		xmlHttp.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
		
		//发送请求
		xmlHttp.send("emailAjax="+emailEle.value);
		
		//为xmlHttp注册onreadystatechange监听
		xmlHttp.onreadystatechange = function(){
			if(xmlHttp.readyState == 4 && xmlHttp.status == 200){
				//双重判断
				//获得响应内容
				var text = xmlHttp.responseText;
				
				if(text == "emailError")
				{
					span.innerHTML = "邮箱已被注册";
				}
			}
		};
	};
	
	/**
	 * 验证手机号
	 */
	//获取手机号框对象。
	var phoneEle = document.getElementById("phone");
	//给该手机号文本框添加失去焦点监听
	phoneEle.onblur = function(){
		
		/*
		 *将id为phoneError的value清空
		 */
		//获得span的DOM对象
		var span = document.getElementById("phoneError");
		span.innerHTML = "";
		/**
		 * 若id为phone的节点value为空。则输出“用户名不能为空”
		 */
		if(phoneEle.value == ""){
			span.innerHTML = "手机号不能为空";
		}
		
		/*四步*/
		//获取XMLHttpRequest对象s
		var xmlHttp = createXMLHttpRequest();
		
		//获得服务器的连接
		xmlHttp.open("POST","<c:url value='/confirmPhoneAction' />",true);
		
		//设置请求头
		xmlHttp.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
		
		//发送请求
		xmlHttp.send("phoneAjax="+phoneEle.value);
		
		//为xmlHttp注册onreadystatechange监听
		xmlHttp.onreadystatechange = function(){
			if(xmlHttp.readyState == 4 && xmlHttp.status == 200){
				//双重判断
				//获得响应内容
				var text = xmlHttp.responseText;
			
				if(text == "phoneError" && text != null)
				{
					span.innerHTML = "手机号已被注册";
				}
			}
		};
	};
};


/**
 * 表单提交前验证
 */
function confirmForm(){
	
	var username = document.getElementById("usernameError").innerHTML
	var password = document.getElementById("passwordError").innerHTML
	var passwordConfirm = document.getElementById("passwordConfirmError").innerHTML
	var email = document.getElementById("emailError").innerHTML
	var phone = document.getElementById("phoneError").innerHTML
	//若以上的变量中的任一一个的innerHTML不为空。则不会提交表单到registAction中
	if(username != "" || password != "" || passwordConfirm != "" || email != "" || phone != ""){
		//将错误信息显示在id为pageError中
		var pageError = document.getElementById('pageError');
		pageError.innerHTML = "请完整填好以下信息方能提交注册。"
	}
}
</script>

</head>
<body>
<h1>注册</h1>
<s:debug></s:debug>
<hr>
<span id="pageError" class="error"><s:property value="#request.pageError"/></span>
	<form action="<c:url value='/registAction'/>" method="post">
			<span>用      户     名：</span><input type="text" id="username"  name="username" value="<s:property value='u.username'/>"/><span id="usernameError" class="error"></span><br><br>
			<span>设 置 密 码：</span><input type="password" id="password" name="password"/>&nbsp;<s:property value="#request.passwordError"/><span id="passwordError" class="error"></span><br><br>
			<span>确 认 密 码：</span><input type="password" id="passwordConfirm" name="passwordConfirm"/>&nbsp;<s:property value="#request.passwordConfirmError"/><span id="passwordConfirmError" class="error"></span><br><br>
			<span>中  国   0086：</span><input type="text" id="phone"  name="phone" value="<s:property value=''/>"/><span id="phoneError" class="error"></span><br><br>
			<span>邮              箱：</span><input type="text" id="email"  name="email" value="<s:property value=''/>"/><span id="emailError" class="error"></span><br><br>
			
			<input type="submit" value="注册"/>
	</form>

</body>
</html>