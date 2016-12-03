package com.taotao.service;

import com.taotao.common.pojo.EsasyUIDataGridResult;
import com.taotao.common.util.TaotaoResult;

public interface ItemParamService {

	EsasyUIDataGridResult getItemParamWtihCatName(int pageNum,int pageSize);

	TaotaoResult isItemParamExist(Long cid);
	TaotaoResult insertItemParam(Long cid,String paramData);
	TaotaoResult deleteItemParamByids(String ids);
	
	

}
