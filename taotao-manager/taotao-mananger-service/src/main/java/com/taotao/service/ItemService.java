package com.taotao.service;

import com.taotao.common.pojo.EsasyUIDataGridResult;
import com.taotao.common.util.TaotaoResult;
import com.taotao.pojo.TbItem;
import com.taotao.*;

public interface ItemService {

	public TbItem getItemById(Long itemId);

	public EsasyUIDataGridResult getItemList(int page,int rows);

	public TaotaoResult insertItem(TbItem item);
	
	
}
