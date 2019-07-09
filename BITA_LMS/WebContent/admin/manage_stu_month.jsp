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
	top:160px;
	height:700px;
	width: 200px;
	text-align:center;
	z-index: 1;
	background-color: gray;
	}
	#content #sidebar ul li{
	list-style: none;
	}
	#content #sidebar ul li a{
	text-decoration: none;
	color: rgb(0,0,0);
	}
	#content #real_content{
	position:relative;
	left:300px;
	width: 600px;
	height:700px;
	}
	#content #page_name{
	width: 120px;
	margin: 0 auto;
	text-align:center;
	border: 1px solid gray;
	}
	#content #upper_content{
	width: 600px;
	}
	#content #upper_content #month_see{
	width: 130px;
	float: left;
	}
	#content #upper_content #month_ck{
	width: 200px;
	margin: 0 auto;
	text-align: center;
	}
	#content #upper_content #lecture_list{
	float:right;
	width: 55px;
	}
	#content #att_list{
	clear:both;
	width: 600px;
	height:500px;
	margin: 0 auto;
	text-align:center;
	}
	#content #att_list table{
	width: 600px;
	margin: 0 auto;
	}
	#content #att_list table,th,td{
	border: 1px solid gray;
	}
	#content #under_list{
	width: 600px;
	height:95px;
	margin: 0 auto;
	}
	#content #under_list #ok_button{
	float:right;
	width: 45px;
	}
	#content #under_list #reject_button{
	float:right;
	width: 45px;
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
		$('#header>img').click(function() {
			location.href = 'main.adm'
		}).mouseenter(function(){
			$('#header>img').css('cursor', 'pointer')
		});
	});
    //테스트 주석
</script>
</head>
<body>
	<div>
		<div id="header">
			<a href="logout.bit">logout</a> <img alt="logo" src="img/logo.jpg" />
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
			<h3>학생관리</h3>
			<br/><br/>
			<ul>
				<li><a href="register.adm">학생등록</a></li>
				<br/>
				<li><a href="manage_stu.adm">수강생관리</a></li>
			</ul>
		</div>
		<div id="real_content">
			<br/>
			<div id="page_name">
				<h2>학생출석</h2>
			</div>
			<div id=upper_content>
				<div id="month_see">
					<button type="button" onclick="location.href='manage_stu.adm'">관리페이지 보기</button>
				</div>
				<div id="month_ck">
					<div><label>month</label></div>
					<div>
						<button><</button>
						<label> 2019-07 </label>
						<button>></button>
					</div>
				</div>
					<div id="lecture_list">
						<select name="lecture_name">
						    <option value="JAVA">JAVA</option>
						    <option value="WEB">WEB</option>
						    <option value="DB">DB</option>
						</select>
					</div>
			</div>
			<div id="att_list">
			<table>
				<thead>
					<tr>
						<th>이름</th>
						<th>1</th>
						<th>2</th>
						<th>3</th>
						<th>4</th>
						<th>5</th>
						<th>6</th>
						<th>7</th>
						<th>8</th>
						<th>9</th>
						<th>10</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td>홍길동</td>
						<td>출석</td>
						<td>결석</td>
						<td>출석</td>
						<td>출석</td>
						<td>공결</td>
						<td>출석</td>
						<td>출석</td>
						<td>결석</td>
						<td>출석</td>
						<td>출석</td>
					</tr>
					<tr>
						<td>김영희</td>
						<td>출석</td>
						<td>결석</td>
						<td>출석</td>
						<td>출석</td>
						<td>공결</td>
						<td>출석</td>
						<td>출석</td>
						<td>결석</td>
						<td>출석</td>
						<td>출석</td>
					</tr>
				</tbody>
			</table>
		</div>
		<div id="under_list">
			<div id="reject_button">
				<button type="button">취소</button>
			</div>
			<div id="ok_button">
				<button type="button">확인</button>
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