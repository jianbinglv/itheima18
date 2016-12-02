package com.taotao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.taotao.common.util.JsonUtils;
import com.taotao.common.util.PictureResult;
import com.taotao.service.PictureService;

/**
 * 图片上传controller
 * <p>Title: PictureController</p>
 * <p>Description: </p>
 * <p>Company: isoftstone</p> 
 * @author	jianbinglv
 * @date	2016年12月1日下午1:48:17
 * @version 1.0
 */

@Controller
@Scope("prototype")
public class PictureController {

	
	@Autowired
	private PictureService pictureService;
	
	@RequestMapping("/pic/upload")
	@ResponseBody
	public String uploadFile(MultipartFile uploadFile){
		System.out.println("file name:"+uploadFile.getOriginalFilename());
		
		PictureResult result = this.pictureService.uploadPic(uploadFile);
		
		return JsonUtils.objectToJson(result);
		
	}
	
}
