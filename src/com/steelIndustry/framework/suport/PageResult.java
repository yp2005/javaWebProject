package com.steelIndustry.framework.suport;

import java.util.List;


public class PageResult<Entity> {
	/**
	 * 当前页记录数
	 */
	private int currentResultCount;
	/**
	 * 是否第一页
	 */
	private boolean first;
	/**
	 * 是否最后一页
	 */
	private boolean last;
	/**
	 * 是否有下一页
	 */
	private boolean next;
	/**
	 * 是否有上一页
	 */
	private boolean previous;
	/**
	 * 当前页是否有记录
	 */
	private boolean content;
	/**
	 * 总记录行数
	 */
	private long totalResultCount;
	/**
	 * 总页数
	 */
	private int totalPageCount;
	/**
	 * 当前页页码
	 */
	private int pageNumber;
	/**
	 * 期望页面记录数
	 */
	private int pageSize;
	
	/**
	 * 查询结果集
	 */
	private List<Entity> resultSet;
	public int getCurrentResultCount() {
		return currentResultCount;
	}
	public void setCurrentResultCount(int currentResultCount) {
		this.currentResultCount = currentResultCount;
	}
	public boolean isFirst() {
		return first;
	}
	public void setFirst(boolean first) {
		this.first = first;
	}
	public boolean isLast() {
		return last;
	}
	public void setLast(boolean last) {
		this.last = last;
	}
	public boolean isNext() {
		return next;
	}
	public void setNext(boolean next) {
		this.next = next;
	}
	public boolean isPrevious() {
		return previous;
	}
	public void setPrevious(boolean previous) {
		this.previous = previous;
	}
	public boolean isContent() {
		return content;
	}
	public void setContent(boolean content) {
		this.content = content;
	}
	public long getTotalResultCount() {
		return totalResultCount;
	}
	public void setTotalResultCount(long totalResultCount) {
		this.totalResultCount = totalResultCount;
	}
	public int getTotalPageCount() {
		return totalPageCount;
	}
	public void setTotalPageCount(int totalPageCount) {
		this.totalPageCount = totalPageCount;
	}
	public int getPageNumber() {
		return pageNumber;
	}
	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}
	public List<Entity> getResultSet() {
		return resultSet;
	}
	public void setResultSet(List<Entity> resultSet) {
		this.resultSet = resultSet;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
}
