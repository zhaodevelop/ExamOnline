package com.ruanyuan.controller;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ruanyuan.common.utils.Page;
import com.ruanyuan.pojo.Role;
import com.ruanyuan.pojo.Middlej;
import com.ruanyuan.service.JournalService;
import com.ruanyuan.service.RoleService;
/**
 * 登录日志控制层
 * @author
 *
 */
@Controller
@RequestMapping("/journa")
public class JournalController {
		//引用日志业务类接口
		@Autowired
		@Qualifier("JournalService")
		private JournalService journalService;
		
		//引用用户业务类接口
		@Autowired
		@Qualifier("roleService")
		private RoleService roleService;
		
		//跳转到日志页面
		@RequestMapping("/journal.action")
		public String journalList(@RequestParam(defaultValue="1")Integer page,String rank,String userName,
				@RequestParam(defaultValue="6")Integer rows, String startTime,String endTime,Model model,HttpServletRequest request) {
			// 多条件查询
			//查询所有用户
			Page<Middlej> journal = journalService.getJournals(startTime, endTime, page, rows,userName,rank);
			System.out.println("----------list-----------"+journal);	
			List<Role> role = roleService.getAllRole();
			model.addAttribute("page", journal);
			//查询的条件开始时间结束时间
			request.setAttribute("startTime",startTime);
			request.setAttribute("endTime",endTime);
			request.setAttribute("role", role);
			request.setAttribute("userName", userName);
			request.setAttribute("rank", rank);
			//到主页面
			return "admin/journalManage";	
		}
		
		/**
		 * 根据日志id删除
		 */
		@RequestMapping("/deletejournal.action")
		@ResponseBody
		public String deletejournal(Integer id) {
			int rows =  journalService.deletejournal(id);
			if(rows > 0){
		        return "OK";
		    }else{
		        return "FAIL";
		    }
		}
		/**
		 * 批量删除
		 * @param ids
		 * @return
		 */
		@RequestMapping("/deletejournals.action")
		@ResponseBody
		public String deleteFieldByIds(int[] ids){
			
			List<Integer> list = new ArrayList<Integer>();
			for (int i = 0; i < ids.length; i++) {
				Integer a = ids[i];
				list.add(a);
			}			
			int rows = journalService.deletejournals(list);
			if(rows > 0){
		        return "OK";
		    }else{
		        return "FAIL";
		    }
		}
		/**
		 *下载
		 * @param response
		 * @throws Exception
		 */
		  @RequestMapping("/logindown.action")
		    public void getexcel2(HttpServletResponse response) throws Exception {
		        List<Middlej> midlist= journalService.getJournalByUser();
		        System.out.println(midlist);
		        //创建一个工作簿
		        @SuppressWarnings("resource")
				Workbook workbook=new XSSFWorkbook(); 
				//Workbook workbook= new XSSFWorkbook();
		        Sheet sheet = workbook.createSheet("登录日志");
		        //定义标题
		        String[] titles ={"日志编号","用户等级","用户名称","登录时间","登录ip"};
		        //行
		        Row row = sheet.createRow(0);
		        //写入标题行
		        for (int i =0;i<titles.length;i++){
		            Cell cell=row.createCell(i);
		            cell.setCellValue(titles[i]);
		        }
		        //根据标题查询行数
		        for (int i = 0;i<midlist.size();i++){
		            //穿件第i+1行
		            row=sheet.createRow(i+1);
		            //得到第一个字段的值
		            Middlej mid=midlist.get(i);
		            //创建第i个列
		            Cell idCell =row.createCell(0);
		            //写入第i+1行第i列  写入第一个字符
		            idCell.setCellValue(mid.getjId());		            
		            //写第二个字符
		            Cell nameCell =row.createCell(1);
		            nameCell.setCellValue(mid.getRoleName());
		            Cell countCell =row.createCell(2);
		            countCell.setCellValue(mid.getUserName());
		            Cell remCell =row.createCell(3);
		            remCell.setCellValue(mid.getjTime());
		            Cell resmCell =row.createCell(4);
		            resmCell.setCellValue(mid.getIp());
		        }
		        //创建文件名称
		        String fileName= URLEncoder.encode("用户登录日志.xlsx","UTF-8");
		        response.setContentType("application/octet-stream");
		        response.setHeader("Content-disposition","attachment;filename="+fileName);
		        response.setHeader("filename",fileName);      
		        //写入文件
		        workbook.write(response.getOutputStream());
		        
		      
		    }
		
		
}
