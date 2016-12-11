package com.taotao.sso.controller;


import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.common.util.ExceptionUtil;
import com.taotao.common.util.TaotaoResult;
import com.taotao.pojo.TbUser;
import com.taotao.sso.service.RegisterService;

/**
 * 用户注册controller
 * <p>Title: RegisterController</p>
 * <p>Description: </p>
 * <p>Company: isoftstone</p> 
 * @author	jianbinglv
 * @date	2016年12月11日下午5:53:20
 * @version 1.0
 */
@Controller
@Scope("prototype")
public class RegisterController {

	@Autowired
	private RegisterService registerService;
	
	@RequestMapping("/regist")
	public String showRegisterPage(){
		return "register";
	}
	
	
	@RequestMapping("/user/check/{param}/{type}")
	@ResponseBody
	public Object checkData(@PathVariable String param,
							@PathVariable Integer type,String callback){
		try {
			
		TaotaoResult result = registerService.checkData(param, type);
		if(StringUtils.isNotBlank(callback)){
			//请求为jsonp调用需要支持jsonp
			MappingJacksonValue jsonpvalue = new MappingJacksonValue(result);
			jsonpvalue.setJsonpFunction(callback);
			return jsonpvalue;
		}
		
		return result;
		
		} catch (Exception e) {
			e.printStackTrace();
			return TaotaoResult.build(500, ExceptionUtil.getStackTrace(e));
		}
	}
	@RequestMapping(value="/user/register",method=RequestMethod.POST)
	@ResponseBody
	public TaotaoResult registerUser(TbUser user){
		try {
			
			return this.registerService.insertUser(user);
		} catch (Exception e) {
			return TaotaoResult.build(400, "注册失败");
		}

	}
	
}

















