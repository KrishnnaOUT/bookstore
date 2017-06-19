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

function registAjax(){
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
				} else {
					span.innerHTML = "";
				}
			}
		};
	};
	
	
	//获取用户名框对象。
	var emailEle = document.getElementById("email");
	//给该用户名文本框添加失去焦点监听
	emailEle.onblur = function(){
		
		/*
		 *将id为emailError的value清空
		 */
		//获得span的DOM对象
		var span = document.getElementById("emailError");
		span.innerHTML = "";
		
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
				} else {
					span.innerHTML = "";
				}
			}
		};
	};
	
	
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
			
				if(text == "phoneError")
				{
					span.innerHTML = "手机号已被注册";
				} else {
					span.innerHTML = "";
				}
			}
		};
	};
};
