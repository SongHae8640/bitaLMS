<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.ArrayList,com.bit.model.TeacherDao,com.bit.model.ScoreDto"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link href="https://fonts.googleapis.com/css?family=Noto+Sans+KR:100,300,400,500,700,900&display=swap&subset=korean" rel="stylesheet">
<link type="text/css" rel="stylesheet" href="css/frame.css" />
<style type="text/css">
.selected { 
	background: gray;
	cursor: pointer; 
}
#content input[type=text]{
	width: 100px;
} 
#content #content_wrap1{
	width: 210px;
	margin: 0 auto;

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
		$("thead>tr th:nth-child(n+2):nth-child(-n+4)").each(function(){
			$(this).mouseenter(function() {
				$(this).css('cursor', 'pointer');

			});					
			$(this).click(function(){
				$(this).addClass("selected");
				$(this).text(); 
				var test_click=($(this).text());
				var idx=test_click.substring(0,1);
				$(this).siblings().removeClass("selected");
		$('#update_btn').click(function(){
			$(location).attr('href', 'score_update.tea?idx='+idx)
		});
		$('#insert_btn').click(function(){
			$(location).attr('href', 'score_insert.tea?idx='+idx)
		});
	});
}); 
		//총 점수 잡기
		//1차시험점수
		var score_list1 = new Array();
   		$(".first").each(function(index, item){
	   		score_list1.push($(item).text());
   });
		//2차시험점수
		var score_list2 = new Array();
   		$(".second").each(function(index, item){
	   		score_list2.push($(item).text());
   });
		//3차시험점수
		var score_list3 = new Array();
   		$(".third").each(function(index, item){
	   		score_list3.push($(item).text());
   });
		//학생 이름
		var name_list = new Array();
		$('tbody>tr>td:nth-child(1)').each(function(index, item){
			name_list.push($(item).text());
	});
 
		//각  학생의 총점수를 보내 평균을 출력한다
	   var data = "name_list=" + name_list + "&score_list1=" + score_list1 + "&score_list2="+ score_list2 + "&score_list3=" + score_list3;
        $.ajax({
            type : "post",
            data : data, 
            url : "/BITA_LMS/score.tea",
            success : function(value) {
            	console.log("통신성공 평균");
            },
            error : function(){
            	console.log("통신실패 평균");
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
		<div id="content_wrap1">
			<h2>성적관리</h2>
			<span>시험을 선택해주세요</span>
			<table border="1">
				<thead>
					<tr>
						<th>이름</th>
						<th id="select1">1차</th>
						<th id="select2">2차</th>
						<th id="select3">3차</th>
						<th>평균</th>
					</tr>
				</thead>
				<tbody>
		<% 
		ArrayList<ScoreDto> scoreList = (ArrayList<ScoreDto>)request.getAttribute("scoreList");
		System.out.println(scoreList);
		System.out.println("점수리스트개수"+scoreList.size());
		
		if(scoreList!=null){
			for(int i=0;i<scoreList.size();i++ ){	//10개씩 보여지게  자바스크립트로..
		%>	
				<tr>
					<td><%=scoreList.get(i).getName()%></td>
					<td class="first"><%=scoreList.get(i).getFirstScore()%></td>  
					<td class="second"><%=scoreList.get(i).getSecondScore()%></td> 
					<td class="third"><%=scoreList.get(i).getThirdScore()%></td>
					<td><%=scoreList.get(i).getAvgScore()%></td>
				</tr>
		<%
			}	
		}
		%>
				</tbody>
			</table>
			<div>
				<input type="button" value="입력" id="insert_btn"/>
				<input type="button" value="수정" id="update_btn"/>
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