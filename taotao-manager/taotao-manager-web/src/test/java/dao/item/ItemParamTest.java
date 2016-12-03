package dao.item;

import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.taotao.mapper.ItemParamWtihCatNameMapper;
import com.taotao.pojo.ItemParamWtihCatName;

public class ItemParamTest {

	@Test
	public void testGetItemParam(){
		ApplicationContext context = new ClassPathXmlApplicationContext("/spring/applicationContext-dao.xml");
		ItemParamWtihCatNameMapper mapper = context.getBean(ItemParamWtihCatNameMapper.class);
		List<ItemParamWtihCatName> list = mapper.getItemParamWtihCatName();
		System.out.println(list.size());
	}
}
