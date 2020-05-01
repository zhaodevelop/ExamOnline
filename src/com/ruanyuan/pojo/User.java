package com.ruanyuan.pojo;

/**
 * 用户表实体类
 * 
 */
public class User {
	// 用户id
	private int userId;	
	// 用户名称
	private String userName;
	// 用户账号
	private String account;
	// 用户密码
	private String passWord;
	// 性别
	private String sex;	
	// 电话号码
	private String phone;
	// 住址
	private String address;	
	//邮箱
	private String email;
	// 头像
	private String headImg;	
	// 角色表外键
	private Role role;  
	// 起始行
	private Integer start; 
	  // 所取行数
	private Integer rows;           
	
	/**
	 * 属性的geeter/setter方法
	 * @return
	 */
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getPassWord() {
		return passWord;
	}
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getHeadImg() {
		return headImg;
	}
	public void setHeadImg(String headImg) {
		this.headImg = headImg;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
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
		return "User [userId=" + userId + ", userName=" + userName
				+ ", account=" + account + ", passWord=" + passWord + ", sex="
				+ sex + ", phone=" + phone + ", address=" + address
				+ ", email=" + email + ", headImg=" + headImg + ", role="
				+ role + "]";
	}

}
