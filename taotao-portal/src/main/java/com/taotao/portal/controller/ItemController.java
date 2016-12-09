package com.taotao.portal.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.common.util.JsonUtils;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemParamItem;
import com.taotao.portal.service.ItemService;

@Controller
@Scope("prototype")
public class ItemController {

	@Autowired
	private ItemService itemService;
	
	
	@RequestMapping("/item/{iid}")
	public String showItem(@PathVariable Long iid, Model model){
		
		TbItem item = this.itemService.getItemById(iid);
		//加载商品规格参数
		String jsonData = this.itemService.getItemParamById(iid);
		List<Map> list = JsonUtils.jsonToList(jsonData, Map.class);
		model.addAttribute("itemParamitem",list);
		
		String image = item.getImage();
		String[] images = image.split(",");
		model.addAttribute("images", images);
		model.addAttribute("item",item);
		return "item";
	}
	
	
	@RequestMapping(value="/item/desc/{iid}",produces=MediaType.TEXT_HTML_VALUE+";charset=utf-8")
	@ResponseBody
	public String showItemDesc(@PathVariable Long iid){
		String desc = this.itemService.getItemDescById(iid);
		return desc;
		
		
	}	
	@RequestMapping(value="/item/param/{iid}",produces=MediaType.TEXT_HTML_VALUE+";charset=utf-8")
	@ResponseBody
	public String showItemParam(@PathVariable Long iid,Model model){
		/*String jsonData = this.itemService.getItemParamById(iid);
		List<Map> list = JsonUtils.jsonToList(jsonData, Map.class);
		model.addAttribute("itemParamitem",list);*/
		return "<h3>加载商品规格参数成功</h3>";
	}	
}
