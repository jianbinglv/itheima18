package com.taotao.rest.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.taotao.common.util.JsonUtils;
import com.taotao.mapper.TbItemMapper;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemExample;
import com.taotao.pojo.TbItemExample.Criteria;
import com.taotao.rest.dao.JedisClient;
import com.taotao.rest.service.ItemService;

@Service
public class ItemServiceImpl implements ItemService{

	@Autowired
	private TbItemMapper itemMapper;
	@Autowired
	private JedisClient jedisClient;
	@Value("${REDIS_ITEM_KEY}")
	private String REDIS_ITEM_KEY;
	@Value("${REDIS_ITEM_EXPIRE_TIME}")
	private Integer REDIS_ITEM_EXPIRE_TIME;
	
	@Override
	public TbItem getItem(Long iid) {
		String itemRedisKey = this.REDIS_ITEM_KEY+iid;
		//添加redis缓存
		//查询jedis数据库缓存
		try {
			
			String itemJson = this.jedisClient.get(itemRedisKey);
			if(!StringUtils.isBlank(itemJson)){//有查询结果
				TbItem itemJedis = JsonUtils.jsonToPojo(itemJson, TbItem.class);
				return itemJedis;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		//没有查询到结果
		TbItemExample example = new TbItemExample();
		Criteria criteria = example.createCriteria();
		criteria.andIdEqualTo(iid);
		TbItem item = this.itemMapper.selectByExample(example).get(0);
		//将item添加到redis中
		try {
			String json = JsonUtils.objectToJson(item);
			this.jedisClient.set(itemRedisKey, json);
			jedisClient.expire(itemRedisKey, this.REDIS_ITEM_EXPIRE_TIME);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return item;
	}

}
