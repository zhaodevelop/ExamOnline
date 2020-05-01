package com.ruanyuan.common.utils;
import java.util.List;
/**
 * 分页实体类
 * @author 
 *
 * @param <T>
 */
public class Page<T> {   
	private int total;    // 总条数
	private int page;     // 当前页
	private int size;     // 每页数
	private List<T> result; // 结果集
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public List<T> getResult() {
		return result;
	}
	public void setResult(List<T> result) {
		this.result = result;
	}
	@Override
	public String toString() {
		return "Page [total=" + total + ", page=" + page + ", size=" + size + ", result=" + result + "]";
	}

}
