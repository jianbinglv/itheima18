package com.taotao.portal.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.taotao.common.util.HttpClientUtil;
import com.taotao.common.util.JsonUtils;
import com.taotao.common.util.TaotaoResult;
import com.taotao.pojo.TbContent;
import com.taotao.portal.pojo.AdNode;
import com.taotao.portal.service.ContentService;

/**
 * 通过rest服务取广告位内容
 * <p>Title: ContentServiceImpl</p>
 * <p>Description: </p>
 * <p>Company: isoftstone</p> 
 * @author	jianbinglv
 * @date	2016年12月7日下午12:02:10
 * @version 1.0
 */

@Service
public class ContentServiceImpl implements ContentService{

	
	@Value("${REST_CONTENT_LIST}")
	private String Ad1RestUrl;
	
	public String getContentList() {
		//第一种方式
		/*InputStream in = this.getClass().getResourceAsStream("properties/url.properties");
		Properties properties = new Properties();
		properties.load(in);
		properties.getProperty("REST_CONTENT_LIST");
		 */
		//String contentStr = HttpClientUtil.doGet("http://localhost:8081/rest/content/89");
		String contentStr = HttpClientUtil.doGet(Ad1RestUrl);
		TaotaoResult result = TaotaoResult.formatToList(contentStr, TbContent.class);
		List<TbContent> contentList = (List<TbContent>) result.getData();
		//List<TbContent> contentList = JsonUtils.jsonToList(contentStr, TbContent.class);
		List<AdNode> resultList = new ArrayList<>();
		for (TbContent tbContent : contentList) {
			AdNode node = new AdNode();
		/*	"srcB": "http://image.taotao.com/images/2015/03/03/2015030304360302109345.jpg",
	        "height": 240,
	        "alt": "",
	        "width": 670,
	        "src": "http://image.taotao.com/images/2015/03/03/2015030304360302109345.jpg",
	        "widthB": 550,
	        "href": "http://sale.jd.com/act/e0FMkuDhJz35CNt.html?cpdad=1DLSUE",
	        "heightB": 240*/
			
			node.setAlt(tbContent.getSubTitle());
			node.setHeight(240);
			node.setHeightB(240);
			node.setWidth(670);
			node.setWidthB(550);
			node.setSrc(tbContent.getPic());
			node.setSrcB(tbContent.getPic2());
			node.setHref(tbContent.getUrl());
			
			resultList.add(node);
		}
		return JsonUtils.objectToJson(resultList);
	}

}
























