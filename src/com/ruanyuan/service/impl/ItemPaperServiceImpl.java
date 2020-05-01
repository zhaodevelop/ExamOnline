package com.ruanyuan.service.impl;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ruanyuan.mapper.ItemPaperMapper;
import com.ruanyuan.pojo.ItemPaper;
import com.ruanyuan.service.ItemPaperService;

/**
 * 试卷与试题的中间关系表数据业务逻辑层接口实现类
 *
 */
@Service
@Transactional
public class ItemPaperServiceImpl implements ItemPaperService {

	// 注入ItemPaperMapper
	@Autowired
	private ItemPaperMapper itemPaperMapper;
	
	/**
	 * 添加信息
	 */
	public String addItemPaper(List<Integer> ibIds,Integer tpId) {
		int count = this.itemPaperMapper.addItemPaper(ibIds,tpId);
		if(count>0){
			return "ok";
		}else{
			return "no";
		}
	}

	/**
	 * 根据试卷id删除信息
	 */
	public String deleteItemPaperBytpId(Integer tpId) {
		int count = this.itemPaperMapper.deleteItemPaperBytpId(tpId);
		if(count>0){
			return "ok";
		}else{
			return "no";
		}
	}

	/**
	 * 根据试卷id批量删除信息
	 */
	public String deleteItemPaperBytpIds(List<Integer> tpIds) {
		int count = this.itemPaperMapper.deleteItemPaperBytpIds(tpIds);
		if(count>0){
			return "ok";
		}else{
			return "no";
		}
	}
	/**
	 * 根据id查询种间关系表
	 */
	@Override
	public ItemPaper getItemPaperById(int id) {
		// TODO Auto-generated method stub
		return itemPaperMapper.getItemPaperById(id);
	}
}
