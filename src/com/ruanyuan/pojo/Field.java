package com.ruanyuan.pojo;

import java.util.List;

/**
 * 行业表实体类
 * 
 * @author
 * 
 */
public class Field {
	// 行业id
	private int fieldId;
	// 行业名称
	private String fieldName;
	// 行业介绍
	private String introduce;
	// 行业集合
	private List<Course> courseList;
	// 起始行数
	private int startLine;
	// 每页显示行数
	private int rows;

	/**
	 * 属性的getter/setter方法
	 * 
	 * @return
	 */
	public int getFieldId() {
		return fieldId;
	}

	public List<Course> getCourseList() {
		return courseList;
	}

	public void setCourseList(List<Course> courseList) {
		this.courseList = courseList;
	}

	public void setFieldId(int fieldId) {
		this.fieldId = fieldId;
	}

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public void setFileName(String fieldName) {
		this.fieldName = fieldName;
	}

	public String getIntroduce() {
		return introduce;
	}

	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}

	public int getStartLine() {
		return startLine;
	}

	public void setStartLine(int startLine) {
		this.startLine = startLine;
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	/**
	 * 重写的toString方法
	 */
	@Override
	public String toString() {
		return "Field [fieldId=" + fieldId + ", fieldName=" + fieldName + ", introduce=" + introduce + ", startLine="
				+ startLine + ", rows=" + rows + "]";
	}
}
