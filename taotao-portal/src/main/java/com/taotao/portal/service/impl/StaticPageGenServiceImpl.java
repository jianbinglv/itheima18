package com.taotao.portal.service.impl;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfig;

import com.taotao.common.util.JsonUtils;
import com.taotao.common.util.TaotaoResult;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemDesc;
import com.taotao.pojo.TbItemParamItem;
import com.taotao.portal.service.ItemService;
import com.taotao.portal.service.StaticPageGenService;


import freemarker.core.ParseException;
import freemarker.template.Configuration;
import freemarker.template.MalformedTemplateNameException;
import freemarker.template.Template;
import freemarker.template.TemplateNotFoundException;

/**
 * 生成商品信息静态页面
 * <p>Title: StaticOageGenServiceImpl</p>
 * <p>Description: </p>
 * <p>Company: isoftstone</p> 
 * @author	jianbinglv
 * @date	2016年12月9日下午6:12:48
 * @version 1.0
 */
@Service
public class StaticPageGenServiceImpl implements StaticPageGenService{

	@Autowired
	private ItemService itemService;
	@Autowired
	private FreeMarkerConfig freeMarkerConfig;
	
	@Value("${FREEMARKER_TEMPLATE}")
	private String FREEMARKER_TEMPLATE;
	
	@Value("${STATIC_HTML_LOCATION}")
	private String STATIC_HTML_LOCATION;
	
	@Override
	public TaotaoResult genItemHtml(Long iId) throws Exception {
		//取数据
		TbItem item = itemService.getItemById(iId);
		String itemDesc = this.itemService.getItemDescById(iId);
		String json = this.itemService.getItemParamById(iId);
		List<Map> list = JsonUtils.jsonToList(json, Map.class);
		//生成静态html
		Configuration configuration = freeMarkerConfig.getConfiguration();
		Template template = configuration.getTemplate(this.FREEMARKER_TEMPLATE);
		//数据集
		Map root = new HashMap<>();
		//添加属性
		String image = item.getImage();
		String[] images = image.split(",");
		root.put("itemDesc", itemDesc);
		root.put("itemParamitem", list);
		root.put("item", item);
		root.put("images", images);
		System.out.println(item.getPrice());
		//writer对象
		
		Writer out = new FileWriter(new File(this.STATIC_HTML_LOCATION+iId+".html"));
		template.process(root, out);
		out.flush();
		out.close();
		return TaotaoResult.ok();
	}

	
}
