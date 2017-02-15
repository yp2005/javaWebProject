package com.steelIndustry.framework.jquery.datatable;

/**
 * 页数实体
 * 
 * @author wanglei
 *
 */
public class TablePage {
	private Integer start = 0;
	private Integer length = 10;

	public Integer getStart() {
		return start;
	}

	public void setStart(Integer start) {
		this.start = start;
	}

	public Integer getLength() {
		return length;
	}

	public void setLength(Integer length) {
		this.length = length;
	}
	
	public Integer getPageNumber(){
		if(this.getStart() == 0){
			return 0;
		}
		int p = this.getLength()/this.getStart();
		return p;
	}
}