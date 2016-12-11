package com.taotao.sso.service.impl;

import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import com.taotao.common.util.CookieUtils;
import com.taotao.common.util.JsonUtils;
import com.taotao.common.util.TaotaoResult;
import com.taotao.mapper.TbUserMapper;
import com.taotao.pojo.TbUser;
import com.taotao.pojo.TbUserExample;
import com.taotao.pojo.TbUserExample.Criteria;
import com.taotao.sso.dao.JedisClient;
import com.taotao.sso.service.LoginService;

/**
 * 用户登陆service
 * <p>Title: LoginServiceImpl</p>
 * <p>Description: </p>
 * <p>Company: isoftstone</p> 
 * @author	jianbinglv
 * @date	2016年12月11日下午6:54:58
 * @version 1.0
 */
@Service
public class LoginServiceImpl implements LoginService{

	@Autowired
	private TbUserMapper userMapper;
	@Autowired
	private JedisClient jedisClient;
	@Value("${SESSION_REDIS_KEY}")
	private String SESSION_REDIS_KEY;
	@Value("${SESSION_EXPIRE}")
	private Integer SESSION_EXPIRE;
	
	@Override
	public TaotaoResult login(String username, String password,HttpServletRequest request,HttpServletResponse response) {
		//
		TbUserExample example = new TbUserExample();
		Criteria criteria = example.createCriteria();
		criteria.andUsernameEqualTo(username);
		List<TbUser> userList = this.userMapper.selectByExample(example);
		if(userList.isEmpty()||userList==null){
			return TaotaoResult.build(400, "用户名不存在 ");
		}
		
		TbUser user = userList.get(0);
		if(!user.getPassword().equals(DigestUtils.md5DigestAsHex(password.getBytes()))){
			//密码错误
			System.out.println(user.getPassword()+":"+DigestUtils.md5DigestAsHex(password.getBytes()));
			return TaotaoResult.build(400, "密码错误");
		}
		//生成token模拟session ID
		UUID uuid = UUID.randomUUID();
		String token= uuid.toString();
		//用户信息写入jedis
		user.setPassword(null);
		this.jedisClient.set(SESSION_REDIS_KEY+token,JsonUtils.objectToJson(user));
		this.jedisClient.expire(SESSION_REDIS_KEY+token, this.SESSION_EXPIRE);
		//写cookie
		CookieUtils.setCookie(request, response, "TT_TOKEN", token);
			
		return TaotaoResult.ok(token);
	}

	@Override
	public TaotaoResult getUserByToken(String token) {
		String json = jedisClient.get(SESSION_REDIS_KEY+token);
		if(!StringUtils.isBlank(json)){
			TbUser user = JsonUtils.jsonToPojo(json,TbUser.class);
			this.jedisClient.expire(SESSION_REDIS_KEY+token,this.SESSION_EXPIRE);
			return TaotaoResult.ok(user);
		}
		
		return TaotaoResult.build(400, "登陆session已过期");
	}
	
	
	

}


















