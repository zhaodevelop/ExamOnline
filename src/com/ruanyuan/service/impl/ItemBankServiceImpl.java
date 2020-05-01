package com.ruanyuan.service.impl;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;


import org.apache.commons.lang3.StringUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.ruanyuan.common.utils.ExcelUtil;
import com.ruanyuan.common.utils.Page;
import com.ruanyuan.mapper.CourseMapper;
import com.ruanyuan.mapper.FieldMapper;
import com.ruanyuan.mapper.ItemBankMapper;
import com.ruanyuan.mapper.ItemPaperMapper;
import com.ruanyuan.mapper.KnowledgeMapper;
import com.ruanyuan.mapper.UserMapper;
import com.ruanyuan.pojo.Course;
import com.ruanyuan.pojo.Field;
import com.ruanyuan.pojo.ItemBank;
import com.ruanyuan.pojo.ItemPaper;
import com.ruanyuan.pojo.Knowledge;

import com.ruanyuan.pojo.User;
import com.ruanyuan.service.ItemBankService;
/**
 * 题库业务逻辑层接口实现类
 * @author 
 *
 */
@Service
@Transactional
public class ItemBankServiceImpl implements ItemBankService{
	//注解注入ItemBankMapper
	@Autowired
	private ItemBankMapper itemBankMapper;
	//注解注入userMapper
	@Autowired
	private ItemPaperMapper itemPaperMapper;
	//注解注入userMapper
	@Autowired
	private UserMapper userMapper;
	//注解注入fieldMapper
	@Autowired
	private FieldMapper fieldMapper;
	//注解注入fieldMapper
	@Autowired
	private CourseMapper courseMapper;
	//注解注入fieldMapper
	@Autowired
	private KnowledgeMapper knowledgeMapper;
	/**
	 * 根据题库ID查找试题
	 * @param itemBankid 题库ID
	 * @return 课程实体类对象
	 */
	@Override
	public ItemBank getItemBankById(Integer ItemBankid) {
		// TODO Auto-generated method stub
		ItemBank itemBank= itemBankMapper.getItemBankById(ItemBankid);
		return itemBank;
	}
	/**
	 * 添加题库信息
	 * @param fieldId1 行业id
	 * @param courseId1 课程ID
	 * @param knowledgeId1 知识点ID
	 * @param question1 试题名称
	 * @param optionA1	选项A
	 * @param optionB1 选项B
	 * @param optionC1 选项C
	 * @param optionD1 选项D
	 * @param answer1 答案
	 * @param user 用户对象
	 * @return 返回 执行结果
	 */
	@Override
	public String addItemBank(Integer fieldId1,Integer courseId1,Integer knowledgeId1,String question1,String optionA1,
			String optionB1,String optionC1,String optionD1,String answer1,User user) {
		//创建题库对象
		ItemBank itemBank = new ItemBank();
		//创建知识点对象并赋值
		Knowledge knowledge = new Knowledge();
		knowledge.setKnowledgeId(knowledgeId1);
		//创建课程对象并赋值
		Course course = new Course();
		course.setCourseId(courseId1);
		//创建行业对象并赋值
		Field field= new Field();
		field.setFieldId(fieldId1);
		//向题库表各属性赋值
		itemBank.setUser(user);
		itemBank.setQuestion(question1);
		itemBank.setOptionA(optionA1);
		itemBank.setOptionB(optionB1);
		itemBank.setOptionC(optionC1);
		itemBank.setOptionD(optionD1);
		itemBank.setAnswer(answer1);
		itemBank.setKnowledge(knowledge);
		itemBank.setCourse(course);
		itemBank.setField(field);
		// TODO Auto-generated method stub
		int count = itemBankMapper.addItemBank(itemBank);
		if(count>0) {
			return "ok";
		}else {
			return "no";
		}
		
	}
	/**
	 * 修改题库信息
	 * @param itemBankId 题库id
	 * @param question 试题名称
	 * @param optionA  选项A
	 * @param optionB 选项B
	 * @param optionC 选项C
	 * @param optionD 选项D
	 * @param fieldId 行业id
	 * @param courseId 课程ID
	 * @param knowledgeId 知识点ID
	 * @param answer 答案
	 * @return 返回 执行结果
	 */
	@Override
	public String updateItemBank(Integer itemBankId,String question,String optionA,String optionB,String optionC,String optionD,
			Integer fieldId,Integer courseId,Integer knowledgeId,String answer) {
		// TODO Auto-generated method stub
		ItemBank itemBank = new ItemBank();
		//创建知识点对象
		Knowledge knowledge = new Knowledge();
		if(knowledgeId !=null) {
			knowledge.setKnowledgeId(knowledgeId);
		}
		//创建课程对象
		Course course = new Course();
		if(courseId!=null) {
			course.setCourseId(courseId);
		}
		//创建行业对象
		Field field = new Field();
		if(field!=null) {
			field.setFieldId(fieldId);
		}
		itemBank.setIbId(itemBankId);
		//试题不为空时
		if(StringUtils.isNotBlank(question)) {
			itemBank.setQuestion(question);
		}
		//选项A不为空时
		if(StringUtils.isNotBlank(optionA)) {
			itemBank.setOptionA(optionA);
		}
		//选项B不为空时
		if(StringUtils.isNotBlank(optionB)) {
			itemBank.setOptionB(optionB);
		}
		//选项C不为空时
		if(StringUtils.isNotBlank(optionC)) {
			itemBank.setOptionC(optionC);
		}
		//选项D不为空时
		if(StringUtils.isNotBlank(optionD)) {
			itemBank.setOptionD(optionD);
		}
		//答案不为空时
		if(StringUtils.isNotBlank(answer)) {
			itemBank.setAnswer(answer);
		}
		//插入知识点对象
		itemBank.setKnowledge(knowledge);
		//插入课程对象
		itemBank.setCourse(course);
		//插入行业对象
		itemBank.setField(field);
		System.out.println("itemBank::::::"+itemBank);
		int count = itemBankMapper.updateItemBank(itemBank);
		if(count>0) {
			return "ok";
		}else {
			return "no";
		}
	}
	/**
	 * 根据题库ID删除题库
	 * @param itemBankid题库ID
	 * @return 受影响行数
	 */
	@Override
	public int deleteItemBankById(Integer itemBankid) {
		// TODO Auto-generated method stub
		int count = itemBankMapper.deleteItemBankById(itemBankid);
		return count;
	}
	/**
	 * 多条件分页查询
	 * @param start页数
	 * @param rows 每页显示几个
	 * @param userIds用户id集合
	 * @param fieldId行业id
	 * @param courseId课程id
	 * @param knowledgeId知识点ID
	 * @param question试题
	 * @return page对象
	 */
	@Override
	public Page<ItemBank> selectMoreItemBanks(Integer page, Integer rows,Integer fieldId,Integer courseId,Integer knowledgeId
			,String question,String userName) {
		// TODO Auto-generated method stub
		//用户id集合
		List<Integer> userIds= new ArrayList<Integer>();
		//当用户名名称个不为空时
		if(StringUtils.isNotBlank(userName)) {
			List<User> users = userMapper.getUserByNames(userName);
			if(users.size()>0 && users!=null) {
				for (User user : users) {
					userIds.add(user.getUserId());
				}
			}else {
				userIds.add(-1);
			}
		}
		Integer page1=(page-1) * rows;
		// 每页数
		//itembank.setRows(rows);
		//查询试题列表
		List<ItemBank> itemBanks=itemBankMapper.selectMoreItemBanks(page1, rows, userIds, fieldId, courseId, knowledgeId, question);
		//System.out.println("itemBank="+itemBanks);
		//查询总数
		int count = itemBankMapper.selectItemBankListCount(page1, rows, userIds, fieldId, courseId, knowledgeId, question);
		// 创建Page返回对象
		Page<ItemBank> results = new Page<ItemBank>();
		results.setPage(page);
		results.setResult(itemBanks);
		results.setSize(rows);
		results.setTotal(count);
		return results;
	}
	/**
	 * 批量删除题库的试题
	 * @param ibIds 题库id集合
	 * @return 返回 执行结果
	 */
	@Override
	public String deleteItemBankByIds(List<Integer>  ids) {
		// TODO Auto-generated method stub
		int count = itemBankMapper.deleteItemBankByIds(ids);
		if(count>0) {
			return "ok";
		}else {
			return "NO";
		}
	}
	/**
	 *  先批量删除关系表在批量删除题库的试题
	 * @param ids 题库id集合
	 * @return 返回 执行结果
	 */
	@Override
	public String deleteItemBankByIds1(List<Integer>  ids) {
		// TODO Auto-generated method stub
		//先删除关系表
		itemPaperMapper.deleteItemPaperByItemBankId(ids);
		//再删除题库数据
		int count = itemBankMapper.deleteItemBankByIds(ids);
		//进行判断返回结果
		if(count>0) {
			return "ok";
		}else {
			return "NO";
		}
	}
	/**
	 * 根据题库id查询中间关系表
	 * @param ids 题库id集合
	 * @return  返回 执行结果
	 */
	public String selectItemPaperByItemBankId(List<Integer> ids) {
		// TODO Auto-generated method stub
		//查询删除的数据是否关联到中间关系表
		List<ItemPaper> s = itemPaperMapper.getItemPaperByItemBankId(ids);
		//进行判断返回结果
		if(s!=null&&s.size()>0) {
			return "Consider";
		}else {
			return "NoConsider";
		}
	}
	/**
	 * 根据题库名称查询
	 * @param question试题
	 * @return 返回 执行结果
	 */
	@Override
	public String getItemBankByQuestion(String question) {
		// TODO Auto-generated method stub
		//执行查询语句
		ItemBank itemBank = itemBankMapper.getItemBankByQuestion(question);
		//进行判断
		if(itemBank!=null) {
			return "ok";
		}else {
			return "no";
		}
	}
	/**
	 * 根据行业id，课程id，知识点id查询试题信息
	 * @param fieldId 行业ID
	 * @param courseId 课程ID
	 * @return 题库集合
	 */
	public List<ItemBank> getMoreItemBankByIds(Integer fieldId,
			Integer courseId) {
		List<ItemBank> itemBankList = this.itemBankMapper.getMoreItemBankByIds(fieldId, courseId);
		return itemBankList;
	}
	/**
	 * Excel上传批量添加试题
	 * @param file 文件
	 * @param request request作用域
	 * @param user User对象
	 * @return 返回 执行结果
	 */
	@Override
	public Map<String,String> importExcel(MultipartFile file, HttpServletRequest request,User user) {
		// TODO Auto-generated method stub
		//执行成功的数量
		int count = 0;
		//map集合
		Map<String,String> map = new HashMap<String,String>();
		//重名
		String duplicate = "";
		//错误
		String error ="";
		//编码不存在
		String nothingness="";
		int row = 0;
	    try {
	        System.out.println(file.getOriginalFilename());
	        InputStream in = file.getInputStream();
	        List<List<Object>> listob = ExcelUtil.getBankListByExcel(in, file.getOriginalFilename());
			//去掉excel的重复试题名称数据
			for (int i = 0; i < listob.size()-1; i++) {
				for (int j = listob.size()-1; j > i; j--) {
					if(listob.get(i).get(1).equals(listob.get(j).get(1))) {
						listob.remove(j);
					}
				}
			}
	        List<ItemBank> itemBankList = new ArrayList<ItemBank>();
	        //String createBy = request.getSession().getAttribute("userName").toString();
	        //遍历listob数据，把数据放到List中
	        for (int i = 0; i < listob.size(); i++) {
	            List<Object> ob = listob.get(i);
	            System.out.println("ob"+ob);
	            //题库对象
	            ItemBank itemBank = new ItemBank();
	    		//创建知识点对象
	    		Knowledge knowledge = new Knowledge();
	    		//创建课程对象
	    		Course course = new Course();
	    		//创建行业对象
	    		Field field = new Field();
	    		itemBank.setUser(user);

	    		if((String.valueOf(ob.get(6)).trim().equals("A") || String.valueOf(ob.get(6)).trim().equals("B") ||
	    				String.valueOf(ob.get(6)).trim().equals("C") || String.valueOf(ob.get(6)).trim().equals("D")
	    				)&& String.valueOf(ob.get(7)).trim().matches("^([0-9]+$)+") && 
	    				String.valueOf(ob.get(8)).trim().matches("^([0-9]+$)+") &&
	    				String.valueOf(ob.get(9)).trim().matches("^([0-9]+$)+")){
		    		//试题查重
		    		ItemBank itemBank1=itemBankMapper.getItemBankByQuestion(String.valueOf(ob.get(1)).trim());
		    		//判断行业是否存在
		    		Field field1 =  fieldMapper.getFieldById(Integer.valueOf(String.valueOf(ob.get(7))));
		    		//判断课程是否存在
		    		Course course1 = courseMapper.getCourseById(Integer.valueOf(String.valueOf(ob.get(8))));		    	
		    		//判断知识点是否存在
		    		Knowledge knowledge1=knowledgeMapper.getKnowledgeById(Integer.valueOf(String.valueOf(ob.get(9))));
		    		if(itemBank1!=null) {
						System.out.println("重名：" +String.valueOf(ob.get(0)).trim());
						duplicate+=String.valueOf(ob.get(0)).trim()+",";
						row++;
		    		}else if(field1==null || course1==null || knowledge1==null ) {
		    			System.out.println("编码不存在：" +String.valueOf(ob.get(0)).trim());
		    			nothingness+=String.valueOf(ob.get(0)).trim()+",";
						row++;
		    		}else {
			            //通过遍历实现把每一列封装成一个model中，再把所有的model用List集合装载
			    		itemBank.setQuestion(String.valueOf(ob.get(1)).trim());
			    		itemBank.setOptionA(String.valueOf(ob.get(2)).trim());
			    		itemBank.setOptionB(String.valueOf(ob.get(3)).trim());
			    		itemBank.setOptionC(String.valueOf(ob.get(4)).trim());
			    		itemBank.setOptionD(String.valueOf(ob.get(5)).trim());
			    		itemBank.setAnswer(String.valueOf(ob.get(6)).trim());
			    		field.setFieldId(Integer.valueOf(String.valueOf(ob.get(7))));
			    		course.setCourseId(Integer.valueOf(String.valueOf(ob.get(8))));
			    		knowledge.setKnowledgeId(Integer.valueOf(String.valueOf(ob.get(9))));
			    		itemBank.setField(field);
			    		itemBank.setCourse(course);
			    		itemBank.setKnowledge(knowledge);
			    		itemBankList.add(itemBank);
		    		}    			
	    		}else {
					row++;
					error+=String.valueOf(ob.get(0)).trim()+",";
	    		}

	        }
	        //批量插入
	        System.out.println("itemBankList="+itemBankList);
	        if(row==0) {
		        count= itemBankMapper.insertItemBankBatch(itemBankList); 
		        System.out.println("count="+count);
	        }

	    } catch (Exception e) {
	    	return map;
	    }
		if(duplicate.contains(",")) {
			duplicate=duplicate.substring(0,duplicate.length()-1);
		}
		if(error.contains(",")) {
			error=error.substring(0,error.length()-1);
		}
		if(nothingness.contains(",")) {
			nothingness=nothingness.substring(0,nothingness.length()-1);
		}
		System.out.println("nothingness"+nothingness);
		map.put("duplicate", duplicate);
		map.put("error", error);
		map.put("success",String.valueOf(count));	    
		map.put("nothingness", nothingness);    
	    return map;
	}
	/**
	 * 根据行业id查询题库信息
	 * @param fieldId 行业ID
	 * @return 题库集合
	 */
	@Override
	public List<ItemBank> getItemBankByFieldId(Integer fieldId) {
		// TODO Auto-generated method stub
		return itemBankMapper.getItemBankByFieldId(fieldId);
	}
	/**
	 * 根据课程id查询题库信息
	 * @param courseId 课程id
	 * @return 题库集合
	 */
	@Override
	public List<ItemBank> getItemBankByCourseId(Integer courseId) {
		// TODO Auto-generated method stub
		return itemBankMapper.getItemBankByCourseId(courseId);
	}
	/**
	 * 根据知识点id查询题库信息
	 * @param knowledgeId 知识点id
	 * @return 题库集合
	 */
	@Override
	public List<ItemBank> getItemBankByKnowledgeId(Integer knowledgeId) {
		// TODO Auto-generated method stub
		return itemBankMapper.getItemBankByKnowledgeId(knowledgeId);
	}
	/**
	 * 根据行业id批量查询题库信息
	 * @param 数组ids
	 * @return 题库集合
	 */
	@Override
	public List<ItemBank> getItemBankByFieldIds(int[] ids) {
		// TODO Auto-generated method stub
		return itemBankMapper.getItemBankByFieldIds(ids);
	}
	/**
	 * 根据课程id批量查询题库信息
	 * @param 数组ids
	 * @return 题库集合
	 */
	@Override
	public List<ItemBank> getItemBankByCourseIds(int[] ids) {
		// TODO Auto-generated method stub
		return itemBankMapper.getItemBankByCourseIds(ids);
	}
	/**
	 * 根据知识点id批量查询题库信息
	 * @param 数组ids
	 * @return 题库集合
	 */
	@Override
	public List<ItemBank> getItemBankByKnowledgeIds(int[] ids) {
		// TODO Auto-generated method stub
		return itemBankMapper.getItemBankByKnowledgeIds(ids);
	}
	/**
	 * 上传试题模板
	 * 返回试题对象
	 */
	@Override
	public ItemBank getItemBankTemplate() {
		// TODO Auto-generated method stub
		return itemBankMapper.getItemBankTemplate();
	}
}
