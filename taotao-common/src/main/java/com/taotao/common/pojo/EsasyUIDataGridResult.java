package com.taotao.common.pojo;

import java.util.List;

/**
 * jquery EasyUI的通用返回类型
 * <p>Title: EsasyUIDataGridResult</p>
 * <p>Description: </p>
 * <p>Company: isoftstone</p> 
 * @author	jianbinglv
 * @date	2016年11月29日下午11:20:32
 * @version 1.0
 */

public class EsasyUIDataGridResult{

	
	private long total;
	private List<?> rows;
	
	public long getTotal() {
		return total;
	}
	public void setTotal(long total) {
		this.total = total;
	}
	public List<?> getRows() {
		return rows;
	}
	public void setRows(List<?> rows) {
		this.rows = rows;
	}
	
}
