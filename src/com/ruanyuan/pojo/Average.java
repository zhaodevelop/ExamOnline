package com.ruanyuan.pojo;
/**
 * 平均分
 * @author 
 *
 */
public class Average implements Comparable<Average> {
//	行业名称
	private String fieldName;
//    平均分
	private float average;
	public String getFieldName() {
		return fieldName;
	}
	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}
	public float getAverage() {
		return average;
	}
	public void setAverage(float average) {
		this.average = average;
	}
	public Average(String fieldName, float average) {
		super();
		this.fieldName = fieldName;
		this.average = average;
	}
	public Average() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Average [fieldName=" + fieldName + ", average=" + average + "]";
	}
	@Override
	public int compareTo(Average o) {
		// TODO Auto-generated method stub
		return (int) (this.average-o.getAverage());
	}
	
}
