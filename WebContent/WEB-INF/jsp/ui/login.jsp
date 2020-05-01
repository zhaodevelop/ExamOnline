<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>登录页</title>
	<meta name="renderer" content="webkit|ie-comp|ie-stand">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width,user-scalable=yes, minimum-scale=0.4, initial-scale=0.8,target-densitydpi=low-dpi" />
    <meta http-equiv="Cache-Control" content="no-siteapp" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/ui/login/font.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/ui/login/login.css">
	  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/ui/login/xadmin.css">
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/ui/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/layui/layui.js" charset="utf-8"></script>
    <!--[if lt IE 9]>
      <script src="https://cdn.staticfile.org/html5shiv/r29/html5.min.js"></script>
      <script src="https://cdn.staticfile.org/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
    <style type="text/css">
		.login-bg{
			 background:url(${pageContext.request.contextPath}/images/admin/bg.png) no-repeat center;
			 background-size: cover;
			 overflow: hidden;
		}
		.login #darkbannerwrap {
		    background: url(${pageContext.request.contextPath}/images/admin/aiwrap.png);
		    width: 18px;
		    height: 10px;
		    margin: 0 0 20px -58px;
		    position: relative;
		}
    </style>
</head>
<body class="login-bg">
    
    <div class="login layui-anim layui-anim-up">
        <div class="message">在线考试系统登录</div>
        <div id="darkbannerwrap"></div>
        
        <form method="post" class="layui-form" >
            <input name="login" id="login" placeholder="用户名"  type="text"  class="layui-input" >
            <hr class="hr15">
            <input name="password" id="password"  placeholder="密码"  type="password" class="layui-input">
            <hr class="hr15">
            <input value="登录" lay-submit lay-filter="login" onclick="requestJson()" style="width:100%;" type="submit">
            <hr class="hr20" >
        </form>
    </div>

<script type="text/javascript">
		function requestJson(){
       		var name =$("#login").val();//#是id选择器
            var pwd =$("#password").val();
       		
            if(name==""){
               
                layer.msg("用户名不能为空！");
                return false;
            }
            else if(pwd==""){
            	layer.msg("密码不能为空！");
                return false;
            }
            else
            {
            $.ajax({
                type:'post',
                url:'${pageContext.request.contextPath}/login/loginTest',
                dataType:"json",//注意使用的是打他dataType，而不是Content-Type
                async: true,
                data:{login:name,password:pwd},
                success:function(data){
                   if(data==0){
                	   layer.msg("用户名或密码错误！")
                   }
                   else if (data==1) {
                	   window.location.href ="${pageContext.request.contextPath}/login/adminIndex";
					}
                   else{
                	   window.location.href ="${pageContext.request.contextPath}/login/login";
                   }
                }
            });
           }
       }
   </script>
    <script>
        $(function  () {
            layui.use('form', function(){
              var form = layui.form;
               //layer.msg('玩命卖萌中', function(){
              //   //关闭后的操作
                // });
              //监听提交
              form.on('submit(login)', function(data){
                // alert(888)
                
                return false;
              });
            });
        })
    </script>
    <!-- 底部结束 -->
    <script>
    //百度统计可去掉
    var _hmt = _hmt || [];
    (function() {
      var hm = document.createElement("script");
      hm.src = "https://hm.baidu.com/hm.js?b393d153aeb26b46e9431fabaf0f6190";
      var s = document.getElementsByTagName("script")[0]; 
      s.parentNode.insertBefore(hm, s);
    })();
    </script>
</body>
</html>