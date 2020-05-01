package com.ruanyuan.pojo;

/**
 * 错题统计表
 * @author
 *
 */
public class Wrong {

	//错题id
	private int wrongId;
	//做题统计（外键）
	private Statistics statistics;
	/**
	 * 属性的getter/setter方法
	 * @return
	 */
	public int getWrongId() {
		return wrongId;
	}
	public void setWrongId(int wrongId) {
		this.wrongId = wrongId;
	}
	public Statistics getStatistics() {
		return statistics;
	}
	public void setStatistics(Statistics statistics) {
		this.statistics = statistics;
	}
	/**
	 * 定义toString方法
	 */
	@Override
	public String toString() {
		return "Wrong [wrongId=" + wrongId + ", statistics=" + statistics + "]";
	}
	
}
