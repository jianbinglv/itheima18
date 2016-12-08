package com.taotao.rest.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.taotao.common.util.JsonUtils;
import com.taotao.mapper.TbItemDescMapper;
import com.taotao.mapper.TbItemParamMapper;
import com.taotao.pojo.TbItemParam;
import com.taotao.pojo.TbItemParamExample;
import com.taotao.rest.dao.JedisClient;
import com.taotao.rest.service.ItemParamService;
@Service
public class ItemParamServiceImpl implements ItemParamService{
	
	@Autowired
	private TbItemParamMapper itemParamMapper;
	@Autowired
	private JedisClient jedisClient;
	@Value("${REDIS_ITEM_PARAM_KEY}")
	private String REDIS_ITEM_PARAM_KEY;
	@Value("${REDIS_ITEM_EXPIRE_TIME}")
	private Integer REDIS_ITEM_EXPIRE_TIME;
	
	
	
	public TbItemParam getItemParam(Long iid) {
		String itemParamKey = this.REDIS_ITEM_PARAM_KEY+iid;
		//查询redis缓存中是否有该条记录
		String json = this.jedisClient.get(itemParamKey);
		if(!StringUtils.isBlank(itemParamKey)){
			//查询结果不为空 直接返回
			TbItemParam itemParam = JsonUtils.jsonToPojo(json, TbItemParam.class);
			return itemParam;
		}
		//查询为空
		TbItemParamExample example = new TbItemParamExample();
		TbItemParam itemParamDb = this.itemParamMapper.selectByExampleWithBLOBs(example).get(0);
		//结果放入redis缓存中
		try {
			
			this.jedisClient.set(itemParamKey, JsonUtils.objectToJson(itemParamDb));
			this.jedisClient.expire(itemParamKey, this.REDIS_ITEM_EXPIRE_TIME);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return itemParamDb;
	}

}
