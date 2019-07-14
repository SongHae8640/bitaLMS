<%@page import="com.bit.model.UserDto"%>
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
		
		//현재 날짜
		var now = new Date();
	    var year= now.getFullYear();
	    var mon = (now.getMonth()+1)>9 ? ''+(now.getMonth()+1) : '0'+(now.getMonth()+1);
	    var day = now.getDate()>9 ? ''+now.getDate() : '0'+now.getDate();        
	   	var chan_val = year + '-' + mon + '-' + day;
	   	$('#todaySpan').text(chan_val);


		$('#insertBtn').on('click', function(e){
			var title = $('#qnaLTitle').val();
			var type = $("select[name=question_type]").val()
			var questionContent = $('#qnaLContent').val();

			if(!title){
				alert("제목을 입력 하시오.");
				return false;
			}
			if(!questionContent){
				alert("질문을 입력 하시오.");
				return false;
			}

			$.ajax({
				url : "qna_insert.stu",
				type : "POST",
				data : {
						title : title,
						type : type,
						questionContent : questionContent					
				},
				success : function(data){
					if(data =='OK'){
						location.href = "qna.stu";	
					}else{
						console.log("질문 수정 실패");
					}
					
				},
				error : function(){
					console.log("질문 수정 실패");
				}
			})

			e.preventDefault();	
		})
		
		$("span[name=question_type]").change(function() {
			console.log("change")
			//var k = $(this).children("option:selected").text();
			
	    });
	});
</script>
</head>
<%
	UserDto userBean = (UserDto)session.getAttribute("userBean");
%>
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
		<h2>1:1문의</h2>
		<div id="content">
			<form>
				<div>
					<label>제목</label><input id =qnaLTitle type="text" value="" /> <span></span>
				</div>
				<div>
					<label>분류</label>
					<select name="question_type">
						<option value="성적문의" selected="selected">성적문의</option>
						<option value="강사">강사</option>
						<option value="행정">행정</option>
					</select>
				</div>
				<div>
					<label>작성자</label><span><%=userBean.getName() %></span>
				</div>
				<div>
					<label>날짜</label> <span id="todaySpan"></span>
				</div>
				<div>
					<label>내용</label>
					<textarea id="qnaLContent" cols="30" rows="10" ></textarea>
				</div>
				<div>
					<button type="button" id="insertBtn">확인</button>
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