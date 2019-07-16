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
	#content{
	height:700px;
	margin: 0 auto;
	}
	#content #sidebar{
	position:absolute;
	top:243px;
	height:700px;
	width: 200px;
	text-align:center;
	background-color: gray;
	}
	#content #sidebar ul li{
	list-style: none;
	}
	#content #sidebar ul li a{
	text-decoration: none;
	color: rgb(0,0,0);
	}
	#content #page_name{
	width: 120px;
	margin: 0 auto;
	text-align:center;
	border: 1px solid gray;
	}
	#content #real_content{
	position:relative;
	left:300px;
	width: 600px;
	height:700px;
	}
	#content #real_content #q_detail{
	width: 600px;
	}
	#content #real_content #q_detail table{
	width: 600px;
	margin: 0 auto;
	}
	#content #real_content #q_detail table,th,td{
	border: 1px solid gray;
	}
	#content #real_content #q_detail #submit{
	width:145px;
	float: right;
	}
	#content #real_content #q_detail #qna_content {
	overflow:auto;
	width:600px;
	height:200px;
	}
	#qna_answer{
	height:200px;
	}
	#content #under_list{
	width: 600px;
	height:95px;
	margin: 0 auto;
	}
	#content #under_list div{
	width: 80px;
	}
	#list_button{
	float: left;
	}
	#ans_button{
	float: right;
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

		
		$('#list_btn').click(function(){
				location.replace('qna.adm'); 
		});
		
		$('#delete_btn').click(function(){
			if(!confirm("정말 삭제하시겠습니까?")){
				console.log("삭제안함")
				return false;
			}
			console.log("삭제")
			$.ajax({
				url : "qna_delete.adm",
				type : "POST",
				data : {
						qnaLId : $('#qnaLId').text()
				},
				success : function(data){
					if(data =='OK'){
						console.log("삭제 성공");
						location.href = "qna.adm";	
					}else{
						console.log("삭제 실패");
					}
					
				},
				error : function(){
					console.log("삭제 실패");
				}
			})
			
		});
		
		$('#ans_btn').click(function(){
			var questionAnswer = $('#qnaLAnswer').val();
			if(!questionAnswer){
				alert("답변을 입력 하시오.");
				return false;
			}

			$.ajax({
				url : "qna_update.adm",
				type : "POST",
				data : {
						qnaLId : $('#qnaLId').text(),
						questionAnswer : questionAnswer
				},
				success : function(data){
					if(data =='OK'){
						console.log("답변 성공");
						location.href = "qna_detail.adm?idx="+$('#qnaLId').text();	
					}else{
						console.log("답변 실패");
					}
					
				},
				error : function(){
					console.log("답변 실패");
				}
			})
			
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
						<li><a href="register.adm">학생등록</a></li>
						<li><a href="manage_stu.adm">수강생관리</a></li>
					</ul></li>
				<li><a href="manage_lec.adm">강좌관리</a></li>
				<li><a href="manage_tea.adm">강사관리</a></li>
				<li><a href="qna.adm">1:1문의</a></li>
			</ul>
		</div>
		<div id="content">
			<div id="sidebar">
			<br/><br/><br/><br/>
			<h3>1:1문의</h3>
			<br/><br/>
		</div>
		<div id="real_content">
			<br/>
			<div id="page_name">
				<h2>1:1문의</h2>
			</div>
			<br/><br/>
		<div id="q_detail">
		<%
			QnaLDto bean = (QnaLDto)request.getAttribute("qnaLBean");
		%>
			<table>
				<div >
					<span id="qnaLId" style="display:none;"><%=bean.getQnaLId()%></span>
				</div>
					<tr>
						<th>제목</th>
						<td><%=bean.getTitle() %></td>
					</tr>
					<tr>
						<th>작성자</th>
						<td><%=bean.getStdName() %></td>
					</tr>
					<tr>
						<td colspan="2">
						<div id="qna_content">
							<div id="submit">
								<span ><%=bean.getWriteDate() %></span>
							</div>
							<div>
								<p><%=bean.getQuestionContent() %></p>
							</div>
						</div>
						<div id="qna_answer">
						<%
							if(bean.getIsCheck().equals("1")){
	
						%>
							<textarea id="qnaLAnswer" rows="12" cols="80" ><%=bean.getAnswerContent() %></textarea>
						<%
						}else{
						%>
							<textarea id="qnaLAnswer" rows="12" cols="80"  placeholder="미답변 문의 입니다."></textarea>
						<%
							}
						%>
						</div>
						</td>
					</tr>
			</table>
		</div>
		<div id="under_list">
			<div>
				<button type="button" id="list_btn">목록</button>
			</div>
			<div>
			<%
				if(bean.getIsCheck().equals("1")){
	
			%>
				<button type="button" id="ans_btn">답변수정</button>
			<%
				}else{
			%>
				<button type="button" id="ans_btn">답변등록</button>
			<%
				}
			%>	
			</div>
			<div>
				<button type="button" id="delete_btn">글삭제</button>
			</div>
		</div>
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