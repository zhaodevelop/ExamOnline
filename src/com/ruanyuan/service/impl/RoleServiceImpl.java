package com.ruanyuan.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



import com.ruanyuan.mapper.RoleMapper;
import com.ruanyuan.pojo.Role;
import com.ruanyuan.service.RoleService;

/**
 * 角色表业务逻辑层接口实现类
 *
 */
@Service("roleService")
@Transactional
public class RoleServiceImpl implements RoleService {

	// 注入RoleMapper
	@Autowired
	private RoleMapper roleMapper;
	
	
	public void setRoleMapper(RoleMapper roleMapper) {
		this.roleMapper = roleMapper;
	}

	/**
	 * 添加角色信息
	 */
	public int addRole(Role role) {
		int count = this.roleMapper.addRole(role);
		return count;
	}

	/**
	 * 根据id删除角色信息
	 */
	public int deleteRoleById(int roleId) {
		int count = this.roleMapper.deleteRoleById(roleId);
		return count;
	}

	/**
	 * 根据id修改角色信息
	 */
	public int updateRoleById(Role role) {
		int count = this.roleMapper.updateRoleById(role);
		return count;
	}

	/**
	 * 根据id查询角色信息
	 */
	public Role getRoleById(int roleId) {
		Role role = this.roleMapper.getRoleById(roleId);
		return role;
	}

	public List<Role> getAllRole() {
		// TODO Auto-generated method stub
		return roleMapper.getAllRole();
	}

}
