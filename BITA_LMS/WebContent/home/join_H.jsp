<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link href="https://fonts.googleapis.com/css?family=Noto+Sans+KR:100,300,400,500,700,900&display=swap&subset=korean" rel="stylesheet">
<link type="text/css" rel="stylesheet" href="css/frame_H.css" />
<link type="text/css" rel="stylesheet" href="css/loginframe_H.css" />
<link rel="stylesheet" type="text/css" href="css/jquery.bxslider.css" />
<style type="text/css">
#menu>ul {
	width: 760px; 
	list-style-type: none;
	margin: 0px auto;
}
#content{
	clear:both;
	width: 70%;
	height:100%;
	margin: 0px auto;
	position:relative;
}
#content #sidebar{
	position:absolute;
	float:left;
	height:100%; 
	width: 187px;
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
#content #con_form1{
	float:right;
	position:absolute;
	left:200px;      
	width: 80%;   
	height:100%;
	overflow: auto;
} 
#content #con_form2{ 
	background-color: rgb(220,220,220);
	width: 600px;
	height:720px;
	line-height: 47px; 
	margin: 100px auto;
}
#content #con_form2 input[type=text],input[type=password]{
	width:130px; 
}
#content #con_form2 #inflow{
	border-radius: 10px;
	width:500px;
	height:100px;
	background-color: rgb(190,190,190);
	margin: 0 auto;
}
#content #con_form2 #agree_des{
	width:600px;
	height:150px;
	overflow: auto;
	background-color:rgb(190,190,190); 
}
#content #con_form2 #agree_des p {
	 margin-top: 0;
	 margin-bottom: 0;  
} 
#inflow input[type=checkbox]{  
	width: 65px;  
}
#content #con_form2 div[type=button]{
	margin: 0 auto;
}
#header>#login_btn{
	top:-75px;
	right:15px;
	position:absolute;
	float:right;
	cursor: pointer;
}
#header>#logout_btn{
	top:-75px;
	right:15px;
	position:absolute;
	float:right;
	cursor: pointer;
}
</style>
<script type="text/javascript" src="js/jquery-1.12.4.js"></script>
<script type="text/javascript" src="js/jquery.bxslider.js"></script>
<script type="text/javascript">
$(document).ready(function() {
	$('.topmenu1').on({
		mouseenter: function(){$('.submenu1').css('display', 'block');},
		mouseleave: function(){$('.submenu1').css('display', 'none');}
	});
	$('.topmenu2').on({
		mouseenter: function(){$('.submenu2').css('display', 'block');},
		mouseleave: function(){$('.submenu2').css('display', 'none');}
	});
	$('.topmenu3').on({
		mouseenter: function(){$('.submenu3').css('display', 'block');},
		mouseleave: function(){$('.submenu3').css('display', 'none');}
	});
	$('.topmenu4').on({
		mouseenter: function(){$('.submenu4').css('display', 'block');},
		mouseleave: function(){$('.submenu4').css('display', 'none');}
	});	
	$('#header>img').click(function() {
		location.href = 'main.home'
	}).mouseenter(function(){
		$('#header>img').css('cursor', 'pointer')
	});
	$("#login_btn").click(function(){
		$('#myModal').show();
	});
	$("#exit").click(function(){
           $('#myModal').hide();
      });
	$(document).mouseup(function (e){
		var container=$("#myModal");
		if(container.has(e.target).length===0)
			container.hide();
	});
	$("#Lms_login").click(function(){
		window.open('login.bit');
	});
	//로그인 클릭&엔터시 데이터 submit
	$('input').keypress(function (e) {
	 var key = e.which;
	 if(key == 13)  // the enter key code
	  {
	    $('#signin_btn').click();
	    return false;  
	  }
	});
	$("#signin_btn").click(function(){
		var id = $("#log_id").val();
           var pw = $("#log_pw").val();

           if (id == "") {
               alert("아이디를 입력해주세요");
               $("#log_id").focus();
               return;
           }
           if (pw == "") {
               alert("비밀번호를 입력해주세요");
               $("#log_pw").focus();
               return;
           }
           //비동기 ajax 방식으로 데이터 주고 받기
           var data = "id=" + id + "&pw=" + pw;
           
           $.ajax({
               type : "post",
               data : data,
               url : "/BITA_LMS/login.home",
               success : function(value) {					//로그인 성공 시 위에 환영문구 뜸
               	console.log(value);
               	$('#login_btn').remove();
               	$('#header').append(value);
               	$('#myModal').hide();
               	location.href = 'main.home';		//회원가입페이지에서 로그인하면 메인화면으로 이동
               },
               error : function(){
               	alert("id&pw를 다시 확인하세요");
               }   
		});
	}); 
	$('#submit_btn').click(function(){				//회원가입 유효성검사
		var getCheck= RegExp(/^[a-zA-Z0-9]{4,12}$/);
		var inflow_check = false;
		var getMail = RegExp(/^[A-Za-z0-9_\.\-]+@[A-Za-z0-9\-]+\.[A-Za-z0-9\-]+/);
		var fmt = RegExp(/^\d{6}[1234]\d{6}$/);
		var agree = $("#agree").is(":checked");   //약관동의 체크(true)상태여야 함 
		//아이디 공백 확인
	      if($("#id").val() == ""){
	        alert("아이디를 입력해주세요");
	        $("#id").focus();
	        return false;
	      }
	    //아이디 유효성검사
	      if(!getCheck.test($("#id").val())){
	        alert("아이디는 4~12자의 영문 대소문자와 숫자로만 입력해주세요");
	        $("#id").val("");
	        $("#id").focus();
	        return false;
	      }
	      //아이디 비밀번호 같음 확인
	      if($("#id").val() == $("#pw").val()){
	        alert("아이디와 비밀번호가 같습니다");
	        $("#pw").val("");
	        $("#pw").focus();
	        return false;
	      }
	    //비밀번호  공백 확인
	      if($("#pw").val() == ""){
	        alert("비밀번호를 입력해주세요");
	        $("#pw").focus();
	        return false;
	      }
	      //비밀번호 유효성검사
	      if(!getCheck.test($("#pw").val())){
	        alert("비밀번호는 4~12자의 영문 대소문자와 숫자로만 입력해주세요");
	        $("#pw").val("");
	        $("#pw").focus();
	        return false;
	      }
	           
	      //비밀번호 확인란 공백 확인
	      if($("#pw_re").val() == ""){
	        alert("비밀번호 확인란을 입력해주세요");
	        $("#pw_re").focus();
	        return false;
	      }
	           
	      //비밀번호 서로확인
	      if($("#pw").val() != $("#pw_re").val()){
	          alert("비밀번호가 상이합니다");
	          $("#pw").val("");
	          $("#pw_re").val("");
	          $("#pw_re").focus();
	          return false;
	      }
	      //이름 공백 검사
	      if($("#name").val() == ""){
	        alert("이름을 입력해주세요");
	        $("#name").focus();
	        return false;
	      }
	          
	      //이메일 공백 확인
	      if($("#email").val() == ""){
	        alert("이메일을 입력해주세요");
	        $("#email").focus();
	        return false;
	      }
 
	      //이메일 유효성 검사
	      if(!getMail.test($("#email").val())){
	        alert("이메일형식에 맞게 입력해주세요")
	        $("#email").val("");
	        $("#email").focus();
	        return false;
	      }
	      //연락처 공백검사
	      if($("#phone_num").val() == ""){
	          alert("연락처를 입력해주세요")
	          $("#phone_num").val("");
	          $("#phone_num").focus();
	          return false;
	          }
	      //유입경로 유효성 검사
	      for(var i=0;i<$('[name="inflow_path"]').length;i++){
	            if($('input:checkbox[name="inflow_path"]').eq(i).is(":checked") == true) {
	            	inflow_check = true;
	              break;
	                }
	              }    
	       if(!inflow_check){
	            alert("하나이상 관심분야를 체크해 주세요");
	            return false;
	      	}
	       if(agree!=true){  
		       	alert("약관에 동의해주세요");
		        return false;
		   	}
	       $("#joinform").submit();

	});
	$("#id_chk").click(function(){
		window.open('idcheck.home','pop', 'menubar=no,status=no,scrollbars=no,resizable=no ,width=320,height=300,top=50,left=50');
	});
});  
</script>
</head>
<body>
	<div id="header">
			<%
			if(session.getAttribute("userBean")==null){ 
			%>
			<a href="#" id="login_btn">login</a>
			<%} 
			%>
			<img alt="logo" src="img/logo.jpg" />
		</div>
		<div id="menu">
			<ul>
				<li class="topmenu1"><a href="#">학원소개</a>
					<ul class="submenu1">
						<li><a href="#">학원안내</a></li>
						<li><a href="#">강사진소개</a></li>
						<li><a href="#">오시는길</a></li>
					</ul></li>
				<li class="topmenu2"><a href="#">수강안내</a>
					<ul class="submenu2">
						<li><a href="#">강좌정보</a></li>
						<li><a href="apply.home">수강신청</a></li>
					</ul></li>
				<li class="topmenu3"><a href="#">커뮤니티</a>
					<ul class="submenu3">
						<li><a href="#">수강후기</a></li>
						<li><a href="#">자료실</a></li>
					</ul></li>
				<li><a href="#" id="Lms_login">E-Learning</a></li>
				<li class="topmenu4"><a href="#">고객센터</a>
					<ul class="submenu4">
						<li><a href="#">공지사항</a></li>
						<li><a href="#">Q&A</a></li>
					</ul></li>	
			</ul>
		</div>
		<div id="content">
		<div id="sidebar">
			<br/><br/>
			<h3>회원가입</h3>
			<br/>
		</div>
		<div id="con_form1">
		<form method="post" action="join.home" id="joinform">
			<div id="con_form2">
				<div>
					<label>ID</label>
					<%
					String id_ck = (String)request.getAttribute("id");
					if(id_ck==null){
						System.out.print(id_ck);
					%>
					<input type="text" name="id" id="id"  placeholder="내용을 입력해주세요" /><button type="button" id="id_chk">중복확인</button>
					<%
					}else {
					%>
					<input type="text" name="id" id="id" value="<%=id_ck %>" /><button type="button" id="id_chk">중복확인</button>
					<%
					}
					%>				
				</div>
				<div>
					<label>비밀번호</label>
					<input type="password" name="pw" id="pw" placeholder="내용을 입력해주세요" />
				</div>
				<div>
					<label>비밀번호 확인</label>
					<input type="password" name="pw_re" id="pw_re" placeholder="내용을 입력해주세요" />
				</div>
				<div> 
					<label>이름</label>
					<input type="text" name="name" id="name" placeholder="내용을 입력해주세요" />
				</div>
				<div>
					<label>이메일</label>
					<input type="email" name="email" id="email">
				</div>
				<div>
					<label>연락처</label>
						<input type="tel"name="phone_num" id="phone_num" placeholder="00*-000*-00000"
						pattern="[0-9]{2,3}-[0-9]{3,4}-[0-9]{4}" /> 
				</div>
				<div>
					<label>유입경로(중복선택 가능)</label>
					<div id="inflow"> 
						<input type="checkbox" name="inflow_path" value="1"><label>지인소개</label>
	              		<input type="checkbox" name="inflow_path" value="2"><label>인터넷 검색</label>
	              		<input type="checkbox" name="inflow_path" value="3"><label>블로그/카페</label>
	              		<input type="checkbox" name="inflow_path" value="4"><label>커뮤니티</label>
	              		<input type="checkbox" name="inflow_path" value="5"><label>학원생 추천</label>
	              		<input type="checkbox" name="inflow_path" value="6">
	              		<input type="text" name="ect" id="ect" placeholder="기타" />
	              	</div>
				</div>
				<div>
					<label>약관동의</label> 
					<div id="agree_des">
					<p>비트캠프(안양지점)가 수집하는 개인정보의 항목첫째, 회사는 회원가 입, 원활한 고객상담, 각종 서비스의 제공을 위해 최초 회원가입 당시 아래와 같은 최소한의 개인정보를 필수항목으로 수집하고 있습니다.<br />
						회원가입<br />
						- 이름, 생년월일, 성별, 아이디, 비밀번호, 별명, 연락처(메일주소, 휴대폰 번호 중 선택), 가입인증정보<br />
						만14세 미만 아동 회원가입 <br />
						- 이름, 생년월일, 성별, 법정대리인 정보, 아이디, 비밀번호, 연락처 (메일주소, 휴대폰 번호 중 선택), 가입인증정보<br />
						단체아이디 회원가입 <br />
						- 단체아이디, 회사명, 대표자명, 대표 전화번호, 대표 이메일 주소, 단체주소, 관리자 아이디, 관리자 연락처, 관리자 부서/직위<br />
						- 선택항목 : 대표 홈페이지, 대표 팩스번호<br />
						둘째, 서비스 이용과정이나 사업처리 과정에서 아래와 같은 정보들이 자동으로 생성되어 수집될 수 있습니다.<br />
						- IP Address, 쿠키, 방문 일시, 서비스 이용 기록, 불량 이용 기록<br />
						셋째, 네이버 아이디를 이용한 부가 서비스 및 맞춤식 서비스 이용 또는 이벤트 응모 과정에서 해당 서비스의 이용자에 한해서만 개인정보 추가 수집이 발생할 수 있으며, 이러한 경우 별도의 동의를 받습니다. <br />
						넷째, 성인컨텐츠, 유료/게임 등 일부 서비스 이용시 관련 법률 준수를 위해 본인인증이 필요한 경우, 아래와 같은 정보들이 수집될 수 있습니다. <br />
						- 이름, 생년월일, 성별, 중복가입확인정보(DI), 암호화된 동일인 식별정보(CI), 휴대폰 번호(선택), 아이핀 번호(아이핀 이용시), 내/외국인 정보
					</p>
					</div>
   					<div><input type="checkbox" name="agree" id="agree"> 개인정보 수집 및 이용에 동의합니다.</div> 
				</div> 
				<div>
					<button type="button" id="submit_btn">확인</button>&nbsp;&nbsp;
					<button type="reset" id="cancle_btn">취소</button>
				</div>
			</div>
			</form>
		</div>
		<div id="myModal" class="modal">		<!-- login 모달창 -->
	 	<div class="modal_content">
		 	 <div id="wrap1"> 
	 		<a href="#"><img alt="exit" id="exit" src="img/exit_btn.png" /></a>
		 	<img alt="logo" id="log_logo" src="img/logo.jpg" />
		 	 	<div id="wrap2">
				    <input type="text" id="log_id" name="id" placeholder=" ID"/>
				    <input type="password" id="log_pw" name="pw" placeholder=" PASSWORD" />
			    </div>
			    <div id="wrap3">
			    	<button type="submit" id="signin_btn">sign in</button>
			    	<p>아직 계정이 없으십니까?&nbsp;&nbsp;<a href="join.home">가입하기</a></p>
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
<%
	//ui를 깨지지 않게 하려면 항상 body 닫기 전에 넣는 것이 좋다. 로딩하다가 출력되기 때문
	Object obj = request.getAttribute("errmsg");
	if (obj != null)
	out.println(obj);
%>
	</body>
</html>