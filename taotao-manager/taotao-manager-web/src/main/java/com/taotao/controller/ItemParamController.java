package com.taotao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.common.pojo.EsasyUIDataGridResult;
import com.taotao.common.util.TaotaoResult;
import com.taotao.service.ItemParamService;

/**
 * 商品类目
 * <p>Title: ItemParamController</p>
 * <p>Description: </p>
 * <p>Company: www.itcast.com</p> 
 * @author	入云龙
 * @date	2016年12月2日下午11:28:28
 * @version 1.0
 */
@Controller
@Scope("prototype")
public class ItemParamController {

	@Autowired
	private ItemParamService itemParamService;
	
	
	@RequestMapping("/item/param/list")
	@ResponseBody
	public EsasyUIDataGridResult itemParamList(Integer page,Integer rows){
		return this.itemParamService.getItemParamWtihCatName(page, rows);
	}
	
	@RequestMapping("item/param/query/itemcatid/{cid}")
	@ResponseBody
	public TaotaoResult isItemParamExist(@PathVariable Long cid){
		return this.itemParamService.isItemParamExist(cid);
	}
	
	@RequestMapping("/item/param/save/{cid}")
	@ResponseBody
	public TaotaoResult insertItemParam(@PathVariable Long cid,String paramData){
		return this.itemParamService.insertItemParam(cid, paramData);
	}
	
	@RequestMapping("/item/param/delete")
	@ResponseBody
	public TaotaoResult deleteItemParam(String ids){
		return this.itemParamService.deleteItemParamByids(ids);
	}
	
}
