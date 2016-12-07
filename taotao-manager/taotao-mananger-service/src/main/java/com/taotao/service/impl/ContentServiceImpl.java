package com.taotao.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.common.pojo.EsasyUIDataGridResult;
import com.taotao.common.util.HttpClientUtil;
import com.taotao.common.util.TaotaoResult;
import com.taotao.mapper.TbContentMapper;
import com.taotao.pojo.TbContent;
import com.taotao.pojo.TbContentExample;
import com.taotao.pojo.TbContentExample.Criteria;
import com.taotao.service.ContentService;

@Service
public class ContentServiceImpl implements ContentService{

	
	@Autowired
	private TbContentMapper contentMapper;
	@Override
	public EsasyUIDataGridResult selectContentList(Long catId,Integer currentPage, Integer pageSize) {
		EsasyUIDataGridResult result = new EsasyUIDataGridResult();
		
		TbContentExample example = new TbContentExample();
		Criteria criteria = example.createCriteria();
		//如果catId=0或者为空时取全部的content
		if(!(catId==0||catId==null)){
			criteria.andCategoryIdEqualTo(catId);
		}
		PageHelper.startPage(currentPage, pageSize);
		List<TbContent> list = this.contentMapper.selectByExample(example);
		
		PageInfo<TbContent> info = new PageInfo<TbContent>(list);
		result.setRows(list);
		result.setTotal(info.getTotal());
		return result;
	}
	
	//保存content
	@Override
	public TaotaoResult insertContent(TbContent content) {
		Date date = new Date();
		content.setCreated(date);
		content.setUpdated(date);
		this.contentMapper.insert(content);
		return TaotaoResult.ok();
	}

}









