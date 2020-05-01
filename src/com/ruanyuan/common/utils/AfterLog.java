package com.ruanyuan.common.utils;

import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpSession;

import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.beans.factory.annotation.Autowired;

import com.ruanyuan.mapper.CourseMapper;
import com.ruanyuan.pojo.Course;
import com.ruanyuan.pojo.Field;
import com.ruanyuan.pojo.ItemBank;
import com.ruanyuan.pojo.Knowledge;
import com.ruanyuan.pojo.OperationLog;
import com.ruanyuan.pojo.Role;
import com.ruanyuan.pojo.TestPaper;
import com.ruanyuan.pojo.User;
import com.ruanyuan.service.FieldService;
import com.ruanyuan.service.ItemBankService;
import com.ruanyuan.service.KnowledgeService;
import com.ruanyuan.service.OperationLogService;
import com.ruanyuan.service.RoleService;
import com.ruanyuan.service.TestPaperService;
import com.ruanyuan.service.UserService;
/**
 * 记录操作日志
 * @author 
 *
 */
public class AfterLog implements MethodBeforeAdvice{


	@Autowired
	private CourseMapper courseMapper;
	@Autowired
	private OperationLogService operationLogServic;
	@Autowired
	private FieldService fieldService;
	@Autowired
	private ItemBankService itemBankService;
	@Autowired
	private KnowledgeService knowledgeService;
	@Autowired
	private RoleService roleService;
	@Autowired
	private TestPaperService testpaperService;
	@Autowired
	private UserService userService;
	@Autowired
	private HttpSession session;








	/**
	 * method要执行的目标对象的方法
	 * args参数
	 * traget目标对象
	 */
	@Override
	public void before(Method method, Object[] args, Object traget) throws Throwable {
		// TODO Auto-generated method stub
		OperationLog op= new OperationLog();
		SimpleDateFormat date=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");	 
		op.setoTime(date.format(new Date()));		
		/**
		 * 判断那个表
		 */
		String name=method.getName();
		if (name.contains("Course")) {
			name ="课程";
		}else if (name.contains("course")) {
			name ="课程";
		}else if (name.contains("Field")) {
			name ="行业";
		}else if (name.contains("field")) {
			name ="行业";
		}else if (name.contains("ItemBank")) {
			name ="题库";
		}else if (name.contains("itemBank")) {
			name ="题库";
		}else if (name.contains("Journal")) {
			name ="登录日志";
		}else if (name.contains("journal")) {
			name ="登录日志";
		}else if (name.contains("Knowledge")) {
			name ="知识点";
		}else if (name.contains("knowledge")) {
			name ="知识点";
		}else if (name.contains("OperationLog")) {
			name ="操作日志";
		}else if (name.contains("operationLog")) {
			name ="操作日志";
		}else if (name.contains("Role")) {
			name ="角色";
		}else if (name.contains("role")) {
			name ="角色";
		}else if (name.contains("Statistics")) {
			name ="做题统计";
		}else if (name.contains("statistics")) {
			name ="做题统计";
		}else if (name.contains("TestPaper")) {
			name ="试卷";
		}else if (name.contains("testPaper")) {
			name ="试卷";
		}else if (name.contains("User")) {
			name ="用户";
		}else if (name.contains("user")) {
			name ="用户";
		}else if (name.contains("Wrong")) {
			name ="错题";
		}else if (name.contains("wrong")) {
			name ="错题";
		}else if (name.contains("ItemPaper")) {
			name ="试卷试题关系";
		}else if (name.contains("itemPaper")) {
			name ="试卷试题关系";
		}else {
			name =method.getName();
		}

		
		/**
		 * 判断不写入操作日志的表
		 */
		switch (name) {
		case "登录日志":
			System.out.println("不写入的登录日志表");
			break;
		case "操作日志":
			System.out.println("不写入的操作日志表");

			break;
		case "试卷试题关系":
			System.out.println("不写入的表");

			break;
		case "错题":
			System.out.println("不写入的表");

			break;
		case "做题统计":
			System.out.println("不写入的表");

			break;

			/**
			 * 如果不是以上表则写入操作日志
			 */
		default:
			//参数处理
			String params = ""; 
			for (Object arg : args) {
				System.out.println(arg);
				params+=arg;	
			}
			System.out.println("参数"+params+"长度"+params.length());

			/**
			 * 根据类型写入数据
			 */
			if(method.getName().contains("update")){
				User user = (User) session.getAttribute("user");
				op.setUser(user);
				op.setContent("修改了"+name+"参数为"+params);
				op.setoType("修改");
				operationLogServic.addOperationLog(op);
				System.out.println("写入成功");
			}
			if(method.getName().contains("add")){
				User user = (User) session.getAttribute("user");
				op.setUser(user);
				op.setContent("添加了"+name+"参数为"+params);
				op.setoType("添加");
				operationLogServic.addOperationLog(op);
				System.out.println(op);
				System.out.println("写入成功");
			}
			/**
			 * 删除
			 */
			if(method.getName().contains("del")){
				User user = (User) session.getAttribute("user");
				op.setUser(user);
				op.setoType("删除");
				/**
				 * 判断表
				 */
				switch (name) {
				case "课程":
					//判断表查询删除字段
					if (params.contains("[")) {
						String[] spli =params.split(",");
						for (int i = 0; i < spli.length; i++) {
							spli[i] =spli[i].replace("[", "");
							spli[i] =spli[i].replace("]", "");
							System.out.println(spli[i]);
							String str = spli[i];
							String strs =str.trim();
							Integer num = Integer.parseInt(strs);
							Course cour =courseMapper.getCourseById(num);
							op.setContent("删除了"+cour.getCourseName()+name);	
							operationLogServic.addOperationLog(op);
							System.out.println(op);
							System.out.println("写入成功");
						}
					}else {
						Course cour  = courseMapper.getCourseById(Integer.parseInt(params));
						op.setContent("删除了"+cour.getCourseName()+name);	
						operationLogServic.addOperationLog(op);
						System.out.println(op);
						System.out.println("写入成功");
					}	
					break;
				case "行业":
					//判断表查询删除字段
					if (params.contains("[")) {
						String[] spli =params.split(",");
						for (int i = 0; i < spli.length; i++) {
							spli[i] =spli[i].replace("[", "");
							spli[i] =spli[i].replace("]", "");
							System.out.println(spli[i]);
							String str = spli[i];
							String strs =str.trim();
							System.out.println("去空格");
							Integer num = Integer.parseInt(strs);
							System.out.println("转整形"+num);
							Field field =fieldService.getFieldById(num);
							System.out.println(field);
							op.setContent("删除了"+field.getFieldName()+name);	
							operationLogServic.addOperationLog(op);
							System.out.println(op);
							System.out.println("写入成功");
						}
					}else {
						Field field =fieldService.getFieldById(Integer.parseInt(params));
						op.setContent("删除了"+field.getFieldName()+name);	
						operationLogServic.addOperationLog(op);
						System.out.println(op);
						System.out.println("写入成功");
					}	
					break;
				case "题库":
					//判断表查询删除字段
					if (params.contains("[")) {
						String[] spli =params.split(",");
						for (int i = 0; i < spli.length; i++) {
							spli[i] =spli[i].replace("[", "");
							spli[i] =spli[i].replace("]", "");
							System.out.println(spli[i]);
							String str = spli[i];
							String strs =str.trim();
							Integer num = Integer.parseInt(strs);
							ItemBank itemBank = itemBankService.getItemBankById(num);
							op.setContent("删除了"+itemBank.getQuestion()+name);	
							operationLogServic.addOperationLog(op);
							System.out.println(op);
							System.out.println("写入成功");
						}
					}else {
						ItemBank itemBank = itemBankService.getItemBankById(Integer.parseInt(params));
						op.setContent("删除了"+itemBank.getQuestion()+name);	
						operationLogServic.addOperationLog(op);
						System.out.println(op);
						System.out.println("写入成功");
					}	
					break;		
				case "知识点":
					//判断表查询删除字段
					if (params.contains("[")) {
						String[] spli =params.split(",");
						for (int i = 0; i < spli.length; i++) {
							spli[i] =spli[i].replace("[", "");
							spli[i] =spli[i].replace("]", "");
							System.out.println(spli[i]);
							String str = spli[i];
							String strs =str.trim();
							Integer num = Integer.parseInt(strs);
							Knowledge knowledge =knowledgeService.getKnowledgeById(num);
							op.setContent("删除了"+knowledge.getKnowledgeName()+name);	
							operationLogServic.addOperationLog(op);
							System.out.println(op);
							System.out.println("写入成功");
						}
					}else {
						Knowledge knowledge =knowledgeService.getKnowledgeById(Integer.parseInt(params));
						op.setContent("删除了"+knowledge.getKnowledgeName()+name);	
						operationLogServic.addOperationLog(op);
						System.out.println(op);
						System.out.println("写入成功");
					}	
					break;			
				case "角色":
					//判断表查询删除字段
					if (params.contains("[")) {
						String[] spli =params.split(",");
						for (int i = 0; i < spli.length; i++) {
							spli[i] =spli[i].replace("[", "");
							spli[i] =spli[i].replace("]", "");
							System.out.println(spli[i]);
							String str = spli[i];
							String strs =str.trim();
							Integer num = Integer.parseInt(strs);
							Role  role = roleService.getRoleById(num);
							op.setContent("删除了"+role.getRoleName()+name);	
							operationLogServic.addOperationLog(op);
							System.out.println(op);
							System.out.println("写入成功");
						}
					}else {
						Role  role = roleService.getRoleById(Integer.parseInt(params));
						op.setContent("删除了"+role.getRoleName()+name);	
						operationLogServic.addOperationLog(op);
						System.out.println(op);
						System.out.println("写入成功");
					}	
					break;			
				case "试卷":
					//判断表查询删除字段
					if (params.contains("[")) {
						String[] spli =params.split(",");
						for (int i = 0; i < spli.length; i++) {
							spli[i] =spli[i].replace("[", "");
							spli[i] =spli[i].replace("]", "");
							System.out.println(spli[i]);
							String str = spli[i];
							String strs =str.trim();
							Integer num = Integer.parseInt(strs);
							TestPaper testpaper =testpaperService.getTestPaperById(num);
							op.setContent("在题库中删除了了"+testpaper.getTpName()+"试题");	
							operationLogServic.addOperationLog(op);
							System.out.println(op);
							System.out.println("写入成功");
						}
					}else {
						TestPaper testpaper =testpaperService.getTestPaperById(Integer.parseInt(params));
						op.setContent("删除了"+testpaper.getTpName()+name);	
						operationLogServic.addOperationLog(op);
						System.out.println(op);
						System.out.println("写入成功");
					}	
					break;
				case "用户":
					//判断表查询删除字段
					if (params.contains("[")) {
						String[] spli =params.split(",");
						for (int i = 0; i < spli.length; i++) {
							spli[i] =spli[i].replace("[", "");
							spli[i] =spli[i].replace("]", "");
							System.out.println(spli[i]);
							String str = spli[i];
							String strs =str.trim();
							Integer num = Integer.parseInt(strs);
							User use = userService.getUser(num);
							op.setContent("删除了"+use.getUserName()+name);	
							operationLogServic.addOperationLog(op);
							System.out.println(op);
							System.out.println("写入成功");
						}
					}else {
						User use = userService.getUser(Integer.parseInt(params));
						op.setContent("删除了"+use.getUserName()+name);	
						operationLogServic.addOperationLog(op);
						System.out.println(op);
						System.out.println("写入成功");
					}	
					break;		
				default:
					op.setContent(name+"删除了"+params);
					op.setoType("删除");
					operationLogServic.addOperationLog(op);
					System.out.println("写入成功");
					break;
				}				
				break;
			} 
		}
	}

}
