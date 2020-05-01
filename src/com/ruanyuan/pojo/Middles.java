package com.ruanyuan.pojo;
/**
 * 做题统计
 * @author 
 *
 */
public class Middles {
	private int statisticsId;//做题统计id
	private int userid;//用户id 没用
	private float fraction;//分数
	private int wrongNumber;//错题数
	private String itemBankIds;//错题id
	private String submissionTime;//时间
	private int testPaperId;//试卷id  没用
	private String tpName;//试卷名称
	private String userName;//姓名
	public int getStatisticsId() {
		return statisticsId;
	}
	public void setStatisticsId(int statisticsId) {
		this.statisticsId = statisticsId;
	}
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
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
	public int getTestPaperId() {
		return testPaperId;
	}
	public void setTestPaperId(int testPaperId) {
		this.testPaperId = testPaperId;
	}
	public String getTpName() {
		return tpName;
	}
	public void setTpName(String tpName) {
		this.tpName = tpName;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	@Override
	public String toString() {
		return "Middles [statisticsId=" + statisticsId + ", userid=" + userid + ", fraction=" + fraction
				+ ", wrongNumber=" + wrongNumber + ", itemBankIds=" + itemBankIds + ", submissionTime=" + submissionTime
				+ ", testPaperId=" + testPaperId + ", tpName=" + tpName + ", userName=" + userName + "]";
	}
	
	
	
	
}
