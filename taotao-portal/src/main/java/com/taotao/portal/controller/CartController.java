package com.taotao.portal.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
/**
 * 购物车controller
 * <p>Title: CatController</p>
 * <p>Description: </p>
 * <p>Company: isoftstone</p> 
 * @author	jianbinglv
 * @date	2016年12月12日下午5:23:38
 * @version 1.0
 */
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.portal.pojo.CartItem;
import com.taotao.portal.service.CartService;
@Controller
@Scope("prototype")
public class CartController {

	@Resource(name="cartServiceByCartJsonImpl")
	private CartService cartService;
	
	@RequestMapping("/cart/add/{iId}.html")
	public String addToCart(@PathVariable Long iId,
			Integer num,
			HttpServletRequest request,
			HttpServletResponse response,
			Model model){
		this.cartService.addToCart(iId,num,request, response);
		
		return "cart-success";
		
	}
	//http://localhost:8082/cart/show.html
	@RequestMapping(value={"/cart/cart","/cart/show"})
	public String showCart(Model model,HttpServletRequest request,HttpServletResponse response){
		List<CartItem> list = this.cartService.showCart(request, response);
		model.addAttribute("cartList",list);
		return "cart";
	}

	@RequestMapping("/cart/delete/{iId}")
	public String deleteCart(HttpServletRequest request,HttpServletResponse response,@PathVariable Long iId){
		List<Long> iIds = new ArrayList<Long>();
		iIds.add(iId);
		this.cartService.deleteItemCart(request, response, iIds);
		return "cart-success";
	}
	
	
	//http://localhost:8082/cart/update/num/118464495231975/4.action
	@RequestMapping("/cart/update/num/{iId}/{num}")
	@ResponseBody
	public Object itemCartNumSub(HttpServletRequest request,HttpServletResponse response,@PathVariable Long iId,@PathVariable Integer num){
		this.cartService.updateNum(request, response, iId, num);
		return "ok";
	}
}


















