package com.taotao.service;

import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.taotao.common.pojo.SearchItem;
import com.taotao.common.pojo.SearchResult;
import com.taotao.portal.service.ItemSearchService;

public class ItemSearchTest {

	
	@Test
	public void testSearchItem(){
		//taotao-portal/src/main/resources/spring/applicationContext-service.xml
		ApplicationContext context = new ClassPathXmlApplicationContext("/spring/applicationContext-*.xml");
		ItemSearchService service = context.getBean(ItemSearchService.class);
		SearchResult result = service.searchItem("手机", 1, 30);
		List<SearchItem> items = result.getItemList();
		System.out.println(items.size());
	}

	
}
