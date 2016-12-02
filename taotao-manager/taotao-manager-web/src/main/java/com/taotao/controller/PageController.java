package com.taotao.controller;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 页面跳转controller
 * <p>Title: PageController</p>
 * <p>Description: </p>
 * <p>Company: isoftstone</p> 
 * @author	jianbinglv
 * @date	2016年11月29日下午8:38:04
 * @version 1.0
 */

@Controller
@Scope("prototype")
public class PageController {

	
	@RequestMapping("/")
	public String showIndex(){
	
		return "index";
	}
	
	@RequestMapping("/{page}")
	public String showPage(@PathVariable String page){
		return page;
	}
	
	
}
