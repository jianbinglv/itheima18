package com.taotao.rest.service;

import java.applet.AppletContext;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.taotao.pojo.TbItemDesc;
import com.taotao.pojo.TbItemParamItem;

public class ItemInfoTest {
	@Test
	public void testItemDesc(){
		
		ApplicationContext context = new ClassPathXmlApplicationContext("/spring/applicationContext-*.xml");
		ItemDescService service = context.getBean(ItemDescService.class);
		TbItemDesc desc = service.getItemDesc(856645L);
		System.out.println(desc.getItemDesc());
	}
	@Test
	public void testItem(){
		
		ApplicationContext context = new ClassPathXmlApplicationContext("/spring/applicationContext-*.xml");
		ItemParamItemService service = context.getBean(ItemParamItemService.class);
		TbItemParamItem desc = service.getItemParam(118464495231975L);
		System.out.println(desc.getParamData());
	}
}
