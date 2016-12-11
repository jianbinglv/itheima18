package com.taotao.freemarker;

import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import freemarker.template.Configuration;
import freemarker.template.Template;

public class FreeMarkerTest {

	@Test
	public void testFreeMarker()throws Exception{
		//创建一个confiuration
		Configuration configuration = new Configuration(Configuration.getVersion());
		//指定文件存放路径
		configuration.setDirectoryForTemplateLoading(new File("E:\\workspace\\git2\\taotao\\taotao-portal\\src\\main\\webapp\\WEB-INF\\ftl"));
		//指定字符集编码
		configuration.setDefaultEncoding("utf-8");
		//指定模板对象
		Template template = configuration.getTemplate("first.ftl");
		//创建数据集
		Map root = new HashMap<>();
		root.put("hello", "hello freeMarker");
		//创建writer对象
		Writer out = new FileWriter("E:\\workspace\\freemarkerhtml\\hello.html");
		template.process(root, out);
		out.flush();
		out.close();
	}
}
