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
	#content #page_name{
	width: 120px;
	margin: 0 auto;
	text-align:center;
	border: 1px solid gray;
	}
	#content #real_content{
	position:relative;
	left:300px;
	width: 600px;
	height:700px;
	}
	#content #tea_detail{
	clear:both;
	width: 500px;
	height:500px;
	margin: 0 auto;
	}
	#content #tea_detail #tea_pic{
	float:left;
	border: 1px solid gray;
	width: 100px;
	height:140px;
	}
	#content #real_content #tea_detail{
	width: 600px;
	}
	#content #real_content #tea_detail table,th,td{
	border: 1px solid gray;
	}
	#tea_table1{
	width:300px;
	} 
	#tea_table2{
	width:600px;
	height:320px;
	margin: 0 auto;
	}
	#tea_table2 input{
	width:160px;
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
	#content #under_list #ok_button{
	float: right;
	width: 50px;
	}
	#content #under_list #del_button{
	float: right;
	width: 50px;
	}
	#pic{
	width:100px;
	}
	#tea_pic label {
	position: absolute;
	top:235px;
	left:0px;
	height:15px;
  display: inline-block;
  padding: .5em .75em;
  color: #fff;
  font-size: inherit;
  line-height: 15px;
  vertical-align: middle;
  background-color: #2C528C;
  cursor: pointer;
  border: 1px solid #003366;
  border-radius: .25em;
  -webkit-transition: background-color 0.2s;
  transition: background-color 0.2s;
}

#tea_pic label:hover {
  background-color: #51A0D5;
}

#tea_pic label:active {
  background-color: #51A0D5;
}

#tea_pic input[type="file"] {
  position: absolute;
  width: 1px;
  height: 1px;
  padding: 0;
  margin: -1px;
  overflow: hidden;
  clip: rect(0, 0, 0, 0);
  border: 0;
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
		$('#list_btn').click(function(){
				location.replace('manage_tea.adm'); 
		});
		$('#reject_btn').click(function(){
			location.replace('manage_tea.adm'); 
		});
		$("#teacher").change(function() {
	        readURL(this);
	        
	    });
		$("#add_career1").click(function(){
			var plus = $("#add_career1").prev();
			plus.after('<input type="text" name="tea_career1" placeholder="회사명-개발내용"><br>');
		});
		$("#add_career2").click(function(){
			var plus = $("#add_career2").prev();
			plus.after('<input type="text" name="tea_career2" placeholder="책제목-출판사"><br>');
		});
		$("#add_qul").click(function(){
			var plus = $("#add_qul").prev();
			plus.after('<input type="text" name="tea_qul" placeholder="정처기"><br>');
		});
		
		$("#btn").click(function(){

			var result = txtFieldCheck() == true ? true : false;
			
			if(result==true && $('#tea_password').val()!=$('#tea_password_re').val()){
				alert('비밀번호와 재입력한 비밀번호가 맞지 않습니다.');
				$("#tea_password").focus();
				result = false;
			}

			
			if(result==true){
				$("#send_tea").attr("action", "manage_tea_insert.adm");
				$("#send_tea").submit();
			}

		});
	});
	
	 function readURL(input) {
	        if (input.files && input.files[0]) {
	            var reader = new FileReader();
	            reader.onload = function(e) {
	                $('#pic').attr('src', e.target.result);
	                $('#pic').attr('width', '100px');
	            }
	            reader.readAsDataURL(input.files[0]);
	        }
	 }
	 
	 function txtFieldCheck(){

		// form안의 모든 text type 조회

		var txtEle = $(".checkField");

		  

		for(var i = 0; i < txtEle.length; i ++){


			if("" == $(txtEle[i]).val() || null == $(txtEle[i]).val()){
	
				var ele_id = $(txtEle[i]).attr("id");
		
				var label_txt = $("label[for='" + ele_id +"']").text();
		
				console.log("id : " + ele_id + ", label : " + label_txt);
		
				showAlert(ele_id, label_txt);
				
				return false;
	
			}

		}
		return true;

	}

		function showAlert(ele_id, label_txt){

		alert(label_txt + " 은/는 꼭 입력해야합니다.");

		// 해당 id에 focus.

		$("#" + ele_id).focus();

		}
	    
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
			<h3>강사관리</h3>
			<br/><br/>
		</div>
		<form id="send_tea" method="post" enctype="multipart/form-data" action="" >
		<div id="real_content">
			<div id="page_name">
				<h2>강사정보</h2>
			</div>
			<br/><br/>
		<div id="tea_detail">
			<div id="tea_pic">
				<img id="pic"src="img/tea.png" />
				<label for="teacher">upload</label>
				<input type="file" name="teacher" id="teacher" class="checkField"/>
			</div>
			<table id="tea_table1">
					<tr>
						<td><label for="name">강사명</label></td>
						<td><input type="text" name="name" id="name" placeholder="김코난" class="checkField"></td>
					</tr>
					<tr>
						<td><label for="tea_level">학력</label></td>
						<td><input type="text" name="tea_level" id="tea_level" placeholder="세종대학교 컴퓨터공학 석사" class="checkField"></td>
					</tr>
			</table>
			<table id="tea_table2">
				<tr>
					<td>경력사항</td>
					<td>저서</td>
					<td>자격</td>
				</tr>
				<tr>
				<!-- 추가버튼누르면 빈칸 추가되면서 데이터 입력가능 -->
					<td><input type="text" name="tea_career1" placeholder="회사명-개발내용"><button type="button" id="add_career1">+</button></td>
					<td><input type="text" name="tea_career2" placeholder="책제목-출판사"><button type="button" id="add_career2">+</button></td>
					<td><input type="text" name="tea_qul" placeholder="정처기"><button type="button" id="add_qul">+</button></td>
				</tr>
				<tr>
					<td><label for="tea_id">ID</label></td>
					<td colspan="2"><input type="text" name="tea_id" id="tea_id" placeholder="4~12자 사이의 영문자 또는 영문자+숫자" class="checkField"></td>
				</tr>
				<tr>
					<td><label for="tea_password">비밀번호</label></td>
					<td colspan="2"><input type="password" name="tea_password" id="tea_password" placeholder="양문자+숫자" class="checkField"></td>
				</tr>
				<tr>
					<td><label for="tea_password_re">비밀번호 확인</label></td>
					<td colspan="2"><input type="password" name="tea_password_re" id="tea_password_re" placeholder="영문자+숫자" class="checkField"></td>
				</tr>
				<tr>
					<td><label for="tea_mail">이메일</label></td>
					<td colspan="2"><input type="email" name="tea_mail" id="tea_mail" placeholder="kmkm@naver.com" class="checkField"></td>
				</tr>
				<tr>
					<td><label for="tea_tel">연락처</label></td>
					<td colspan="2"><input type="tel" name="tea_tel" id="tea_tel" placeholder="00*-000*-00000" pattern="[0-9]{2,3}-[0-9]{3,4}-[0-9]{4}" class="checkField"></td>
				</tr>
			</table>
	</div>
		<div id="under_list">
			<div id="list_button">
				<button type="button" id="list_btn">목록</button>
			</div>
			<div id="del_button">
				<button type="button" id="reject_btn">취소</button>
			</div>
			<div id="ok_button">
				<button type="button" id="btn">확인</button>
			 	<!-- 등록 누르면 출력된 데이터 수강생관리에 전달 -->
			</div>
		</div>
	</div>
	</form>
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
