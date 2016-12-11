package com.taotao.sso.service;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.taotao.common.util.TaotaoResult;
import com.taotao.mapper.TbUserMapper;
import com.taotao.pojo.TbUser;

public class RegisterTest {

	@Test
	public void testRegisterUser(){
		
		ApplicationContext context = new ClassPathXmlApplicationContext("/spring/applicationContext-*.xml");
		TbUserMapper service = context.getBean(TbUserMapper.class);
	    TbUser user = new TbUser();
	    Date date = new Date();
	    user.setUsername("lvjianbing");
	    user.setPassword("b19921101");
	    user.setPhone("15201094560");
	    user.setEmail("10@qq.com");
	    user.setCreated(date);
	    user.setUpdated(date);
	    service.insert(user);
	    
	}
	@Test
	public void testRegisterUser2(){
		
		ApplicationContext context = new ClassPathXmlApplicationContext("/spring/applicationContext-*.xml");
		RegisterService service = context.getBean(RegisterService.class);
	    TbUser user = new TbUser();
//	    TbUser [id=null, username=lvjianbing, password=b19921101, phone=15201094560, email=null, created=null, updated=null]

	    Date date = new Date();
	    user.setUsername("lvjianbing");
	    user.setPassword("b19921101");
	    user.setPhone("15201094560");
	    	
	    user.setEmail("11@qq.com");
	    user.setCreated(date);
	    user.setUpdated(date);
	    TaotaoResult result = service.insertUser(user);
	    System.out.println(result.getMsg());
	}
	
}
