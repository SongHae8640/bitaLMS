<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link href="https://fonts.googleapis.com/css?family=Noto+Sans+KR:100,300,400,500,700,900&display=swap&subset=korean" rel="stylesheet">
<link type="text/css" rel="stylesheet" href="/BITA_LMS/css/frame.css" />
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
	height:120px;
	#content #real_content #lec_detail{
	width: 600px;
	}
	#content #real_content #curri_thumb{
	border: 1px solid gray;
	width: 200x;
	}
	#content #real_content #lec_detail table,th,td{
	border: 1px solid gray;
	}
	#lec_table1{
	width:450px;
	float: right;
	margin-top: 25px;
	}
	#lec_table2{
	width:600px;
	height:320px;
	margin: 0 auto;
	}
	#content #page_name{
	width: 120px;
	margin: 0 auto;
	text-align:center;
	border: 1px solid gray;
	}
	#content #tea_detail{
	clear:both;
	width: 500px;
	height:500px;
	margin: 0 auto;
	}
	#content #under_list{
	width: 600px;
	height:95px;
	margin: 0 auto;
	}
	#content #under_list div{
	width: 80px;
	}
	#content #under_list #list_button{
	float: left;
	}
	#content #under_list #ans_button{
	float: right;
	}
	#content #under_list #del_button{
	width: 45px;
	float: right;
	}
	
</style>
<script type="text/javascript" src="/js/jquery-1.12.4.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		$('.topmenu').mouseenter(function() {
			$('.submenu').css('display', 'block')
		});
		$('.topmenu').mouseleave(function() {
			$('.submenu').css('display', 'none')
		});
		$('#list_btn').click(function(){ 
			location.href('manage_lec.amd');  
			}); 
		$('#del_btn').click(function(){
			var result = confirm('정말 삭제하시겠습니까?'); 
			if(result) { //yes-해당수강신청삭제
				location.href('manage_lec.adm'); } 
			else { 
				//no-변동사항없음
				} 
			});
	});
</script>
</head>
<body>
		<div id="header">
			<a href="#">logout</a> <img alt="logo" src="img/logo.jpg" />
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
			<h3>강좌관리</h3>
			<br/><br/>
		</div>
		<div id="real_content">
			<div id="page_name">
				<h2>강좌상세</h2>
			</div>
			<br/><br/>
		<div id="lec_detail">
			<div id="curri_thumb">
				<h3>커리큘럼이미지</h3>
			</div>
			<table id="lec_table1">
					<tr>
						<td>강좌명</td>
						<td>JAVA</td>
					</tr>
					<tr>
						<td>강사명</td>
						<td>김코난</td>
					</tr>
					<tr>
						<td>교육기간</td>
						<td>2019-07-01 ~ 2019-10-01</td>
					</tr>
					<tr>
						<td>교육수준</td>
						<td>3수준</td>
					</tr>
					<tr>
						<td>최대인원</td>
						<td>30</td>
					</tr>
			</table>
			<table id="lec_table2">
				<tr>
					<td>
						<label>진도율</label>
						<progress value="20" max="100"></progress>
					</td>
				</tr>
				<tr>
					<td>
						강좌내용
					</td>
				</tr>
				<tr>
					<td>
						응용소프트웨어 엔지니어링은 컴퓨터 프로그래밍 언어로 각 
						업무에맞는 소프트웨어의 기능에 관한 설계, 구현 및 테스트를 
						수행하고,사용자에게 배포하며, 버전관리를 통해 제품의 성능을 
				 		향상시키고,서비스를 개선할 수 있는 인재양성을 목표로 한다.
						데이터베이스 구현을 위하여 DBMS(Data Base Management System)
						(DataBase Management Systems) 설치, 데이터베이스 생성, 데이
						터베이스 오브젝트를 계획, 설계하고 구현하는 능력을 함양한다.
					</td>
				</tr>
				<tr>
					<td>
						<div id="curri_des">
							<h3>커리큘럼이미지</h3>			
						</div>
					</td>
				</tr>
			</table>
	</div>
		<div id="under_list">
			<div id="list_button">
				<button type="button" id="list_btn">목록</button>
			</div>
			<div id="del_button">
				<button type="button" id="del_btn">삭제</button>
			</div>
			<div id="ans_button">
				<button type="button">수정</button>
			 	<!-- 등록 누르면 출력된 데이터 수강생관리에 전달 -->
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