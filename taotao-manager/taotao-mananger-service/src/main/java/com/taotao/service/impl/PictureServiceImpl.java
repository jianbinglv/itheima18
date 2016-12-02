package com.taotao.service.impl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.taotao.common.util.FastDFSClient;
import com.taotao.common.util.PictureResult;
import com.taotao.service.PictureService;

/**
 * 图片上传service
 * <p>Title: PictureServiceImpl</p>
 * <p>Description: </p>
 * <p>Company: isoftstone</p> 
 * @author	jianbinglv
 * @date	2016年12月1日下午1:27:42
 * @version 1.0
 */

@Service
public class PictureServiceImpl implements PictureService{

	@Value("${FAST_DFS_SERVER_IP}")
	private String FAST_DFS_SERVER_IP;
	
	@Override
	public PictureResult uploadPic(MultipartFile picFile) {
		PictureResult result = new PictureResult();
		//判断图片是否为空
		if(picFile.isEmpty()){//为空，返回result
			result.setError(1);
			result.setMessage("图片为空");
			
			return result;
		}else{//图片不为空上传到图片服务器
			try {
				FastDFSClient client = new FastDFSClient("classpath:properties/client.conf");
				String fileName = picFile.getOriginalFilename();
				String extendName = fileName.substring(fileName.lastIndexOf(".")+1);
				String url = FAST_DFS_SERVER_IP+client.uploadFile(picFile.getBytes(),extendName);
				result.setError(0);
				result.setUrl(url);
				System.out.println(url);
				return result;
			} catch (Exception e) {
				result.setError(1);
				result.setMessage("图片上传失败");
				e.printStackTrace();
				return result;
				
			}
			
		}
		
	}

}
