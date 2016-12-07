package service;

import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.taotao.common.pojo.EasyUITreeNode;
import com.taotao.pojo.TbContentCategory;
import com.taotao.service.ContentCategoryService;

public class ContentServiceTest {

	
	@Test
	public void testContentService(){
		ApplicationContext context = new ClassPathXmlApplicationContext("/spring/applicationContext-*.xml");
		
		ContentCategoryService service  = context.getBean(ContentCategoryService.class);

		List<EasyUITreeNode> list = service.getContentCategory(30L);
		System.out.println(list.size());
	}
}
