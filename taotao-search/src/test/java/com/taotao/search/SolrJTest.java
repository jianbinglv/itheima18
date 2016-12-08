package com.taotao.search;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.junit.Test;

public class SolrJTest {

	
	@Test
	public void testSolrJ() throws Exception{
		SolrServer  server =new HttpSolrServer("http://192.168.1.16:8080/solr/");
		SolrInputDocument document = new SolrInputDocument();
		document.addField("id", "solrtest01");
		document.addField("item_title", "测试商品");
		document.addField("item_sell_point","卖点");
		server.add(document);
		server.commit();
		
	}
	@Test
	public void testSolrJQuery() throws Exception{
		SolrServer server = new HttpSolrServer("http://192.168.1.16:8080/solr/");
		SolrQuery query = new SolrQuery();
		query.setQuery("*:*");
		QueryResponse response = server.query(query);
		SolrDocumentList list = response.getResults();
		for (SolrDocument document : list) {
			System.out.println(document.get("id"));
			System.out.println(document.get("item_title"));
			System.out.println(document.get("item_sell_point"));
		}
	}
}
