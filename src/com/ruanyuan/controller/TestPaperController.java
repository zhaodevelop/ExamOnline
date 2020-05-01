package com.ruanyuan.controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
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
import com.ruanyuan.pojo.TestPaper;
import com.ruanyuan.pojo.User;
import com.ruanyuan.service.CourseService;
import com.ruanyuan.service.FieldService;
import com.ruanyuan.service.ItemPaperService;
import com.ruanyuan.service.StatisticsService;
import com.ruanyuan.service.TestPaperService;


/**
 * 试卷控制层
 *
 */
@Controller
@RequestMapping("/testPaper")
public class TestPaperController {


	// 注入TestPaperService
	@Autowired
	private TestPaperService testPaperService;


	// 注入FieldService(行业)
	@Autowired
	private FieldService fieldService;

	// 注入CourseService(课程)
	@Autowired
	private CourseService courseService;


	// 注入ItemPaperService(题库与试卷中间关系表)
	@Autowired
	private ItemPaperService itemPaperService;


	// 注入ItemBankService(做题统计)
	@Autowired
	private StatisticsService statisticsService;

	/**
	 * 跳入试卷管理页面
	 */
	@RequestMapping("/testPaperManager.action")
	public String testPaperManager(@RequestParam(defaultValue="1")Integer page,
			@RequestParam(defaultValue="6")Integer rows,String tpName,
			String userName,String startTime,String endTime,
			Integer fieldId,Integer courseId,Model model,
			HttpServletRequest request){
		// 分页多条件查询所有客户
		Page<TestPaper> testPapers = this.testPaperService.getTestPapers(tpName, fieldId, 
				courseId, startTime, endTime, userName, page, rows);
		// 行业信息
		List<Field> fieldList = this.fieldService.getFields();
		// 添加参数
		model.addAttribute("page", testPapers);
		model.addAttribute("tpName", tpName);
		model.addAttribute("userName", userName);
		model.addAttribute("startTime", startTime);
		model.addAttribute("endTime", endTime);
		model.addAttribute("fieldList", fieldList);
		model.addAttribute("fieldId", fieldId);
		model.addAttribute("courseId", courseId);

		return "admin/testPaper";
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

	/**
	 * 单个删除
	 */
	@SuppressWarnings("unused")
	@RequestMapping("/delTestPaper.action")
	@ResponseBody
	public String delTestPaper(Integer tpId){
		// 删除试卷信息，先删除试卷与中间表的数据
		String ipCount = this.itemPaperService.deleteItemPaperBytpId(tpId);
		// 删除做题统计表信息
		int statistics = this.statisticsService.deleteStatisticsByTpId(tpId);
		// 删除试卷表的信息
		String tpCount = this.testPaperService.deleteTestPaperById(tpId);
		return tpCount;
	}

	/**
	 * 批量删除
	 */
	@SuppressWarnings("unused")
	@RequestMapping(value="/delMoreTestPaper.action")
	@ResponseBody
	public String delMoreTestPaper(Integer[] tpIds){
		// 创建List集合储存试卷id
		List<Integer> textPaperIds = new ArrayList<Integer>();
		// 遍历数组，储存在集合内
		for (Integer integer : tpIds) {
			textPaperIds.add(integer.intValue());
		}
		// 删除试卷信息，先删除试卷与中间表的数据
		String ipCount = this.itemPaperService.deleteItemPaperBytpIds(textPaperIds);
		// 删除做题统计表信息
		int statistics = this.statisticsService.deleteStatisticsByTpIds(textPaperIds);
		// 删除试卷表的信息
		String tpCount = this.testPaperService.deleteMoreTestPaper(textPaperIds);
		return tpCount;
	}

	/**
	 * 添加试卷
	 * @throws ParseException 
	 */
	@RequestMapping("/addTestPaper.action")
	@ResponseBody
	public String addTestPaper(String tpName,Integer fieldId,Integer courseId,
			String startTime,String endTime,Integer number,
			Integer oneBranch,HttpSession session) throws ParseException{
		if((tpName.equals("")) || fieldId == null || courseId==null || number==null || oneBranch==null){
			return "incomplete";
		}
		if(number <=0 || oneBranch<=0){
			return "truenum";
		}
		// 从sessin作用域中获取用户
		User user = (User) session.getAttribute("user");
		// 创建TestPaper对象
		TestPaper testPaper = new TestPaper();
		// 查重，根据试卷名称查询
		testPaper = this.testPaperService.selectTestPaperByName(tpName);
		if(testPaper != null){
			return "again";
		}else{
			// 接收时间戳
			Long startLong = Long.parseLong(startTime);
			Long endLong = Long.parseLong(endTime);
			// 创建Date对象
			Date startdate = new Date(startLong);
			Date enddate = new Date(endLong);
			// 创建DateFormat对象
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			// 系统当前时间
			String nowTime = dateFormat.format(new Date());
			// 转换开始时间格式
			String timeStart = dateFormat.format(startdate);
			// 转换结束时间格式
			String timeEnd = dateFormat.format(enddate);
			// 判断时间的逻辑性
			if(dateFormat.parse(timeStart).compareTo(dateFormat.parse(timeEnd))>0 || 
					dateFormat.parse(timeStart).compareTo(dateFormat.parse(nowTime)) <0 ||
					dateFormat.parse(timeEnd).compareTo(dateFormat.parse(nowTime)) <0){
				return "timefalse";
			}
			// 根据行业id查询行业信息
			Field field = this.fieldService.getFieldById(fieldId);
			// 根据课程id查询课程信息
			Course course = this.courseService.getCourseById(courseId);
			// 创建TestPaper对象
			TestPaper tp = new TestPaper();
			// 给相应的属性赋值
			tp.setTpName(tpName);
			tp.setField(field);
			tp.setCourse(course);
			tp.setNumber(number);
			tp.setOneBranch(oneBranch);
			tp.setStartTime(timeStart);
			tp.setEndTime(timeEnd);
			tp.setTpTime(nowTime);
			// 总分
			Integer totalScore = number*oneBranch;
			tp.setTotalScore(totalScore);
			tp.setUser(user);
			// 添加试卷信息
			String count = this.testPaperService.addTestPaper(tp);
			return count;
		}

	}
	/**
	 * 根据修改传过来的试卷id查询试卷信息
	 */
	@RequestMapping("/getTestPaperById.action")
	@ResponseBody
	public TestPaper getTestPaperById(Integer tpId){
		// 创建TestPaper对象
		TestPaper testPaper = new TestPaper();
		// 根据id查询试卷信息
		testPaper = this.testPaperService.getTestPaperById(tpId);
		return testPaper;
	}

	/**
	 * 执行试卷的修改操作
	 * @throws ParseException 
	 */
	@RequestMapping("/updateTestPaper.action")
	@ResponseBody
	public String updateTestPaper(Integer tpId,String tpName,Integer fieldId,Integer courseId,
			String startTime,String endTime,Integer number,Integer oneBranch) throws ParseException{
		if((tpName.equals("")) || fieldId == null || courseId==null || number==null || oneBranch==null
				|| number <=0 || oneBranch<=0){
			return "incomplete";
		}
		if(number <=0 || oneBranch<=0){
			return "truenum";
		}
		// 接收时间戳
		Long startLong = Long.parseLong(startTime);
		Long endLong = Long.parseLong(endTime);
		// 创建Date对象
		Date startdate = new Date(startLong);
		Date enddate = new Date(endLong);
		// 创建DateFormat对象
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		// 系统当前时间
		String nowTime = dateFormat.format(new Date());
		// 转换开始时间格式
		String timeStart = dateFormat.format(startdate);
		// 转换结束时间格式
		String timeEnd = dateFormat.format(enddate);
		// 判断时间的逻辑性
		if(dateFormat.parse(timeStart).compareTo(dateFormat.parse(timeEnd))>0 || 
				dateFormat.parse(timeStart).compareTo(dateFormat.parse(nowTime)) <0 ||
				dateFormat.parse(timeEnd).compareTo(dateFormat.parse(nowTime)) <0){
			return "timefalse";
		}
		// 根据行业id查询行业信息
		Field field = this.fieldService.getFieldById(fieldId);
		// 根据课程id查询课程信息
		Course course = this.courseService.getCourseById(courseId);
		// 创建TestPaper对象
		TestPaper testPaper = new TestPaper();
		// 给相应的属性赋值
		testPaper.setTpId(tpId);
		testPaper.setTpName(tpName);
		testPaper.setField(field);
		testPaper.setCourse(course);
		testPaper.setNumber(number);
		testPaper.setOneBranch(oneBranch);
		testPaper.setStartTime(timeStart);
		testPaper.setEndTime(timeEnd);
		testPaper.setTpTime(nowTime);
		// 总分
		Integer totalScore = number*oneBranch;
		testPaper.setTotalScore(totalScore);
		// 修改前根据id查询试卷信息获取试题数
		TestPaper tp = this.testPaperService.getTestPaperById(tpId);
		// 执行修改操作
		String count = this.testPaperService.updateTestPaper(testPaper,tp.getNumber(),tp.getField().getFieldId(),tp.getCourse().getCourseId());
		return count;
	}
	/**
	 * 根据试卷id查询做题统计表的信息
	 */
	@RequestMapping("/getItemPaperByTpId.action")
	@ResponseBody
	public String getItemPaperByTpId(Integer tpId){
		// 执行查询操作
		String  statistics = this.statisticsService.selectStatisticsByTestPaperId(tpId);
		return statistics;
	}

	/**
	 * 根据试卷id批量做题统计表的信息
	 * @param tpIds
	 * @return
	 */
	@RequestMapping(value="/getItemPaperByTpIds.action")
	@ResponseBody
	public String getItemPaperByTpIds(Integer[] tpIds){
		// 创建List集合储存试卷id
		List<Integer> textPaperIds = new ArrayList<Integer>();
		// 遍历数组，储存在集合内
		for (Integer integer : tpIds) {
			textPaperIds.add(integer.intValue());
		}
		// 执行查询操作
		String  statistics = this.statisticsService.selectStatisticsByTestPaperIds(textPaperIds);
		return statistics;
	}
}
