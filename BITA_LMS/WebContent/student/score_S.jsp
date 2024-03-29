<%@page import="com.bit.model.ScoreDto"%>
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
table{
	width: 350px;
	text-align: center;
}
h2{
 margin-top: 0px;
 margin-bottom:0px;
 padding: 0px;
}
#content{
	width:  1000px;
	height: 700px;
	margin: 0px auto;
	padding: 0px;
} 
#content #content_wrap1{
	width: 900px;
	margin: 0 auto;
	height: 100%;
}
#content #content_wrap1 #content_wrap2{
	height: 300px;
	width:350px; 
 	position: relative; 
 	top:100px;
	margin: 0 auto;
	
}
#content #content_wrap3 #page_name{
	width: 93px; 
	margin: 0px auto; 
	border: 1px solid gray;
}
#content #sidebar{
	float:left; 
	height:700px;  
	width: 187px;
	text-align:center; 
	background-color: lightgray;
	text-align: center;
}
#content #sidebar ul{
	text-align: center;
	padding: 0px;
	
}
#content #sidebar ul li{
	list-style: none;
	display: inline-block;
	height: 35px;
	width:100%;
	
}
#content #sidebar ul li a{
	text-decoration: none; 
	color: rgb(0,0,0);	 
}
#click{
	display: inline-block;
	height: 35px;
	width:100%;
	background-color: gray;
}
#content_wrap3 {
	position:relative;
	top:40px;
	height:400px;
	border: 1 solid gray;
}
#btn_wrap{
	width:90px;
	margin: 10px auto;
}
#btn_wrap button{
	display: block;
	width: 100px;
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
		$('#claim_btn').click(function(){
			location.href='qna_insert.stu';		
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
		<div id="sidebar">
			<br/><br/><br/><br/>
			<h3>성적관리</h3>
			<br/><br/>
		</div>
			<div id="content_wrap1">
			<div id="content_wrap2">
			<div id="content_wrap3">
			<div id="page_name">
			<h2>성적관리</h2>
			</div>
			<br/>
			<table border="1">
				<thead>
					<tr>
						<th>이름</th>
						<th>1차</th> 
						<th>2차</th>
						<th>3차</th>
					</tr>
				</thead>
				<tbody>
				<%
				ScoreDto scoreBean =(ScoreDto)request.getAttribute("scoreBean");
				System.out.println("뷰-"+scoreBean);
				if(scoreBean!=null){
				%>  
					<tr>
						<td><%=scoreBean.getName() %></td>
						<td><%=scoreBean.getFirstScore() %></td>
						<td><%=scoreBean.getSecondScore() %></td>
						<td><%=scoreBean.getThirdScore() %></td>
					</tr>
				<%
				}
				%>
				</tbody>
			</table>			
			<div id="btn_wrap"> 
				<button type="button" id="claim_btn">이의신청</button>
			</div>
			</div>
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
</body>
</html>