package com.ruanyuan.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.annotations.Param;
import org.springframework.web.multipart.MultipartFile;

import com.ruanyuan.common.utils.Page;
import com.ruanyuan.pojo.User;

/**
 * 用户表业务逻辑层接口
 *
 */
public interface UserService {

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
	 * 根据用户名称模糊查询用户信息
	 * 
	 * @param userName 用户姓名
	 * @return 用户集合
	 */
	public List<User> getUserByNames(String userName);

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
	public User getUserByAccountAndPassWord(String account, String passWord);

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
	public Page<User> getAllUsers(@Param("page") Integer page, @Param("rows") Integer rows,
			@Param("userName") String userName, @Param("account") String account, @Param("roleId") Integer roleId);

	/**
	 * 验证用户账号是否唯一
	 * 
	 * @param account 用户账号
	 * @return 用户实体类对象
	 */
	public User testAccount(String account);

	
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
	 * 根据id查询用户信息是否重复
	 * 
	 * @param userId用户id
	 * @return user用户类对象
	 */
	public User getUserByaccount(User user1);

	/**
	 * 判断用户名是否存在
	 * 
	 * @param user1 用户姓名
	 * @return 用户实体类对象
	 */
	public User getUserByaccount(String user1);

	/**
	 * Excel上传批量注册
	 * 
	 * @param file
	 * @param request
	 * @return
	 */
	public Map<String,String> importExcel(MultipartFile file, HttpServletRequest request);

	/**
	 * 根据id查询用户信息
	 * 
	 * @param id
	 * @return 用户
	 */
	public User getUser(int id);

}
