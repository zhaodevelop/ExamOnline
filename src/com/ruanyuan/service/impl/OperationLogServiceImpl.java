package com.ruanyuan.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ruanyuan.common.utils.Page;
import com.ruanyuan.mapper.OperationLogMapper;
import com.ruanyuan.pojo.Middleo;
import com.ruanyuan.pojo.OperationLog;
import com.ruanyuan.service.OperationLogService;
/**
 * 操作日志逻辑层实现类
 * @author 
 *
 */

@Service("OperationLogService")
public class OperationLogServiceImpl implements OperationLogService{
	//创建operationLogMapper对象
	@Autowired
	private OperationLogMapper operationLogMapper;
	//创建operationLogMapper对象的setter方法，setter注入方式
	public void setOperationLogMapper(OperationLogMapper operationLogMapper) {
		this.operationLogMapper = operationLogMapper;
	}
	/**
	 * 操作日志：添加
	 */
	public int addOperationLog(OperationLog operationLog) {
		// TODO Auto-generated method stub
		return operationLogMapper.addOperationLog(operationLog);
	}
	/**
	 * 操作日志：修改
	 */
	public int updateOperationLog(OperationLog operationLog) {
		// TODO Auto-generated method stub
		return operationLogMapper.updateOperationLog(operationLog);
	}
	/**
	 * 操作日志：删除，根据操作日志id删除
	 */
	public int deleteOperationLog(int id) {
		// TODO Auto-generated method stub
		return operationLogMapper.deleteOperationLog(id);
	}
	/**
	 * 操作日志：添加，根据操作日志id查询
	 */
	public OperationLog getOperationLogById(int id) {
		// TODO Auto-generated method stub
		return operationLogMapper.getOperationLogById(id);
	}
	/**
	 * 登录日志导出，根据用户查询
	 */
	public List<Middleo> getOperationLogByUser() {
		// TODO Auto-generated method stub
		return operationLogMapper.getOperationLoglByUser();
	}
	
	/**
	 * 多条件查询
	 */
	public Page<Middleo> getOperationLog(String startTime, String endTime, Integer page, Integer rows,String userName,String rank) {
		// TODO Auto-generated method stub
		//当前页
		int start = ((page-1)*rows);
		List<Middleo> operlist=operationLogMapper.getOperationLogs(startTime, endTime, start, rows,userName,rank);
		int count = operationLogMapper.getOperationLogCount(startTime, endTime,userName, rank);
		Page<Middleo> result = new Page<Middleo>();
		result.setPage(page);  
		result.setSize(rows);  
		result.setTotal(count);
		result.setResult(operlist);	
		return result;
	}
	/**
	 * 多条件查询数量
	 */
	public int getOperationLogCount(String startTime, String endTime,String userName,String rank) {
		// TODO Auto-generated method stub
		return operationLogMapper.getOperationLogCount(startTime, endTime,userName,rank);
	}
	/**
	 * 批量删除
	 */
	public int deleteOperationLogs(List<Integer> ids) {
		// TODO Auto-generated method stub
		return operationLogMapper.deleteOperationLogs(ids);
	}
	/**
	 * 根据id查询
	 */
	public Middleo selectOperaById(int id) {
		return operationLogMapper.selectOperaById(id);
	}
	

}
