package com.ruanyuan.service.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ruanyuan.common.utils.Page;
import com.ruanyuan.mapper.JournalMapper;
import com.ruanyuan.pojo.Journal;

import com.ruanyuan.pojo.Middlej;
import com.ruanyuan.service.JournalService;
/**
 * 登录日志逻辑层实现类
 * @author 
 *
 */
@Service("JournalService")
public class JournalServiceImpl implements JournalService{
	//创建journalMapper对象
	@Autowired
	private JournalMapper journalMapper;
	//创建journalMapper对象的setter方法，setter注入方式
	public void setJournalMapper(JournalMapper journalMapper) {
		this.journalMapper = journalMapper;
	}
	/**
	 * 登录日志：添加
	 */
	public int addJournal(Journal journal) {
		// TODO Auto-generated method stub
		return journalMapper.addJournal(journal);
	}
	/**
	 * 登录日志：修改
	 */
	public int updateJournal(Journal journal) {
		// TODO Auto-generated method stub
		return journalMapper.updateJournal(journal);
	}
	/**
	 * 登录日志：删除，根据登录日志id删除
	 */
	public int deletejournal(int id) {
		// TODO Auto-generated method stub
		return journalMapper.deletejournal(id);
	}
	/**
	 * 登录日志：查询，根据登录日志id查询
	 */
	public Journal getJournalById(int id) {
		// TODO Auto-generated method stub
		return journalMapper.getJournalById(id);
	}
	/**
	 * 登录日志导出，根据用户查询
	 */
	public List<Middlej> getJournalByUser() {
		// TODO Auto-generated method stub
		return journalMapper.getJournalByUser();
	}
	/**
	 * 多条件查询数量
	 */
	public int getjournalCount(String startTime, String endTime,String userName,String rank) {
		// TODO Auto-generated method stub
		return journalMapper.getjournalCount(startTime, endTime,userName,rank);
	}
	/**
	 * 批量删除
	 */
	public int deletejournals(List<Integer> ids) {
		// TODO Auto-generated method stub
		return journalMapper.deletejournals(ids);
	}
	/**
	 * 多条件查询
	 * @param startTime
	 * @param endTime
	 * @param page
	 * @param rows
	 * @param userName
	 * @param rank
	 * @return
	 */
	public Page<Middlej> getJournals(String startTime,String endTime,Integer page,Integer rows,String userName,String rank) {
		// TODO Auto-generated method stub
		int start = ((page-1)*rows);
		List<Middlej> jourlist=journalMapper.getJournals(startTime, endTime, start, rows,userName,rank);
		for (Middlej other : jourlist) {
			System.out.println("一手数据"+other);
		}
		int count = journalMapper.getjournalCount(startTime, endTime,userName,rank);
		Page<Middlej> aa = new Page<Middlej>();
		aa.setPage(page);  
		aa.setSize(rows);  
		aa.setTotal(count);
		aa.setResult(jourlist);	
		return aa;
		
	}
	
	
	
	
	

}
