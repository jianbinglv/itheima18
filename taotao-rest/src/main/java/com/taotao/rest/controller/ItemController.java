package com.taotao.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.common.util.ExceptionUtil;
import com.taotao.common.util.TaotaoResult;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemDesc;
import com.taotao.pojo.TbItemParamItem;
import com.taotao.rest.service.ItemDescService;
import com.taotao.rest.service.ItemParamItemService;
import com.taotao.rest.service.ItemService;

/**
 * 商品信息controller
 * <p>Title: ItemController</p>
 * <p>Description: </p>
 * <p>Company: isoftstone</p> 
 * @author	jianbinglv
 * @date	2016年12月8日下午10:59:34
 * @version 1.0
 */
@Controller
public class ItemController {
	@Autowired
	private ItemService itemService;
	@Autowired
	private ItemParamItemService itemParamitemService;
	@Autowired
	private ItemDescService itemDescService;
	
	
	/**
	 * 得到商品基本信息服务
	 * <p>Title: getItemBase</p>
	 * <p>Description: </p>
	 * @param itemId
	 * @return
	 */
	
	@RequestMapping("/item/base/{itemId}")
	@ResponseBody
	public TaotaoResult getItemBase(@PathVariable Long itemId){
		try {
			TbItem item = this.itemService.getItem(itemId);
			return TaotaoResult.ok(item);
		} catch (Exception e) {
			e.printStackTrace();
			return TaotaoResult.build(500, ExceptionUtil.getStackTrace(e));
		}
	}
	/**
	 * 得到商品参数信息服务
	 * <p>Title: getItemParam</p>
	 * <p>Description: </p>
	 * @param itemId
	 * @return
	 */
	@RequestMapping("/item/param/{itemId}")
	@ResponseBody
	public TaotaoResult getItemParam(@PathVariable Long itemId){
		TbItemParamItem itemParam = this.itemParamitemService.getItemParam(itemId);
		return TaotaoResult.ok(itemParam);
	}
	
	/**
	 * 得到商品描述信息服务
	 * <p>Title: getItemDesc</p>
	 * <p>Description: </p>
	 * @param itemId
	 * @return
	 */
	@RequestMapping("/item/desc/{itemId}")
	@ResponseBody
	public TaotaoResult getItemDesc(@PathVariable Long itemId){
	
		
		
		TbItemDesc itemDesc = this.itemDescService.getItemDesc(itemId);
		return TaotaoResult.ok(itemDesc);
	}
	
	
}
