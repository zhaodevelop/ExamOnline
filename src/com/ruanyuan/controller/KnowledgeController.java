package com.ruanyuan.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONArray;
import org.json.JSONObject;
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
import com.ruanyuan.pojo.KnowledgeVo;
import com.ruanyuan.pojo.TestPaper;
import com.ruanyuan.service.CourseService;
import com.ruanyuan.service.FieldService;
import com.ruanyuan.service.ItemBankService;
import com.ruanyuan.service.KnowledgeService;
import com.ruanyuan.service.TestPaperService;
/**
 * 知识点控制层
 * @author 
 *
 */
@Controller
@RequestMapping("/knowledge")
public class KnowledgeController {
	// 注入CourseService
	@Autowired
	private CourseService courseService;
	// 注入KnowledgeService
	@Autowired
	private KnowledgeService knowledgeService;
	// 注入ItemBankService
	@Autowired
	private ItemBankService itemBankService;
	// 注入TestPaperService
	@Autowired
	private TestPaperService testPaperService;
	// 注入FieldService
	@Autowired
	private FieldService fieldService;

	// 跳转到知识点页面
	@RequestMapping("/knowledgeManage.action")
	public String knowledgeList(@RequestParam(defaultValue = "1") Integer page,
			@RequestParam(defaultValue = "6") Integer rows, String knowledgeName, String courseId,String fieldId,Model model,
			HttpServletRequest request) {
		// 条件查询所有知识点
		Page<KnowledgeVo> knowledges = knowledgeService.findKnowledgeList(page, rows, knowledgeName, courseId,fieldId);
		//查询所有课程信息
		List<Course> courses = courseService.getCourses();
		//查询所有行业信息
		List<Field> fields = fieldService.getFields();
		//将查询的所有知识点和课程信息和行业信息放到model中
		model.addAttribute("courses", courses);
		model.addAttribute("page", knowledges);
		model.addAttribute("fields", fields);
		// 查询的条件知识点名称，及课程id存入作用域
		request.setAttribute("knowledgeName", knowledgeName);
		request.setAttribute("id", courseId);
		request.setAttribute("fieldId", fieldId);
		System.out.println("knowledges=" + knowledges);
		// 到主页面
		return "admin/knowledgeManage";
	}
	/**
	 * 创建知识点信息
	 */
	@RequestMapping("/create.action")
	@ResponseBody
	public String courseadd(String knowledgeName,Integer courseId) {
		//根据知识点名称查询知识点信息
		Knowledge knowledge1 = knowledgeService.getKnowledgeByKnowledgeName(knowledgeName);
		//判断查重
		if(knowledge1!=null) {
			return "REPEATE";
		}else if(knowledgeName==null || knowledgeName.equals("")){
			return "NULL";
		}else if(courseId==null){
			return "NULL";
		}else {
			//创建Knowledge对象
			Knowledge knowledge = new Knowledge();
			//根据课程id查询课程信息
			Course course = courseService.getCourseById(courseId);
			//赋值
			knowledge.setCourse(course);
			knowledge.setKnowledgeName(knowledgeName);
			//执行知识点添加操作
			int rows = knowledgeService.addKnowledge(knowledge);
			if(rows > 0){
		        return "OK";
		    }else{
		        return "FAIL";
		    }
		}
	}
	/**
	 * 根据知识点id获取知识点信息
	 */
	@RequestMapping("/getKnowledgeById.action")
	@ResponseBody
	public Knowledge getKnowledgeById(Integer id,Model model) {
		//根据知识点id查询知识点信息
		Knowledge knowledge = knowledgeService.getKnowledgeById(id);
		//获取课程course对象
		Course course = knowledge.getCourse();
		//获取courseid
		int courseId = course.getCourseId();
		//获取行业对象field
		Field field = course.getField();
		//获取行业id
		int fieldId = field.getFieldId();
		//将查询的行业id和课程id放到model中
		model.addAttribute("fid", fieldId);
		model.addAttribute("cid", courseId);
		//返回knowledge对象
		return knowledge;
	}
	/**
	 * 根据知识点id修改知识点信息
	 */
	@RequestMapping("/updateKnowledge")
	@ResponseBody
	public String updateKnowledge(String knowledgeName,Integer knowledgeId,Integer courseId) {
		//判断知识点名称和课程id是否为空
		if(knowledgeName==null || knowledgeName.equals("")){
			return "NULL";
		}else if(courseId==null){
			return "NULL";
		}else {
			//创建Knowledge对象
			Knowledge knowledge = new Knowledge();
			//根据课程id查询课程信息
			Course course = courseService.getCourseById(courseId);
			//赋值
			knowledge.setCourse(course);
			knowledge.setKnowledgeId(knowledgeId);
			knowledge.setKnowledgeName(knowledgeName);
			//执行知识点修改操作
			int rows = knowledgeService.updateKnowledge(knowledge);
			if(rows > 0){
		        return "OK";
		    }else{
		        return "FAIL";
		    }
		}
	}
	/**
	 * 根据知识点id删除知识点信息
	 */
	@RequestMapping("/deleteKnowledge")
	@ResponseBody
	public String deleteKnowledge(Integer id) {
		// 根据行业id批量查询题库信息
		List<ItemBank> itemBanks = itemBankService.getItemBankByKnowledgeId(id);
		// 根据行业id批量查询试卷信息
		List<TestPaper> testPapers = testPaperService.getTestPaperByKnowledgeId(id);
		if (itemBanks.size() > 0 || testPapers.size() > 0) {
			return "FAIL";
		} else {
			knowledgeService.deleteKnowledgeById(id);
			return "OK";
		}
	}
	// 批量删除功能
	@RequestMapping("/deleteKnowledgeByIds")
	@ResponseBody
	public String deleteKnowledgeByIds(int[] ids) {
		System.out.println("knowledgeController层的deleteKnowledgeByIds:" + ids);
		//将传过来的ids数组放到list集合中
		List<Integer> list = new ArrayList<Integer>();
		for (int i = 0; i < ids.length; i++) {
			list.add(ids[i]);
		}
		// 根据行业id批量查询题库信息
		List<ItemBank> itemBanks = itemBankService.getItemBankByKnowledgeIds(ids);
		// 根据行业id批量查询试卷信息
		List<TestPaper> testPapers = testPaperService.getTestPaperByKnowledgeIds(ids);
		if (itemBanks.size() > 0 || testPapers.size() > 0) {
			return "FAIL";
		} else {
			knowledgeService.deleteKnowledgeByIds(list);
			return "OK";
		}

	}
	/**
	 * 二级联动,根据行业id查询课程信息返回到ajax
	 */
	@RequestMapping(value="/getCourses.action",produces="text/html; charset=utf-8")
	@ResponseBody
	public String getCourses(Integer fieldId,HttpServletRequest request){
		// 根据行业id查询课程信息
		List<Course> courseList= this.courseService.getCourseByFieldId(fieldId);
		// 创建JSON对象
		JSONArray json = new JSONArray();
		if(courseList.size()>0){
			for (Course course : courseList) {
				// 创建JSONObject对象
				JSONObject jo = new JSONObject();
				jo.put("courseId", course.getCourseId());
				jo.put("courseName", course.getCourseName());
				// 添加到JSONArray中
				json.put(jo);
			}
		}
		// 返回JSON数据到前台
		return json.toString();
	}
}
