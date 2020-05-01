package com.ruanyuan.pojo;

/**
 * 通过率实体类
 * @author 
 *
 */
public class PassRate  {
//	行业名称
	private String fieldName;
//	通过率
	private float passrate;
//	getter/setter方法
	public String getFieldName() {
		return fieldName;
	}
	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}
	public float getPassrate() {
		return passrate;
	}
	public void setPassrate(float passrate) {
		this.passrate = passrate;
	}
	@Override
	public String toString() {
		return "PassRate [fieldName=" + fieldName + ", passrate=" + passrate + "]";
	}
	public PassRate(String fieldName, float passrate) {
		super();
		this.fieldName = fieldName;
		this.passrate = passrate;
	}
}
