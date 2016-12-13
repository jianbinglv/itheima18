package com.taotao.portal.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.taotao.common.util.HttpClientUtil;
import com.taotao.common.util.TaotaoResult;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemDesc;
import com.taotao.pojo.TbItemParamItem;
import com.taotao.portal.service.ItemService;

/**
 * 商品信息service
 * <p>Title: ItemServiceImpl</p>
 * <p>Description: </p>
 * <p>Company: isoftstone</p> 
 * @author	jianbinglv
 * @date	2016年12月9日上午11:08:24
 * @version 1.0
 */
@Service
public class ItemServiceImpl implements ItemService {

	@Value("${REST_SERVICE_ADDRESS}")
	private String REST_SERVICE_ADDRESS;
	
	/**
	 * 得到商品基本信息
	 * <p>Title: getItemById</p>
	 * @param iid
	 * @return {@link TbItem}
	 */
	public TbItem getItemById(Long iid) {
		String url = this.REST_SERVICE_ADDRESS+"/item/base/"+iid;
		String itemJson = HttpClientUtil.doGet(url);
		TaotaoResult result= TaotaoResult.formatToPojo(itemJson, TbItem.class);
		TbItem item = (TbItem) result.getData(); 
		return item;
	}
	/**
	 * 得到商品描述
	 * <p>Title: getItemDescById</p>
	 * <p>Description: </p>
	 * @param iid
	 */
	public String getItemDescById(Long iid) {
		String url = this.REST_SERVICE_ADDRESS+"/item/desc/"+iid;
		String descJson = HttpClientUtil.doGet(url);
		TaotaoResult taotaoResult = TaotaoResult.formatToPojo(descJson, TbItemDesc.class);
		TbItemDesc itemDesc = (TbItemDesc) taotaoResult.getData();
		String desc = itemDesc.getItemDesc();
		return desc;
	}
	/**
	 * 得到商品规格参数信息
	 * <p>Title: getItemParamById</p>
	 * <p>Description: </p>
	 * @param iid
	 * @return
	 */
	public String getItemParamById(Long iid){
		String url = this.REST_SERVICE_ADDRESS+"/item/param/"+iid;
		String paramJson = HttpClientUtil.doGet(url);
		TaotaoResult taotaoResult = TaotaoResult.formatToPojo(paramJson, TbItemParamItem.class);
		TbItemParamItem itemParam = (TbItemParamItem)taotaoResult.getData();
		String result = itemParam.getParamData();
		return result;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
