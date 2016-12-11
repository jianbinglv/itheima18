package com.taotao.sso.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import com.taotao.sso.service.LoginService;

/**
 * 用户注册controller
 * <p>Title: LoginController</p>
 * <p>Description: </p>
 * <p>Company: isoftstone</p> 
 * @author	jianbinglv
 * @date	2016年12月11日下午8:47:38
 * @version 1.0
 */
@Controller
@Scope("prototype")
public class LoginController {

	@Autowired
	private LoginService loginService;
	
	@RequestMapping(value="/user/login",method=RequestMethod.POST)
	@ResponseBody
	public TaotaoResult login(String username,String password,
			HttpServletRequest request,HttpServletResponse response){
		try {
			
			TaotaoResult result = this.loginService.login(username, password, request, response);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return TaotaoResult.build(500, ExceptionUtil.getStackTrace(e));
		}
	}

	@RequestMapping(value="/user/token/{token}")
	@ResponseBody
	public Object getUserByToken(@PathVariable String token,String callback){
		try {
			TaotaoResult result = this.loginService.getUserByToken(token);
			if(StringUtils.isNotBlank(callback)){
				MappingJacksonValue jacksonValue = new MappingJacksonValue(result);
				jacksonValue.setJsonpFunction(callback);
				return jacksonValue;
			}
			return result;
			
		} catch (Exception e) {
			// TODO: handle exception
			return TaotaoResult.build(500, ExceptionUtil.getStackTrace(e));
		}
	}
	//http://localhost:8084/user/showLogin
	@RequestMapping(value="/user/showLogin")
	public String showLoginPage(){
		return "login";
	}
}



















