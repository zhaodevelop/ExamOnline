package com.ruanyuan.controller;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ruanyuan.common.utils.Page;
import com.ruanyuan.pojo.Course;
import com.ruanyuan.pojo.Field;
import com.ruanyuan.pojo.ItemBank;
import com.ruanyuan.pojo.TestPaper;
import com.ruanyuan.service.CourseService;
import com.ruanyuan.service.FieldService;
import com.ruanyuan.service.ItemBankService;
import com.ruanyuan.service.TestPaperService;

import net.sf.json.JSONObject;
/**
 * 行业控制层
 * @author
 *
 */
@Controller
@RequestMapping("/field")
public class FieldController {
	//注入FieldService
	@Autowired
	private FieldService fieldService;
	//注入courseService
	@Autowired
	private CourseService courseService;
	//注入ItemBankService
	@Autowired
	private ItemBankService itemBankService;
	//注入TestPaperService
	@Autowired
	private TestPaperService testPaperService;
	//跳转到行业页面
	@RequestMapping("/fieldManage.action")
	public String fieldList(@RequestParam(defaultValue="1")Integer page,
			@RequestParam(defaultValue="6")Integer rows,String fieldName, String introduce, Model model,HttpServletRequest request) {
		// 条件查询所有行业
		Page<Field> fields = fieldService.findFieldList(page, rows, fieldName, introduce);
		//将所有行业信息存到model对象中
		model.addAttribute("page", fields);
		//查询的条件行业名称，行业介绍存入作用域
		request.setAttribute("fieldName",fieldName);
		request.setAttribute("introduce",introduce);
		System.out.println("fields="+fields);
		//到主页面
		return "admin/fieldManage";	
	}
	/**
	 * 查询所有行业信息
	 */
	@RequestMapping(value="/fields.action",produces="text/html;charset=utf-8")
	@ResponseBody
	public String getFields() {
		//查询所有行业信息
		List<Field> fields = fieldService.getFields();
		// 创建JSONObject对象
        JSONObject obj = new JSONObject();
        obj.put("list", fields);
        //数据转为JSON格式的
        String json = obj.toString();
        // 返回JSON数据到前台
        return json;
	}
	/**
	 * 创建行业信息
	 */
	@RequestMapping("/create.action")
	@ResponseBody
	public String fieldadd(String introduce,String fieldName) {
		//根据行业名称查询行业信息
		Field field1 = fieldService.getFieldByFieldName(fieldName);
		//判断是否重名
		if(field1!=null) {
			System.out.println("重名了，请重新添加");
			return "REPEATE";
		}else if(fieldName==null || fieldName.equals("")){
			return "NULL";
		}else {
			//创建Field对象
			Field field = new Field();
			//赋值
			field.setIntroduce(introduce);
			field.setFieldName(fieldName);
			//执行添加行业操作
			int rows = fieldService.addField(field);
			if(rows > 0){
		        return "OK";
		    }else{
		        return "FAIL";
		    }
		}
	}
	/**
	 * 根据行业id获取行业信息
	 */
	@RequestMapping("/getFieldById.action")
	@ResponseBody
	public Field getFieldById(Integer id) {
		return fieldService.getFieldById(id);
	}
	/**
	 * 根据行业id修改行业信息
	 */
	@RequestMapping("/updateField")
	@ResponseBody
	public String updateField(Integer fieldId,String fieldName,String introduce) {
		//判断行业名称是否为空
		if(fieldName==null || fieldName.equals("")){
			return "NULL";
		}else {
			//创建行业对象
			Field field = new Field();
			//赋值
			field.setFieldId(fieldId);
			field.setFieldName(fieldName);
			field.setIntroduce(introduce);
			//修改行业信息，返回受影响的行数
			int rows =  fieldService.updateFieldById(field);
			if(rows > 0){
		        return "OK";
		    }else{
		        return "FAIL";
		    }
		}
		
	}
	/**
	 * 根据行业id删除行业信息
	 */
	@RequestMapping("/deleteField")
	@ResponseBody
	public String deleteField(Integer id) {
		//根据行业id查询课程信息
		List<Course> courses = courseService.getCourseByFieldId(id);
		//根据行业id批量查询题库信息
		List<ItemBank> itemBanks = itemBankService.getItemBankByFieldId(id);
		//根据行业id批量查询试卷信息
		List<TestPaper> testPapers = testPaperService.getTestPaperByFieldId(id);
		if(courses.size()>0 || itemBanks.size()>0 || testPapers.size()>0) {
			return "FAIL";
		}else{
			fieldService.deleteFieldById(id);
	        return "OK";
	    }
	}
	//批量删除功能
	@RequestMapping("/deleteFieldByIds")
	@ResponseBody
	public String deleteFieldByIds(int[] ids){
		System.out.println("fieldController层的deleteFieldByIds:"+ids);
		//将传过来的ids数组放到list集合中
		List<Integer> list = new ArrayList<Integer>();
		for (int i = 0; i < ids.length; i++) {
			list.add(ids[i]);
		}
		//根据行业id批量查询课程信息
		List<Course> courses = courseService.getCourseByFieldIds(ids);
		//根据行业id批量查询题库信息
		List<ItemBank> itemBanks = itemBankService.getItemBankByFieldIds(ids);
		//根据行业id批量查询试卷信息
		List<TestPaper> testPapers = testPaperService.getTestPaperByFieldIds(ids);
		if(courses.size()>0 || itemBanks.size()>0 || testPapers.size()>0) {
			return "FAIL";
		}else{
			fieldService.deleteFieldByIds(list);
	        return "OK";
	    }
		
	}
}
