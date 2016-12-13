package com.taotao.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.common.pojo.EsasyUIDataGridResult;
import com.taotao.common.util.HttpClientUtil;
import com.taotao.common.util.IDUtils;
import com.taotao.common.util.TaotaoResult;
import com.taotao.mapper.TbItemDescMapper;
import com.taotao.mapper.TbItemMapper;
import com.taotao.mapper.TbItemParamItemMapper;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemDesc;
import com.taotao.pojo.TbItemExample;
import com.taotao.pojo.TbItemExample.Criteria;
import com.taotao.pojo.TbItemParamItem;
import com.taotao.service.ItemService;

/**
 * 
 * <p>Title: ItemServiceImpl</p>
 * <p>Description: </p>
 * <p>Company: isoftstone</p> 
 * @author	jianbinglv
 * @date	2016年11月29日下午5:22:56
 * @version 1.0
 */

@Service("itemService")
public class ItemServiceImpl implements ItemService{
	
	@Autowired
	private TbItemMapper itemMapper;
	@Autowired
	private TbItemDescMapper itemDescMapper;
	@Autowired
	private TbItemParamItemMapper tbItemParamItemMapper;
	
	
	@Override
	public TbItem getItemById(Long itemId) {
		// TODO Auto-generated method stub
		TbItemExample example = new TbItemExample();
		Criteria criteria = example.createCriteria();
		
		criteria.andIdEqualTo(itemId);
		List<TbItem> list = itemMapper.selectByExample(example);
		TbItem item = null; 
		
		if(list.size()>0&&list!=null){
			item = list.get(0);
		}
		//方式二
		//TbItem item =  itemMapper.selectByPrimaryKey(itemId);
		return item;
	}

	@Override
	public EsasyUIDataGridResult getItemList(int page, int rows) {
		//分也处理
		PageHelper.startPage(page, rows);
		//执行查询
		TbItemExample example = new TbItemExample();
		List<TbItem> list = itemMapper.selectByExample(example);
		PageInfo<TbItem> pageInfo = new PageInfo<TbItem>(list);
		EsasyUIDataGridResult easyUIDataGridResult = new EsasyUIDataGridResult();
		easyUIDataGridResult.setRows(list);
		easyUIDataGridResult.setTotal(pageInfo.getTotal());
		
		return easyUIDataGridResult;
	}

	@Override
	public TaotaoResult insertItem(TbItem item,String desc,String itemParams) {
		Date date = new Date();
		
		//保存商品实体
		item.setId(IDUtils.genItemId());
		item.setUpdated(date);
		item.setCreated(date);
		item.setStatus((byte)1);
		this.itemMapper.insert(item);
		//保存商品描述
		TbItemDesc itemDesc = new TbItemDesc();
		itemDesc.setItemId(item.getId());
		itemDesc.setCreated(date);
		itemDesc.setUpdated(date);
		itemDesc.setItemDesc(desc);
		this.itemDescMapper.insert(itemDesc);
		//保存商品规格参数
		TbItemParamItem itemParamItem = new TbItemParamItem();
		itemParamItem.setCreated(date);
		itemParamItem.setUpdated(date);
		itemParamItem.setItemId(item.getId());
		itemParamItem.setParamData(itemParams);
		this.tbItemParamItemMapper.insert(itemParamItem);
		//添加到solr索引库中
		/*HttpClientUtil.doGet("http://localhost:8083/search/importOne?iId="+item.getId());*/
		
		return TaotaoResult.ok();
	}

}
