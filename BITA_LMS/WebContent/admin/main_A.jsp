﻿<%@page import="com.bit.model.LectureDto"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link
	href="https://fonts.googleapis.com/css?family=Noto+Sans+KR:100,300,400,500,700,900&display=swap&subset=korean"
	rel="stylesheet">
<link type="text/css" rel="stylesheet" href="css/frame.css" />
<style type="text/css">
#menu>ul {
	width: 610px;
	list-style-type: none;
	margin: 0px auto;
}
#content {
	height: 700px;
	margin: 0 auto;
}

#content #content_upper {
	height: 450px;
	margin: 0 auto;
}

#content #content_under {
	height: 250px;
}

#content #content_under #myinfo {
	float: left;
	width: 400px;
	margin-top: 50px;
}

#content #content_under #myinfo #myinfo_form {
	width: 300px;
	margin: 0 auto;
}

#content #content_under #myinfo #myinfo_form #mypic {
	float: left;
	height: 100px;
	width: 100px;
}

#content #content_under #myinfo #myinfo_form #welcome {
	float: right;
	line-height: 50px;
	width: 190px;
	text-align: center;
}

#content #content_under #myinfo #myinfo_form2 {
	overflow: auto;
	width: 300px;
}

#content #content_under #mywork {
	float: right;
	width: 395px;
	margin: 100px auto;
}

#content #content_under #mywork #mywork_form {
	width: 395px;
	text-align: center;
}
</style>
<script type="text/javascript" src="js/jquery-1.12.4.js"></script>

<script type="text/javascript">
	$(document).ready(function() {
		$('.topmenu').mouseenter(function() {
			$('.submenu').css('display', 'block')
		});
		$('.topmenu').mouseleave(function() {
			$('.submenu').css('display', 'none')
		});
		$('#header>img').click(function() {
			location.href = 'main.adm'
		}).mouseenter(function(){
			$('#header>img').css('cursor', 'pointer')
		});
		
		updateData();
	});
	
	function updateData(){
		$.ajax({
			type:"get",
			url:"teacher/teacherStatus.jsp",
			datatype : "json",
			error :function(data){
				var show="";
				$.each(data, function(index,item){
					console.log(item.name);
					show+="<span>"+"지점:"+item.lecName+"</span><br>";
					show+="<span>"+"소속:"+item.totalyDays+"</span><br>";
					show+="<span>"+"신청 현황:"+getSubmission+"</span><br>";
					show+="<span>"+"Q&A 문의:"+totalQnalNum+"</span><br>";
					
				});
			}
		});
	}
		
</script>

</head>

<body>
	<div>
		<div id="header">
			<a href="logout.bit">logout</a> <img alt="logo" src="img/logo.jpg" />
		</div>
		<div id="menu">
			<ul>
				<li class="topmenu"><a href="#">학생관리</a>
					<ul class="submenu">
						<li><a href="register.adm">학생등록</a></li>
						<li><a href="manage_stu.adm">수강생관리</a></li>
					</ul>
				</li>
				<li><a href="manage_lec.adm">강좌관리</a></li>
				<li><a href="manage_tea.adm">강사관리</a></li>
				<li><a href="qna.adm">1:1문의</a></li>
			</ul>
		</div>
		<div id="content">
			<div id="content_upper">
				<div id="calender">
					<h1>달력</h1>
					<jsp:include page="call_calendar_A.jsp" flush="false"></jsp:include>
				</div>
			</div>
			<div id="content_under">
			<!--
				<div id="myinfo">
					<div id="myinfo_form">
						<img id="mypic" alt="mypic" src="img/person_sample.png">
						<div id="welcome">
							<h1></h1>
							<p>님 환영합니다!</p>
						</div>
					</div>
					<div id="myinfo_form2">
						<label for="branch">지점 :</label> <span>비트교육센터 안양지점</span> <br /> <label for="belong">소속 :</label> <span>belong</span>
					</div>
				</div>
				<div id="mywork">
					<div id="mywork_form">
						<label for="today_a">신청현황 </label> <span> ?? / 30</span> <br /> <label for="today_q">문의현황 </label> <span> ?? / 30</span>
					</div>
				</div>
				  -->
			</div>
		</div>

		<div id="footer">
			<div>
				<img alt="logo" src="img/logo.jpg" />
				<p>
					비트캠프 서울시 서초구 강남대로 459 (서초동, 백암빌딩)｜ 사업자등록번호 : 214-85-24928<br>
					(주)비트컴퓨터 서초본원 대표이사 : 조현정 / 문의 : 02-3486-9600 / 팩스 : 02-6007-1245<br>
					통신판매업 신고번호 : 제 서초-00098호 / 개인정보보호관리책임자 : 최종진<br> Copyright
					&copy; 비트캠프 All rights reserved.
				</p>
			</div>
		</div>
	</div>
</body>

</html>
