<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>       
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>修改密码</title>
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
<%--网页图标 --%>
<link rel="shortcut icon" href="${pageContext.request.contextPath}/images/ui/favicon.ico">
<link rel="stylesheet" href="${pageContext.request.contextPath}/PC/css/home.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/PC/css/list.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/PC/css/center.css" type="text/css">
<script type="text/javascript" src="${pageContext.request.contextPath}/PC/js/jquery-1.7.min.js"></script>
<script type="text/javascript">
$(function(){
	$("#userPass").css("background-color","#bbb");
	$("#userTitle").removeAttr();
	$("#wrong").removeAttr();
})
</script>
</head>
<body>
<!-- 页头 start-->
	<nav class="navbar navbar-default navbar-fixed-top header">
		<div class="container">
			<div class="collapse navbar-collapse" id="header-navbar-collapse">
				<ul class="nav navbar-nav">
					<li class="dropdown "><a class=""
						href="${pageContext.request.contextPath}/ui/ui">在线考试系统</a></li>
						<li class="dropdown "><a class=""
						href="${pageContext.request.contextPath}/ui/ui">返回首页</a></li>
				</ul>
			</div>
		</div>
	</nav>
	<!-- 页头 end-->
    <div class="shang">
      <div class="dn">
        <div class="z1"> 
        		
        </div> 
     </div>
    </div>
        <div class="content">
    		<!-- 导入左侧列表 -->
		<jsp:include page="center_left.jsp"></jsp:include>
        
        <div class="right_list">
        	<div class="right_title" style="border-bottom:red solid 1px; padding-bottom:5px;padding-top: 0px;">
            	<span>修改密码</span>
            </div> 
            	<h3 style="color:red;">${tip}</h3>
            <form action="${pageContext.request.contextPath}/center/index/ui/no" method="post" onsubmit="return checkPassword();">
                 <div class="right_info">
        	 		<div class="right_info_titel">
                    	<span>旧密码：</span>
                    </div>
                    <div class="right_info_cont"><input type="password" class="input" name="password" placeholder="请输入旧密码" required></div>
        		 </div>
                 <div class="right_info">
        	 		<div class="right_info_titel">
                    	<span>新密码：</span>
                    </div>
                    <div class="right_info_cont"><input type="password" id="pass" name="pass" class="input" placeholder="请输入新密码" required></div>
        		 </div>
                 <div class="right_info">
					<div class="right_info_titel">
                    	<span>确认密码：</span>
                    </div>
                    <div class="right_info_cont"><input type="password" id="pass01" name="pass01" class="input" placeholder="确认密码" required></div>
        		 </div>
                  <div class="right_info">
					<div class="right_info_titel">
                    </div>
                    <div class="right_info_cont">
                    	<input type="submit"  value="立即修改" >
                    </div>
        		 </div>
   			</form>
        </div>
        
   
    </div>
     <!-- 页脚 -->
		<div class="text-center copyright" style="position: absolute;bottom:0px;width:100%">
				<span style="font-size: 12px;">Copyright @2020-2030 在线考试</span>

	</div>
</body>
<script type="text/javascript">
function checkPassword(){
	var pass = document.getElementById('pass').value;
	var pass01 = document.getElementById('pass01').value;
	var reg=/^[0-9]{6,16}$|^[a-zA-Z]{6,16}$/; //全是数字或全是字母 6-16个字符
 	var reg1=/^[A-Za-z0-9]{6,16}$/; //数字、26个英文字母 6-16个字符
 	var reg2=/^\w{6,16}$/;  // 由数字、26个英文字母或者下划线组成的字符串 6-16个字符
 	
 	if(pass.length==0){
		alert("密码不能为空！");  
	}else if(pass.length>16){
		  alert("密码长度大于16个字符!"); 
	}else if (pass != pass01) {
		alert("两次密码不一致");
		return false;
	} else {
		return true;
	}
}

</script>
</html>