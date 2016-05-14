package com.computerstore.domain;

import java.util.List;

public class PageBean<T> {
	private int pageCode;//current page code
	private int pageSize;//records' number on one page
	private int totalCount;//total amount of records from database
	private int totalPage;//total number of pages
	private String url;
	private List<T> pList;//encapsulate the data of current page to collection
	
	public PageBean() {	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public int getPageCode() {
		return pageCode;
	}
	public void setPageCode(int pageCode) {
		this.pageCode = pageCode;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	public int getTotalPage() {
		totalPage = totalCount/pageSize;
		if(totalCount%pageSize != 0){
			totalPage++;
		}
		return totalPage;
	}
	public List<T> getpList() {
		return pList;
	}
	public void setpList(List<T> pList) {
		this.pList = pList;
	}
	
	
}
