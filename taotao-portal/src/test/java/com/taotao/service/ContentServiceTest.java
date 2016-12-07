package com.taotao.service;

import java.util.List;

import org.junit.Test;

import com.taotao.common.util.HttpClientUtil;
import com.taotao.common.util.JsonUtils;
import com.taotao.common.util.TaotaoResult;
import com.taotao.pojo.TbContent;

public class ContentServiceTest {

	
	@Test
	public void testGetContentList(){
		String contentStr = HttpClientUtil.doGet("http://localhost:8081/rest/content/89");
		System.out.println(contentStr);
		TaotaoResult result = TaotaoResult.formatToList(contentStr, TbContent.class);
		List<TbContent> list = (List<TbContent>) result.getData();
		//List<TbContent> contentList = JsonUtils.jsonToList(contentStr, TbContent.class);
		System.out.println("size"+list.size());
	}
}
