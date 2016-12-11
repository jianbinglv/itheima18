package com.taotao.sso.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.taotao.common.util.TaotaoResult;

public interface LoginService {

	public TaotaoResult login(String username,String password,HttpServletRequest request,HttpServletResponse response);
	TaotaoResult getUserByToken(String token);
}
