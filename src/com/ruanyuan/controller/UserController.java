package com.ruanyuan.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.ruanyuan.common.utils.ImageUtils;
import com.ruanyuan.common.utils.MD5;
import com.ruanyuan.common.utils.Page;
import com.ruanyuan.pojo.User;
import com.ruanyuan.service.UserService;

import net.sf.json.JSONObject;


/**
 * 后台用户管理控制层
 * 
 * @author
 */
@Controller
@RequestMapping("/User")
public class UserController {
	//注解注入userService
	@Autowired
	@Qualifier("userService")
	private UserService userService;

	/**
	 * 展示所有用户信息方法
	 * 
	 * @param page     页数
	 * @param rows     行数
	 * @param userName 用户姓名
	 * @param account  用户账号
	 * @param model    model层
	 * @param power    角色
	 * @return 跳转地址
	 */
	@RequestMapping("/allUser")
	public String showAllUsers(@RequestParam(defaultValue = "1") Integer page,
			@RequestParam(defaultValue = "6") Integer rows, String userNamee, String account, Model model,
			Integer power) {
		//多条件查询用户信息
		Page<User> users = userService.getAllUsers(page, rows, userNamee, account, power);
		//添加到作用域
		model.addAttribute("page", users);
		model.addAttribute("account", account);
		model.addAttribute("userName", userNamee);
		model.addAttribute("power", power);
		//跳转到用户管理页面
		return "admin/userManage";
	}

	/**
	 * 添加用户
	 * 
	 * @param img     图片信息
	 * @param request request请求
	 * @param user    用户实体类
	 * @param model   model层
	 * @return 跳转到用户展示页
	 */
	@RequestMapping(value = "/add")
	public String newUser1(@RequestParam("headimg") MultipartFile img, HttpServletRequest request, User user,
			Model model) throws IOException {
		String imgPath;
		String password = MD5.MD5Encode(user.getPassWord());
		user.setPassWord(password);
		
		
		//上传图片
		imgPath = ImageUtils.upload(request, img);
		//将上传图片的地址封装到实体类
		user.setHeadImg(imgPath);
		System.out.println("99999999999999999999999:"+user);
		//添加用户信息，
		userService.addUser(user);
		return "redirect:/User/allUser";
	}

	/**
	 * 验证登录名是否唯一
	 * 
	 * @param account  用户账号
	 * @param request  request请求
	 * @param response response响应
	 * @param model    model层
	 * @throws IOException
	 */
	@RequestMapping("/testAccount")
	@ResponseBody
	public void AccountTest(String account, HttpServletRequest request, HttpServletResponse response,
			ModelAndView model) throws IOException {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		try {
			//验证用户账号是否唯一
			User user = userService.testAccount(account);
			//如果不唯一，提示信息
			if (user != null) {
				out.println("*该用户名已经存在，请重新输入");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			//如果唯一，提示信息
			out.println("*该用户名已经存在，请重新输入");
		}
		//释放资源
		out.flush();
		out.close();
	}

	/**
	 * 删除用户方法
	 * 
	 * @param request request请求
	 * @return 影响的行数
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public int deleteUser(HttpServletRequest request) {
		//获取请求参数
		String items = request.getParameter("ssid");
		int item = Integer.parseInt(items);
		//根据用户id删除用户信息，返回受影响的行数
		int id1 = userService.deleteUserById(item);
		return id1;
	}

	/**
	 * (修改)根据id获取用户信息方法
	 * @param id 用户id
	 * @return
	 */
	@RequestMapping("/toupdate")
	@ResponseBody
	public User toUpdateUser(int id) {
		User user = userService.getUserById(id);
		return user;
	}
	/**
	 * 修改用户信息
	 * @param user
	 * @return 跳转至用户展示页
	 */
	@RequestMapping("/update")
	@ResponseBody
	public String  updateUser(User user) {
		if(user.getPassWord().length()!=32) {
			System.out.println("99999999999999999999999999999999999999999999999999999999999999999");
		String passWord = MD5.MD5Encode(user.getPassWord());
		user.setPassWord(passWord);
		}
		int id = userService.updateUserById(user);
		if (id>0) {
			return "OK";
		}else {
			return "false";
		}
	}

	/**
	 * 批量删除用户
	 * 
	 * @param request request请求
	 * @param response response响应
	 */
	@RequestMapping("/batchDel")
	@ResponseBody
	public void batchDelete(HttpServletRequest request, HttpServletResponse response) {
		//获取请求参数
		String items = request.getParameter("items");
		//新建集合，存放序号
		List<String> list = new ArrayList<String>();
		//截取字符串
		String[] strs = items.split(",");
		//遍历字符串
		for (String str : strs) {
			list.add(str);
		}
		//多条件删除用户
		userService.deleteMoreUser(list);
	}

	/**
	 *  Excel批量导入数据，注册用户
	 * @param file 文件
	 * @param request request请求
	 * @return 上传结果
	 */
	@RequestMapping(value = "/importExcel", produces = "text/html;charset=utf-8")
	@ResponseBody
	public String deltemBank(MultipartFile file, HttpServletRequest request) {
		// 调用查询方法
		Map<String,String> map = userService.importExcel(file, request);
        JSONObject obj = new JSONObject();
        obj.put("list", map);
        String json = obj.toString();//数据转为JSON格式的
		// 返回结果
		return json;
	}
	/**
	 * 上传案例文件下载
	 * @param request:request对象
	 * @param filename 下载的文件名称
	 * @return Execption
	 */
	@RequestMapping("/download")
	public ResponseEntity<byte[]> fileDownload(HttpServletRequest request,String filename){
		//指定下载的文件所在路径
		String path = request.getServletContext().getRealPath("/file/");
		//创建该文件对象
		File file = new File(path+File.separator+filename);
		//对文件名编码，防止中文文件乱码
		try {
			filename = this.getFilename(request, filename);
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		//设置响应头
		HttpHeaders headers = new HttpHeaders();
		//通知浏览器以下载的方式打开文件
		headers.setContentDispositionFormData("attachment", filename);
		//定义以流的形式下载返回文件的数据
		headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
		//使用StringMVC框架的ResponseEntity对象封装返回下载数据
		try {
			return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file),headers,HttpStatus.OK);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}
	/**
	 * 根据浏览器的不同进行编码设置，返回编码后的文件名
	 * @throws UnsupportedEncodingException 
	 */
	public String getFilename(HttpServletRequest request,String filename) throws UnsupportedEncodingException {
		//IE不同版本User-Agent中出现的关键词
		String[] IEBrowserKeyWords = {"MSIE","Trident","Edge"};
		//获取请求代理信息
		String userAgent = request.getHeader("User-Agent");
		for (String keyWord : IEBrowserKeyWords) {
			if(userAgent.contains(keyWord)) {
				//IE内核浏览器，统一为UTF-8编码显示
				try {
					return URLEncoder.encode(filename,"UTF-8");
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		//谷歌等其他浏览器统一为ISO-8859-1编码显示
		return new String(filename.getBytes("UTF-8"),"ISO-8859-1");
		
	}
}
