package com.ruanyuan.mapper;

import java.util.List;

import com.ruanyuan.pojo.Knowledge;
import com.ruanyuan.pojo.KnowledgeVo;

/**
 * 知识点实体类接口
 * 
 * @author
 *
 */
public interface KnowledgeMapper {
	/**
	 * 根据知识点ID查找知识点
	 * 
	 * @param knowledgeid 知识点ID
	 * @return 知识点实体类对象
	 */
	public Knowledge getKnowledgeById(Integer knowledgeid);

	/**
	 * 添加知识点
	 * 
	 * @param knowledge知识点对象
	 * @return 返回受影响行数
	 */
	public int addKnowledge(Knowledge knowledge);

	/**
	 * 修改知识点
	 * 
	 * @param knowledge知识点对象
	 * @return 返回受影响行数
	 */
	public int updateKnowledge(Knowledge knowledge);

	/**
	 * 根据知识点ID删除知识点
	 * 
	 * @param knowledgeid知识点ID
	 * @return 受影响行数
	 */
	public int deleteKnowledgeById(Integer knowledgeid);

	/**
	 * 根据课程查找知识点
	 * 
	 * @param courseId
	 * @return knowledge集合
	 */
	public List<Knowledge> getKnowledgeByCourseId(Integer courseId);

	/**
	 * 查询全部知识点信息
	 * 
	 * @return knowledge集合
	 */
	public List<Knowledge> getKnowledges();

	/**
	 * 分页查询知识点信息
	 * 
	 * @param 知识点包装类对象
	 * @return Knowledge集合
	 */
	public List<KnowledgeVo> getKnowledgeByPage(KnowledgeVo knowledgeVo);

	/**
	 * 获取知识点总数
	 * @param 知识点包装类对象
	 * @return 返回知识点总数
	 */
	public int getKnowledgeCount(KnowledgeVo knowledgeVo);

	/**
	 * 根据知识点名称查询知识点信息
	 * 
	 * @param knowledgeName
	 * @return Knowledge对象
	 */
	public Knowledge getKnowledgeByKnowledgeName(String knowledgeName);

	/**
	 * 根据选中的知识点id批量删除知识点信息
	 * 
	 * @param 数组ids
	 * @return 受影响的行数
	 */
	public int deleteKnowledgeByIds(List<Integer> ids);

	/**
	 * 根据课程id批量查询知识点信息
	 * 
	 * @param 数组ids
	 * @return Knowledge集合
	 */
	public List<Knowledge> getKnowledgeByCourseIds(int[] ids);
}
