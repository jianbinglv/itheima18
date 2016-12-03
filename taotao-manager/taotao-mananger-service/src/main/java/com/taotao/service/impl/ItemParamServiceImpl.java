package com.taotao.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.miemiedev.mybatis.paginator.springmvc.PageListAttrHandlerInterceptor;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.common.pojo.EsasyUIDataGridResult;
import com.taotao.common.util.TaotaoResult;
import com.taotao.mapper.ItemParamWtihCatNameMapper;
import com.taotao.mapper.TbItemParamItemMapper;
import com.taotao.mapper.TbItemParamMapper;
import com.taotao.pojo.ItemParamWtihCatName;
import com.taotao.pojo.TbItemParam;
import com.taotao.pojo.TbItemParamExample;
import com.taotao.pojo.TbItemParamExample.Criteria;
import com.taotao.service.ItemParamService;

/**
 * 商品规格参数
 * <p>Title: ItemParamServiceImpl</p>
 * <p>Description: </p>
 * <p>Company: isoftstone</p> 
 * @author	jianbinglv
 * @date	2016年12月3日上午9:44:52
 * @version 1.0
 */

@Service
public class ItemParamServiceImpl implements ItemParamService {

	@Autowired
	private ItemParamWtihCatNameMapper itemParamWtihCatNameMapper;
	@Autowired
	private TbItemParamMapper tbItemParamMapper;
	
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

	@Override
	public TaotaoResult isItemParamExist(Long cid) {
		TbItemParamExample example = new TbItemParamExample();
		Criteria criteria = example.createCriteria();
		criteria.andItemCatIdEqualTo(cid);
		List<TbItemParam> list = this.tbItemParamMapper.selectByExampleWithBLOBs(example);
		if(list!=null&&list.size()>0){
			return TaotaoResult.ok(list.get(0));
		}else{
			return TaotaoResult.ok();
		}
	}

	@Override
	public TaotaoResult insertItemParam(Long cid, String paramData) {
		TbItemParam item = new TbItemParam();
		Date  date = new Date();
		item.setCreated(date);
		item.setUpdated(date);
		item.setParamData(paramData);
		item.setItemCatId(cid);
		this.tbItemParamMapper.insert(item);
		return TaotaoResult.ok();
	}

	@Override
	public TaotaoResult deleteItemParamByids(String ids) {
		String[] idsStr = ids.split(",");
		for (int i = 0; i < idsStr.length; i++) {
			String str= idsStr[i];
			this.tbItemParamMapper.deleteByPrimaryKey(Long.parseLong(str));
		}
		return TaotaoResult.ok();
	}
	
}
