package com.taotao.portal.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.taotao.common.util.CookieUtils;
import com.taotao.common.util.HttpClientUtil;
import com.taotao.common.util.TaotaoResult;
import com.taotao.pojo.TbItem;
import com.taotao.portal.pojo.CartItem;
import com.taotao.portal.service.CartService;

@Service
public abstract class  CartServiceImpl implements CartService{

	
	@Value("${REST_SERVICE_ADDRESS}")
	private String REST_SERVICE_ADDRESS;
	
	public TaotaoResult addToCart(Long iId,int num,HttpServletRequest request,HttpServletResponse response) {
		String cartItems = CookieUtils.getCookieValue(request, "CART_ITEMS");
		//检查cookie中是否存在商品
		if (StringUtils.isNotBlank(cartItems)) {
			//存在
			String[] cookieitems = cartItems.split(",");
			//cartItems置空
			cartItems="";
			
			String item_num = iId+"_"+num;
			//检查cookie中是否已经存在该商品
			for (String cookieitem : cookieitems) {
				//遍历cookie得到商品数量和id
				String[] itemAndnum = cookieitem.split("_");
				//id
				Long itemId = Long.parseLong(itemAndnum[0]);
				Integer num_in_cookie = Integer.parseInt(itemAndnum[1]);
				if(iId.equals(itemId)){
					//是该商品
					//拼接string
					item_num=iId+"_"+(num_in_cookie+num);
					System.out.println("num in cookie:"+item_num);
				}else{
					//不是该商品
					item_num=iId+"_"+num;
				}
				cartItems=cartItems+item_num+",";
			}
		}else{
			//cookie中没有商品
			cartItems=iId+"_"+num;
		}
		System.out.println("CART_ITEMS in cookie:"+cartItems);
		CookieUtils.setCookie(request, response, "CART_ITEMS", cartItems,3*60*60*24);
		return TaotaoResult.ok();
	}

	@Override
	public List<CartItem> showCart(HttpServletRequest request, HttpServletResponse response) {
		
		String cookieItems = CookieUtils.getCookieValue(request, "CART_ITEMS");
		String[] item_nums = cookieItems.split(",");
		List<CartItem> itemList = new ArrayList<>();
		//遍历cookie中item
		for (String item_num : item_nums) {
			//取得itemid和num
			String[] itemdetail = item_num.split("_");
			CartItem cartitem = new CartItem();
			//从rest服务中获取item
			String json = HttpClientUtil.doGet(this.REST_SERVICE_ADDRESS+"/item/base/"+itemdetail[0]);
			TaotaoResult result = TaotaoResult.formatToPojo(json, TbItem.class);
			TbItem item = (TbItem)result.getData();
			
			//设置caritem
			cartitem.setId(item.getId());
			List<String> list = Arrays.asList(item.getImage().split(","));
			cartitem.setImages(list);
			cartitem.setNum(Integer.parseInt(itemdetail[1]));
			cartitem.setPrice(item.getPrice());
			cartitem.setTitle(item.getTitle());
			//将cartitem添加到list中
			itemList.add(cartitem);
		}
		return itemList;
	}
	
}
