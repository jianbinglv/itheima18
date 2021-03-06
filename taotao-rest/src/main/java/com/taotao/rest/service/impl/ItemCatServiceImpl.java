package com.taotao.rest.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.alibaba.druid.util.StringUtils;
import com.taotao.common.util.JsonUtils;
import com.taotao.mapper.TbItemCatMapper;
import com.taotao.pojo.TbItemCat;
import com.taotao.pojo.TbItemCatExample;
import com.taotao.pojo.TbItemCatExample.Criteria;
import com.taotao.rest.dao.JedisClient;
import com.taotao.rest.pojo.CatNode;
import com.taotao.rest.pojo.ItemCatResult;
import com.taotao.rest.service.ItemCatService;

/**
 * Item分类Json数据
 * <p>Title: ItemCatServiceImpl</p>
 * <p>Description: </p>
 * <p>Company: isoftstone</p> 
 * @author	jianbinglv
 * @date	2016年12月5日下午1:24:03
 * @version 1.0
 */



@Service
public class ItemCatServiceImpl implements ItemCatService{

	@Autowired
	private TbItemCatMapper itemCatMapper;
	@Autowired
	private JedisClient jedisClient;

	@Value("${REDIS_ITEM_CATEGORY_KEY}")
	private String REDIS_ITEM_CATEGORY_KEY;
	
	public ItemCatResult getItemCatList() {
		//为商品类目添加缓存
		//从缓存中查询
		String json = jedisClient.get(REDIS_ITEM_CATEGORY_KEY);
		
		if(!StringUtils.isEmpty(json)){
			//查询结果不为空直接返回
			ItemCatResult result = JsonUtils.jsonToPojo(json, ItemCatResult.class);
			return result;
		}
		//不为空
		
		System.out.println("itemcat redis缓存不为空");
		List catList = this.getItemCatList(0L);
		ItemCatResult result = new ItemCatResult();
		result.setData(catList);
		//将结果加入到redis缓存中
		String redisResult = JsonUtils.objectToJson(result);
		this.jedisClient.set(REDIS_ITEM_CATEGORY_KEY, redisResult);
		return result;
	}

	
	private List getItemCatList(Long parentId){
		
		
		//根据parent查询列表
		TbItemCatExample example = new TbItemCatExample();
		Criteria criteria = example.createCriteria();
		criteria.andParentIdEqualTo(parentId);
		List<TbItemCat> list  = this.itemCatMapper.selectByExample(example);
		List resultList = new ArrayList<>();
		int index =0;
		for (TbItemCat tbItemCat : list) {
			if(index>=14){
				break;
			}
			//如果是fu节点
			if(tbItemCat.getIsParent()){
				
			CatNode node = new CatNode();
			node.setUrl("/products/"+tbItemCat.getId()+".html");
			//当前节点为第一级节点
			if(tbItemCat.getParentId()==0){//
				
					node.setName("<a href='"+node.getUrl()+"'>"+tbItemCat.getName()+"</a>");
					index++;
			}else{
					node.setName(tbItemCat.getName());
				}
				node.setItems(getItemCatList(tbItemCat.getId()));
				resultList.add(node);
			}else{
				//是叶子节点
				String item = "/products/"+tbItemCat.getId()+".html|"+tbItemCat.getName();
				resultList.add(item);
			}
		}
		
		return resultList;
		
		
	}
}
