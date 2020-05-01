package com.ruanyuan.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.ruanyuan.common.utils.ImageUtils;
import com.ruanyuan.common.utils.MD5;
import com.ruanyuan.pojo.User;
import com.ruanyuan.service.UserService;

/**
 * 修改后台个人中心控制层
 * @author 
 *
 */
@Controller
@RequestMapping("/personal")
public class PersonalController {
	
	//定义servite
	@Autowired
	private UserService userService;

	
	//跳转个人资料页
	@RequestMapping("/toPersonalData")
	public String toPersonalData(HttpSession session) {
		System.out.println("sss");
		return "admin/personalData";
	}
	//跳转修改个人资料页
	@RequestMapping("/toUpdateData")
	public String toUpdateData() {
		return "admin/updateData";
	}
	//修改个人资料
	@RequestMapping("/updateData")
	public void updateData(HttpServletResponse response,HttpSession session,User user,HttpServletRequest request, MultipartFile pictureFile) {
		//定义写出方法
		PrintWriter out=null;
		try {
			out = response.getWriter();
			response.setContentType("text/html; charset=utf-8");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//更改头像操作
		//得到上传图片的地址
		String imgPath;
		try {
            //ImageUtils为之前添加的工具类
			imgPath = ImageUtils.upload(request, pictureFile);
			//创建user对象
			if (imgPath != null) {
				// 将上传图片的地址封装到实体类
				user.setHeadImg(imgPath);
				System.out.println("-----------------图片上传成功！");
			}else{
                System.out.println("-----------------图片上传失败！");
            }
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("----------------图片上传失败！");
		}
		//调用修改方法
		int count = userService.updateUserById(user);
		//调用根据id查询方法
		User users = userService.getUserById(user.getUserId());
		//删除原session中user的值
		session.removeAttribute("user");
		//向session添加修改过的值
		session.setAttribute("user", users);
		//判断是否修改成功 如果count>0为成功 成功后关闭此页并刷新父业 
		if (count>0) {
			System.out.println("修改成功");
			out.print("<script>window.parent.location.reload()</script>");
		}else {
			out.print("<script>alert('修改失败');</script>");
		}
	}
	//跳转修改密码页
	@RequestMapping("/toUpdatePass")
	public String toUpdatePass() {
		return "admin/updatePass"; 
	}
	//修改密码
	@RequestMapping("/updatePass")
	@ResponseBody
	public int updatePass(String oldPass,String password,int userId,HttpSession session) {
		System.out.println("修改密码操作");
		User user = userService.getUserById(userId);
		oldPass=MD5.MD5Encode(oldPass);
		if (oldPass.equals(user.getPassWord())) {
			User user1 = new User();
			user1.setUserId(user.getUserId());
			password = MD5.MD5Encode(password);
			user1.setPassWord(password);
			int count = userService.updateUserById(user1);
			System.out.println("aaa");
			if (count>0) {
				//删除原session中user的值
				session.removeAttribute("user");
				return 0;
				//out.print("<script>window.parent.location.reload()</script>");
			}else {
				return 2;
			}
		}else {
			return 1;
		}
	}
	
	
}
