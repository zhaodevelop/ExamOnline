<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib prefix = "c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'result.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/admin/vote.css">
	<script type="text/javascript"  src="${pageContext.request.contextPath}/js/admin/jquery-1.11.3.min.js"></script>
	<script type="text/javascript"  src="${pageContext.request.contextPath}/js/admin/echarts.js"></script>
	<script type="text/javascript">
		$(function(){
			    var myCharts = echarts.init(document.getElementById('main'));
			    //用来盛放X轴坐标值，即节目名称
				var spName=[];  
				//用来盛放投票数  
		        var spcount=[];  
				//通过ajax动态填充数据
				$.ajax({
		  			url:"${pageContext.request.contextPath}/analysis/analysis.action1",
		  			type:"post",
		  			dataType:"json",
		  			success:function(data){
		  				$.each(data,function(i,values){
		  					console.log(data);
		  					spName.push(data[i].fieldName);  
		  					spcount.push(Math.round((data[i].passrate)*100));
		  			})
		  		
		           var option= {
		           		  //标题            
						  title: {
						  		//标题内容
						        text: '行业通过率前五图示',
						        x:'center'
						  },
						  color: ['#3398DB'],
						  //工具箱
						  toolbox: {
						        show: true,
						        feature: {
						            dataZoom:{
						            show: true
						            },
						            //还原
						            restore: {
						                show: true
						            },
						            //下载
						            saveAsImage: {
						                show: true
						            }
						        }
						    },
						    //提示框
						    tooltip : {			
						        trigger: 'item',
						        //坐标轴指示器
						        // 默认为直线，可选为：'line'为直线 | 'shadow'为阴影					        		
						        axisPointer : {         
						            type : 'line'        
						        },
						    	formatter: '{b}:{c}%'						   
						    },
						    xAxis: {//横坐标
						        type: 'category',
						        //data的内容为我们获取的参数
						        data: spName,
						        "axisTick": {
						            "alignWithLabel": true
						        },
						        "nameTextStyle": {
						            "color": "#82b0ec"
						        },
						        "axisLine": {
						            "lineStyle": {
						                "color": "#82b0ec"
						            }
						        },
						        "axisLabel": {
						            "textStyle": {
						                "color": "#000"
						            },
						            margin: 30
						        }
						    },
						    yAxis: {//纵坐标
						        type: 'value',
						        "axisLabel": {
						            "textStyle": {
						                "color": "#82b0ec"
						            },
						            // "formatter": "{value}%"
						        },
						    },
						    series: [{
					            type: 'pictorialBar',
					            symbolSize: [60, 16],
					            symbolOffset: [0, -10],
					            symbolPosition: 'end',
					            z: 12,
						    	//data的内容为我们获取的参数
						        data: spcount,
						        
						        //标签，主要是用来满足需求：要在坐标轴的每个点上将各自对应的数据显示下来
						        label: {
					                "normal": {
					                    "show": true,
					                    "position": "inside",
					                    formatter: '{c}%',
					                    fontSize: 20,
					                    fontWeight: 'bold',
					                    color: '#34DCFF'
					              },
					            },
					            "itemStyle": {
				                    "color": "#2DB1EF"
				                },
				                
						       // type: 'bar'
						    },
						    {
					            name: '',
					            type: 'pictorialBar',
					            symbolSize: [60, 16],
					            symbolOffset: [0, 10],
					            // "barWidth": "20",
					            z: 12,
					            data: spcount,
					            "itemStyle": {
				                    "color": "#2DB1EF"
				                },
				                formatter: '{c}%'
					        },
					        {
					            name: '',
					            type: 'pictorialBar',
					            symbolSize: [90, 30],
					            symbolOffset: [0, 20],
					            z: 10,
					            itemStyle: {
					                normal: {
					                    color: 'transparent',
					                    borderColor: '#2EA9E5',
					                    borderType: 'solid',
					                    borderWidth: 1
					                }
					            },
					            data: spcount,
					            formatter: '{c}%'
					            
					           
					        },					    
						       {
						            type: 'bar',
						            itemStyle: {
						                normal: {
						                    //color: '#14b1eb',
						                    opacity: .9
						                }
						            },
						            //silent: true,
						            "barWidth": "60",
						            barGap: '10%', // Make series be overlap
						            barCateGoryGap: '10%',
						            data: spcount,
						            formatter: '{c}%'
						        }						    						   						    
						    ]
						};
		  				console.log(spcount);
						myCharts.setOption(option);
					}
  				})
		})		
	    </script>
  </head>
  
  <body style="background:#fff;">
    <div style="position: relative;">
  
  	<center>
		<table>
			<tr>
			<td><div id="main" style="width:1100px;height:600px;position: absolute;top:0%;left:10%;z-index:1;">
			</div></td>
			</tr>
		</table>
	</center>
	<a href="${pageContext.request.contextPath }/analysis/average.action" style="width:200px;height:28px;line-height:28px;position: absolute;top:5%;left:2%;text-align:center;background-color:#1E9FFF;border-radius: 20px;z-index:2;color:#000;text-decoration:none">
					切换行业平均分</a>
	</div>	
  </body>
</html>
