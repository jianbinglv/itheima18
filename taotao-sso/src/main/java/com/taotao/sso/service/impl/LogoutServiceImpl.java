package com.taotao.sso.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.taotao.common.util.TaotaoResult;
import com.taotao.sso.dao.JedisClient;
import com.taotao.sso.service.LogoutService;

/**
 * 用户安全退出
 * <p>Title: LogoutServiceImpl</p>
 * <p>Description: </p>
 * <p>Company: isoftstone</p> 
 * @author	jianbinglv
 * @date	2016年12月11日下午9:38:40
 * @version 1.0
 */
@Service
public class LogoutServiceImpl implements LogoutService{
	@Autowired
	private JedisClient jedisClient;
	@Value("${SESSION_REDIS_KEY}")
	private String SESSION_REDIS_KEY;
	
	
	public TaotaoResult logout(String token) {
		this.jedisClient.del(this.SESSION_REDIS_KEY+token);
		return TaotaoResult.ok();
	}

}
