<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="com.bit.model.LectureDto, java.util.ArrayList,com.bit.model.UserDto"%>
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
	#content #people_check{
	float:right;
	width: 130px;
	}
	#content #lecture_list{
	clear:both;
	float:right;
	width: 55px;
	}
	#content #stu_list{
	clear:both;
	width: 600px;
	height:500px;
	margin: 0 auto;
	text-align:center;
	}
	#content #stu_list table{
	width: 600px;
	margin: 0 auto;
	}
	#content #stu_list table,th,td{
	border:1px solid gray;
	}
	#content #under_list{
	width: 500px;
	height:95px;
	margin: 0 auto; 
	}
	#content #under_list #del_button{
	float:right;
	width: 45px;
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
<script type="text/javascript" src="js/jquery-1.12.4.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		$('input[type=checkbox]').prop( 'checked', false )
		
		
		$( '#ck_all' ).click( function() {
          $( '.stu_ck' ).prop( 'checked', this.checked );
        } );
		
		var stuList = $('#stu_list>table>tbody>tr');
		
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
		
		var form = $('#form')
		
		$('#del_btn').click(submitForm);
		
		$("select[name=lecture_name]").change(
	               function() {
	                  var k = $(this).children("option:selected").text();
	                  if(k=="전체"){
	                	  stuList.show();
	                  }
	                  else{
	                	  stuList.hide();
		                  var temp = $("#stu_list>table>tbody>tr>td:contains('"
		                        + k + "')");
		                  $(temp).parent().show();
	                  }
	         });
	});
	
	
	
	var submitForm = function() {
		var result = confirm('정말 삭제하시겠습니까?'); 
		if(result) { 
			//yes-해당수강신청삭제
			    if ($('#form').find('input[name=userId]').length == 'undefined') //단일
					{
  						 if ($('#form').find('input[name=userId]').is(":checked")==false)
		        	{
  						form.elements['userId'].attr( 'disabled', true );
		       		 }
					} else { //다중
				        for (i=0; i<$('#form').find('input[name=userId]'); i++)
				        {
				            if ($('#form').find('input[name=userId]').is(":checked").checked==false)
				            {
				            	$('#form').find('input[name=userId]').eq(i).attr( 'disabled', true );
				            }
				        }
				    }
			
				$("#form").attr("method", "post");
				$("#form").attr("target", "hid_reload");
				$("#form").attr("action", "user_delete.adm");
				$("#form").submit().promise().done(function() {
					window.location.reload();
				});
		} 
		else { 
			//no-변동사항없음
		} 
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
				<h2>수강생관리</h2>
			</div>
			<div id="lecture_list">
				<select name="lecture_name">
				    <option value="" selected="selected">전체</option>
				<%
				ArrayList<LectureDto> lectureList = (ArrayList<LectureDto>)request.getAttribute("arrangeLectureList");
				if(lectureList !=null){
				for(LectureDto bean : lectureList){
						if(bean.getLectureId()>0){
				%>
					<option value="" ><%=bean.getName()%></option>
				<%
						}
					}
				}
				%>
				</select>
			</div>
			<div id="month_ck">
				<button type="button" onclick="location.href = 'manage_stu_month.adm'">월별출석표 보기</button>
			</div>
			<div id="stu_list">
			<form id="form" method="post">
			<table>
				<thead>
					<tr>
						<th>번호</th>
						<th>이름</th>
						<th>출석률</th>
						<th>출석상태</th>
						<th>강좌</th>
						<th><input type="checkbox" id="ck_all"></th>
					</tr>
				</thead>
				<tbody>
					<%
								ArrayList<UserDto> userBean = (ArrayList<UserDto>)request.getAttribute("userBean");
								if(userBean !=null){
									for(UserDto bean : userBean){
					%>
					<tr>
						<td><%=bean.getRowNum() %></td>
						<td><%=bean.getName() %></td>
						<td><progress value="<%=bean.getAttendanceDays() %>" max="<%=bean.getTotalDays()%>"></progress></td>
						<td><%=bean.getAttendanceStatus() %></td>
						<td><%=bean.getLectureName() %></td>
						<td><input type="checkbox" name="userId" class="stu_ck" value="<%=bean.getUserId() %>"></td>
					</tr>
					<%
									}
								}
					%>
					<!-- 
					<tr>
						<td>1</td>
						<td>김경민</td>
						<td><progress value="60" max="100"></progress></td>
						<td>출석</td>
						<td>JAVA</td>
						<td><input type="checkbox" id="stu_ck"></td>
					</tr>
					 -->
				</tbody>
			</table>
			</form>
			<iframe name="hid_reload" style="display:none"></iframe>
		</div>
		<div id="under_list">
			<div id="del_button">
				<button type="button" id="del_btn">삭제</button>
			</div>
			<div id="search_box">
				<form action="register_list.adm">
					<input type="text" id="search_sub" name="search_sub">
					<button type="submit">검색</button>
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