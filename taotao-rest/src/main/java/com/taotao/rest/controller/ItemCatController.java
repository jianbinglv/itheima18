package com.taotao.rest.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.common.util.JsonUtils;
import com.taotao.rest.pojo.ItemCatResult;
import com.taotao.rest.service.ItemCatService;

/**
 * 商品分类详情controller
 * <p>Title: ItemCatController</p>
 * <p>Description: </p>
 * <p>Company: isoftstone</p> 
 * @author	jianbinglv
 * @date	2016年12月5日下午2:35:00
 * @version 1.0
 */

@Controller
@Scope("prototype")
@RequestMapping("/item/cat")
public class ItemCatController {

	@Autowired
	private ItemCatService itemCatService;
	
	/*@RequestMapping("/list")
	@ResponseBody
	public ItemCatResult showItemList(){
		return this.itemCatService.getItemCatList();
	} */
	@RequestMapping(value="/list",produces=MediaType.APPLICATION_JSON_VALUE+";charset=utf-8")
	@ResponseBody
	public String showItemCatList(String callback){

		ItemCatResult result = this.itemCatService.getItemCatList();
		String json = JsonUtils.objectToJson(result);
		if(StringUtils.isBlank(callback)){
			return json;
		
		}else{
			//支持jsonp调用
			return callback+"("+json+")";
		}
	} 
	
}
