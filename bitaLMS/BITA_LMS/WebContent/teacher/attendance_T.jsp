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
<link type="text/css" rel="stylesheet" href="../css/frame.css" />
<style type="text/css">
</style>
<script type="text/javascript" src="../js/jquery-1.12.4.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		$('.topmenu').mouseenter(function() {
			$('.submenu').css('display', 'block')
		});
		$('.topmenu').mouseleave(function() {
			$('.submenu').css('display', 'none')
		});
		$('#content>form>select>option').eq(0).click(function(){
			
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
				<li class="topmenu"><a href="#">학생관리</a>
					<ul class="submenu">
						<li><a href="attendance.tea">출결관리</a></li>
						<li><a href="score.tea">성적관리</a></li>
						<li><a href="assignment.tea">과제관리</a></li>
					</ul></li>
				<li><a href="qna.tea">1:1문의</a></li>
			</ul>
		</div>
		<div id="content">
			<h2>출결관리</h2>
			<form action="">
				<select name="" id="">
					<option value="">day</option>
					<option value="">month</option>
				</select>
				<table>
					<thead>
						<tr>
							<th>이름</th>
							<th>출석</th>
							<th>지각</th>
							<th>결석</th>
							<th>공결</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td>홍길동</td>
							<td><input type="radio" name="ra" value="a1" /></td>
							<td><input type="radio" name="ra" value="a2" /></td>
							<td><input type="radio" name="ra" value="a3" /></td>
							<td><input type="radio" name="ra" value="a4" /></td>
						</tr>
						<tr>
							<td>김코난</td>
							<td><input type="radio" name="ra1" value="a1" /></td>
							<td><input type="radio" name="ra1" value="a2" /></td>
							<td><input type="radio" name="ra1" value="a3" /></td>
							<td><input type="radio" name="ra1" value="a4" /></td>
						</tr>
					</tbody>
				</table>
				<div>
					<button type="submit">제출</button>
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
</body>
</html>