<%@page import="com.bit.model.UserDto"%>
<%@page import="com.bit.model.AssignmentDto"%>
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
		$('#ch').click(function(){
			if($('#ch').prop("checked")){
				$("input[name=chk]").prop("checked",true);
			}else{
				$("input[name=chk]").prop("checked",false);
			}
		});
		$('#insert').click(function(){
			alert('hi');
			window.location.href='assignment_insert.tea';
		});
		$('#delete').click(function(){
			$(location).attr('href','assignment_T_delete.tea');
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
			<h2>과제관리</h2>
			<table border="1">
				<thead>
					<tr>
						<th>번호</th>
						<th>제목</th>
						<th>작성자</th>
						<th>작성일</th>
						<th><input type="checkbox" name="ch1" id="ch"/></th>
					</tr>
				</thead>
				<tbody>
					<%
						ArrayList<AssignmentDto> list=(ArrayList<AssignmentDto>)request.getAttribute("assignmentList");
							for(AssignmentDto bean : list){
								System.out.println("bean"+bean.toString());
								UserDto userBean = (UserDto) session.getAttribute("userBean");
								System.out.println("userBean"+userBean.toString());
								
					%>
					<tr>
						<td><%=bean.getRowNum()%></td>
						<td><a href="assignment_detail.tea?idx=<%=bean.getAssignmentId()%>"><%=bean.getTitle()%></a></td>
						<td><%=userBean.getUserId()%></td>
						<td><%=bean.getWriteDate()%></td>
						<td><input type="checkbox" name="chk" id="chk" /></td>
					</tr>
					<%
						}
					%>
					
				</tbody>
			</table>
			<div>
				<input type="button" id="insert" value="등록">
				<input type="button" id="delete" value="삭제">
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