package com.ruanyuan.service.impl;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import com.ruanyuan.common.utils.Page;
import com.ruanyuan.mapper.TestPaperMapper;
import com.ruanyuan.mapper.UserMapper;
import com.ruanyuan.pojo.ItemBank;
import com.ruanyuan.pojo.TestPaper;
import com.ruanyuan.pojo.User;
import com.ruanyuan.service.ItemBankService;
import com.ruanyuan.service.ItemPaperService;
import com.ruanyuan.service.StatisticsService;
import com.ruanyuan.service.TestPaperService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 试卷表业务逻辑层接口实现类
 *
 */
@Service
@Transactional
public class TestPaperServiceImpl implements TestPaperService {
	// 注入TestPaperMapper
	@Autowired
	private TestPaperMapper testPaperMapper;
	// 注入ItemPaperService
	@Autowired
	private ItemPaperService itemPaperService;
	// 注入ItemBankService
	@Autowired
	private ItemBankService itemBankService;
	//注入statisticsService
	@Resource
    private StatisticsService statisticsService;
	//注入UserMapper
	@Resource
	private UserMapper userMapper;
	/**
	 * 根据试卷id删除试卷信息
	 */
	public String deleteTestPaperById(Integer id) {
		int count =  testPaperMapper.deleteTestPaperById(id);
		if(count>0){
			return "ok";
		}else{
			return "no";
		}
	}
	/**
	 * 添加试卷信息
	 */
	@SuppressWarnings("unused")
	public String addTestPaper(TestPaper testPaper) {
		// 根据行业id，课程id，知识点id查询试题信息
		List<ItemBank> itemBankList = itemBankService.getMoreItemBankByIds(
				testPaper.getField().getFieldId(), testPaper.getCourse().getCourseId());
		// 创建数组集合对象储存试题id
		List<Integer> ibIds = new ArrayList<Integer>();
		for (ItemBank itemBank : itemBankList) {
			ibIds.add(itemBank.getIbId());
		}
		if(ibIds.size()<testPaper.getNumber()){
			return "noMore";
		}
		// 创建Set集合对象(取出不重复的试题id)
		Set<Integer>  numSet = new HashSet<Integer>();
		// 由于froeach不能遍历set集合，在创建一个List集合储存Set中的试卷id
		List<Integer> ibIdList = new ArrayList<Integer>();
		// 取出所需试题数的试题id
		while (numSet.size()<testPaper.getNumber()) {
			int index = (int) (Math.floor(Math.random() * ibIds.size()));
			if(!numSet.contains(ibIds.get(index))) {
				numSet.add(ibIds.get(index));
			}
		}
		for (Integer num : numSet) {
			ibIdList.add(num);
		}
		// 添加试卷信息
		int count =  testPaperMapper.addTestPaper(testPaper);
		// 根据名称再查询试卷信息，获取试卷id
		TestPaper tp = this.testPaperMapper.selectTestPaperByName(testPaper.getTpName());
		// 向试卷与试题中间表内插入数据
		String ipCount = itemPaperService.addItemPaper(ibIdList,tp.getTpId());
		if(count>0){
			return "ok";
		}else{
			return "no";
		}
	}
	/**
	 * 根据试卷id修改试卷信息
	 */
	@SuppressWarnings("unused")
	public String updateTestPaper(TestPaper testPaper,Integer number,Integer fieldId,Integer courseId) {
		// 判断试题数量行业和课程是否进行了修改
		if(number!=testPaper.getNumber() || fieldId!= testPaper.getField().getFieldId()
				|| courseId != testPaper.getCourse().getCourseId()){ 
			// 根据试卷id删除中间表下的试题信息
			String ipCount = itemPaperService.deleteItemPaperBytpId(testPaper.getTpId());
			// 根据行业id，课程id查询试题信息
			List<ItemBank> itemBankList = this.itemBankService.getMoreItemBankByIds(
					testPaper.getField().getFieldId(), testPaper.getCourse().getCourseId());
			// 创建数组集合对象储存试题id
			List<Integer> ibIds = new ArrayList<Integer>();
			for (ItemBank itemBank : itemBankList) {
				ibIds.add(itemBank.getIbId());
			}
			if(ibIds.size()<testPaper.getNumber()){
				return "againAdd";
			}
			// 创建Set集合对象(取出不重复的试题id)
			Set<Integer>  numSet = new HashSet<Integer>();
			// 由于froeach不能遍历set集合，在创建一个List集合储存Set中的试卷id
			List<Integer> ibIdList = new ArrayList<Integer>();
			// 取出所需试题数的试题id
			while (numSet.size()<testPaper.getNumber()) {
				int index = (int) (Math.floor(Math.random() * ibIds.size()));
				if(!numSet.contains(ibIds.get(index))) {
					numSet.add(ibIds.get(index));
				}
			}
			for (Integer num : numSet) {
				ibIdList.add(num);
			}
			// 执行修改操作
			int count = testPaperMapper.updateTestPaper(testPaper);
			// 向试卷与试题中间表内插入数据
			String ipCounts = this.itemPaperService.addItemPaper(ibIdList,testPaper.getTpId());
			return ipCounts;
		}else{
			int count = testPaperMapper.updateTestPaper(testPaper);
			if(count>0){
				return "ok";
			}else{
				return "no";
			}
		}
	}
	/**
	 * 多条件查询试卷信息(试卷名称,行业,课程,试卷创建时间,组卷人)
	 */
	public Page<TestPaper> getTestPapers(String tpName, Integer fieldId,
			Integer courseId, String startTime, String endTime,
			String userName, Integer page, Integer rows) {
		// 存放用户id
		List<Integer> userIds = new ArrayList<Integer>();
		if(userName != "" && userName !=null){
			// 根据组卷人姓名查询用户信息
			List<User> userIdList = this.userMapper.getUserByNames(userName);
			// 判断用户集合是否为空
			if(userIdList.size()>0 && userIdList!=null){
				// 遍历用户集合
				for (User user : userIdList) {
					// 将用户id添加到集合中
					userIds.add(user.getUserId());
				}
			}else{
				userIds.add(-1);
			}
		}
		// 当前页
		int start = ((page-1)*rows);
		// 查询试卷信息列表
		List<TestPaper> testPaperList = this.testPaperMapper.getTestPapers(tpName, fieldId, courseId, startTime, endTime, userIds, start, rows);
		// 查询试卷总数
		Integer count = this.testPaperMapper.getTestPaperCount(tpName, fieldId, courseId, startTime, endTime, userIds);
		// 创建Page返回对象
		Page<TestPaper> result = new Page<TestPaper>();
		// 添加数据
		result.setPage(page);  // 当前页数
		result.setSize(rows);  // 每页数
		result.setTotal(count);	// 总数
		result.setResult(testPaperList);;	// 试卷信息
		return result;	
	}
	/**
	 * 根据试卷id查询试卷信息
	 */
	public TestPaper getTestPaperById(Integer id) {
		TestPaper testPaper =  testPaperMapper.getTestPaperById(id);
		return testPaper;
	}

	/**
	 * 根据试卷名称查询试卷信息
	 */
	public List<TestPaper> getTestPaperByName(String tpName) {
		List<TestPaper> testPaperList = this.testPaperMapper.getTestPaperByName(tpName);
		return testPaperList;
	}
	/**
	 * 根据试卷id批量删除
	 */
	public String deleteMoreTestPaper(List<Integer> testPaperIds) {
		int count = this.testPaperMapper.deleteMoreTestPaper(testPaperIds);
		if(count>0){
			return "ok";
		}else{
			return "no";
		}
	}

	/**
	 * 根据试卷名称查询试卷信息
	 */
	public TestPaper selectTestPaperByName(String tpName) {
		TestPaper testPaper = this.testPaperMapper.selectTestPaperByName(tpName);
		return testPaper;
	}
	
    
    /**
     * 	根据ID查询试卷 以及试题信息  （嵌套结果）
     */
	@Override
	public TestPaper getTestPaperWithItemBank(Integer tpId,Integer courseId) {
		// TODO Auto-generated method stub
		return this.testPaperMapper.getTestPaperWithItemBank(tpId,courseId);
	}
	/**
	 * 根据行业ID当前时间随机抽取可用试卷
	 */
	public TestPaper randowGetTestPaper(Integer courseId ) {
		//获取试卷Id数组
		Integer[] tpIds = this.getTestPaperIdByNowTime(courseId);
		//随机生成试卷Id
		int index = (int) (Math.random()*tpIds.length);
		//根据随机数 获取试卷信息
		TestPaper tp = this.getTestPaperWithItemBank(tpIds[index],courseId);
		return tp;
		
	}
    /**
     *	获取试卷Id数组
     */
	@Override
	public Integer[] getTestPaperIdByNowTime(Integer courseId) {
		// TODO Auto-generated method stub
		return this.testPaperMapper.getTestPaperIdByNowTime(courseId);
	}
	 /**
     *	获取试卷信息
     */
	@Override
	public List<TestPaper> getTestPaperByNowTime(Integer courseId) {
		// TODO Auto-generated method stub
		return this.testPaperMapper.getTestPaperByNowTime(courseId);
	}
	/**
	 * 判断该课程 现在是否有考试    该学生是否已经答题
	 */
	@Override
	public String isHaveExams(Integer userId , Integer courseId) {
		// TODO Auto-generated method stub
		//创建是否在考试范围内   是否已经考试   haveExams:有考试    noHaveExams:没有考试    yesExams:您已经考过试了
		String token = "";
		//创建集合  调用方法 接收试卷信息
		List<TestPaper> tpList = this.getTestPaperByNowTime(courseId);
		//判断集合中是否有试卷  如果没有则现在没有考试   如果有则有考试 再判断学生是否已经考过试
		if(tpList.size() == 0 ) {
			token = "noHavaExams";
		}else {
			token = "havaExams";
			//判断学生是否已经考过试
			int count = this.statisticsService.getStatisticsIsExams(userId , courseId);
			System.out.println(count);
			if(count > 0) {
				token = "yesExams";
			}
		}
		return token;
	}
	/**
	 * 根据行业id查询试卷信息
	 */
	@Override
	public List<TestPaper> getTestPaperByFieldId(Integer fieldId) {
		// TODO Auto-generated method stub
		return testPaperMapper.getTestPaperByFieldId(fieldId);
	}
	/**
	 * 根据课程id查询试卷信息
	 */
	@Override
	public List<TestPaper> getTestPaperByCourseId(Integer courseId) {
		// TODO Auto-generated method stub
		return testPaperMapper.getTestPaperByCourseId(courseId);
	}
	/**
	 * 根据知识点id查询试卷信息
	 */
	@Override
	public List<TestPaper> getTestPaperByKnowledgeId(Integer knowledgeId) {
		// TODO Auto-generated method stub
		return testPaperMapper.getTestPaperByKnowledgeId(knowledgeId);
	}
	/**
	 * 根据行业id批量查询试卷信息
	 */
	@Override
	public List<TestPaper> getTestPaperByFieldIds(int[] ids) {
		// TODO Auto-generated method stub
		return testPaperMapper.getTestPaperByFieldIds(ids);
	}
	/**
	 * 根据课程id批量查询试卷信息
	 */
	@Override
	public List<TestPaper> getTestPaperByCourseIds(int[] ids) {
		// TODO Auto-generated method stub
		return testPaperMapper.getTestPaperByCourseIds(ids);
	}
	/**
	 * 根据知识点id批量查询试卷信息
	 */
	@Override
	public List<TestPaper> getTestPaperByKnowledgeIds(int[] ids) {
		// TODO Auto-generated method stub
		return testPaperMapper.getTestPaperByKnowledgeIds(ids);
	}
	/**
	 * 根据行业ID获取课程信息
	 */
	@Override
	public List<TestPaper> getTestPaperByCouseId(Integer courseId) {
		// TODO Auto-generated method stub
		return this.testPaperMapper.getTestPaperByCouseId(courseId);
	}
}
