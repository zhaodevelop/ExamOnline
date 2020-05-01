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
	height: 360px;
	width: 500px;
	margin: 0 auto;
	margin-top:40px;
	background-color: white;
	}
	
	.bj table{
		color:black;
		margin-left: 72px;
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
	.t{
	height:10px;
	color:red;
	text-align: center;
	}
</style>
</head>
<body>
	<div class="bj">
	<form onsubmit="return requestJson()">
    	
    	<input type="hidden" name="userId" id="userId" value="${sessionScope.user.userId}">
    	<div style="height: 100px;"></div>
    		<table>
    			<tr>
    				<td>请输入旧密码</td>
    				<td><input type="password" id="old" name="oldPass"/></td>
    			</tr>
    			<tr>
    				<td>请输入新密码：</td>
    				<td><input type="password" id="pass" name="newPass"/></td>
    			</tr>
    			<tr>
    				<td>请再次输入新密码：</td>
    				<td><input type="password" id="pass2"/></td>
    			</tr>
    		</table>
    		<div id="pwdinfo" class="t">${a}</div>
    		<div class="an"><button type="submit" >完成修改</button></div>
   	</form>
   	</div> 
</body>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/admin/jquery.min.js"></script>
<script type="text/javascript">
function requestJson(){
	
   	var old = $("#old").val();
  	var password = $("#pass").val();
	var password2 = $("#pass2").val();
	var pwdinfo = $("#pwdinfo").val();
	var userId = $("#userId").val();
	var pwdinfo = document.getElementById("pwdinfo");
	
	if(old==""){
		 pwdinfo.innerHTML = "请输入当前密码";
		return false;
	}
	else if(password == "" ){
		pwdinfo.innerHTML = "请输入新密码";
		return false;
	}
	else if(password == old){
		pwdinfo.innerHTML = "新旧密码不能一样，请重新输入";
		return false;
	}
	else if(password2 == "" ){
		pwdinfo.innerHTML = "请确认新密码";
		return false;
	}
	else if (password != password2) {
		pwdinfo.innerHTML = "两次密码输入不一致，请重新输入";
	    return false;
	}
	else if (password.length < 5) {
		pwdinfo.innerHTML = "密码长度不能小于5";
        return false;
    }
	else{
    $.ajax({
        type:'post',
        url:'updatePass',
        dataType:"json",//注意使用的是打他dataType，而不是Content-Type
        async: false,
        data:{oldPass:old,password:password,userId:userId},
        success:function(data){
           if(data==0){
               alert("密码修改成功");
               window.parent.location.reload();
           }
           else if (data==1) {
        	   alert("旧密码错误！");
			}
           else{
        	   alert("修改失败")
           }
        }
       });
	}
}
 </script>
</html>