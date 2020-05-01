package com.ruanyuan.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ruanyuan.mapper.StatisticsMapper;
import com.ruanyuan.pojo.ItemBank;
import com.ruanyuan.pojo.Statistics;
import com.ruanyuan.pojo.User;
import com.ruanyuan.service.ItemBankService;
import com.ruanyuan.service.StatisticsService;
import com.ruanyuan.service.TestPaperService;
/**
 * 做题统计表逻辑层实现类
 * @author
 *
 */
@Service("statisticsService")
@Transactional
public class StatisticsServiceImpl implements StatisticsService {

	//注入注解StatisticsMapper
	@Autowired
	private StatisticsMapper statisticsMapper;
	//注入注解ItemBankService
	@Resource
	private ItemBankService itemBankService;
	//注入注解TestPaperService
	@Resource
	private TestPaperService testPaperService;
	/**
	 * 根据id查询做题统计信息
	 */
	@Override
	public Statistics getStatisticsByStatisticsId(int statisticsId) {
		// TODO Auto-generated method stub
		return this.statisticsMapper.getStatisticsByStatisticsId(statisticsId);
	}

	/**
	 * 查询所有做题统计信息
	 */
	@Override
	public List<Statistics> getAllStatistics() {
		// TODO Auto-generated method stub
		System.out.println("aaa");
		List<Statistics> statistics = statisticsMapper.getAllStatistics();
		
		return statistics;
	}
	/**
	 * 添加 做题统计
	 * @param  ibIds  考题ID数组
	 * @param	tpId	试卷ID
	 * @param	oneBranch   单题分数
	 */
	@Override
	public int addStatistics(Integer[] ibIds ,Integer tpId,Float oneBranch, HttpServletRequest request,HttpSession session) {
		
		//声明 做题实体类对象
		Statistics sta = new Statistics();
		//定义变量  储存分数
		float fraction = 0;
		//定义变量  储存错题数量
		int wrongNumber = 0;
		//定义变量 储存错题ID
		String wrongIbIds = "";
		//遍历考试题ID   计算分数、错题数量、错题ID
		for (Integer i : ibIds) {
			//获取选中题的答案
			String answer = request.getParameter("answer"+i);
			//查询ID的课题数据
			ItemBank itemBank = itemBankService.getItemBankById(i);
			//判断本题答案与所选答案是否正确    正确+分  错误+错题数量 和错题ID
			if(itemBank.getAnswer().equals(answer)) {
				fraction += oneBranch;
			}else {
				wrongNumber += 1;
				wrongIbIds += i+":"+answer+",";
				
			}
		}
		//如果有错题   最后一个逗号删除
		if(wrongIbIds.contains(",")) {
			wrongIbIds = wrongIbIds.substring(0, wrongIbIds.length()-1);
		}
		//获取登录的用户ID
		User user = (User) session.getAttribute("user");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		//封装数据
		sta.setUser(user);
		sta.setFraction(fraction);
		sta.setWrongNumber(wrongNumber);
		sta.setItemBankIds(wrongIbIds);
		sta.setSubmissionTime(sdf.format(new Date()));;
		sta.setTestPaper(testPaperService.getTestPaperById(tpId));
		return this.statisticsMapper.addStatistics(sta);
	}
	/**
	 * 修改做题统计
	 */
	@Override
	public int updateStatistics(Statistics statistics) {
		// TODO Auto-generated method stub
		return this.statisticsMapper.updateStatistics(statistics);
	}

	/**
	 * 删除做题统计
	 */
	@Override
	public int deleteStatistics(int statisticsId) {
		// TODO Auto-generated method stub
		System.out.println("aaa");
		return this.statisticsMapper.deleteStatistics(statisticsId);
		
	}
	
	/**
	 * 判断该学生是否答题
	 */
	@Override
	public int getStatisticsIsExams(Integer userId , Integer courseId) {
		// TODO Auto-generated method stub
		return this.statisticsMapper.getStatisticsIsExams(userId, courseId);
	}
	/**
	 * 根据用户ID 和 试卷ID查询 得分成绩
	 * @param userId  用户ID
	 * @param tpId   试卷ID 
	 * @return  返回成绩
	 */
	@Override
	public Float getFractionByUserIdAndTpId(Integer userId, Integer tpId) {
		// TODO Auto-generated method stub
		return this.statisticsMapper.getFractionByUserIdAndTpId(userId, tpId);
	}
	@Override
	public List<Statistics> getStatisticsByTestPaperId(Integer testPaperId) {
		// TODO Auto-generated method stub
		List<Statistics> statisticsByTestPaperId = statisticsMapper.getStatisticsByTestPaperId(testPaperId);
		return statisticsByTestPaperId;
	}
	/**
	 * 根据试卷id查询统计表信息
	 */
	public String selectStatisticsByTestPaperId(Integer testPaperId) {
		List<Statistics> statisticsList = this.statisticsMapper.selectStatisticsByTestPaperId(testPaperId);
		if(statisticsList.size()>0){
			return "haveStatistics";
		}else{
			return "noStatistics";		
		}

	}
	/**
	 * 根据试卷id批量查询统计表信息
	 */
	public String selectStatisticsByTestPaperIds(List<Integer> testPaperIds) {
		List<Statistics> statisticsList = this.statisticsMapper.selectStatisticsByTestPaperIds(testPaperIds);
		if(statisticsList.size()>0){
			return "haveStatistics";
		}else{
			return "noStatistics";		
		}
	}
	/**
	 * 根据试卷id单个删除统计表信息
	 */
	@Override
	public int deleteStatisticsByTpId(Integer testPaperId) {
		int count = this.statisticsMapper.deleteStatisticsByTpId(testPaperId);
		return count;
	}
	/**
	 * 根据试卷id批量删除统计表信息
	 */
	@Override
	public int deleteStatisticsByTpIds(List<Integer> testPaperIds) {
		int count = this.statisticsMapper.deleteStatisticsByTpIds(testPaperIds);
		return count;
	}
}
