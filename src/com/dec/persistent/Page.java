/**
 * Copyright &copy; 2012-2013 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.dec.persistent;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import com.dec.common.config.Global;

/**
 * 分页�?
 * 
 * @author ThinkGem
 * @version 2013-7-2
 * @param <T>
 */
public class Page<T> {

	private int pageNo = 1; // 当前页码
	private int pageSize = Integer.valueOf(Global.getConfig("page.pageSize")); // 页面大小，设置为�?1”表示不进行分页（分页无效）

	private long count;// 总记录数，设置为�?1”表示不查询总数

	private int first;// 首页索引
	private int last;// 尾页索引
	private int prev;// 上一页索�?
	private int next;// 下一页索�?

	private boolean firstPage;// 是否是第�?��
	private boolean lastPage;// 是否是最后一�?

	private int length = 8;// 显示页面长度
	private int slider = 1;// 前后显示页面长度

	private List<T> list = new ArrayList<T>();

	private String orderBy = ""; // 标准查询有效�?实例�?updatedate desc, name asc

	private String funcName = "page"; // 设置点击页码调用的js函数名称，默认为page，在�?��有多个分页对象时使用�?

	private String message = ""; // 设置提示消息，显示在“共n条�?之后

	/**
	 * 构�?方法
	 * 
	 * @param request
	 *            传�? repage 参数，来记住页码
	 * @param response
	 *            用于设置 Cookie，记住页�?
	 */
	public Page(HttpServletRequest request, HttpServletResponse response) {
		this(request, response, -2);
	}

	/**
	 * 构�?方法
	 * 
	 * @param request
	 *            传�? repage 参数，来记住页码
	 * @param response
	 *            用于设置 Cookie，记住页�?
	 * @param pageSize
	 *            分页大小，如果传�?-1 则为不分页，返回�?��数据
	 */
	public Page(HttpServletRequest request, HttpServletResponse response,
			int pageSize) {
		// 设置页码参数（传递repage参数，来记住页码�?
		String no = request.getParameter("pageNo");
		if (StringUtils.isNumeric(no)) {
			this.setPageNo(Integer.parseInt(no));
		} else if (request.getParameter("repage") != null) {
			if (StringUtils.isNumeric(no)) {
				this.setPageNo(Integer.parseInt(no));
			}
		}
		// 设置页面大小参数（传递repage参数，来记住页码大小�?
		String size = request.getParameter("pageSize");
		if (StringUtils.isNumeric(size)) {
			this.setPageSize(Integer.parseInt(size));
		} else if (request.getParameter("repage") != null) {
			if (StringUtils.isNumeric(size)) {
				this.setPageSize(Integer.parseInt(size));
			}
		}
		if (pageSize != -2) {
			this.pageSize = pageSize;
		}
		// 设置排序参数
		String orderBy = request.getParameter("orderBy");
		if (StringUtils.isNotBlank(orderBy)) {
			this.setOrderBy(orderBy);
		}
	}

	public Page() {
		this(0, 0);
	}

	/**
	 * 构�?方法
	 * 
	 * @param pageNo
	 *            当前页码
	 * @param pageSize
	 *            分页大小
	 */
	public Page(int pageNo, int pageSize) {
		this(pageNo, pageSize, 0);
	}

	/**
	 * 构�?方法
	 * 
	 * @param pageNo
	 *            当前页码
	 * @param pageSize
	 *            分页大小
	 * @param count
	 *            数据条数
	 */
	public Page(int pageNo, int pageSize, long count) {
		this(pageNo, pageSize, count, new ArrayList<T>());
	}

	/**
	 * 构�?方法
	 * 
	 * @param pageNo
	 *            当前页码
	 * @param pageSize
	 *            分页大小
	 * @param count
	 *            数据条数
	 * @param list
	 *            本页数据对象列表
	 */
	public Page(int pageNo, int pageSize, long count, List<T> list) {
		this.setCount(count);
		this.setPageNo(pageNo);
		this.pageSize = pageSize;
		this.setList(list);
	}

	/**
	 * 初始化参�?
	 */
	public void initialize() {

		// 1
		this.first = 1;

		this.last = (int) (count / (this.pageSize < 1 ? 20 : this.pageSize)
				+ first - 1);

		if (this.count % this.pageSize != 0 || this.last == 0) {
			this.last++;
		}

		if (this.last < this.first) {
			this.last = this.first;
		}

		if (this.pageNo <= 1) {
			this.pageNo = this.first;
			this.firstPage = true;
		}

		if (this.pageNo >= this.last) {
			this.pageNo = this.last;
			this.lastPage = true;
		}

		if (this.pageNo < this.last - 1) {
			this.next = this.pageNo + 1;
		} else {
			this.next = this.last;
		}

		if (this.pageNo > 1) {
			this.prev = this.pageNo - 1;
		} else {
			this.prev = this.first;
		}

		// 2
		if (this.pageNo < this.first) {// 如果当前页小于首�?
			this.pageNo = this.first;
		}

		if (this.pageNo > this.last) {// 如果当前页大于尾�?
			this.pageNo = this.last;
		}

	}

	/**
	 * 获取设置总数
	 * 
	 * @return
	 */
	public long getCount() {
		return count;
	}

	/**
	 * 设置数据总数
	 * 
	 * @param count
	 */
	public void setCount(long count) {
		this.count = count;
		if (pageSize >= count) {
			pageNo = 1;
		}
	}

	/**
	 * 获取当前页码
	 * 
	 * @return
	 */
	public int getPageNo() {
		return pageNo;
	}

	/**
	 * 设置当前页码
	 * 
	 * @param pageNo
	 */
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	/**
	 * 获取页面大小
	 * 
	 * @return
	 */
	public int getPageSize() {
		return pageSize;
	}

	/**
	 * 设置页面大小（最�?00�?
	 * 
	 * @param pageSize
	 */
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize <= 0 ? 10 : pageSize > 500 ? 500 : pageSize;
	}

	/**
	 * 首页索引
	 * 
	 * @return
	 */
	public int getFirst() {
		return first;
	}

	/**
	 * 尾页索引
	 * 
	 * @return
	 */
	public int getLast() {
		return last;
	}

	/**
	 * 获取页面总数
	 * 
	 * @return getLast();
	 */
	public int getTotalPage() {
		return getLast();
	}

	/**
	 * 是否为第�?��
	 * 
	 * @return
	 */
	public boolean isFirstPage() {
		return firstPage;
	}

	/**
	 * 是否为最后一�?
	 * 
	 * @return
	 */
	public boolean isLastPage() {
		return lastPage;
	}

	/**
	 * 上一页索引�?
	 * 
	 * @return
	 */
	public int getPrev() {
		if (isFirstPage()) {
			return pageNo;
		} else {
			return pageNo - 1;
		}
	}

	/**
	 * 下一页索引�?
	 * 
	 * @return
	 */
	public int getNext() {
		if (isLastPage()) {
			return pageNo;
		} else {
			return pageNo + 1;
		}
	}

	/**
	 * 获取本页数据对象列表
	 * 
	 * @return List<T>
	 */
	public List<T> getList() {
		return list;
	}

	/**
	 * 设置本页数据对象列表
	 * 
	 * @param list
	 */
	public void setList(List<T> list) {
		this.list = list;
	}

	/**
	 * 获取查询排序字符�?
	 * 
	 * @return
	 */
	public String getOrderBy() {
		return orderBy;
	}

	/**
	 * 设置查询排序，标准查询有效， 实例�?updatedate desc, name asc
	 */
	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}

	/**
	 * 获取点击页码调用的js函数名称 function ${page.funcName}(pageNo){location=
	 * "${ctx}/list-${category.id}${urlSuffix}?pageNo="+i;}
	 * 
	 * @return
	 */
	public String getFuncName() {
		return funcName;
	}

	/**
	 * 设置点击页码调用的js函数名称，默认为page，在�?��有多个分页对象时使用�?
	 * 
	 * @param funcName
	 *            默认为page
	 */
	public void setFuncName(String funcName) {
		this.funcName = funcName;
	}

	/**
	 * 设置提示消息，显示在“共n条�?之后
	 * 
	 * @param message
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * 分页是否有效
	 * 
	 * @return this.pageSize==-1
	 */
	public boolean isDisabled() {
		return this.pageSize == -1;
	}

	/**
	 * 是否进行总数统计
	 * 
	 * @return this.count==-1
	 */
	public boolean isNotCount() {
		return this.count == -1;
	}

	/**
	 * 获取 Hibernate FirstResult
	 */
	public int getFirstResult() {
		int firstResult = (getPageNo() - 1) * getPageSize();
		if (firstResult >= getCount()) {
			firstResult = 0;
		}
		return firstResult;
	}

	/**
	 * 获取 Hibernate MaxResults
	 */
	public int getMaxResults() {
		return getPageSize();
	}

	public String toString() {
		return "Page [pageNo=" + pageNo + ", pageSize=" + pageSize + ", count="
				+ count + ", first=" + first + ", last=" + last + ", prev="
				+ prev + ", next=" + next + ", firstPage=" + firstPage
				+ ", lastPage=" + lastPage + ", length=" + length + ", slider="
				+ slider + ", list=" + list + ", orderBy=" + orderBy
				+ ", funcName=" + funcName + ", message=" + message + "]";
	}
}
