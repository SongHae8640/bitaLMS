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
			location.href = 'main.stu'
		}).mouseenter(function(){
			$('#header>img').css('cursor', 'pointer')
		});
	});
</script>
</head>
<body>
	<div>
		<div id="header">
			<a href="logout.bit">logout</a> <img alt="logo" src="img/logo.jpg" />
		</div>
		<div id="menu">
			<ul>
				<li class="topmenu"><a href="attendance_S.jsp">출결관리</a>
				<li><a href="score_S.jsp">성적관리</a></li>
				<li><a href="assignment_S.jsp">과제관리</a></li>
				<li><a href="qna_S.jsp">1:1문의</a></li>
			</ul>
		</div>
		<div id="content">
			<h2>1:1문의</h2>

			<table border="1">
				<thead>
					<tr>
						<th>번호</th>
						<th>제목</th>
						<th>작성자</th>
						<th>작성일</th>
						<th>답변여부</th>
						<th>분류</th>
						<th><input type="checkbox" name="ch1" /></th>
					</tr>
				</thead>
				<tbody>
					<tr>
					<tr>
						<td>1</td>
						<td><a href="qna_S/qnadetail_S.jsp">sub</a></td>
						<td>name</td>
						<td>day</td>
						<td>vs</td>
						<td>sort</td>
						<th><input type="checkbox" name="ch2" /></th>
					</tr>
				</tbody>
				<tfooter>
				<tr>
					<td><select name="" label="">
							<opt>
							<option value="">성적문의</option>
							<option value="">강사</option>
							<option value="">행정</option>
							</opt>
					</select></td>
					<td><input type="text" name="" id="" /></td>
					<td><button>검색</button></td>
				</tr>
				</tfooter>
			</table>
			<div>
				<form action="qna_S/qnaadd_S.jsp">
					<button type="submit">등록</button>
				</form>
				<button type="">삭제</button>
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