package com.ruanyuan.controller;

import java.text.ParseException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ruanyuan.common.utils.DateUtils;
import com.ruanyuan.pojo.TestPaper;
import com.ruanyuan.pojo.User;
import com.ruanyuan.service.StatisticsService;
import com.ruanyuan.service.TestPaperService;

/**
 * 	在线考试控制层
 * @author 
 *
 */
@Controller
@RequestMapping("/exams")
public class ExamsController {
	
	//注入testPaperService  statisticsService
	@Resource
	private TestPaperService testPaperService;
	@Resource
	private StatisticsService statisticsService;
	


	/**
	 * 开始考试
	 * @param model  携带数据（试卷信息）
	 * @return  返回页面
	 * @throws ParseException    时间转化格式异常
	 */
	@RequestMapping("/startExams/{courseId}.action")
	public String startExams(@PathVariable("courseId") Integer courseId , Model model) throws ParseException {
		//创建试卷对象   调用方法 随机生成试卷
		TestPaper tp = new TestPaper();
		try {
			//开始考试异常 则跳回首页     情况可能是停留在开始考试按钮页面，超出考试时间范围，会出现异常
			tp = this.testPaperService.randowGetTestPaper(courseId);
		} catch (Exception e) {
			// TODO: handle exception
			return "redirect:/ui/ui";
		}
		//计算距离考试结束还剩多长时间   截串。0
		String	examsTime = DateUtils.examsTime(tp.getEndTime().substring(0,tp.getEndTime().length()-2));
		//添加到作用域
		model.addAttribute("testPaper", tp);
		model.addAttribute("examsTime", examsTime);
		return "ui/exams";
	}
	/**
	 * 交卷评分  并添加到 做题统计表
	 * @param ibIds   考试题Id 数组
	 * @param tpId    试卷ID
	 * @param oneBranch   单题分数
	 * @param request    注入request域
	 * @return  返回页面
	 */
	@RequestMapping(value = "/autoScore.action")
	public String autoScore(Integer[] ibIds ,Integer tpId,Float oneBranch, HttpServletRequest request,HttpSession session,Model model) {

		//添加到做题统计表
		int count = statisticsService.addStatistics(ibIds ,tpId,oneBranch,request,session);
		//如果添加成功  再得出成绩  
		if(count > 0) {
			//获取登录的用户信息
			User user = (User) session.getAttribute("user");
			//根据用户信息  和试卷ID查询 得分成绩
			Float fraction = statisticsService.getFractionByUserIdAndTpId(user.getUserId(), tpId);
			model.addAttribute("fraction", fraction);
			System.out.println("添加成功");
			String str = "yes";
			model.addAttribute("sc", str);
		}else {
			System.out.println("添加失败");
		}
		return "redirect:/ui/ui";
		
	}
	
	/**
	 * ajax请求  判断该课程 现在是否有考试    该学生是否已经答题
	 * @param courseId   课程ID
	 * @param userId  学生ID
	 * @return  返回令牌字符串
	 */
	@RequestMapping("/isHaveExams.action")
	public @ResponseBody
		String isHaveExams(Integer userId , Integer courseId) {
		//调用方法 判断是否有考试  学生是否考过试
		String token = testPaperService.isHaveExams(userId , courseId);
		return token;
	}
}
