<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-9">
<title>网上书吧-主页-Krishnna</title>
<link rel="stylesheet" type="text/css"  href="<c:url value='/front/css/init.css'></c:url>">
<link rel="stylesheet" type="text/css"  href="<c:url value='/front/css/main.css'></c:url>">


</head>
<body>

<div id="all">
	<c:import url="/front/jsp/state.jsp"></c:import>
	
	<div id="main">
	
	<c:import url="/front/jsp/head.jsp"></c:import>
	
        <div id="centerPart">
            <div id="centerPart_left_bar">
                <div class="classText">
                	<div class="bigclass">
                    	<a class="bar_text" href="<c:url value='/searchAction?good_BigClass=文学随笔'/>">文学随笔</a>
                    </div>
                    
                    <div class="detailclass">
                    	<span class="bar_text_span">
                    		<a class="bar_text" href="<c:url value='/searchAction?good_SmallClass=古典文学'/>">古典文学</a> / 
                    		<a class="bar_text" href="<c:url value='/searchAction?good_SmallClass=现代文学'/>">现代文学</a> / 
                    		<a class="bar_text" href="<c:url value='/searchAction?good_SmallClass=外国文学'/>">外国文学</a> / 
                    		<a class="bar_text" href="<c:url value='/searchAction?good_SmallClass=国学'/>">国学</a> / <a 
                    		class="bar_text"  href="<c:url value='/searchAction?good_SmallClass=个人随笔'/>">个人随笔</a> / 
                    		<a class="bar_text" href="<c:url value='/searchAction?good_SmallClass=人物传记'/>">人物传记</a>
                    	</span>
                    </div>
                </div>
                
                 <div class="classText">
                	<div class="bigclass">
                    	<a class="bar_text" href="<c:url value='/searchAction?good_BigClass=网络文学'/>">网络文学</a>
                    </div>
                    
                    <div class="detailclass">
                    	<span class="bar_text_span">
                    		<a class="bar_text" href="<c:url value='/searchAction?good_SmallClass=玄幻'/>">玄幻</a> / 
                    		<a class="bar_text" href="<c:url value='/searchAction?good_SmallClass=悬疑'/>">悬疑</a> / 
                    		<a class="bar_text" href="<c:url value='/searchAction?good_SmallClass=科幻'/>">科幻</a> / 
                    		<a class="bar_text" href="<c:url value='/searchAction?good_SmallClass=都市'/>">都市</a> / 
                    		<a class="bar_text"  href="<c:url value='/searchAction?good_SmallClass=战争'/>">战争</a> / 
                    		<a class="bar_text" href="<c:url value='/searchAction?good_SmallClass=穿越'/>">穿越</a>
                    	</span>
                    </div>
                </div>
                
                 <div class="classText">
                	<div class="bigclass">
                    	<a class="bar_text" href="<c:url value='/searchAction?good_BigClass=启蒙趣味'/>">启蒙趣味</a>
                    </div>
                    
                    <div class="detailclass">
                    	<span class="bar_text_span">
                    		<a class="bar_text" href="<c:url value='/searchAction?good_SmallClass=童话故事'/>">童话故事</a> / 
                    		<a class="bar_text" href="<c:url value='/searchAction?good_SmallClass=启蒙图册'/>">启蒙图册</a> / 
                    		<a class="bar_text" href="<c:url value='/searchAction?good_SmallClass=励志育人'/>">励志育人</a> / 
                    		 <a class="bar_text"  href="<c:url value='/searchAction?good_SmallClass=笑话全集'/>">笑话全集</a>
                    	</span>
                    </div>
                </div>
                
                
                 <div class="classText">
                	<div class="bigclass">
                    	<a class="bar_text" href="<c:url value='/searchAction?good_BigClass=工具技能'/>">工具技能</a>
                    </div>
                    
                    <div class="detailclass">
                    	<span class="bar_text_span">
                    		<a class="bar_text" href="<c:url value='/searchAction?good_SmallClass=工学图书'/>">工学图书</a> / 
                    		<a class="bar_text" href="<c:url value='/searchAction?good_SmallClass=理学图书'/>">理学图书</a> / 
                    		<a class="bar_text" href="<c:url value='/searchAction?good_SmallClass=生活技巧'/>">生活技巧</a> / 
                    		<a class="bar_text" href="<c:url value='/searchAction?good_SmallClass=烹饪'/>">烹饪</a> / 
                    		<a class="bar_text"  href="<c:url value='/searchAction?good_SmallClass=IT技能'/>">IT技能</a> / 
                    		<a class="bar_text" href="<c:url value='/searchAction?good_SmallClass=其他'/>">其他</a>
                    	</span>
                    </div>
                </div>
            </div>
            
            <div id="centerPart_panels">
            	<img class="panels_img" src="front/images/fengmian.jpg"/>
            </div>
            
            <div class="recommend">
                <div class="recommend_bar">
                	<img class="recommend_img" src="front/images/recommend.png" />
                </div>
                
                <div class="recommend_content">
                	<div class="main_recommend">
                		<a href="<c:url value='/goodDetailAction?gid=${recommend[0].gid }'/>" target="blank">
                    		<img class="main_recommend_img" src="<c:url value='${recommend[0].picture }'/>" />
                    	</a>
                        <div class="main_recommend_info">
                            <a class="bookName book_text" href="<c:url value='/goodDetailAction?gid=${recommend[0].gid }'/>">${recommend[0].gname }</a><br />
                            <span class="author book_text">${recommend[0].storeName }</span><br />
                            <span class="price main_price book_text">Sale：&nbsp;￥${recommend[0].price }</span>
                        </div>
                    </div>
                    
                    <div class="incident_recommend">
                    <!-- 循环开始 -->
                    <c:forEach items="${recommend }" begin="1" var="good" varStatus="status">
                        <div class="incident_recommend_one">
                        	<a href="<c:url value='/goodDetailAction?gid=${good.gid }'/>" target="blank">
                    			<img class="incident_recommend_one_img" src="<c:url value='${good.picture }'/>" />
                    		</a>
                            <div class="incident_recommend_info">
                                <a class="bookName book_text" href="<c:url value='/goodDetailAction?gid=${good.gid }'/>">${good.gname }</a><br />
                                <span class="author book_text">${good.storeName }</span><br />
                                <span class="price book_text">￥${good.price }</span>
                            </div>
                    	</div>
                    	<c:if test="${status.count eq 4 }">
                    		<br />
                    	</c:if>
                    </c:forEach>
                    <!-- 循环结束 -->   
                    </div> 
                </div>
            </div>

            <div class="recommend">
                <div class="recommend_bar">
                	<img class="recommend_img" src="front/images/newBook.png" />
                </div>
                
				<div class="recommend_content">
                	<div class="main_recommend">
                    	<a href="<c:url value='/goodDetailAction?gid=${newBooks[0].gid }'/>" target="blank">
                    		<img class="main_recommend_img" src="<c:url value='${newBooks[0].picture }'/>" />
                    	</a>
                        <div class="main_recommend_info">
                            <a class="bookName book_text" href="<c:url value='/goodDetailAction?gid=${newBooks[0].gid }'/>">${newBooks[0].gname }</a><br />
                            <span class="author book_text">${newBooks[0].storeName }</span><br />
                            <span class="price main_price book_text">Sale：&nbsp;￥${newBooks[0].price }</span>
                        </div>
                    </div>
                    
                    <div class="incident_recommend">
                    <!-- 循环开始 -->
                    <c:forEach items="${newBooks }" begin="1" var="good" varStatus="status">
                        <div class="incident_recommend_one">
                    		<a href="<c:url value='/goodDetailAction?gid=${good.gid }'/>" target="blank">
                    			<img class="incident_recommend_one_img" src="<c:url value='${good.picture }'/>" />
                    		</a>
                            <div class="incident_recommend_info">
                                <a class="bookName book_text" href="<c:url value='/goodDetailAction?gid=${good.gid }'/>">${good.gname }</a><br />
                                <span class="author book_text">${good.storeName }</span><br />
                                <span class="price book_text">￥${good.price }</span>
                            </div>
                    	</div>
                    	<c:if test="${status.count eq 4 }">
                    		<br />
                    	</c:if>
                    </c:forEach>
                    <!-- 循环结束 -->   
                    </div> 
                </div>
            </div>
		</div>       
    </div>
	
	<c:import url="/front/jsp/foot.jsp"></c:import>
</div>
</body>
</html>