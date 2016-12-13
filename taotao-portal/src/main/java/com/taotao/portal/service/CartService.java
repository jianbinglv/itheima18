package com.taotao.portal.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

import com.taotao.common.util.TaotaoResult;
import com.taotao.portal.pojo.CartItem;

public interface CartService {

	
	TaotaoResult addToCart(Long iId,int num,HttpServletRequest request,HttpServletResponse response);
	List<CartItem> showCart(HttpServletRequest request,HttpServletResponse response);
	TaotaoResult deleteItemCart(HttpServletRequest request, HttpServletResponse response, List<Long> iIds);
	TaotaoResult updateNum(HttpServletRequest request, HttpServletResponse response, Long itemId,Integer num);
}
