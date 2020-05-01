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
import com.ruanyuan.pojo.Knowledge;
import com.ruanyuan.pojo.TestPaper;
import com.ruanyuan.service.CourseService;
import com.ruanyuan.service.FieldService;
import com.ruanyuan.service.ItemBankService;
import com.ruanyuan.service.KnowledgeService;
import com.ruanyuan.service.TestPaperService;
/**
 * 课程控制层
 * @author
 *
 */
@Controller
@RequestMapping("/course")
public class CourseController {

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
	// 注入TestPaperService
	@Autowired
	private TestPaperService testPaperService;

	// 跳转到课程页面
	@RequestMapping("/courseManage.action")
	public String courseList(@RequestParam(defaultValue = "1") Integer page,
			@RequestParam(defaultValue = "6") Integer rows, String courseName ,String fieldId, Model model,
			HttpServletRequest request) {
		// 条件查询所有课程
		Page<Course> courses = courseService.findCourseList(page, rows, courseName,fieldId);
		// 查询所有行业
		List<Field> fields = fieldService.getFields();
		// 将查询的课程及行业放进model
		model.addAttribute("fields", fields);
		model.addAttribute("page", courses);
		// 查询的条件课程名称，行业id存入作用域
		request.setAttribute("courseName", courseName);
		request.setAttribute("id", fieldId);
		//输出查到的所有课程
		System.out.println("courses=" + courses);
		// 到主页面
		return "admin/courseManage";
	}
	/**
	 * 创建行业信息
	 */
	@RequestMapping("/create.action")
	@ResponseBody
	public String courseadd(String courseName,Integer fieldId) {
		//根据课程名称查询
		Course course1 = courseService.getCourseByCourseName(courseName);
		//判断是否重名
		if(course1!=null) {
			System.out.println("重名了，请重新添加");
			return "REPEATE";
		}else if(courseName==null || courseName.equals("")){
			return "NULL";
		}else if(fieldId==null){
			return "NULL";
		}else {
			//创建Course对象
			Course course = new Course();
			//根据行业id查询行业信息
			Field field = fieldService.getFieldById(fieldId);
			//给course对象赋值
			course.setCourseName(courseName);
			course.setField(field);
			//添加课程信息，返回受影响的行数
			int rows = courseService.addCourse(course);
			if(rows > 0){
		        return "OK";
		    }else{
		        return "FAIL";
		    }
		}
	}
	/**
	 * 根据课程id获取课程信息
	 */
	@RequestMapping("/getCourseById.action")
	@ResponseBody
	public Course getCourseById(Integer id) {
		return courseService.getCourseById(id);	
	}
	/**
	 * 根据课程id修改课程信息
	 */
	@RequestMapping("/updateCourse")
	@ResponseBody
	public String updateCourse(String courseName,Integer fieldId,Integer courseId) {
		//判断课程名称和行业id是否为空
		if(courseName==null || courseName.equals("")){
			return "NULL";
		}else if(fieldId==null){
			return "NULL";
		}else {
			//创建Course对象
			Course course = new Course();
			//根据行业id查询行业信息
			Field field = fieldService.getFieldById(fieldId);
			//赋值
			course.setCourseName(courseName);
			course.setCourseId(courseId);
			course.setField(field);
			//修改课程信息
			int rows = courseService.updateCourse(course);
			if(rows > 0){
		        return "OK";
		    }else{
		        return "FAIL";
		    }
		}
		
	}
	// 批量删除功能
	@RequestMapping("/deleteCourseByIds")
	@ResponseBody
	public String deleteCourseByIds(int[] ids) {
		System.out.println("courseController层的deleteCourseByIds:" + ids);
		//将传过来的ids数组放到list集合中
		List<Integer> list = new ArrayList<Integer>();
		for (int i = 0; i < ids.length; i++) {
			list.add(ids[i]);
		}
		// 根据课程id批量查询知识点信息
		List<Knowledge> knowledges = knowledgeService.getKnowledgeByCourseIds(ids);
		// 根据行业id批量查询题库信息
		List<ItemBank> itemBanks = itemBankService.getItemBankByCourseIds(ids);
		// 根据行业id批量查询试卷信息
		List<TestPaper> testPapers = testPaperService.getTestPaperByCourseIds(ids);
		if (knowledges.size() > 0 || itemBanks.size() > 0 || testPapers.size() > 0) {
			return "FAIL";
		} else {
			courseService.deleteCourseByIds(list);
			return "OK";
		}
	}
	/**
	 * 根据课程id删除课程信息
	 */
	@RequestMapping("/deleteCourse")
	@ResponseBody
	public String deleteCourse(Integer id) {
		// 根据课程id批量查询知识点信息
		List<Knowledge> knowledges = knowledgeService.getKnowledgeByCourseId(id);
		// 根据行业id批量查询题库信息
		List<ItemBank> itemBanks = itemBankService.getItemBankByCourseId(id);
		// 根据行业id批量查询试卷信息
		List<TestPaper> testPapers = testPaperService.getTestPaperByCourseId(id);
		if (knowledges.size() > 0 || itemBanks.size() > 0 || testPapers.size() > 0) {
			return "FAIL";
		} else {
			courseService.deleteCourseById(id);
			return "OK";
		}
	}
}
