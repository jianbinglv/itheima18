package com.taotao.rest.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.taotao.common.util.TaotaoResult;
import com.taotao.rest.dao.JedisClient;
import com.taotao.rest.service.CartService;

@Service
public class CartServiceImpl implements CartService{

	
	@Autowired 
	private JedisClient jedisClient;
	@Value("${REDIS_CART_KEY}")
	private String REDIS_CART_KEY;
	
	
	public TaotaoResult insertCartToRedis(String username,String cartdetail) {
		try {
			this.jedisClient.set(this.REDIS_CART_KEY+username,cartdetail);
			return TaotaoResult.ok(this.REDIS_CART_KEY+username);
		} catch (Exception e) {
			
			return TaotaoResult.build(400, "保存失败");
		}
	}


	@Override
	public TaotaoResult getCart(String username) {
		String cartdetail = this.jedisClient.get(REDIS_CART_KEY+username);
		return TaotaoResult.ok(cartdetail);
	}

}
