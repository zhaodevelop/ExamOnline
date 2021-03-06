package com.ruanyuan.service;

import java.util.List;

import com.ruanyuan.common.utils.Page;
import com.ruanyuan.pojo.Field;

/**
 * 行业表业务逻辑层接口
 *
 */
public interface FieldService {
	/**
	 * 添加行业信息
	 * 
	 * @param field 行业类对象
	 * @return 受影响行数
	 */
	public int addField(Field field);

	/**
	 * 根据id删除行业信息
	 * 
	 * @param FieldId 行业id
	 * @return 返回受影响行数
	 */
	public int deleteFieldById(int FieldId);

	/**
	 * 根据行业id修改行业信息
	 * 
	 * @param field行业类对象
	 * @return 返回受影响行数
	 */
	public int updateFieldById(Field field);

	/**
	 * 根据行业id查询信息
	 * 
	 * @param FieldId行业id
	 * @return 返回受影响行数
	 */
	public Field getFieldById(int FieldId);

	/**
	 * 查询所有行业
	 * 
	 * @return field集合
	 */
	public List<Field> getFields();

	/**
	 * 查询行业列表
	 * 
	 * @param Field实体类属性
	 * @return Page<Field>
	 */
	public Page<Field> findFieldList(Integer page, Integer rows, String fieldName, String introduce);

	/**
	 * 获取行业总数
	 * 
	 * @return 返回总数
	 */
	public int getFieldCount(Field field);

	/**
	 * 根据行业名称查询行业信息
	 * 
	 * @param fieldName
	 * @return Field对象
	 */
	public Field getFieldByFieldName(String fieldName);

	/**
	 * 根据选中的行业id批量删除行业信息
	 * 
	 * @param 数组ids
	 * @return 受影响的行数
	 */
	public int deleteFieldByIds(List<Integer> ids);

	/**
	 * 获取所有行业信息
	 * 
	 * @return 返回数据查询到的信息
	 */
	public List<Field> getAllField();
}
