package com.taotao.portal.service.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.taotao.common.util.CookieUtils;
import com.taotao.common.util.HttpClientUtil;
import com.taotao.common.util.TaotaoResult;
import com.taotao.pojo.TbUser;
import com.taotao.portal.service.UserService;

/**
 * 用户管理service
 * <p>Title: UserServiceImpl</p>
 * <p>Description: </p>
 * <p>Company: isoftstone</p> 
 * @author	jianbinglv
 * @date	2016年12月11日下午11:47:01
 * @version 1.0
 */
@Service
public class UserServiceImpl implements UserService{

	/*SSO_ADDRESS=http://localhost:8084
		#根据token取得user地址
		SSO_GET_USER_BY_TOKEN=/user/token/*/


	@Value("${SSO_ADDRESS}")
	private String SSO_ADDRESS;
	@Value("${SSO_GET_USER_BY_TOKEN}")
	private String SSO_GET_USER_BY_TOKEN;
	
	@Override
	public TbUser getUserByToken(HttpServletRequest request,HttpServletResponse response) {
		try {
			
			String token = CookieUtils.getCookieValue(request, "TT_TOKEN");
			if(StringUtils.isBlank(token)){
				return null;
			}
			String json = HttpClientUtil.doGet(this.SSO_ADDRESS+this.SSO_GET_USER_BY_TOKEN+token);
			
			TaotaoResult result = TaotaoResult.format(json);
			//如果result状态为空
			if(result.getStatus()!=200){
				return null;
			}
			
			TaotaoResult result2 = TaotaoResult.formatToPojo(json,TbUser.class);
			TbUser user = (TbUser)result2.getData();
			
			return user;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
