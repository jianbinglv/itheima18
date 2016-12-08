package com.taotao.rest.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.taotao.common.util.JsonUtils;
import com.taotao.mapper.TbItemDescMapper;
import com.taotao.pojo.TbItemDesc;
import com.taotao.pojo.TbItemDescExample;
import com.taotao.pojo.TbItemDescExample.Criteria;
import com.taotao.rest.dao.JedisClient;
import com.taotao.rest.service.ItemDescService;


@Service
public class ItemDescServiceImpl implements ItemDescService{

	
	@Autowired
	private TbItemDescMapper itemDescMapper;
	@Autowired
	private JedisClient jedisClient;
	@Value("${REDIS_ITEM_DESC_KEY}")
	private String REDIS_ITEM_DESC_KEY;
	@Value("${REDIS_ITEM_EXPIRE_TIME}")
	private Integer REDIS_ITEM_EXPIRE_TIME;
	
	@Override
	public TbItemDesc getItemDesc(Long iid) {
		String itemDescRedisKey = this.REDIS_ITEM_DESC_KEY+iid;
		//添加redis缓存
		//查询jedis数据库缓存
		try {
			
			String itemDescJson = this.jedisClient.get(itemDescRedisKey);
			if(!StringUtils.isBlank(itemDescJson)){//有查询结果
			TbItemDesc itemDescJedis = JsonUtils.jsonToPojo(itemDescJson, TbItemDesc.class);
				return itemDescJedis;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		//没有查询到结果
		TbItemDesc item = this.itemDescMapper.selectByPrimaryKey(iid);
		//将item添加到redis中
		try {
			String json = JsonUtils.objectToJson(item);
			this.jedisClient.set(itemDescRedisKey, json);
			jedisClient.expire(itemDescRedisKey, this.REDIS_ITEM_EXPIRE_TIME);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return item;
	}
}
