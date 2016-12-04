package com.taotao.controller;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.common.util.JsonUtils;
import com.taotao.pojo.TbItemParamItem;
import com.taotao.service.ItemParamItemService;


/**
 * 展示商品规格参数
 * <p>Title: ItemParamItemController</p>
 * <p>Description: </p>
 * <p>Company: isoftstone</p> 
 * @author	jianbinglv
 * @date	2016年12月4日下午12:58:20
 * @version 1.0
 */

@Controller
@Scope("prototype")
public class ItemParamItemController {
	@Autowired
	private ItemParamItemService itemParamItemService;
	
	@RequestMapping("/item/param/item/show/{pid}")
	public String showItemParamItem(@PathVariable Long pid,Model model){
		TbItemParamItem itemParamItem = this.itemParamItemService.showItemParamItem(pid);
		String jsonData = itemParamItem.getParamData();
		List<Map> list = JsonUtils.jsonToList(jsonData, Map.class);
		model.addAttribute("itemParamitem",list);
		return "upload";
	}
}
