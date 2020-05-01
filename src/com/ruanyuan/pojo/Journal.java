package com.ruanyuan.pojo;



/**
 * 登录日志实体类
 * @author 
 *
 */
public class Journal {
	//登录日志的id
	private int jId;
	//登录用户id
	private User user;
	//登录时间
	private String jTime;
	//登录ip
	private String ip;
	
	//setter和getter方法
	public int getjId() {
		return jId;
	}
	public void setjId(int jId) {
		this.jId = jId;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}

	public String getjTime() {
		return jTime;
	}
	public void setjTime(String jTime) {
		this.jTime = jTime;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	//重写toString方法
	@Override
	public String toString() {
		return "Journal [jId=" + jId + ", user=" + user + ", jTime=" + jTime + ", ip=" + ip + "]";
	}
	
	
}
