package com.taotao.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.common.util.TaotaoResult;
import com.taotao.rest.service.CartService;

@Controller
@Scope("prototype")
public class CartController {

	@Autowired
	private CartService cartService;
	@RequestMapping("/cart/add")
	@ResponseBody
	public TaotaoResult add(String username,String cartdetail){
		return this.cartService.insertCartToRedis(username, cartdetail);
	}  
	@RequestMapping("/cart/get")
	@ResponseBody
	public TaotaoResult get(String username){
		return this.cartService.getCart(username);
	}
}
