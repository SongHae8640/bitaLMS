<%@page import="java.sql.Date"%>
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
body table{
	width:600px;
	border:none;
}
body table td{
	border:none;
	height:20px;
}
.circle{
	width:100px;
	height:100px;
	border-radius:50%;
	background-color:lightblue;
	border:none;
	font-size:16pt;
	font-weight:700;
	color:white;
	text-align:center;
	line-height:100px;
}
.smallCircle{
	margin:0px auto;
	width:60px;
	height:60px;
	border-radius:50%;
	background-color:lightblue;
	border:none;
	font-size:10pt;
	font-weight:500;
	color:white;
	text-align:center;
	line-height:28px;
	white-space:pre-line;
}
</style>
<script type="text/javascript" src="js/jquery-1.12.4.js"></script>
<script type="text/javascript">
var id = $('#name').text();
	
	$(document).ready(function(){
		$('.topmenu').mouseenter(function() {
			$('.submenu').css('display', 'block')
		});
		$('.topmenu').mouseleave(function() {
			$('.submenu').css('display', 'none')
		});
		$('#content button').hide().eq(0).show().click(function() {
			$('#content button').show().eq(0).hide();
		});
		ajaxCall();
		
		$('#checkbtn').click(ajaxBtnCall);
	});
	
	var ajaxCall = function() {
		//var id = $('#name').text();
		$.when($.ajax({ 
			url: "callAttendance.stu", 
			dataType: "json", 
			data: {id:id},
			type: "post",
			success: function(data){
				console.log('왜안돼');
				if(data.status!='null'){
					$("#status").text(data.status);
				}else{
					$("#status").text('입실전');
				}
				$(".smallCircle").eq(0).html('출석<br>'+data.출석);
				$(".smallCircle").eq(1).html('지각<br>'+data.지각);
				$(".smallCircle").eq(2).html('조퇴<br>'+data.조퇴);
				$(".smallCircle").eq(3).html('외출<br>'+data.외출);
				$(".smallCircle").eq(4).html('결석<br>'+data.결석);
				$("#name").text(data.name+"님");
				if(data.checkinTime!='null'){
					$("#checkTime").text("입실:"+data.checkinTime+" / "+"퇴실:");	
					if(data.checkoutTime!='null'){
						var temp = $("#checkTime").text()
						$("#checkTime").text(temp+data.checkoutTime);	
					}
				}
				$("label[for='attendanceProgress']").next().val(data.attendanceDays)
				$("label[for='attendanceProgress']").next().attr('max',data.totalDays);
				$("label[for='lecName']").next().html('<span>'+data.lecName+' 과정</span>');
				$("label[for='lecPeriod']").next().html('<br><span>'+data.startDate+' ~ '+data.endDate+'</span>')
				
			},error : function(){
                alert("통신실패");
            }
		})).done(function() {
			if($('#status').text()=="입실전"){
				$('#checkbtn').text('입실');
			}else if($('#status').text()=="입실"){
				$('#checkbtn').text('퇴실');
			}else if($('#status').text()=="퇴실"){
				$('#checkbtn').hide();
			}else{
				$('#checkbtn').hide();
			}
		});
	}
	
	var ajaxBtnCall = function() {
		//var id = $('#name').text();
		var btn = $('#checkbtn').text();
		$.when($.ajax({
			url: "callAttendanceBtn.stu", 
			dataType: "json", 
			data: {btn:btn},
			type: "post",
			success: function(data){
				if(data.status!='null'){
					$("#status").text(data.status);
					if(data.checkinTime!='null'){
						$("#checkTime").text("입실:"+data.checkinTime+" / "+"퇴실:");	
						if(data.checkoutTime!='null'){
							var temp = $("#checkTime").text()
							$("#checkTime").text(temp+data.checkoutTime);	
						}
					}
				}
			},error : function(){
                alert("통신실패");
            }
		})).done(function() {
			if($('#status').text()=="입실"){
				$('#checkbtn').text('퇴실');
			}else if($('#status').text()=="퇴실"){
				$('#checkbtn').hide();
			}else{
				$('#checkbtn').hide();
			}
		});
	}
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
			<h2>출석상황</h2>
				<form>
					<table border="1">
						<tr>
							<td rowspan="4" class="circle" id="status">입실전</td>
							<td id="name"></td>
							<td rowspan="4"><div class="smallCircle">출석<br>0</div></td>
							<td rowspan="4"><div class="smallCircle">지각<br>0</div></td>
							<td rowspan="4"><div class="smallCircle">조퇴<br>0</div></td>
							<td rowspan="4"><div class="smallCircle">외출<br>0</div></td>
							<td rowspan="4"><div class="smallCircle">결석<br>0</div></td>
						</tr>
						<tr>
							<td>환영합니다!</td>
						</tr>
						<tr>
							<td id="checkTime">입실:/퇴실:</td>
						</tr>
						<tr>
							<td>
							<button type="button" id="checkbtn">입실</button>
							</td>
						</tr>
						<tr>
							<td colspan="2"><label for="lecName">강좌명 : </label><span></span></td>
							<td rowspan="3" colspan="5"><label for="attendanceProgress">출결현황 </label><progress></progress></td>
						</tr>
						<tr>
							<td colspan="2"><label for="lecPeriod">강좌기간 :</label> <span></span></td>
						</tr>

					</table>
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