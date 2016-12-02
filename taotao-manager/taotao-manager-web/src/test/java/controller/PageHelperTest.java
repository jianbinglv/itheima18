package controller;

import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.mapper.TbItemMapper;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemExample;

/**
 * mybaits 分页插件测试
 * <p>Title: PageHelperTest</p>
 * <p>Description: </p>
 * <p>Company: isoftstone</p> 
 * @author	jianbinglv
 * @date	2016年11月29日下午10:43:20
 * @version 1.0
 */

public class PageHelperTest {
	
	
	@Test
	public void testPageHelper(){
		
		//1.获得mapper代理对象
		ApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-*.xml");
		TbItemMapper mapper = context.getBean(TbItemMapper.class);
		
		//2.设置分页
		PageHelper.startPage(2, 50);
		//3.执行查询
		TbItemExample example = new TbItemExample();
		List<TbItem> list = mapper.selectByExample(example);
		//获取分页结果
		PageInfo<TbItem> pageInfo = new PageInfo<TbItem>(list);
		long total = pageInfo.getTotal();
		System.out.println("total"+total);
		System.out.println(pageInfo.getPages()+"pages:");
		System.out.println(pageInfo.getPageSize()+"pageSize");
		System.out.println(list.size());
	}
	

	
	
}
