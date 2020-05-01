package com.ruanyuan.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;


import org.springframework.web.multipart.MultipartFile;

import com.ruanyuan.common.utils.Page;
import com.ruanyuan.pojo.ItemBank;

import com.ruanyuan.pojo.User;

/**
 * 逻辑层题库接口
 * @author 
 *
 */
public interface ItemBankService {
	/**
	 * 根据题库ID查找试题
	 * @param itemBankid 题库ID
	 * @return 课程实体类对象
	 */
	public ItemBank getItemBankById(Integer ItemBankid);
	/**
	 * 根据题库名称查询
	 * @param question试题
	 * @return 返回执行结果
	 */
	public String getItemBankByQuestion(String question);
	/**
	 * 添加题库信息
	 * @param fieldId1 行业id
	 * @param courseId1 课程ID
	 * @param knowledgeId1 知识点ID
	 * @param question1 试题名称
	 * @param optionA1	选项A
	 * @param optionB1 选项B
	 * @param optionC1 选项C
	 * @param optionD1 选项D
	 * @param answer1 答案
	 * @param user 用户对象
	 * @return 返回执行结果
	 */
	public String addItemBank(Integer fieldId1,Integer courseId1,Integer knowledgeId1,String question1,String optionA1,
			String optionB1,String optionC1,String optionD1,String answer1,User user);
	/**
	 * 修改题库信息
	 * @param itemBankId 题库id
	 * @param question 试题名称
	 * @param optionA  选项A
	 * @param optionB 选项B
	 * @param optionC 选项C
	 * @param optionD 选项D
	 * @param fieldId 行业id
	 * @param courseId 课程ID
	 * @param knowledgeId 知识点ID
	 * @param answer 答案
	 * @return 返回执行结果
	 */
	public String  updateItemBank(Integer itemBankId,String question,String optionA,String optionB,String optionC,String optionD,
			Integer fieldId,Integer courseId,Integer knowledgeId,String answer);
	/**
	 * 根据题库ID删除题库
	 * @param itemBankid题库ID
	 * @return 受影响行数
	 */
	public int  deleteItemBankById(Integer itemBankid);
	
	/**
	 * 多条件分页查询
	 * @param start页数
	 * @param rows 每页显示几个
	 * @param userIds用户id集合
	 * @param fieldId行业id
	 * @param courseId课程id
	 * @param knowledgeId知识点ID
	 * @param question试题
	 * @return page对象
	 */
	public Page<ItemBank> selectMoreItemBanks(Integer page, Integer rows,Integer fieldId,Integer courseId,Integer knowledgeId
			,String question,String userName);

	/**
	 * 批量删除题库的试题
	 * @param ibIds 题库id集合
	 * @return 返回执行结果
	 */
	public String deleteItemBankByIds(List<Integer>  ids);
	/**
	 *  先批量删除关系表在批量删除题库的试题
	 * @param ids 题库id集合
	 * @return 返回执行结果
	 */
	public String deleteItemBankByIds1(List<Integer>  ids);
	/**
	 * 根据题库id查询中间关系表
	 * @param ids 题库id集合
	 * @return  返回执行结果
	 */
	public String selectItemPaperByItemBankId(List<Integer>  ids);
	/**
	 * 根据行业id，课程id，知识点id查询试题信息
	 * @param fieldId 行业ID
	 * @param courseId 课程ID
	 * @return 题库集合
	 */
	public List<ItemBank> getMoreItemBankByIds(Integer fieldId,Integer courseId);
	/**
	 * Excel上传批量添加试题
	 * @param file 文件
	 * @param request request作用域
	 * @param user User对象
	 * @return 返回执行结果
	 */
	public Map<String,String> importExcel(MultipartFile file, HttpServletRequest request,User user);
	/**
	 * 根据行业id查询题库信息
	 * @param fieldId 行业ID
	 * @return 题库集合
	 */
	public List<ItemBank> getItemBankByFieldId(Integer fieldId);
	/**
	 * 根据课程id查询题库信息
	 * @param courseId 课程id
	 * @return 题库集合
	 */
	public List<ItemBank> getItemBankByCourseId(Integer courseId);
	/**
	 * 根据知识点id查询题库信息
	 * @param knowledgeId 知识点id
	 * @return 题库集合
	 */
	public List<ItemBank> getItemBankByKnowledgeId(Integer knowledgeId);
	/**
	 * 根据行业id批量查询题库信息
	 * @param 数组ids
	 * @return 题库集合
	 */
	public List<ItemBank> getItemBankByFieldIds(int[] ids);
	/**
	 * 根据课程id批量查询题库信息
	 * @param 数组ids
	 * @return 题库集合
	 */
	public List<ItemBank> getItemBankByCourseIds(int[] ids);
	/**
	 * 根据知识点id批量查询题库信息
	 * @param 数组ids
	 * @return 题库集合
	 */
	public List<ItemBank> getItemBankByKnowledgeIds(int[] ids);
	/**
	 * 上传试题模板
	 * @return试题
	 */
	public ItemBank getItemBankTemplate();
	
}
