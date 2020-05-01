package com.ruanyuan.service;

import java.util.List;

import com.ruanyuan.common.utils.Page;
import com.ruanyuan.pojo.Middleo;
import com.ruanyuan.pojo.OperationLog;
/**
 * 业务逻辑层操作日志接口
 * @author 
 *
 */
public interface OperationLogService {
	/**
	 * 操作日志：添加信息
	 * @param 操作日志对象operationLog
	 * @return 受影响的行数
	 */
	public int addOperationLog(OperationLog operationLog);
	/**
	 * 操作日志：修改信息
	 * @param 操作日志对象operationLog
	 * @return 受影响的行数
	 */
	public int updateOperationLog(OperationLog operationLog);
	/**
	 * 操作日志：删除信息，根据操作日志id删除
	 * @param 操作日志id
	 * @return 受影响的行数
	 */
	public int deleteOperationLog(int id);
	/**
	 * 操作日志：查询信息，根据操作日志id查询操作信息
	 * @param 操作日志id
	 * @return 操作日志operationLog对象
	 */
	public OperationLog getOperationLogById(int id);
	/**
	 * 导出登录日志查询根据用户查询
	 * @param user
	 * @return
	 */
	public List<Middleo> getOperationLogByUser();
	/**
	 * 分页多条件查询(开始时间结束时间)
	 */
    public Page<Middleo> getOperationLog(String startTime,String endTime,Integer page,Integer rows,String userName,String rank);

	/**
	 * 多条件查询操作日志
	 * @return
	 */
	public int getOperationLogCount(String startTime,String endTime,String userName,String rank);
	/**
	 * 批量删除日志信息
	 */
	public int deleteOperationLogs(List<Integer> ids);
	/**
	 * 根据id查询
	 * @param id
	 * @return
	 */
	public Middleo selectOperaById(int id);

}
