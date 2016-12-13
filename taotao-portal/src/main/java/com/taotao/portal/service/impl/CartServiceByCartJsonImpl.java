package com.taotao.portal.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.taotao.common.util.CookieUtils;
import com.taotao.common.util.HttpClientUtil;
import com.taotao.common.util.JsonUtils;
import com.taotao.common.util.TaotaoResult;
import com.taotao.pojo.TbItem;
import com.taotao.portal.pojo.CartItem;
import com.taotao.portal.service.CartService;

@Service("cartServiceByCartJsonImpl")
public class CartServiceByCartJsonImpl implements CartService{

	@Value("${REST_SERVICE_ADDRESS}")
	private String REST_SERVICE_ADDRESS;
	
	@Override
	public TaotaoResult addToCart(Long iId, int num, HttpServletRequest request, HttpServletResponse response) {
		String json = CookieUtils.getCookieValue(request, "TT_CART");
		List<CartItem> cartItemList;
		//为该商品创建一个实例
		CartItem item = new CartItem();
		item.setId(iId);
		item.setNum(num);
		//检查购物车是否为空
		if(StringUtils.isBlank(json)){
			//购物车空
			cartItemList = new LinkedList<>();
			
			cartItemList.add(item);
			CookieUtils.setCookie(request, response, "TT_CART", JsonUtils.objectToJson(cartItemList),3*24*60*60);
		}else{
			//购物车不为空
			cartItemList = JsonUtils.jsonToList(json, CartItem.class);
			Iterator<CartItem> iterator = cartItemList.iterator();
			while(iterator.hasNext()){
				CartItem i = iterator.next(); 
				if(i.getId().equals(item.getId())){
					item.setNum(i.getNum()+item.getNum());
					iterator.remove();
				}
			}
			
			cartItemList.add(item);
			CookieUtils.setCookie(request, response, "TT_CART", JsonUtils.objectToJson(cartItemList),3*24*60*60);
		}
		
		return TaotaoResult.ok();
	}

	@Override
	public List<CartItem> showCart(HttpServletRequest request, HttpServletResponse response) {
		String json = CookieUtils.getCookieValue(request, "TT_CART");
		List<CartItem> result = new ArrayList<>();
		if(StringUtils.isNotBlank(json)){
			//购物车不为空
			List<CartItem> cartItemList = JsonUtils.jsonToList(json, CartItem.class);
			for (CartItem cartitem : cartItemList) {
				//从rest服务中获取item
				String restjson = HttpClientUtil.doGet(this.REST_SERVICE_ADDRESS+"/item/base/"+"118464495231975");
				TaotaoResult taotaoResult = TaotaoResult.formatToPojo(restjson, TbItem.class);
				TbItem item = (TbItem)taotaoResult.getData();
				
				//设置caritem
				cartitem.setId(item.getId());
				List<String> list = Arrays.asList(item.getImage().split(","));
				cartitem.setImages(list);
				cartitem.setNum(cartitem.getNum());
				cartitem.setPrice(item.getPrice());
				cartitem.setTitle(item.getTitle());
				//将cartitem添加到list中
				result.add(cartitem);
			}
		}
		return result;
	}

	@Override
	public TaotaoResult deleteItemCart(HttpServletRequest request,HttpServletResponse response,List<Long> iIds) {
		String json = CookieUtils.getCookieValue(request, "TT_CART");
		if(StringUtils.isNotBlank(json)){
			
			List<CartItem> cartList = JsonUtils.jsonToList(json, CartItem.class);
			for (Long iId : iIds) {
				Iterator<CartItem> iterator= cartList.iterator();
				while(iterator.hasNext()){
					CartItem i = iterator.next();
					if(i.getId().equals(iId)){
						iterator.remove();
					}
				}
				
				/*for (CartItem cartItem : cartList) {
					if(cartItem.getId().equals(iId)){
						cartList.remove(cartItem);
					}
				}*/
			}
			if(cartList.size()!=0){
				
				CookieUtils.setCookie(request, response, "TT_CART", JsonUtils.objectToJson(cartList), 3*24*60*60);
			}else{
				CookieUtils.deleteCookie(request, response, "TT_CART");
			}
		}
		return TaotaoResult.ok();
	}

	@Override
	public TaotaoResult updateNum(HttpServletRequest request, HttpServletResponse response, Long itemId,Integer num) {
		String json = CookieUtils.getCookieValue(request, "TT_CART");
		if(StringUtils.isNotBlank(json)){
			CartItem item = new CartItem();
			List<CartItem> cartList = JsonUtils.jsonToList(json, CartItem.class);
			Iterator<CartItem> iterator= cartList.iterator();
			while(iterator.hasNext()){
				CartItem i = iterator.next();
				if(i.getId().equals(itemId)){
					i.setNum(num);
					item = i;
					iterator.remove();
				}
				
			}
			cartList.add(item);
				
			CookieUtils.setCookie(request, response, "TT_CART", JsonUtils.objectToJson(cartList), 3*24*60*60);
		}
		return TaotaoResult.ok();
	}


}
