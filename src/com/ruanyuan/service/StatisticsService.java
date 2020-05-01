package com.ruanyuan.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.ruanyuan.pojo.Statistics;
/**
 * 做题统计表业务逻辑层接口
 * @author 
 *
 */
public interface StatisticsService {

	/**
	 * 根据统计id查询
	 * @param statisticsId 做题统计ID
	 * @return 做题统计类对象
	 */
	public Statistics getStatisticsByStatisticsId(int statisticsId);
	/**
	 * 查询全部
	 * @return 做题统计类集合
	 */
	public List<Statistics> getAllStatistics();
	/**
	 * 添加做题统计
	 * @param ibIds   考题ID数组
	 * @param tpId    试卷ID
	 * @param oneBranch   单题分数
	 * @param request    
	 * @param session
	 * @return   返回数据库受影响的行数
	 */
	public int addStatistics(Integer[] ibIds ,Integer tpId,Float oneBranch, HttpServletRequest request,HttpSession session);
	
	/**
	 * 根据统计id修改表
	 * @param statistics 做题统计类对象
	 * @return 返回受影响行数
	 */
	public int updateStatistics(Statistics statistics);
	/**
	 * 根据统计id删除表
	 * @param statistics 做题统计类对象
	 * @return 返回受影响行数
	 */
	public int deleteStatistics(int statisticsId);
	
	/**
	 * 查询该考生是否已经答题
	 * @param  userId   学生iD
	 * @param	courseId  课程iD
	 * @return   返回是否考试的信息
	 */
	public int getStatisticsIsExams(Integer userId , Integer courseId);
	/**
	 * 根据用户ID 和 试卷ID查询 得分成绩
	 * @param userId  用户ID
	 * @param tpId   试卷ID 
	 * @return  返回成绩
	 */
	public Float getFractionByUserIdAndTpId(Integer userId ,Integer tpId);
	/**
	 * 根据试卷id查询统计表信息
	 * @param testPaperId试卷id
	 * @return statistics的list集合
	 */
	public List<Statistics> getStatisticsByTestPaperId(Integer testPaperId);
	/**
	 * 根据试卷id查询统计表信息
	 * @param testPaperId试卷id
	 * @return statistics的list集合
	 */
	public String selectStatisticsByTestPaperId(Integer testPaperId);
	/**
	 * 根据试卷id查询统计表信息
	 * @param testPaperId试卷id
	 * @return statistics的list集合
	 */
	public String selectStatisticsByTestPaperIds(List<Integer> testPaperIds);
	/**
	 * 根据试卷id删除统计表信息
	 * @param testPaperId 试卷id
	 * @return	受影响的行数
	 */
	public int deleteStatisticsByTpId(Integer testPaperId);
	/**
	 * 根据试卷id批量删除统计信息
	 * @param testPaperIds试卷id(集合)
	 * @return 受影响的行数
	 */
	public int deleteStatisticsByTpIds(List<Integer> testPaperIds);
	
}
