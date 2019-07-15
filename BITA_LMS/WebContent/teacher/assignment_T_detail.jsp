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
<script type="text/javascript" src="js/jquery-1.12.4.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
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
						//체크박스 확인
						/* var arrayParam = new Array();
						var formData;
							$("input:checkbox[name=chk]:checked").each(function(){
								arrayParam.push($(this).val());
							}); */
						/* $('#checked').click(function(){
							formData = new FormData();
							
							formData.append("submissionBean", $('#submissionBean').text());
							$.ajax({
								url : 'assignment_check.tea',
								method : 'post',
								data : formData, 
								contentType : false,
								processData : false,
								success : function() {
									alert('업로드 성공');
								}
							});
						}); */
/* 						var test; */
/* 						var num=document.getElementsByName("num"); */
					
						
/* 						var twoArray=new Array();
						for(var i=0; num.length;i++){
							var oneArray=new Array();
							oneArray.push(num[i].value);
							oneArray.push(assignmentId[i].value);
							oneArray.push(name[i].value);
							if(chk[i].checked==true){
								alert('true')
								test=1;
							}else{
								alert('false')
								test=0;
							}
							oneArray.push(test);
							twoArray.push(oneArray);
							console.log(twoArray);
							var jsonStr = JSON.stringify(twoArray);
						}
 */						
						
						$("#serch")
								.keyup(
										function() {
											var k = $(this).val();
											$("table>tbody>tr").hide();
											var temp = $("table>tbody>tr>td:nth-child(6n+2):contains('"
													+ k + "')");
											$(temp).parent().show();
										});
						
						
						
						$('#delete').click(function(){
							$(location).attr('href','assignment/delete.jsp?idx='+$('#idx').text());
						})
						$('#detail').click(function(){
							$(location).attr('href','assignment.tea');
						})
						$('#edit').click(function(){
							$(location).attr('href','assignment_edit.tea');
						})
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
			<form action="assignment_edit.tea" method="post">
				<h2>과제 상세</h2>
				<%
					AssignmentDto bean_a=(AssignmentDto)request.getAttribute("AssignmentBean");
					System.out.println(bean_a.toString());
				%>
				<div>
					<label>제목</label> <span><%=bean_a.getTitle()%></span>
				</div>
				<div>
					<label>작성자</label> <span><%=bean_a.getWriter()%></span>
				</div>
				<div>
					<label>날짜</label> <span><%=bean_a.getWriteDate()%></span>
				</div>
				<div>
					<label>내용</label>
					<span><%=bean_a.getContent()%></span>
					<span id="idx"><%=bean_a.getAssignmentId()%> </div>
					<span id="submissionBean"><%=request.getAttribute("submissionList") %></span>
				</div>

				<div>
					<button type="button" id="detail">과제목록</button>
				</div>
				<div>
					<button type="button" id="edit">수정</button>
					<input type="button" id="delete" value="삭제" >
				</div>
				</form>
				<%
					String root=request.getContextPath();
				%>
				<form action="assignment_check.tea" method="post">
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
					<tbody id="body">
						<%
							ArrayList<SubmissionDto> list=(ArrayList<SubmissionDto>)request.getAttribute("submissionList");
										for(SubmissionDto bean_s : list){
											System.out.println("bean_s="+bean_s);
											int i=0;
											i++;
											int checked=0;
						%>
						<tr>
							<td><%=bean_s.getRowNum() %></td>
							<td><a id="download" href="http://localhost:9090/BITA_LMS/upload/<%=bean_s.getFileName() %>" download><%=bean_s.getFileName() %></a></td>
							<td><%=bean_s.getStdName() %></td>
							<td><%=bean_s.getSubmitDate() %></td>
							<td><%=bean_s.getIsCheck() %></td>
							<td><input type="checkbox" name="chked" value="<%=bean_s.getIsCheck() %>" class="clickChk" <%=checked==1? "checked=\"checked\"":"" %>/></td>
							
							<input type="hidden" name="idx" value="<%=bean_a.getAssignmentId()%>">
							<input type="hidden" name="id" value="<%=bean_s.getStdName() %>">
 							<input type="hidden" name="chked" value="<%=bean_s.getIsCheck() %>">
							
							
						</tr>

						<%
						}
							
						%>
					</tbody>
				<div>
					<input type="text" id="serch">
					<button id="checked" type="submit">과제확인</button>
				</div>
				</table>	
			</form>
			<script>
				$('.clickChk')
						.bind(
								'click',
								function() {
									var assignmentId = document
											.getElementsByName("idx");
									var name = document.getElementsByName("id");
									var chk = document
											.getElementsByName("chked");
									var trNum = $(this).closest('tr').prevAll().length;
									alert(trNum);
									var assignment_id = assignmentId[trNum].value;
									var std_id = name[trNum].value;
									var is_check = chk[trNum].value;
									if (chk[trNum].checked == true) {
										checkYn = 1;
									} else {
										checkYn = 0;
									}

									console.log(assignment_id);
									console.log(std_id);
									console.log(chk[trNum].value);
									
									if(chk[trNum].value==false){
										alert('확인완료')
										test=1;
									}else{
										alert('확인햇잖아 시발 다시')
										test=0;
									}
									
									location.href = "assignment_check.tea?assignment_id="
											+ assignment_id
											+ "&std_id="
											+ std_id + "&is_check=" + is_check;

								});
			</script>
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