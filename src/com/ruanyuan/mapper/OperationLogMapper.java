package com.ruanyuan.mapper;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.ruanyuan.pojo.Middleo;
import com.ruanyuan.pojo.OperationLog;
/**
 * 操作日志数据访问层接口
 * @author
 *
 */
public interface OperationLogMapper {
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
	 * 导出操作日志查询根据用户查询
	 * @param user
	 * @return
	 */
	public List<Middleo> getOperationLoglByUser();
	/**
	 * 分页多条件查询(开始时间结束时间)
	 */
	public List<Middleo> getOperationLogs(
			@Param("startTime")String startTime,@Param("endTime")String endTime,
			@Param("start")Integer start,@Param("rows")Integer rows,
			@Param("userName")String userName,@Param("rank")String rank);
	/**
	 * 多条件查询操作总数
	 * @return
	 */
	public int getOperationLogCount(@Param("startTime")String startTime,@Param("endTime")String endTime,@Param("userName")String userName,@Param("rank")String rank);
	/**
	 * 批量删除日志信息
	 */
	public int deleteOperationLogs(List<Integer> ids);
	/**
	 * 根据id查询
	 * @param id
	 * @return
	 */
	public OperationLog getOperationLogById(int id);
	/**
	 * 根据id查询
	 * @param id
	 * @return
	 */
	public Middleo selectOperaById(int id);

}
