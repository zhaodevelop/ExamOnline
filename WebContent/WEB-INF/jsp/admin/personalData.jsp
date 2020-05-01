<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
	<script src="${pageContext.request.contextPath }/layui/layui.js" charset="utf-8"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath }/js/admin/xadmin.js"></script>
<style type="text/css">
body{
background-color: rgba(0,0,0,0.1);
}
.bj{
	height: 600px;
	width: 700px;
	margin: 0 auto;
	margin-top:20px;
	background-color: white;
}
.bj table{
	color:black;
	margin: 0 auto;
	margin-left: 72px;
	text-align: center;
	width: 600px;
	line-height: 40px;
}

.photo{
	width:138px;
	height:138px;
	border-radius:50%;
	overflow:hidden;
	margin: 0 auto;
}
.photo img{
	width:138px;
	height:138px;
}

button {
    width: 120px;
    height: 31px;
    color: #f5f5f5;
    font-size: 14px;
    border: none;
   	background-color: rgba(0,150,136,0.8);
    margin-top: 12px;
    margin-left: 70px;
    border-radius:10px 10px 10px 10px;
    float: left;
}

.an button:hover{
	background-color: rgba(0,150,136,0.7);
}
.an button:active{
	background-color: rgba(0,150,136,0.6);
}

.an{
	margin-left:120px;
	text-align: center;		
}
</style>
</head>
<body>
	<div class="bj">
		<h2 style="text-align: center;">个人信息</h2>
		<div class="photo" ><img alt="头像" src="${pageContext.request.contextPath }/${sessionScope.user.headImg}"></div>
		<!-- src应为${sessionScope.user.headImg} -->
		<hr>
		<table>
			<tr>
				<td>账&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;号</td>
				<td>${sessionScope.user.account}</td>
			</tr>
			<tr>
				<td>用户名称</td>
				<td>${sessionScope.user.userName}</td>
			</tr>
			<tr>
				<td>性&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;别</td>
				<td>${sessionScope.user.sex}</td>
			</tr>
			<tr>
				<td>手&nbsp;机&nbsp;号</td>
				<td>${sessionScope.user.phone}</td>
			</tr>
			<tr>
				<td>地&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;址</td>
				<td>${sessionScope.user.address}</td>
			</tr>
			<tr>
				<td>电子邮箱</td>
				<td>${sessionScope.user.email}</td>
			</tr>
		</table>
		
		<div class = "an">
			<button onclick="xadmin.open('修改个人资料','${pageContext.request.contextPath }/personal/toUpdateData')" >修改个人资料</button>
			<button onclick="window.location.href='${pageContext.request.contextPath }/personal/toUpdatePass'">修改密码</button>
		</div>
	</div>
</body>
</html>