package com.ruanyuan.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ruanyuan.common.utils.Page;
import com.ruanyuan.mapper.WrongMapper;
import com.ruanyuan.pojo.ItemBank;
import com.ruanyuan.pojo.Middles;
import com.ruanyuan.pojo.Statistics;
import com.ruanyuan.service.WrongService;
/**
 * 用户业务逻辑层接口实现类
 *
 */
@Service
@Transactional
public class WrongServiceImpl implements WrongService {

	// 注入WrongMapper
		@Autowired
		private WrongMapper wrongMapper;
		
		/**
		 * 根据用户id从题库查询做题信息
		 */
	@Override
	public List<Statistics> getStatisticsByUserId(int userId) {
		// TODO Auto-generated method stub
		List<Statistics> statistics = this.wrongMapper.getStatisticsByUserId(userId);
		return statistics;
		
	}
	

	/**
	 * 根据错题id从题库查询错题信息
	 */
	public List<ItemBank> getItemBankByWrongId(Map<Integer,String> ibIds) {
		List<ItemBank> itemBankList = this.wrongMapper.getItemBankByWrongId(ibIds);
		return itemBankList;
	}
	
	/**
	 * 多条件查询
	 */
	@Override
	public Page<Middles> getAllItemBank(Integer userId,String startTime, String endTime, Integer page, Integer rows ,String tpName) {
		// TODO Auto-generated method stub
		//当前页
		int start = ((page-1)*rows);
		//根据页数查记录
		List<Middles> wronglist=wrongMapper.getAllItemBank(userId,startTime, endTime, start, rows,tpName);
		//查询总数
		int count = wrongMapper.getItemBankCount(userId,startTime, endTime,tpName);
		Page<Middles> result = new Page<Middles>();
		
		result.setPage(page);  //页数
		result.setSize(rows);  //记录数
		result.setTotal(count);//总数
		result.setResult(wronglist);	//查询出来的记录
		return result;
	}

	/**
	 * 多条件查询总数
	 */
	@Override
	public int getItemBankCount(Integer userId,String startTime, String endTime,String tpName) {
		// TODO Auto-generated method stub
		return wrongMapper.getItemBankCount(userId,startTime, endTime,tpName);
	}
	

}
