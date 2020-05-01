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
<title>Insert title here</title>
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
</head>

<body>
	<!-- 日志查询部分  start-->
	<div id="page-wrapper">
		<div class="row">
			<div class="col-lg-12">
				<h1 class="page-header" style="margin-top: 20px">登录日志</h1>
			</div>
			<!-- /.col-lg-12 -->
		</div>
		<!-- /.row -->
		<div class="panel panel-default">
			<div class="panel-body">
				<form class="form-inline" method="post"
					action="${pageContext.request.contextPath }/journa/journal.action">
					
					<div class="form-group">
						<label for="customerFrom">用户等级</label> 
							<select	class="form-control" id="rank" name="rank" >
							<option value="">--请选择--</option>
							<c:forEach items="${role}" var="aa" >
								<option value="${aa.roleName}"
								       <c:if test="${aa.roleName == rank}">selected</c:if>>
								    ${aa.roleName}
								</option>
							</c:forEach>
						</select>
					</div>&nbsp;&nbsp;&nbsp;&nbsp;
					
						<div class="form-group">
						<label for="customerName">用户姓名</label> <input type="text"
							class="form-control" id="userName" value="${userName}"
							name="userName" />
					</div>&nbsp;
					
					<div class="form-group">
						<label for="customerName">起始时间</label> <input type="date"
							class="form-control" id="startTime" value="${startTime }"
							name="startTime" />
					</div>&nbsp;
					<div class="form-group">
						<label for="customerName">结束时间</label> <input type="date"
							class="form-control" id="endTime"  onchange="getBigTime()" value="${endTime }"
							name="endTime" />
					</div>&nbsp;&nbsp;&nbsp;&nbsp;
					<button type="submit" class="btn btn-primary">查询</button>&nbsp;&nbsp;&nbsp;&nbsp;
					<a href="${pageContext.request.contextPath }/journa/journal.action"
						class="btn btn-primary" data-target="#newCustomerDialog"
						onclick="clearCustomer()">查询全部</a>
				
				</form>
			</div>
		</div>
		</a> 

	
						<a href="${pageContext.request.contextPath }/journa/logindown.action"
						class="btn btn-primary" data-target="#newCustomerDialog"
						onclick="clearCustomer()">Excel导出</a>&nbsp;
		<a href="#" class="btn btn-primary" data-toggle="modal"
			id="del" style="background-color: #d9534f; border-color: #d43f3a; ">批量删除</a>
		<div class="row">
			<div class="col-lg-12">
				<div class="panel panel-default">
					<div class="panel-heading">登录日志列表</div>
					<!-- /.panel-heading -->
					<c:choose>
						<c:when test="${fn:length(page.result)>0}">
					<table class="table table-bordered table-striped">
						<thead>
							<tr>
								<th><input id="all" type="checkbox" />&nbsp;全选</th>
								<th>用户等级</th>
								<th>用户名称</th>
								<th>登录时间</th>
								<th>登录ip</th>
								<th>操作</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${page.result}" var="result">
								<tr style="text-align: center;">
									<td><input name="id" type="checkbox"
									value="${result.jId}" /></td>						
									<td>${result.roleName}</td>																	
									<td>${result.userName}</td>									
								<td>${fn:substring(result.jTime,0,16)}</td>
								<td>${result.ip}</td>
									<td> <a href="#"
										class="btn btn-danger btn-xs"
										onclick="deleteField(${result.jId})"> 删除 </a></td>
								</tr>
							</c:forEach>

						</tbody>
					</table>
					<div class="col-md-12 text-right">
						<itheima:page
							url="${pageContext.request.contextPath }/journa/journal.action" />
					</div>
					</c:when>
						<c:otherwise>
							<div style="text-align: center;margin: 50px 0;font-size:18px;">
									抱歉,没有找到日志信息
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
	<!-- 用户列表查询部分  end-->

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
	<!-- 编写js代码 -->
	<script type="text/javascript">
	//全选
	var oall=document.getElementById("all");
	var oid=document.getElementsByName("id");
	oall.onclick=function(){
		for(var i=0;i<oid.length;i++){
			//所有的选择框和全选一致
			oid[i].checked=oall.checked;		
		}
	};
	//点击复选框
	for(var i=0;i<oid.length;i++){
		oid[i].onclick=function(){
			//判断是否全部选中,遍历集合
			for(var j=0;j<id.length;j++){
			  if(oid[j].checked==false){
				oall.checked=false;
				break;
			  }else{
			    oall.checked=true;
			  }
			}
		};
	}
	//批量删除
	$("#del").click(function(){
			var ids="";
			var n=0;
			for(var i=0;i<oid.length;i++){				
				if(oid[i].checked==true){//选中为true
					var id=oid[i].value;
					if(n==0){
						ids+="ids="+id;
					}else{
						ids+="&ids="+id;
					}
					n++;
				}			
			}
			if (ids.length<1) {
				alert("请选择你要删除的日志!");
				return false;
			}
			if(confirm('确定要删除所选日志吗?')) {
				$.get("${pageContext.request.contextPath}/journa/deletejournals.action",
						ids,function(data){
					if(data=="OK"){
						//alert("批量删除成功!");
						window.location.reload();
						location.href="${pageContext.request.contextPath }/journa/journal.action";
					}else{
						//alert("批量删除失败!");
						location.href="${pageContext.request.contextPath }/journa/journal.action";
					}
				});
			}
	});
	// 判断查询的开始时间和结束时间的大小
	function getBigTime(){
		var startTime=$("#startTime").val();
		var start=new Date(startTime.replace("-", "/").replace("-", "/"));
		var endTime=$("#endTime").val();
		var end=new Date(endTime.replace("-", "/").replace("-", "/"));
		if(end<start){
		 	alert("开始时间不能大于结束时间,请重新选择时间！");
		 	$("#endTime").val("");
		}
	}

	// 删除登录信息
	function deleteField(id) {
	    if(confirm('确实要删除该日志吗?')) {
	    	
	$.post("${pageContext.request.contextPath }/journa/deletejournal.action",{"id":id},
	function(data){
	            if(data =="OK"){
	                //alert("日志删除成功！");
	        		location.href="${pageContext.request.contextPath }/journa/journal.action";
	            }else{
	                //alert("日志删除失败！");
	        		location.href="${pageContext.request.contextPath }/journa/journal.action";
	            }
	        });
	    }
	}
</script>
</body>
</html>