<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
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
	content="1483758872##fd2cac389b2b7c009a744bcaecaa41d71592cfe8">

<%-- 首页 可以用于行业展示 --%>
<title>在线考试首页</title>

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
</style>

<style style="text/css">
.navbar-banner {
	margin-top: 50px;
	background: url("${pageContext.request.contextPath}/img/ui/homepage-bg.png");
	background-size: cover;
	backgtound-repeat: no-repeat;
}
.footer_quanzhan{
	margin-top: 0px;
    border-top: none;
    padding-top: 0px;
    width: 100%;
    background: #fafafa;
}
.footer_quanzhan .footer_f {
    clear: both;
    width: 100%;
    line-height: 24px;
    padding-top: 15px;
    text-align: center;
    font-size: 12px;
    color: #999;
    font-family: "微软雅黑","microsoft yahei",Arial,Helvetica,sans-serif;
    text-decoration: none;
}
.h20 {
    width: 100%;
    height: 20px;
    overflow: hidden;
    clear: both;
}
.fl {
    display: inline;
    float: left;
}
.study_step_bg {
    width: 100%;
    height: 133px;
    background: #fff;
    border-bottom: 1px solid #d8d8d8;
    border-top: 1px solid #ededed;
}
.width1000 {
    width: 1000px;
    margin: 0 auto;
}
.study_step_title {
    width: 244px;
    padding-top: 40px;
}
.study_step_title ul {
    width: 100%;
    padding-top: 9px;
}
.study_step_title ul {
    width: 100%;
    padding-top: 9px;
}
.study_step_title li {
    float: left;
    margin-right: 8px;
}
ul, li {
    list-style-type: none;
}
.study_step_row {
    width: 24px;
    height: 24px;
    margin: 55px 40px 0 16px;
    background: url(${pageContext.request.contextPath}/images/ui/index_step_sign.png) no-repeat;
}
.step_con1 {
    width: 170px;
    padding-top: 45px;
}
.study_step_title li a {
    display: block;
    height: 30px;
    line-height: 30px;
    padding: 0 20px;
    font-size: 15px;
    border-radius: 35px;
    color: #16c216;
    border: 1px solid #e9e9e9;
}
.study_step_title li a:hover {
    background: #16c216;
    color: #fff;
    border: 1px solid #16c216;
    text-decoration: none;

}
.layui-nav-bar{
	top:50px;
}
</style>
	<script
		src="${pageContext.request.contextPath}/js/ui/jquery.min.js"></script>
<script type="text/javascript">
	$(function(){
		var url=window.location.href;                    //获取当前页面的url
		if('yes'=='${sc}'){
			alert("您的考试成绩为："+'${fraction}'+"分");
			if(url.indexOf("?")!=-1){                        //判断是否存在参数
				url = url.replace(/(\?|#)[^'"]*/, '');           //去除参数
				window.history.pushState({},0,url);
			}
		}
		if('yes'=='${tip}'){
			alert("修改成功，请重新登录！");
			if(url.indexOf("?")!=-1){                        //判断是否存在参数
				url = url.replace(/(\?|#)[^'"]*/, '');           //去除参数
				window.history.pushState({},0,url);
			}
		}
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
						<a  style="color: #fff">首页</a></li>
				</ul>
				<div class="navbar-right btns" style="${sessionScope.user != null ?'display: none;':''}">
					<a class="btn btn-default navbar-btn sign-in" data-sign="signin"
						href="${pageContext.request.contextPath }/login/toLogin" data-toggle="modal" style="color: #fff;background-color: #16c216;">登录
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
	<div class="line-and-laboratory" style="background-image: linear-gradient(#a6e1a5 10%, #fafafa 40%);">
		<div class="container">
			<div class="clearfix text-center item-header" style="background-color: #fff;padding-bottom: 60px;margin-bottom: 0">
				<br><br>
				<br>
				<br>
				<span class="line"></span> 
				<span class="text-center item-title" style="width: 300px">在线考试系统</span>
				<span class="line"></span>
				<br>
				<br>
				<span class="text-center item-title" style="width: 500px;font-size: 20px;">简单易用的智能在线考试系统，节省大量考试成本</span>
				<br>
				<br>
			</div>
			<div class="tab-content">
				<div class="tab-pane clearfix active" id="study-line" style="background-color: #fff;padding-bottom: 80px;">
					<%-- 行业展示  start--%>
					<c:forEach var="field" items="${fieldList}">
						<div class="col-xs-12 col-sm-6 col-md-4">
							<c:choose>
								<c:when test="${sessionScope.user != null}">
								<a href="${pageContext.request.contextPath}/ui/classList/${field.fieldId}.action">
								</c:when>
								<c:otherwise>
								<a data-sign="signin" data-toggle="modal" href="#sign-modal">	
								</c:otherwise>
							</c:choose>
								<div class="path-item">
									<div class="col-xs-7 col-sm-8">
										<div class="path-name">${field.fieldName}</div>
										<div class="path-course-num">
											<span>${fn:length(field.courseList)}</span> 门课程
										</div>
									</div>
									<div class="desc-layer"  style="background: hsla(229, 28%, 52%, 0.82);">
										<div class="center">${field.introduce}</div>
									</div>
								</div>
							</a>
						</div>
					</c:forEach>
				</div>
			</div>
		</div>
	</div>
	<div class="h20"></div>
	<div class="h20"></div>
	<!-- 内容 end -->
	<div class="study_step_bg" style="position: relative;bottom: 0">
		<div class="width1000">
			<div class="study_step_title fl">
				<h3>考试过关从这里开始</h3>
				<ul class="userlogin" style="${sessionScope.user != null ?'display: none;':''}">
					<li><a data-sign="signin"
						href="#sign-modal" data-toggle="modal">登录</a></li>
				</ul>
			</div>
			<div class="study_step_row fl"></div>
			<div class="step_con1 fl">
				<h3><span style="font-size: 30px;color: #46ff06;">01.</span>选择行业</h3>
				<P>根据所需选择行业</P>
			</div>
			<div class="study_step_row fl"></div>
			<div class="step_con1 fl">
				<h3><span style="font-size: 30px;color: #46ff06;">02.</span>选择课程</h3>
				<P>根据所需选择课程</P>
			</div>
			<div class="study_step_row fl"></div>
			<div class="step_con1 fl">
				<h3><span style="font-size: 30px;color: #46ff06;">03.</span>开始考试</h3>
				<P>在线考试功能</P>
			</div>
		</div>
	</div>
	<!-- 页脚star -->
	<div class="footer_quanzhan" style="position: relative;bottom: 0;background:#4C5157;color:#ccc">
		<div class="h20"></div>
		<div class="footer_f" style="color:#fff">
			<span style="color:#ccc">关于我们</span>
			&nbsp;&nbsp;|&nbsp;
			<span style="color:#ccc">免责声明</span>
			&nbsp;&nbsp;|&nbsp;
			<span style="color:#ccc">在线考试</span>
			&nbsp;&nbsp;|&nbsp;
			<span style="color:#ccc">网站导航</span>
			&nbsp;&nbsp;|&nbsp;
			<span style="color:#ccc">在线判卷</span>
			&nbsp;&nbsp;&nbsp;&nbsp;  版权所有  ©2020-2030  &nbsp;&nbsp;&nbsp;&nbsp;
		</div>
		<div class="h20"></div>
	</div>
	<!-- 页脚end -->
	
	

	<div id="base-data" data-flash="false" data-is-login=false
		data-is-jwt=true data-socket-url="wss://comet.xxxxxx.com"
		data-msg-user="" data-msg-system=""></div>
	<script src="${pageContext.request.contextPath }/layui/layui.js" type="text/javascript" charset="utf-8"></script>
 	<script type="text/javascript" src="${pageContext.request.contextPath }/js/admin/xadmin.js"></script>
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
	<div id="jinja-data" data-post-url="/registercheck"></div>

	<script
		src="${pageContext.request.contextPath}/js/ui/index.js?=2016121272249"></script>
	
</body>
</html>
