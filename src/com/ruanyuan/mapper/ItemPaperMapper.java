package com.ruanyuan.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ruanyuan.pojo.ItemPaper;

/**
 * 题库试卷中间表接口
 * @author 
 *
 */
public interface ItemPaperMapper {
	/**
	 * 根据试题id查询中间表
	 * @param ibId 试题id集合形式
	 * @return 返回中间表信息集合形式
	 */
	public List<ItemPaper> getItemPaperByItemBankId(@Param("ids")List<Integer>  ids);
	/**
	 * 根据题库id批量删除中间关系表数据
	 * @param ids 试题id(集合)
	 * @return 返回受影响行数
	 */
	public int deleteItemPaperByItemBankId(@Param("ids")List<Integer>  ids);
	/**
	 * 根据试题id和试卷id添加中间表信息
	 * @param ibIds试题id(集合)
	 * @param tpId试卷id
	 * @return 返回受影响行数
	 */
	public int addItemPaper(@Param("ibIds")List<Integer> ibIds,@Param("tpId")Integer tpId);
	/**
	 * 根据试卷id删除信息
	 * @param tpId 试卷id
	 * @return 返回受影响行数
	 */
	public int deleteItemPaperBytpId(@Param("tpId")Integer tpId);
	/**
	 * 根据试卷id批量删除
	 * @param tpIds 试卷id(集合)
	 * @return 返回受影响行数
	 */
	public int deleteItemPaperBytpIds(@Param("tpIds")List<Integer> tpIds);
	/**
	 * 中间关系表查询
	 * @param id 中间关系表id
	 * @return 中间关系表实体对象
	 */
	public ItemPaper getItemPaperById(int id);
}
