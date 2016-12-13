package com.taotao.search.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.common.util.ExceptionUtil;
import com.taotao.common.util.TaotaoResult;
import com.taotao.search.service.ItemImportService;

@Controller
@Scope("prototype")
public class ItemImortController {

	@Autowired
	private ItemImportService itemImportService;
	
	@RequestMapping("/importall")
	@ResponseBody
	public TaotaoResult importItemAll(){
		try {
			return this.itemImportService.importItem();
		} catch (Exception e) {
			e.printStackTrace();
			return TaotaoResult.build(500, ExceptionUtil.getStackTrace(e));
		}
	}
	@RequestMapping("/importOne")
	@ResponseBody
	public TaotaoResult importOneItem(Long iId){
		try {
			return this.itemImportService.importOneItem(iId);
		} catch (Exception e) {
			e.printStackTrace();
			return TaotaoResult.build(500, ExceptionUtil.getStackTrace(e));
		}
	}
}
