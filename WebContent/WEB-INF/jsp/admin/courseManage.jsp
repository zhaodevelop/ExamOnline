<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="itheima" uri="http://systop.com/common/"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
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
	<!-- 课程列表查询部分  start-->
	<div id="page-wrapper">
		<div class="row">
			<div class="col-lg-12">
				<h1 class="page-header" style="margin-top: 20px">课程管理</h1>
			</div>
			<!-- /.col-lg-12 -->
		</div>
		<!-- /.row -->
		<div class="panel panel-default">
			<div class="panel-body">
				<form class="form-inline" method="post"
					action="${pageContext.request.contextPath }/course/courseManage.action">
					<div class="form-group">
						<label for="customerName">课程名称</label> <input type="text"
							class="form-control" id="customerName" value="${courseName}"
							name="courseName" />
					</div>
					<div class="form-group">
						<label for="custIndustry">所属行业</label> <select
							class="form-control" id="fieldId" name="fieldId">
							<option value="">--请选择--</option>
							<c:forEach items="${fields}" var="item">
								<option value="${item.fieldId}"
									<c:if test="${item.fieldId == id}"> selected</c:if>>
									${item.fieldName}</option>
							</c:forEach>
						</select>
					</div>
					<button type="submit" class="btn btn-primary">查询</button>
					<a
						href="${pageContext.request.contextPath }/course/courseManage.action"
						class="btn btn-primary" data-target="#newCustomerDialog"
						onclick="clearCustomer()">查询全部</a>
				</form>
			</div>
		</div>
		<a href="#" class="btn btn-primary" data-toggle="modal"
			data-target="#addCourse" onclick="clearCourse()">新建</a> &nbsp;<a
			href="#" class="btn btn-danger btn-xs" style="width:80px;
				height:33px;line-height:33px;font-size:14px; data-toggle="modal" id="del">批量删除</a>
		<div class="row">
			<div class="col-lg-12">
				<div class="panel panel-default">
					<div class="panel-heading">课程信息列表</div>
					<!-- /.panel-heading -->
					<c:choose>
						<c:when test="${fn:length(page.result)>0}">
							<table class="table table-bordered table-striped">
								<thead>
									<tr>
										<th><input id="all" type="checkbox" />&nbsp;全选</th>
										<th>课程ID</th>
										<th>课程名称</th>
										<th>行业名称</th>
										<th>操作</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${page.result}" var="result">
										<tr style="text-align: center;">
											<td><input id="person" name="id" type="checkbox"
												value="${result.courseId}" /></td>
											<td>${result.courseId}</td>
											<td>${result.courseName}</td>
											<td>${result.field.fieldName}</td>
											<td><a href="#" class="btn btn-primary btn-xs"
												data-toggle="modal" data-target="#CourseEditDialog"
												onclick="editCourse(${result.courseId})"> 修改 </a> <a
												href="#" class="btn btn-danger btn-xs"
												onclick="deleteCourse(${result.courseId})"> 删除 </a></td>
										</tr>
									</c:forEach>

								</tbody>
							</table>
							<div class="col-md-12 text-right">
								<itheima:page
									url="${pageContext.request.contextPath }/course/courseManage.action" />
							</div>
							<!-- /.panel-body -->
						</c:when>
						<c:otherwise>
							<div style="text-align: center;margin: 50px 0;font-size:18px;">
									抱歉,没有找到课程信息
							</div>
						</c:otherwise>
					</c:choose>
				</div>
				<!-- /.panel -->
			</div>
			<!-- /.col-lg-12 -->
		</div>
	</div>
	<!-- 课程列表查询部分  end-->
	<!-- 创建课程模态框 -->
	<div class="modal fade" id="addCourse" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">新建课程信息</h4>
				</div>
				<div class="modal-body">
					<form class="form-horizontal" id="new_course_form">
						<div class="form-group">
							<label for="new_customerName" class="col-sm-2 control-label">
								课程名称 </label>
							<div class="col-sm-10">
								<input type="text" class="form-control" id="new_courseName"
									placeholder="课程名称" name="courseName" />
							</div>
						</div>
						<div class="form-group">
							<label for="new_custIndustry"
								style="float: left; padding: 7px 15px 0 28px;">所属行业</label>
							<div class="col-sm-10">
								<select class="form-control" id="new_fieldId" name="fieldId">
									<option value="">--请选择--</option>
									<c:forEach items="${fields}" var="item">
										<option value="${item.fieldId}">${item.fieldName}</option>
									</c:forEach>
								</select>
							</div>
						</div>
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					<button type="button" class="btn btn-primary"
						onclick="createCourse()">添加</button>
				</div>
			</div>
		</div>
	</div>
	<!-- 修改课程模态框 -->
	<div class="modal fade" id="CourseEditDialog" tabindex="-1"
		role="dialog" aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">修改课程信息</h4>
				</div>
				<div class="modal-body">
					<form class="form-horizontal" id="edit_course_form">
						<input type="hidden" id="edit_courseId" name="courseId" />
						<div class="form-group">
							<label for="edit_customerName" class="col-sm-2 control-label">课程名称</label>
							<div class="col-sm-10">
								<input type="text" class="form-control" id="edit_courseName"
									placeholder="课程名称" name="courseName" />
							</div>
						</div>
						<div class="form-group">
							<label for="new_custIndustry"
								style="float: left; padding: 7px 15px 0 27px;">所属行业</label>
							<div class="col-sm-10">
								<select class="form-control" id="edit_fieldId" name="fieldId">
									<option value="">--请选择--</option>
									<c:forEach items="${fields}" var="item">
										<option value="${item.fieldId}"
											<c:if test="${item.fieldId == thisfieldId}"> selected</c:if>>
											${item.fieldName}</option>
									</c:forEach>
								</select>
							</div>
						</div>
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					<button type="button" class="btn btn-primary"
						onclick="updateCourse()">保存修改</button>
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
			for(var j=0;j<oid.length;j++){
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
		var checkedNum=$("#person:checked").length;
		if(checkedNum==0){
			alert("请选择你要删除的课程信息");
			return false;
		}
		if(confirm('确定要删除所选课程吗?')) {
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
			$.get("${pageContext.request.contextPath }/course/deleteCourseByIds",
					ids,function(data){
				if(data=="OK"){
					window.location.href="${pageContext.request.contextPath}/course/courseManage.action";
				}else{
					alert("您所选中的课程中存在知识点，题库，试卷信息不能删除!");
					window.location.reload();
				}
			});
		}
	});
	//清空新建课程窗口中的数据
	function clearCourse() {
	    $("#new_courseName").val("");
	    $("#new_fieldId").val("")
	}
	// 创建课程
	function createCourse() {
	$.post("${pageContext.request.contextPath }/course/create.action",
	$("#new_course_form").serialize(),
		function(data){
	        if(data =="OK"){
	            alert("课程添加成功！");
	            window.location.reload();
	        }else if(data == "NULL"){
	            alert("没有填全信息，请重新添加！");
	            window.location.reload();
	        }else if(data == "REPEATE"){
	            alert("课程重名，请重新添加！");
	            window.location.reload();
	        }else{
	        	alert("课程添加失败！");
	            window.location.reload();
	        }
	        
	    });
	}
	// 通过课程id获取修改的课程信息
	function editCourse(id) {
	    $.ajax({
	        type:"get",
	        url:"${pageContext.request.contextPath }/course/getCourseById.action",
	        data:{"id":id},
	        success:function(data) {
	        	$("#edit_courseId").val(data.courseId);
	            $("#edit_courseName").val(data.courseName);
	            $("#edit_fieldId").val(data.field.fieldId);
	        }
	    });
	}
    // 执行修改课程操作
	function updateCourse() {
		$.post("${pageContext.request.contextPath }/course/updateCourse",
		$("#edit_course_form").serialize(),
		function(data){
			if(data=="OK"){
				alert("课程信息更新成功！");
				window.location.reload();
			}else if(data == "NULL"){
	            alert("没有填全信息，请重新修改！");
	            window.location.reload();
	        }else{
				alert("课程信息更新失败！");
				window.location.reload();
			}
		});
	}
	// 删除课程信息
	function deleteCourse(id) {
	    if(confirm('确实要删除该课程吗?')) {
	$.post("${pageContext.request.contextPath }/course/deleteCourse",{"id":id},
	function(data){
	            if(data =="OK"){
	            	window.location.href="${pageContext.request.contextPath}/course/courseManage.action";
	            }else{
	            	alert("该课程下有知识点、题库、试卷信息，不能删除！");
	                window.location.reload();
	            }
	        });
	    }
	}
</script>
</body>
</html>