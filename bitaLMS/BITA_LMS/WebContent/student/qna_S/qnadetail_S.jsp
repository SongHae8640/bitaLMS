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
<script type="text/javascript" src="../../js/jquery-1.12.4.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		$('#content>form input').hide();
		$('#content>form button').hide().eq(0).show();
		$('#content>form button').eq(0).click(function(){
			$('#content>form input').show().prev('span').hide();			
			$('#content>form input').show().eq(0).hide();
			$('#content>form button').show().eq(0).hide();
			$('#edit').text('수정페이지');
		});
		$('.topmenu').mouseenter(function() {
			$('.submenu').css('display', 'block')
		});
		$('.topmenu').mouseleave(function() {
			$('.submenu').css('display', 'none')
		});
		/* $('#content>form button').hide().eq(0).show().click(function(){
			$('#content>form input').prev().text('edit page');
			$('#content>form input').show().prev('span').hide();			
			$('#content>form input').show().eq(0).hide();			
		}); */
	});
</script>
</head>
<body>

	<div id="header">
		<a href="#">logout</a> <img alt="logo" src="img/logo.jpg" />
	</div>
	<div id="menu">
		<ul>
			<li class="topmenu"><a href="../attendance_S.jsp">출결관리</a>
			<li><a href="../score_S.jsp">성적관리</a></li>
			<li><a href="../assignment_S.jsp">과제관리</a></li>
			<li><a href="../qna_S.jsp">1:1문의</a></li>
		</ul>
	</div>
	<h2 id="edit">상세보기</h2>
	<div id="content">
		<form action="">
			<div>
				<label>제목</label> <span>sub</span> <input type="text" value="sub" />
			</div>
			<div>
				<label>작성자</label> <span>name</span> <input type="text" value="김코난" />
			</div>
			<div>
				<label>날짜</label> <span>date</span> <input type="text" value="date" />
			</div>
			<div>
				<label>분류</label> <select name="" label="">
					<opt>
					<option value="">성적문의</option>
					<option value="">강사</option>
					<option value="">행정</option>
					</opt>
				</select>
			</div>
			<div>
				<label>내용</label>
				<textarea name="" id="" cols="30" rows="10">hello</textarea>
			</div>

			<div>
				<button type="button">edit</button>
				<button type="submit">edit</button>
				<button type="reset">cancle</button>
				<button type="button">back</button>
			</div>
		</form>

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