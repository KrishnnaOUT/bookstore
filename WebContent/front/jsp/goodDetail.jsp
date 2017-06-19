<%@ page language="java" contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>商品详情页</title>

<link rel="stylesheet" type="text/css"  href="<c:url value='/front/css/init.css'></c:url>">
<link rel="stylesheet" type="text/css"  href="<c:url value='/front/css/goodDetail.css'></c:url>">

<script type="text/javascript">


window.onload=function(){
	
	var commitOrderButton = document.getElementById("commitOrder");
	commitOrderButton.onclick=function(){
		document.model.action="<c:url value='/commitOrderAction'/>"
	};
	
	var addToCartButton = document.getElementById("addToCart");
	addToCartButton.onclick=function(){
		document.model.action="<c:url value='/addToCartAction'/>"
	};
	
	
}


</script>

</head>
<body>
<div id="all">

	<c:import url="/front/jsp/state.jsp"></c:import>
	
	
	<div id="main">
    	
    	<c:import url="/front/jsp/head.jsp"></c:import>
        
        <!--商品展示主要部分-->
        <div class="good_detail">
        	
            <div class="good_detail_left">
            	<div class="picture_div">
                	<img class="picture" src="${pageContext.request.contextPath}<s:property value='good.picture'/>" />
                </div> 
            </div>
        
        	<div class="good_detail_center">
            	<form id="model" name="model" action="" method="post">
				
					<input type="hidden" name="gid" value="<s:property value='good.gid'/>">
					<input type="hidden" name="gname" value="<s:property value='good.gname'/>">
					<input type="hidden" name="storeName" value="<s:property value='good.storeName'/>">
					<input type="hidden" name="price" value="<s:property value='good.price'/>">
					<input type="hidden" name="briefing" value="<s:property value='good.briefing'/>">
					<input type="hidden" name="salesNum" value="<s:property value='good.salesNum'/>">
					<input type="hidden" name="picture" value="<s:property value='good.picture'/>">
					<input type="hidden" name="bookClass" value="<s:property value='good.bookClass'/>">
					<input type="hidden" name="bookDate" value="<s:property value='good.bookDate'/>">
					
					<input type="hidden" name="directPay" value="1">
            	<div class="good_title">
                    <div class="good_gname">
                        <span class="gname"><s:property value="good.gname"/></span>
                    </div>
                    
                    <div class="good_briefing">
                        <span class="briefing"><s:property value="good.briefing"/></span>
                    </div>
                </div>
                
                <div class="good_importInfo">
                	<!--价格模块-->
                	<div class="good_div price_all">
                        <div class="text_info">
                        	<span class="info">价格</span>
                        </div>
                        
                        <div class="price_div">
                       		<span class="price">￥&nbsp;<s:property value="good.price"/></span>
                        </div>
                        <div class="good_sale_info">
                        	<span class="salesNum"><s:property value="good.salesNum"/></span>
                            <br />
                            <span class="price_info">交易成功</span>
                        </div>
                    </div>
                    
                    <!--配送模块-->
                    <div class="good_div address_all">
                        <div class="text_info">
                        	<span class="info">配送</span>
                        </div>
                        
                        <div class="address_div">
                        	<span class="store_address address_info"><s:property value="good.storeName"/></span>
                            <span class="info">&nbsp;至&nbsp;</span>
                            <span class="address address_info">广东梅州市嘉应学院</span>
                        </div>
                    </div>
                    
                    <!--规格模块-->
                    <div class="good_div specifications_all">
                        <div class="text_info">
                        	<span class="info">规格</span>
                        </div>
                        
                        <div class="specifications_div">
                        	<span class="specifications">精修版全套（16本）</span>&nbsp;&nbsp;
                            <span class="specifications">精修版（8本）</span><br /><br />
                            <span class="specifications">普通版全套（16本）</span>&nbsp;&nbsp;
                            <span class="specifications">精修版（8本）</span><br /><br />
                        </div>
                    </div>
                    
                    <!--数量模块-->
                    <div class="good_div">
                        <div class="text_info">
                        	<span class="info">数量</span>
                        </div>
                        
                        <div class="count_div">
                        	<a class="adjust_count add_count" href="#">-</a>
								<input class="count" name="count" type="text" value="1"/>
							<a class="adjust_count sub_count" href="#">+</a>
                            <span>件</span>
                        </div>
                        
                        <div class="inventory_div">
                        	<span>库存：</span>
                            <span class="inventory">7824件</span>
                        </div>
                    </div>
                    
                </div>
                
               <div class="button_div">
               		<div class="operation_button">
                    	<div class="operation_div">
                    		<input type="submit"  id="commitOrder" class="operation buy_now" value="立即购买" ></input>
                        </div>
                        <div class="operation_div">
                        	<input type="submit"  id="addToCart" class="operation addtoCart" value="加入购物车"></input>
                        </div>
                    </div>
               </div> 
               </form>
            </div>
            
            <div class="good_detail_right">
            	<div class="store_all">
                	<span class="storeName">国学出版社</span><br /><br />
                    <span class="info">信誉：</span>
                    <span class="info">掌柜：</span>
                </div>
            </div>
            
        </div>

    </div>
</div>		
</body>
</html>