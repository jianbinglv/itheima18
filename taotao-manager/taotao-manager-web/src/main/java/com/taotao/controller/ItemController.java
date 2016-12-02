package com.taotao.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.common.pojo.EsasyUIDataGridResult;
import com.taotao.common.util.TaotaoResult;
import com.taotao.pojo.TbItem;
import com.taotao.service.ItemService;

/**
 * 商品查询Controller
 * <p>Title: ItemController</p>
 * <p>Description: </p>
 * <p>Company: isoftstone</p> 
 * @author	jianbinglv
 * @date	2016年11月29日下午5:45:23
 * @version 1.0
 */


@Controller("itemController")
public class ItemController {

	@Resource(name="itemService")
	private ItemService itemService;
	
	
	//根据id查询商品信息
	@RequestMapping("/item/{itemId}")
	@ResponseBody
	public TbItem getItemByid(@PathVariable Long itemId){
		TbItem item = itemService.getItemById(itemId);
		return item;
	}
	
	@RequestMapping("/item/list")
	@ResponseBody
	public EsasyUIDataGridResult listItem(Integer page,Integer rows){
		return this.itemService.getItemList(page, rows);
	}
	
	@RequestMapping("/item/save")
	@ResponseBody
	public TaotaoResult addItem(TbItem item){
		TaotaoResult result = this.itemService.insertItem(item);
		System.out.println("保存成功");
		return result;
	}
}
