package com.taotao.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taotao.common.pojo.EasyUITreeNode;
import com.taotao.common.util.TaotaoResult;
import com.taotao.mapper.TbContentCategoryMapper;
import com.taotao.pojo.TbContentCategory;
import com.taotao.pojo.TbContentCategoryExample;
import com.taotao.pojo.TbContentCategoryExample.Criteria;
import com.taotao.service.ContentCategoryService;


/**
 * 后台管理：网站内容管理service
 * <p>Title: ContentServiceImpl</p>
 * <p>Description: </p>
 * <p>Company: isoftstone</p> 
 * @author	jianbinglv
 * @date	2016年12月6日上午9:29:56
 * @version 1.0
 */

@Service
public class ContentCategoryServiceImpl implements ContentCategoryService{

	@Autowired
	private TbContentCategoryMapper tbContentCategoryMapper;
	
	public List<EasyUITreeNode> getContentCategory(Long conPid) {
		List<EasyUITreeNode> result = new ArrayList<EasyUITreeNode>();
		TbContentCategoryExample example = new TbContentCategoryExample();
		Criteria criteria = example.createCriteria();
		criteria.andParentIdEqualTo(conPid);
		List<TbContentCategory> list = this.tbContentCategoryMapper.selectByExample(example);
		for (TbContentCategory tbContentCategory : list) {
			EasyUITreeNode node = new EasyUITreeNode();
			node.setId(tbContentCategory.getId());
			node.setText(tbContentCategory.getName());
			node.setState(tbContentCategory.getIsParent()?"closed":"open");
			result.add(node);
		}
		return result;
	}

	@Override
	public TaotaoResult insertContentCategoryNode(String nodename, Long parentId) {
		Date date = new Date();
		
		TbContentCategory parentNode = this.tbContentCategoryMapper.selectByPrimaryKey(parentId);
		
		//判断父节点原始状态是否为父节点，如果不是父节点则不允许添加
		if (parentNode.getIsParent()){
			//是父节点
			
			TbContentCategory node = new TbContentCategory();
			
			node.setCreated(date);
			node.setUpdated(date);
			node.setParentId(parentId);
			node.setName(nodename);
			node.setSortOrder(1);
			node.setStatus(1);
			node.setIsParent(false);
			
			this.tbContentCategoryMapper.insert(node);
			return TaotaoResult.ok();
		}else{
			return TaotaoResult.build(200, "不允许添加");
		}
		
	}

	@Override
	public TaotaoResult updateContentCategoryNode(String nodename, Long id) {
		Date date = new Date();
		TbContentCategory node = this.tbContentCategoryMapper.selectByPrimaryKey(id);
		node.setUpdated(date);
		node.setName(nodename);
		
		this.tbContentCategoryMapper.updateByPrimaryKey(node);
			
		return TaotaoResult.ok();
	}

	@Override
	public TaotaoResult deleteContentCategoryNodeById(Long id, Long parentId) {
	
		TbContentCategory node = this.tbContentCategoryMapper.selectByPrimaryKey(id);
		if(!node.getIsParent()){
			this.tbContentCategoryMapper.deleteByPrimaryKey(id);
			return TaotaoResult.ok();
		}else{
			return TaotaoResult.build(200, "不允许删除");
		}
	}	
	
	

}
