package com.ruanyuan.pojo;

import java.util.List;

/**
 * 课程表实体类
 * 
 * @author
 *
 */
public class Course {
	// 课程ID
	private int courseId;
	// 课程名称
	private String courseName;
	// 知识点集合
	private List<Knowledge> knowledgeList;
	// 行业
	private Field field;
	// 起始行数
	private int startLine;
	// 每页显示行数
	private int rows;

	/**
	 * 属性的getter/setter方法
	 * 
	 * @return
	 */
	public Field getField() {
		return field;
	}

	public List<Knowledge> getKnowledgeList() {
		return knowledgeList;
	}

	public void setKnowledgeList(List<Knowledge> knowledgeList) {
		this.knowledgeList = knowledgeList;
	}

	public int getCourseId() {
		return courseId;
	}

	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public void setField(Field field) {
		this.field = field;
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
		return "Course [courseId=" + courseId + ", courseName=" + courseName + ", field=" + field + ", startLine="
				+ startLine + ", rows=" + rows + "]";
	}
}