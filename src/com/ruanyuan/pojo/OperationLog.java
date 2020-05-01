package com.ruanyuan.pojo;

/**
 * 操作日志实体类
 * @author
 *
 */

public class OperationLog {
	//操作日志id
	private int oId;
	//执行操作时间
	private String oTime;
	//操作人id
	private User user;
	//操作内容
	private String content;
	//操作类别
	private String oType;
	
	//setter和getter方法
	public int getoId() {
		return oId;
	}
	public void setoId(int oId) {
		this.oId = oId;
	}

	public String getoTime() {
		return oTime;
	}
	public void setoTime(String oTime) {
		this.oTime = oTime;
	}
	public String getoType() {
		return oType;
	}
	public void setoType(String oType) {
		this.oType = oType;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	@Override
	public String toString() {
		return "OperationLog [oId=" + oId + ", oTime=" + oTime + ", user=" + user + ", content=" + content + ", oType="
				+ oType + "]";
	}

	//重写toString方法

	
}
