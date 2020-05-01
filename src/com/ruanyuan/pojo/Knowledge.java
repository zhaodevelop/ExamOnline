package com.ruanyuan.pojo;

/**
 * 知识点表实体类
 * 
 * @author
 *
 */
public class Knowledge {
	// 知识点ID
	private int knowledgeId;
	// 知识点名称
	private String knowledgeName;
	// 课程
	private Course course;
	// 起始行数
	private int startLine;
	// 每页显示行数
	private int rows;

	/**
	 * 属性的getter/setter方法
	 */
	public int getKnowledgeId() {
		return knowledgeId;
	}

	public void setKnowledgeId(int knowledgeId) {
		this.knowledgeId = knowledgeId;
	}

	public String getKnowledgeName() {
		return knowledgeName;
	}

	public void setKnowledgeName(String knowledgeName) {
		this.knowledgeName = knowledgeName;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
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
		return "Knowledge [knowledgeId=" + knowledgeId + ", knowledgeName=" + knowledgeName + ", course=" + course
				+ ", startLine=" + startLine + ", rows=" + rows + "]";
	}
}
