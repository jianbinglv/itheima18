package com.taotao.portal.service;

import com.taotao.pojo.TbItem;

public interface ItemService {

	TbItem getItemById(Long iid);
	String getItemDescById(Long iid);
	String getItemParamById(Long iid);
}
