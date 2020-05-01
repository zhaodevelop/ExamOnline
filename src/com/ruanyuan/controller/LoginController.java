package com.ruanyuan.controller;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ruanyuan.common.utils.MD5;
import com.ruanyuan.pojo.Field;
import com.ruanyuan.pojo.Journal;
import com.ruanyuan.pojo.User;
import com.ruanyuan.service.FieldService;
import com.ruanyuan.service.JournalService;
import com.ruanyuan.service.UserService;

/**
 * 登录控制层
 * @author 
 *
 */
@Controller
@RequestMapping("/login")
public class LoginController {
	//注解注入FieldService
	@Autowired
	private FieldService fieldService;
	//注解注入UserService
	@Autowired
	private UserService userService;
	//注解注入JournalService	
	@Autowired
	public JournalService journalService;
	/**
	 * 跳转到登录页
	 * @return
	 */
	@RequestMapping("toLogin")
	public String toLogin() {
		return "ui/login";
	}
	/**
	 * 使用Ajax判断用户名和密码
	 * @param login 账号
	 * @param password 密码
	 * @param session
	 * @return 判断
	 * @throws UnknownHostException 
	 */
	@RequestMapping("/loginTest")
	@ResponseBody
	public int checklogin(String login,String password,HttpSession session){
		//MD5加密
		String password1 = MD5.MD5Encode(password);
		User user = userService.getUserByAccountAndPassWord(login, password1);
		//用户名密码正确
		if(user!=null) {
			//向session中添加user信息
            session.setAttribute("user", user);
            //判断是否有头像 如没有则加入默认头像
            if (user.getHeadImg()==null) {
				user.setHeadImg("images/admin/bg.png");
			}
            //添加登录日志
        	Journal journal=new Journal();
            journal.setUser(user);
            String ip = null;
			try {
				ip = InetAddress.getLocalHost().getHostAddress();
			} catch (UnknownHostException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
   		 	journal.setIp(ip);
         	SimpleDateFormat date=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            journal.setjTime(date.format(new Date()));
            journalService.addJournal(journal);
            //判断用户级别
            if (user.getRole().getRoleId()==1) {
                //返会操作选项
            	return 1;
			}else {
				//返会操作选项
				return 2;
			}
            
        }else {
        	//返会操作选项
            return 0;
        }      
	}
	/**
	 * 跳转登陆后的页面
	 * @return
	 */
	@RequestMapping("/login")
	public String getLogin( Model model) {
		//获取全部课程信息
		List<Field> fieldList = fieldService.getAllField();
		model.addAttribute("fieldList", fieldList);
		//返会跳转页面
		return "ui/index";
	}	
		
	/**
	 * 跳转到后台页面
	 * @return
	 */
	@RequestMapping("/adminIndex")
	public String login(HttpSession session) {
		User user = (User) session.getAttribute("user");
		if(user.getRole().getRoleId()==1) {
			//返会跳转页面
			return "admin/index";
		}
		//返会跳转页面
		return "redirect:/ui/ui";
	}	
	

	//跳转到用户管理页面
	@RequestMapping("/userManage.action")
	public String test2() {
		//返会跳转页面
		return "admin/userManage";
	}
	//abc
	@RequestMapping("/abc")
	public String test4() {
		//返会跳转页面
		return "admin/abc";
	}
}
