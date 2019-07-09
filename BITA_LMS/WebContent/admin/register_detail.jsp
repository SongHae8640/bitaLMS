<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="com.bit.model.RegisterDto"%>
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
	#content #real_content{
	position:relative;
	left:300px;
	width: 600px;
	height:700px;
	}
	#content #real_content #app_detail{
	width: 600px;
	}
	#app_table{
	width:600px;
	margin: 0 auto;
	}
	#content #real_content #app_detail table,th,td{
	border: 1px solid gray; 
	}
	#content #real_content #app_detail #reg_form {
	width:300px;
	margin: 0 auto;
	}
	#content #real_content #app_detail #reg_form div{
	clear:both;
	width:300px;
	}
	#content #real_content #app_detail #submit{
	width:150px;
	float: right;
	}
	#content #page_name{
	width: 120px;
	margin: 0 auto;
	text-align:center;
	border: 1px solid gray;
	}
	#content #people_check{
	float:right;
	width: 130px;
	}
	#content #lecture_list{
	clear:both;
	float:right;
	width: 55px;
	}
	#content #app_list{
	clear:both;
	width: 500px;
	height:500px;
	margin: 0 auto;
	text-align:center;
	}
	#content #under_list{
	width: 600px;
	height:95px;
	margin: 0 auto;
	}
	#content #under_list div{
	width: 50px;
	}
	#content #under_list #list_button{
	float: left;
	}
	#content #under_list #reg_button{
	float: right;
	}
	#content #under_list #del_button{
	width: 45px;
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
		$('#header>img').click(function() {
			location.href = 'main.adm'
		}).mouseenter(function(){
			$('#header>img').css('cursor', 'pointer')
		});
		$('#del_btn').click(function(){
			var result = confirm('정말 삭제하시겠습니까?'); 
			if(result) { //yes-해당수강신청삭제
				location.replace('register.adm'); } 
			else { 
				//no-변동사항없음
				} 
			});
		$('#list_btn').click(function(){
				location.replace('register.adm');
			});
	});
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
				<h2>학생등록</h2>
			</div>
				<br/><br/>
		<div id="app_detail">
			<table id="app_table">
					<%
								RegisterDto registerBean = (RegisterDto) request.getAttribute("registerBean");
								if(registerBean !=null){
					%>
					<tr>
						<th>제목</th>
						<td><%=registerBean.getUserName() %>님의 수강신청</td>
					</tr>
					<tr>
						<th>작성</th>
						<td><%=registerBean.getUserId() %></td>
					</tr>
					<tr>
						<td colspan="2">
						<div id="reg_form">
							<div id="submit">
								<span>제출일 <%=registerBean.getApplyDate() %></span>
							</div>
							<div>
								<label>이름</label>
								<span><%=registerBean.getUserName() %></span>
							</div>
							<div>
								<label>강좌선택</label>
								<span><%=registerBean.getLecName() %></span>
							</div>
							<div>
								<label>연락처</label>
								<span><%=registerBean.getPhoneNumber() %></span>
							</div>
							<div>
								<label>파일첨부</label>
								<a><%=registerBean.getFileName() %></a>
							</div>
						</div>
						</td>
					</tr>
					<%
								}
					%>
					<!--<tr>
						<th>제목</th>
						<td>김경민님의 수강신청</td>
					</tr>
					<tr>
						<th>작성</th>
						<td>rudals108</td>
					</tr>
					<tr>
						<td colspan="2">
						<div id="reg_form">
							<div id="submit">
								<span>제출일 2019-07-03</span>
							</div>
							<div>
								<label>이름</label>
								<span>김경민</span>
							</div>
							<div>
								<label>강좌선택</label>
								<span>JAVA</span>
							</div>
							<div>
								<label>연락처</label>
								<span>010-1234-5678</span>
							</div>
							<div>
								<label>파일첨부</label>
								<span>파일다운로드하는링크</span>
							</div>
						</div>
						</td>
					</tr>  -->
			</table>
		</div>
		<div id="under_list">
			<div id="list_button">
				<button type="button" id="list_btn">목록</button>
			</div>
			<div id="del_button">
				<button type="button" id="del_btn">삭제</button>
			</div>
			<div id="reg_button">
				<button type="button">등록</button>
			 	<!-- 등록 누르면 출력된 데이터 수강생관리에 전달 -->
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