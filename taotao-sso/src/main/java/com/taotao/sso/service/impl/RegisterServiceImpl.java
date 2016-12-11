package com.taotao.sso.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import com.taotao.common.util.TaotaoResult;
import com.taotao.mapper.TbUserMapper;
import com.taotao.pojo.TbUser;
import com.taotao.pojo.TbUserExample;
import com.taotao.pojo.TbUserExample.Criteria;
import com.taotao.sso.service.RegisterService;
/**
 * 用户注册service
 * <p>Title: RegisterServiceImpl</p>
 * <p>Description: </p>
 * <p>Company: isoftstone</p> 
 * @author	jianbinglv
 * @date	2016年12月11日下午5:43:36
 * @version 1.0
 */

@Service
public class RegisterServiceImpl implements RegisterService{

	
	@Autowired
	private TbUserMapper tbUserMaper;
	
	@Override
	public TaotaoResult checkData(String param, int type) {
		TbUserExample example = new TbUserExample();
		Criteria criteria = example.createCriteria();
		if(1==type){
			if(!StringUtils.isBlank(param)){
			//123分别代表username phone email;
				criteria.andUsernameEqualTo(param);
			}
		}else if(2==type){
			if(!StringUtils.isBlank(param)){
				criteria.andPhoneEqualTo(param);
			}
			
		}else if(3==type){
			if(!StringUtils.isBlank(param)){
				criteria.andEmailEqualTo(param);
			}
		}
		
		List<TbUser> userList  = tbUserMaper.selectByExample(example);
		if(userList.isEmpty()||userList==null){
			return TaotaoResult.ok(true);
		}
		return TaotaoResult.ok(false);
	}

	@Override
	public TaotaoResult insertUser(TbUser user) {
		//校验数据
		//检验用户名密码不能为空
		if(StringUtils.isBlank(user.getUsername())
				||StringUtils.isBlank(user.getPassword())){
			return TaotaoResult.build(400, "用户名密码不能为空");
		}
		//校验数据是否重复
		if(!(boolean)checkData(user.getUsername(),1).getData()){
			return TaotaoResult.build(400, "用户名已注册"); 
		} 
		if(!(boolean)checkData(user.getPhone(),2).getData()){
			return TaotaoResult.build(400, "电话号码已被注册");
		} 
		/*if(!(boolean)checkData(user.getEmail(),3).getData()){
			return TaotaoResult.build(400, "邮箱已被注册");
		}*/
		//数据没问题，可以注册
		Date date = new Date();
		user.setCreated(date);
		user.setUpdated(date);
		//密码md5加密
		user.setPassword(DigestUtils.md5DigestAsHex(user.getPassword().getBytes()));
		this.tbUserMaper.insert(user);
		return TaotaoResult.ok();
	}

}





















