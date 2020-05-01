package com.ruanyuan.controller;



import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ruanyuan.pojo.Average;

import com.ruanyuan.pojo.Field;
import com.ruanyuan.pojo.PassRate;
import com.ruanyuan.pojo.Statistics;
import com.ruanyuan.pojo.TestPaper;

import com.ruanyuan.service.FieldService;
import com.ruanyuan.service.StatisticsService;
import com.ruanyuan.service.TestPaperService;


import net.sf.json.JSONArray;
/**
 * 统计分析控制层
 * @author 
 *
 */
@Controller
@RequestMapping("/analysis")
public class AnalysisController {
	// 注入FieldService
	@Autowired
	private FieldService fieldService;
	// 注入TestPaperService
	@Autowired
	private TestPaperService testPaperService;
	// 注入StatisticsService
	@Autowired
	private StatisticsService statisticsService;
	//跳转到通过率etcharts
	@RequestMapping(value = "/passingRate.action")
	public String getPieCharts() {
		return "admin/passingRate";
	}
	/**
	 * 
	 * @return
	 */
	@RequestMapping(value = "/average.action")
	public String getaverage() {
		return "admin/average";
	}
	
	// 平均分
	@RequestMapping("/analysis.action")
	@ResponseBody
	public List<Average> analysis() {
//		行业平均分集合
		List<Average> avList = new ArrayList<Average>();
//		查询所有的行业
		List<Field> fields = fieldService.getFields();
		String fieldName=null;
		for(Field field:fields) {
			//平均分
			float average=0;
			fieldName = field.getFieldName();
			List<TestPaper> testpapers = testPaperService.getTestPaperByFieldId(field.getFieldId());
			List<Integer> list=new ArrayList<Integer>();
			float n = 0;
			float allscore = 0;
			//总人数
			float num=0;
			for(TestPaper testpaper:testpapers) {
				int tscore = testpaper.getTotalScore();
				double totalscore = (tscore)*(0.6);
				List<Statistics> statistics = statisticsService.getStatisticsByTestPaperId(testpaper.getTpId());
				for(Statistics sta:statistics) {
					float score = sta.getFraction();
					allscore=allscore+score;
					int uid = sta.getUser().getUserId();
					list.add(uid);
					if(score>totalscore) {
						n++;
					}
				}
			}
		
			num = list.size();
			if (num!=0) {
				average = Math.round(allscore/num);
			}
			avList.add(new Average(fieldName,average));
			Collections.sort(avList,Collections.reverseOrder());
			System.out.println("aaa"+avList);
		}
		for (Average e : avList) {
			System.out.println("avList集合为"+e);
		}
		//转为JSON格式传入前台
		JSONArray jsonArray = JSONArray.fromObject(avList.subList(0, 5));
		return jsonArray;
	}
	
	
	// 跳转到课程页面
	@RequestMapping("/analysis.action1")
	@ResponseBody
	public List<PassRate> analysis1() {
//		行业通过率集合
		List<PassRate> passList = new ArrayList<PassRate>();
//		查询所有的行业
		List<Field> fields = fieldService.getFields();
		String fieldName=null;
		
		for(Field field:fields) {
			//通过率
			float passrate=0;
			fieldName = field.getFieldName();
			List<TestPaper> testpapers = testPaperService.getTestPaperByFieldId(field.getFieldId());
			List<Integer> list=new ArrayList<Integer>();
			float n = 0;
			float allscore = 0;
			//总人数
			float num=0;
			for(TestPaper testpaper:testpapers) {
				int tscore = testpaper.getTotalScore();
				double totalscore = (tscore)*(0.6);
				List<Statistics> statistics = statisticsService.getStatisticsByTestPaperId(testpaper.getTpId());
				for(Statistics sta:statistics) {
					float score = sta.getFraction();
					allscore=allscore+score;
					int uid = sta.getUser().getUserId();
					list.add(uid);
					if(score>totalscore) {
						n++;
					}
				}
			}
			num = list.size();
			if (num!=0) {
				passrate = (float)(Math.round((n/num)*100)/(float)100);
			}
			passList.add(new PassRate(fieldName,passrate));
		     Collections.sort(passList, new Comparator<PassRate>() {
		            public int compare(PassRate arg0, PassRate arg1) {
		                if (arg0.getPassrate()>arg1.getPassrate()){
		                    return -1;
		                }else if(arg0.getPassrate()<arg1.getPassrate()){
		                    return 1;
		                }else{
		                    return 0;
		                }
		            }
		        });
		     System.out.println(passList);
//			for (int i = 0; i < passList.size(); i++) {
//				for(int j=0;j<passList.size()-1;j++){
//					float a;
//					if ((passList.get(j - 1)).compareTo(passList.get(j)) > 0) { // 比较两个整数的大小
//						a = passList.get(j - 1);
//						passList.set((j - 1), list.get(j));
//						passList.set(j, a);
//					}
//				}
//				}

//			 Collections.sort(passList, new Comparator<PassRate>() {  
//		            @Override  
//		            public int compare(PassRate o1, PassRate o2) {  
//		                int i = (int) (o2.getPassrate() - o1.getPassrate());  
//		                if(i == 0){  
//		                    return (int) (o2.getPassrate() - o1.getPassrate());  
//		                }  
//		                return i;
//		            }  
//		        }); 
			 for (PassRate e : passList) {
					System.out.println("passrate集合为"+e);
				}
			
		}
		
		//转为JSON格式传入前台
		JSONArray jsonArray = JSONArray.fromObject(passList.subList(0, 5));
		System.out.println("ddddddddddddddddddddd"+jsonArray);
		return jsonArray;
	}
}