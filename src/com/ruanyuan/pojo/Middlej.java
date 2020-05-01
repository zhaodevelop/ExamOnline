package com.ruanyuan.pojo;
/**
 * 登录日志用户角色
 * @author 
 *
 */
public class Middlej {
		//登录日志的id
		private int jId;
		//登录用户id
		private User user;
		//登录时间
		private String jTime;
		//登录ip
		private String ip;
		//用户姓名
		private String userName;
		//角色名称
		private String roleName;
		
		
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
			return "other [jId=" + jId + ", user=" + user + ", jTime=" + jTime + ", ip=" + ip + ", userName=" + userName
					+ ", roleName=" + roleName + "]";
		}
		public Middlej(int jId, User user, String jTime, String ip, String userName, String roleName) {
			super();
			this.jId = jId;
			this.user = user;
			this.jTime = jTime;
			this.ip = ip;
			this.userName = userName;
			this.roleName = roleName;
		}
		public Middlej() {
			super();
			// TODO Auto-generated constructor stub
		}
		
		
}
