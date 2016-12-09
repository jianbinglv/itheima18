package com.taotao.rest.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.taotao.common.util.JsonUtils;
import com.taotao.mapper.TbItemParamItemMapper;
import com.taotao.pojo.TbItemParamItem;
import com.taotao.pojo.TbItemParamItemExample;
import com.taotao.pojo.TbItemParamItemExample.Criteria;
import com.taotao.rest.dao.JedisClient;
import com.taotao.rest.service.ItemParamItemService;
@Service
public class ItemParamItemServiceImpl implements ItemParamItemService{
	
	@Autowired
	private TbItemParamItemMapper itemParamItemMapper;
	@Autowired
	private JedisClient jedisClient;
	@Value("${REDIS_ITEM_PARAM_KEY}")
	private String REDIS_ITEM_PARAM_KEY;
	@Value("${REDIS_ITEM_EXPIRE_TIME}")
	private Integer REDIS_ITEM_EXPIRE_TIME;
	
	
	
	public TbItemParamItem getItemParam(Long iid) {
		String itemParamKey = this.REDIS_ITEM_PARAM_KEY+iid;
		//查询redis缓存中是否有该条记录
		
		try {
			String json = this.jedisClient.get(itemParamKey);
			
			if(!StringUtils.isBlank(json)){
				//查询结果不为空 直接返回
				TbItemParamItem itemParam = JsonUtils.jsonToPojo(json, TbItemParamItem.class);
				return itemParam;
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		//查询为空
		TbItemParamItemExample example = new TbItemParamItemExample();
		Criteria criteria = example.createCriteria();
		criteria.andItemIdEqualTo(iid);
		List<TbItemParamItem> list = this.itemParamItemMapper.selectByExampleWithBLOBs(example);
		TbItemParamItem itemParamitemDb=null;
		if(list!=null&&list.size()>0){
			
			itemParamitemDb = list.get(0);
		}
		//结果放入redis缓存中
		try {
			
			this.jedisClient.set(itemParamKey, JsonUtils.objectToJson(itemParamitemDb));
			this.jedisClient.expire(itemParamKey, this.REDIS_ITEM_EXPIRE_TIME);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return itemParamitemDb;
	}

}
