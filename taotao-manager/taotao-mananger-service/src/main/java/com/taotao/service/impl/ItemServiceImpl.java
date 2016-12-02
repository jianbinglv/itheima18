package com.taotao.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.common.pojo.EsasyUIDataGridResult;
import com.taotao.common.util.IDUtils;
import com.taotao.common.util.TaotaoResult;
import com.taotao.mapper.TbItemMapper;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemExample;
import com.taotao.pojo.TbItemExample.Criteria;
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
	public TaotaoResult insertItem(TbItem item) {
		Date date = new Date();
		item.setId(IDUtils.genItemId());
		item.setUpdated(date);
		item.setCreated(date);
		item.setStatus((byte)1);
		this.itemMapper.insert(item);
		return TaotaoResult.ok();
	}

}
