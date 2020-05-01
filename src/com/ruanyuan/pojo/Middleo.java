package com.ruanyuan.pojo;
/**
 * 操作日志
 * @author 
 *
 */
public class Middleo {
	//操作日志的id
	private int oId;
	//操作用户id
	private User user;
	//操作时间
	private String oTime;
	//操作内容
	private String content;
	//操作类别
	private String oType;
	//用户姓名
	private String userName;
	//角色名称
	private String roleName;
	public int getoId() {
		return oId;
	}
	public void setoId(int oId) {
		this.oId = oId;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getoTime() {
		return oTime;
	}
	public void setoTime(String oTime) {
		this.oTime = oTime;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getoType() {
		return oType;
	}
	public void setoType(String oType) {
		this.oType = oType;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	@Override
	public String toString() {
		return "Middleo [oId=" + oId + ", user=" + user + ", oTime=" + oTime + ", content=" + content + ", oType="
				+ oType + ", userName=" + userName + ", roleName=" + roleName + "]";
	}
	public Middleo(int oId, User user, String oTime, String content, String oType, String userName, String roleName) {
		super();
		this.oId = oId;
		this.user = user;
		this.oTime = oTime;
		this.content = content;
		this.oType = oType;
		this.userName = userName;
		this.roleName = roleName;
	}
	public Middleo() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
}
