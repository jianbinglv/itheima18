package com.taotao.freemarker;

import org.junit.Test;

import freemarker.template.Configuration;

public class FreeMarkerTest {

	@Test
	public void testFreeMarker(){
		//创建一个confiuration
		Configuration configuration = new Configuration(Configuration.getVersion());
		//指定文件存放路径
		//configuration.setDirectoryForTemplateLoading();
	}
}
