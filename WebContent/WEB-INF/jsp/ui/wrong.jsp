<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="itheima" uri="http://systop.com/common/"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>做题记录</title>
<link rel="shortcut icon" href="${pageContext.request.contextPath}/images/ui/favicon.ico">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/ui/font-awesome/font-awesome.min.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/ui/monokai-sublime.min.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/ui/bootstrap/bootstrap.min.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/ui/katex/katex.min.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/ui/video-js.min.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/ui/styles.css?=2016121272249">
<link rel="shortcut icon"
	href="${pageContext.request.contextPath}/images/ui/favicon.ico">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/PC/css/home.css"
	type="text/css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/PC/css/list.css"
	type="text/css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/PC/css/center.css"
	type="text/css">

<script type="text/javascript"
	src="${pageContext.request.contextPath}/PC/js/jquery-1.7.min.js"></script>
<!-- 引入css样式文件 -->
<!-- Bootstrap Core CSS -->
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/css/admin/bootstrap.min.css">
<!-- MetisMenu CSS -->
<link
	href="${pageContext.request.contextPath }/css/admin/metisMenu.min.css"
	rel="stylesheet" type="text/css" />
<!-- DataTables CSS -->
<link
	href="${pageContext.request.contextPath }/css/admin/dataTables.bootstrap.css"
	rel="stylesheet" type="text/css" />
<!-- Custom CSS -->
<link
	href="${pageContext.request.contextPath }/css/admin/sb-admin-2.css"
	rel="stylesheet" type="text/css" />
<!-- Custom Fonts -->
<link
	href="${pageContext.request.contextPath }/css/admin/font-awesome.min.css"
	rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath }/css/admin/boot-crm.css"
	rel="stylesheet" type="text/css" />
	<script type="text/javascript">
$(function(){
	$("#wrong").css("background-color","#bbb");
	$("#userPass").removeAttr();
	$("#userTitle").removeAttr();

})
</script>
</head>
<style>
.getWrong {
	width: 900px;
	display: none;
	position: absolute;
	top:30px;
	left:200px;
	background-color: #fff;
	background-clip: padding-box;
	border: 1px solid rgba(0, 0, 0, .2);
	border-radius: 6px;
	outline: 0;
	margin: 30px auto;
	transform: translate(0,0);
	box-shadow: 0 5px 15px rgba(0,0,0,.5);
	OVERFLOW-Y: auto; OVERFLOW-X:hidden;
	z-index:10000;
}

.modal-header {
	padding: 15px;
	border-bottom: 1px solid #e5e5e5;
	overflow: auto;
}

.close {
	-webkit-appearance: none;
	padding: 0;
	cursor: pointer;
	background: 0 0;
	border: 0;
	float: right;
    font-size: 21px;
    font-weight: 700;
    line-height: 1;
    color: #000;
    text-shadow: 0 1px 0 #fff;
    opacity: .2;
}
.modal-title{
	font-weight: bold;
	margin: 0;
    line-height: 1.42857143;
    font-size: 18px;
    color: inherit;
    font-family: inherit;
    box-sizing: border-box;
}
.detailWrong{
	padding-top: 20px;
    height: 500px;
    margin: 0 auto;
    margin-bottom: 20px;
}
.itemBankWrong{
	border:0;
	border-radius:5px;
	background-color:rgba(241,241,241,.98);
	padding: 10px;
	resize: none;
}
.nav navbar-nav{ background:#F00; color:#FFF} 
}
</style>

<body>
<!-- 页头 start-->
	<nav class="navbar navbar-default navbar-fixed-top header" style="background-color:#121a2a ;">
		<div class="container">
			<div class="collapse navbar-collapse" id="header-navbar-collapse">
				<ul class="nav navbar-nav">
					<li class="dropdown "><a class=""
						href="${pageContext.request.contextPath}/ui/ui">在线考试系统</a></li>
						<li class="dropdown "><a class=""
						href="${pageContext.request.contextPath}/ui/ui">返回首页</a></li>
				</ul>
			</div>
		</div>
	</nav>
	<!-- 页头 end-->
<div class="shang">
		<div class="dn">
			<div class="z1">
			
			</div>
		</div>
	</div>
	
	<div class="content">
		<!-- 导入左侧列表 -->
		<jsp:include page="center_left.jsp"></jsp:include>
		<div class="right_list">
			<div class="right_title">
				<span>历史做题记录</span>
			</div>
			<div class="right_select"></div>
	<!-- 知识点列表查询部分  start-->
	<div id="page-wrapper">
		<div class="panel panel-default">
			<div class="panel-body">
				<form class="form-inline" method="post"
					action="${pageContext.request.contextPath }/center/wrong.action">
					<div class="form-group">
						<label for="tpName">试卷名称</label> <input type="text"
							class="form-control" id="tpName" value="${tpName}"
							name="tpName" />
					</div>
					<div class="form-group">
						<label for="customerName">起始时间</label> <input type="date"
							class="form-control" id="startTime" value="${startTime }"
							name="startTime" />
					</div>&nbsp;
					<div class="form-group">
						<label for="customerName">结束时间</label> <input type="date"
							class="form-control" id="endTime" value="${endTime }" onchange="getBigTime()"
							name="endTime" />
					</div>&nbsp;&nbsp;&nbsp;&nbsp;
					<button type="submit" class="btn btn-primary" style="margin-bottom: -22px;">查询</button>
					<a href="${pageContext.request.contextPath }/center/wrong.action"
						class="btn btn-primary" data-target="#newKnowledgeDialog"
						onclick="clearKnowledge()" style="margin-bottom: -22px;">查询全部</a>
				</form>
			</div>
		</div>
		<div class="row">
			<div class="col-lg-12">
				<div class="panel panel-default">
					<div class="panel-heading">历史做题记录</div>
					<!-- /.panel-heading -->
				<c:choose>
					<c:when test="${fn:length(page.result)>0}">
					<table class="table table-bordered table-striped">
						<thead>
							<tr>
							<th>试卷名称</th>
							<th>分数</th>
							<th>错题数量</th>
							<th>提交日期</th>
							<th>操作</th>
						</tr>
						</thead>
						<tbody>
						<c:forEach items="${page.result}" var="result">
							<tr style="text-align: center;">
								<td>${result.tpName}
								<td>${result.fraction}</td>
								<td>${result.wrongNumber}</td>
								<td>${fn:substring(result.submissionTime,0,16)}</td>
								<td>
									
									<input type="button" value="错题详情"
										style="color: #fff; background-color: #337ab7; border-color: #2e6da4; 
										padding: 1px 5px; font-size: 12px; line-height: 1.5; border-radius: 3px;"
										onclick="getWrongById('${result.itemBankIds}')"/>
								</td>
							</tr>
						</c:forEach>

						</tbody>
					</table>
					<div class="col-md-12 text-right">
						<itheima:page
							url="${pageContext.request.contextPath }/center/wrong.action" />
					</div>
					</c:when>
					<c:otherwise>
							<div style="text-align: center;margin: 50px 0;font-size:18px;">
									抱歉,没有找到考试信息
							</div>
					</c:otherwise>
				</c:choose>
					<!-- /.panel-body -->
				</div>
				<!-- /.panel -->
			</div>
			<!-- /.col-lg-12 -->
		</div>
	</div>
	</div>
	</div>


	<!-- 引入js文件 -->
	<!-- jQuery -->
	<script
		src="${pageContext.request.contextPath }/js/admin/jquery-1.11.3.min.js"></script>
	<!-- Bootstrap Core JavaScript -->
	<script
		src="${pageContext.request.contextPath }/js/admin/bootstrap.min.js"></script>
	<!-- Metis Menu Plugin JavaScript -->
	<script
		src="${pageContext.request.contextPath }/js/admin/metisMenu.min.js"></script>
	<!-- DataTables JavaScript -->
	<script
		src="${pageContext.request.contextPath }/js/admin/jquery.dataTables.min.js"></script>
	<script
		src="${pageContext.request.contextPath }/js/admin/dataTables.bootstrap.min.js"></script>
	<!-- Custom Theme JavaScript -->
	<script
		src="${pageContext.request.contextPath }/js/admin/sb-admin-2.js"></script>
	<!-- 弹出错题详情 -->
	<div class="getWrong" id="getWrongById">
		<div class="modal-header">
			<button class="close" id="close" onclick="clcoseGetWrong()">
				<span>×</span>
			</button>
			<h4 class="modal-title">错题信息</h4>
			<div class="detailWrong" id="detailWrong"></div>
		</div>
	</div>
	 <!-- 页脚 -->
	<div class="text-center copyright" style="position: absolute;bottom:0px;width:100%">
				<span style="font-size: 12px;">Copyright @2020-2030 在线考试</span>

	</div>
</body>
<script type="text/javascript">

//判断查询的开始时间和结束时间的大小
function getBigTime(){
	var startTime=$("#startTime").val();
	var start=new Date(startTime.replace("-", "/").replace("-", "/"));
	var endTime=$("#endTime").val();
	var end=new Date(endTime.replace("-", "/").replace("-", "/"));
	if(end<start){
	 	alert("亲~开始时间不能大于结束时间,请重新输入");
	 	$("#endTime").val("");
	}
}
//错题信息
function getWrongById(itemBankIds){
		$("#getWrongById").css("display","block");
		$("#detailWrong").empty();
		$.ajax({
			async:false,
			type:"post",
	        url:"${pageContext.request.contextPath}/center/wrongItemBank/getItemBanks.action",
	        data:{"itemBankIds":itemBankIds},
	        success:function(data) {
	        	if(data!="noWrong"){
	        		var result = eval(data);
					for(var i=0;i<result.length;i++){
						var question = "<span>问题："+result[i].question+"&nbsp;&nbsp;(正确答案:"+result[i].Answer+")</span><br><br>";
						var optionA = result[i].Wrong == "A"? "<span style='color:red'>A."+result[i].optionA+"</span>&nbsp;&nbsp;&nbsp;&nbsp;": "<span>A."+result[i].optionA+"</span>&nbsp;&nbsp;&nbsp;&nbsp;";
						var optionB = result[i].Wrong == "B"? "<span style='color:red'>B."+result[i].optionB+"</span>&nbsp;&nbsp;&nbsp;&nbsp;": "<span>B."+result[i].optionB+"</span>&nbsp;&nbsp;&nbsp;&nbsp;";
						var optionC = result[i].Wrong == "C"? "<span style='color:red'>C."+result[i].optionC+"</span>&nbsp;&nbsp;&nbsp;&nbsp;": "<span>C."+result[i].optionC+"</span>&nbsp;&nbsp;&nbsp;&nbsp;";
						var optionD = result[i].Wrong == "D"? "<span style='color:red'>D."+result[i].optionD+"</span>&nbsp;&nbsp;&nbsp;&nbsp;": "<span>D."+result[i].optionD+"</span>&nbsp;&nbsp;&nbsp;&nbsp;";
		        		$("#detailWrong").append("<div class='WrongItemBank' id='WrongItemBank'></div>");
		        		$("#WrongItemBank").append("<div class='itemBankWrong' id='itemBankWrong'>"+question+optionA+optionB+optionC+optionD+"</div><br>");
		        	}
	        	}else{
	        		$("#detailWrong").append("<div class='WrongItemBank' id='WrongItemBank'><span>太棒了,该试卷你没有做错题~</span></div>");
	        	}
	        }
		})
}
function clcoseGetWrong(){
	$("#getWrongById").css("display","none");
}
</script>
</html>