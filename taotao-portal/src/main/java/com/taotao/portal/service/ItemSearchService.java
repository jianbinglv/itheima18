package com.taotao.portal.service;

import com.taotao.common.pojo.SearchResult;

public interface ItemSearchService {

	SearchResult searchItem(String query,Integer currentPage,Integer rows);
}
