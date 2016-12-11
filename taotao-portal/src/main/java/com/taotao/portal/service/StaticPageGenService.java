package com.taotao.portal.service;

import java.io.IOException;

import com.taotao.common.util.TaotaoResult;

import freemarker.core.ParseException;
import freemarker.template.MalformedTemplateNameException;
import freemarker.template.TemplateNotFoundException;

public interface StaticPageGenService {

	TaotaoResult genItemHtml(Long iId) throws Exception;
		
}
