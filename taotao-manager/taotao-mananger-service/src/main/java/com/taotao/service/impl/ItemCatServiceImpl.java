package com.taotao.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.taotao.common.pojo.EasyUITreeNode;
import com.taotao.mapper.TbItemCatMapper;
import com.taotao.pojo.TbItemCat;
import com.taotao.pojo.TbItemCatExample;
import com.taotao.pojo.TbItemCatExample.Criteria;
import com.taotao.service.ItemCatService;

/**
 * 商品分类管理
 * <p>Title: ItemCatServiceImpl</p>
 * <p>Description: </p>
 * <p>Company: isoftstone</p> 
 * @author	jianbinglv
 * @date	2016年11月30日上午12:05:08
 * @version 1.0
 */

@Service("itemCatService")
public class ItemCatServiceImpl implements ItemCatService{

	@Resource(name="tbItemCatMapper")
	private TbItemCatMapper itemCatMapper;

	@Override
	public List<EasyUITreeNode> getItemCatList(long catId) {
		//根据parentid查询分类列表
		
		TbItemCatExample itemCatExample = new TbItemCatExample();
		//设置查询条件
		Criteria criteria = itemCatExample.createCriteria();
		criteria.andParentIdEqualTo(catId);
		List<TbItemCat> list = this.itemCatMapper.selectByExample(itemCatExample);
		List<EasyUITreeNode> resultList = new ArrayList<EasyUITreeNode>();
		for (TbItemCat tbItemCat : list) {
			EasyUITreeNode node = new EasyUITreeNode();
			node.setId(tbItemCat.getId());
			node.setText(tbItemCat.getName());
			node.setState(tbItemCat.getIsParent()?"closed":"open");
			resultList.add(node);
		}
		return resultList;
	}
	
	
	
}
























