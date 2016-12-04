package com.taotao.controller;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.taotao.pojo.TbUser;

public class AuthorityFilter implements Filter{

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		System.out.println("my filter 初始化");
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest hRequest = (HttpServletRequest)request;
		HttpServletResponse hResponse = (HttpServletResponse)response;
		HttpSession session = hRequest.getSession();
		TbUser user = (TbUser)session.getAttribute("user");
		String url = hRequest.getRequestURI();
		
		System.out.println(url);
		
		if(user==null&&!"/login.jsp".equals(url)){
			hResponse.sendRedirect("/login.jsp");
		}else{
			chain.doFilter(hRequest, hResponse);
		}
		
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}


}
