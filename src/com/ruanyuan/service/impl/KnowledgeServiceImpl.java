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
import com.ruanyuan.mapper.KnowledgeMapper;
import com.ruanyuan.pojo.Course;
import com.ruanyuan.pojo.Field;
import com.ruanyuan.pojo.Knowledge;
import com.ruanyuan.pojo.KnowledgeVo;
import com.ruanyuan.service.KnowledgeService;

/**
 * 知识点逻辑层接口实现类
 * 
 * @author
 *
 */
@Service
@Transactional
public class KnowledgeServiceImpl implements KnowledgeService {
	// 注解注入KnowledgeMapper
	@Autowired
	private KnowledgeMapper knowledgeMapper;
	// 注解注入CourseMapper
	@Autowired
	private CourseMapper courseMapper;
	// 注解注入ItemBankMapper
	@Autowired
	private ItemBankMapper itemBankMapper;
	// 注解注入FieldMapper
	@Autowired
	private FieldMapper fieldMapper;

	/**
	 * 根据id查询知识点
	 */
	@Override
	public Knowledge getKnowledgeById(Integer knowledgeid) {
		// TODO Auto-generated method stub
		Knowledge knowledge = knowledgeMapper.getKnowledgeById(knowledgeid);
		return knowledge;
	}

	/**
	 * 添加知识点
	 */
	@Override
	public int addKnowledge(Knowledge knowledge) {
		// TODO Auto-generated method stub
		int count = knowledgeMapper.addKnowledge(knowledge);
		return count;
	}

	/**
	 * 修改知识点
	 */
	@Override
	public int updateKnowledge(Knowledge knowledge) {
		// TODO Auto-generated method stub
		int count = knowledgeMapper.updateKnowledge(knowledge);
		if(knowledge.getCourse()!=null) {
			//更新题库数据
			itemBankMapper.updateItemBankByKnowledgeId(knowledge.getCourse().getField().getFieldId(),
					knowledge.getCourse().getCourseId(),knowledge.getKnowledgeId());
			
		}
		return count;
	}

	/**
	 * 删除知识点
	 */
	@Override
	public int deleteKnowledgeById(Integer knowledgeid) {
		// TODO Auto-generated method stub
		int count = knowledgeMapper.deleteKnowledgeById(knowledgeid);
		return count;
	}

	/**
	 * 根据课程id查询知识点
	 */
	@Override
	public List<Knowledge> getKnowledgeByCourseId(Integer courseId) {
		// TODO Auto-generated method stub
		List<Knowledge> knowledges = knowledgeMapper.getKnowledgeByCourseId(courseId);
		return knowledges;
	}

	/**
	 * 查询所有知识点
	 */
	@Override
	public List<Knowledge> getKnowledges() {
		// TODO Auto-generated method stub
		return knowledgeMapper.getKnowledges();
	}

	/**
	 * 分页查询课程信息
	 */
	@Override
	public Page<KnowledgeVo> findKnowledgeList(Integer page, 
			Integer rows, String knowledgeName, String courseId,String fieldId) {
		// TODO Auto-generated method stub
		// 创建知识点包装类对象
		KnowledgeVo knowledgeVo = new KnowledgeVo();
		// 判断知识点名称
		if (StringUtils.isNotBlank(knowledgeName)) {
			knowledgeVo.setKnowledgeName(knowledgeName);
		}
		// 判断课程id是否为空
		if (StringUtils.isNotBlank(courseId)) {
			// 创建课程对象
			Course course = 
					courseMapper.getCourseById(Integer.valueOf(courseId));
			knowledgeVo.setCourse(course);
		}
		// 判断行业id是否为空
		if (StringUtils.isNotBlank(fieldId)) {
			Field field = fieldMapper.getFieldById(Integer.valueOf(fieldId));
			knowledgeVo.setField(field);
		}
		// 当前页
		knowledgeVo.setStartLine((page - 1) * rows);
		// 每页数
		knowledgeVo.setRows(rows);
		List<KnowledgeVo> knowledgeVos = knowledgeMapper.getKnowledgeByPage(knowledgeVo);
		System.out.println("service:" + knowledgeVos);
		// 查询知识点列表总记录数
		Integer count = knowledgeMapper.getKnowledgeCount(knowledgeVo);
		// 创建Page返回对象
		Page<KnowledgeVo> results = new Page<KnowledgeVo>();
		// 赋值
		results.setPage(page);
		results.setResult(knowledgeVos);
		results.setSize(rows);
		results.setTotal(count);
		// 返回结果results
		return results;
	}

	/**
	 * 获取知识点总数
	 */
	@Override
	public int getKnowledgeCount(KnowledgeVo knowledgeVo) {
		// TODO Auto-generated method stub
		return knowledgeMapper.getKnowledgeCount(knowledgeVo);
	}

	/**
	 * 根据知识点名称查询知识点信息
	 */
	@Override
	public Knowledge getKnowledgeByKnowledgeName(String knowledgeName) {
		// TODO Auto-generated method stub
		return knowledgeMapper.getKnowledgeByKnowledgeName(knowledgeName);
	}

	/**
	 * 根据选中的知识点id批量删除知识点信息
	 */
	@Override
	public int deleteKnowledgeByIds(List<Integer> ids) {
		// TODO Auto-generated method stub
		return knowledgeMapper.deleteKnowledgeByIds(ids);
	}

	/**
	 * 根据课程id批量查询知识点信息
	 */
	@Override
	public List<Knowledge> getKnowledgeByCourseIds(int[] ids) {
		// TODO Auto-generated method stub
		return knowledgeMapper.getKnowledgeByCourseIds(ids);
	}
}
