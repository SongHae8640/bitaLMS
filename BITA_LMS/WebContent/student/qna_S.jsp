<%@page import="com.bit.model.StudentDao"%>
<%@page import="com.bit.model.QnaLDto"%>
<%@page import="java.util.ArrayList"%>
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
#menu>ul {
	width: 610px;
	list-style-type: none;
	margin: 0px auto;
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
		
		$('#addBtn').on('click', function(){
			location.href = "qna_insert.stu";
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
					</tr>
				</thead>
				<tbody>
					<tr>
					<%
					ArrayList<QnaLDto> qList = (ArrayList<QnaLDto>)request.getAttribute("qnaLList");
					for(QnaLDto bean : qList){
					%>
					<tr>
						<td><%=bean.getRowNum() %></td>
						<td><a href="qna_detail.stu?idx=<%=bean.getQnaLId() %>"><%=bean.getTitle() %></a></td>
						<td><%=bean.getStdName() %></td>
						<td><%=bean.getWriteDate() %></td>
						<td><%=bean.getIsCheck() %></td>
						<td><%=bean.getType() %></td>
					</tr>
					<%
					}
					%>
				</tbody>
			</table>
			<div>
				<button type="button" id="addBtn">등록</button>
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