package com.taotao.rest.service;

import com.taotao.common.util.TaotaoResult;

public interface ContentService {

	TaotaoResult showContentList(Long cid);
	TaotaoResult syncRedisContent(Long cid);
	
}
