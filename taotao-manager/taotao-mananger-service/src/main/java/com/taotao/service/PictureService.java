package com.taotao.service;

import org.springframework.web.multipart.MultipartFile;

import com.taotao.common.util.PictureResult;


public interface PictureService {

	public PictureResult uploadPic(MultipartFile picFile);
}
