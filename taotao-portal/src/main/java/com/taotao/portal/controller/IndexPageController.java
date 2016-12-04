package com.taotao.portal.controller;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Scope("prototype")
public class IndexPageController {

	@RequestMapping("/index")
	public String showIndexPage(){

		return "index";
	}
}
