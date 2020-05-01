package com.ruanyuan.service.impl;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.ruanyuan.common.utils.ExcelUtil;
import com.ruanyuan.common.utils.MD5;
import com.ruanyuan.common.utils.Page;
import com.ruanyuan.mapper.UserMapper;
import com.ruanyuan.pojo.Role;
import com.ruanyuan.pojo.User;
import com.ruanyuan.service.UserService;

/**
 * 用户业务逻辑层
 * 
 * @author
 */
@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {

	// 注解注入UserMapper
	@Autowired
	private UserMapper userMapper;

	/**
	 * 添加用户信息
	 */
	@Override
	public int addUser(User user) {
		int count = this.userMapper.addUser(user);
		return count;
	}

	/**
	 * 根据用户id删除用户信息
	 */
	@Override
	public int deleteUserById(int userId) {
		int count = this.userMapper.deleteUserById(userId);
		return count;
	}

	/**
	 * 根据用户id修改用户信息
	 */
	@Override
	public int updateUserById(User user) {
		int count = this.userMapper.updateUserById(user);
		return count;
	}

	/**
	 * 根据用户id查询用户信息
	 */
	@Override
	public User getUserById(int userId) {
		User user = this.userMapper.getUserById(userId);
		return user;
	}

	/**
	 * 根据角色id查询用户信息
	 */
	@Override
	public List<User> getUserByRoleId(int roleId) {
		List<User> userList = this.userMapper.getUserByRoleId(roleId);
		return userList;
	}

	/**
	 * 根据角色表id删除用户信息
	 */
	@Override
	public int deleteUserByRoleId(int roleId) {
		int count = this.userMapper.deleteUserByRoleId(roleId);
		return count;
	}

	/**
	 * 根据用户名称模糊查询用户信息
	 */
	@Override
	public List<User> getUserByNames(String userName) {
		// TODO Auto-generated method stub
		List<User> users = this.userMapper.getUserByNames(userName);
		return users;
	}

	/**
	 * Excel批量上传数据，注册用户
	 */
	@Override
	public int insertUserBatch(@Param("list") List<User> list) {
		// TODO Auto-generated method stub
		int count = this.userMapper.insertUserBatch(list);
		return count;
	}

	/**
	 * 根据用户名查询
	 */
	public User selectUserByName(String userName) {
		User user = this.userMapper.selectUserByName(userName);
		return user;
	}

	/**
	 * 根据角色账号查询角色信息
	 */
	@Override
	public User getUserByAccountAndPassWord(String account, String passWord) {
		User user = this.userMapper.getUserByAccountAndPassWord(account, passWord);
		return user;
	}

	/**
	 * 多条件查询所有用户信息
	 */
	@Override
	public Page<User> getAllUsers(Integer page, Integer rows, String userName, String account, Integer roleId) {
		// TODO Auto-generated method stub
		int page1 = (page - 1) * rows;
		// 查询用户列表
		List<User> users = userMapper.getAllUsers(page1, rows, userName, account, roleId);
		// 查询用户列表总记录数
		int count = userMapper.getUserNum(page1, rows, userName, account, roleId);
		System.out.println("roleId=" + roleId);
		// 创建page返回对象
		Page<User> result = new Page<User>();
		// 传入参数
		result.setPage(page);
		result.setResult(users);
		result.setSize(rows);
		result.setTotal(count);
		// 返回分页结果集
		return result;
	}

	/**
	 * 验证用户账号是否唯一
	 */
	public User testAccount(String account) {
		User user = userMapper.testAccount(account);
		return user;
	}

	/**
	 * 批量删除
	 */
	@Override
	public void deleteMoreUser(List<String> personList) {
		// TODO Auto-generated method stub
		userMapper.deleteMoreUser(personList);
	}

	/**
	 * 根据用户名模糊查询
	 */
	@Override
	public User getLikeUserName(String userName) {
		// TODO Auto-generated method stub
		User user = userMapper.getLikeUserName(userName);
		return user;
	}

	/**
	 * 根据登录账号查询
	 */
	@Override
	public User getInfoByAccount(String account) {
		// TODO Auto-generated method stub
		User user = userMapper.getInfoByAccount(account);
		return user;
	}

	/**
	 * 判断用户名是否存在
	 */
	@Override
	public User getUserByaccount(String user1) {
		// TODO Auto-generated method stub
		User user = userMapper.getInfoByAccount(user1);
		return user;
	}

	/**
	 * 根据用户id修改个人中心信息
	 */
	@Override
	public int updateUserById1(User user) {
		int count = this.userMapper.updateUserById1(user);
		return count;
	}

	/**
	 * 根据用户账号查询用户信息
	 */
	@Override
	public User getUserByaccount(User user1) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Excel实现批量上传
	 */
	public Map<String,String> importExcel(MultipartFile file, HttpServletRequest request) {
		//执行成功的数量
		int count = 0;
		Map<String,String> map = new HashMap<String,String>();
		//重名
		String duplicate = "";
		//错误
		String error ="";
		//
		int row=0;
		try {
//			获取请求文件的输入流
			InputStream in = file.getInputStream();
//			获取IO流数据
			List<List<Object>> listob = ExcelUtil.getBankListByExcel(in, file.getOriginalFilename());
			for (int i = 0; i < listob.size()-1; i++) {
				for (int j = listob.size()-1; j > i; j--) {
					if(listob.get(i).get(2).equals(listob.get(j).get(2))) {
						listob.remove(j);
					}
				}
			}
//			创建用户列表集合
			List<User> userList = new ArrayList<User>();
			// 遍历listob数据，把数据放到List中
			for (int i = 0; i < listob.size(); i++) {
				List<Object> ob = listob.get(i);
//				实例化用户实体类
				User user = new User();
//				实例化角色实体类
				Role role = new Role();
//				验证用户账号为否唯一
				User user1 = userMapper.testAccount(String.valueOf(ob.get(2)).trim());
//				通过遍历实现把每一列封装成一个model中，再把所有的model用List集合装载
				if(String.valueOf(ob.get(2)).trim().matches("^([A-Za-z0-9]+$)+") && String.valueOf(ob.get(3)).trim().matches("^([A-Za-z0-9]+$)+") &&
						(String.valueOf(ob.get(4)).trim().equals("男") || String.valueOf(ob.get(4)).trim().equals("女")) &&
						String.valueOf(ob.get(5)).trim().matches("^([0-9]+$)+") && (Integer.valueOf(String.valueOf(ob.get(8)))==1 || Integer.valueOf(String.valueOf(ob.get(8)))==2)&&
						String.valueOf(ob.get(7)).trim().matches("^([a-zA-Z0-9_\\.\\-])+\\@(([a-zA-Z0-9\\-])+\\.)+([a-zA-Z0-9]{2,4})+$")) {
						//System.out.println("ob正确："+ob);
	//					如果用户账号重复
						if (user1 != null) {	
							System.out.println("重名：" +String.valueOf(ob.get(0)).trim());
							duplicate+=String.valueOf(ob.get(0)).trim()+",";
							row++;				
						} else {
							user.setUserName(String.valueOf(ob.get(1)).trim());
							user.setAccount(String.valueOf(ob.get(2)).trim());
							user.setPassWord(MD5.MD5Encode(String.valueOf(ob.get(3)).trim()));
							user.setSex(String.valueOf(ob.get(4)).trim());
							user.setPhone(String.valueOf(ob.get(5)).trim());
							user.setAddress(String.valueOf(ob.get(6)).trim());
							user.setEmail(String.valueOf(ob.get(7)).trim());
							role.setRoleId(Integer.valueOf(String.valueOf(ob.get(8))));
							user.setRole(role);
							System.out.println("user:"+user);
	//						添加到用户集合汇总
							userList.add(user);
						}							
				}else {
					//System.out.println("ob"+ob);
					//System.out.println("错误：" +String.valueOf(ob.get(0)).trim());
					//格式错误
					row++;
					error+=String.valueOf(ob.get(0)).trim()+",";
				}
			}

//			批量插入
			if(row==0) {
				count = this.userMapper.insertUserBatch(userList);
				System.out.println("count=" + count);
			}
		} catch (Exception e) {
			return map;
		}	
		if(duplicate.contains(",")) {
			duplicate=duplicate.substring(0,duplicate.length()-1);
		}
		if(error.contains(",")) {
			error=error.substring(0,error.length()-1);
		}
		map.put("duplicate", duplicate);
		map.put("error", error);
		map.put("success",String.valueOf(count));
		return map;
	}

	/**
	 * 根据用户id查询用户
	 */
	@Override
	public User getUser(int id) {
		// TODO Auto-generated method stub
		return userMapper.getUser(id);
	}

}
