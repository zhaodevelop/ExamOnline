<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="itheima" uri="http://systop.com/common/"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() 
	                   + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
	<!-- 引入css样式文件 -->
	<!-- Bootstrap Core CSS -->
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/admin/bootstrap.min.css">
	<!-- MetisMenu CSS -->
	<link href="${pageContext.request.contextPath }/css/admin/metisMenu.min.css" rel="stylesheet" type="text/css"/>
	<!-- DataTables CSS -->
	<link href="${pageContext.request.contextPath }/css/admin/dataTables.bootstrap.css" rel="stylesheet" type="text/css"/>
	<!-- Custom CSS -->
	<link href="${pageContext.request.contextPath }/css/admin/sb-admin-2.css" rel="stylesheet" type="text/css"/>
	<!-- Custom Fonts -->
	<link href="${pageContext.request.contextPath }/css/admin/font-awesome.min.css" rel="stylesheet" type="text/css" />
	<link href="${pageContext.request.contextPath }/css/admin/boot-crm.css" rel="stylesheet" type="text/css" />
	<link href="${pageContext.request.contextPath }/css/admin/ItemBank.css" rel="stylesheet" type="text/css" />
	<style type="text/css">
	.getWrong {
	width: 500px;
	display: none;
	position: absolute;
	top:30px;
	/*display:block;*/
	left:300px;
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
	.detailWrong{
	padding-top: 20px;
    height: 500px;
    margin: 0 auto;
    margin-bottom: 20px;
}
	</style>



</head>

<body onload="selectCourses('fieldIdFrom','courseForm','ajaxCourse','knowledgeForm','ajaxKnowledge')">
    <!-- 客户列表查询部分  start-->
	<div id="page-wrapper">
		<div class="row">
			<div class="col-lg-12" >
				<h1 class="page-header" style="margin-top: 20px">题库管理</h1>
			</div>
			<!-- /.col-lg-12 -->
		</div>
		<!-- /.row -->
		<div class="panel panel-default">
			<div class="panel-body">
				<form class="form-inline" method="post" 
				      action="${pageContext.request.contextPath }/itemBank/list.action">
					<div class="form-group">
						<label for="customerName" >命题人</label> 
						<input type="text" class="form-control" id="userName" 
						                   value="${userName }" name="userName"  />
					</div>
					<div class="form-group">
						<label for="questionName">试题</label> 
						<input type="text" class="form-control" id="questionName" 
						                   value="${question }" name="question" />
					</div>
					<div class="form-group">
						<label for="customerFrom">行业</label> 
						<select	class="form-control" id="fieldIdFrom" name="fieldId" onchange="selectCourses('fieldIdFrom','courseForm','ajaxCourse','knowledgeForm','ajaxKnowledge')">
							<option value="">--请选择--</option>
							<c:forEach items="${fields}" var="field">
								<option value="${field.fieldId}"
								       <c:if test="${field.fieldId == fieldId}">selected</c:if>>
								    ${field.fieldName}
								</option>
							</c:forEach>
						</select>
					</div>
					
					<div class="form-group">
						<label for="custIndustry">课程</label> 
						<select	class="form-control" id="courseForm"  name="courseId" onchange="selectKnowledge('courseForm','knowledgeForm','ajaxKnowledge')">
						<option value="">--请选择--</option>
						</select>
					</div>
					<input type="hidden" value="${ajaxCourse}" id="ajaxCourse" name="ajaxCourse">
					<div class="form-group">
						<label for="custLevel">知识点</label>
						<select	class="form-control" id="knowledgeForm" name="knowledgeId" >
						<option value="">--请选择--</option>
						</select>
					</div>
					<input type="hidden" value="${ajaxKnowledge}" id="ajaxKnowledge" name="ajaxKnowledge">
					<button type="submit" class="btn btn-primary">查询</button>
					<a href="${pageContext.request.contextPath }/itemBank/list.action" class="btn btn-primary"   data-target="#newCustomerDialog" onclick="clearCustomer()">查询全部</a>
				</form>
				<div  style="width: 500px;float:left;margin-top:12px;">
					<form method="post" enctype="multipart/form-data" id="form1" >  
					<a href="javascript:;" class="a-upload">  
						<input type="file" name="file" class="btn btn-primary"  id="excelFile" >Excel批量添加>>
					</a>
					<input type="button"  id="fileForm"  style="margin-top:-3px;margin-left:10px;" class="btn btn-primary"  value="上传">
							<a href="${pageContext.request.contextPath }/itemBank/download.action" 
						class="btn btn-primary"  style="margin-top:-3px;margin-left:10px;"
						>上传模板下载</a>
					</form>
			
				</div>
				
			</div>
		</div>
		<a href="#" class="btn btn-primary " data-toggle="modal" data-target="#addItemBank" onclick="addclear()">新建</a>
				<div class="row">
		<div class="col-lg-12">
				<div class="panel panel-default">
					<div class="panel-heading">题库信息列表</div>
					<!-- /.panel-heading -->
				<c:choose>
					<c:when test="${fn:length(page.result)>0}">
					<table class="table table-bordered table-striped">
						<thead>
							<tr>
								<th><input type="checkbox" id="all" value="" name="" />全选</th>
								<th>试题ID</th>
								<th>命题人</th>
								<th>题目</th>
								<th>行业</th>
								<th>课程</th>
								<th>知识点</th>
								<th>操作</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${page.result}" var="result">
							<tr style="text-align: center;">
								<td><input type="checkbox"  value="${result.ibId}" name="ibId" /></td>
								<td>${result.ibId}</td>
								<td>${result.user.userName}</td>
								<td>${fn:substring(result.question, 0, 20)}<c:if test="${fn:length(result.question)>20}">...</c:if></td>
								<td>${result.field.fieldName}</td>
								<td>${result.course.courseName}</td>
								<td>${result.knowledge.knowledgeName}<c:if test="${fn:length(result.knowledge.knowledgeName)>15}">...</c:if></td>
								<td>
								<a href="#" class="btn btn-primary btn-xs" data-toggle="modal" data-target="#ItemBankDetil" onclick= "detailItemBank(${result.ibId})" >详情</a>
								<a href="#" class="btn btn-primary btn-xs" data-toggle="modal" data-target="#updateItemBank" onclick= "getItemBank(${result.ibId})">修改</a>
								<a href="# "  class="btn btn-danger btn-xs"
								onclick ="deleteOne(${result.ibId})">删除</a>
								</td>
							</tr>
							</c:forEach>
		
						</tbody>
					</table>

					<div class="col-md-12 text-right">
						<itheima:page url="${pageContext.request.contextPath }/itemBank/list.action" />
					</div>
					<!-- /.panel-body -->
				</div>
				</c:when>
				<c:otherwise>
						<div style="text-align: center;margin: 50px 0;font-size:18px;">
							抱歉,还没有找到试题信息
						</div>
				</c:otherwise>
			</c:choose>
							<a href="#" class="btn btn-danger btn-xs"   id="delMore" style="width:80px;
				height:33px;font-size:14px;position:absolute;left:75px;
				top:-34px;padding-top:7px;">批量删除</a>
				<!-- /.panel -->
			</div>
			<!-- /.col-lg-12 -->
		</div>
	</div>
	<!-- 题库列表查询部分  end-->
	<!-- 添加试题模态框 -->
	<div class="modal fade" id="addItemBank" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">添加试题信息</h4>
				</div>
				<div class="modal-body">
					<form class="form-horizontal" id="add__itemBank_form">
						<div class="form-group">
							<label for="edit_customerFrom" class="col-sm-2 control-label">行业</label> 
							<div class="col-sm-10">
							<select	class="form-control" id="fieldId1" name="fieldId1" style="width:300px;" 
							onchange="selectCourses('fieldId1','courseId1','-1','knowledgeId1','-1')" >
								<option value="">--请选择--</option>
								<c:forEach items="${fields2}" var="field">
									<option value="${field.fieldId}">
								    ${field.fieldName}
								</option>
								</c:forEach>
							</select>
							</div>
						</div>
						<div class="form-group">
							<label for="edit_customerFrom" class="col-sm-2 control-label">课程</label> 
							<div class="col-sm-10">
							<select	class="form-control" id="courseId1" name="courseId1" style="width:300px;" 
							onchange="selectKnowledge('courseId1','knowledgeId1','-1')">
								<option value="">--请选择--</option>
							</select>
							</div>
						</div>
						<div class="form-group">
							<label for="edit_customerFrom" class="col-sm-2 control-label">知识点</label> 
							<div class="col-sm-10">
							<select	class="form-control" id="knowledgeId1" name="knowledgeId1" style="width:300px;" >
								<option value="">--请选择--</option>
							</select>
							</div>
						</div>						
						<div class="form-group">
							<label for="add_itemBank" class="col-sm-2 control-label">试题</label>
							<div class="col-sm-10">
							 <textarea rows="5" cols="60" name="question1" id="ibquestion1"></textarea>
							</div>
						</div>
						<div class="form-group">
							<label for="add_itemBank" class="col-sm-2 control-label">选项A</label>
							<div class="col-sm-10">
								<input type="text" class="form-control" id="optionA1" placeholder="选项A" name="optionA1" />
							</div>
						</div>
						<div class="form-group">
							<label for="add_itemBank" class="col-sm-2 control-label">选项B</label>
							<div class="col-sm-10">
								<input type="text" class="form-control" id="optionB1" placeholder="选项B" name="optionB1" />
							</div>
						</div>
						<div class="form-group">
							<label for="add_itemBank" class="col-sm-2 control-label">选项C</label>
							<div class="col-sm-10">
								<input type="text" class="form-control" id="optionC1" placeholder="选项C" name="optionC1" />
							</div>
						</div>
						<div class="form-group">
							<label for="add_itemBank" class="col-sm-2 control-label">选项D</label>
							<div class="col-sm-10">
								<input type="text" class="form-control" id="optionD1" placeholder="选项D" name="optionD1" />
							</div>
						</div>
						<div class="form-group" >
							<label for="add_itemBank" class="col-sm-2 control-label">答案</label>
							<div class="col-sm-10" style="margin-top:6px;">
								<label style="font-size:16px;"><input name="answer1" type="radio" value="A" />A</label> 
								<label style="margin-left:15px;font-size:16px;"><input name="answer1" type="radio" value="B" />B</label> 
								<label style="margin-left:15px;font-size:16px;"><input name="answer1" type="radio" value="C" />C</label> 
								<label style="margin-left:15px;font-size:16px;"><input name="answer1" type="radio" value="D" />D</label> 
						
							</div>
						</div>
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					<button type="button" class="btn btn-primary" onclick="addUser()">添加</button>
				</div>
			</div>
		</div>
	</div>	
	<!-- 修改客户模态框 -->
	<div class="modal fade" id="updateItemBank" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">修改试题信息</h4>
				</div>
				<div class="modal-body">
					<form class="form-horizontal" id="update__itemBank_form">
						<input type="hidden" value="" id="itemBankId" name="itemBankId">	
						<div class="form-group">
							<label for="edit_customerFrom" class="col-sm-2 control-label">行业</label> 
							<div class="col-sm-10">
							<select	class="form-control" id="fieldId2" name="fieldId2" style="width:300px;" 
							onchange="selectCourses('fieldId2','courseId2','courseUpdate','knowledgeId2','knowledgeUpdate')" >
								
							</select>
							</div>
						</div>
						<div class="form-group">
							<label for="edit_customerFrom" class="col-sm-2 control-label">课程</label> 
							<div class="col-sm-10">
							<select	class="form-control" id="courseId2" name="courseId2" style="width:300px;" 
							onchange="selectKnowledge('courseId2','knowledgeId2','knowledgeUpdate')">
								<option value="">--请选择--</option>
							</select>
							</div>
						</div>
						<input type="hidden" value="${courseUpdate}" id="courseUpdate" name="courseUpdate">
						<div class="form-group">
							<label for="edit_customerFrom" class="col-sm-2 control-label">知识点</label> 
							<div class="col-sm-10">
							<select	class="form-control" id="knowledgeId2" name="knowledgeId2" style="width:300px;" >
								<option value="">--请选择--</option>
							</select>
							</div>
						</div>					
						<input type="hidden" value="${knowledgeUpdate}" id="knowledgeUpdate" name="knowledgeUpdate">	
						<div class="form-group">
							<label for="add_itemBank" class="col-sm-2 control-label">试题</label>
							<div class="col-sm-10">
							 <textarea rows="5" cols="60" name="question_update" id="question_update"></textarea>
							</div>
						</div>
						<div class="form-group">
							<label for="add_itemBank" class="col-sm-2 control-label">选项A</label>
							<div class="col-sm-10">
								<input type="text" class="form-control" id="optionA_update" placeholder="选项A" name="optionA2" />
							</div>
						</div>
						<div class="form-group">
							<label for="add_itemBank" class="col-sm-2 control-label">选项B</label>
							<div class="col-sm-10">
								<input type="text" class="form-control" id="optionB_update" placeholder="选项B" name="optionB2" />
							</div>
						</div>
						<div class="form-group">
							<label for="add_itemBank" class="col-sm-2 control-label">选项C</label>
							<div class="col-sm-10">
								<input type="text" class="form-control" id="optionC_update" placeholder="选项C" name="optionC2" />
							</div>
						</div>
						<div class="form-group">
							<label for="add_itemBank" class="col-sm-2 control-label">选项D</label>
							<div class="col-sm-10">
								<input type="text" class="form-control" id="optionD_update" placeholder="选项D" name="optionD2" />
							</div>
						</div>
						<div class="form-group" >
							<label for="add_itemBank" class="col-sm-2 control-label">答案</label>
							<div class="col-sm-10" style="margin-top:6px;">
								<label style="font-size:16px;"><input name="answer2" type="radio" value="A" />A</label> 
								<label style="margin-left:15px;font-size:16px;"><input name="answer2" type="radio" value="B" />B</label> 
								<label style="margin-left:15px;font-size:16px;"><input name="answer2" type="radio" value="C" />C</label> 
								<label style="margin-left:15px;font-size:16px;"><input name="answer2" type="radio" value="D" />D</label> 
						
							</div>
						</div>
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					<button type="button" class="btn btn-primary" onclick="updateItemBank()">修改</button>
				</div>
			</div>
		</div>
	</div>	
	<!-- 修改客户模态框结束 -->
	<!-- 错误提示框 -->
	<div class="getWrong" id="getWrongById">
		<div class="modal-header">
			<button class="close" id="close" onclick="clcoseGetWrong()">
				<span>×</span>
			</button>
			<h3 class="modal-title" style="font-weight: bold;">错误信息<span style='color:red;font-size: 16px;font-weight: normal;'>(请修改完后重新上传整个Excel)</span></h3>
			<div class="detailWrong" id="detailWrong" style="margin-top:20px;font-size:15px;font-family: '微软雅黑';"></div>
		</div>
	</div>
	<!-- 详情页面模态框 开始-->
	<div class="modal fade" id="ItemBankDetil" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document" style="width:800px;">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="myModalLabel" style="font-weight: bold;">试题详情</h4>
				</div>
				<div id="detailContent"style="border:1px solid #ccc;width:90%;height:500px;margin:0 auto;margin-bottom:20px;">
					<table >
						<tr>
							<td class="tong">命题人</td>
							<td id="userName_detail"></td>
						</tr>
						<tr>
							<td class="tong">行业</td>
							<td id="field_detail"></td>
						</tr>
						<tr>
							<td class="tong">课程</td>
							<td id="course_detail"></td>
						</tr>
						<tr>
							<td class="tong">知识点</td>
							<td id="knowledge_detail"></td>
						</tr>
						<tr>
							<td class="tong">试题</td>
							<td id="question_detail"></td>
						</tr>
						<tr>
							<td class="tong">选项A</td>
							<td id="optionA_detail"></td>
						</tr>
						<tr>
							<td class="tong">选项B</td>
							<td id="optionB_detail"></td>
						</tr>
						<tr>
							<td class="tong">选项C</td>
							<td id="optionC_detail"></td>
						</tr>
						<tr>
							<td class="tong">选项D</td>
							<td id="optionD_detail"></td>
						</tr>
						<tr>
							<td class="tong">答案</td>
							<td id="answer_detail"></td>
						</tr>
						<tr>
							<td class="tong">在用试卷</td>
							<td id="ItemPaper_detail"></td>
						</tr>
					</table>
				</div>
			</div>
		</div>
	</div>
	<!-- 详情页面模态框 结束-->
<!-- 引入js文件 -->
<!-- jQuery -->
<script src="${pageContext.request.contextPath }/js/admin/jquery-1.11.3.min.js"></script>
<!-- Bootstrap Core JavaScript -->
<script src="${pageContext.request.contextPath }/js/admin/bootstrap.min.js"></script>
<!-- Metis Menu Plugin JavaScript -->
<script src="${pageContext.request.contextPath }/js/admin/metisMenu.min.js"></script>
<!-- DataTables JavaScript -->
<script src="${pageContext.request.contextPath }/js/admin/jquery.dataTables.min.js"></script>
<script src="${pageContext.request.contextPath }/js/admin/dataTables.bootstrap.min.js"></script>
<!-- Custom Theme JavaScript -->
<script src="${pageContext.request.contextPath }/js/admin/sb-admin-2.js"></script>
<script src="${pageContext.request.contextPath }/js/admin/itemBank.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/admin/ajaxfileupload.js"></script>

<!-- 编写js代码 -->
<script type="text/javascript">

	// 详情页获取数据
	function detailItemBank(id) {
	    $.ajax({
	        type:"get",
	        url:"${pageContext.request.contextPath }/itemBank/itemBankDetail.action",
	        data:{"ibId":id},
	        success:function(data) { 
	        	$("#userName_detail").html(data.user.userName);
	            $("#field_detail").html(data.field.fieldName);
	            $("#course_detail").html(data.course.courseName);
	            $("#knowledge_detail").html(data.knowledge.knowledgeName);
	            $("#question_detail").html(data.question);
	            $("#optionA_detail").html(data.optionA);
	            $("#optionB_detail").html(data.optionB);
	            $("#optionC_detail").html(data.optionC);
	            $("#optionD_detail").html(data.optionD);
	            $("#answer_detail").html(data.answer);
	            var $ss="";
	        	$.each(data.testPaperList,function(index,val){
					if(data.testPaperList.length-1==index){
	        			$ss+=val.tpName;
	        		}else{
	        			$ss+=val.tpName+",";
	        		}
	        	})
	            $("#ItemPaper_detail").html($ss);
	        }
	    });
	}
    // 执行修改试题操作
	function updateItemBank() {
    	var itemBankId = $("#itemBankId").val();
    	var question_update = $("#question_update").val();
    	var optionA_update	=$("#optionA_update").val();
    	var optionB_update	=$("#optionB_update").val();
    	var optionC_update	=$("#optionC_update").val();
    	var optionD_update	=$("#optionD_update").val();
    	var courseId	=$("#courseId2").val();
    	var knowledgeId	=$("#knowledgeId2").val();
    	var fieldId	=$("#fieldId2").val();
    	var answer = "";
    	$("input[name='answer2']").each(function(i){
            if($(this).is(":checked"))
            {
            	answer =  $(this).val();
             }
    	})
    	    	//判断信息是否有全部填完
    	if(($("#fieldId2").val() && $("#knowledgeId2").val() && $("#courseId2").val() && $("#question_update").val() && $("#optionA_update").val() && $("#optionB_update").val() && 
    			$("#optionC_update").val() && $("#optionD_update").val()  && $("#answer2").val())!="" && ($(":radio[name='answer2']:checked").val()!=null) ){
		    $.ajax({
		        type:"post",
		        url:"${pageContext.request.contextPath }/itemBank/updateItemBank.action",
		        data:{"itemBankId":itemBankId,"question":question_update,"optionA":optionA_update,"optionB":optionB_update,
		        	"optionC":optionC_update,"optionD":optionD_update,"knowledgeId":knowledgeId,"courseId":courseId,"fieldId":fieldId,
		        	"answer":answer
		        },
		        success:function(data) { 
					if(data=="ok"){
						alert("试题信息更新成功！");
						window.location.reload();
					}else{
						alert("试题信息更新失败！");
						window.location.reload();
					}
		        }
		    });
    	}else{
    		alert("信息填写不全");
    		return false;
    	}
	}
	//添加前清空
	function addclear() {
		//获取行业下拉列表
		var count = $("#fieldId1 option").length;
		//遍历选中
		for ( var i = 0; i < count; i++) {
			if ($("#fieldId1 ").get(0).options[i].value == "") {
				$("#fieldId1 ").get(0).options[i].selected = true;
				break;
			}
		}
		$("#knowledgeId1").val("");
		$("#courseId1").val("");
		$("#ibquestion1").val("");
		$("#optionA1").val("");
		$("#optionB1").val("");
		$("#optionC1").val("");
		$("#optionD1").val("");
		$("#answer1").val("");
		var $radio=$(":radio[name='answer1']:checked").val();
		if($radio!=null){
			$(":radio[name='answer1']:checked").attr('checked',false); 
		}
	}
    // 执行添加用户操作
	function addUser() {
    	//判断信息是否有全部填完
    	if(($("#fieldId1").val() && $("#knowledgeId1").val() && $("#courseId1").val() && $("#ibquestion1").val() && $("#optionA1").val() && $("#optionB1").val() && 
    			$("#optionC1").val() && $("#optionD1").val()  && $("#answer1").val())!="" && ($(":radio[name='answer1']:checked").val()!=null) ){
    		//试题名称查询
    	    $.ajax({
    	        type:"post",
    	        url:"${pageContext.request.contextPath }/itemBank/getItemBankByQuestion.action",
    	        data:{"question":$("#ibquestion1").val()},
    	        success:function(data) { 
    	       	  //名称存在
    	          if(data=="ok"){
    	      		alert("试题已存在");
    	    		return false;
    	          }else{//名称不存在
    	  			$.post("${pageContext.request.contextPath }/itemBank/addItemBank.action",$("#add__itemBank_form").serialize(),function(data){
    					if(data=="ok"){
    						alert("添加成功！");
    						window.location.reload();
    					}else{
    						alert("添加失败！");
    						window.location.reload();
    					}
    				});
    	          }
    	        }
    	    });
    	}else{
    		alert("信息填写不全");
    		return false;
    	}
	}
	// 一级联动操作
	function selectField(fieldId,one) {

		//判断行业id是否为空
		//if(fieldId !=null && fieldId!="" && $("#"+one).val()==null){
			$("#"+one).empty();
		     $.ajax({
		            url : "${pageContext.request.contextPath }/field/fields.action",
		            type : 'post',
		            dataType : 'json',
		            async : false,
		            //contentType: "application/json; charset=utf-8",
		            //返回集合
		            success : function(data) {
		                console.log(data);                  
		                   if(data!=null){
		                       $("#"+one).prepend("<option value=''>--请选择--</option>");
		                        $.each(data,function(i,item){
		                              $.each(item,function(j,val){
		                            	  if(fieldId ==val.fieldId){
		                            		  //alert(fieldId);
		                            		  $("#"+one).append("<option selected value="+val.fieldId+">"+val.fieldName+"</option>");    
		                            	  }else{
		                               		  $("#"+one).append("<option value="+val.fieldId+">"+val.fieldName+"</option>");    
		                                  }
		                                                                      
		                                })

		                         }); 
		                    }                                                    
		           }
		     })
	   // }
	}
    //二级联动
	function selectCourses(one,two,twoHidden,three,threeHidden){
		console.log("one="+one);  
	    var object = $("#"+one).val();
	    console.log("object="+object);   
		if(object !="" && object!=null){
			$("#"+two).empty();
		     $.ajax({
		   		 url : "${pageContext.request.contextPath }/itemBank/getCourseByField.action?fieldId="+object,
				 type : 'post',
		 	 	 dataType : 'json',
		    	 async : false,
		          //contentType: "application/json; charset=utf-8",
		         success : function(data) {
		               console.log(data);                  
		               if(data!=null){
			               $("#"+two).prepend("<option value=''>--请选择--</option>");
			               $.each(data,function(i,item){
			                  	$.each(item,function(j,val){
				                    if($("#"+twoHidden).val()==val.courseId){
				                         $("#"+two).append("<option selected value="+val.courseId+">"+val.courseName+"</option>");    
				                    }else{
				                         $("#"+two).append("<option value="+val.courseId+">"+val.courseName+"</option>");    
				                    }                               
			          	  		 })
	
			     	 		 }); 
		     	  		}                                                    
		           }
		       })
		}else{//当行业为空时，课程和知识点页清空
			$("#"+two).empty(); 
			$("#"+three).empty();
			$("#"+two).prepend("<option value=''>--请选择--</option>");
			$("#"+three).prepend("<option value=''>--请选择--</option>");
		}
	    selectKnowledge(two,three,threeHidden);    
	}
 
    // 三级联动操作
	function selectKnowledge(two,three,threeHidden) {
	    var courseForm =  $("#"+two).val();
		if(courseForm !=null){
		 $("#"+three).empty();
		     $.ajax({
		            url : "${pageContext.request.contextPath }/itemBank/getKnowledgeByCourse.action?courseId="+courseForm,
		            type : 'post',
		            dataType : 'json',
		            async : false,
		            //contentType: "application/json; charset=utf-8",
		            success : function(data) {
		                console.log(data);                  
		                   if(data!=null){
		                       $("#"+three).prepend("<option value=''>--请选择--</option>");
		                        $.each(data,function(i,item){
		                              $.each(item,function(j,val){
		                            	  if($("#"+threeHidden).val()==val.knowledgeId){
		                            		  $("#"+three).append("<option selected value="+val.knowledgeId+">"+val.knowledgeName+"</option>");    
		                            	  }else{
		                               		  $("#"+three).append("<option value="+val.knowledgeId+">"+val.knowledgeName+"</option>");    
		                                  }
		                                                                      
		                                })

		                         }); 
		                    }                                                    
		           }
		     })
	    }	     
	}
	// 通过id获取试题信息(修改前查询)
	function getItemBank(id) {
	    $.ajax({
	        type:"post",
	        url:"${pageContext.request.contextPath }/itemBank/itemBank.action",
	        data:{"itemBankId":id},
	        success:function(data) { 
	        	$("#itemBankId").val(data.ibId);
	            $("#question_update").val(data.question);
	            $("#optionA_update").val(data.optionA);
	            $("#optionB_update").val(data.optionB);
	            $("#optionC_update").val(data.optionC);
	            $("#optionD_update").val(data.optionD);
	            $("#courseUpdate").val(data.course.courseId);
	            $("#knowledgeUpdate").val(data.knowledge.knowledgeId);
	            ///alert(data.field.fieldId,'fieldId2');
	            //查询行业选中
	            selectField(data.field.fieldId,'fieldId2');
	            //根据行业查课程和知识点
	            selectCourses('fieldId2','courseId2','courseUpdate','knowledgeId2','knowledgeUpdate');
	            //设置选中单选按钮
	            $("input[name='answer2']").each(function(i){
                    if($(this).val()==data.answer)
                    {
                    	$(this).attr("checked",true);
                     
                    }
                });
	        }
	    });
	}
	//Excel上传校验form表单信息  
	function checkData(){  
	   var fileDir = $("#excelFile").val();  
	   var suffix = fileDir.substr(fileDir.lastIndexOf("."));  
	   if("" == fileDir){  
	       alert("请选择需要导入的Excel文件！"); 
	       return false;  
	   }  
	   if(".xls" != suffix && ".xlsx" != suffix ){  
	       alert("请选择Excel格式的文件导入！");  
	       return false;  
	   }  
	   return true;  
	}
	//Excel批量上传
	$("#fileForm").click(function(){  
		   if(checkData()){  
		    	$.ajaxFileUpload({
		    		//alert("2");
		            url:"${pageContext.request.contextPath}/itemBank/importExcelItemBank.action",
		            type:"post",
		            fileElementId :"excelFile",
		            success:function (data) {
		            	var str = $(data).find("body").text();//获取返回的字符串
		                var json = $.parseJSON(str);//把字符串转化为json对象
		            	$.each(json,function(i,val){		            		
		            		if(val.success==null &&  val.duplicate==null && val.error==null && val.nothingness==null){
		            			alert("格式错误");
		            			return false;
		            		}else if(val.success!=0 && val.success!=null){
		            			alert("成功导入"+val.success+"条数据");
		            			window.location.href="${pageContext.request.contextPath}/itemBank/list.action";
		            			return false;
		            		}else if(val.success==0 &&  val.duplicate==null && val.error==null && val.nothingness==null){
		            			alert("格式错误");
		            			return false;
		            		}else{
		            			$(".getWrong").css("display","block");
		            			var $duplicate = "";
		            			var $error="";
		            			var $nothingness="";
		            			if(val.duplicate!=null && val.duplicate!=""){
		            				$duplicate ="试题重复:请检查Excel第" +val.duplicate+"行(试题已存在)" +"</br><hr>";
		            			}
		            			if(val.error!=null && val.error!=""){
		            				$error="格式错误:请检查Excel第"+val.error+"行数据(数据格式错误)"+"</br><hr>";
		            			}
		            			if(val.nothingness!=null && val.nothingness!=""){
		            				$nothingness="编号错误:请检查Excel第"+val.nothingness+"行(查正行业或课程或知识点编号是否存在)"+"</br><hr>";
		            			}
			            		$("#detailWrong").html($duplicate+$error+$nothingness);	            			
		            			return false;
		            		}	                                                                      
	                    }); 		      
		            },
		            error:function(erro){
		                console.log(erro);
		            }
		        });
		    }       
	 });
	//关闭错误信息
	function clcoseGetWrong(){
		$("#getWrongById").css("display","none");
	}
	
	

</script>
</body>
</html>
