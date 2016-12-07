package com.taotao.rest.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taotao.common.util.TaotaoResult;
import com.taotao.mapper.TbContentMapper;
import com.taotao.pojo.TbContent;
import com.taotao.pojo.TbContentExample;
import com.taotao.pojo.TbContentExample.Criteria;
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

	@Autowired()
	private TbContentMapper tbContentMapper;

	public TaotaoResult showContentList(Long cid) {
		TbContentExample example = new TbContentExample();
		Criteria criteria = example.createCriteria();
		criteria.andCategoryIdEqualTo(cid);
		List<TbContent> list = this.tbContentMapper.selectByExample(example);
		return TaotaoResult.ok(list);
	}

}
