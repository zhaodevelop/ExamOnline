<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="itheima" uri="http://systop.com/common/"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>试卷管理</title>
<!-- 引入css样式文件 -->
<!-- Bootstrap Core CSS -->
<link
	href="${pageContext.request.contextPath}/css/admin/bootstrap.min.css"
	rel="stylesheet" />
<!-- MetisMenu CSS -->
<link
	href="${pageContext.request.contextPath}/css/admin/metisMenu.min.css"
	rel="stylesheet" />
<!-- DataTables CSS -->
<link
	href="${pageContext.request.contextPath}/css/admin/dataTables.bootstrap.css"
	rel="stylesheet" />
<!-- Custom CSS -->
<link href="${pageContext.request.contextPath}/css/admin/sb-admin-2.css"
	rel="stylesheet" />
<!-- Custom Fonts -->
<link
	href="${pageContext.request.contextPath}/css/admin/font-awesome.min.css"
	rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/admin/boot-crm.css"
	rel="stylesheet" type="text/css" />
</head>
<body>

	<!-- 客户列表查询部分  start-->
	<div id="page-wrapper" style="margin: 0">
		<div class="row">
			<div class="col-lg-12">
				<h1 class="page-header" style="margin-top: 20px">试卷管理</h1>
			</div>
		</div>
		<!-- /.row -->
		<div class="panel panel-default">
			<div class="panel-body">
				<form class="form-inline" method="post"
					action="${pageContext.request.contextPath }/testPaper/testPaperManager.action">
					<div class="form-group">
						<label for="customerName">试卷名称</label> <input type="text"
							class="form-control" id="tpName" value="${tpName }" name="tpName" />
					</div>
					<div class="form-group">
						<label for="customerName">组卷人名称</label> <input type="text"
							class="form-control" id="userName" value="${userName }"
							name="userName" />
					</div>
					<div class="form-group">
						<label for="customerName">起始时间</label> <input type="date"
							class="form-control" id="startTime" value="${startTime }"
							name="startTime" />
					</div>
					<div class="form-group">
						<label for="customerName">结束时间</label> <input type="date"
							class="form-control" id="endTime" value="${endTime }" onchange="getBigTime()"
							name="endTime" />
					</div>
					<br /> <br />
					<div class="form-group">
						<label for="customerFrom">所属行业</label> <input type="hidden"
							id="hiddenField" value="${fieldId}" /> <select
							class="form-control" id="fieldId" name="fieldId"
							onchange="selectFiled()">
							<option value="">--请选择--</option>
							<c:forEach items="${fieldList}" var="field">
								<option value="${field.fieldId}">${field.fieldName}</option>
							</c:forEach>
						</select>
					</div>
					<div class="form-group">
						<label for="custIndustry">所属课程</label> <input type="hidden"
							id="hiddenCourse" value="${courseId}" /> <select
							class="form-control" id="courseId" name="courseId">
							<option value="">--请选择--</option>
						</select>
					</div>
					<button type="submit" class="btn btn-primary">查询</button>
					<a
						href="${pageContext.request.contextPath }/testPaper/testPaperManager.action"
						class="btn btn-primary">查询全部</a>
				</form>
			</div>
		</div>
		<a href="#" class="btn btn-primary" data-toggle="modal"
			data-target="#newCustomerDialog" onclick="clearTestPaper()">新建</a>
		<div class="row">
			<div class="col-lg-12">
				<div class="panel panel-default">
					<div class="panel-heading">试卷信息列表</div>
					<!-- /.panel-heading -->
					<c:choose>
						<c:when test="${fn:length(page.result)>0}">
							<table class="table table-bordered table-striped" style="text-align: center;">
								<thead>
									<tr>
										<th><input type="checkbox" id="allTest"
											onclick="setAllOrNo()" />全选</th>
										<th>试卷名称</th>
										<th>试卷所属行业</th>
										<th>试卷所属课程</th>
										<th>考试开始时间</th>
										<th>考试结束时间</th>
										<th>组卷人</th>
										<th>操作</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${page.result}" var="row">
										<tr>
											<td><input type="checkbox" name="tpCheck"
												value="${row.tpId}" id="tpCheck" /></td>
											<td>${row.tpName}</td>
											<td>${row.field.fieldName}</td>
											<td>${row.course.courseName}</td>
											<td>${fn:substring(row.startTime,0,16)}</td>
											<td>${fn:substring(row.endTime,0,16)}</td>
											<td>${row.user.userName}</td>
											<td><a href="#" class="btn btn-primary btn-xs"
												data-toggle="modal" data-target="#testPaperDialog"
												onclick="getTestPaperById(${row.tpId})">详情</a> <a href="#"
												class="btn btn-primary btn-xs" data-toggle="modal"
												data-target="#testPaperEditDialog"
												onclick="editTestPaper(${row.tpId})">修改</a> <a href="#"
												class="btn btn-danger btn-xs"
												onclick="deleteTestPaper(${row.tpId})">删除</a></td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						
							<div class="col-md-12 text-right">
								<itheima:page
									url="${pageContext.request.contextPath }/testPaper/testPaperManager.action" />
							</div>
						</c:when>
						<c:otherwise>
							<div style="text-align: center;margin: 50px 0;font-size:18px;">
									抱歉,还没有试卷信息
							</div>
						</c:otherwise>
					</c:choose>
						<button class="btn btn-primary" id="delMore"
								style="background-color: #d9534f; border-color: #d43f3a; position: absolute; top: -34px; left: 80px">批量删除</button>
					<!-- /.panel-body -->
				</div>
				<!-- /.panel -->
			</div>
			<!-- /.col-lg-12 -->
		</div>
	</div>
	<!-- 创建客户模态框 -->
	<div class="modal fade" id="newCustomerDialog" tabindex="-1"
		role="dialog" aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">新建试卷</h4>
				</div>
				<div class="modal-body">
					<div class="form-horizontal" id="new_testPaper_form">
						<div class="form-group">
							<label for="new_customerName" class="col-sm-2 control-label">
								试卷名称 </label>
							<div class="col-sm-10">
								<input type="text" class="form-control" id="a_tpName"
									placeholder="试卷名称" name="tpName" />
							</div>
						</div>
						<div class="form-group">
							<label for="new_customerFrom"
								style="float: left; padding: 7px 15px 0 27px;">所属行业</label>
							<div class="col-sm-10">
								<select class="form-control" id="a_field" name="fieldId"
									onchange="addFieldChange()">
									<option value="">--请选择--</option>
									<c:forEach items="${fieldList}" var="field">
										<option value="${field.fieldId}"
											<c:if test="${field.fieldId == fieldId}">selected</c:if>>
											${field.fieldName }</option>
									</c:forEach>
								</select>
							</div>
						</div>
						<div class="form-group">
							<label for="new_custIndustry"
								style="float: left; padding: 7px 15px 0 27px;">所属课程</label>
							<div class="col-sm-10">
								<select class="form-control" id="a_course" name="courseId">
									<option value="">--请选择--</option>
								</select>
							</div>
						</div>
						<div class="form-group">
							<label for="new_linkMan" class="col-sm-2 control-label">开考时间</label>
							<div class="col-sm-10">
								<input type="datetime-local" class="form-control"
									id="a_startTimeLocal" placeholder="开考时间" name="startTime" />
							</div>
						</div>
						<div class="form-group">
							<label for="new_phone" class="col-sm-2 control-label">结束时间</label>
							<div class="col-sm-10">
								<input type="datetime-local" class="form-control"
									id="a_endTimeLocal" placeholder="结束时间" name="endTime" />
							</div>
						</div>
						<div class="form-group">
							<label for="new_mobile" class="col-sm-2 control-label">试卷题数</label>
							<div class="col-sm-10">
								<input type="text" class="form-control" id="a_number"
									placeholder="试卷题数" name="number" />
							</div>
						</div>
						<div class="form-group">
							<label for="new_zipcode" class="col-sm-2 control-label">单题分数</label>
							<div class="col-sm-10">
								<input type="text" class="form-control" id="a_oneBranch"
									placeholder="试题单题分数" name="oneBranch" />
							</div>
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					<button type="button" class="btn btn-primary"
						onclick="createTestPaper()">生成试卷</button>
				</div>
			</div>
		</div>
	</div>
	<!-- 修改试卷信息模态框 -->
	<div class="modal fade" id="testPaperEditDialog" tabindex="-1"
		role="dialog" aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">修改试卷信息</h4>
				</div>
				<div class="modal-body">
					<form class="form-horizontal" id="edit_testPaper_form">
						<input type="hidden" id="edit_cust_id" name="cust_id" />
						<div class="form-group">
							<label for="edit_customerName" class="col-sm-2 control-label">试卷名称</label>
							<div class="col-sm-10">
								<input type="hidden" name="tpId" id="u_hideTestId" /> <input
									type="text" class="form-control" id="u_tpName"
									placeholder="试卷名称" name="tpName" />
							</div>
						</div>
						<div class="form-group">
							<label for="edit_customerFrom"
								style="float: left; padding: 7px 15px 0 27px;">所属行业</label>
							<div class="col-sm-10">
								<select class="form-control" id="u_Field" name="fieldId"
									onchange="updateFieldChange()">
								</select>
							</div>
						</div>
						<div class="form-group">
							<label for="edit_custIndustry"
								style="float: left; padding: 7px 15px 0 27px;">所属课程</label>
							<div class="col-sm-10">
								<select class="form-control" id="u_Course" name="courseId">
								</select>
							</div>
						</div>
						<div class="form-group">
							<label for="edit_linkMan" class="col-sm-2 control-label">开考时间</label>
							<div class="col-sm-10">
								<input type="datetime-local" class="form-control"
									id="u_startTime" placeholder="开考时间" name="startTime" />
							</div>
						</div>
						<div class="form-group">
							<label for="edit_phone" class="col-sm-2 control-label">结束时间</label>
							<div class="col-sm-10">
								<input type="datetime-local" class="form-control" id="u_endTime"
									placeholder="结束时间" name="endTime" />
							</div>
						</div>
						<div class="form-group">
							<label for="edit_mobile" class="col-sm-2 control-label">试卷题数</label>
							<div class="col-sm-10">
								<input type="text" class="form-control" id="u_number"
									placeholder="试卷题数" name="number" />
							</div>
						</div>
						<div class="form-group">
							<label for="edit_mobile" class="col-sm-2 control-label">单题分数</label>
							<div class="col-sm-10">
								<input type="text" class="form-control" id="u_oneBranch"
									placeholder="试题单题分数" name="oneBranch" />
							</div>
						</div>
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					<button type="button" class="btn btn-primary"
						onclick="updateTestPaper()">保存修改</button>
				</div>
			</div>
		</div>
	</div>
	<!-- 试卷详情模态框 -->
	<div class="modal fade" id="testPaperDialog" tabindex="-1"
		role="dialog" aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">试卷详细信息</h4>
				</div>
				<div class="modal-body">
					<form class="form-horizontal" id="edit_testPaper_form">
						<input type="hidden" id="edit_cust_id" name="cust_id" />
						<div class="form-group">
							<label for="edit_customerName" class="col-sm-2 control-label">试卷名称</label>
							<div class="col-sm-10">
								<input class="form-control" readonly id="s_tpName"
									style="border: 0"></input>
							</div>
						</div>
						<div class="form-group">
							<label for="edit_customerFrom"
								style="float: left; padding: 7px 15px 0 27px;">所属行业</label>
							<div class="col-sm-10">
								<input class="form-control" readonly id="s_field"
									style="border: 0"></input>
							</div>
						</div>
						<div class="form-group">
							<label for="edit_custIndustry"
								style="float: left; padding: 7px 15px 0 27px;">所属课程</label>
							<div class="col-sm-10">
								<input class="form-control" readonly id="s_course"
									style="border: 0"></input>
							</div>
						</div>
						<div class="form-group">
							<label for="edit_linkMan" class="col-sm-2 control-label">开考时间</label>
							<div class="col-sm-10">
								<input class="form-control" readonly id="s_startTime"
									style="border: 0"></input>
							</div>
						</div>
						<div class="form-group">
							<label for="edit_phone" class="col-sm-2 control-label">结束时间</label>
							<div class="col-sm-10">
								<input class="form-control" readonly id="s_endTime"
									style="border: 0"></input>
							</div>
						</div>
						<div class="form-group">
							<label for="edit_phone" class="col-sm-2 control-label">创建时间</label>
							<div class="col-sm-10">
								<input class="form-control" readonly id="s_tpTime"
									style="border: 0"></input>
							</div>
						</div>
						<div class="form-group">
							<label for="edit_mobile" class="col-sm-2 control-label">试卷题数</label>
							<div class="col-sm-10">
								<input class="form-control" readonly id="s_number"
									style="border: 0"></input>
							</div>
						</div>
						<div class="form-group">
							<label for="edit_mobile" class="col-sm-2 control-label">单题分数</label>
							<div class="col-sm-10">
								<input class="form-control" readonly id="s_oneBranck"
									style="border: 0"></input>
							</div>
						</div>
						<div class="form-group">
							<label for="edit_mobile" class="col-sm-2 control-label">试卷总分</label>
							<div class="col-sm-10">
								<input class="form-control" readonly id="s_totalScorce"
									style="border: 0"></input>
							</div>
						</div>
						<div class="form-group">
							<label for="edit_zipcode" class="col-sm-2 control-label">组卷人</label>
							<div class="col-sm-10">
								<input class="form-control" readonly id="s_userName"
									style="border: 0"></input>
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
	<!-- 客户列表查询部分  end-->
	<!-- 引入js文件 -->
	<!-- jQuery -->
	<script
		src="${pageContext.request.contextPath}/js/admin/jquery-1.11.3.min.js"></script>
	<!-- Bootstrap Core JavaScript -->
	<script
		src="${pageContext.request.contextPath}/js/admin/bootstrap.min.js"></script>
	<!-- Metis Menu Plugin JavaScript -->
	<script
		src="${pageContext.request.contextPath}/js/admin/metisMenu.min.js"></script>
	<!-- DataTables JavaScript -->
	<script
		src="${pageContext.request.contextPath}/js/admin/jquery.dataTables.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/js/admin/dataTables.bootstrap.min.js"></script>
	<!-- Custom Theme JavaScript -->
	<script src="${pageContext.request.contextPath}/js/admin/sb-admin-2.js"></script>
	<!-- 编写js代码 -->
<script type="text/javascript">
//全选/全部选
function setAllOrNo(){
	var allTest = document.getElementById("allTest");
	var tpCheck = document.getElementsByName("tpCheck");
	if(allTest.checked == false){
	for (var i = 0; i < tpCheck.length; i++) {
		tpCheck[i].checked = false;
		}
	}else{
	for (var i = 0; i < tpCheck.length; i++) {
		tpCheck[i].checked = true;
		}
	}
}

//行业和课程联动(查询)
function selectFiled(fieldId){
	var options=$("#fieldId option:selected");
    var value=options.val();
    $.ajax({
	async:false,
		type:"post",
		dataType:"json",
		url:"${pageContext.request.contextPath}/testPaper/getCourses.action",
		data:{fieldId:value}, //二级产品类别的父ID
		success:function(data){
			$("#courseId").empty();
			$("#courseId").append("<option value= ''>--请选择--</option>")
			var result = eval(data);
			for(var i=0;i<result.length;i++){
				$("#courseId").append("<option value='"+result[i].courseId+"'>"+result[i].courseName+"</option>");
			}
		}
	})
}
//清空新建试卷窗口中的数据
function clearTestPaper() {
	$("#a_tpName").val("");
    $("#a_field").val("");
    $("#a_course").val("");
    $("#a_startTimeLocal").val("");
    $("#a_endTimeLocal").val("");
    $("#a_number").val("");
    $("#a_oneBranch").val("");
    $("#a_userName").val("");
}
// 行业联动课程(添加)
function addFieldChange(){
   var options=$("#a_field option:selected");
   var value=options.val();
   $.ajax({
		async:false,
		type:"post",
		dataType:"json",
		url:"${pageContext.request.contextPath}/testPaper/getCourses.action",
		data:{fieldId:value}, //二级产品类别的父ID
		success:function(data){
			$("#a_course").empty();
			$("#a_course").append("<option value= ''>--请选择--</option>")
			// 把JSON数据转成数组
			var result = eval(data);
			for(var i=0;i<result.length;i++){
				$("#a_course").append("<option value='"+result[i].courseId+"'>"+result[i].courseName+"</option>");
			}
		}
	})
}
//行业联动课程(修改)
function updateFieldChange(){
   var options=$("#u_Field option:selected");
   var value=options.val();
   $.ajax({
		async:false,
		type:"post",
		dataType:"json",
		url:"${pageContext.request.contextPath}/testPaper/getCourses.action",
		data:{fieldId:value}, //二级产品类别的父ID
		success:function(data){
			$("#u_Course").empty();
			$("#u_Course").append("<option value= ''>--请选择--</option>")
			// 把JSON数据转成数组
			var result = eval(data);
			for(var i=0;i<result.length;i++){
				$("#u_Course").append("<option value='"+result[i].courseId+"'>"+result[i].courseName+"</option>");
			}
		}
	})
}

//单个删除
function deleteTestPaper(tpId){
	 if(confirm('确实要删除该试卷信息吗?')) {
		$.post("${pageContext.request.contextPath}/testPaper/getItemPaperByTpId.action",{"tpId":tpId},
		function(data){
			if(data =="haveStatistics"){
				if(confirm('该试卷下有做题统计信息,这会一同删除，你确定要执行删除吗?')){
					todelTestPaper(tpId);
				}
			}else{
				todelTestPaper(tpId);
			}
		});
	 }
}
//执行删除操作
function todelTestPaper(tpId){
	$.post("${pageContext.request.contextPath}/testPaper/delTestPaper.action",{"tpId":tpId},
	function(data){
		if(data =="ok"){
			//alert("试卷删除成功！");
			location.href="${pageContext.request.contextPath}/testPaper/testPaperManager.action";
		}else{
			alert("出错啦~试卷删除失败！");
			location.href="${pageContext.request.contextPath}/testPaper/testPaperManager.action";
		}
	});
}
//点击批量删除
$("#delMore").click(function(){
	var tpCheck = document.getElementsByName("tpCheck");
	var tpIds = [];
	for(id in tpCheck){
		if(tpCheck[id].checked){
			tpIds.push(tpCheck[id].value);
		}
	}
	if(tpIds.length==0){
		alert("请选中你想要删除的试卷");
	}else{
		if(confirm('确实要删除这些试卷信息吗?')) {
			$.ajax({
				async:false,
				type:"post",
				url:"${pageContext.request.contextPath}/testPaper/getItemPaperByTpIds.action",
				traditional:true,
				data : {
					"tpIds":tpIds
				},
				success:function(data){
					if(data =="haveStatistics"){
						if(confirm('这些试卷下有做题统计信息,这会一同删除，你确定要执行删除吗?')){
							todelTestPapers(tpIds);
						}
					}else{
						todelTestPapers(tpIds);
					}
				}
			})
		}
	}
})
//执行批量删除操作
function todelTestPapers(tpIds){
	$.ajax({
		async:false,
		type:"post",
		url:"${pageContext.request.contextPath}/testPaper/delMoreTestPaper.action",
		traditional:true,
		data : {
			"tpIds":tpIds
		},
		success:function(data){
			if(data =="ok"){
				//alert("试卷删除成功！");
				location.href="${pageContext.request.contextPath}/testPaper/testPaperManager.action";
			}else{
				alert("出错啦~试卷删除失败！");
				window.location.reload();
			}
		}
	})
}
// 生成试卷(ajax传递)
function createTestPaper() {
	var a_tpName = $("#a_tpName").val();	//试卷名称
	var fieldId = $("#a_field").val();	// 行业id
	var courseId = $("#a_course").val();	// 课程id
	var startTime = $("#a_startTimeLocal").val();
	var timeStart = new Date(startTime).getTime();	// 开始时间,把datetime-local转为时间戳传到后台
	var endTime = $("#a_endTimeLocal").val();
	var timeEnd = new Date(endTime).getTime();	// 结束时间,把datetime-local转为时间戳传到后台
	var a_number = $("#a_number").val();	// 试题数
	var a_oneBranch = $("#a_oneBranch").val();	// 单题分数
	if(isNaN(timeStart) || isNaN(timeEnd) || timeStart == timeEnd){
		alert("亲~请输入正确时间格式(时间不能为空且开考时间与结束时间不能相等)");
	}else{
		$.ajax({
			async:false,
			type:"post",
			url:"${pageContext.request.contextPath}/testPaper/addTestPaper.action",
			data:{
				tpName:a_tpName,
				fieldId:fieldId,
				courseId:courseId,
				startTime:timeStart,
				endTime:timeEnd,
				number:a_number,
				oneBranch:a_oneBranch,
			},
			success:function(data){
				if(data == "ok"){
			         alert("试卷生成成功！");
			         window.location.reload();
			    }else if(data == "again"){
					alert("该试卷名称已存在,请重新输入");
				}else if(data == "noMore"){
					alert("抱歉~~输入题数大于题库内的题数(或题库无相关试题)无法自动出题");
				}else if(data == "incomplete"){
					alert("亲~信息填写不全,请查看填写内容")
				}else if(data == "truenum"){
					alert("亲~请输入正确题数和单题分数")
				}else if(data == "timefalse"){
					alert("亲~请查看时间设置是否正确(必须大于当前系统时间,开考时间不能大于结束时间)")
				}else{
					alert("出错啦,试卷生成失败！");
					window.location.reload();
				}
			}
		})
	}
	
	
}


//通过id获取修改的试卷信息
function editTestPaper(tpId) {
	getFieldsList();
    $.ajax({
        type:"get",
        url:"${pageContext.request.contextPath}/testPaper/getTestPaperById.action",
        data:{"tpId":tpId},
        success:function(data) {
        	// 转换开考时间格式
        	var startTime = changeTime(data.startTime);
        	// 转换结束考试时间格式
        	var endTime = changeTime(data.endTime);
        	// 转换创建试卷时间格式
        	var tpTime = changeTime(data.tpTime);
        	// 给相应的文本框赋值
        	$("#u_hideTestId").val(data.tpId);
            $("#u_tpName").val(data.tpName);
            $("#u_startTime").val(startTime);
            $("#u_endTime").val(endTime);
            $("#u_tpTime").val(tpTime);
            $("#u_number").val(data.number);
            $("#u_oneBranch").val(data.oneBranch);
            // 选中对应行业
            $("#u_Field option").each(function(){
				if($(this).val() == data.field.fieldId){
					$(this).attr("selected","selected");
					// 调用行业和课程联动方法
					updateFieldChange();
					// 选中对应课程
					$("#u_Course option").each(function(){
							if($(this).val() == data.course.courseId){
								$(this).attr("selected","selected");
							}
					})
				}
			})
        }
    });
}
// 将Date日期格式转换为DateTime-local格式
function changeTime(time){
	format = "";
	var nTime = new Date(time);
	format += nTime.getFullYear()+"-";
	format += (nTime.getMonth()+1)<10?"0"+(nTime.getMonth()+1):(nTime.getMonth()+1);
	format += "-";
	format += nTime.getDate()<10?"0"+(nTime.getDate()):(nTime.getDate());
	format += "T";
	format += nTime.getHours()<10?"0"+(nTime.getHours()):(nTime.getHours());
	format += ":";
	format += nTime.getMinutes()<10?"0"+(nTime.getMinutes()):(nTime.getMinutes());
	format += ":00";
	return format;
}

//执行修改试卷信息操作
function updateTestPaper() {
	var u_tpId = $("#u_hideTestId").val(); // 试卷id
	var u_tpName = $("#u_tpName").val();	//试卷名称
	var fieldId = $("#u_Field").val();	// 行业id
	var courseId = $("#u_Course").val();	// 课程id
	var startTime = $("#u_startTime").val();
	var timeStart = new Date(startTime).getTime();	// 开始时间,把datetime-local转为时间戳传到后台
	var endTime = $("#u_endTime").val();
	var timeEnd = new Date(endTime).getTime();	// 结束时间,把datetime-local转为时间戳传到后台
	var u_number = $("#u_number").val();	// 试题数
	var u_oneBranch = $("#u_oneBranch").val();	// 单题分数
	
	if(isNaN(timeStart) || isNaN(timeEnd) || timeStart == timeEnd){
		alert("亲~请输入正确时间格式(时间不能为空且开考时间与结束时间不能相等)");
	}else{
		$.ajax({
			async:false,
			type:"post",
			url:"${pageContext.request.contextPath}/testPaper/updateTestPaper.action",
			data:{
				tpId:u_tpId,
				tpName:u_tpName,
				fieldId:fieldId,
				courseId:courseId,
				startTime:timeStart,
				endTime:timeEnd,
				number:u_number,
				oneBranch:u_oneBranch,
			},
			success:function(data){
				if(data == "ok"){
			         alert("试卷信息更新成功！");
			         location.href="${pageContext.request.contextPath}/testPaper/testPaperManager.action";
			    }else if(data == "againAdd"){
					alert("抱歉~~输入题数大于题库内的题数(或题库无相关试题),无法自动出题");
				}else if(data == "incomplete"){
					alert("亲~信息填写不全,请查看填写内容")
				}else if(data == "truenum"){
					alert("亲~请输入正确题数和单题分数")
				}else if(data == "timefalse"){
					alert("亲~请查看时间设置是否正确(必须大于当前系统时间,开考时间不能大于结束时间)")
				}else{
					alert("出错啦~试卷信息更新失败");
					window.location.reload();
				}
			}
		})
	}
}

// 试卷详情
function getTestPaperById(tpId){
	// 清空内容
	$("#s_tpName").val("");
    $("#s_field").val("");
    $("#s_course").val("");
    $("#s_startTime").val("");
    $("#s_endTime").val("");
    $("#s_number").val("");
    $("#s_oneBranch").val("");
    $("#s_totalScorce").val("");
    $("#s_userName").val("");
	$.ajax({
        type:"get",
        url:"${pageContext.request.contextPath}/testPaper/getTestPaperById.action",
        data:{"tpId":tpId},
        success:function(data) {
        	// 给相应的文本框赋值
            $("#s_tpName").val(data.tpName);
            $("#s_field").val(data.field.fieldName);
           	$("#s_course").val(data.course.courseName);
        	$("#s_startTime").val(data.startTime.substring(0,16));
            $("#s_endTime").val(data.endTime.substring(0,16));
            $("#s_tpTime").val(data.tpTime.substring(0,16));
            $("#s_number").val(data.number);
            $("#s_oneBranck").val(data.oneBranch);
            $("#s_totalScorce").val(data.totalScore);
            $("#s_userName").val(data.user.userName);
        }
	})
}
//获取所有行业信息
function getFieldsList(){
		$.ajax({
	        url : "${pageContext.request.contextPath }/field/fields.action",
	        type : 'post',
	        dataType : 'json',
	        async : false,
	        //contentType: "application/json; charset=utf-8",
	        //返回集合
	        success : function(data) {              
	            if(data!=null){
	            	$("#u_Field").empty();
	                $("#u_Field").prepend("<option value=''>--请选择--</option>");
	                $.each(data,function(i,item){
	                    $.each(item,function(j,val){
	                    	$("#u_Field").append("<option value="+val.fieldId+">"+val.fieldName+"</option>");
	                    })
	                })
	            }                                                    
	       }
	 	})
}

// 判断查询的开始时间和结束时间的大小
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


// 页面加载就执行
window.onload=function(){
	// 获取行业id
	var fieldId = $("#hiddenField").val();
	// 获取课程id
	var courseId = $("#hiddenCourse").val();
	// 选中行业
	$("#fieldId option").each(function(){
		if($(this).val() == fieldId){
			$(this).attr("selected","selected");
		}
	})
	// 判断隐藏域行业id是不是null
	if(fieldId!= null){
		// 根据行业id查询课程
		selectFiled(fieldId)
		$("#courseId option").each(function(){
			if($(this).val() == courseId){
				$(this).attr("selected","selected")
			}
		})
	}
}
</script>
</body>
</html>
