<%@page import="com.bit.model.AttachedFileDto"%>
<%@page import="com.bit.model.SubmissionDto"%>
<%@page import="com.bit.model.AssignmentDto"%>
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
</style>
<%
	int i = 1;
%>
<script type="text/javascript" src="js/jquery-1.12.4.js"></script>
<script type="text/javascript">
	var btn, file, xhr, formData;

	$(document).ready(function() {
		$('#upload').click(function() {
	
			var input = document.querySelector('#myfile');
			var idx = document.querySelector('#idx').text;
			alert(idx);
			formData = new FormData();
			formData.append('myfile', input.files[0]);
			formData.append("idx", $('#idx').text());
			formData.append("submissionBean", $('#submissionBean').text());
			
			$.ajax({
				url : 'upload.bit',
				method : 'post',
				data : formData, 
				contentType : false,
				processData : false,
				success : function() {
					alert('업로드 성공');
				}
			});
		});

		$('.topmenu').mouseenter(function() {
			$('.submenu').css('display', 'block')
		});
		$('.topmenu').mouseleave(function() {
			$('.submenu').css('display', 'none')
		});
		$('#ch').click(function() {
			if ($('#ch').prop("checked")) {
				$("input[name=chk]").prop("checked", true);
			} else {
				$("input[name=chk]").prop("checked", false);
			}
		});
		$('#assignmentList').click(function(){
			alert('hi');
			window.location.href='assignment.stu';
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
			<h2>과제 상세</h2>
<% 					
					AssignmentDto bean_a=(AssignmentDto)request.getAttribute("assignmentBean");
/* 					int assignmentId = Integer.parseInt(request.getParameter("idx")); */
/* 					System.out.println(str[0]); */
				%>
				<div>
					<label>제목</label> <span><%=bean_a.getTitle() %></span>
				</div>
				<div>
					<label>작성자</label> <span><%=bean_a.getWriter() %></span>
				</div>
				<div>
					<label>날짜</label> <span><%=bean_a.getWriteDate() %></span>
					<a id="idx"><%=bean_a.getAssignmentId() %></a>
				</div>
				<div>
					<label>내용</label>
					<span><%=bean_a.getContent() %></span>
					<span id="assignmentBean"><%=request.getAttribute("assignmentBean") %></span>
					<span id="submissionBean"><%=request.getAttribute("submissionList") %></span>
 			</div>

				<div>
					<button id="assignmentList">과제목록</button>
				</div>
				
			<table border="1">
				<thead>
					<tr>
						<th>번호</th>
						<th>제목</th>
						<th>작성자</th>
						<th>작성일</th>
						<th>확인여부</th>
						<th><input type="checkbox" name="" id="ch" /></th>
					</tr>
				</thead>
				<tbody>
				 <%
/* 				 			AttachedFileDto fileBean=request.getAttribute(""); */
							ArrayList<SubmissionDto> list=(ArrayList<SubmissionDto>)request.getAttribute("submissionList");
										for(SubmissionDto bean_s : list){
											System.out.println("bean_s="+bean_s);
						%>
						<tr>
							<td><%=bean_s.getRowNum() %></td>
							<td><a id="download" href="http://localhost:9090/BITA_LMS/upload/<%=bean_s.getFileName() %>" download><%=bean_s.getFileName() %></a></td>
							<td><%=bean_s.getStdName() %></td>
							<td><%=bean_s.getSubmitDate() %></td>
							<td><%=bean_s.getIsCheck() %></td>
							<td><input type="checkbox" name="chk" /></td>
						</tr>

						<%
						}
							
						%>

				</tbody>
			</table>
			<div>
				<div>
					<input type="file" id="myfile" />
				</div>
				<div>
					<button id="upload">등록</button>
				</div>
				<button onclick="location='assignment_delete_S.stu'">삭제</button>
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