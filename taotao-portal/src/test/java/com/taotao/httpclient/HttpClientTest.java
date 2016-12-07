package com.taotao.httpclient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.junit.Test;


/**
 * HttpClient测试类
 * <p>Title: HttpClientTest</p>
 * <p>Description: </p>
 * <p>Company: isoftstone</p> 
 * @author	jianbinglv
 * @date	2016年12月6日上午11:55:10
 * @version 1.0
 */
public class HttpClientTest {

	
	@Test
	public void testHttpClientGet() throws ClientProtocolException, IOException{
		//创建httpclient对象
		CloseableHttpClient client = HttpClients.createDefault();
		//创建get请求
		HttpGet httpGet = new HttpGet("http://localhost:8080/");
		//执行请求
		CloseableHttpResponse response = client.execute(httpGet);
		//接受返回结果
		HttpEntity entity = response.getEntity();
		//获取相应内容
		String html = EntityUtils.toString(entity);
		System.out.println(html);
		//关闭资源
		response.close();
		client.close();
	}
	
	@Test
	public void testHttoPost(){
		
		//创建httpclient对象
		
		CloseableHttpClient httpClient = HttpClients.createDefault();
		//创建post对象
		HttpPost post = new HttpPost();
		List<NameValuePair> form = new ArrayList<>();
		//https://passport.baidu.com/v2/api/?login&apiver=v3&callback=parent.bd__pcbs__gn1rp6
		//https://www.nuomi.com/?cid=002540
		form.add(new BasicNameValuePair("cid=","002540"));
		
		
		
	}
	@Test
	public void testURLConnection() throws IOException{
		URL url = new URL("http://www.baidu.com");
		URLConnection conn = url.openConnection();
		InputStream in = conn.getInputStream();
		BufferedReader br = new BufferedReader(new InputStreamReader(in));
		String html =null;
		while((html=br.readLine())!=null){
			System.out.println(html);	
		}
		in.close();
		br.close();
	}
	
	
}
