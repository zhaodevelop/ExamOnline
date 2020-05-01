package com.ruanyuan.common.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 	转换日期工具类
 * @author 
 *
 */
public class DateUtils {
		/**
		 * 计算距离考试结束 还剩多长时间
		 * @param time   考试结束时间
		 * @return		返回 转换完成之后的 HH:mm:ss事件格式
		 * @throws ParseException   日期格式转换异常
		 */
		public static String examsTime(String endTime) throws ParseException {
			//时间格式
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			//转换成日期格式
			String nowTime = sdf.format(new Date());
			Date stime = sdf.parse(nowTime);
			Date etime = sdf.parse(endTime);
			//计算相差多少毫秒
			long millTime = etime.getTime()-stime.getTime();
			//调用方法  修改格式并且返回
			return converLongTimeToStr(millTime);
		}
		/**
		 * 计算距离考试开始 还剩多长时间
		 * @param staTime   考试开始时间
		 * @return		返回 转换完成之后的 HH:mm:ss事件格式
		 * @throws ParseException	日期格式转换异常
		 */
		public static String examsStaTime(String staTime) throws ParseException {
			//时间格式
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			//转换成日期格式
			String nowTime = sdf.format(new Date());
			Date ntime = sdf.parse(nowTime);
			Date stime = sdf.parse(staTime);
			//计算相差多少毫秒
			long millTime = stime.getTime()-ntime.getTime();
			//调用方法  修改格式并且返回
			return converLongTimeToStr(millTime);
		}
			/**
			 * 将毫秒转换成  HH:mm:ss格式
			 * @param time   毫秒
			 * @return  返回一个   HH:mm:ss格式  的时间类型
			 */
		public static String converLongTimeToStr(long time) {
			//定义  秒  分  时  等于多少毫秒
			int ss = 1000;
			int mi = ss * 60;
			int hh = mi * 60;
					
			//将毫秒 转换成   时分秒
			long hour = (time) / hh;
			long minute = (time - hour * hh) / mi;
			long second = (time - hour * hh - minute * mi) / ss;
			//换成  00：00：00格式  大于10  写成10：10：10
			String strHour = hour < 10 ? "0" + hour : "" + hour;
			String strMinute = minute < 10 ? "0" + minute : "" + minute;
			String strSecond = second < 10 ? "0" + second : "" + second;
			//拼接时间格式   00：00：00
			if (hour > 0) {
				return strHour + ":" + strMinute + ":" + strSecond;
			} else {
				 return "00"+":"+strMinute + ":" + strSecond;
			}
		}
}
