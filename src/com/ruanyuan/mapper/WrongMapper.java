package com.ruanyuan.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.ruanyuan.pojo.ItemBank;
import com.ruanyuan.pojo.Middles;
import com.ruanyuan.pojo.Statistics;
/**
 * 
 * 错题数据访问层接口
 * @author 
 *
 */
public interface WrongMapper {
	/**
	 * 根据用户Id查询做题信息
	 * @param userId
	 * @return
	 */
	public List<Statistics> getStatisticsByUserId(int userId);
	
	/**
	 * 根据错题id查询错题信息
	 * @param ibIds 错题id
	 * @return 试题对象(List集合)
	 */
	public List<ItemBank> getItemBankByWrongId(@Param("ibIds")Map<Integer,String> ibIds);
	
	/**
	 * 获取做题点总数
	 * @return 返回总数
	 */
	public int selectStatisticsMapperListCount(Statistics statistics);
	
	/**
	 * 多条件查询  
	 * @param startTime
	 * @param endTime
	 * @param start
	 * @param rows
	 * @return
	 */
	public List<Middles> getAllItemBank(@Param("userId") Integer userId,@Param("startTime")String startTime,@Param("endTime")String endTime,
			@Param("start")Integer start,@Param("rows")Integer rows,@Param("tpName")String tpName);
	/**
	 * 多条件查询总数
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	public int getItemBankCount(@Param("userId") Integer userId,@Param("startTime")String startTime,@Param("endTime")String endTime,@Param("tpName")String tpName);

}
