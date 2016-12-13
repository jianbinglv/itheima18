package com.taotao.rest.service;

import com.taotao.common.util.TaotaoResult;

public interface CartService {

	public TaotaoResult insertCartToRedis(String username,String cartdetail);
	public TaotaoResult getCart(String username);
	
}
