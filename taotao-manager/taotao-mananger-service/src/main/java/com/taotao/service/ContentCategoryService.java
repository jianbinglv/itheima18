package com.taotao.service;

import java.util.List;

import com.taotao.common.pojo.EasyUITreeNode;
import com.taotao.common.util.TaotaoResult;

public interface ContentCategoryService {

	
	List<EasyUITreeNode> getContentCategory(Long contid);

	TaotaoResult insertContentCategoryNode(String nodename, Long parentId);
	
	TaotaoResult updateContentCategoryNode(String nodename,Long id);

	TaotaoResult deleteContentCategoryNodeById(Long id, Long parentId);
}
