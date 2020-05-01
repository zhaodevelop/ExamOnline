package com.ruanyuan.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ruanyuan.common.utils.Page;
import com.ruanyuan.mapper.FieldMapper;
import com.ruanyuan.pojo.Field;
import com.ruanyuan.service.FieldService;

/**
 * 行业逻辑层接口实现类
 * 
 * @author
 * 
 */
@Service
@Transactional
public class FieldServiceImpl implements FieldService {

	// 注入FieldMapper
	@Autowired
	private FieldMapper fieldMapper;

	/**
	 * 添加行业信息
	 */
	@Override
	public int addField(Field field) {
		int count = this.fieldMapper.addField(field);
		return count;
	}

	/**
	 * 根据id删除行业信息
	 */
	@Override
	public int deleteFieldById(int FieldId) {
		int count = this.fieldMapper.deleteFieldById(FieldId);
		return count;
	}

	/**
	 * 根据id修改行业信息
	 */
	@Override
	public int updateFieldById(Field field) {
		int count = this.fieldMapper.updateFieldById(field);
		return count;
	}

	/**
	 * 根据id查询行业信息
	 */
	@Override
	public Field getFieldById(int FieldId) {
		Field field = this.fieldMapper.getFieldById(FieldId);
		return field;
	}

	/**
	 * 查询所有行业
	 */
	@Override
	public List<Field> getFields() {
		// TODO Auto-generated method stub
		// 查询全部行业
		List<Field> fields = fieldMapper.getFields();
		return fields;
	}

	/**
	 * 获取行业总数
	 */
	@Override
	public int getFieldCount(Field field) {
		// TODO Auto-generated method stub
		return fieldMapper.getFieldCount(field);
	}

	/**
	 * 分页查询行业信息
	 */
	@Override
	public Page<Field> findFieldList(Integer page, Integer rows, String fieldName, String introduce) {
		// TODO Auto-generated method stub
		// 创建行业对象
		Field field = new Field();
		// 判断行业名称
		if (StringUtils.isNotBlank(fieldName)) {
			field.setFieldName(fieldName);
		}
		// 判断行业介绍
		if (StringUtils.isNotBlank(introduce)) {
			field.setIntroduce(introduce);
		}
		// 当前页
		field.setStartLine((page - 1) * rows);
		// 每页数
		field.setRows(rows);
		// 查询行业列表
		List<Field> fields = fieldMapper.getFieldByPage(field);
		System.out.println("service:" + fields);
		// 查询行业列表总记录数
		Integer count = fieldMapper.getFieldCount(field);
		// 创建Page返回对象
		Page<Field> results = new Page<Field>();
		// 赋值
		results.setPage(page);
		results.setResult(fields);
		results.setSize(rows);
		results.setTotal(count);
		// 返回结果results
		return results;
	}

	/**
	 * 根据行业名称查询行业信息
	 */
	@Override
	public Field getFieldByFieldName(String fieldName) {
		// TODO Auto-generated method stub
		return fieldMapper.getFieldByFieldName(fieldName);
	}

	/**
	 * 根据选中的行业id批量删除行业信息
	 */
	@Override
	public int deleteFieldByIds(List<Integer> ids) {
		// TODO Auto-generated method stub
		return fieldMapper.deleteFieldByIds(ids);
	}

	/**
	 * 获取全部行业信息
	 */
	@Override
	public List<Field> getAllField() {
		// TODO Auto-generated method stub
		return this.fieldMapper.getAllField();
	}
}
