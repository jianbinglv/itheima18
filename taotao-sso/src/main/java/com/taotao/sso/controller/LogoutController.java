package com.taotao.sso.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.common.util.CookieUtils;
import com.taotao.common.util.TaotaoResult;
import com.taotao.sso.service.LogoutService;

/**
 * 用户注销controller
 * <p>Title: LogoutController</p>
 * <p>Description: </p>
 * <p>Company: isoftstone</p> 
 * @author	jianbinglv
 * @date	2016年12月11日下午9:41:37
 * @version 1.0
 */
@Controller
@Scope("prototype")
public class LogoutController {

	@Autowired
	private LogoutService logoutService;

	
	@RequestMapping("/user/logout")
	private String logout(HttpServletRequest request,HttpServletResponse response){
		String token =CookieUtils.getCookieValue(request, "TT_TOKEN");
		System.out.println("sessionID:"+token);
		try {
			this.logoutService.logout(token);
			return "login";
		} catch (Exception e) {
			return "error";
		}
	}
	
}
