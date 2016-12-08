package com.taotao.search.service;

import org.apache.solr.client.solrj.SolrServerException;

import com.taotao.common.pojo.SearchResult;


public interface SearchService {

	SearchResult search(String quertStr,int page,int rows) throws SolrServerException;
}
