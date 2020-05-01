package com.ruanyuan.pojo;

import java.util.List;

/**
 * 题库表实体类
 * @author 
 *
 */
public class ItemBank {
	//试题ID
	private int ibId;	
	//命题人
	private User user;
	//试题
	private String question;
	//选项A
	private String optionA;
	//选项B
	private String optionB;
	//选项C
	private String optionC;
	//选项D
	private String optionD;
	//答案
	private String Answer;	
	//知识点
	private Knowledge knowledge;
	//行业
    private Field field;  
    //课程
    private Course course;
	//试卷表
	List<TestPaper> testPaperList;
	// 起始行
	private Integer start;     
	// 所取行数
	private Integer rows;  
	/**
	 * 属性的geeter/setter方法
	 * @return
	 */

	public int getIbId() {
		return ibId;
	}

	public void setIbId(int ibId) {
		this.ibId = ibId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getOptionA() {
		return optionA;
	}

	public void setOptionA(String optionA) {
		this.optionA = optionA;
	}

	public String getOptionB() {
		return optionB;
	}

	public void setOptionB(String optionB) {
		this.optionB = optionB;
	}

	public String getOptionC() {
		return optionC;
	}

	public void setOptionC(String optionC) {
		this.optionC = optionC;
	}

	public String getOptionD() {
		return optionD;
	}

	public void setOptionD(String optionD) {
		this.optionD = optionD;
	}

	public String getAnswer() {
		return Answer;
	}

	public void setAnswer(String answer) {
		Answer = answer;
	}

	public Knowledge getKnowledge() {
		return knowledge;
	}

	public void setKnowledge(Knowledge knowledge) {
		this.knowledge = knowledge;
	}

	public List<TestPaper> getTestPaperList() {
		return testPaperList;
	}

	public void setTestPaperList(List<TestPaper> testPaperList) {
		this.testPaperList = testPaperList;
	}
	
	public Field getField() {
		return field;
	}

	public void setField(Field field) {
		this.field = field;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}
	



	public Integer getStart() {
		return start;
	}

	public void setStart(Integer start) {
		this.start = start;
	}

	public Integer getRows() {
		return rows;
	}

	public void setRows(Integer rows) {
		this.rows = rows;
	}
	/**
	 * 重写的toString方法
	 */

	@Override
	public String toString() {
		return "ItemBank [ibId=" + ibId + ", user=" + user + ", question=" + question + ", optionA=" + optionA
				+ ", optionB=" + optionB + ", optionC=" + optionC + ", optionD=" + optionD + ", Answer=" + Answer
				+ ", knowledge=" + knowledge + ", field=" + field + ", course=" + course + ", testPaperList="
				+ testPaperList + ", start=" + start + ", rows=" + rows + "]";
	}








	

}
