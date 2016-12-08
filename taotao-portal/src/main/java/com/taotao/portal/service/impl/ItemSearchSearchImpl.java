package com.taotao.portal.service.impl;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.taotao.common.pojo.SearchItem;
import com.taotao.common.pojo.SearchResult;
import com.taotao.common.util.HttpClientUtil;
import com.taotao.common.util.JsonUtils;
import com.taotao.common.util.TaotaoResult;
import com.taotao.portal.service.ItemSearchService;

/**
 * 
 * 查询商品service
 * <p>Title: ItemSearchSearchImpl</p>
 * <p>Description: </p>
 * <p>Company: isoftstone</p> 
 * @author	jianbinglv
 * @date	2016年12月8日下午4:28:46
 * @version 1.0
 */

@Service
public class ItemSearchSearchImpl implements ItemSearchService{

	@Value("${SOLR_ADDRESS}")
	private String SOLR_ADDRESS;
	
	public SearchResult searchItem(String query, Integer currentPage, Integer rows) {
		StringBuffer url = new StringBuffer();
		url.append(SOLR_ADDRESS);
		url.append("?keyword=");
		try {
			query = new String(query.getBytes("iso8859-1"),"utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		url.append(query);
		url.append("&page="+currentPage);
		url.append("&rows="+rows);
		
		String json = HttpClientUtil.doGet(url.toString());
		TaotaoResult taotaoResult = TaotaoResult.formatToPojo(json, SearchResult.class);
		
		SearchResult result = (SearchResult) taotaoResult.getData();
		return result;
	}

}

























