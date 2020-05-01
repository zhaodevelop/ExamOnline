package com.ruanyuan.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.servlet.ModelAndView;

import com.ruanyuan.common.utils.DateUtils;
import com.ruanyuan.pojo.Course;
import com.ruanyuan.pojo.Field;
import com.ruanyuan.pojo.TestPaper;
import com.ruanyuan.service.CourseService;
import com.ruanyuan.service.FieldService;
import com.ruanyuan.service.TestPaperService;
/**
 * 前台页面控制层
 * @author
 *
 */
@Controller
@RequestMapping("/ui")
public class UIController {
	
	//注解注入FieldService	courseService	testPaperService
	@Autowired
	private FieldService fieldService;
	@Autowired
	private CourseService courseService;
	@Autowired
	private TestPaperService testPaperService;
	//ui
	@RequestMapping("/ui")
	public String test4(Float fraction ,String sc , HttpServletRequest request , Model model,String tip) {
		// 查询全部行业信息
		List<Field> fieldList = fieldService.getAllField();
		model.addAttribute("fieldList", fieldList);
		//封装考试成绩
		model.addAttribute("fraction", fraction);
		model.addAttribute("sc", sc);
		model.addAttribute("tip", tip);
		//返回跳转页面
		return "ui/index";
	}
	
	/**
	 * 跳转到前台个人中心
	 * @return
	 */
	@RequestMapping("/info")
	public String toInformation(){
		//返回跳转页面
		return "ui/centerinfo";
	}
	/**
	 * 课程展示页
	 * @param fieldId  行业ID
	 * @param model
	 * @return
	 */
	@RequestMapping("/classList/{fieldId}.action")
	public String classList(@PathVariable("fieldId") Integer fieldId,Model model){
		//获取课程信息
		Field field = fieldService.getFieldById(fieldId);
		//向field添加信息
		model.addAttribute("field", field);
		//返回跳转页面
		return "ui/classList";
	}
	/**
	 * 知识点展示信息
	 * @param courseId
	 * @param model
	 * @return
	 * @throws ParseException 
	 */
	@RequestMapping("/knowledgeList/{courseId}.action")
	public String knowledgeList(@PathVariable("courseId") Integer courseId , Model model) throws ParseException{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		//获取课程信息
		Course course = courseService.getCourseById(courseId);
		//获取本课程的试卷信息
		List<TestPaper> tpList = testPaperService.getTestPaperByCouseId(courseId);
		//声明遍历储存本课程最早开始考试的试卷
		String tCourse = "";
		//遍历试卷信息获取最早开始考试的时间
		if(tpList.size() > 0 && tpList.size() == 1) {
			tCourse = tpList.get(0).getStartTime().substring(0, tpList.get(0).getStartTime().length()-2);
		}else {
			for (int i = 0;i < tpList.size() ; i++) {
				if(i != tpList.size()-1) {
					if(new Date().before(sdf.parse(tpList.get(i).getStartTime()))) {
						if(sdf.parse(tpList.get(i).getStartTime()).before(sdf.parse(tpList.get(i+1).getStartTime())) || sdf.parse(tpList.get(i).getStartTime()) == sdf.parse(tpList.get(i+1).getStartTime())){
							tCourse = tpList.get(i).getStartTime().substring(0, tpList.get(i).getStartTime().length()-2);
						}
					}
				}else {
					if(new Date().before(sdf.parse(tpList.get(i).getStartTime()))) {
						if(sdf.parse(tpList.get(i).getStartTime()).before(sdf.parse(tCourse)) || sdf.parse(tpList.get(i).getStartTime()) == sdf.parse(tCourse)) {
							tCourse = tpList.get(i).getStartTime().substring(0, tpList.get(i).getStartTime().length()-2);
						}

					}
				}
			}
		}
		//声明字符串 接受还有多长时间开始考试
		String matTime ="";
		//t为空则该行业暂无考试
		if(tCourse != "") {
			matTime = DateUtils.examsStaTime(tCourse);
		}else {
			matTime = null;
		}
		model.addAttribute("matTime", matTime);
		model.addAttribute("course", course);
		//返回跳转页面
		return "ui/knowledgeList";
	}

	/**
	 * 退出登录操作
	 * @return
	 */
	@RequestMapping("/toExit")
	public ModelAndView toExit(HttpSession session,Model model) {
		System.out.println("删除session操作");
		//删除原session中user的值
		session.removeAttribute("user");
		//查询行业信息
		List<Field> fieldList = this.fieldService.getFields();
		//将行业信息的值存入modle中
		model.addAttribute("fieldList", fieldList);
		//返回跳转页面
		return new ModelAndView("redirect:/ui/test4");
	}
	
}
