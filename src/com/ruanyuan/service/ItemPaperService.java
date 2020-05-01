package com.ruanyuan.service;

import java.util.List;

import com.ruanyuan.pojo.ItemPaper;


/**
 * 试卷与试题的中间关系表数据业务逻辑层接口
 */
public interface ItemPaperService {

	/**
	 * 根据试题id和试卷id添加中间表信息
	 * @param ibIds试题id(集合)
	 * @param tpId试卷id
	 * @return 返回String类型结果
	 */
	public String addItemPaper(List<Integer> ibIds,Integer tpId);
	/**
	 * 根据试卷id删除信息
	 * @param tpId 试卷id
	 * @return 返回String类型结果
	 */
	public String deleteItemPaperBytpId(Integer tpId);
	/**
	 * 根据试卷id批量删除
	 * @param tpIds 试卷id(集合)
	 * @return 返回String类型结果
	 */
	public String deleteItemPaperBytpIds(List<Integer> tpIds);
	/**
	 * 中间关系表查询
	 * @param id 中间关系表id
	 * @return 中间关系表实体对象
	 */
	public ItemPaper getItemPaperById(int id);
}
