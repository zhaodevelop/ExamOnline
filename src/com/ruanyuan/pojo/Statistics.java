package com.ruanyuan.pojo;

/**
 * 做题统计表
 * @author
 *
 */
public class Statistics {

	//统计id
	private int statisticsId;
	//用户（外键）
	private User user;
	//分数
	private float fraction;
	//错题数
	private int wrongNumber;
	//错题
	private String itemBankIds;
	//提交试卷日期
	private String submissionTime;
	//试卷（外键）
	private TestPaper testPaper;
	/**
	 * 属性的getter/setter方法
	 * @return
	 */
	public int getStatisticsId() {
		return statisticsId;
	}

	public void setStatisticsId(int statisticsId) {
		this.statisticsId = statisticsId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public float getFraction() {
		return fraction;
	}

	public void setFraction(float fraction) {
		this.fraction = fraction;
	}

	public int getWrongNumber() {
		return wrongNumber;
	}

	public void setWrongNumber(int wrongNumber) {
		this.wrongNumber = wrongNumber;
	}

	public String getItemBankIds() {
		return itemBankIds;
	}

	public void setItemBankIds(String itemBankIds) {
		this.itemBankIds = itemBankIds;
	}

	public String getSubmissionTime() {
		return submissionTime;
	}

	public void setSubmissionTime(String submissionTime) {
		this.submissionTime = submissionTime;
	}

	public TestPaper getTestPaper() {
		return testPaper;
	}

	public void setTestPaper(TestPaper testPaper) {
		this.testPaper = testPaper;
	}
	
	
	
	/**
	 * 定义toString方法
	 */
	
	@Override
	public String toString() {
		return "Statistics [statisticsId=" + statisticsId + ", user=" + user + ", fraction=" + fraction
				+ ", wrongNumber=" + wrongNumber + ", itemBankIds=" + itemBankIds + ", submissionTime=" + submissionTime
				+ ", testPaper=" + testPaper + "]";
	}

	
}
