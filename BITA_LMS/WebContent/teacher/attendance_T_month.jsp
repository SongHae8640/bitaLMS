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
#content #att_list table{
	margin: 0 auto;
	width:100%;
	border-collapse: collapse;
}
#content #att_list table,th,td{
	border: 1px solid gray;
	border-collapse: collapse;
}
</style>
<script type="text/javascript" src="js/jquery-1.12.4.js"></script>
<script type="text/javascript">
	var yearMonth = new Date();
	$(document).ready(function() {
		if(yearMonth.getMonth()*1<10){
			yearMonth = $('#yearMonth').text(yearMonth.getFullYear()+"-0"+(yearMonth.getMonth()+1));
		}else{
			yearMonth = $('#yearMonth').text(yearMonth.getFullYear()+"-"+(yearMonth.getMonth()+1));			
		}
		$('.topmenu').mouseenter(function() {
			$('.submenu').css('display', 'block')
		});
		$('.topmenu').mouseleave(function() {
			$('.submenu').css('display', 'none')
		});
		$('#content>button').hide().eq(0).show().click(function() {
			$('#content>button').show().eq(0).hide();
		});
		$('#sb').change(function(){
			var state=$('#sb option:selected').val();
			if(state=='day'){
				$(location).attr('href', 'attendance.tea')
			}else if(state=='month'){
				$(location).attr('href', 'attendance_month.tea')
			}
		});
		
		ajaxCall();
		
		$('.monthBtn').click(ajaxMonthCall);
	});
	
	var ajaxCall = function() {
		//맨처음 데이터 가져올 때 & 월별 데이터 가져올때
		$.when($.ajax({ 
			url: "attendance_month.tea", 
			dataType : 'json',
			data : {yearMonth:$('#yearMonth').text()},
			type: "post",
			success: function(data){
				obj = data;
				console.log($('#yearMonth').text());
			},error : function(){
                alert("통신실패");
            }
		})).done(function() {
			var lastDay = ( new Date( $('#yearMonth').text().substr(0,4), $('#yearMonth').text().substr(5,2), 0) ).getDate();
			var thead = $('#att_list>table>thead>tr');
			var tbody = $('#att_list>table>tbody');
			thead.append("<th>이름</th>");
			for(var i=1; i<=lastDay; i++){
				thead.append("<th>"+i+"</th>");
			}
			thead.parent().parent().parent().css("width", "1000px");
			thead.parent().parent().css("width", "1000px");
			thead.css("width", "1000px");
			thead.children().css("width", "100px");
			var check = "";
			var cnt = 0;
			$.each(obj,function(key,value) {
				if(key==0){
					check = value.name;
					tbody.append("<tr><th>"+value.name+"</th></tr>");
					cnt++;
				}else{
					if(check==value.name){
					}else{
						check = value.name;
						tbody.append("<tr><th>"+value.name+"</th></tr>");
						cnt++;
					}
				}
			});
			for(var i=1; i<=cnt; i++){
				for(var j=1; j<=lastDay; j++){
					tbody.children().eq(i-1).append("<td></td>");
				}
			}
			var num = 0;
			$.each(obj,function(key,value) {
				var idx = value.dayTime*1;
				if(key==0){
					check = value.name;
					tbody.children().eq(0).children().eq(idx).text(value.status);
					console.log(value.dayTime);
					num++;
				}else{
					if(check!=value.name){
						check = value.name;
						tbody.children().eq(num).children().eq(idx).text(value.status);
						console.log(value.dayTime);
						num++;
					}else{
						num--;
						tbody.children().eq(num).children().eq(idx).text(value.status);
						console.log(value.dayTime);
						num++;
					}
				}
			});
			
		});
	}
	
	var ajaxMonthCall = function() {
		//월이동 버튼을 눌렀을 때
		var yyyy = $('#yearMonth').text().substr(0, 4);
	    var m = $('#yearMonth').text().substr(5, 2);
	    
		if($(this).attr('id')=='prevMonth'){
			m = m-1;
			if((m*1)<1){
				m=12;
				yyyy=yyyy-1;
			}
		}else{
			m = (m*1)+1;
			if((m*1)>12){
				m=1;
				yyyy=(yyyy*1)+1;
			}
		}
		if((m*1)<10){
			 $('#yearMonth').text(yyyy+"-0"+m);			
		}else{
			 $('#yearMonth').text(yyyy+"-"+m)
		}
		$('#att_list>table>thead>tr>th').remove();
		$('#att_list>table>tbody').children().remove();
		ajaxCall();
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
			<h2>월별 출석 현황</h2>
			<select name="sb" id="sb">
				<option value="" selected="selected">전체</option>
				<option value="day" id="day">day</option>
				<option value="month" id="month">month</option>
			</select>
			<div id=upper_content>
				<div id="month_ck">
					<div><label>month</label></div>
					<div>
						<button id="prevMonth" class="monthBtn"><</button>
						<label id="yearMonth"></label>
						<button id="nextMonth" class="monthBtn">></button>
					</div>
				</div>
					<div id="api">
					<p><br>(출)석 / (공)결 / (결)석 / (지)각 / (조)퇴<br><br></p>
					</div>
			</div>
			<div id="att_list">
			<table>
				<thead>
					<tr>
					<!-- 이름 및 날짜 th -->
					</tr>
				</thead>
				<tbody>
				 
				</tbody>
			</table>
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