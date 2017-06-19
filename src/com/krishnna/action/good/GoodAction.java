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
	 * ����Good���϶��󡣷���ֵջ��
	 */
	private Good good = new Good();
	public Good getGood() {
		return good;
	}
	
	/**
	 * ��������������󣬷���ֵջ��
	 */
	private ResultModel model = new ResultModel();
	public ResultModel getModel() {
		return model;
	}

	/**
	 * ���GoodService����
	 */
	private GoodService goodService;
	public void setGoodService(GoodService goodService) {
		this.goodService = goodService;
	}

	/**
	 * ���request����
	 */
	HttpServletRequest request = ServletActionContext.getRequest();

	/**
	 * ȫ��ѯ��
	 */
	@Override
	public String execute() throws Exception {
		
		//����service�㷽����
		Good good = new Good();
		List<Good> goodList = goodService.find(good);
		//��goodList�������session����
		request.setAttribute("goodList", goodList);
		
		return "SUCCESS";
	}
	
	/**
	 * ���������ݿ���ݹ����ų�����ǰ�Ÿ�����Ϊ�ر��Ƽ���
	 */
	public String homePageRecommend() throws Exception {
		System.out.println("����homePageRecommend����������������������������");
		/*
		 * ����service�㷽����
		 */
		//������ѯ������һ��������������ֵ���ر��Ƽ���ͼ��
		List<Good> goodListRecommend = goodService.findRecommend();
		//��goodListRecommend�������request����
		request.setAttribute("recommend", goodListRecommend);
		
		//������ѯ������һ��������������ֵ���ر��Ƽ���ͼ��
		List<Good> goodListNews = goodService.findNews();
		// ��goodListRecommend�������request����
		request.setAttribute("newBooks", goodListNews);
		
		return "SUCCESS";
	}

	/**
	 *  ����id��ѯĳ����Ʒ������
	 *  ��ת������ҳ��goodDetail.jsp
	 */
	public String goodDetail(){

		String gid = request.getParameter("gid");

		/*
		 * ����һ�� Good����good1��Ϊservice�㷽���Ĳ����� 
		 */
		Good good1 = new Good();
		good1.setGid(gid);
		//����service�㷽��������Good����goodList��
		List<Good> goodList = goodService.find(good1);
		//��ü�����Good����,����ֵ��good��Ϊ�˴���ֵջ��
		good = goodList.get(0);
		
		return "SUCCESS";
	}
	
	/**
	 * ��ҳ��������������
	 * @throws Exception 
	 */
	public String search() throws Exception {
		
		//����������е��û������ֵ
		String keyword = request.getParameter("keyword");
		if(!"".equals(keyword)&&keyword != null){
			//���������е��û������ֵ������������session����
			request.getSession().setAttribute("keyword", keyword);
		}
		//ȡ���ϴ�������ֵ��
		String oldkeyword = (String) request.getSession().getAttribute("keyword");
		
		if(keyword != null&&keyword.length()>0){
			/*
			 * ������Ϊ��������������keyword��Ϊ��
			 *    ���session���е���������������keyword�����
			 */
			request.getSession().setAttribute("good_bigclass", "");
			request.getSession().setAttribute("good_smallclass", "");
			request.getSession().setAttribute("good_price", "");
		}
		
		//���ͼ������
		String good_bigclass = request.getParameter("good_BigClass");
		
		//��ȡԭ����session��good_bigclass��ֵ�����洢��oldBigClass��
		String oldBigClass = (String) request.getSession().getAttribute("good_bigclass");
		
		/*
		 * �ж�ͼ�������Ƿ�Ϊ�գ�
		 * 	��Ϊ�գ�������ѯ���keywordҲΪ�ա�
		 * 		--��ô��ͼ������ = �ϴβ�ѯͼ�������ֵ
		 *  ����Ϊ�գ�
		 *  	--��ͼ������������������session����
		 */
		if(!"".equals(good_bigclass)&&good_bigclass != null){
			//��ͼ������������������session����
			request.getSession().setAttribute("good_bigclass", good_bigclass);
		} else if("".equals(keyword)||keyword == null){
			//��keyword��Ϊ�գ���˵������ȫ�������������Ͳ��ٲ���
			good_bigclass = (String) request.getSession().getAttribute("good_bigclass");
		}
		
		//���ͼ��ϸ��
		String good_smallclass = request.getParameter("good_SmallClass");
		String oldSmallClass = null;//ԭ���洢��session�е�good_classֵ

		//��ȡԭ����session��good_smallclass��ֵ�����洢��oldSmallClass��
		oldSmallClass = (String) request.getSession().getAttribute("good_smallclass");
		
		/*
		 * �ж�ͼ��ϸ���Ƿ�Ϊ�գ�
		 * 	��Ϊ�գ�������ѯ���keywordҲΪ�ա�
		 * 		--��ô��ͼ��ϸ�� = �ϴβ�ѯͼ��ϸ���ֵ
		 *  ����Ϊ�գ�
		 *  	--��ͼ������������������session����
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
		
		
		//��ü۸�����
		String good_price = request.getParameter("good_price");
		
		/*
		 * �жϼ۸������Ƿ�Ϊ�գ�
		 * 	��Ϊ�գ�������ѯ���keywordҲΪ�ա�
		 * 		--��ô�ü۸����� = �ϴβ�ѯ�۸������ֵ
		 *  ����Ϊ�գ�
		 *  	--���۸�����������������session����
		 */
		if(!"".equals(good_price)&&good_price != null){
			request.getSession().setAttribute("good_price", good_price);
		} else if("".equals(keyword)||keyword == null) {
			good_price = (String) request.getSession().getAttribute("good_price");
		}
		
		/*
		 * ������²���ͼ��ϸ���࣬�Һ��ϴβ���ͼ��ϸ�����ֵ��һ���������ͼ��ϸ������һ������
		 * ��������-->���۸�����������Ϊ��
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
					 * �����ڵ�ͼ��ϸ�����������������ĸ�����������һ��
					 * ���ü۸�����������Ϊ�ա�
					 * ����session��good_priceҲ��Ϊ���ַ���
					 */
					good_price = null;
					request.getSession().setAttribute("good_price", "");
				}
			}
		}
		
		
		//������ѯ���Ϊnull������Ϊ���ַ���
		if(keyword == null){
			keyword="";
		}
		
		//�����������
		String good_order = request.getParameter("good_order");
		
		/*
		 * ������������Ϊ�գ�
		 *   ��session���е�good_smallclass��Ϊ��
		 *   	��good_smallclass = session�е�good_smallclass
		 *   ��session���е�good_price��Ϊ��
		 * 		��good_price = session�е�good_price
		 */
		
		//��õ�ǰҳ����Ĭ��Ϊ1
		int currentPage = 1;
		String flag = request.getParameter("current_page");
		if(!"".equals(flag)&&flag != null){
			currentPage = Integer.parseInt(flag);
		}
		
		/*
		 * ��good_smallclassֵΪall������good_smallClass��Ϊ��
		 * ��good_priceֵΪall������good_price��Ϊ��
		 */
		if("all".equals(good_smallclass)){
			good_smallclass = null;
		}
		if("all".equals(good_price)){
			good_price = null;
		}
		
		//����ҵ���
		model = goodService.search(keyword.trim(),good_bigclass,good_smallclass,good_price,good_order,currentPage);
		
		//����ѯ�����ص�
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
	 * ����ͼ��������Ҷ�Ӧ����ͼ��
	 */
	public String searchByClass(){
		return "SUCCESS";
	}
}
