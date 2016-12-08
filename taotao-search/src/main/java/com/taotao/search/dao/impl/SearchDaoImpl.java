package com.taotao.search.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.taotao.common.pojo.SearchItem;
import com.taotao.common.pojo.SearchResult;
import com.taotao.search.dao.SearchDao;


@Repository
public class SearchDaoImpl implements SearchDao{

	
	@Autowired
	private SolrServer solrServer;
	
	@Override
	public SearchResult search(SolrQuery query) throws SolrServerException {
		//执行查询
		QueryResponse response = solrServer.query(query);
		SolrDocumentList lists = response.getResults();
		
		List<SearchItem> itemList = new ArrayList<>();
		for (SolrDocument document : lists) {
			//分装到searchitem对象中
			/*"id": "679533",
	        "item_title": "阿尔卡特 (OT-986+) 曜石黑 AK47 加强版 联通3G手机",
	        "item_sell_point": "少量库存，抢完即止！<a  target=\"blank\"  href=\"http://sale.jd.com/act/bxYeI1346g.html?erpad_source=erpad\">“领券更优惠！”</a>",
	        "item_price": 49900,
	        "item_image": "http://image.taotao.com/jd/b3251c85da8e4302b7389f3371dd0a68.jpg",
	        "item_category_name": "手机",*/
			SearchItem item = new SearchItem();
			item.setId((String) document.get("id"));
			item.setCategory_name((String) document.get("item_category_name"));
			//只取一张图片
			String imgUrls= (String)document.get("item_image");
			String imgurl = "";
			if(!StringUtils.isBlank(imgUrls)){
				String[] urlArray = imgUrls.split(",");
				imgurl = urlArray[0];
				item.setImage(imgurl);
			}else{
				item.setImage("");
			}
			item.setPrice((Long) document.get("item_price"));
			//item.setItem_desc(document.get("item_desc"));
			item.setSell_point((String) document.get("item_sell_point"));
			
			//取高亮显示
			Map<String, Map<String, List<String>>> highlighting =  response.getHighlighting();
			List<String> list = highlighting.get(document.get("id")).get("item_title");
			String itemTitle="";
			if(list.size()>0&&list!=null){
				itemTitle = list.get(0);
			}else{
				itemTitle = (String) document.get("item_title");
			}
			item.setTitle(itemTitle);
			//加入到itemList中
			itemList.add(item);
		}

		SearchResult result= new SearchResult();
		result.setItemList(itemList);
		result.setRecordCount(lists.getNumFound());
		return result;
	}

	
}



























