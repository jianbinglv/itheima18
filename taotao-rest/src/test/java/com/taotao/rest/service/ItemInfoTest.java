package com.taotao.rest.service;

import java.applet.AppletContext;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.taotao.pojo.TbItemDesc;

public class ItemInfoTest {
	@Test
	public void testItemDesc(){
		
		ApplicationContext context = new ClassPathXmlApplicationContext("/spring/applicationContext-*.xml");
		ItemDescService service = context.getBean(ItemDescService.class);
		TbItemDesc desc = service.getItemDesc(856645L);
		System.out.println(desc.getItemDesc());
	}
	
	
}
