package com.ruanyuan.mapper;

import java.util.List;

import com.ruanyuan.pojo.Course;

/**
 * 课程实体类接口
 * 
 * @author
 *
 */
public interface CourseMapper {
	/**
	 * 根据课程ID查找课程
	 * 
	 * @param courseid 课程ID
	 * @return 课程实体类对象
	 */
	public Course getCourseById(Integer courseid);

	/**
	 * 添加课程
	 * 
	 * @param course
	 * @return 返回受影响行数
	 */
	public int addCourse(Course course);

	/**
	 * 修改课程
	 * 
	 * @param course课程对象
	 * @return 返回受影响行数
	 */
	public int updateCourse(Course course);

	/**
	 * 根据课程ID删除课程
	 * 
	 * @param courseid课程ID
	 * @return 受影响行数
	 */
	public int deleteCourseById(Integer courseid);

	/**
	 * 根据行业id查询课程信息
	 * 
	 * @param fieldId
	 * @return course集合
	 */
	public List<Course> getCourseByFieldId(Integer fieldId);

	/**
	 * 查询全部课程信息
	 * 
	 * @return course集合
	 */
	public List<Course> getCourses();

	/**
	 * 分页查询课程信息
	 * 
	 * @param 课程实体类对象
	 * @return Course集合
	 */
	public List<Course> getCourseByPage(Course course);

	/**
	 * 获取课程总数
	 * 
	 * @param 课程实体类对象
	 * @return 返回总数
	 */
	public int getCourseCount(Course course);

	/**
	 * 根据课程名称查询课程信息
	 * 
	 * @param courseName
	 * @return Course对象
	 */
	public Course getCourseByCourseName(String courseName);

	/**
	 * 根据选中的课程id批量删除课程信息
	 * 
	 * @param 数组ids
	 * @return 受影响的行数
	 */
	public int deleteCourseByIds(List<Integer> ids);

	/**
	 * 根据行业id批量查询课程信息
	 * 
	 * @param 数组ids
	 * @return course集合
	 */
	public List<Course> getCourseByFieldIds(int[] ids);

	/**
	 * 获取所有课程信息
	 * 
	 * @return course集合
	 */
	public List<Course> getAllCourse();
}
