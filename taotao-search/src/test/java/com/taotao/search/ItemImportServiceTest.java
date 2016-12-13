package com.taotao.search;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.taotao.common.pojo.SearchItem;
import com.taotao.search.mapper.ItemMapper;
import com.taotao.search.service.ItemImportService;

public class ItemImportServiceTest {
	
	@Test
	public void addOneItemTSor() throws Exception{
		ApplicationContext context= new ClassPathXmlApplicationContext("/spring/applicationContext-*.xml");
		ItemImportService mapper = context.getBean(ItemImportService.class);
		mapper.importOneItem(118464495231975L);
	}
	

	

}
