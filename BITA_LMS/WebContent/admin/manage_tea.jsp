<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList,com.bit.model.TeacherDto" %>
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
	#content #page_name{
	width: 120px;
	margin: 0 auto;
	text-align:center;
	border: 1px solid gray;
	}
	#content #tea_list{
	clear:both;
	width: 600px;
	height:500px;
	margin: 0 auto;
	text-align:center;
	}
	#content #tea_list table{
	width: 600px;
	margin: 0 auto;
	}
	
	#content #tea_list table,th,td{
	border: 1px solid gray;
	}
	#content #tea_list #tea_pic{
	float: left;
	position:relative;
	left:33px;
	width: 80px;
	height:120px;
	border: 1px solid gray;
	}
	#content #tea_list #tea_table tbody table{
	float: right;
	position:relative;
	left:-30px;
	width: 230px;
	margin-top: 15px;
	}
	#content #tea_list #tea_table tbody table td{
	text-align:left;
	}
	#content #under_list{
	clear:both;
	width: 600px;
	height:95px;
	margin: 0 auto;
	}
	#content #under_list #insert_button{
	float:right;
	width: 50px;
	}
	#content #under_list #search_box{
	clear:both;
	width: 230px;
	margin: 0 auto;
	}
	#content #under_list #page_button{
	width: 150px;
	margin: 0 auto;
	}
</style>
<script type="text/javascript" src="./js/jquery-1.12.4.js"></script>
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
		$('#search_btn').click(function(){
			var select_sub=$("#search_sub").val();
			if(select_sub==""){
				$("#tea_table>tbody>tr").show();
				return false;
			}else{
				var temp = $("#tea_table>tbody>tr>td:nth-child(3):contains('" + select_sub+ "')");
				$("#tea_table>tbody>tr").hide();
	            $(temp).parent().show();
	            return false;
			}
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
			<h3>강사관리</h3>
			<br/><br/>
		</div>
		<div id="real_content">
			<br/>
			<div id="page_name">
				<h2>강사관리</h2>
			</div>
			<br/><br/>
			<div id="tea_list">
			<table id="tea_table">
				<thead>
					<tr>
						<th>번호</th>
						<th>강사정보</th>
						<th>강좌 시작일</th>
					</tr>
				</thead>
				<tbody>
				<%
				ArrayList<TeacherDto> teacherList = (ArrayList<TeacherDto>)request.getAttribute("teacherList");
				if(teacherList !=null){
					//String id = teacherList.get(0).getTeacherId();
					for(int i=0; i<teacherList.size(); i++){
						TeacherDto bean = teacherList.get(i);
						//if(i!=0 && id.equals(bean.getTeacherId())){
						//	continue;
						//}else{
						//	id = bean.getTeacherId();
						//}
				%>
					<tr onclick="location.href='manage_tea_detail.adm?idx=<%=bean.getTeacherId()%>'">
						<td><%=bean.getRowNum() %></td>
						<td>
							<div id="tea_pic">
								<h3>강사이미지<!-- 나중에 file data에서 빼올 것 --></h3>
							</div>
							<table>
								<tr>
									<td><%=bean.getName() %> / <%=bean.getLecName() %></td>
								</tr>
								<tr>
									<td><%=bean.getType() %></td>
								</tr>
								<tr>
									<td><%=bean.getContent() %></td>
								</tr>
							</table>
						</td>
						<td><%=bean.getStartDate() %></td>
					</tr>
					<%
						}
					}
					%>
				</tbody>
			</table>
		</div>
		<div id="under_list">
			<div id="insert_button">
				<button type="button" onClick="location.href='manage_tea_insert.adm'">등록</button>
			</div>
			<div id="search_box">
				<form action="register_list.adm">
					<input type="text" id="search_sub" name="search_sub">
					<button type="submit" id="search_btn">검색</button>
				</form>
			</div>
			<div id="page_button">
				<button><</button>
				<button>1</button>
				<button>2</button>
				<button>3</button>
				<button>></button>
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