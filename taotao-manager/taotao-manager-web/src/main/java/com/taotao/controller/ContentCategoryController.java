package com.taotao.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.common.pojo.EasyUITreeNode;
import com.taotao.common.util.TaotaoResult;
import com.taotao.service.ContentCategoryService;

/**
 *商城门户页面内容管理
 * <p>Title: ContentController</p>
 * <p>Description: </p>
 * <p>Company: isoftstone</p> 
 * @author	jianbinglv
 * @date	2016年12月5日下午4:32:13
 * @version 1.0
 */

@Controller
@Scope("prototype")
public class ContentCategoryController {

	
	@Autowired
	private ContentCategoryService contentService;
	
	@RequestMapping("/content/category/list")
	@ResponseBody
	public List<EasyUITreeNode> showContentPage(@RequestParam(value="id",defaultValue="0") Long pid){
		
		List<EasyUITreeNode> list = this.contentService.getContentCategory(pid);
		return list;
	}
	///content/query/list?categoryId=0&page=1&rows=20
	
	@RequestMapping("/content/category/create")
	@ResponseBody
	public TaotaoResult createContentCategory(@RequestParam(value="name")String nodename,@RequestParam(value="parentId") Long parentId){
		return this.contentService.insertContentCategoryNode(nodename,parentId);
	}
	@RequestMapping("/content/category/update")
	@ResponseBody
	public TaotaoResult updateContentCategory(@RequestParam(value="id") Long id, String name){
		
		return this.contentService.updateContentCategoryNode(name, id);
		
	}
	
	@RequestMapping("/content/category/delete")
	@ResponseBody
	public TaotaoResult deleteContentCategory(@RequestParam(value="id") Long id,@RequestParam(value="id") Long parentId){

		return this.contentService.deleteContentCategoryNodeById(id,parentId);
		
	}
	
	
}
