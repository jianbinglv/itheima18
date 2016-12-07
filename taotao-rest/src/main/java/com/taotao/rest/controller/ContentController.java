package com.taotao.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.common.util.ExceptionUtil;
import com.taotao.common.util.TaotaoResult;
import com.taotao.rest.service.ContentService;

@Controller
@Scope("prototype")
/**
 * rest服务：得到content 列表JSON
 * <p>Title: ContentController</p>
 * <p>Description: </p>
 * <p>Company: isoftstone</p> 
 * @author	jianbinglv
 * @date	2016年12月7日上午11:15:58
 * @version 1.0
 */
public class ContentController {

	@Autowired
	private ContentService contentService;
	
	
	@RequestMapping("/content/{cid}")
	@ResponseBody
	public TaotaoResult getContentList(@PathVariable Long cid){
		try {
			return contentService.showContentList(cid);
			
		} catch (Exception e) {
			e.printStackTrace();
			return TaotaoResult.build(500, ExceptionUtil.getStackTrace(e));
		}
	}
	
	
}
