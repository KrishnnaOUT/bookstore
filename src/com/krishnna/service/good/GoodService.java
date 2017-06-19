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
 * ��Ʒģ��ҵ���߼���
 * @author Ningkui
 */
@Transactional
public class GoodService {
	
	/**
	 * ���GoodDao����	
	 */
	private GoodDao goodDao;
	public void setGoodDao(GoodDao goodDao) {
		this.goodDao = goodDao;
	}
	
	/**
	 * ���HttpSolrServer����
	 */
	private HttpSolrServer server;
	public void setServer(HttpSolrServer server) {
		this.server = server;
	}

	/**
	 * ����Good���󡣡�������ѯ������
	 */
	public List<Good> find(Good good){
		
		// HQL���
		String hql = "from Good";
		List<Good> goodList = null;
		/**
		 * �жϲ���good�е�����
		 */
		// ��gid���ڣ�����sql���������gid
		if (good.getGid() != null) {
			hql = "from Good good where good.gid=?";
			// ����id��ѯ
			goodList = (List<Good>) goodDao.find(hql, new Object[]{good.getGid()});
		} else {
			// ȫ��ѯ
			goodList = (List<Good>) goodDao.find(hql, new Object[]{});
		}
		
		return goodList;
	}
	
	/**
	 *  ����dao�㷽���� ������������������ȡ��ǰ������¼��������ҳ���ر��Ƽ���ͼ��ģ��
	 */
	public List<Good> findRecommend(){
		//����dao�㷽�����������ݿ�
		List<Good> goodList = (List<Good>) goodDao.findBycriteria(new Good(), new Object[]{"salesNum",0,9});
		return goodList;
	}
	
	/**
	 *  ����dao�㷽���� �������ʱ�併������ȡ��ǰ������¼��������ҳ�������ٵݡ�ͼ��ģ��
	 */
	public List<Good> findNews(){
		//����dao�㷽�����������ݿ�
		List<Good> goodList = (List<Good>) goodDao.findBycriteria(new Good(), new Object[]{"bookDate",0,9});
		return goodList;
	}
	
	/**
	 * ����solr����ˣ�����������ѯ
	 * @throws Exception 
	 * ������
	 * queryString:�������ṩ�Ĳ�ѯ���
	 * good_bigclass:ͼ��Ĵ��������
	 * good_smallclass:ͼ���ϸ�������
	 * good_price:ͼ��ļ۸�����
	 * good_order:ͼ�����������
	 * currpage:��ǰҳ��
	 */
	public ResultModel search(String queryString,String good_bigclass,String good_smallclass,
			String good_price,String good_order,int currentpage) throws Exception{
		
		/*
		 * ������ѯ����
		 */
		SolrQuery query = new SolrQuery();
		
		/*
		 * ���ò�ѯ���
		 */
		if(!"".equals(queryString)&&queryString != null) {
			query.setQuery(queryString);
		} else {
			query.setQuery("*:*");
		}
		
		/*
		 * ���ù�������
		 */
		boolean isSetFirstFilter = false;
		//����ͼ�������������
		if(!"".equals(good_bigclass)&&good_bigclass != null){
			query.setFilterQueries("good_bigclass:"+good_bigclass);
			isSetFirstFilter = true;
		}
		
		//����ͼ��ϸ����������
		if(!"".equals(good_smallclass)&&good_smallclass != null){
			if(isSetFirstFilter){
				query.addFilterQuery("good_smallclass:"+good_smallclass);
			}else {
				query.setFilterQueries("good_smallclass:"+good_smallclass);
				isSetFirstFilter = true;
			}
		}
		
		if(!"".equals(good_price)&&good_price != null){
			//�и��ַ���
			String[] price = good_price.split("-");
			//���ü۸������������
			if(isSetFirstFilter){
				query.addFilterQuery("good_price:["+price[0]+" TO "+price[1]+"]");
			}else {
				query.setFilterQueries("good_price:["+price[0]+" TO "+price[1]+"]");
			}
		}
		
		//�����������
		if(!"".equals(good_order)&&good_order != null){
			query.setSort(good_order, ORDER.asc);
		}
		
		//���÷�ҳ
		query.setStart((currentpage-1)*6);
		query.setRows(6);
		
		//������ʾ��Field��ļ���
		query.setFields("id","good_name","good_price","good_briefing","good_picture");
		
		//����Ĭ����
		query.set("df", "good_keyword");
		
		//���ø���
		query.setHighlight(true);
		query.addHighlightField("good_name");
		query.setHighlightSimplePre("<font style=\"color:red\" >");
		query.setHighlightSimplePost("</font>");
		
		//ִ�в�ѯ�������õ���Ӧ���
		QueryResponse response = server.query(query);
		
		SolrDocumentList list = response.getResults();
		System.out.println(list);
		//�õ������������
		long recordCount = list.getNumFound();
		
		//��ø������ֵ���ʾ
		Map<String,Map<String,List<String>>> highLighting = response.getHighlighting();
		
		//������Ʒ���Ϻ���Ʒ
		List<Good> goods = new ArrayList<Good>();
		Good go;
		
		//���������
		for (SolrDocument doc : list) {
			//ʵ������Ʒ
			go = new Good();
			
			//�����Ʒid
			go.setGid(doc.get("id").toString());
			
			//��ø�id���Ƿ��и����ı�
			List<String> list2 = highLighting.get(doc.get("id")).get("good_name");
			//�����Ʒ����
			if(list2 != null) {
				go.setGname(list2.get(0));
			} else {
				go.setGname(doc.get("good_name").toString());
			}
			
			//�����Ʒ�۸�
			go.setPrice((float) doc.get("good_price"));
			
			//��Ʒ����
			go.setBriefing(doc.get("good_briefing").toString());
			
			//��ƷͼƬ
			go.setPicture(doc.get("good_picture").toString());
			
			//����Ʒ�洢��������
			goods.add(go);
		}
		
		//�������ģ��
		ResultModel model = new ResultModel();
		
		//������Ʒ����
		model.setList(goods);
		
		//����������
		model.setRecordCount(recordCount);
		
		//����ҳ��
		int pageCount = (int) (recordCount/6);
		pageCount = pageCount + 1;
		model.setPageCount(pageCount);
		
		//���õ�ǰҳ��
		model.setCurrentPage(currentpage);
		
		return model;
	}
}
