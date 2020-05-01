<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>     
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>账户信息</title>
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
<link rel="shortcut icon" href="${pageContext.request.contextPath}/images/ui/favicon.ico">
<link rel="stylesheet" href="${pageContext.request.contextPath}/PC/css/home.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/PC/css/list.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/PC/css/center.css" type="text/css">
<script type="text/javascript" src="${pageContext.request.contextPath}/PC/js/jquery-1.7.min.js"></script>
<style type="text/css">
	.button{
		opacity:0.5;//设置蒙版效果
		pointer-events: none;//禁用按钮
	}
	#getCode{
		display: inline;
		height:30px;
		width:30px;
		border:none;
	}
	 img {
            width: 100px;
            height: 100px;
            border-radius: 50%;
        }
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
</style>
<script type="text/javascript">
function getFileUrl(sourceId) {
var url;
if (navigator.userAgent.indexOf("MSIE")>=1) { // IE
url = document.getElementById(sourceId).value;
} else if(navigator.userAgent.indexOf("Firefox")>0) { // Firefox
url = window.URL.createObjectURL(document.getElementById(sourceId).files.item(0));
} else if(navigator.userAgent.indexOf("Chrome")>0) { // Chrome
url = window.URL.createObjectURL(document.getElementById(sourceId).files.item(0));
}
return url;
}
function preImg(sourceId, targetId) {
var url = getFileUrl(sourceId);
var imgPre = document.getElementById(targetId);
imgPre.src = url;

}
$(function(){
	$("#userTitle").css("background-color","#bbb");
	$("#userPass").removeAttr();
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
        	<div class="right_title" style=" border-bottom:red solid 1px; padding-bottom:5px; padding-top: 0px;">
            	<span>账户信息</span>
            </div> 
            <h3 style="color:red">${tip}</h3>
            <form  action="${pageContext.request.contextPath}/center/updateinfo" enctype="multipart/form-data" method="post">
        		 
        		 <div class="right_info">
        	 		<div class="right_info_titel">
                    	<span>*用户名：</span>
                    </div>
                    <div class="right_info_cont">
                    	<input type="text" class="input" name="userName" value="${sessionScope.user.userName}" placeholder="请输入姓名" required>
                    </div>
        		 </div>
                 <div class="right_info">
        	 		<div class="right_info_titel">
                    	<span>*电话：</span>
                    </div>
                    <div class="right_info_cont">
                    	<input type="text" class="input" name="phone" value="${sessionScope.user.phone}" placeholder="请输入电话" required>
                    </div>
        		 </div>
                 <div class="right_info">
					<div class="right_info_titel">
                    	<span>*性别：</span>
                    </div>
                    <div class="right_info_cont">
                    	<c:choose>
                    		<c:when test="${sessionScope.user.sex == '男'}">
	                    		<input type="radio" name="sex" value="男" checked>男&nbsp;&nbsp;
		        				<input type="radio" name="sex" value="女">女
                    		</c:when>
                    		<c:when test="${sessionScope.user.sex == '女'}">
	                    		<input type="radio" name="sex" value="男">男&nbsp;&nbsp;
		        				<input type="radio" name="sex" value="女" checked>女
                    		</c:when>
                    		<c:otherwise>
                    			<input type="radio" name="sex" value="男">男&nbsp;&nbsp;
	        					<input type="radio" name="sex" value="女">女
                    		</c:otherwise>
                    	</c:choose>
                    </div>
        		 </div>
                 <div class="right_info">
					<div class="right_info_titel">
                    	<span>*联系地址：</span>
                    </div>
                    <div class="right_info_cont">
                    	<input type="text" name="address" id="address" class="input" value="${sessionScope.user.address}" placeholder="请输入地址"  required >
                    </div>
        		 </div>
                 <div class="right_info">
					<div class="right_info_titel">
                    	<span>*邮箱：</span>
                    </div>
                    <div class="right_info_cont">
                    	<input type="text" name="email" class="input" value="${sessionScope.user.email}" placeholder="请输入邮箱"  required>
                    </div>
        		 </div>
        		 
        		    <div class="right_info">
					<div class="right_info_titel">
                    	<span>*头像上传：</span>
                    </div>
                    <div class="right_info_cont">
                    
                    <img id="imgPre" src="${pageContext.request.contextPath}/${sessionScope.user.headImg }" width="100px" height="100px" style="display: block;" />
                    <br/>
                    <a class="a-upload">
					<input type="file" name="headimg" id="headimg"  value="请选择图片" accept=".png,.jpg,.jpeg,.gif,.bmp" onchange="preImg(this.id,'imgPre');checkData()" />修改头像
					</a>
					
                    </div>
        		 </div>
        		
                  <div class="right_info">
					<div class="right_info_titel">
                    </div>
                    <input type="hidden" name="userId" value="${sessionScope.user.userId} ">
                    
                    <div class="right_info_cont">
                    <input type="submit"  value="保存" onclick="return sub()" style="margin-top:56px; padding: 7px 24px;">
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
		var token = true;
         //JS校验form表单信息  
         function checkData(){  
            var fileDir = $("#headimg").val();  
            var suffix = fileDir.substr(fileDir.lastIndexOf("."));  
            if("" == fileDir){  
                alert("选择需要上传的图片！"); 
                token =  false;  
            }  else{
            	 token =  true;  
            } 
            if(".png" != suffix && ".jpg" != suffix && ".jpeg" != suffix && ".gif" != suffix && ".bmp" != suffix && ".PNG" != suffix && ".JPG" != suffix && ".JPEG" != suffix && ".GIF" != suffix && ".BMP" != suffix ){  
                alert("请选择正确的图片格式！"); 
                imgPre.src = "${pageContext.request.contextPath }/${sessionScope.user.headImg}";
                token =  false;  
            } else{
            	 token =  true;  
            } 
         }  
         
         function sub(){
        	 if(token == true){
        		 return true;
        	 }else{
        		 alert("请选择正确的图片格式！");  
        		 return false;
        	 }
         }
</script>

</html>

