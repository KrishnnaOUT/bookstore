<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet" type="text/css"  href="<c:url value='/front/css/init.css'></c:url>">
<link rel="stylesheet" type="text/css"  href="<c:url value='/front/css/find.css'></c:url>">
<script type="text/javascript" src="<c:url value='/front/js/find.js'></c:url>"></script>

<title>搜索页面</title>

</head>
<body>

<div id="all">

	<c:import url="/front/jsp/state.jsp"></c:import>
	
	<form action="<c:url value='/searchAction'/>" method="post" id="submitForm">
	<div id="main">
		<div id="head">	
	        	<div id="logo">
	        		<img src="<c:url value='/front/images/logo2.png'></c:url>" />
	            </div>
	            <div id="search">
	            	<c:choose>
	            		<c:when test="${not empty keyword }">
	            			<input id="searchBox" name="keyword" type="text" name="keyword" value="${keyword }"/>
	            		</c:when>
	            		<c:otherwise>
	                		<input id="searchBox" name="keyword" type="text"  name="keyword" alt="请输入书名/作者"/>
	                	</c:otherwise>
	                </c:choose>
	                <input id="searchButton" type="submit" value="搜索"/>
	            </div>
        </div>
        <br />
        <div id="bar">
        	<ul>
            	<li class="bigClass" id="firstLi">多维度查询</li>
                <li class="bigClass"><a href="#">今日精选</a></li>
                <li class="bigClass"><a href="#">天天特价</a></li>
                <li class="bigClass"><a href="#">新书速递</a></li>
                <li class="bigClass"><a href="#">图书漂流</a></li>
            </ul>
        </div>

		<div class="factorsSelect_bar">
			<div class="isSelectFactorList">
				<span>全部结果 > </span>
				<c:forEach items="${facotrList }" var="factor">
					<span>${factor }</span>&nbsp;
				</c:forEach>
			</div>
			<div class="book_class">
            <div class="bar_child class_child1">
                <ul>
                    <li class="class_info"><a href="<c:url value='/searchAction?good_BigClass=文学随笔'/>" class="factor_info">文学随笔</a></li>
                    <li class="class_info"><a href="<c:url value='/searchAction?good_BigClass=文学随笔&good_SmallClass=all'/>" class="class_text classall" style="background-color: yellow">全部</a></li>
                    <li class="class_info"><a href="<c:url value='/searchAction?good_BigClass=文学随笔&good_SmallClass=古典文学'/>" class="class_text class11">古典文学</a></li>
                    <li class="class_info"><a href="<c:url value='/searchAction?good_BigClass=文学随笔&good_SmallClass=现代文学'/>" class="class_text class12">现代文学</a></li>
                    <li class="class_info"><a href="<c:url value='/searchAction?good_BigClass=文学随笔&good_SmallClass=外国文学'/>" class="class_text class13">外国文学</a></li>
                    <li class="class_info"><a href="<c:url value='/searchAction?good_BigClass=文学随笔&good_SmallClass=国学'/>" class="class_text class14">国学</a></li>
                    <li class="class_info"><a href="<c:url value='/searchAction?good_BigClass=文学随笔&good_SmallClass=个人随笔'/>" class="class_text class15">个人随笔</a></li>
                    <li class="class_info"><a href="<c:url value='/searchAction?good_BigClass=文学随笔&good_SmallClass=人物传记'/>" class="class_text class16">人物传记</a></li>
                </ul>
            </div>
            
             <div class="bar_child class_child2">
                <ul>
                    <li class="class_info"><a href="<c:url value='/searchAction?good_BigClass=网络文学'/>" class="factor_info">网络文学</a></li>
                    <li class="class_info"><a href="<c:url value='/searchAction?good_BigClass=网络文学&good_SmallClass=all'/>" class="class_text classall" style="background-color: yellow">全部</a></li>
                    <li class="class_info"><a href="<c:url value='/searchAction?good_BigClass=网络文学&good_SmallClass=玄幻'/>" class="class_text class21">玄幻</a></li>
                    <li class="class_info"><a href="<c:url value='/searchAction?good_BigClass=网络文学&good_SmallClass=悬疑'/>" class="class_text class22">悬疑</a></li>
                    <li class="class_info"><a href="<c:url value='/searchAction?good_BigClass=网络文学&good_SmallClass=科幻'/>" class="class_text class23">科幻</a></li>
                    <li class="class_info"><a href="<c:url value='/searchAction?good_BigClass=网络文学&good_SmallClass=都市'/>" class="class_text class24">都市</a></li>
                    <li class="class_info"><a href="<c:url value='/searchAction?good_BigClass=网络文学&good_SmallClass=战争'/>" class="class_text class25">战争</a></li>
                    <li class="class_info"><a href="<c:url value='/searchAction?good_BigClass=网络文学&good_SmallClass=穿越'/>" class="class_text class26">穿越</a></li>
                </ul>
            </div>
            
             <div class="bar_child class_child3">
                <ul>
                    <li class="class_info"><a href="<c:url value='/searchAction?good_BigClass=启蒙趣味'/>" class="factor_info">启蒙趣味</a></li>
                    <li class="class_info"><a href="<c:url value='/searchAction?good_BigClass=启蒙趣味&good_SmallClass=all'/>" class="class_text classall" style="background-color: yellow">全部</a></li>
                    <li class="class_info"><a href="<c:url value='/searchAction?good_BigClass=启蒙趣味&good_SmallClass=童话故事'/>" class="class_text class31">童话故事</a></li>
                    <li class="class_info"><a href="<c:url value='/searchAction?good_BigClass=启蒙趣味&good_SmallClass=启蒙图册'/>" class="class_text class32">启蒙图册</a></li>
                    <li class="class_info"><a href="<c:url value='/searchAction?good_BigClass=启蒙趣味&good_SmallClass=励志育人'/>" class="class_text class33">励志育人</a></li>
                    <li class="class_info"><a href="<c:url value='/searchAction?good_BigClass=启蒙趣味&good_SmallClass=人物传记'/>" class="class_text class34">人物传记</a></li>
                    <li class="class_info"><a href="<c:url value='/searchAction?good_BigClass=启蒙趣味&good_SmallClass=笑话全集'/>" class="class_text class35">笑话全集</a></li>
                </ul>
            </div>
            
             <div class="bar_child class_child4">
                <ul>
                    <li class="class_info"><a href="<c:url value='/searchAction?good_BigClass=工具技能'/>" class="factor_info">工具技能</a></li>
                    <li class="class_info"><a href="<c:url value='/searchAction?good_BigClass=工具技能&good_SmallClass=all'/>" class="class_text classall" style="background-color: yellow">全部</a></li>
                    <li class="class_info"><a href="<c:url value='/searchAction?good_BigClass=工具技能&good_SmallClass=工学图书'/>" class="class_text class41">工学图书</a></li>
                    <li class="class_info"><a href="<c:url value='/searchAction?good_BigClass=工具技能&good_SmallClass=理学图书'/>" class="class_text class42">理学图书</a></li>
                    <li class="class_info"><a href="<c:url value='/searchAction?good_BigClass=工具技能&good_SmallClass=生活技巧'/>" class="class_text class43">生活技巧</a></li>
                    <li class="class_info"><a href="<c:url value='/searchAction?good_BigClass=工具技能&good_SmallClass=烹饪'/>" class="class_text class44">烹饪</a></li>
                    <li class="class_info"><a href="<c:url value='/searchAction?good_BigClass=工具技能&good_SmallClass=IT技能'/>" class="class_text class45">IT技能</a></li>
                </ul>
             </div>
            </div>
            
            
            <div class="bar_child price_child">
                <ul>
                    <li class="class_info"><span class="factor_info">价格区间</span></li>
                    <li class="class_info"><a class="class_all_all price_all" href="<c:url value='/searchAction?good_price=all'/>" style="background-color:yellow">全部</a></li>
                    <li class="class_info"><a href="<c:url value='/searchAction?good_price=0-50'/>" class="price1">0-50</a></li>
                    <li class="class_info"><a href="<c:url value='/searchAction?good_price=50-100'/>" class="price2">50-100</a></li>
                    <li class="class_info"><a href="<c:url value='/searchAction?good_price=100-300'/>" class="price3">100-300</a></li>
                    <li class="class_info"><a href="<c:url value='/searchAction?good_price=300-1000'/>" class="price4">300-1000</a></li>
                </ul>
            </div>
            
            <div class="goodShow_head">
            	<div class="goodShow_head_order">
                	<input type="submit" class="curr curr_all" value="综合" ></input>
                    <input type="submit" class="curr curr_salesNum" onclick="javascript:OrderBySales();" value="销量"></input>
                    <input type="submit" class="curr curr_popular" value="人气"></input>
               		<input type="submit" class="curr curr_price" onclick="javascript:OrderByPrice();" value="价格"></input>
               		<input type="hidden" id="good_order" class="curr curr_order" name="good_order" value=""></input>
                </div>
                
                <div class="goodShow_head_page">
                	<span>共${model.recordCount }件商品</span>&nbsp;
                	<span>${model.currentPage }/${model.pageCount }</span>&nbsp;
                	<c:if test="${model.currentPage ne 1 }">
      					<a href="<c:url value='/searchAction?current_page=${model.currentPage-1 }'/>" class="overPage_button"><span><<</span></a>
      			`	</c:if>
      				<c:if test="${model.currentPage ne model.pageCount }">
      					<a href="<c:url value='/searchAction?current_page=${model.currentPage+1 }'/>" class="overPage_button"><span>>></span></a>
      				</c:if>
                </div>
            </div>
        </div>
        
        <div class="main_goodShow">
            <div class="good_list">
            	<div class="incident_recommend">
            		<c:forEach items="${model.list }" var="good">
                        <div class="incident_recommend_one">
                        	<div class="incident_recommend_one_img_div">
                    			<a href="<c:url value='/goodDetailAction?gid=${good.gid }'/>" target="blank">
                    				<img class="incident_recommend_one_img" src="<c:url value='${good.picture }'/>" />
                    			</a>
                            </div>
                    		
                            <div class="incident_recommend_info">
                                <a class="bookName book_text" href="#">${good.gname }</a><br />
                                <span class="author book_text">${good.storeName }</span><br />
                                <span class="price book_text">￥${good.price }</span>
                            </div>
                    	</div>
                    </c:forEach>    
               	</div> 
            </div>
        </div>
    	
	</div>
	
	</form>
	
	<c:import url="/front/jsp/foot.jsp"></c:import>
</div>
</body>
</html>