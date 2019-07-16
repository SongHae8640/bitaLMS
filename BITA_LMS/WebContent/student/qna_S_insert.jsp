<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.Date,java.text.SimpleDateFormat"%>
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
	padding: 0px;
}
#content #content_wrap1 #content_wrap2{
	height: 300px;
	width:230px; 
 	position: relative; 
 	top:100px;
 	left:30px;
	margin: 0 auto;
	
}
#content #sidebar{
	float:left; 
	height:700px;  
	width: 187px;
	text-align:center; 
	background-color: lightgray;
	text-align: center;
}
#click{
	display: inline-block;
	height: 35px;
	width:100%;
	background-color: gray;
}
#btn_wrap{
	width:105px;
	margin: 10px auto;
}
#btn_wrap button{
	display: inline-block;
	width: 50px;
}
#questionContent{
	height: 150px;
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
		$("#submit_btn").click(function(){
			if($('#title').val()==""){ 
				alert("제목을 입력해주세요");
				return false;
			} 
			if($('#questionContent').val()==""){
				alert("내용을 입력해주세요");
				return false;
			}     
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
			<h3>1:1문의</h3>
			<br/>
		</div>
		<div id="content_wrap1">
			<div id="content_wrap2">
		<%
		String name =(String)request.getAttribute("name");
		SimpleDateFormat format = new SimpleDateFormat ("yyyy-MM-dd");
		Date time = new Date();
		String date = format.format(time);
		%>
			<h1>1:1문의 등록</h1>
			<form action="qna_insert.stu" method="post">
				<div>
					<label>제목</label><input type="text" name="title" id="title"/>
				</div>
				<div>
					<label>작성자</label><span><%=" "+name %></span>
				</div>
				<div>
					<label>날짜</label><span><%=" "+date %></span>
				</div>
				<div>
					<label>분류</label> 
					<select name="type"  id="type">
						<option value="이의신청">이의신청</option>
						<option value="성적문의">성적문의</option>
						<option value="기타문의">기타문의</option>
					</select>
				</div>
				<div>
					<label>내용</label>
					<textarea name="questionContent" id="questionContent"></textarea>
				</div>
				<div id="btn_wrap"> 
					<button type="submit" id="submit_btn">확인</button>
					<button type="button" id="cancle_btn">취소</button>
				</div>
			</form>
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