package com.krishnna.action.good;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.krishnna.entity.Good;
import com.krishnna.entity.ResultModel;
import com.krishnna.service.good.GoodService;
import com.opensymphony.xwork2.ActionSupport;

public class GoodAction extends ActionSupport {
	private static final long serialVersionUID = 1L;
	
	/**
	 * 创建Good集合对象。放入值栈中
	 */
	private Good good = new Good();
	public Good getGood() {
		return good;
	}
	
	/**
	 * 创建搜索结果对象，放入值栈中
	 */
	private ResultModel model = new ResultModel();
	public ResultModel getModel() {
		return model;
	}

	/**
	 * 获得GoodService对象
	 */
	private GoodService goodService;
	public void setGoodService(GoodService goodService) {
		this.goodService = goodService;
	}

	/**
	 * 获得request对象
	 */
	HttpServletRequest request = ServletActionContext.getRequest();

	/**
	 * 全查询，
	 */
	@Override
	public String execute() throws Exception {
		
		//调用service层方法，
		Good good = new Good();
		List<Good> goodList = goodService.find(good);
		//将goodList对象存入session域中
		request.setAttribute("goodList", goodList);
		
		return "SUCCESS";
	}
	
	/**
	 * 仅搜索数据库根据规则排出来的前九个。作为特别推荐栏
	 */
	public String homePageRecommend() throws Exception {
		System.out.println("进入homePageRecommend方法。。。。。。。。。。。。");
		/*
		 * 调用service层方法，
		 */
		//条件查询。根据一定规则搜索出最值得特别推荐的图书
		List<Good> goodListRecommend = goodService.findRecommend();
		//将goodListRecommend对象存入request域中
		request.setAttribute("recommend", goodListRecommend);
		
		//条件查询。根据一定规则搜索出最值得特别推荐的图书
		List<Good> goodListNews = goodService.findNews();
		// 将goodListRecommend对象存入request域中
		request.setAttribute("newBooks", goodListNews);
		
		return "SUCCESS";
	}

	/**
	 *  根据id查询某件商品的详情
	 *  跳转到详情页面goodDetail.jsp
	 */
	public String goodDetail(){

		String gid = request.getParameter("gid");

		/*
		 * 创建一个 Good对象good1作为service层方法的参数， 
		 */
		Good good1 = new Good();
		good1.setGid(gid);
		//调用service层方法，返回Good集合goodList，
		List<Good> goodList = goodService.find(good1);
		//获得集合中Good对象,并赋值给good，为了传入值栈中
		good = goodList.get(0);
		
		return "SUCCESS";
	}
	
	/**
	 * 主页搜索框搜索功能
	 * @throws Exception 
	 */
	public String search() throws Exception {
		
		//获得搜索框中的用户填入的值
		String keyword = request.getParameter("keyword");
		if(!"".equals(keyword)&&keyword != null){
			//将搜索框中的用户填入的值搜索条件放入session域中
			request.getSession().setAttribute("keyword", keyword);
		}
		//取出上次搜索的值。
		String oldkeyword = (String) request.getSession().getAttribute("keyword");
		
		if(keyword != null&&keyword.length()>0){
			/*
			 * 若本次为搜索框搜索，即keyword不为空
			 *    则把session域中的所有搜索条件除keyword外清空
			 */
			request.getSession().setAttribute("good_bigclass", "");
			request.getSession().setAttribute("good_smallclass", "");
			request.getSession().setAttribute("good_price", "");
		}
		
		//获得图书大分类
		String good_bigclass = request.getParameter("good_BigClass");
		
		//获取原来在session中good_bigclass的值，并存储于oldBigClass中
		String oldBigClass = (String) request.getSession().getAttribute("good_bigclass");
		
		/*
		 * 判断图书大分类是否为空，
		 * 	若为空，且主查询语句keyword也为空。
		 * 		--那么让图书大分类 = 上次查询图书大分类的值
		 *  若不为空，
		 *  	--将图书大分类搜索条件放入session域中
		 */
		if(!"".equals(good_bigclass)&&good_bigclass != null){
			//将图书大分类搜索条件放入session域中
			request.getSession().setAttribute("good_bigclass", good_bigclass);
		} else if("".equals(keyword)||keyword == null){
			//若keyword不为空，这说明启用全局搜索。条件就不再采用
			good_bigclass = (String) request.getSession().getAttribute("good_bigclass");
		}
		
		//获得图书细类
		String good_smallclass = request.getParameter("good_SmallClass");
		String oldSmallClass = null;//原来存储在session中的good_class值

		//获取原来在session中good_smallclass的值，并存储于oldSmallClass中
		oldSmallClass = (String) request.getSession().getAttribute("good_smallclass");
		
		/*
		 * 判断图书细类是否为空，
		 * 	若为空，且主查询语句keyword也为空。
		 * 		--那么让图书细类 = 上次查询图书细类的值
		 *  若不为空，
		 *  	--将图书大分类搜索条件放入session域中
		 */
		if(!"".equals(good_smallclass)&&good_smallclass != null){
			request.getSession().setAttribute("good_smallclass", good_smallclass);
		} else if("".equals(keyword)||keyword == null) {
			if(!"".equals(oldBigClass)&&oldBigClass != null&&
					!"".equals(good_bigclass)&&good_bigclass != null){
				if(oldBigClass.equals(good_bigclass)){
					good_smallclass = oldSmallClass;
				}
			}
		}
		
		
		//获得价格区间
		String good_price = request.getParameter("good_price");
		
		/*
		 * 判断价格区间是否为空，
		 * 	若为空，且主查询语句keyword也为空。
		 * 		--那么让价格区间 = 上次查询价格区间的值
		 *  若不为空，
		 *  	--将价格区间搜索条件放入session域中
		 */
		if(!"".equals(good_price)&&good_price != null){
			request.getSession().setAttribute("good_price", good_price);
		} else if("".equals(keyword)||keyword == null) {
			good_price = (String) request.getSession().getAttribute("good_price");
		}
		
		/*
		 * 如果重新查找图书细分类，且和上次查找图书细分类的值不一样，则除了图书细分类这一条件外
		 * 其他条件-->即价格区间条件置为空
		 */
		if("".equals(oldSmallClass)||oldSmallClass == null){
			if("".equals(good_smallclass)||good_smallclass == null){
				if(!"".equals(oldkeyword)&&oldkeyword != null){
					if(keyword == null){
						keyword = oldkeyword;
					}
				}
			}
		} else {
			if(!"".equals(good_smallclass)&&good_smallclass != null){
				if(!good_smallclass.equals(oldSmallClass)){
					/*
					 * 若现在的图书细类搜索条件和曾经的该搜索条件不一样
					 * 则让价格搜索条件置为空。
					 * 且让session中good_price也置为空字符串
					 */
					good_price = null;
					request.getSession().setAttribute("good_price", "");
				}
			}
		}
		
		
		//若主查询语句为null，则复制为空字符串
		if(keyword == null){
			keyword="";
		}
		
		//获得排序条件
		String good_order = request.getParameter("good_order");
		
		/*
		 * 若排序条件不为空，
		 *   若session域中的good_smallclass不为空
		 *   	则将good_smallclass = session中的good_smallclass
		 *   若session域中的good_price不为空
		 * 		则将good_price = session中的good_price
		 */
		
		//获得当前页数，默认为1
		int currentPage = 1;
		String flag = request.getParameter("current_page");
		if(!"".equals(flag)&&flag != null){
			currentPage = Integer.parseInt(flag);
		}
		
		/*
		 * 若good_smallclass值为all，则让good_smallClass置为空
		 * 若good_price值为all，则让good_price置为空
		 */
		if("all".equals(good_smallclass)){
			good_smallclass = null;
		}
		if("all".equals(good_price)){
			good_price = null;
		}
		
		//调用业务层
		model = goodService.search(keyword.trim(),good_bigclass,good_smallclass,good_price,good_order,currentPage);
		
		//将查询条件回调
		request.setAttribute("keyword", keyword);
		List<String> facotrList = new ArrayList<String>();
		facotrList.add(good_bigclass);
		facotrList.add(good_smallclass);
		facotrList.add(good_price);
		request.setAttribute("facotrList", facotrList);
		request.setAttribute("good_order", good_order);
		
		
		return "SUCCESS";
	}
	
	/**
	 * 根据图书的类别查找对应类别的图书
	 */
	public String searchByClass(){
		return "SUCCESS";
	}
}
