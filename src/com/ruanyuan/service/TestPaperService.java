package com.ruanyuan.service;
import java.util.List;

import com.ruanyuan.common.utils.Page;
import com.ruanyuan.pojo.TestPaper;




/**
 * 试卷表业务逻辑层接口
 *
 */
public interface TestPaperService {
    /**
     * 根据试卷id删除试卷信息
     * @param id 试卷id
     * @return 受影响行数
     */
    String deleteTestPaperById(Integer id);
    /**
     * 添加试卷信息
     * @param testPaper 试卷实体对象
     * @return 字符串
     */
    String addTestPaper(TestPaper testPaper);
    /**
     * 修改试卷信息
     * @param testPaper 试卷实体对象
     * @param number 试卷题数
     * @param number 行业id
     * @param number 课程id
     * @return 字符串
     */
    String updateTestPaper(TestPaper testPaper, Integer number,Integer fieldId,Integer courseId);
    /**
     * 根据试卷id查询试卷信息
     * @param id 试卷id
     * @return 试卷实体对象
     */
    TestPaper getTestPaperById(Integer id);
    /**
     * 根据试卷名称查询试卷信息
     * @param tpName 试卷名称
     * @return 试卷实体对象
     */
    public TestPaper selectTestPaperByName(String tpName);
    /**
     * 根据试卷名称查询试卷信息
     * @param tpName 试卷名称
     * @return List集合约束为试卷实体对象
     */
    public List<TestPaper> getTestPaperByName(String tpName);
    /**
     * 多条件查询查询试卷信息
     * @param tpName 试卷名称
     * @param fieldId 行业
     * @param courseId 课程
     * @param startTime 试卷创建开始时间
     * @param endTime 试卷创建结束时间
     * @param userName List集合约束为用户实体对象
     * @param start 起始行
     * @param rows 所取行数
     * @return 返回List集合约束为试卷实体对象
     */
    public Page<TestPaper> getTestPapers(String tpName,Integer fieldId,Integer courseId,
    		String startTime,String endTime,String userName,Integer page,Integer rows);
    /**
     * 批量删除试卷信息
     */
    /**
     * 根据试卷id批量删除试卷信息
     * @param testPaperIds 试卷id
     * @return 返回字符串
     */
    public String deleteMoreTestPaper(List<Integer> testPaperIds);
    
    
    /**
     * 	根据ID查询试卷 以及试题信息  （嵌套结果）
     * @param tpId  试卷ID
     * @return  返回数据库查询的信息  
     */
    public TestPaper getTestPaperWithItemBank(Integer tpId,Integer courseId);
    /**
	 * 根据行业ID当前时间随机抽取可用试卷
	 * @param courseId   行业ID
	 * @return   返回查询到的试卷信息
	 */
	public TestPaper randowGetTestPaper(Integer courseId );
    /**
     *	获取试卷Id数组
     * @param courseId 课程id
     * @return  返回试卷Id数组
     */
    public Integer[] getTestPaperIdByNowTime(Integer courseId);
    /**
     * 获取试卷
     * @param courseId 课程id
     * @return 返回试卷信息(集合)
     */
    public List<TestPaper> getTestPaperByNowTime(Integer courseId);
    /**
	 * 判断该课程 现在是否有考试    该学生是否已经答题
	 * @param courseId   课程ID
	 * @param userId  学生ID
	 * @return  返回字符串
	 */
    public String isHaveExams(Integer courseId,Integer userId);
    /**
  	 * 根据行业id查询试卷信息
  	 * @param fieldId
  	 * @return 返回试卷信息(集合)
  	 */
  	public List<TestPaper> getTestPaperByFieldId(Integer fieldId);
  	/**
  	 * 根据课程id查询试卷信息
  	 * @param courseId 课程id
  	 * @return 返回试卷信息(集合)
  	 */
  	public List<TestPaper> getTestPaperByCourseId(Integer courseId);
  	/**
  	 * 根据知识点id查询试卷信息
  	 * @param knowledgeId 知识点id
  	 * @return 返回试卷信息(集合)
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
