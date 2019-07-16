<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList,com.bit.model.TeacherDto,com.bit.model.LectureDto"%>
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
	#content #real_content #lec_detail{
	width: 600px;
	}
	#content #real_content #lec_detail #curri_thumb{
	float: left;
	width:100px;
	height:140px;
	border: 1px solid gray;
	margin-top: 5px;
	}
	#content #real_content #lec_detail table,th,td{
	border: 1px solid gray;
	}
	#lec_table1{
	width:400px;
	}
	#lec_table1 input{
	width:80px;
	}
	#lec_table2{
	width:600px;
	height:320px;
	margin: 0 auto;
	}

	#content #real_content #lec_detail #qna_content {
	width:600px;
	}
	#content #real_content #lec_detail #qna_content div{
	clear:both;
	width:300px;
	}
	#content #real_content #lec_detail #curri_thumb{
	float: left;
	width:150px;
	height:140px;
	border: 1px solid gray;
	}
	#content #page_name{
	width: 120px;
	margin: 0 auto;
	text-align:center;
	border: 1px solid gray;
	}
	#content #lec_detail{
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
	#content #under_list #ok_button{
	float: right;
	width: 45px;
	}
	#content #under_list #reject_button{
	float: right;
	width: 45px;
	}
	
#pic, #curri_thumb{
	width:150px;
	}
	#curri_thumb label {
	position: absolute;
	top:280px;
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

#curri_thumb label:hover {
  background-color: #51A0D5;
}

#curri_thumb label:active {
  background-color: #51A0D5;
}

#curri_thumb input[type="file"] {
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
			location.replace('manage_lec.adm');  
		});
		$('#reject_btn').click(function(){
			//원래글 디테일
			window.history.back();
		});
		$("#btn").click(function(){

			var result = txtFieldCheck() == true ? true : false;

			
			if(result==true){
				$("#send_tea").attr("action", "manage_tea_insert.adm");
				$("#send_tea").submit();
			}

		});
		
		$("#lecture").change(function() {
	        readURL(this);
	        
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
			<h3>강좌관리</h3>
			<br/><br/>
		</div>
		<form name="send_lec" method="post" action="manage_lec_update.adm" accept-charset="utf-8"  enctype="multipart/form-data">
		<div id="real_content">
		<br />

			<div id="page_name">
				<h2>강좌수정</h2>
			</div>
			<br/><br/>
		<div id="lec_detail">
			<div id="curri_thumb">
				<img src="\BITA_LMS\save\lecture\JAVA.jpg" id="curri_thumb">
				<label for="lecture">upload</label>
				<input type="file" name="lecture" id="lecture" class="checkField"/>
			</div>
			<table id="lec_table1">
					<tr>
					<%
								LectureDto lectureBean = (LectureDto)request.getAttribute("lectureBean");
								if(lectureBean !=null){
					%>
						<td><label for="lec_name">강좌명</label><input type="hidden" name="lec_id" id="lec_id" value="<%=lectureBean.getLectureId() %>"></td>
						<td><input type="text" name="lec_name" id="lec_name" class="checkField" value="<%=lectureBean.getName() %>"></td>
					</tr>
					<tr>
						<td>강사명</td>
						<td>
							<select name="tea_name" class="checkField">
							<option value="<%=lectureBean.getTeaId() %>"><%=lectureBean.getTeaName() %></option>
							<%
				ArrayList<TeacherDto> teacherList = (ArrayList<TeacherDto>)request.getAttribute("teacherList");
				if(teacherList !=null){
					for(int i=0; i<teacherList.size(); i++){
						
				%>
							    <option value="<%=teacherList.get(i).getTeacherId() %>"><%=teacherList.get(i).getName() %></option>
							    <%
					}
				}
							    %>
							</select>
						</td>
					</tr>
					<tr>
						<td>교육기간</td>
						<td>
						<input type="date" name="lec_start" style="width: 120px" class="checkField" value="<%=lectureBean.getStartDate() %>">
						~
						<input type="date" name="lec_end" style="width: 120px" class="checkField" value="<%=lectureBean.getEndDate() %>">
						</td>
					</tr>
					<tr>
						<td><input type="text" name="lec_level" class="checkField" value="<%=lectureBean.getLv() %>">수준</td>
					</tr>
					<tr>
						<td><label for="max_stu">최대인원</label></td>
						<td><input type="text" name="max_stu" id="max_stu" class="checkField" value="<%=lectureBean.getMaxStd() %>"></td>

					</tr>
			</table>
			<table id="lec_table2">
				<tr>
					<td>
					<label for="content_area">강의내용</label>
						<div>
						<textarea name="content_area" id="content_area" rows="6" cols="70"><%=lectureBean.getContent() %></textarea>
						</div>
					</td>
				</tr>
				<tr>
					<td>
						<label for="lec_file">커리큘럼 이미지</label>
						<div id="curri_des">
							<img src="\BITA_LMS\save\lecture\java_curri.jpg" id="curri_thumb">
							<input type="file" name="lec_file" id="lec_file"/>
						</div>
					</td> 
				</tr>
				<%
								}
					%>
			</table>
	</div>
		<div id="under_list">
			<div id="list_button">
				<button type="button" id="list_btn">목록</button>
			</div>
			<div id="reject_button">
				<button type="button" id="reject_btn">취소</button>
			</div>
			<div id="ok_button">
				<button type="submit" id="ok_btn">확인</button>
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