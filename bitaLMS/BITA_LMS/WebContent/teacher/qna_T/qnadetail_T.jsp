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
<link type="text/css" rel="stylesheet" href="../../css/frame.css" />
<style type="text/css">
#menu>ul {
	width: 610px;
	list-style-type: none;
	margin: 0px auto;
}
</style>
<script type="text/javascript" src="../../js/jquery-1.12.4.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		$('.topmenu').mouseenter(function() {
			$('.submenu').css('display', 'block')
		});
		$('.topmenu').mouseleave(function() {
			$('.submenu').css('display', 'none')
		});
		$('#content form textarea').hide();
		$('#content>form button').hide().eq(0).show().click(function(){
			$('#content>form button').show().eq(0).hide();
			$('#content>form textarea').show();
			$('#content>form span').hide();
		});
	});
	
</script>
</head>
<body>
	<div>
		<div id="header">
			<a href="#">logout</a> <img alt="logo" src="img/logo.jpg" />
		</div>
		<div id="menu">
			<ul>
				<li class="topmenu"><a href="attendance.stu">출결관리</a>
				<li><a href="score.stu">성적관리</a></li>
				<li><a href="assignment.stu">과제관리</a></li>
				<li><a href="qna.stu">1:1문의</a></li>
			</ul>
		</div>
		<h2>상세보기</h2>
		<div id="content">
			<div>
				<label>제목</label> <span>sub</span>
			</div>
			<div>
				<label>작성자</label> <span>name</span>
			</div>
			<div>
				<label>날짜</label> <span>date</span>
			</div>
			<div>
				<label>분류</label> <span>이의신청</span>
			</div>
				<div>
					<label>내용</label> <span>hello</span>
				</div>
			<form action="">
					<h1 id="edit">답글</h1>
					<div>
						<span>hello</span>
						<textarea rows="10" cols="30"></textarea>
					</div>
					<div>
						<button type="button">수정</button>
						<button type="submit">수정완료</button>
						<button type="reset">취소</button>
					</div>
			</form>
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