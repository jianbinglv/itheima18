package com.taotao.portal.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.taotao.portal.service.ContentService;

@Controller
@Scope("prototype")
public class IndexPageController {

	@Autowired
	private ContentService contentService;
	
	
	@RequestMapping("/index")
	public String showIndexPage(Model model){
		//取大广告位内容
		String json = this.contentService.getContentList();
		model.addAttribute("ad1", json);
		return "index";
	}
	
	
}
