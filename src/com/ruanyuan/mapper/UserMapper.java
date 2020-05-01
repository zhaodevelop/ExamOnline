package com.ruanyuan.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ruanyuan.pojo.User;

/**
 * 用户表实体类接口
 *
 */
public interface UserMapper {

	/**
	 * 添加用户信息
	 * 
	 * @param user用户类对象
	 * @return 受影响行数
	 */
	public int addUser(User user);

	/**
	 * 根据id删除用户信息
	 * 
	 * @param userId 用户id
	 * @return 受影响行数
	 */
	public int deleteUserById(int userId);

	/**
	 * 根据id修改用户信息
	 * 
	 * @param user用户类对象
	 * @return 受影响行数
	 */
	public int updateUserById(User user);

	/**
	 * 根据id查询用户信息
	 * 
	 * @param userId用户id
	 * @return user用户类对象
	 */
	public User getUserById(int userId);

	/**
	 * 根据角色id查询用户信息
	 * 
	 * @return 用户集合
	 */
	public List<User> getUserByRoleId(int roleId);

	/**
	 * 根据角色表id删除用户信息
	 * 
	 * @param roleId 角色id
	 * @return 受影响的行数
	 */
	public int deleteUserByRoleId(int roleId);

	/**
	 * 根据用户名称查询用户信息
	 * 
	 * @param userName 用户姓名
	 * @return 用户集合
	 */
	public List<User> getUserByNames(@Param("userName") String userName);

	/**
	 * 根据姓名查询
	 * 
	 * @param userName 用户姓名
	 * @return 用户实体类对象
	 */
	public User selectUserByName(@Param("userName") String userName);

	/**
	 * 根据角色账号查询角色信息
	 * 
	 * @param account  账号
	 * @param passWord 密码
	 * @return 用户实体类对象
	 */
	public User getUserByAccountAndPassWord(@Param("account") String account, @Param("passWord") String passWord);

	/**
	 * Excel批量上传数据注册用户
	 * 
	 * @param list 用户集合
	 * @return 受影响的行数
	 */
	public int insertUserBatch(@Param("list") List<User> list);

	/**
	 * 多条件查询查询用户信息
	 * 
	 * @param start    起始行
	 * @param rows     所取行数
	 * @param userName 用户姓名
	 * @param account  用户账号
	 * @param roleId   角色id
	 * @return 用户集合
	 */
	public List<User> getAllUsers(@Param("start") Integer start, @Param("rows") Integer rows,
			@Param("userName") String userName, @Param("account") String account, @Param("roleId") Integer roleId);

	/**
	 * 验证用户账号是否唯一
	 * 
	 * @param account 用户账号
	 * @return 用户实体类对象
	 */
	public User testAccount(String account);

	/**
	 * 多条件查询用户数量
	 * 
	 * @param start    起始行
	 * @param rows     所取行数
	 * @param userName 用户姓名
	 * @param account  用户账号
	 * @param roleId   角色id
	 * @return 用户数量
	 */
	public int getUserNum(@Param("start") Integer start, @Param("rows") Integer rows,
			@Param("userName") String userName, @Param("account") String account, @Param("roleId") Integer roleId);

	/**
	 * 批量删除用户
	 * 
	 * @param personList 所选用户集合
	 */
	public void deleteMoreUser(List<String> personList);

	/**
	 * 根据用户名模糊查询
	 * 
	 * @param userName 用户姓名
	 * @return 用户实体类对象
	 */
	public User getLikeUserName(String userName);

	/**
	 * 根据登录账号查询用户
	 * 
	 * @param account 用户账号
	 * @return 用户实体类对象
	 */
	public User getInfoByAccount(String account);

	/**
	 * 根据id修改个人中心信息
	 * 
	 * @param 用户实体类类对象
	 * @return 受影响行数
	 */
	public int updateUserById1(User user);

	/**
	 * 获取用户信息
	 * 
	 * @param id 用户id
	 * @return 用户实体类对象
	 */
	public User getUser(int id);
}
