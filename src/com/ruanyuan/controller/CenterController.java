package com.ruanyuan.controller;


import java.io.IOException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;


import com.ruanyuan.common.utils.ImageUtils;
import com.ruanyuan.common.utils.MD5;
import com.ruanyuan.common.utils.Page;
import com.ruanyuan.pojo.ItemBank;
import com.ruanyuan.pojo.Middles;

import com.ruanyuan.pojo.User;
import com.ruanyuan.service.UserService;
import com.ruanyuan.service.WrongService;

/**
 * 个人中心控制层
 *
 */
@Controller
@RequestMapping("/center")
public class CenterController {
	//注解注入UserService
	@Autowired
	private UserService userService;
	//注解注入WrongService
	@Autowired
	private WrongService wrongService;
	
	/**
	 * 跳转到前台个人中心修改密码
	 * @return
	 *
	 */
	@RequestMapping("/ui/password")
	public String toUpdatePassword(){
		return "ui/center_password";
	}
	
	/**
	 * 跳转前台个人信息
	 * @return
	 */
	@RequestMapping("ui/info1")
	public String toInformation1(HttpSession session,User user){
		//获取登录用户的信息
		User user1 = (User)session.getAttribute("user");
		user.setUserId(user1.getUserId());
		User users = userService.getUserById(user1.getUserId());
		//删除原session中user的值
		session.removeAttribute("user");
		//向session添加修改过的值
		session.setAttribute("user", users);
		return "ui/centerinfo";
	}
	/**
	 * 前台电脑端个人中心修改密码
	 * @return
	 */
	@RequestMapping("/index/ui/no")
	public String indexPcUpdatePassWord(String pass,String password,String pass01,HttpSession session,HttpServletRequest request,Model model){
	//获取登录用户的信息
		User user = (User)session.getAttribute("user");
		String tip="";
		password=MD5.MD5Encode(password);
		//判断密码输入的是否一致
		if (user.getPassWord().equals(password) && pass.equals(pass01) ) {
			if(pass!=pass01) {
				tip="两次输入一致";
			}
		User user1 = new User();
		pass=MD5.MD5Encode(pass);
		user1.setPassWord(pass);
		user1.setUserId(user.getUserId());
		int count = userService.updateUserById1(user1);
		if (count>0) {
			//如果修改成功回到首页
			tip = "yes";
			session.removeAttribute("user");
			model.addAttribute("tip",tip);
				return "redirect:/ui/ui";
		}//如果失败提示
			else
				tip = "修改失败";
				model.addAttribute("tip",tip);
				//返回页面
				return "ui/center_password";
		} else {
			tip = "原密码错误";
			model.addAttribute("tip",tip); 
			//返回页面
				return "ui/center_password";
		}
	}
	/**
	 * 修改账号信息
	 * @param user
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/updateinfo")
	public String indexUpdateUser(User user,HttpSession session,HttpServletRequest request,Model model){
		
		String tip="";
		//调用修改方法
		int count = userService.updateUserById(user);
		//调用根据id查询方法
		User users = userService.getUserById(user.getUserId());
			//删除原session中user的值
		session.removeAttribute("user");
			//向session添加修改过的值
		session.setAttribute("user", users);
	
		if (count>0)
			tip = "修改成功！";
		model.addAttribute("tip", tip);
		//返回结果
		return "tip";
	}
	

	
	
	/**
	 * 添加用户信息
	 * @param user，封装表单中除图片地址以外的其他数据（要求<input>中的name跟实体类中的属性一致）
	 * @param request，用来获取文件的存储位置等
	 * @param pictureFile，封装上传图片的信息如大小、文件名、扩展名等,（要求<input>中的name跟次命名一致）。
	 * @return
	 * 注意：图片提交input输入框的name属性值要与Controller中MultipartFile
	 * 接口所声明的形参名一致，不然需要用@RequestParam注解绑定
	 */
	@RequestMapping(path = "/updateinfo", method = RequestMethod.POST)
	public String addUser(User user,HttpSession session, HttpServletRequest request, MultipartFile headimg,Model model) {
		// 得到上传图片的地址
		String tip="";
		String imgPath;
		try {
            //ImageUtils为之前添加的工具类
			imgPath = ImageUtils.upload(request, headimg);
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
			System.out.println("----------------123图片上传失败！");
		}
		//将数据提交到数据库（包含文件和普通表单数据）
		int rowNo = userService.updateUserById1(user);
		if (rowNo > 0) {
				tip = "修改成功！";
			model.addAttribute("tip", tip);
			//获取用户登录的信息
			User user1 = (User)session.getAttribute("user");
			user.setUserId(user1.getUserId());
			User users = userService.getUserById(user1.getUserId());
			session.setAttribute("user", users);
			return "ui/centerinfo";
		} else {
			return "centerinfo";
		}
	}
	
	
	
	/**
	 * 查询错题信息
	 * @return
	 */
	@RequestMapping(value="/wrongItemBank/getItemBanks.action",produces="text/html; charset=utf-8")
	@ResponseBody
	public String getWrongItemBanks(String itemBankIds){
		if(itemBankIds!=null && itemBankIds!=""){
			// 创建集合储存试题id
			Map<Integer,String> mapIbIds = new HashMap<Integer,String>();
			// 创建String数组
			String[] ibIds = null;
			//分割字符串
			for (int i = 0; i < itemBankIds.length(); i++) {
				ibIds = itemBankIds.split(",");
			}
			for (String string : ibIds) {
				// 获取字符 : 的位置进行截取字符串
				Integer a = string.indexOf(":");
				mapIbIds.put(Integer.parseInt(string.substring(0, a)), string.substring(a+1, string.length()));
			}
			// 查询错题信息
			List<ItemBank> itemBankList = this.wrongService.getItemBankByWrongId(mapIbIds);
			// 创建JSON对象
			JSONArray json = new JSONArray();
			if(itemBankList.size()>0){
				for (ItemBank itemBank : itemBankList) {
					// 创建JSONObject对象
					JSONObject jo = new JSONObject();
					// 添加所需数据
					jo.put("IbId", itemBank.getIbId());
					jo.put("question",itemBank.getQuestion());
					jo.put("optionA",itemBank.getOptionA());
					jo.put("optionB",itemBank.getOptionB());
					jo.put("optionC",itemBank.getOptionC());
					jo.put("optionD",itemBank.getOptionD());
					jo.put("Answer",itemBank.getAnswer());
					for (Entry<Integer, String> entry : mapIbIds.entrySet()) {  
						if(itemBank.getIbId() == entry.getKey()){
							jo.put("Wrong",entry.getValue());
						}
					}
					// 添加到JSONArray中
					json.put(jo);
				}
			}
			// 返回JSON数据到前台
			return json.toString();
		}else{
			return "noWrong";
		}
	}
		
	//跳转到历史做题页面
			@RequestMapping("/wrong.action")
			public String journalList(@RequestParam(defaultValue="1")Integer page,String tpName,
					@RequestParam(defaultValue="5")Integer rows, String startTime,String endTime,Integer userId,HttpServletRequest request,HttpSession session) {
				//获取用户登录的信息
				User user1 = (User)session.getAttribute("user");
				
				
				// 多条件查询
				Page<Middles> itemlist= wrongService.getAllItemBank(user1.getUserId(),startTime, endTime, page, rows,tpName);
				//存入作用域
				//request.setAttribute("userId", user1.getUserId());
				request.setAttribute("page", itemlist);
				request.setAttribute("startTime",startTime);
				request.setAttribute("endTime",endTime);
				request.setAttribute("tpName", tpName);
				//返回页面
				return "ui/wrong";	
			}
}
