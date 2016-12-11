package com.taotao.portal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.common.util.ExceptionUtil;
import com.taotao.common.util.TaotaoResult;
import com.taotao.portal.service.StaticPageGenService;

@Controller
@Scope("prototype")
public class StaticPageGenController {

	
	@Autowired
	private StaticPageGenService staticPageGenService;
	
	@RequestMapping("/gen/{itemId}")
	@ResponseBody
	public TaotaoResult genItemPage(@PathVariable Long itemId){
		try {
			TaotaoResult result = this.staticPageGenService.genItemHtml(itemId);
			System.out.println("成功");
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return TaotaoResult.build(500,ExceptionUtil.getStackTrace(e));
		}
	}
	
}
