package com.taotao.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.miemiedev.mybatis.paginator.springmvc.PageListAttrHandlerInterceptor;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.common.pojo.EsasyUIDataGridResult;
import com.taotao.mapper.ItemParamWtihCatNameMapper;
import com.taotao.pojo.ItemParamWtihCatName;
import com.taotao.service.ItemParamService;

/**
 * 
 * <p>Title: ItemParamServiceImpl</p>
 * <p>Description: </p>
 * <p>Company: www.itcast.com</p> 
 * @author	入云龙
 * @date	2016年12月2日下午11:22:17
 * @version 1.0
 */

@Service
public class ItemParamServiceImpl implements ItemParamService {

	@Autowired
	private ItemParamWtihCatNameMapper itemParamWtihCatNameMapper;
	@Override
	public EsasyUIDataGridResult getItemParamWtihCatName(int pageNum,int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		List<ItemParamWtihCatName> list = this.itemParamWtihCatNameMapper.getItemParamWtihCatName();
		PageInfo<ItemParamWtihCatName> info = new PageInfo<ItemParamWtihCatName>(list);
		EsasyUIDataGridResult result = new EsasyUIDataGridResult();
		result.setRows(list);
		result.setTotal(info.getTotal());
		return result;
	}

}
