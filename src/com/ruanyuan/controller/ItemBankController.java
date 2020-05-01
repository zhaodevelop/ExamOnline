package com.ruanyuan.controller;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.ruanyuan.common.utils.Page;
import com.ruanyuan.pojo.Course;
import com.ruanyuan.pojo.Field;
import com.ruanyuan.pojo.ItemBank;
import com.ruanyuan.pojo.Knowledge;

import com.ruanyuan.pojo.User;
import com.ruanyuan.service.CourseService;
import com.ruanyuan.service.FieldService;
import com.ruanyuan.service.ItemBankService;
import com.ruanyuan.service.KnowledgeService;


import net.sf.json.JSONObject;



/**
 * 题库表控制层
 * @author 
 *
 */
@Controller
@RequestMapping("/itemBank")
public class ItemBankController {
	//注解注入ItemBankService
	@Autowired
	private ItemBankService itemBankService;
	//注解注入FieldService
	@Autowired
	private FieldService fieldService;
	//注解注入CustomerService
	@Autowired
	private CourseService courseService;
	//注解注入KnowledgeService
	@Autowired
	private KnowledgeService knowledgeService;
	
	
	
	/**
	 * 分页多条件查询题库信息
	 * @param page 页数
	 * @param rows 每页条数
	 * @param fieldId 行业ID
	 * @param userName 用户名称
	 * @param courseId 课程ID
	 * @param knowledgeId 知识点ID
	 * @param question 试题
	 * @param model  Model对象
	 * @param request request作用域
	 * @return 查询结果
	 */
	@RequestMapping(value = "/list.action")
	public String ItemBankList(@RequestParam(defaultValue="1")Integer page,
			@RequestParam(defaultValue="6")Integer rows,Integer fieldId, 
			 String userName,Integer courseId,Integer knowledgeId,String question,
			 Model model,HttpServletRequest request) {
		Page<ItemBank>  itembanks = (Page<ItemBank>) itemBankService.selectMoreItemBanks(page, rows, fieldId, courseId, knowledgeId, question,userName);
		model.addAttribute("page", itembanks);
		//查询所有行业，存入作用域
		List<Field> fields = fieldService.getFields();
		//System.out.println("fields="+fields);
		//存入所有行业应用于查询
		request.setAttribute("fields",fields);
		//存入所有行业应用于添加
		request.setAttribute("fields2",fields);
		//存入查询的行业id
		request.setAttribute("fieldId", fieldId);
		//存入查询的命题人
		request.setAttribute("userName", userName);
		//存入查询的试题
		request.setAttribute("question", question);
		//存入查询的课程
		request.setAttribute("ajaxCourse", courseId);
		//存入查询的知识点
		request.setAttribute("ajaxKnowledge",knowledgeId);
		//查询的条件用户名称存入作用域
		System.out.println(itembanks);
		//到主页面
		return "admin/itemBankManage";	
	}
	/**
	 * 添加题库信息
	 * @param fieldId1 行业ID
	 * @param courseId1 课程ID
	 * @param knowledgeId1 知识点ID
	 * @param question1 试题
	 * @param optionA1 选项A
	 * @param optionB1 选项B
	 * @param optionC1 选项C
	 * @param optionD1 选项D
	 * @param answer1 答案
	 * @param session session作用域
	 * @return  执行结果
	 */
	@RequestMapping("/addItemBank.action")
	@ResponseBody
	public String addItemBank(Integer fieldId1,Integer courseId1,Integer knowledgeId1,String question1,String optionA1,
			String optionB1,String optionC1,String optionD1,String answer1,HttpSession session) {
		// 从sessin作用域中获取用户
		User user = (User) session.getAttribute("user");
		//执行添加操作
		String str = itemBankService.addItemBank(fieldId1, courseId1, knowledgeId1, question1, optionA1, optionB1, optionC1, optionD1, answer1,user);
	    return str;
	}
	/**
	 * Excel批量上传添加试题
	 * @param file 文件
	 * @param request request作用域
	 * @param session  session作用域
	 * @return  执行结果
	 */
	@RequestMapping(value="/importExcelItemBank.action",produces="text/html;charset=utf-8")
	@ResponseBody
	public String importExcel(MultipartFile file, HttpServletRequest request,HttpSession session)  {
		// 从sessin作用域中获取用户
		User user = (User) session.getAttribute("user");
		//执行上传操作
		Map<String,String> map = itemBankService.importExcel(file, request,user);	
	    JSONObject obj = new JSONObject();
	    obj.put("list", map);
	    String json = obj.toString();//数据转为JSON格式的
		// 返回结果
		return json;
	}

	/**
	 * 修改题库试题
	 * @param itemBankId 题库ID
	 * @param question 试题
	 * @param optionA 选项A
	 * @param optionB 选项B
	 * @param optionC 选项C
	 * @param optionD 选项D
	 * @param fieldId 行业ID
	 * @param courseId 课程ID
	 * @param knowledgeId 知识点ID
	 * @param answer 答案
	 * @return  执行结果
	 */
	@RequestMapping("/updateItemBank.action")
	@ResponseBody
	public String updateItemBank(Integer itemBankId,String question,String optionA,String optionB,String optionC,String optionD,
			Integer fieldId,Integer courseId,Integer knowledgeId,String answer
			) {
		//调用修改方法
		String str = itemBankService.updateItemBank(itemBankId, question, optionA, optionB, optionC, optionD, fieldId, courseId, knowledgeId, answer);
	    return str;
	}
	/**
	 * 查看详情页数据
	 * @param ibId 题库id
	 * @return 题库对象
	 */
	@RequestMapping("/itemBankDetail.action")
	@ResponseBody
	public ItemBank getItemBankById(Integer ibId) {
		//根据id查询试题信息
		ItemBank itemBank = itemBankService.getItemBankById(ibId);
		System.out.println("itemBank"+itemBank);
		//将数据返回到前端
	    return itemBank;
	}
	/**
	 * 修改题库前的查询
	 * @param request request作用域
	 * @param itemBankId 题库ID
	 * @return 题库对象
	 */
	@RequestMapping("/itemBank.action")
	@ResponseBody
	public ItemBank getItemBan(HttpServletRequest request,Integer itemBankId) {
		//根据id查询试题信息
		ItemBank itemBank = itemBankService.getItemBankById(itemBankId);
		//将对象添加到作用域
		//request.setAttribute("selectItemBank", itemBank);
		//将数据返回到前端
	    return itemBank;
	}
	/**
	 * 通过行业ID查询课程信息
	 * @param fieldId 行业id
	 * @return 返回json数据
	 */
	@RequestMapping(value="/getCourseByField.action",produces="text/html;charset=utf-8")
	@ResponseBody
	public String getCourseByFieldId(@RequestParam("fieldId") Integer fieldId) {
		//通过行业ID查询课程信息
		List<Course> courselist = courseService.getCourseByFieldId(fieldId);
        JSONObject obj = new JSONObject();
        obj.put("list", courselist);
        String json = obj.toString();//数据转为JSON格式的
        // System.out.println(a);
        return json;
	}
	
	/**
	 * 通过课程ID查询知识点信息
	 * @param courseId 课程ID
	 * @return 返回json数据
	 */
	@RequestMapping(value="/getKnowledgeByCourse.action",produces="text/html;charset=utf-8")
	@ResponseBody
	public String getKnowledgeByCourse(@RequestParam("courseId") Integer courseId) {
		//通过行业ID查询课程信息
		List<Knowledge> knowledgelist = knowledgeService.getKnowledgeByCourseId(courseId);
        JSONObject obj = new JSONObject();
        obj.put("list", knowledgelist);
        String json = obj.toString();//数据转为JSON格式的
        // System.out.println(a);
        return json;
	}
	/**
	 * 删除操作中转
	 * 判断是否关联到其他表
	 * 弹出提示删除内容关联到其他表是否删除？
	 * @param ids 题库id集合
	 * @return 查询结果
	 */
	@RequestMapping("/delItemBank.action")
	@ResponseBody
	public String deltemBank(Integer[] ids) {
		// 创建List集合储存试题id
		List<Integer> ids1 = new ArrayList<Integer>();
		// 遍历数组，储存在集合内
		for (Integer id : ids) {
			ids1.add(id);
		}
		//调用查询方法
		String str = itemBankService.selectItemPaperByItemBankId(ids1);
		//返回结果
	    return str;
	}
	/**
	 * 直接批量删除题库试题
	 * @param ids 题库id集合
	 * @return 删除结果
	 */
	@RequestMapping("/deleteItemBank.action")
	@ResponseBody
	public String deleteItemBank(Integer[] ids) {
		// 创建List集合储存试题id
		List<Integer> ids1 = new ArrayList<Integer>();
		// 遍历数组，储存在集合内
		for (Integer id : ids) {
			ids1.add(id);
		}
		//调用删除方法
		String str = itemBankService.deleteItemBankByIds(ids1);
		//返回结果
	    return str;
	}
	/**
	 *  先删除关系表在直接批量删除题库
	 * @param ids 题库id集合
	 * @return 删除结果
	 */
	@RequestMapping("/deleteItemBank2.action")
	@ResponseBody
	public String deleteItemBank2(Integer[] ids) {
		// 创建List集合储存试题id
		List<Integer> ids1 = new ArrayList<Integer>();
		// 遍历数组，储存在集合内
		for (Integer id : ids) {
			ids1.add(id);
		}
		//调用删除方法
		String str = itemBankService.deleteItemBankByIds1(ids1);
		//返回结果
	    return str;
	}
	/**
	 * 试题名称查重
	 * @param question 试题
	 * @return 查询结果
	 */
	@RequestMapping("/getItemBankByQuestion.action")
	@ResponseBody
	public String getItemBankByQuestion(String question) {
		//调用删除方法
		String str = itemBankService.getItemBankByQuestion(question);
		//返回结果
	    return str;
	}
	/**
	 *上传模板下载
	 * @param response
	 * @throws Exception
	 */
	  @RequestMapping("/download.action")
	    public void download(HttpServletResponse response) throws Exception {
		  	//试题样板
	        ItemBank itemBank= itemBankService.getItemBankTemplate();
	        //获取全部知识点
	        List<Knowledge> knowledges = knowledgeService.getKnowledges();
	        //创建一个工作簿
	        @SuppressWarnings("resource")
			Workbook workbook=new XSSFWorkbook(); 
			//Workbook workbook= new XSSFWorkbook();
	        //sheet1
	        Sheet sheet = workbook.createSheet("上传模板");
	        //sheet2
	        Sheet sheet2 = workbook.createSheet("数据详情");
	        //定义sheet1标题
	        String[] titles ={"试题","选项A","选项B","选项C","选项D","答案","行业编号","课程编号","知识点编号"};
	        //定义sheet2标题
	        String[] titles2 ={"行业编号","行业名称","课程编号","课程名称","知识点编号","知识点名称"};
	        //行
	        Row row = sheet.createRow(0);
	        //行
	        Row row2 = sheet2.createRow(0);
	        //写sheet1入标题行
	        for (int i =0;i<titles.length;i++){
	            Cell cell=row.createCell(i);
	            cell.setCellValue(titles[i]);
	        }
	        //写sheet2入标题行
	        for (int i =0;i<titles2.length;i++){
	            Cell cell=row2.createCell(i);
	            cell.setCellValue(titles2[i]);
	        }
	        /**
	         * sheet1插入数据
	         */
	            //第一行开始
	            row=sheet.createRow(1);
	            //得到第一个字段的值
	            row.createCell(0).setCellValue(itemBank.getQuestion());		            
	            //写第二个字符
	            row.createCell(1).setCellValue(itemBank.getOptionA());
	            row.createCell(2).setCellValue(itemBank.getOptionB());
	            row.createCell(3).setCellValue(itemBank.getOptionC());
	            row.createCell(4).setCellValue(itemBank.getOptionD());
	            row.createCell(5).setCellValue(itemBank.getAnswer());
	            row.createCell(6).setCellValue(itemBank.getField().getFieldId());
	            row.createCell(7).setCellValue(itemBank.getCourse().getCourseId());
	            row.createCell(8).setCellValue(itemBank.getKnowledge().getKnowledgeId());
	            
	        //从第二行开始
	        row2 = sheet2.createRow(1);
	        //遍历知识点
	        for (Knowledge k : knowledges) {
	        	row2.createCell(0).setCellValue(k.getCourse().getField().getFieldId());
		        row2.createCell(1).setCellValue(k.getCourse().getField().getFieldName());
		        row2.createCell(2).setCellValue(k.getCourse().getCourseId());
		        row2.createCell(3).setCellValue(k.getCourse().getCourseName());
		        row2.createCell(4).setCellValue(k.getKnowledgeId());
		        row2.createCell(5).setCellValue(k.getKnowledgeName());
		        Integer count = row2.getRowNum();
		        row2 = sheet2.createRow(count+=1);
			}
	        //创建文件名称
	        String fileName= URLEncoder.encode("批量上传模板.xlsx","UTF-8");
	        response.setContentType("application/octet-stream");
	        response.setHeader("Content-disposition","attachment;filename="+fileName);
	        response.setHeader("filename",fileName);      
	        //写入文件
	        workbook.write(response.getOutputStream());
	        
	      
	    }
}
