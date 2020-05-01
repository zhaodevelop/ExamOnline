<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="content-type" content="text/html;charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0" />

<%-- 考试 --%>
<title>考试界面</title>
<%--网页图标 --%>
<link rel="shortcut icon" href="${pageContext.request.contextPath}/images/ui/favicon.ico">
<link href="${pageContext.request.contextPath}/css/ui/main.css"
	rel="stylesheet" type="text/css" />

<link href="${pageContext.request.contextPath}/css/ui/test.css"
	rel="stylesheet" type="text/css" />

<style>
.hasBeenAnswer {
	background: #5d9cec;
	color: #fff;
}

.reading h2 {
	width: 100%;
	margin: 20px 0 70px;
	text-align: center;
	line-height: 2;
	font-size: 20px;
	color: #59595b;
}

.reading h2 a {
	text-decoration: none;
	color: #59595b;
	font-size: 20px;
}

.reading h2 a:hover {
	color: #2183f1;
}
</style>

</head>

<body>
	<div class="main">
		<!--nr start-->
		<div class="test_main">
			<div class="nr_left">
				<div class="test">
					 <form action="${pageContext.request.contextPath}/exams/autoScore.action" method="post" id="form1">
					 	<input type="hidden" name="tpId" value="${testPaper.tpId}"/>
					 	<input type="hidden" name="oneBranch" value="${testPaper.oneBranch}"/>
						<%--  下方 提交按钮 start --%>
						<div class="test_title">
							<p class="test_time">
								<b class="alt-1">${examsTime}</b>
							</p>
							<font><input id="handPaper" type="button" name="test_jiaojuan" value="交卷"></font>
						</div>
						<%--  下方 提交按钮 end  onclick="handPaper()"--%>

						<%-- 试卷多少道题  start --%>
						<div class="test_content">
							<div class="test_content_title">
								<h2>单选题</h2>
								<p>
									<span>共</span><i class="content_lit">${testPaper.number}</i><span>题，</span><span>合计</span><i
										class="content_fs">${testPaper.totalScore}</i><span>分&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;试卷名称:${testPaper.tpName}</span>
								</p>
							</div>
						</div>
						<%-- 试卷多少道题  end --%>

						<%--考试题 展示  start --%>
						<div class="test_content_nr">
							<ul style="margin-bottom: 40px">
								<c:forEach var="itemBank" items="${testPaper.itemBankList}" varStatus="index">
									<li id="qu_${index.count}">
										<input type="hidden" name="ibIds" value="${itemBank.ibId}"/>
										<div class="test_content_nr_tt">
											<i>${index.count}</i><span>(${testPaper.oneBranch}分)</span><font>${itemBank.question}</font>
										</div>
										<div class="test_content_nr_main">
											<ul>
												<li class="option"><input type="radio"
													class="radioOrCheck" name="answer${itemBank.ibId}" value="A" id="0_answer_${index.count}_option_1" />
													<label for="0_answer_${index.count}_option_1"> A.
														<span class="ue" style="display: inline;">${itemBank.optionA}</span>
													</label>
												</li>
												<li class="option"><input type="radio"
													class="radioOrCheck" name="answer${itemBank.ibId}" value="B" id="0_answer_${index.count}_option_2" />
													<label for="0_answer_${index.count}_option_2"> B.
														<span class="ue" style="display: inline;">${itemBank.optionB}</span>
													</label>
												</li>
												<li class="option"><input type="radio"
													class="radioOrCheck" name="answer${itemBank.ibId}" value="C" id="0_answer_${index.count}_option_3" />
													<label for="0_answer_${index.count}_option_3"> C.
														<span class="ue" style="display: inline;">${itemBank.optionC}</span>
													</label>
												</li>
												<li class="option"><input type="radio"
													class="radioOrCheck" name="answer${itemBank.ibId}" value="D" id="0_answer_${index.count}_option_4" />
													<label for="0_answer_${index.count}_option_4"> D.
														<span class="ue" style="display: inline;">${itemBank.optionD}</span>
													</label>
												</li>
											</ul>
										</div>
									</li>
								</c:forEach>
								<%--考试题 展示  start --%>
							</ul>
						</div>
					</form>
				</div>

			</div>
			<%--  右边   答题卡部分  start --%>
			<div class="nr_right">
				<div class="nr_rt_main">
					<div class="rt_nr1">
						<div class="rt_nr1_title">
							<h1>
								<i class="icon iconfont">&#xe692;</i>答题卡
							</h1>
							<p class="test_time">
								<!-- <i class="icon iconfont">&#xe6fb;</i> --><b class="alt-1">${examsTime}</b>
							</p>
						</div>

						<div class="rt_content">
							<div class="rt_content_tt">
								<h2>单选题</h2>
								<p>
									<span>共</span><i class="content_lit">${testPaper.number}</i><span>题</span>
								</p>
							</div>
							<div class="rt_content_nr answerSheet">
								<ul>
									<c:forEach var="itemBank" items="${testPaper.itemBankList}" varStatus="index">
										<li><a href="#qu_${index.count}">${index.count}</a></li>
									</c:forEach>
								</ul>
							</div>
						</div>

					</div>

				</div>
			</div>
			<%--  右边   答题卡部分  end --%>
		</div>
		<!--nr end-->
	</div>
</body>
    <script src="http://cdn.bootstrapmb.com/jquery/jquery-1.11.1.min.js"></script>
<script
	src="${pageContext.request.contextPath}/js/ui/jquery.easy-pie-chart.js"></script>
<!--时间js-->
<script
	src="${pageContext.request.contextPath}/js/ui/jquery.countdown.js"></script>
<script type="text/javascript">
		var count = 0;
		//当编辑内容之后 刷新和 关闭页面时拦截操作：
		$(window).bind('beforeunload', function () {
		    return '您输入的内容尚未保存，确定离开此页面吗？';  //好像这个提示并没什么用
		});
	window.jQuery(function($) {
		"use strict";

		$('time').countDown({
			with_separators : false
		});
		$('.alt-1').countDown({
			css_class : 'countdown-alt-1'
		});
		$('.alt-2').countDown({
			css_class : 'countdown-alt-2'
		});

	});

	$(function() {
		$('li.option label').click(
				function() {
					var examId = $(this).closest('.test_content_nr_main')
							.closest('li').attr('id'); <%--得到题目ID--%>

					var cardLi = $('a[href=#' + examId + ']'); <%--根据题目ID找到对应答题卡--%>
					<%--设置已答题--%>
					if (!cardLi.hasClass('hasBeenAnswer')) {
						cardLi.addClass('hasBeenAnswer');
					}

		});
		
		<%--DOM元素加载完后初始化倒计时--%>
		$('div, h1, time').countDown();
		<%--每一秒触发的事件--%>
		$('.countdown-alt-1').on('time.tick', function(ev, ms) {
			// do something...
		});
		<%--倒计时结束之后   触发的事件  可以用于自动提交--%>
		$('.countdown-alt-1').on('time.elapsed', function() {
			// do something...
			if(count == 0){
				 $(window).unbind('beforeunload');//这个是取消提醒
				 $.MsgBox.Alert("消息提示", "考试时间结束,已为您自动交卷.5s后退出！");
				 <%--延时执行  交卷--%>
				 setTimeout(function(){
					// alert(123);
					 $("#form1").submit();
				},5000);
			}
			//alert(456);
			count +=1;
		});
		
	});
	
	$(window).unload(function(){
        //响应事件
        alert("获取到了页面要关闭的事件了！"); 
    }); 
	<%--
			交卷提示  
	

	当然你也可以不给回调函数,点击确定后什么也不做，只是关闭弹出层--%>
	<%-- confim回调函数    test --%>
	$("#handPaper").bind("click", function () { 
		 $(window).unbind('beforeunload');//这个是取消提醒
		$.MsgBox.Confirm("温馨提示", "确定要交卷吗？"); 
	});
	(function() {
	    $.MsgBox = {
	        Alert: function(title, msg) {
	            GenerateHtml("alert", title, msg);
	        },
	        Confirm: function(title, msg, callback) {
	            GenerateHtml("confirm", title, msg);
	            btnOk(callback);
	            btnNo();
	        }
	    }
	    <%--生成Html--%>
	    var GenerateHtml = function(type, title, msg) {
	        var _html = "";
	        _html += '<div id="mb_box"></div><div id="mb_con"><span id="mb_tit">' + title + '</span>';
	        _html += '<a id="mb_ico">x</a><div id="mb_msg">' + msg + '</div><div id="mb_btnbox">';
	        if (type == "confirm") {
	            _html += '<input id="mb_btn_ok" type="button" value="确定" />';
	            _html += '<input id="mb_btn_no" type="button" value="取消" />';
	        }
	        _html += '</div></div>';
	        <%--必须先将_html添加到body，再设置Css样式--%>
	        $("body").append(_html);
	        <%--生成Css--%>
	        GenerateCss();
	    }

	    <%--生成Css--%>
	    var GenerateCss = function() {
	        $("#mb_box").css({
	            width: '100%',
	            height: '100%',
	            zIndex: '99999',
	            position: 'fixed',
	            filter: 'Alpha(opacity=60)',
	            backgroundColor: 'black',
	            top: '0',
	            left: '0',
	            opacity: '0.6'
	        });
	        $("#mb_con").css({
	            zIndex: '999999',
	            width: '400px',
	            position: 'fixed',
	            backgroundColor: 'White',
	            borderRadius: '15px'
	        });
	        $("#mb_tit").css({
	            display: 'block',
	            fontSize: '14px',
	            color: '#444',
	            padding: '10px 15px',
	            backgroundColor: '#DDD',
	            borderRadius: '15px 15px 0 0',
	            borderBottom: '3px solid #009BFE',
	            fontWeight: 'bold'
	        });
	        $("#mb_msg").css({
	            padding: '20px',
	            lineHeight: '20px',
	            borderBottom: '1px dashed #DDD',
	            fontSize: '13px'
	        });
	        $("#mb_ico").css({
	            display: 'block',
	            position: 'absolute',
	            right: '10px',
	            top: '9px',
	            border: '1px solid Gray',
	            width: '18px',
	            height: '18px',
	            textAlign: 'center',
	            lineHeight: '16px',
	            cursor: 'pointer',
	            borderRadius: '12px',
	            fontFamily: '微软雅黑'
	        });
	        $("#mb_btnbox").css({
	            margin: '15px 0 10px 0',
	            textAlign: 'center'
	        });
	        $("#mb_btn_ok,#mb_btn_no").css({
	            width: '85px',
	            height: '30px',
	            color: 'white',
	            border: 'none'
	        });
	        $("#mb_btn_ok").css({
	            backgroundColor: '#168bbb'
	        });
	        $("#mb_btn_no").css({
	            backgroundColor: 'gray',
	            marginLeft: '20px'
	        });
	        <%--右上角关闭按钮hover样式--%>
	        $("#mb_ico").hover(function() {
	            $(this).css({
	                backgroundColor: 'Red',
	                color: 'White'
	            });
	        }, function() {
	            $(this).css({
	                backgroundColor: '#DDD',
	                color: 'black'
	            });
	        });
	        var _widht = document.documentElement.clientWidth; <%--屏幕宽--%>
	        var _height = document.documentElement.clientHeight; <%--屏幕高--%>
	        var boxWidth = $("#mb_con").width();
	        var boxHeight = $("#mb_con").height();
	        <%--让提示框居中--%>
	        $("#mb_con").css({
	            top: (_height - boxHeight) / 2 + "px",
	            left: (_widht - boxWidth) / 2 + "px"
	        });
	    }
	    <%--确定按钮事件--%>
	    var btnOk = function(callback) {
	        $("#mb_btn_ok").click(function() {
	            $("#mb_box,#mb_con").remove();
	            $("#form1").submit();
	        });
	    }
	    <%--取消按钮事件--%>
	    var btnNo = function() {
	        $("#mb_btn_no,#mb_ico").click(function() {
	            $("#mb_box,#mb_con").remove();
	            return false;
	        });
	    }
	})();
</script>
</html>

