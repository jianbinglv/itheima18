package com.taotao.search.dao;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;

import com.taotao.common.pojo.SearchResult;


public interface SearchDao {

	public SearchResult search(SolrQuery query) throws SolrServerException;
}
