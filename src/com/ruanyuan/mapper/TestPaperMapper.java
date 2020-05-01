package com.ruanyuan.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ruanyuan.pojo.TestPaper;


/**
 * 试卷表数据访问层接口
 **/
public interface TestPaperMapper {
	/**
     * 增加试卷信息
     * @param testPaper 试卷实体对象
     * @return 返回受影响行数
     */
    int addTestPaper(@Param("testPaper")TestPaper testPaper);
    /**
     * 根据试卷id删除试卷信息
     * @param id试卷id
     * @return 返回受影响行数
     */
    int deleteTestPaperById(Integer id);
    /**
     * 根据试卷id修改试卷信息
     * @param testPaper 试卷实体对象
     * @return 返回受影响行数
     */
    int updateTestPaper(@Param("testPaper")TestPaper testPaper);
    /**
     * 根据试卷id查询试卷信息
     * @param id 试卷id
     * @return 试卷实体对象
     */
    TestPaper getTestPaperById(Integer id);
    /**
     * 根据试卷名称查询
     * @param tpName 试卷名称
     * @return 返回试卷实体对象
     */
    public TestPaper selectTestPaperByName(String tpName);
    /**
     * 根据试卷名称查询试卷信息
     * @param tpName 试卷名称
     * @return List集合约束为试卷实体对象
     */
    public List<TestPaper> getTestPaperByName(String tpName);
    /**
     * 分页多条件查询
     * @param tpName 试卷名称
     * @param fieldId 行业
     * @param courseId 课程
     * @param startTime 试卷创建开始时间
     * @param endTime 试卷创建结束时间
     * @param userIdList List集合约束为用户实体对象
     * @param start 起始行
     * @param rows 所取行数
     * @return 返回List集合约束为试卷实体对象
     */
    public List<TestPaper> getTestPapers(@Param("tpName")String tpName,@Param("fieldId")Integer fieldId,
    		@Param("courseId")Integer courseId,@Param("startTime")String startTime,@Param("endTime")String endTime,
    		@Param("userIdList")List<Integer> userIdList,
    		@Param("start")Integer start,@Param("rows")Integer rows);
    /**
     * 多条件查询试卷总数
     * @param tpName 试卷名称
     * @param fieldId 行业
     * @param courseId 课程
     * @param startTime 试卷创建开始时间
     * @param endTime 试卷创建结束时间
     * @param userIdList List集合约束为用户实体对象
     * @return 返回试卷总数
     */
    public int getTestPaperCount(@Param("tpName")String tpName,@Param("fieldId")Integer fieldId,
    		@Param("courseId")Integer courseId,@Param("startTime")String startTime,@Param("endTime")String endTime,
    		@Param("userIdList")List<Integer> userIdList);
    /**
     * 批量删除试卷信息
     * @param testPaperIds List集合约束为试卷id
     * @return 返回受影响行数
     */
    public int deleteMoreTestPaper(@Param("testPaperIds")List<Integer> testPaperIds);
    
    /**
     * 	根据ID查询试卷 以及试题信息  （嵌套结果）
     * @param tpId  试卷ID
     * @return  返回数据库查询的信息  
     */
    public TestPaper getTestPaperWithItemBank(@Param("tpId")Integer tpId ,@Param("courseId")Integer courseId);
    /**
     * 获取试卷Id数组
     * @param courseId 课程id
     * @return 返回试卷Id数组
     */
    public Integer[] getTestPaperIdByNowTime(Integer courseId);
    /**
     * 获取试卷
     * @param courseId 课程id
     * @return 返回试卷信息集合形式
     */
    public List<TestPaper> getTestPaperByNowTime(Integer courseId);
    /**
	 * 根据行业id查询试卷信息
	 * @param fieldId 行业id
	 * @return 返回试卷信息集合形式
	 */
	public List<TestPaper> getTestPaperByFieldId(Integer fieldId);

		/**
		 * 根据课程id查询试卷信息
		 * @param courseId 课程id
		 * @return 返回试卷信息集合形式
		 */
		public List<TestPaper> getTestPaperByCourseId(Integer courseId);
		/**
		 * 根据知识点id查询试卷信息
		 * @param knowledgeId 知识点id
		 * @return 返回试卷信息集合形式
		 */
		public List<TestPaper> getTestPaperByKnowledgeId(Integer knowledgeId);
		/**
		 * 根据行业id批量查询试卷信息
		 * @param 数组ids
		 * @return TestPaper的list集合
		 */
		public List<TestPaper> getTestPaperByFieldIds(int[] ids);
		/**
		 * 根据课程id批量查询试卷信息
		 * @param 数组ids
		 * @return TestPaper的list集合
		 */
		public List<TestPaper> getTestPaperByCourseIds(int[] ids);
		/**
		 * 根据知识点id批量查询试卷信息
		 * @param 数组ids
		 * @return TestPaper的list集合
		 */
		public List<TestPaper> getTestPaperByKnowledgeIds(int[] ids);
	    /**
	     * 根据课程ID获取课程信息
	     * @param courseId  课程ID
	     * @return  返回信息	返回试卷信息
	     */
	    public List<TestPaper> getTestPaperByCouseId(Integer courseId);
}
