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
	var updateTimer;	//updateData를 갱신 처리하는 타이머
	var xhr;
	var temp;
	$(document).ready(function() {
		
		$('.topmenu').mouseenter(function() {
			$('.submenu').css('display', 'block')
		});
		$('.topmenu').mouseleave(function() {
			$('.submenu').css('display', 'none')
		});
		$('#content>button').hide().eq(0).show().click(function() {
			$('#content>button').show().eq(0).hide();
		});
		
		updateData();
	});
	
	function updateData(){
		$.ajax({
            type : "GET",
            url : "student/studentStatus.jsp",
            dataType : "json",
            error : function(){
                alert('통신실패!!');
            },
            success : function(data){
            	var show = "";
				$.each(data, function(index,item){
					console.log(item.name);
					show +="<span>"+"출석 일 수 : "+item.attendanceDays+"</span><br>";
					show +="<span>"+"총 일 수 : "+item.totalyDays+"</span><br>";
					show +="<span>"+"새로운 Q&A답변 : "+item.newAnswerNum+"</span><br>";
					show +="<span>"+"Q&A 질문 : "+item.totalQnaLNum+"</span><br>";
					show +="<span>"+"수업 진행 일 수  : "+item.progressDays+"</span><br>";
				})
					
                $("#status").html(show) ;
            }
             
        });
		
		//updateTimer = setTimeout("updateData()", 1000);	// 00초에 한번씩 갱신
		
	};
</script>
</head>
<body>
	<div>
		<div id="header">
			<a href="logout.bit">logout</a> <img alt="logo" src="img/logo.jpg" />
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
			<h2>메인</h2>
			
			
			<div id = "calendar">
			
			</div>
			
			<div id = "attendance"></div>
				<jsp:include page="../call_attendance_S.jsp" flush="false"/>
			
			<div id = "status">
				여기!
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