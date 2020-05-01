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

<%-- 课程展示页 --%>
<title>课程</title>



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
<script src="${pageContext.request.contextPath }/layui/layui.js" type="text/javascript" charset="utf-8"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/admin/xadmin.js"></script>
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
</style>


</head>

<body style="font: 14px Helvetica Neue;">

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
			<%-- 本课程 --%>
			<li class="active"><a
				href="${pageContext.request.contextPath}/ui/classList/${field.fieldId}.action">${field.fieldName}</a></li>
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
									<h4>${field.fieldName}</h4>
								</div>
								<div
									class="col-md-3 col-sm-6 col-md-offset-1 path-total-courses">
									<span class="total-courses-box">课程 <span class="num">${fn:length(field.courseList)}</span></span>
								</div>
							</div>
							<div class="col-md-10 col-md-offset-1 path-desc-text">${field.introduce}</div>
						</div>
					</div>
				</div>
				<div class="content">
					<div class="tab-content">
						<div role="tabpanel" class="tab-pane path-details active"
							id="path-details">
							<div class="details-box">
								<div class="row">
									<%-- 本行业 课程展示  start --%>
									<c:forEach var="course" items="${field.courseList}">
										<div class="col-md-4 col-sm-6  course">
											<a class="course-box" style="min-height: 60px"
												href="${pageContext.request.contextPath}/ui/knowledgeList/${course.courseId}.action">
												<div class="course-body">
													<span class="course-title" data-toggle="tooltip"
														data-placement="bottom" title="${course.courseName}">${course.courseName}</span>
												</div>
											</a>
										</div>
									</c:forEach>
									<%-- 本行业 课程展示  end --%>
								</div>
							</div>
						</div>
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
	<script type="text/javascript">
		Raven.config(
				'https://bc3878b7ed0a4468a65390bd79e6e73f@sentry.xxxxxx.com/5',
				{
					release : '3.12.13'
				}).install();
	</script>
	<div id="jinja-data" data-user-id="0" data-user-in-path="false"
		data-join-path-url="/jobs/9/join" data-leave-path-url="/jobs/9/leave"
		data-path-comment-url="/jobs/9/comment" data-user-logined="false"></div>
	<script
		src="${pageContext.request.contextPath}/js/ui/index.js?=2016121272249"></script>

	<!-- 页脚 -->
	<div class="footers" >
		<div class="row" style="margin-right:0px;">
			<div class="text-center copyright" style="margin-top: 82px;">
				<span>Copyright @2020-2030 在线考试</span> 
			</div>
		</div>
	</div>
</body>
</html>
