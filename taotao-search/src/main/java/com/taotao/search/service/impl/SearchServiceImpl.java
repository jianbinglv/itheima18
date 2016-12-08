package com.taotao.search.service.impl;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taotao.common.pojo.SearchResult;
import com.taotao.search.dao.SearchDao;
import com.taotao.search.service.SearchService;

@Service
public class SearchServiceImpl implements SearchService{

	@Autowired
	private SearchDao searchDao;
	public SearchResult search(String quertStr, int page, int rows) throws SolrServerException {
		SolrQuery query = new SolrQuery();
		query.setQuery(quertStr);
		//设置分页
		query.setStart((page-1)*rows);
		query.setRows(rows);
		//设置搜索域
		query.set("df", "item_title");
		query.setHighlight(true);
		query.addHighlightField("item_title");
		query.setHighlightSimplePre("<font class=\"skcolor_ljg\">");
		query.setHighlightSimplePost("</font>");
		//执行查询
		SearchResult result = searchDao.search(query);
		Long recordCount = result.getRecordCount();
		int pageCount = (int) (recordCount/rows);
		if(recordCount%rows>0){
			pageCount++;
		}
		result.setPageCount(pageCount);
		result.setCurPage(page);
		return result;
	}

}
