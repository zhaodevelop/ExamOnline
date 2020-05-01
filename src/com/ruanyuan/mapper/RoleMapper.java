package com.ruanyuan.mapper;

import java.util.List;

import com.ruanyuan.pojo.Role;

/**
 * 角色表数据访问接口
 *
 */
public interface RoleMapper {

	/**
	 * 添加角色信息
	 * @param role实体对象
	 * @return 受影响行数
	 */
	public int addRole(Role role);
	/**
	 * 根据角色id删除信息
	 * @param roleId 角色id
	 * @return 受影响行数
	 */
	public int deleteRoleById(int roleId);
	/**
	 * 根据id修改角色信息
	 * @param role实体对象
	 * @return 受影响行数
	 */
	public int updateRoleById(Role role);
	/**
	 * 根据角色id查询信息
	 * @param roleId 角色id
	 * @return Role对象
	 */
	public Role getRoleById(int roleId);
	/**
	 * 查询全部
	 * @return
	 */
	public List<Role> getAllRole();
}
