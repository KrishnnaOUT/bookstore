package com.krishnna.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * 搜索结果实体类
 * @author Ningkui
 *
 */
public class ResultModel {

	List<Good> list = new ArrayList<Good>();
	
	private long recordCount; //记录总数
	
	private int pageCount; //总页数
	
	private int currentPage;//当前页

	public List<Good> getList() {
		return list;
	}

	public void setList(List<Good> list) {
		this.list = list;
	}

	public long getRecordCount() {
		return recordCount;
	}

	public void setRecordCount(long recordCount) {
		this.recordCount = recordCount;
	}

	public int getPageCount() {
		return pageCount;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	
	
}
