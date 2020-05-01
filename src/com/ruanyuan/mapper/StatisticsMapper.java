package com.ruanyuan.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ruanyuan.pojo.Statistics;
/**
 * 做题统计表接口
 * @author 
 *
 */
public interface StatisticsMapper {

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
	 * 添加做题统计表
	 * @param statistics 做题统计类对象
	 * @return 返回受影响行数
	 */
	public int addStatistics(Statistics statistics);
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
	 * @param 	userId   学生iD
	 * @param 	courseId 课程iD
	 * @return   返回是否考试的信息
	 */
	public int getStatisticsIsExams(@Param("userId") Integer userId,@Param("courseId") Integer courseId);
	/**
	 * 根据用户ID 和 试卷ID查询 得分成绩
	 * @param userId  用户ID
	 * @param tpId   试卷ID 
	 * @return  返回成绩
	 */
	public Float getFractionByUserIdAndTpId(@Param("userId") Integer userId ,@Param("tpId") Integer tpId);
	/*
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
	public List<Statistics> selectStatisticsByTestPaperId(Integer testPaperId);
	/**
	 * 根据试卷id查询统计表信息
	 * @param testPaperId试卷id
	 * @return statistics的list集合
	 */
	public List<Statistics> selectStatisticsByTestPaperIds(@Param("testPaperIds")List<Integer> testPaperIds);
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
	public int deleteStatisticsByTpIds(@Param("testPaperIds")List<Integer> testPaperIds);
}
