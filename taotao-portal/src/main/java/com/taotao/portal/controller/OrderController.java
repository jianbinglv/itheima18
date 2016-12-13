package com.taotao.portal.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.taotao.pojo.TbUser;
import com.taotao.portal.pojo.CartItem;
import com.taotao.portal.pojo.OrderInfo;
import com.taotao.portal.service.CartService;
import com.taotao.portal.service.OrderService;

@Controller
@Scope("prototype")
public class OrderController {

	
	@Autowired
	private CartService cartService;
	@Autowired
	private OrderService orderService;
	
	@RequestMapping("/order/order-cart")
	public String showMakeSurePage(HttpServletRequest request,HttpServletResponse response,Model model){
		List<CartItem> list = this.cartService.showCart(request, response);
		model.addAttribute("cartList",list);
		return"order-cart";
	}
	@RequestMapping(value="/order/create",method=RequestMethod.POST)
	public String createOrder(OrderInfo orderInfo,HttpServletRequest request,Model model){
		
		TbUser user = (TbUser) request.getAttribute("user");
		//补全信息
		orderInfo.setUserId(user.getId());
		orderInfo.setBuyerNick(user.getUsername());
		//调用服务
		String orderId = this.orderService.createOrder(orderInfo);
		//订单传递给页面
		model.addAttribute("orderId",orderId);
		model.addAttribute("payment", orderInfo.getPayment());
		model.addAttribute("date", new DateTime().plusDays(3).toString("yyyy-MM-dd"));
		//返回逻辑视图
		return "success";
	}
}
