package service;

import java.util.*;
import java.util.Map.Entry;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.taotao.common.util.JsonUtils;
import com.taotao.pojo.TbItemParamItem;
import com.taotao.service.ItemParamItemService;

public class ItemParamItemTes {

	@Test
	public void getItemParamItem(){
		
		ApplicationContext context =  new ClassPathXmlApplicationContext("/spring/applicationContext-*.xml");
		ItemParamItemService service = context.getBean(ItemParamItemService.class);
		TbItemParamItem item = service.showItemParamItem(143771131488369L);
		String data = item.getParamData();
		List<Map> list = JsonUtils.jsonToList(data, Map.class);
		System.out.println(list.size());
//		/Map map = list.get(0);
		for (Map map : list) {
			System.out.println(map.get("group"));
			List<Map> lismap2 = (List<Map>) map.get("params");
			for (Map map2 : lismap2) {
				System.out.println(map2.get("k"));
				System.out.println(map2.get("v"));
			}
			
			System.out.println("-----------------------");
		}

	}

}
	
















