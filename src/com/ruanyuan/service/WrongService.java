package com.ruanyuan.service;

import java.util.List;
import java.util.Map;

import com.ruanyuan.common.utils.Page;
import com.ruanyuan.pojo.ItemBank;
import com.ruanyuan.pojo.Middles;
import com.ruanyuan.pojo.Statistics;

/**
 * 错题业务逻辑层接口
 * @author 
 *
 */
public interface WrongService {
/**
 * 根据用户id 查询做题信息
 * @param userId
 * @return
 */
	
	public List<Statistics> getStatisticsByUserId(int userId);
	
	/**
	 * 根据错题id查询错题信息
	 * @param ibIds 错题id
	 * @return 试题对象(List集合)
	 */
	public List<ItemBank> getItemBankByWrongId(Map<Integer,String> ibIds);

	/**
	 * 多条件查询
	 * @param startTime
	 * @param endTime
	 * @param page
	 * @param rows
	 * @return
	 */
	public Page<Middles> getAllItemBank(Integer userId,String startTime,String endTime,Integer page,Integer rows,String tpName);
	/**
	 * 多条件查询总数
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	public int getItemBankCount(Integer userId,String startTime,String endTime,String tpName);
}
