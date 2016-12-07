package com.taotao.rest.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.taotao.common.util.JsonUtils;
import com.taotao.common.util.TaotaoResult;
import com.taotao.mapper.TbContentMapper;
import com.taotao.pojo.TbContent;
import com.taotao.pojo.TbContentExample;
import com.taotao.pojo.TbContentExample.Criteria;
import com.taotao.rest.dao.JedisClient;
import com.taotao.rest.dao.impl.JedisClientCluster;
import com.taotao.rest.service.ContentService;
/**
 * 显示网站内容service
 * <p>Title: ContentServiceImpl</p>
 * <p>Description: </p>
 * <p>Company: isoftstone</p> 
 * @author	jianbinglv
 * @date	2016年12月7日上午11:10:23
 * @version 1.0
 */

@Service
public class ContentServiceImpl implements ContentService{

	@Autowired
	private TbContentMapper tbContentMapper;
	
	@Autowired
	private JedisClient jedisClient;
	
	@Value("${REDIS_CONTENT_KEY}")
	private String REDIS_CONTENT_KEY;
	public TaotaoResult showContentList(Long cid) {
		//添加redis缓存
		try {
			
			String json = jedisClient.hget(REDIS_CONTENT_KEY, cid+"");
			if(!StringUtils.isBlank(json)){
				//不为空
				System.out.println("redis中不为空");
				List<TbContent> list = JsonUtils.jsonToList(json, TbContent.class);
				return TaotaoResult.ok(list);
			}
		} catch (Exception e) {
		}
		
		
		//为空则查询数据库
		System.out.println("缓存为空");
		TbContentExample example = new TbContentExample();
		Criteria criteria = example.createCriteria();
		criteria.andCategoryIdEqualTo(cid);
		List<TbContent> list = this.tbContentMapper.selectByExample(example);
	
		//保存list到redis缓存
		try {
			jedisClient.hset(this.REDIS_CONTENT_KEY, cid+"", JsonUtils.objectToJson(list));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return TaotaoResult.ok(list);
	}
	@Override
	public TaotaoResult syncRedisContent(Long cid) {
		jedisClient.hdel(REDIS_CONTENT_KEY, cid+"");
		return TaotaoResult.ok();
	}
}
