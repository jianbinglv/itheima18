package com.taotao.common.pojo;


/**
 * easyui 异步加载tree返回类型
 * <p>Title: EasyUITreeNode</p>
 * <p>Description: </p>
 * <p>Company: isoftstone</p> 
 * @author	jianbinglv
 * @date	2016年11月30日上午12:00:44
 * @version 1.0
 */
public class EasyUITreeNode {

	private long id;
	private String text;
	private String state;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	
	
}
