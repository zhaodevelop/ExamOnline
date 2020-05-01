package com.ruanyuan.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ruanyuan.common.utils.Page;
import com.ruanyuan.mapper.CourseMapper;
import com.ruanyuan.mapper.FieldMapper;
import com.ruanyuan.mapper.ItemBankMapper;
import com.ruanyuan.pojo.Course;
import com.ruanyuan.pojo.Field;
import com.ruanyuan.service.CourseService;

/**
 * 课程业务逻辑层接口实现类
 * 
 * @author
 *
 */
@Service
@Transactional
public class CourseServiceImpl implements CourseService {
	// 注解注入CourseMapper
	@Autowired
	private CourseMapper courseMapper;
	// 注解注入FieldMapper
	@Autowired
	private FieldMapper fieldMapper;
	// 注解注入ItemBankMapper
	@Autowired
	private ItemBankMapper itemBankMapper;
	/**
	 * 根据课程id查询课程
	 */
	@Override
	public Course getCourseById(Integer courseid) {
		// TODO Auto-generated method stub
		Course course = courseMapper.getCourseById(courseid);
		return course;
	}

	/**
	 * 添加课程
	 */
	@Override
	public int addCourse(Course course) {
		// TODO Auto-generated method stub
		int count = courseMapper.addCourse(course);
		return count;
	}

	/**
	 * 修改课程
	 */
	@Override
	public int updateCourse(Course course) {
		// TODO Auto-generated method stub
		int count = courseMapper.updateCourse(course);
		if(course.getField()!=null) {
			//更新题库数据
			itemBankMapper.updateItemBankByCourseId(course.getField().getFieldId(),course.getCourseId());
		}
		return count;
	}

	/**
	 * 删除课程
	 */
	@Override
	public int deleteCourseById(Integer courseid) {
		// TODO Auto-generated method stub
		int count = courseMapper.deleteCourseById(courseid);
		return count;
	}

	/**
	 * 根据行业ID查询课程信息
	 */
	@Override
	public List<Course> getCourseByFieldId(Integer fieldId) {
		// TODO Auto-generated method stub
		List<Course> courses = courseMapper.getCourseByFieldId(fieldId);
		return courses;
	}

	/**
	 * 分页查询课程信息
	 */
	@Override
	public Page<Course> findCourseList(Integer page, Integer rows, String courseName, String fieldId) {
		// TODO Auto-generated method stub
		// 创建课程对象
		Course course = new Course();
		// 判断课程名称
		if (StringUtils.isNotBlank(courseName)) {
			course.setCourseName(courseName);
		}
		// 判断行业id是否为空
		if (StringUtils.isNotBlank(fieldId)) {
			// 创建行业对象
			Field field = fieldMapper.getFieldById(Integer.valueOf(fieldId));
			course.setField(field);
		}
		// 当前页
		course.setStartLine((page - 1) * rows);
		// 每页数
		course.setRows(rows);
		// 查询课程列表
		List<Course> courses = courseMapper.getCourseByPage(course);
		System.out.println("service:" + courses);
		// 查询课程列表总记录数
		Integer count = courseMapper.getCourseCount(course);
		// 创建Page返回对象
		Page<Course> results = new Page<Course>();
		// 赋值
		results.setPage(page);
		results.setResult(courses);
		results.setSize(rows);
		results.setTotal(count);
		// 返回结果results
		return results;
	}

	/**
	 * 获取所有课程信息
	 */
	@Override
	public List<Course> getCourses() {
		// TODO Auto-generated method stub
		return courseMapper.getCourses();
	}

	/**
	 * 获取课程总数
	 */
	@Override
	public int getCourseCount(Course course) {
		// TODO Auto-generated method stub
		return courseMapper.getCourseCount(course);
	}

	/**
	 * 根据课程名称查询课程信息
	 */
	@Override
	public Course getCourseByCourseName(String courseName) {
		// TODO Auto-generated method stub
		return courseMapper.getCourseByCourseName(courseName);
	}

	/**
	 * 根据选中的课程id批量删除课程信息
	 */
	@Override
	public int deleteCourseByIds(List<Integer> ids) {
		// TODO Auto-generated method stub
		return courseMapper.deleteCourseByIds(ids);
	}

	/**
	 * 根据行业id批量查询课程信息
	 */
	@Override
	public List<Course> getCourseByFieldIds(int[] ids) {
		// TODO Auto-generated method stub
		return courseMapper.getCourseByFieldIds(ids);
	}

	/**
	 * 获取所有课程信息
	 */
	@Override
	public List<Course> getAllCourse() {
		// TODO Auto-generated method stub
		return courseMapper.getAllCourse();
	}
}
