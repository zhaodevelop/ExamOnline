package com.ruanyuan.service;

import java.util.List;
import com.ruanyuan.common.utils.Page;
import com.ruanyuan.pojo.Journal;
import com.ruanyuan.pojo.Middlej;
/**
 * 业务逻辑层登录日志接口
 * @author 
 *
 */
public interface JournalService {
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
	 * 多条件查询日志总数
	 * @return
	 */
	public int getjournalCount(String startTime,String endTime,String userName,String rank);
	/**
	 * 批量删除日志信息
	 */
	public int deletejournals(List<Integer> ids);
	/**
	 * 分页多条件查询
	 * @param startTime
	 * @param endTime
	 * @param page
	 * @param rows
	 * @param userName
	 * @param rank
	 * @return
	 */
	public Page<Middlej> getJournals(String startTime,String endTime,Integer page,Integer rows,String userName,String rank);

}