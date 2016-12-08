package com.taotao.common.pojo;

import java.util.List;

import com.taotao.common.pojo.SearchItem;


/**
 * 搜索solr服务返回的结果
 * <p>Title: SearchResult</p>
 * <p>Description: </p>
 * <p>Company: isoftstone</p> 
 * @author	jianbinglv
 * @date	2016年12月8日下午2:34:51
 * @version 1.0
 */
public class SearchResult {

	
	
	private List<SearchItem> itemList;
	private Long recordCount;
	private int pageCount;
	private int curPage;
	public List<SearchItem> getItemList() {
		return itemList;
	}
	public void setItemList(List<SearchItem> itemList) {
		this.itemList = itemList;
	}
	public Long getRecordCount() {
		return recordCount;
	}
	public void setRecordCount(Long recordCount) {
		this.recordCount = recordCount;
	}
	public int getPageCount() {
		return pageCount;
	}
	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}
	public int getCurPage() {
		return curPage;
	}
	public void setCurPage(int curPage) {
		this.curPage = curPage;
	}
	
	
	
}