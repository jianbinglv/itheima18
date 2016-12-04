package com.taotao.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taotao.mapper.TbItemParamItemMapper;
import com.taotao.pojo.TbItemParamItem;
import com.taotao.pojo.TbItemParamItemExample;
import com.taotao.pojo.TbItemParamItemExample.Criteria;
import com.taotao.service.ItemParamItemService;

@Service
public class ItemParamItemServiceImpl implements ItemParamItemService{

	@Autowired
	private TbItemParamItemMapper tbItemParamItemMapper; 
	
	public TbItemParamItem showItemParamItem(Long itemId) {
		
		TbItemParamItemExample example = new TbItemParamItemExample();
		Criteria criteria= example.createCriteria();
		criteria.andItemIdEqualTo(itemId);
		List<TbItemParamItem> list = this.tbItemParamItemMapper.selectByExampleWithBLOBs(example);
		if(list.size()>0&&list!=null){
			return list.get(0);
		}else{
			return null;
		}
		
	}

}
