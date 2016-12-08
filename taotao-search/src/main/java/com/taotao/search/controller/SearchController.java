package com.taotao.search.controller;

import org.apache.solr.client.solrj.SolrServerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.common.pojo.SearchResult;
import com.taotao.common.util.ExceptionUtil;
import com.taotao.common.util.TaotaoResult;
import com.taotao.search.service.SearchService;

@Controller
@Scope("prototype")
public class SearchController {

	@SuppressWarnings("unused")
	@Autowired
	private SearchService searchService;
	
	@RequestMapping("/q")
	@ResponseBody
	public TaotaoResult search(@RequestParam(defaultValue="")String keyword,
			@RequestParam(defaultValue="30") Integer rows,
			@RequestParam(defaultValue="1")Integer page){
		try {
			keyword = new String(keyword.getBytes("iso8859-1"),"utf-8");
			SearchResult result =this.searchService.search(keyword, page, rows);
			return TaotaoResult.ok(result);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return TaotaoResult.build(500, ExceptionUtil.getStackTrace(e));
					
		}
	}
}
