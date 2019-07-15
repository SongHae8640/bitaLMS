<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList,com.bit.model.LectureDto"%>
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
	height:500px;
	margin: 0 auto;
	text-align:center;
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
	#content #under_list #update_button{
	float:right;
	width: 45px;
	}
	#api{
		width:100%;
		float:right;
	}
	#api>p{
	float:right;
	margin:0px;
	padding:0px;
	}
</style>
<script type="text/javascript" src="js/jquery-1.12.4.js"></script>
<script type="text/javascript">
	var obj = null;
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
		$('#header>img').click(function() {
			location.href = 'main.adm'
		}).mouseenter(function(){
			$('#header>img').css('cursor', 'pointer')
		});
		
		$('#ok_button').hide().next().hide();
		ajaxCall();
		
		$('#update_button').click(function(){
			$(this).hide();
			$('#ok_button').show().next().show();
			var arr = $('#att_list>table td');
			$.each(arr, function (index, item) { // 두 번째 매개변수로는 콜백함수인데 콜백함수의 매개변수 중
				// 첫 번째 index는 배열의 인덱스 또는 객체의 키를 의미하고
				// 두 번째 매개 변수 item은 해당 인덱스나 키가 가진 값을 의미합니다.
				var itemText = $(item).text();
				if(itemText=="공"){					
					$(item).html('<select name="attendance_name"><option value="" >공</option></select>');
				}else{
					$(item).html('<select name="attendance_name"><option value="" >'+itemText+'</option><option value="" >공</option></select>');					
				}
			});
			
		});
		
		$('#reject_button').click(function(){
			$('#att_list>table>thead>tr>th').remove();
			$('#att_list>table>tbody').children().remove();
			ajaxCall();
			$(this).hide().next().show();
			$('#ok_button').hide();
		});
		
		
		$('#ok_button').click(ajaxBtnCall);
		
		$('.monthBtn').click(ajaxMonthCall);
	});
	
	var ajaxCall = function() {
		//맨처음 데이터 가져올 때 & 월별 데이터 가져올때
		$.when($.ajax({ 
			url: "manage_stu_month.adm", 
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
					tbody.append("<tr><th>"+value.name+"</th>"+'<input type="hidden" value="'+value.id+'" class="id"/>'+'<input type="hidden" value="'+value.lecName+'" class="lecture"/>'+"</tr>");
					cnt++;
				}else{
					if(check==value.name){
					}else{
						check = value.name;
						tbody.append("<tr><th>"+value.name+"</th>"+'<input type="hidden" value="'+value.id+'" class="id"/>'+'<input type="hidden" value="'+value.lecName+'" class="lecture"/>'+"</tr>");
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
					tbody.children().eq(0).children().eq(idx+2).text(value.status);
					console.log(value.dayTime);
					num++;
				}else{
					if(check!=value.name){
						check = value.name;
						tbody.children().eq(num).children().eq(idx+2).text(value.status);
						console.log(value.dayTime);
						num++;
					}else{
						num--;
						tbody.children().eq(num).children().eq(idx+2).text(value.status);
						console.log(value.dayTime);
						num++;
					}
				}
			});
			
			$("select[name=lecture_name]").change(
		               function() {
		                  var k = $("select[name=lecture_name]").children("option:selected").text();
		                  if(k=="전체"){
		                	  $('#att_list>table>tbody>tr').show();
		                  }
		                  else {
			                 $("#att_list>table>tbody>tr").hide();
			                 $('.lecture[value*='+k+']').parent().show();
		                  }
		     });
		});
	}
	
	var ajaxBtnCall = function() {
		//수정확인 버튼을 눌렀을 때
		var result = null;
		var nowDay = $('#yearMonth').text();
		var arrStatusNum = $('#att_list>table tr').children().find('select option:selected').length;
		var arrStatus = new Array(arrStatusNum);
		for(var i=0; i<arrStatusNum; i++){
			if($('#att_list>table tr').children().find('select option:selected').eq(i).text()==""){
				arrStatus[i]="#";
			}else{
				arrStatus[i] = $('#att_list>table tr').children().find('select option:selected').eq(i).text();				
			}
		}
		
		var arrIdNum = $('.id').length;
	    var arrId = new Array(arrIdNum);
	    for(var i=0; i<arrIdNum; i++){                          
	    	arrId[i] = $('.id').eq(i).val();
	    }
	    console.log(arrId);
	    console.log(arrStatus[0]);
		$.when($.ajax({
			url: "manage_stu_month_update.adm", 
			dataType: "json", 
			data: {nowDay:nowDay, arrId:arrId, arrStatus:arrStatus},
			//data: [{nowDay:nowDay},{arrId:arrId},{arrStatus:arrStatus}],
			type: "post",
			traditional: true,
			success: function(data){
				result=data;
				
			},error : function(){
                alert("통신실패");
            }
		})).done(function() {
			console.log(result.msg);
			$('#att_list>table>thead>tr>th').remove();
			$('#att_list>table>tbody').children().remove();
			ajaxCall();
			$('#ok_button').hide().next().hide();
			$('#update_button').show();
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
						<button id="prevMonth" class="monthBtn"><</button>
						<label id="yearMonth"></label>
						<button id="nextMonth" class="monthBtn">></button>
					</div>
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
		<div id="under_list">
			<div id="ok_button">
				<button type="button">확인</button>
			</div>
			<div id="reject_button">
				<button type="button">취소</button>
			</div>
			<div id="update_button">
				<button type="button">수정</button>
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