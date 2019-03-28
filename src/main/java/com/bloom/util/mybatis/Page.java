package com.bloom.util.mybatis;

import java.util.List;

public class Page<T> {
	public static final int DEFAULT_PAGE_NO = 1;
	public static final int DEFAULT_PAGE_SIZE = 10;
	public static final String DEFAULT_PAGE_NO_TEXT = "1";
	public static final String DEFAULT_PAGE_SIZE_TEXT = "10";
	public static Page defaultPage() {
		return new Page(DEFAULT_PAGE_NO,DEFAULT_PAGE_SIZE);
	}
	
	protected int pageNo = 1; // 当前页, 默认为第1页
	protected int pageSize = DEFAULT_PAGE_SIZE; // 每页记录数
	protected long totalCount = -1; // 总记录数, 默认为-1, 表示需要查询
	protected int totalPage = -1; // 总页数, 默认为-1, 表示需要计算
	protected boolean needTotalCount=true;//是否需要总页数，默认为true,如果真的不需要总页数可以主动设置为false，这样可以减少一次数据库查询
	protected List<T> results; // 当前页记录List形式
	
	private String totalCountSql;//查询总数的sql
	
	
	public Page(){
		
	}
	public Page(int startIndex){
		this(startIndex,DEFAULT_PAGE_SIZE);
	}
	public Page(int pageNo,int pageSize){
		this.pageNo=pageNo;
		this.pageSize=pageSize;
		this.needTotalCount=true;
	}
	public Page(int startIndex,int pageSize,boolean needTotalCount){
		this.pageNo=startIndex/pageSize+1;
		this.pageSize=pageSize;
		this.needTotalCount=needTotalCount;
	}
	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public int getPageSize() {
		return pageSize;
	}
	
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
		computeTotalPage();
	}
	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalCount(long totalCount) {
		this.totalCount = totalCount;
		computeTotalPage();
	}

	public long getTotalCount(){
		return this.totalCount;
	}
	protected void computeTotalPage() {
		if (getPageSize() > 0 && totalCount > -1) {
			this.totalPage = (int) (totalCount % getPageSize() == 0 ? totalCount / getPageSize() :totalCount / getPageSize() + 1);
		}
	}

	public List<T> getResults() {
		return results;
	}

	public void setResults(List<T> results) {
		this.results = results;
	}

	/**
	 * @return the totalCountSql
	 */
	public String getTotalCountSql() {
		return totalCountSql;
	}
	/**
	 * @param totalCountSql the totalCountSql to set
	 */
	public void setTotalCountSql(String totalCountSql) {
		this.totalCountSql = totalCountSql;
	}
	

}
