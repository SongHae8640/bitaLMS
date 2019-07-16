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
#content input[type=text]{
	width: 100px;
}
#content input[type=text]{
	width: 100px;
} 
.selected { 
	background: gray;
	cursor: pointer; 
}
#content input[type=text]{
	width: 100px;
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
#content #content_wrap1>div{
	width: 200px; 
	margin: 0px auto; 
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
#btn_wrap{
	width:105px;
	margin: 10px auto;
}
#btn_wrap button{
	display: inline-block;
	width: 50px; 
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
		$('#cancle_btn').click(function(){
			location.replace('score.tea');	//취소버튼 다시 목록으로 돌아가기
		});
		//비동기 ajax 방식으로 데이터 주고 받기 
		$('#submit_btn').click(function(){
		var name_list = new Array();
			$('tbody>tr>td:nth-child(1)').each(function(index, item){
				name_list.push($(item).text());
		});
		var score_list = new Array();
	   		$("input[name=score]").each(function(index, item){
		   		score_list.push($(item).val());
	   });
		function getParameterByName(name) {	//주소에서 idx값 받아오기
		    name = name.replace(/[\[]/, "\\[").replace(/[\]]/, "\\]");
		    var regex = new RegExp("[\\?&]" + name + "=([^&#]*)"),
		        results = regex.exec(location.search);
		    return results === null ? "" : decodeURIComponent(results[1].replace(/\+/g, " "));
		}
		var idx = getParameterByName('idx');
		//점수 입력
        var data = "name_list="+ name_list + "&score_list=" + score_list+ "&idx=" + idx;
        $.ajax({
            type : "post",
            data : data, 
            url : "/BITA_LMS/score_insert.tea",
            success : function(value) {
            	console.log("통신성공 성적입력");
            	location.replace('score.tea');
            },
            error : function(){
            	console.log("통신실패 성적입력");
            } 
			 
		});     
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
		<div id="sidebar">
			<br/><br/><br/><br/>
			<h3>학생관리</h3>
			<br/>
			<ul>
				<li><a href="register.adm">출결관리</a></li>
	
				<li id="click"><a href="manage_stu.adm">성적관리</a></li>

				<li><a href="manage_stu.adm">과제관리</a></li>
			</ul>
		</div>
		<div id="content_wrap1">
		<div id="content_wrap2">
			<h2>성적입력</h2>
			<table border="1">
				<thead>
					<tr>
						<th>이름</th>
						<th class="select_test">1차</th>
						<th class="select_test">2차</th>
						<th class="select_test">3차</th>
						<th>평균</th>
					</tr>
				</thead>
				<tbody> 
				
				<%
				ArrayList<ScoreDto> scoreList = (ArrayList<ScoreDto>)request.getAttribute("scoreList");
				System.out.println(scoreList);
				System.out.println("idx값" +request.getAttribute("idx"));
			
				if(scoreList!=null){
					for(int i=0;i<scoreList.size();i++ ){	//10개씩 보여지게  자바스크립트로..
						String idx=(String)request.getAttribute("idx");
						if(request.getAttribute("idx").equals("1")){ 
				%>
					<tr>
						<td id="name"><%=scoreList.get(i).getName()%></td>
						<td><input type="text" name="score"/></td>
						<td></td>
						<td></td> 
						<td></td>
					</tr>
					<%
					}else if(request.getAttribute("idx").equals("2")){ 
					%>
					<tr>
						<td><%=scoreList.get(i).getName()%></td>
						<td><%=scoreList.get(i).getFirstScore()%></td>
						<td><input type="text" name = "score"/></td>
						<td></td>
						<td></td>
				 	</tr>
					<%
					}else if(request.getAttribute("idx").equals("3")){ 
					%>
					<tr> 
						<td><%=scoreList.get(i).getName()%></td>
						<td><%=scoreList.get(i).getFirstScore() %></td> 
						<td><%=scoreList.get(i).getSecondScore() %></td> 
						<td><input type="text" name = "score" /></td>
				 		<td></td>
					</tr>
					<%  
						}
					}
				}
					%>
				</tbody> 
			</table>
			<div> 
			<div id="btn_wrap"> 
			<input type="hidden" name="idx" value="<%=(String)request.getAttribute("idx") %>" >
				<button type="button" id="submit_btn">확인</button>
				<button type="button" id="cancle_btn" >취소</button>
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
	</div>
</body>
</html>