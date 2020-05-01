<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
			<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="author" content="Lei Shi">
<meta http-equiv="Cache-Control" content="o-transform">
<meta http-equiv="Cache-Control" content="no-siteapp">
<meta name="csrf-token"
	content="1483780974##87f89328c5616669f00302a263fe9061bb852818">

<%-- 知识点展示页 --%>
<title>知识点</title>



<meta content="" name="author">

<%--网页图标 --%>
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
 <link rel="stylesheet" href="${pageContext.request.contextPath }/layui/css/layui.css">
<style>
@font-face {
	font-family: "lantingxihei";
	src: url("${pageContext.request.contextPath}/css/ui/fonts/FZLTCXHJW.TTF");
}

/* modal 模态框*/
#invite-user .modal-body {
	overflow: hidden;
}

#invite-user .modal-body .form-label {
	margin-bottom: 16px;
	font-size: 14px;
}

#invite-user .modal-body .form-invite {
	width: 80%;
	padding: 6px 12px;
	background-color: #eeeeee;
	border: 1px solid #cccccc;
	border-radius: 5px;
	float: left;
	margin-right: 10px;
}

#invite-user .modal-body .msg-modal-style {
	background-color: #7dd383;
	margin-top: 10px;
	padding: 6px 0;
	text-align: center;
	width: 100%;
}

#invite-user .modal-body .modal-flash {
	position: absolute;
	top: 53px;
	right: 74px;
	z-index: 999;
}
/* end modal */
.en-footer {
	padding: 30px;
	text-align: center;
	font-size: 14px;
}
.bootcamp-infobox {
	margin: 50px 0 0;
}

.invite-friends-link {
	margin-top: 10px;
	padding-left: 8px;
}

.invite-friends-link input {
	margin-left: -5px;
}

.invite-friends-link button {
	float: left;
	margin-top: 5px;
	margin-left: -5px;
}

.invite-friends-link .copy-msg {
	float: left;
	margin-top: 10px;
	margin-left: 20px;
	color: #42b1ad;
}

p.join-vip-faq {
	margin-top: 20px;
	margin-bottom: -50px;
	font-size: 13px;
}

p.join-vip-faq img {
	height: 13px;
	width: 13px;
	margin-top: -2px;
}

a.vip-without-bean {
	font-size: 18px;
	line-height: 30px;
}
</style>
<script		src="${pageContext.request.contextPath}/js/ui/jquery.min.js"></script>
<script src="${pageContext.request.contextPath }/layui/layui.js" type="text/javascript" charset="utf-8"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/admin/xadmin.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath }/layui/css/layui.css">
<script type="text/javascript">
$(function(){
	$.ajax({
		type : "post",
		url:"${pageContext.request.contextPath}/exams/isHaveExams.action",
		data : {      
			userId : '${sessionScope.user.userId}' ,  
			courseId : '${course.courseId}'  
		},
		success : function(data){
			<%-- 判断该课程是否有考试    如果没有删除href、添加按钮禁用样式、文本设置成“暂无考试”
			haveExams:有考试    noHaveExams:没有考试    yesExams:您已经考过试了  --%>
			if(data == "noHavaExams"){
				$("#startExams").removeAttr("href").addClass("layui-btn-disabled").text("暂无考试");
			}else if(data == "yesExams"){
				$("#startExams").removeAttr("href").addClass("layui-btn-disabled").text("您已经答题");
			}
		}
	})
})
</script>


</head>

<body>
	<!-- 页头 start-->
	<nav style="background-color:#4c5157;border-radius: 2px;" class="navbar navbar-default navbar-fixed-top header">
		<div class="container">
			<div class="collapse navbar-collapse" id="header-navbar-collapse">
				<ul class="nav navbar-nav">
					<li class="dropdown">
						<a href="${pageContext.request.contextPath}/ui/ui" style="color: #fff">首页</a></li>
				</ul>
				<div class="navbar-right btns" style="${sessionScope.user != null ?'display: none;':''}">
					<a class="btn btn-default navbar-btn sign-in" data-sign="signin"
						href="#sign-modal" data-toggle="modal" style="color: #fff;background-color: #16c216;">登录
					</a>
				</div>
				<div style="${sessionScope.user != null ?'':'display: none;'};background-color:#4c5157;" class="layui-nav navbar-right btns">
				  	<div  class="layui-nav-item">
				    	<a href="javascript:;">
				    		<img style="width: 40px;height: 40px;margin-top: 5px;border-radius:50%;overflow:hidden;" 
				    		alt="头像" src="${pageContext.request.contextPath }/${sessionScope.user.headImg}">
				    	</a>
				    	<dl class="layui-nav-child" style="line-height: 36px;top: 50px;left: -4px;">
				   		<dd><div style="line-height: 36px"><a href="${pageContext.request.contextPath}/ui/info">个人中心</a></div></dd>
				      	<dd style="${sessionScope.user.role.roleId == 1 ?'':'display: none;'}"><div style="line-height: 36px"><a href="${pageContext.request.contextPath }/login/adminIndex">前往后台</a></div></dd>
				      	<dd><div style="line-height: 36px"><a href="${pageContext.request.contextPath }/ui/toExit">退出登录</a></div></dd>
				    	</dl>
				  	</div>
				</div>
			</div>
		</div>
	</nav>
	<!-- 页头 end-->

	<!-- 内容 start -->
	<div class="container layout layout-margin-top">
		<ol class="breadcrumb">
			<%-- 知识点   所属课程及所属行业  --%>
			<li><a
				href="${pageContext.request.contextPath}/ui/classList/${course.field.fieldId}.action">${course.field.fieldName}</a></li>
			<%--知识点  所属课程 --%>
			<li class="active"><a
				href="${pageContext.request.contextPath}/ui/knowledgeList/${course.courseId}.action">
					${course.courseName}</a></li>
		</ol>

		<div class="row">
			<div class="col-md-9 layout-body">
				<div class="content" style="padding: 0px">
					<div class="path-description" style="margin: 0px">
						<div class="path-desc-top clearfix"
							style="background: url('${pageContext.request.contextPath}/images/ui/1471513740139.png') no-repeat;background-size: cover;">
							<div class="name-total-box clearfix">

								<%-- 本行业信息详细展示 --%>
								<div class="col-md-6 col-sm-6 col-md-offset-1 path-name">
									<h4>${course.courseName}</h4>
								</div>
							</div>
							<div class="col-md-10 col-md-offset-1 path-desc-text" style="font-size: 15px">
								<c:choose>
									<c:when test="${matTime != null}">
										距离本课程下一场考试还有<b class="alt-1" style="font-size: 25px">${matTime}</b>秒
									</c:when>
								</c:choose>
							</div>
						</div>
					</div>
				</div>
				<div class="content">
					<ul class="nav nav-tabs" role="tablist">
						<li role="presentation" class="active"><a>在线考试</a></li>
					</ul>
					<div class="tab-content">
						<a id="startExams" href="${pageContext.request.contextPath}/exams/startExams/${course.courseId}.action"
							class="btn start-btn">开始考试</a>	
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- 内容 end -->

	<div id="base-data" data-flash="false" data-is-login=false
		data-is-jwt=true data-socket-url="wss://comet.xxxxxx.com"
		data-msg-user="" data-msg-system=""></div>

	<script
		src="${pageContext.request.contextPath}/js/ui/lib.js?=2016121272249"></script>
	<script
		src="${pageContext.request.contextPath}/js/ui/jquery.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/js/ui/ace.js"></script>
	<script
		src="${pageContext.request.contextPath}/js/ui/aliyun-oss-sdk-4.3.0.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/js/ui/highlight.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/js/ui/jspdf.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/js/ui/plupload.full.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/js/ui/ZeroClipboard.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/js/ui/video.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/js/ui/bootstrap-tour.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/js/ui/bootstrap-table.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/js/ui/bootstrap-table-zh-CN.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/js/ui/bootstrap-table-filter-control.min.js"></script>

	<script
		src="${pageContext.request.contextPath}/js/ui/raven.min.js"></script>
	<script>
		Raven.config(
				'https://bc3878b7ed0a4468a65390bd79e6e73f@sentry.xxxxxx.com/5',
				{
					release : '3.12.13'
				}).install();
	</script>
	<script
		src="${pageContext.request.contextPath}/js/ui/index.js?=2016121272249"></script>
		<!--时间js-->
<script
	src="${pageContext.request.contextPath}/js/ui/jquery.countdown.js"></script>
<script type="text/javascript">
	window.jQuery(function($) {
		"use strict";

		$('time').countDown({
			with_separators : false
		});
		$('.alt-1').countDown({
			css_class : 'countdown-alt-1'
		});
		$('.alt-2').countDown({
			css_class : 'countdown-alt-2'
		})
	});

	$(function() {
		$('li.option label').click(
				function() {
					var examId = $(this).closest('.test_content_nr_main')
							.closest('li').attr('id'); <%--得到题目ID--%>

					var cardLi = $('a[href=#' + examId + ']'); <%--根据题目ID找到对应答题卡--%>
					<%--设置已答题--%>
					if (!cardLi.hasClass('hasBeenAnswer')) {
						cardLi.addClass('hasBeenAnswer');
					}

		});
		
		<%--DOM元素加载完后初始化倒计时--%>
		$('div, h1, time').countDown();
		<%--每一秒触发的事件--%>
		$('.countdown-alt-1').on('time.tick', function(ev, ms) {
			// do something...
		});
		<%--倒计时结束之后   触发的事件  可以用于自动提交--%>
		$('.countdown-alt-1').on('time.elapsed', function() {			
			// do something...
				setTimeout(function(){
					$("div.path-desc-text").remove();
				 	$.ajax({
						type : "post",
						url:"${pageContext.request.contextPath}/exams/isHaveExams.action",
						data : {      
							userId : '${sessionScope.user.userId}' ,  
							courseId : '${course.courseId}'  
						},
						success : function(data){
							<%-- 判断该课程是否有考试    如果没有删除href、添加按钮禁用样式、文本设置成“暂无考试”
							haveExams:有考试    noHaveExams:没有考试    yesExams:您已经考过试了  --%>
							if(data == "noHavaExams"){
								$("#startExams").removeAttr("href").addClass("layui-btn-disabled").text("暂无考试");
							}else if(data == "yesExams"){
								$("#startExams").removeAttr("href").addClass("layui-btn-disabled").text("您已经答题");
							}else if(data == "havaExams"){
								$("#startExams").removeClass("layui-btn-disabled").attr("href","${pageContext.request.contextPath}/exams/startExams/${course.courseId}.action").addClass("btn start-btn").text("开始考试");
							}
						}
					})
				},1500);
		});
		
	});
</script>
	<!-- 页脚 -->
	<div class="footers">
		<div class="row" style="margin-right:0px;">
			<div class="text-center copyright" style="margin-top: 82px;">
				<span>Copyright @2020-2030 在线考试</span> 
			</div>
		</div>
	</div>




</body>
</html>
