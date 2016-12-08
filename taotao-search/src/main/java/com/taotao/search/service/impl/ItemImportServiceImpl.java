package com.taotao.search.service.impl;

import java.io.IOException;
import java.util.List;

import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taotao.common.pojo.SearchItem;
import com.taotao.common.util.TaotaoResult;
import com.taotao.search.mapper.ItemMapper;
import com.taotao.search.service.ItemImportService;


/**
 * 数据库中数据导入搜索库solr中
 * <p>Title: ItemImportServiceImpl</p>
 * <p>Description: </p>
 * <p>Company: isoftstone</p> 
 * @author	jianbinglv
 * @date	2016年12月8日上午11:04:02
 * @version 1.0
 */
@Service
public class ItemImportServiceImpl implements ItemImportService {

	@Autowired
	private ItemMapper itemMapper;
	@Autowired
	private SolrServer solrServer;
	public TaotaoResult importItem() throws Exception{
		List<SearchItem> list = this.itemMapper.getItemList();
		for (SearchItem item : list) {
			SolrInputDocument document = new SolrInputDocument();

			/*private String id;
			private String title;
			private String sell_point;
			private Long price;
			private String image;
			private String category_name;
			private String item_desc;*/
			document.addField("id", item.getId());
			document.addField("item_title", item.getTitle());
			document.addField("item_sell_point", item.getSell_point());
			document.addField("item_price", item.getPrice());
			document.addField("item_image", item.getImage());
			document.addField("item_category_name", item.getCategory_name());
			document.addField("item_desc", item.getItem_desc());
			
			//写入索引库
			this.solrServer.add(document);
			this.solrServer.commit();
			
		}
		return TaotaoResult.ok();
	}

}



























