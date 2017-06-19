package com.krishnna.service.good;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrQuery.ORDER;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.transaction.annotation.Transactional;

import com.krishnna.dao.good.GoodDao;
import com.krishnna.entity.Good;
import com.krishnna.entity.ResultModel;

/**
 * 商品模块业务逻辑层
 * @author Ningkui
 */
@Transactional
public class GoodService {
	
	/**
	 * 获得GoodDao对象	
	 */
	private GoodDao goodDao;
	public void setGoodDao(GoodDao goodDao) {
		this.goodDao = goodDao;
	}
	
	/**
	 * 获得HttpSolrServer对象
	 */
	private HttpSolrServer server;
	public void setServer(HttpSolrServer server) {
		this.server = server;
	}

	/**
	 * 传入Good对象。、条件查询方法。
	 */
	public List<Good> find(Good good){
		
		// HQL语句
		String hql = "from Good";
		List<Good> goodList = null;
		/**
		 * 判断参数good中的条件
		 */
		// 若gid存在，则在sql中添加条件gid
		if (good.getGid() != null) {
			hql = "from Good good where good.gid=?";
			// 根据id查询
			goodList = (List<Good>) goodDao.find(hql, new Object[]{good.getGid()});
		} else {
			// 全查询
			goodList = (List<Good>) goodDao.find(hql, new Object[]{});
		}
		
		return goodList;
	}
	
	/**
	 *  访问dao层方法， 根据销售量降序排序取出前九条记录，用于主页“特别推荐”图书模块
	 */
	public List<Good> findRecommend(){
		//调用dao层方法。访问数据库
		List<Good> goodList = (List<Good>) goodDao.findBycriteria(new Good(), new Object[]{"salesNum",0,9});
		return goodList;
	}
	
	/**
	 *  访问dao层方法， 根据入库时间降序排序取出前九条记录，用于主页“新书速递”图书模块
	 */
	public List<Good> findNews(){
		//调用dao层方法。访问数据库
		List<Good> goodList = (List<Good>) goodDao.findBycriteria(new Good(), new Object[]{"bookDate",0,9});
		return goodList;
	}
	
	/**
	 * 调用solr服务端，进行索引查询
	 * @throws Exception 
	 * 参数：
	 * queryString:搜索框提供的查询语句
	 * good_bigclass:图书的大类别条件
	 * good_smallclass:图书的细类别条件
	 * good_price:图书的价格区间
	 * good_order:图书的排序条件
	 * currpage:当前页码
	 */
	public ResultModel search(String queryString,String good_bigclass,String good_smallclass,
			String good_price,String good_order,int currentpage) throws Exception{
		
		/*
		 * 创建查询对象
		 */
		SolrQuery query = new SolrQuery();
		
		/*
		 * 设置查询语句
		 */
		if(!"".equals(queryString)&&queryString != null) {
			query.setQuery(queryString);
		} else {
			query.setQuery("*:*");
		}
		
		/*
		 * 设置过滤条件
		 */
		boolean isSetFirstFilter = false;
		//设置图书大类别过滤条件
		if(!"".equals(good_bigclass)&&good_bigclass != null){
			query.setFilterQueries("good_bigclass:"+good_bigclass);
			isSetFirstFilter = true;
		}
		
		//设置图书细类别过滤条件
		if(!"".equals(good_smallclass)&&good_smallclass != null){
			if(isSetFirstFilter){
				query.addFilterQuery("good_smallclass:"+good_smallclass);
			}else {
				query.setFilterQueries("good_smallclass:"+good_smallclass);
				isSetFirstFilter = true;
			}
		}
		
		if(!"".equals(good_price)&&good_price != null){
			//切割字符串
			String[] price = good_price.split("-");
			//设置价格区间过滤条件
			if(isSetFirstFilter){
				query.addFilterQuery("good_price:["+price[0]+" TO "+price[1]+"]");
			}else {
				query.setFilterQueries("good_price:["+price[0]+" TO "+price[1]+"]");
			}
		}
		
		//设置排序规则
		if(!"".equals(good_order)&&good_order != null){
			query.setSort(good_order, ORDER.asc);
		}
		
		//设置分页
		query.setStart((currentpage-1)*6);
		query.setRows(6);
		
		//设置显示的Field域的集合
		query.setFields("id","good_name","good_price","good_briefing","good_picture");
		
		//设置默认域
		query.set("df", "good_keyword");
		
		//设置高亮
		query.setHighlight(true);
		query.addHighlightField("good_name");
		query.setHighlightSimplePre("<font style=\"color:red\" >");
		query.setHighlightSimplePost("</font>");
		
		//执行查询索引，得到响应结果
		QueryResponse response = server.query(query);
		
		SolrDocumentList list = response.getResults();
		System.out.println(list);
		//得到结果的总条数
		long recordCount = list.getNumFound();
		
		//获得高亮部分的显示
		Map<String,Map<String,List<String>>> highLighting = response.getHighlighting();
		
		//定于商品集合和商品
		List<Good> goods = new ArrayList<Good>();
		Good go;
		
		//遍历结果集
		for (SolrDocument doc : list) {
			//实例化商品
			go = new Good();
			
			//获得商品id
			go.setGid(doc.get("id").toString());
			
			//获得该id下是否有高亮文本
			List<String> list2 = highLighting.get(doc.get("id")).get("good_name");
			//获得商品名称
			if(list2 != null) {
				go.setGname(list2.get(0));
			} else {
				go.setGname(doc.get("good_name").toString());
			}
			
			//获得商品价格
			go.setPrice((float) doc.get("good_price"));
			
			//商品描述
			go.setBriefing(doc.get("good_briefing").toString());
			
			//商品图片
			go.setPicture(doc.get("good_picture").toString());
			
			//将商品存储进集合中
			goods.add(go);
		}
		
		//构建结果模型
		ResultModel model = new ResultModel();
		
		//设置商品集合
		model.setList(goods);
		
		//设置总条数
		model.setRecordCount(recordCount);
		
		//计算页数
		int pageCount = (int) (recordCount/6);
		pageCount = pageCount + 1;
		model.setPageCount(pageCount);
		
		//设置当前页数
		model.setCurrentPage(currentpage);
		
		return model;
	}
}
