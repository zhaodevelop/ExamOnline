<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html class="x-admin-sm">
    <head>
        <meta charset="UTF-8">
        <title>后台管理系统</title>
        <meta name="renderer" content="webkit|ie-comp|ie-stand">
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
        <meta http-equiv="Cache-Control" content="no-siteapp" />
        <link rel="stylesheet" href="${pageContext.request.contextPath }/css/admin/font.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath }/css/admin/xadmin.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath }/layui/css/layui.css">
        <!-- <link rel="stylesheet" href="${pageContext.request.contextPath }/css/theme5.css"> -->
        <script src="${pageContext.request.contextPath }/layui/layui.js" charset="utf-8"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath }/js/admin/xadmin.js"></script>
        <!-- 让IE8/9支持媒体查询，从而兼容栅格 -->
        <!--[if lt IE 9]>
        <![endif]-->
        <script>
            // 是否开启刷新记忆tab功能
            // var is_remember = false;
        </script>
    </head>
    <body class="index">
        <!-- 顶部开始 -->
        <div class="container">
            <div class="logo">
                <a>后台管理系统</a></div>
            <div class="left_open">
                <a><i title="展开左侧栏" class="iconfont">&#xe699;</i></a>
            </div>
       
            <ul class="layui-nav right" lay-filter="">
                <li class="layui-nav-item">
                    <a href="javascript:;">${sessionScope.user.userName}</a>
                    <dl class="layui-nav-child">
                        <!-- 二级菜单 -->
                        <dd>
                            <a onclick="xadmin.open('个人信息','${pageContext.request.contextPath }/personal/toPersonalData')">个人信息</a></dd>
                        <dd>
                            <a href="${pageContext.request.contextPath }/ui/toExit">退出</a></dd>
                    </dl>
                </li>
                <li class="layui-nav-item to-index">
                    <a href="${pageContext.request.contextPath }/ui/ui">前台首页</a></li>
            </ul>
        </div>
        <!-- 顶部结束 -->
        <!-- 中部开始 -->
        <!-- 左侧菜单开始 -->
        <div class="left-nav">
            <div id="side-nav">
                <ul id="nav">
                    <li>
                           <a onclick="xadmin.add_tab('用户管理','${pageContext.request.contextPath }/User/allUser')">
                           <i class="iconfont left-nav-li" lay-tips="管理员管理">&#xe726;</i>    
                           <cite>用户管理</cite></a>
                    </li>
                    <li>
                    	 	
                           <a onclick="xadmin.add_tab('行业管理','${pageContext.request.contextPath }/field/fieldManage.action')">
                           <i class="iconfont left-nav-li" lay-tips="行业管理">&#xe6eb;</i>    
                           <cite>行业管理</cite></a>
                    </li>
                    <li>
                           <a onclick="xadmin.add_tab('课程管理','${pageContext.request.contextPath }/course/courseManage.action')">
                           <i class="iconfont left-nav-li" lay-tips="课程管理">&#xe723;</i>  
                           <cite>课程管理</cite></a>
                    </li>
                     <li>
                            <a onclick="xadmin.add_tab('知识点管理','${pageContext.request.contextPath }/knowledge/knowledgeManage.action')">
                            <i class="iconfont left-nav-li" lay-tips="知识点管理">&#xe6b3;</i>  
                            <cite>知识点管理</cite></a>
                    </li>
                    <li>
                            <a onclick="xadmin.add_tab('题库管理','${pageContext.request.contextPath }/itemBank/list.action')">
                             <i class="iconfont left-nav-li" lay-tips="题库管理">&#xe6b5;</i>  
                            <cite>题库管理</cite></a>
                    </li>         
                    <li>
                             <a onclick="xadmin.add_tab('试卷管理','${pageContext.request.contextPath }/testPaper/testPaperManager.action')">
                             <i class="iconfont left-nav-li" lay-tips="试卷管理">&#xe69e;</i> 
                             <cite>试卷管理</cite></a>
                    </li>          
                    <li>
                        <a href="javascript:;">
                            <i class="iconfont left-nav-li" lay-tips="日志管理">&#xe726;</i>
                            <cite>日志管理</cite>
                            <i class="iconfont nav_right">&#xe697;</i></a>
                        <ul class="sub-menu">
                            <li>
                                <a onclick="xadmin.add_tab('登录日志','${pageContext.request.contextPath }/journa/journal.action')">
                                    <i class="iconfont">&#xe6a7;</i>
                                    <cite>登录日志</cite></a>
                            </li>
                            <li>
                                <a onclick="xadmin.add_tab('操作日志','${pageContext.request.contextPath }/opera/opera.action')">
                                    <i class="iconfont">&#xe6a7;</i>
                                    <cite>操作日志</cite></a>
                            </li>                 
                        </ul>
                    </li>
                    <li>
                             <a onclick="xadmin.add_tab('统计分析','${pageContext.request.contextPath }/analysis/average.action')">
                             <i class="iconfont left-nav-li" lay-tips="统计分析">&#xe6ce;</i>  
                             <cite>统计分析</cite></a>
                    </li>  
                </ul>
            </div>
        </div>
        <!-- <div class="x-slide_left"></div> -->
        <!-- 左侧菜单结束 -->
        <!-- 右侧主体开始 -->
        <div class="page-content">
            <div class="layui-tab tab" lay-filter="xbs_tab" lay-allowclose="false">
                <ul class="layui-tab-title">
                    <li class="home">
                        <i class="layui-icon">&#xe68e;</i>我的桌面</li></ul>
                <div class="layui-unselect layui-form-select layui-form-selected" id="tab_right">
                    <dl>
                        <dd data-type="this">关闭当前</dd>
                        <dd data-type="other">关闭其它</dd>
                        <dd data-type="all">关闭全部</dd></dl>
                </div>
                <div class="layui-tab-content">
                    <div class="layui-tab-item layui-show">
                        <iframe src='${pageContext.request.contextPath }/analysis/average.action' frameborder="0" scrolling="yes" class="x-iframe"></iframe>
                    </div>
                </div>
                <div id="tab_show"></div>
            </div>
        </div>
        <div class="page-content-bg"></div>
        <style id="theme_style"></style>
        <!-- 右侧主体结束 -->
        <!-- 中部结束 -->
        <script>//百度统计可去掉
            var _hmt = _hmt || []; (function() {
                var hm = document.createElement("script");
                hm.src = "https://hm.baidu.com/hm.js?b393d153aeb26b46e9431fabaf0f6190";
                var s = document.getElementsByTagName("script")[0];
                s.parentNode.insertBefore(hm, s);
            })();</script>
    </body>

</html>