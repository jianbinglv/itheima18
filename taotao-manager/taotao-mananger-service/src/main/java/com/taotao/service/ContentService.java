package com.taotao.service;

import java.util.List;

import com.taotao.common.pojo.EsasyUIDataGridResult;
import com.taotao.common.util.TaotaoResult;
import com.taotao.pojo.TbContent;

public interface ContentService {

	
	EsasyUIDataGridResult selectContentList(Long catId,Integer currentPage,Integer rows);
	TaotaoResult insertContent(TbContent content);

}
