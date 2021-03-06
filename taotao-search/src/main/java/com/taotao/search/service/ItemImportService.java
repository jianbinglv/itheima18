package com.taotao.search.service;

import com.taotao.common.util.TaotaoResult;

public interface ItemImportService {

	TaotaoResult importItem() throws Exception;

	TaotaoResult importOneItem(Long iId) throws Exception;
}
