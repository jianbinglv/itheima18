package com.taotao.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.common.pojo.EsasyUIDataGridResult;
import com.taotao.common.util.TaotaoResult;
import com.taotao.pojo.TbContent;
import com.taotao.service.ContentService;

/**
 * 
 * 后台管理，网页内容分类管理
 * <p>Title: ContentController</p>
 * <p>Description: </p>
 * <p>Company: isoftstone</p> 
 * @author	jianbinglv
 * @date	2016年12月6日上午10:34:36
 * @version 1.0
 */

@Controller
@Scope("prototype")
public class ContentController {
	
	@Autowired
	private ContentService contentService;
	
	//http://localhost:8080/content/query/list?categoryId=0&page=1&rows=20
	@RequestMapping("/content/query/list")
	@ResponseBody
	public EsasyUIDataGridResult showContent(@RequestParam(value="categoryId") Long catId,@RequestParam(value="page") Integer currentPage,Integer rows){
		EsasyUIDataGridResult result = this.contentService.selectContentList(catId, currentPage, rows);
		return result;
	}
	
	//http://localhost:8080/content/save?categoryId=91&content
	@RequestMapping("/content/save")
	@ResponseBody
	public TaotaoResult saveContent(TbContent content){
		System.out.println(content);
		
		return this.contentService.insertContent(content);
		
	}
	
	
}
