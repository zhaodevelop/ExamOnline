package com.ruanyuan.pojo;

/**
 * 角色表实体类
 */
public class Role {
	// 角色id
	private int roleId; 
	 // 角色名称
	private String roleName;
	
	// setter和getter方法
	public int getRoleId() {
		return roleId;
	}
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	// toString方法
	@Override
	public String toString() {
		return "Role [roleId=" + roleId + ", roleName=" + roleName + "]";
	}
	
}
