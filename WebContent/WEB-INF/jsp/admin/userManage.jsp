<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="itheima" uri="http://systop.com/common/"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ page import="java.net.URLEncoder"%>
<html>
<head>
<meta http-equiv="Content-Type"
	content="multipart/form-data; charset=utf-8" />
<title>用户列表</title>
<meta charset="UTF-8">
<style type="text/css">
.a-upload {
	padding: 4px 10px;
	height: 34px;
	line-height: 25px;
	position: relative;
	cursor: pointer;
	color: #888;
	background: #fafafa;
	border: 1px solid #ddd;
	border-radius: 4px;
	display: inline-block;
	*display: inline;
	*zoom: 1;
	color: #fff;
	background-color: #337ab7;
	border-color: #2e6da4;
}

.a-upload  input {
	position: absolute;
	width: 100px;
	right: 0;
	top: 0;
	opacity: 0;
	filter: alpha(opacity = 0);
	cursor: pointer;
	color: #fff;
	background-color: #337ab7;
	border-color: #2e6da4;
}

.a-upload:hover {
	color: #444;
	background: #eee;
	border-color: #ccc;
	text-decoration: none
}

.getWrong {
	width: 500px;
	display: none;
	position: absolute;
	top: 30px;
	/*display:block;*/
	left: 300px;
	background-color: #fff;
	background-clip: padding-box;
	border: 1px solid rgba(0, 0, 0, .2);
	border-radius: 6px;
	outline: 0;
	margin: 30px auto;
	transform: translate(0, 0);
	box-shadow: 0 5px 15px rgba(0, 0, 0, .5);
	OVERFLOW-Y: auto;
	OVERFLOW-X: hidden;
	z-index: 10000;
}

.detailWrong {
	padding-top: 20px;
	height: 500px;
	margin: 0 auto;
	margin-bottom: 20px;
}
</style>
<script
	src="${pageContext.request.contextPath }/js/admin/jquery-1.11.3.min.js"
	charset="utf-8"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/admin/bootstrap.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/admin/ajaxfileupload.js"></script>

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
	<div id="page-wrapper">
		<div class="row">
			<div class="col-lg-12">
				<h1 class="page-header" style="margin-top: 20px">用户管理</h1>
			</div>
		</div>
		<div class="panel panel-default">
			<div class="panel-body">
				<form class="form-inline" method="post"
					action="${pageContext.request.contextPath }/User/allUser">
					<div class="form-group">
						<label for="customerName">用户名称</label> <input type="text"
							class="form-control" id="name" value="${userName }"
							name="userNamee" />
					</div>
					&nbsp;&nbsp;
					<div class="form-group">
						<label for="customerName">登录账号</label> <input type="text"
							class="form-control" id="login" value="${account }"
							name="account" />
					</div>
					&nbsp;&nbsp;
					<div class="form-group">
						<label for="customerFrom">角色</label> <select class="form-control"
							id="power" name="power">
							<option value="">--请选择--</option>
							<option id="mana" value="1">管理员</option>
							<option id="work" value="2">员工</option>

						</select>
					</div>
					&nbsp;&nbsp;
					<button type="submit" class="btn btn-primary">查询</button>
					<a href="${pageContext.request.contextPath }/User/allUser"
						class="btn btn-primary">查询全部</a>
				</form>
				<div
					style="width: 500px; float: left; margin-top: 5px; height: 32px;">
					<form method="post" enctype="multipart/form-data" id="form1">
						<a href="javascript:;" class="a-upload"> <input type="file"
							name="file" class="btn btn-primary" id="excelFile">Excel批量添加>>
						</a> <input type="button" id="fileForm"
							style="margin-top: -3px; margin-left: 10px;"
							class="btn btn-primary" value="上传"> <a
							class="btn btn-primary"
							style="margin-top: -3px; margin-left: 10px;"
							href='${pageContext.request.contextPath }/User/download?filename=<%=URLEncoder.encode("批量添加用户样板.xlsx","UTF-8")%>'>
							上传模板下载 </a>
					</form>
				</div>
			</div>
		</div>

		<div>
			<a href="#myModal" class="btn btn-primary" data-toggle="modal"
				onclick="clearUser()">新建</a> <a href="#"
				style="width: 80px; height: 33px; line-height: 33px; font-size: 14px;"
				class="btn btn-danger btn-xs" data-toggle="modal"
				onclick="deleteMore()">批量删除</a>
		</div>

		<div class="row">
			<div class="col-lg-12">
				<div class="panel panel-default">
					<div class="panel-heading">用户信息列表</div>
					<c:choose>
						<c:when test="${fn:length(page.result)>0}">
							<table class="table table-bordered table-striped">
								<thead>
									<tr>
										<th><input type="checkbox" id="qx" /><span>全选</span></th>
										<th>用户id</th>
										<th>用户角色</th>
										<th>用户姓名</th>
										<th>账号</th>
										<th>性别</th>
										<th>电话</th>
										<th>联系地址</th>
										<th>邮箱</th>
										<th>操作</th>
									</tr>
								</thead>
								<c:forEach items="${page.result }" var="user">
									<tr style="text-align: center;">
										<td><input type="checkbox" value=${user.userId }
											class="person" id="person" /></td>
										<td>${user.userId }</td>
										<td>${user.role.roleName }</td>
										<td>${user.userName }</td>
										<td>${user.account }</td>
										<td>${user.sex }</td>
										<td>${user.phone }</td>
										<td>${user.address}</td>
										<td>${user.email}</td>
										<td style="text-align: center"><a href="#" id="delbtn"
											data-toggle="modal" class="btn btn-danger btn-xs"
											onclick="delSingle(${user.userId})">删除</a> <a href="#"
											data-toggle="modal" data-target="#updateUser"
											class="btn btn-primary btn-xs"
											onclick="editCustomer(${user.userId});clearUpdateUser()">修改</a></td>
									</tr>
								</c:forEach>
							</table>
							<div class="col-md-12 text-right">
								<itheima:page
									url="${pageContext.request.contextPath }/User/allUser" />
							</div>
						</c:when>
						<c:otherwise>
							<div style="text-align: center; margin: 50px 0; font-size: 18px;">
								抱歉,没有找到相关用户信息</div>
						</c:otherwise>
					</c:choose>
				</div>
			</div>
		</div>
	</div>
	<!-- start-创建客户模态框-start  -->
	<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel">
		<div class="modal-dialog">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
						<h4 class="modal-title" id="myModalLabel">新建用户信息</h4>
					</div>
					<div class="modal-body">
						<form class="form-horizontal" id="addForm"
							action="${pageContext.request.contextPath }/User/add"
							method="post" enctype="multipart/form-data"
							onsubmit="return checkAll()">
							<div class="form-group">
								<label for="new_customerName" class="col-sm-2 control-label">
									<span style="color: red; font-size: 15px;">*</span>&nbsp;用户名
								</label>
								<div class="col-sm-10">
									<input type="text" class="form-control" class="form-control"
										id="userName1" placeholder="用户名" name="userName"
										onblur="userTest()" /> <span id="usTip"
										style="color: red; font-size: 15px;"></span>
								</div>
							</div>
							<div class="form-group">
								<label for="new_customerName" class="col-sm-2 control-label">
									<span style="color: red; font-size: 15px;">*</span>&nbsp;账号
								</label>
								<div class="col-sm-10">
									<input type="text" class="form-control" placeholder="账号"
										id="account1" name="account" onblur="acTest()" /> <span
										id="accTip" style="color: red; font-size: 15px;"></span>
								</div>

							</div>
							<div class="form-group">
								<label for="new_phone" class="col-sm-2 control-label"><span
									style="color: red; font-size: 15px;">*</span>&nbsp;密码</label>
								<div class="col-sm-10">
									<input type="password" class="form-control" id="passWord1"
										placeholder="密码" name="passWord" onblur="pwdlength()" /> <span
										id="addpwdTip" style="color: red; font-size: 15px;"></span>
								</div>
							</div>
							<div class="form-group">
								<label for="new_mobile" class="col-sm-2 control-label">重复密码</label>
								<div class="col-sm-10">
									<input type="password" class="form-control" id="repassword1"
										placeholder="重复密码" name="repassword" onblur="pwdTest()" /> <span
										id="repwdTip" style="color: red; font-size: 15px;"></span>
								</div>
							</div>
							<div class="form-group">
								<label for="new_phone" class="col-sm-2 control-label"><span
									style="color: red; font-size: 15px;">*</span>&nbsp;性别</label> <label
									class="radio-inline"> &nbsp;&nbsp;&nbsp;&nbsp;<input
									type="radio" id="man1" value="男" required="required" name="sex">男
								</label> <label class="radio-inline"> <input type="radio"
									required="required" id="woman1" value="女" name="sex">女
								</label>
							</div>
							<div class="form-group">
								<label for="new_mobile" class="col-sm-2 control-label"><span
									style="color: red; font-size: 15px;">*</span>&nbsp;手机号码</label>
								<div class="col-sm-10">
									<input type="text" class="form-control" id="phone1"
										placeholder="手机号码" name="phone" onblur="phoneTest()" /> <span
										id="phTip" style="color: red; font-size: 15px;"></span>
								</div>
							</div>
							<div class="form-group">
								<label for="new_mobile" class="col-sm-2 control-label"><span
									style="color: red; font-size: 15px;">*</span>&nbsp;地址名</label>
								<div class="col-sm-10">
									<input type="text" class="form-control" id="address1"
										placeholder="地址名" name="address" onblur="addressTest()" /> <span
										id="address1Tip" style="color: red; font-size: 15px;"></span>
								</div>
							</div>
							<div class="form-group">
								<label for="new_mobile" class="col-sm-2 control-label"><span
									style="color: red; font-size: 15px;">*</span>邮箱</label>
								<div class="col-sm-10">
									<input type="text" class="form-control" id="email1"
										placeholder="邮箱" name="email" /> <span id="emTip"
										style="color: red; font-size: 15px;"></span>
								</div>
							</div>
							<div class="form-group">
								<label for="new_mobile" class="col-sm-2 control-label">头像</label>
								<div class="col-sm-10">
									<a class="a-upload">上传头像 <input type="file" name="headimg"
										placeholder="请输入头像" onchange="checkImgType ()" id="headimg1"></a>
								</div>

							</div>
							<div class="form-group">
								<label for="new_mobile" class="col-sm-2 control-label"><span
									style="color: red; font-size: 15px;">*</span>&nbsp;角色</label> <label
									class="radio-inline">&nbsp;&nbsp;&nbsp;&nbsp;<input
									type="radio" required="required" id="master" value="1"
									name="role.roleId">管理员
								</label> <label class="radio-inline"> <input type="radio"
									required="required" id="harder" value="2" name="role.roleId">员工
								</label>
							</div>
							<div class="modal-footer">
								<button type="button" class="btn btn-default"
									data-dismiss="modal">关闭</button>
								<input type="submit" class="btn btn-primary">
							</div>
						</form>
					</div>

				</div>
			</div>
		</div>
	</div>
	<!-- end-创建客户模态框-end  -->
	<!-- 错误提示框 -->
	<div class="getWrong" id="getWrongById">
		<div class="modal-header">
			<button class="close" id="close" onclick="clcoseGetWrong()">
				<span>×</span>
			</button>
			<h3 class="modal-title" style="font-weight: bold;">
				错误信息<span style='color: red; font-size: 16px; font-weight: normal;'>(请修改完后重新上传整个Excel)</span>
			</h3>
			<div class="detailWrong" id="detailWrong"
				style="margin-top: 20px; font-size: 15px; font-family: '微软雅黑';"></div>
		</div>
	</div>
	<!-- start-修改客户模态框-start  -->
	<div class="modal fade" id="updateUser" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel">
		<div class="modal-dialog">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
						<h4 class="modal-title" id="myModalLabel">修改用户信息</h4>
					</div>
					<div class="modal-body">
						<form class="form-horizontal" id="updateForm"
							enctype="multipart/form-data" onsubmit="return checkUpdate()">
							<input type="hidden" id="userId" name="userId">
							<div class="form-group">
								<label for="new_customerName" class="col-sm-2 control-label">
									用户名 </label>
								<div class="col-sm-10">
									<input type="text" class="form-control" id="userNameupdate1"
										name="userName" />
								</div>
							</div>
							<div class="form-group">
								<label for="new_customerName" class="col-sm-2 control-label">
									账号 </label>
								<div class="col-sm-10">
									<input type="text" class="form-control" id="account"
										disabled="disabled" name="account" />
								</div>
							</div>
							<div class="form-group">
								<label for="new_phone" class="col-sm-2 control-label">密码</label>
								<div class="col-sm-10">
									<input type="password" class="form-control" id="passWord"
										name="passWord" />
								</div>
							</div>
							<div class="form-group">
								<label for="new_mobile" class="col-sm-2 control-label">重复密码</label>
								<div class="col-sm-10">
									<input type="password" class="form-control" id="repassword"
										onblur="pwdTest1()" name="repassword" /> <span id="pwdTip1"
										style="color: red; font-size: 15px;"></span>
								</div>
							</div>
							<div class="form-group">
								<label for="new_phone" class="col-sm-2 control-label">性别</label>
								<label class="radio-inline">&nbsp;&nbsp;&nbsp;&nbsp;<input
									type="radio" id="man" value="男" name="sex">男
								</label> <label class="radio-inline"> <input type="radio"
									id="woman" value="女" name="sex">女
								</label>
							</div>
							<div class="form-group">
								<label for="new_mobile" class="col-sm-2 control-label">手机号码</label>
								<div class="col-sm-10">
									<input type="text" class="form-control" id="phone" name="phone" />
								</div>
							</div>
							<div class="form-group">
								<label for="new_mobile" class="col-sm-2 control-label">地址名</label>
								<div class="col-sm-10">
									<input type="text" class="form-control" id="address"
										name="address" />
								</div>
							</div>
							<div class="form-group">
								<label for="new_mobile" class="col-sm-2 control-label">邮箱</label>
								<div class="col-sm-10">
									<input type="text" class="form-control" id="email2"
										name="email" />
								</div>
							</div>
							<div class="form-group">
								<label for="new_mobile" class="col-sm-2 control-label">角色</label>
								<label class="radio-inline">&nbsp;&nbsp;&nbsp;&nbsp;<input
									type="radio" id="boss" value="1" name="role.roleId">管理员
								</label> <label class="radio-inline"> <input type="radio"
									id="worker" value="2" name="role.roleId">员工
								</label>
							</div>
							<div class="modal-footer">
								<button type="button" class="btn btn-default"
									data-dismiss="modal">关闭</button>
								<input type="submit" id="updatebtn" class="btn btn-primary"
									onclick="updateUserbtn()" value="提交">
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- end-修改客户模态框-end  -->
	<script type="text/javascript">
	/* 修改用户 */
	function updateUserbtn() {
			$.post("${pageContext.request.contextPath }/User/update",
			$("#updateForm").serialize(),
			function(data){
				if(data=="OK"){
					alert("更新成功！");
					window.location.href="${pageContext.request.contextPath}/User/allUser";;
				}else{
					alert("更新失败！");
					window.location.href="${pageContext.request.contextPath}/User/allUser";;
				}
			});
	}
		/* 清空新建用户窗口中的数据 */
		function clearUser() {
			$("#userName1").val("");
			if (usTip !== null || usTip != "") {
				$("#usTip").html("");
			} 
			$("#account1").val("");
			var accTip = $("#accTip").val();
			if (accTip !== null || accTip != "") {
				$("#accTip").html("");
			} 
			$("#passWord1").val("");
			$("#repassword1").val("");
			if (repwdTip !== null || repwdTip != "") {
				$("#repwdTip").html("");
			} 
			$("#phone1").val("");
			if (phTip !== null || phTip != "") {
				$("#phTip").html("");
			} 
		 	var gental=$('input:radio[id="man1"]:checked').val();
		 	var lady=$('input:radio[id="woman1"]:checked').val();
		    if(gental!=null || lady!=null ){
		    	 $('input:radio[id="man1"]').attr('checked',false); 
		    	 $('input:radio[id="woman1"]').attr('checked',false); 
		     }
			$("#address1").val("");
			$("#email1").val("");
			$("#headimg1").val("");
			 var master=$('input:radio[id="master"]:checked').val();
			 var harder=$('input:radio[id="harder"]:checked').val();
			    if(master!=null || harder!=null ){
			    	 $('input:radio[id="master"]').attr('checked',false); 
			    	 $('input:radio[id="harder"]').attr('checked',false); 
			     }
		}
		function clearUpdateUser(){
			if (pwdTip1 !== null || pwdTip1 != "") {
				$("#pwdTip1").html("");
			} 
		}
	
		/* 修改用户 */
		function editCustomer(id) {
		    $.ajax({
		        type:"get",
		        url:"${pageContext.request.contextPath }/User/toupdate",
		        data:{"id":id},
		        success:function(data) {
		            $("#userId").val(data.userId);
		            $("#userNameupdate1").val(data.userName);
		            $("#account").val(data.account);
		            $("#passWord").val(data.passWord);
		            $("#repassword").val(data.passWord);
		            if(data.sex=="男"){
		            	 $("#man").prop("checked",true);
		            	 $("#woman").prop("checked",false);
		            }
		            if(data.sex=="女"){
		            	 $("#woman").prop("checked",true);
		            	 $("#man").prop("checked",false);
		            }
		            $("#phone").val(data.phone);
		            $("#address").val(data.address);
		            $("#email2").val(data.email);
		            if((data.role.roleName)=="管理员"){
		            	 $("#boss").prop("checked",true);
		            	 $("#worker").prop("checked",false);
		            }
		            if((data.role.roleName)=="员工"){
		            	 $("#worker").prop("checked",true);
		            	 $("#boss").prop("checked",false);
		            }
     		        }
		    });
		}
		/* 定义返回标志 */
		var check=false;
		/* 判断添加账号唯一 */
		function acTest() {
			var account = $("#account1").val();
			if (account == null || account == "") {
				$("#accTip").html("*账号不能为空");
				check = false;
			} else if (account.length<6 || account.length>18) {
				$("#accTip").html("*账号长度必须在6-18位之间");
				check = false;
			} else if (account.length >= 6 && account.length <= 18) {
				$.post("${pageContext.request.contextPath}/User/testAccount", {
					"account" : account,
				}, function(data) {
					$("#accTip").html(data);
				})
				$("#accTip").html("");
				check = true;
			}
			return check;
		} 
		/*地址不为空验证  */
		function addressTest() {
			var address = $("#address1").val();
			if (address == null || address == "") {
				$("#address1Tip").html("*地址不能为空");
				check = false;
			}else{
				$("#address1Tip").html("");
				check = true;
			}
			return check;
		}
		/* 添加用户名验证 */
		function userTest() {
			var userName = $("#userName1").val();
			if (userName == null || userName == "") {
				$("#usTip").html("*用户名不为空");
				check = false;
			} else {
				$("#usTip").html("");
				check = true;
			}
			return check;
		}
		/* 电话号码验证 */
		function phoneTest() {
			var phone = $("#phone1").val();
			if (!(/^1[3456789]\d{9}$/.test(phone))) {
				$("#phTip").html("*手机号码格式不正确！")
				check = false;
			} else {
				$("#phTip").html("")
				check = true;
			}
			return check;
		}
		/* 邮箱验证 */
	 	function emailTest() {
			var email = $("#email1").val();
			if (!(/^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z0-9]+$/
					.test(email))) {
				$("#emTip").html("*邮箱格式不正确！");
				check = false;
			} else {
				$("#emTip").html("")
				check = true;
			}
			return check;
		}


		/* 密码验证 */
		function pwdTest() {
			var pwd = $("#passWord1").val();
			var repwd = $("#repassword1").val();
			if (repwd != pwd) {
				$("#repwdTip").html("* 两次密码输入不一致！");
				check = false;
			} else {
				$("#repwdTip").html("");
				check = true;
			}
			return check;
		}
		
		function pwdTest1() {
			var pwd = $("#passWord").val();
			var repwd = $("#repassword").val();
			if (repwd != pwd) {
				$("#pwdTip1").html("* 两次密码输入不一致！");
				check = false;
			} else {
				$("#pwdTip1").html("");
				check = true;
			}
			return check;
		}
		
	
		/* 密码长度验证 */
		function pwdlength() {
			var pwd = $("#passWord1").val();
			var repwd = $("#repassword1").val();
			if (pwd.length<6 || pwd.length>18) {
				$("#addpwdTip").html("*密码长度不能小于6位");
				check = false;
			}else if (pwd.length >= 6 && pwd.length <= 18) {
				$("#addpwdTip").html("");
				check=true;
			}
			return check;
		}
	 	/* 添加验证通过 */
		function checkAll() {
			if (userTest()&& addressTest()&& pwdTest() && phoneTest() && acTest() && pwdlength()&& emailTest()) {
			} else {
				return false;
			}
		}
		/* 修改验证通过 */
		function checkUpdate() {
			if (pwdTest1()) {
			} else {
				return false;
			}
		}
		
		/* 全选，取消全选 */
		$(function(){
			 isChecked=false;
			 $("#qx").click(function(){
			        isChecked=!isChecked;
			        if(isChecked){
			            $('.person').prop('checked', true);
			        }else{
			            $('.person').removeProp('checked');
			        }
			    });
			 $(".person").click(function(){
				 /* 获取全选按钮 */
				 var all=$("#qx");
				 /* 获取复选框选中的  */
				 var length=$('.person:checked').length;
				 /* 未选中 */
				 var len=$('.person').length;
				 if (length==len) {
					all.get(0).checked=true;
				}else{
					all.get(0).checked=false;
				}
			 })
		})
		/* 批量删除 */
		function deleteMore(){
			var checkedNum=$(".person:checked").length;
			if(checkedNum==0){
				alert("请选择至少一项");
				return false;
			}
			if(confirm("确定删除吗?")){
				var checkedList = new Array();
				$(".person:checked").each(function(){
				checkedList.push($(this).val());
				})
			};
			$.ajax({
				type:"post",
				url:"${pageContext.request.contextPath}/User/batchDel",
				data:{"items":checkedList.toString()},
				datatype:"html",
				success:function(data){
					$(":checkbox").attr("checked",false);
					window.location.href="${pageContext.request.contextPath}/User/allUser";
				},error:function(data){
					alert("抱歉，该用户下有关联信息，不能删除！")
				}
			})	
		}
		/* 单个删除 */
 		 function delSingle(ssid){
 			if(confirm("确定删除吗?")){
 				$.ajax({
 					type:"post",
 					url:"${pageContext.request.contextPath}/User/delete",
 					data:{"ssid":ssid},
 					datatype:"html",
 					success:function(data){
 						//alert("删除成功！");
 						window.location.href="${pageContext.request.contextPath}/User/allUser";
 					},error:function(data){
 						alert("抱歉，该用户下有关联信息，不能删除！")
 					}
 				})
 				};
 		} 
		/* 多条件查询保留字段 */
		$(function(){
				var val1="${power}";
				if(val1!=null){
					if(val1==1){
						$("#mana").attr("selected",true);
					}
					if(val1==2){
						$("#work").attr("selected",true);
					}
				}
		})
//JS校验form表单信息  
function checkData(){  
   var fileDir = $("#excelFile").val();  
   var suffix = fileDir.substr(fileDir.lastIndexOf("."));  
   if("" == fileDir){  
       alert("选择需要导入的Excel文件！"); 
       return false;  
   }  
   if(".xls" != suffix && ".xlsx" != suffix ){  
       alert("选择Excel格式的文件导入！");  
       return false;  
   }  
   return true;  
}
//Excel批量上传
$("#fileForm").click(function(){  
  if(checkData()){  
   	$.ajaxFileUpload({
         url:"${pageContext.request.contextPath}/User/importExcel",
         type:"post",
         fileElementId :"excelFile",
        // dateType:"json",
         success:function (data) {
         	var str = $(data).find("body").text();//获取返回的字符串
             var json = $.parseJSON(str);//把字符串转化为json对象
      	$.each(json,function(i,val){			            	
      		if(val.success==null && val.duplicate==null && val.error==null ){
      			alert("格式错误");
      			return false;
      		}
      		else if(val.success!=0 && val.success!=null){
      			alert("成功导入"+val.success+"条数据");
      			window.location.href="${pageContext.request.contextPath}/User/allUser";
      			return false;
      		}else if(val.success==0 && val.duplicate==null && val.error==null){
      			alert("格式错误");
      			return false;
      		}else{
      			$(".getWrong").css("display","block");
      			var $duplicate = "";
      			var $error="";
      			if(val.duplicate!=null && val.duplicate!=""){
      				$duplicate ="账号重复:请检查Excel第" +val.duplicate+"行(账号已存在)" +"</br><hr>";
      			}
      			if(val.error!=null && val.error!=""){
      				$error="格式错误:请检查Excel第"+val.error+"行数据(数据格式错误)"+"</br><hr>";
      			}
       		$("#detailWrong").html($duplicate+$error);	            			
      			return false;
      		
      		}
                //alert(val.duplicate);		                                                                      
             }); 

         },
         error:function(erro){
             console.log(erro);
         }
     });
   }       
 });
		/* 判断上上传图片类型 */
		function checkImgType (){
			var path = $("#headimg1").val();
	        if (path.length != 0) {
	            var extStart = path.lastIndexOf('.'),
                ext = path.substring(extStart, path.length).toUpperCase();
	            if (ext !== '.PNG' && ext !== '.JPG' && ext !== '.JPEG' && ext !== '.GIF') {
	                alert("您选择的图片格式不正确！");
	                $("#headimg1").val("");
	            }
			}
        }
		//关闭错误信息
		function clcoseGetWrong(){
			$("#getWrongById").css("display","none");
		}
	</script>
</body>