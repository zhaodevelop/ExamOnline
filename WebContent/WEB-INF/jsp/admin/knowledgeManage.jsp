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
	<!-- 知识点列表查询部分  start-->
	<div id="page-wrapper">
		<div class="row">
			<div class="col-lg-12">
				<h1 class="page-header" style="margin-top: 20px">知识点管理</h1>
			</div>
			<!-- /.col-lg-12 -->
		</div>
		<!-- /.row -->
		<div class="panel panel-default">
			<div class="panel-body">
				<form class="form-inline" method="post"
					action="${pageContext.request.contextPath }/knowledge/knowledgeManage.action">
					<div class="form-group">
						<label for="customerName">知识点名称</label> <input type="text"
							class="form-control" id="customerName" value="${knowledgeName}"
							name="knowledgeName" />
					</div>
					<div class="form-group">
						<label for="customerFrom">所属行业</label> <input type="hidden"
							id="hiddenField" value="${fieldId}" /> <select
							class="form-control" id="fieldId" name="fieldId"
							onchange="selectFiled()">
							<option value="">--请选择--</option>
							<c:forEach items="${fields}" var="field">
								<option value="${field.fieldId}"
								<c:if test="${field.fieldId == fieldId}">selected</c:if>>${field.fieldName}</option>
							</c:forEach>
						</select>
					</div>
					<div class="form-group">
						<label for="custIndustry">所属课程</label> <input type="hidden"
							id="hiddenCourse" value="${id}" /> <select
							class="form-control" id="courseId" name="courseId">
							<option value="">--请选择--</option>
						</select>
					</div>
					<button type="submit" class="btn btn-primary">查询</button>
					<a
						href="${pageContext.request.contextPath }/knowledge/knowledgeManage.action"
						class="btn btn-primary" data-target="#newKnowledgeDialog"
						onclick="clearKnowledge()">查询全部</a>
				</form>
			</div>
		</div>
		<a href="#" class="btn btn-primary" data-toggle="modal"
			data-target="#addKnowledge" onclick="clearKnowledge()">新建</a> &nbsp;<a
			href="#" class="btn btn-danger btn-xs" style="width:80px;
				height:33px;line-height:33px;font-size:14px;" data-toggle="modal" id="del">批量删除</a>
		<div class="row">
			<div class="col-lg-12">
				<div class="panel panel-default">
					<div class="panel-heading">知识点信息列表</div>
					<!-- /.panel-heading -->
					<c:choose>
						<c:when test="${fn:length(page.result)>0}">
							<table class="table table-bordered table-striped">
								<thead>
									<tr>
										<th><input id="all" type="checkbox" />&nbsp;全选</th>
										<th>知识点ID</th>
										<th>知识点名称</th>
										<th>课程名称</th>
										<th>所属行业</th>
										<th>操作</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${page.result}" var="result">
										<tr style="text-align: center;">
											<td><input id="person" name="id" type="checkbox"
												value="${result.knowledgeId}" /></td>
											<td>${result.knowledgeId}</td>
											<td>${result.knowledgeName}</td>
											<td>${result.course.courseName}</td>
											<td>${result.field.fieldName}</td>
											<td><a href="#" class="btn btn-primary btn-xs"
												data-toggle="modal" data-target="#KnowledgeEditDialog"
												onclick="editKnowledge(${result.knowledgeId})"> 修改 </a> <a
												href="#" class="btn btn-danger btn-xs"
												onclick="deleteKnowledge(${result.knowledgeId})"> 删除 </a></td>
										</tr>
									</c:forEach>

								</tbody>
							</table>
							<div class="col-md-12 text-right">
								<itheima:page
									url="${pageContext.request.contextPath }/knowledge/knowledgeManage.action" />
							</div>
							<!-- /.panel-body -->
						</c:when>
						<c:otherwise>
							<div style="text-align: center;margin: 50px 0;font-size:18px;">
									抱歉,没有找到知识点信息
							</div>
						</c:otherwise>
					</c:choose>
				</div>
				<!-- /.panel -->
			</div>
			<!-- /.col-lg-12 -->
		</div>
	</div>
	<!-- 知识点列表查询部分  end-->
	<!-- 创建知识点模态框 -->
	<div class="modal fade" id="addKnowledge" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">新建知识点信息</h4>
				</div>
				<div class="modal-body">
					<form class="form-horizontal" id="new_knowledge_form">
						<div class="form-group">
							<label for="new_custIndustry"
								style="float: left; padding: 7px 15px 0 13px;">知识点名称</label>
							<div class="col-sm-10">
								<input type="text" class="form-control" id="new_knowledgeName"
									placeholder="知识点名称" name="knowledgeName" />
							</div>
						</div>
						<div class="form-group">
							<label for="new_customerFrom"
								style="float: left; padding: 7px 15px 0 27px;">所属行业</label>
							<div class="col-sm-10">
								<select class="form-control" id="field" name="fieldId"
									onchange="addFieldChange()">
									<option value="">--请选择--</option>
									<c:forEach items="${fields}" var="field">
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
								<select class="form-control" id="course" name="courseId">
									<option value="">--请选择--</option>
								</select>
							</div>
						</div>
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					<button type="button" class="btn btn-primary"
						onclick="createKnowledge()">添加</button>
				</div>
			</div>
		</div>
	</div>
	<!-- 修改知识点模态框 -->
	<div class="modal fade" id="KnowledgeEditDialog" tabindex="-1"
		role="dialog" aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">修改知识点信息</h4>
				</div>
				<div class="modal-body">
					<form class="form-horizontal" id="edit_knowledge_form">
						<input type="hidden" id="edit_knowledgeId" name="knowledgeId" />
						<div class="form-group">
							<label for="new_custIndustry"
								style="float: left; padding: 7px 15px 0 13px;">知识点名称</label>
							<div class="col-sm-10">
								<input type="text" class="form-control" id="edit_knowledgeName"
									placeholder="知识点名称" name="knowledgeName" />
							</div>
						</div>
						<div class="form-group">
							<label for="edit_customerFrom"
								style="float: left; padding: 7px 15px 0 27px;">所属行业</label>
							<div class="col-sm-10">
								<select class="form-control" id="updateField" name="fieldId"
									onchange="updateFieldChange()">
								</select>
							</div>
						</div>
						<div class="form-group">
							<label for="edit_custIndustry"
								style="float: left; padding: 7px 15px 0 27px;">所属课程</label>
							<div class="col-sm-10">
								<select class="form-control" id="updateCourse" name="courseId">
								</select>
							</div>
						</div>
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					<button type="button" class="btn btn-primary"
						onclick="updateKnowledge()">保存修改</button>
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
	if(oall!=null){
		oall.onclick=function(){
			for(var i=0;i<oid.length;i++){
				//所有的选择框和全选一致
				oid[i].checked=oall.checked;		
			}
		};
	}

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
			alert("请选择你要删除的知识点信息");
			return false;
		}
		if(confirm('确定要删除所选知识点吗?')) {
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
			$.get("${pageContext.request.contextPath }/knowledge/deleteKnowledgeByIds",
					ids,function(data){
				if(data=="OK"){
					window.location.href="${pageContext.request.contextPath}/knowledge/knowledgeManage.action";
				}else{
					alert("您所选中的知识点中存在题库，试卷信息不能删除!");
					window.location.reload();
				}
			});
		}
	});
	//清空新建知识点窗口中的数据
	function clearKnowledge() {
	    $("#new_knowledgeName").val("");
	    $("#field").val("");
	    $("#course").val("");
	}
	// 创建知识点
	function createKnowledge() {
	$.post("${pageContext.request.contextPath }/knowledge/create.action",
	$("#new_knowledge_form").serialize(),
		function(data){
	        if(data =="OK"){
	            alert("知识点添加成功！");
	            window.location.reload();
	        }else if(data == "REPEATE"){
	            alert("知识点重名，请重新添加！");
	            window.location.reload();
	        }else if(data == "NULL"){
	            alert("没有填全信息，请重新添加！");
	            window.location.reload();
	        }else{
	            alert("知识点添加失败！");
	            window.location.reload();
	        }
	    });
	}
	// 获取所有行业信息
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
		            	$("#updateField").empty();
		                $("#updateField").prepend("<option value=''>--请选择--</option>");
		                $.each(data,function(i,item){
		                    $.each(item,function(j,val){
		                    	$("#updateField").append("<option value="+val.fieldId+">"+val.fieldName+"</option>");
		                    })
		                })
		            }                                                    
		       }
		 	})
	}
	// 通过知识点id获取修改的知识点信息
	function editKnowledge(id) {
		getFieldsList();
	    $.ajax({
	        type:"get",
	        url:"${pageContext.request.contextPath }/knowledge/getKnowledgeById.action",
	        data:{"id":id},
	        success:function(data) {
	        	$("#edit_knowledgeId").val(data.knowledgeId);
	            $("#edit_knowledgeName").val(data.knowledgeName);
	            $("#updateField option").each(function(){
					if($(this).val() == data.course.field.fieldId){
						$(this).attr("selected","selected");
						// 调用行业和课程联动方法
						updateFieldChange();
						// 选中对应课程
						$("#updateCourse option").each(function(){
								if($(this).val() == data.course.courseId){
									$(this).attr("selected","selected");
								}
						})
					}
				})
	        }
	    });
	}
    // 执行修改知识点操作
	function updateKnowledge() {
		$.post("${pageContext.request.contextPath }/knowledge/updateKnowledge",
		$("#edit_knowledge_form").serialize(),
		function(data){
			if(data=="OK"){
				alert("知识点信息更新成功！");
				window.location.reload();
			}else if(data == "NULL"){
	            alert("没有填全信息，请重新修改！");
	            window.location.reload();
	        }else{
				alert("知识点信息更新失败！");
				window.location.reload();
			}
		});
	}
	// 删除行业信息
	function deleteKnowledge(id) {
	    if(confirm('确实要删除该知识点吗?')) {
	$.post("${pageContext.request.contextPath }/knowledge/deleteKnowledge",{"id":id},
	function(data){
	            if(data =="OK"){
	                window.location.href="${pageContext.request.contextPath}/knowledge/knowledgeManage.action";
	            }else{
	            	alert("该知识点下有题库、试卷信息，不能删除！");
	                window.location.reload();
	            }
	        });
	    }
	}
	// 行业联动课程(添加)
	function addFieldChange(){
	   var options=$("#field option:selected");
	   var value=options.val();
	   $.ajax({
			async:false,
			type:"post",
			dataType:"json",
			url:"${pageContext.request.contextPath}/knowledge/getCourses.action",
			data:{fieldId:value}, //二级产品类别的父ID
			success:function(data){
				$("#course").empty();
				$("#course").append("<option value= ''>--请选择--</option>")
				// 把JSON数据转成数组
				var result = eval(data);
				for(var i=0;i<result.length;i++){
					$("#course").append("<option value='"+result[i].courseId+"'>"+result[i].courseName+"</option>");
				}
			}
		})
	}
	//行业联动课程(修改)
	function updateFieldChange(){
	   var options=$("#updateField option:selected");
	   var value=options.val();
	   $.ajax({
			async:false,
			type:"post",
			dataType:"json",
			url:"${pageContext.request.contextPath}/knowledge/getCourses.action",
			data:{fieldId:value}, //二级产品类别的父ID
			success:function(data){
				$("#updateCourse").empty();
				$("#updateCourse").append("<option value= ''>--请选择--</option>")
				// 把JSON数据转成数组
				var result = eval(data);
				for(var i=0;i<result.length;i++){
					$("#updateCourse").append("<option value='"+result[i].courseId+"'>"+result[i].courseName+"</option>");
				}
			}
		})
	}
	//行业和课程联动(查询)
	function selectFiled(fieldId){
		var options=$("#fieldId option:selected");
	    var value=options.val();
	    $.ajax({
		async:false,
			type:"post",
			dataType:"json",
			url:"${pageContext.request.contextPath}/knowledge/getCourses.action",
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