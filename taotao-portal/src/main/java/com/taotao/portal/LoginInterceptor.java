package com.taotao.portal;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.taotao.pojo.TbUser;
import com.taotao.portal.service.UserService;

/**
 * 用户登录拦截器
 * <p>Title: LoginInterceptor</p>
 * <p>Description: </p>
 * <p>Company: isoftstone</p> 
 * @author	jianbinglv
 * @date	2016年12月12日上午11:04:39
 * @version 1.0
 */

public class LoginInterceptor implements HandlerInterceptor {

	@Autowired
	private UserService userService;

	@Value("${SSO_LOGIN_URL}")
	private String SSO_LOGIN_URL;
	
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		//1.拦截请求的url
		//2.从cookie中取token
		//3.如果没有token，跳转到登陆页面
		
		//4.取到token，需要调用sso系统服务查询用户信息
		//5.seesion已过期，跳转到登陆页面
		TbUser user = this.userService.getUserByToken(request, response);
		if(user==null){
			response.sendRedirect(this.SSO_LOGIN_URL+"?redirectUrl="+request.getRequestURL());
			return false;
		}
		//把用户对象放入request中
		request.setAttribute("user",user);
		//6.存在session，放行
		return true;
	}

	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
	}

	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
	}

}














