package com.ruanyuan.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.ruanyuan.pojo.Journal;
import com.ruanyuan.pojo.Middlej;

public interface JournalMapper {
	/**
	 * 登录日志：添加信息
	 * @param 登录日志对象journal
	 * @return 受影响的行数
	 */
	public int addJournal(Journal journal);
	/**
	 * 登录日志：修改信息
	 * @param 登录日志对象journal
	 * @return 受影响的行数
	 */
	public int updateJournal(Journal journal);
	/**
	 * 登录日志：删除信息，根据登录日志id删除
	 * @param 登录日志id
	 * @return 受影响的行数
	 */
	public int deletejournal(int id);
	/**
	 * 登录日志：查询信息，根据登录日志id查询登录信息
	 * @param 登录日志id
	 * @return 登录日志Journal对象
	 */
	public Journal getJournalById(int id);
	/**
	 * 导出登录日志查询根据用户查询
	 * @param user
	 * @return
	 */
	public List<Middlej> getJournalByUser();
	
	/**
	 * 多条件查询登录总数
	 * @return
	 */
	public int getjournalCount(@Param("startTime")String startTime,@Param("endTime")String endTime,@Param("userName")String userName,@Param("rank")String rank);
	/**
	 * 批量删除日志信息
	 */
	public int deletejournals(List<Integer> ids);
	/**
	 * 分页多条件查询(开始时间结束时间)
	 */
	public List<Middlej> getJournals(@Param("startTime")String startTime,@Param("endTime")String endTime,
			@Param("start")Integer start,@Param("rows")Integer rows,
			@Param("userName")String userName,@Param("rank")String rank);
}
