package com.web.webstart.base.util;

import java.util.Iterator;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public class MyPage<T> implements Page<T> {
	private int pageNum;
	private List<T> list;
	private int pageSize;
	private int totalPages;
	private int rowCount;
	/**
	 * 初始化Page
	 * @param pageNum		页号
	 * @param pageSize		页长
	 * @param list			数据列表
	 * @param rowCount		数据总条数
	 */
	public MyPage(int pageNum, int pageSize, List<T> list, int rowCount){
		this.list = list;
		this.pageNum = pageNum;
		this.pageSize = pageSize;
		this.rowCount = rowCount;
		this.totalPages = pageSize > 0 ? (int)Math.ceil(((double) rowCount)/pageSize) : 0;
	}
	public List<T> getContent() {
		return list;
	}

	public int getNumber() {
		return pageNum;
	}

	public int getNumberOfElements() {
		return list.size();
	}

	public int getSize() {
		return pageSize;
	}

	public Sort getSort() {
		return null;
	}

	public long getTotalElements() {
		return rowCount;
	}

	public int getTotalPages() {
		return totalPages;
	}

	public boolean hasContent() {
		if(list != null){
			return true;
		}
		return false;
	}

	public boolean hasNextPage() {
		if(totalPages > 0 && totalPages-1 > pageNum){
			return true;
		}
		return false;
	}

	public boolean hasPreviousPage() {
		if(pageNum > 0){
			return true;
		}
		return false;
	}

	public boolean isFirstPage() {
		if(pageNum > 0){
			return false;
		}
		return true;
	}

	public boolean isLastPage() {
		if(totalPages == 0 || (totalPages > 0 && totalPages-1 == pageNum)){
			return true;
		}
		return false;
	}

	public Iterator<T> iterator() {
		return null;
	}

	public Pageable nextPageable() {
		return null;
	}

	public Pageable previousPageable() {
		return null;
	}
	@Override
	public boolean hasNext() {
		return false;
	}
	@Override
	public boolean hasPrevious() {
		return false;
	}
	@Override
	public boolean isFirst() {
		return false;
	}
	@Override
	public boolean isLast() {
		return false;
	}

}
