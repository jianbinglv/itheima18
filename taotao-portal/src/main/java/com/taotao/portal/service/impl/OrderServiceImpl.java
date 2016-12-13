package com.taotao.portal.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.taotao.common.util.HttpClientUtil;
import com.taotao.common.util.JsonUtils;
import com.taotao.common.util.TaotaoResult;
import com.taotao.portal.pojo.OrderInfo;
import com.taotao.portal.service.OrderService;

/**
 * port模块提交订单service
 * <p>Title: OrderServiceImpl</p>
 * <p>Description: </p>
 * <p>Company: isoftstone</p> 
 * @author	jianbinglv
 * @date	2016年12月13日下午7:35:58
 * @version 1.0
 */
@Service
public class OrderServiceImpl implements OrderService{

	@Value("${ORDER_URL}")
	private String ORDER_URL;
	@Value("${CREATE_ORDER}")
	private String CREATE_ORDER;
	

	public String createOrder(OrderInfo orderInfo) {
		String json = JsonUtils.objectToJson(orderInfo);
		String taotaoResultjson = HttpClientUtil.doPostJson(this.ORDER_URL+this.CREATE_ORDER, json);
		TaotaoResult result = TaotaoResult.format(taotaoResultjson);
		String order_id = result.getData().toString();
		return order_id;
	}

}
