<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>个人中心公共列表</title>
<%--网页图标 --%>
<link rel="shortcut icon" href="${pageContext.request.contextPath}/images/ui/favicon.ico">
<script type="text/javascript" src="${pageContext.request.contextPath}/PC/js/jquery-1.7.min.js"></script>
</head>
<body>
	<div class="left_list">
            <div class="cz_title">
            	<span>账户管理</span>
            </div>
             <div class="cz_nr" id="userTitle">
            	<span><a href="${pageContext.request.contextPath}/center/ui/info1" style="color: black;">账户信息</a></span>
            </div>
             <div class="cz_nr" id="userPass">
            	<span><a href="${pageContext.request.contextPath}/center/ui/password" style="color: black;">修改密码</a></span>
            </div>
             <div class="cz_nr" id="wrong">
            	<span><a href="${pageContext.request.contextPath}/center/wrong.action" style="color: black;">历史做题记录</a></span>
            </div>
           
        </div>
</body>
</html>