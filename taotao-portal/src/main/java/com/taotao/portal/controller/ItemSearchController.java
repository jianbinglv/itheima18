package com.taotao.portal.controller;

import java.io.UnsupportedEncodingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.taotao.common.pojo.SearchResult;
import com.taotao.portal.service.ItemSearchService;

@Controller
@Scope("prototype")
public class ItemSearchController {

	@Autowired
	private ItemSearchService itemSearchService;
	
	
	@RequestMapping("/search")
	public String searchItem(Model model,
			String q,@RequestParam(defaultValue="1")Integer page,@RequestParam(defaultValue="30")Integer rows){
		SearchResult result = this.itemSearchService.searchItem(q,page,rows);
		
		try {
			q = new String(q.getBytes("iso8859-1"),"utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		model.addAttribute("itemList",result.getItemList());
		model.addAttribute("totalPages", result.getPageCount());
		model.addAttribute("page", result.getCurPage());
		model.addAttribute("query", q);
		return "search";
	}
}
