<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
body{
background-color: rgba(0,0,0,0.1);
}
.bj{
	height: 530px;
	width: 500px;
	margin: 0 auto;
	margin-top:20px;
	background-color: white;
}

.bj table{
	color:black;
	margin-left: 72px;
	text-align: center;
	width: 400px;
	line-height: 40px;
}
.photo{
	width:100px;
	height:100px;
	border-radius:50%;
	overflow:hidden;
	margin: 0 auto;
}
.photo img{
	width:100px;
	height:100px;
}
.an button{
	width: 120px;
	height: 30px;
	background-color: rgba(0,150,136,0.8);
	color: #f5f5f5;
	font-size: 15px;
	margin-top:30px;
	border: none;
	border-radius:15px 15px 15px 15px;
}

.an button:hover{
	background-color: rgba(0,150,136,0.7);
}
.an button:active{
	background-color: rgba(0,150,136,0.6);
}

.an{
	margin: 0 auto;
	text-align: center;		
}
.a-upload {
	padding: 4px 10px;
	height: 17px;
	line-height: 17px;
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
a{
	text-decoration:none;
}
</style>
</head>
<body>
	<div class="bj">
	<h2 style="text-align: center;margin-bottom: 0px;">修改信息</h2>
	<hr>
	<form action="${pageContext.request.contextPath }/personal/updateData" enctype="multipart/form-data" method="post">
	<div class="photo" >
		<img alt="头像" src="${pageContext.request.contextPath }/${sessionScope.user.headImg}" id="img" >
	</div>
	
	<input type="hidden" name="userId" value="${sessionScope.user.userId}">
		<table>
			<tr>
				<td>头&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;像</td>
				<td>
				<a href="javascript:;" class="a-upload">  
						<input type="file" name="pictureFile" id="pictureFile" value="请选择图片" accept=".png,.jpg,.jpeg,.gif,.bmp" onchange="preImg('pictureFile','img');checkData()"/>修改头像
				</a>
				</td>
			</tr>
			<tr>
				<td>用户名称</td>
				<td><input type="text" name="userName" value="${sessionScope.user.userName}"></td>
			</tr>
			<tr>
				<td>性&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;别</td>
				<td>
					男<input type="radio" name="sex" value="男" ${sessionScope.user.sex == '男' ?'checked':'' }>
					女<input type="radio" name="sex" value="女" ${sessionScope.user.sex == '女' ?'checked':'' }>
				</td>
			</tr>
			<tr>
				<td>手&nbsp;机&nbsp;号</td>
				<td><input type="text" name="phone" value="${sessionScope.user.phone}"></td>
			</tr>
			<tr>
				<td>地&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;址</td>
				<td><input type="text" name="address" value="${sessionScope.user.address}"></td>
			</tr>
			<tr>
				<td>电子邮箱</td>
				<td><input type="text" name="email" value="${sessionScope.user.email}"></td>
			</tr>
		</table>
		<div class="an"><button type="submit">确定修改</button></div>
	</form>
	
	</div>
</body>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/admin/jquery-1.11.3.min.js"></script>
<script type="text/javascript">  
		var token = true;
         //JS校验form表单信息  
         function checkData(){  
            var fileDir = $("#pictureFile").val();  
            var suffix = fileDir.substr(fileDir.lastIndexOf("."));  
            var imgPre = document.getElementById("img");
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
        		 imgPre.src = "${pageContext.request.contextPath }/${sessionScope.user.headImg}";
        		 return false;
        	 }
         }
</script>
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
</script>
</html>