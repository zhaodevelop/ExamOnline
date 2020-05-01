package com.ruanyuan.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ruanyuan.pojo.ItemBank;



/**
 * 题库实体类接口
 * @author
 *
 */
public interface ItemBankMapper {
	/**
	 * 根据题库ID查找试题
	 * @param itemBankid 题库ID
	 * @return 课程实体类对象
	 */
	public ItemBank getItemBankById(Integer ItemBankid);
	/**
	 * 根据试题名称查询
	 * @param question
	 * @return 返回实体类对象
	 */
	public ItemBank getItemBankByQuestion(String question);
	/**
	 * 添加题库
	 * @param ItemBank
	 * @return 返回受影响行数
	 */
	public int addItemBank(ItemBank ItemBank);
	/**
	 * Excel上传批量添加试题
	 * @param list
	 * @return返回受影响行数
	 */
	public int insertItemBankBatch(@Param("list")List<ItemBank> list);
	/**
	 * 修改题库
	 * @param ItemBank题库对象
	 * @return 受影响行数
	 */
	public int  updateItemBank(ItemBank ItemBank);
	/**
	 * 根据题库ID删除题库
	 * @param itemBankid题库ID
	 * @return 受影响行数
	 */
	public int  deleteItemBankById(Integer itemBankid);

	/**
	 * 根据行业id，课程id，知识点id查询试题信息knowledgeId
	 * @param fieldId 行业id
	 * @param courseId 知识点Id
	 * @return 题库集合
	 */
	public List<ItemBank> getMoreItemBankByIds(@Param("fieldId")Integer fieldId,
			@Param("courseId")Integer courseId);
	/**
	 * 多条件分页查询
	 * @param start页数
	 * @param rows
	 * @param userIds用户id集合
	 * @param fieldId行业id
	 * @param courseId课程id
	 * @param knowledgeId知识点ID
	 * @param question试题
	 * @return 题库集合
	 */
	public List<ItemBank> selectMoreItemBanks(@Param("start")Integer start,@Param("rows")Integer rows,@Param("userIds")List<Integer> userIds,
			@Param("fieldId")Integer fieldId,@Param("courseId")Integer courseId,@Param("knowledgeId")Integer knowledgeId
			,@Param("question")String question);
	/**
	 * 多条件分页查询总数
	 * @param start页数
	 * @param rows
	 * @param userIds用户id集合
	 * @param fieldId行业id
	 * @param courseId课程id
	 * @param knowledgeId知识点ID
	 * @param question试题
	 * @return 题库集合
	 */
	public int selectItemBankListCount(@Param("start")Integer start,@Param("rows")Integer rows,@Param("userIds")List<Integer> userIds
			,@Param("fieldId")Integer fieldId,@Param("courseId")Integer courseId,@Param("knowledgeId")Integer knowledgeId
			,@Param("question")String question);
	/**
	 * 批量删除题库的试题
	 * @param ibIds 题库id集合
	 * @return 返回受影响行数
	 */
	public int deleteItemBankByIds(@Param("ids")List<Integer>  ids);
	/**
	 * 根据行业id查询题库信息
	 * @param fieldId 行业ID
	 * @return 题库集合
	 */
	public List<ItemBank> getItemBankByFieldId(Integer fieldId);
	/**
	 * 根据课程id查询题库信息
	 * @param courseId 课程ID
	 * @return 题库集合
	 */
	public List<ItemBank> getItemBankByCourseId(Integer courseId);
	/**
	 * 根据课程id修改题库行业信息
	 * @param fieldId 行业Id
	 * @param courseId 课程ID
	 * @return 受影响行数
	 */
	public int updateItemBankByCourseId(@Param("fieldId")Integer fieldId,@Param("courseId")Integer courseId);
	/**
	 * 根据知识点id修改题库行业和课程
	 * @param fieldId 行业Id
	 * @param courseId 课程ID
	 * @param knowledgeId 知识点id
	 * @return 受影响行数
	 */
	public int updateItemBankByKnowledgeId(@Param("fieldId")Integer fieldId,@Param("courseId")Integer courseId,
			@Param("knowledgeId")Integer knowledgeId);
	/**
	 * 根据知识点id查询题库信息
	 * @param knowledgeId 知识点id
	 * @return	题库集合
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
