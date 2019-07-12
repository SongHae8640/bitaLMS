<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link href="https://fonts.googleapis.com/css?family=Noto+Sans+KR:100,300,400,500,700,900&display=swap&subset=korean" rel="stylesheet">
<link type="text/css" rel="stylesheet" href="css/frame.css" />
<style type="text/css">
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
	});
</script>
</head>
<body>
	<div>
		<div id="header">
			<!-- <a href="#">logout</a> --> <img alt="logo" src="img/logo.jpg" />
		</div>
		<div id="menu">
		<!-- 강사팀 메뉴
			<ul>
				<li class="topmenu"><a href="#">학생관리</a>
					<ul class="submenu">
						<li><a href="#">출결관리</a></li>
						<li><a href="#">성적관리</a></li>
						<li><a href="#">과제관리</a></li>
					</ul></li>
				<li><a href="#">1:1문의</a></li>
			</ul>
			
			-->
			<!--
			행정팀메뉴
			<ul>
				<li class="topmenu"><a href="#">학생관리</a>
					<ul class="submenu">
						<li><a href="#">학생등록</a></li>
						<li><a href="#">수강생관리</a></li>
					</ul></li>
				<li><a href="#">강좌관리</a></li>
				<li><a href="#">강사관리</a></li>
				<li><a href="#">1:1문의</a></li>
			</ul>
			
			-->
			<!-- 학생메뉴
			
			<ul>
				<li class="topmenu"><a href="#">출결관리</a>
				<li><a href="#">성적관리</a></li>
				<li><a href="#">과제관리</a></li>
				<li><a href="#">1:1문의</a></li>
			</ul>
			
			-->
		</div>
		<div id="content">
			<h2>로그인페이지</h2>
			<form action="login.bit" method="post">
				<div>
					<lable for="id">id</lable>
					<input type="text" name="id" id="id" />
				</div>
				<div>
					<lable for="pw">pw</lable>
					<input type="password" name="pw" id="pw" />
				</div>
				<div>
					<button type="submit">로그인</button>
					<button type="reset">취소</button>
					<button type="button">회원가입</button>
				</div>
			</form>
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
	<%
		//ui를 깨지지 않게 하려면 항상 body 닫기 전에 넣는 것이 좋다. 로딩하다가 출력되기 때문
		Object obj = request.getAttribute("errmsg");
		if (obj != null)
			out.println(obj);
	%>
</body>
</html>