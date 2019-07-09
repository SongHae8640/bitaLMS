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

* {
	-moz-box-sizing: border-box;
	box-sizing: border-box;
}
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
	left:250px;      
	width: 70%;   
	height:100%;
	} 
	#content #con_form2{ 
	background-color: rgb(220,220,220);
	border-radius: 10px;
	width: 600px;
	height:550px;
	line-height: 50px; 
	margin: 50px auto;
	}
	#content #con_form2 input[type=text]{
	width:130px; 
	}
	#content #con_form2 #agree_des{
	width:600px;
	height:200px; 
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
	}); 
</script>
</head>
<body>
	<div id="header">
			<a href="#" id="login_btn">login</a> 
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
			<h3>수강안내</h3>
			<br/>
			<div><span>강좌정보</span></div>
			<div><span>수강신청</span></div>
		</div>
		<div id="con_form1">
		<form method="post" action="join.home">
			<div id="con_form2">
				<div>
					<div>
						<label>이름</label>
						<input type="text" name="name" placeholder="내용을 입력해주세요" />
					</div>
					<div>
						<label>강좌선택</label>
				              <select name="lec_select">
				                 <option value="JAVA">JAVA</option>
				                 <option value="WEB">WEB</option>
				                 <option value="DB">DB</option>
				         	</select>
					</div>
					<div>
						<label>연락처</label>
						<input type="text"name="tel1"> -
	               		<input type="text" name="tel2"> -
	               		<input type="text" name="tel3">
					</div>
					<div id="file_upload">
						<label>파일첨부</label>
						<input type="file"id="user_apply"/>
					</div>
					<div>
						<label>약관동의</label>
						<div id="agree_des">
						<p>수강신청 안내
							수강신청을 위해서는 반드시 비트캠프(안양지점) 홈페이지에 회원가입을 하셔야 합니다.<br />
							수강신청은 인터넷 선착순 접수로 진행합니다.(수강료 결제완료시 수강등록 완료 됨)<br />
							회원가입 후 인터넷 수강신청에서 수강신청을 진행하시고, 관리자로부터 승인을 받아야 수강등록이 완료 됩니다.<br />
							본인의 신청내역 및 진행사항은 홈페이지 상단의 마이페이지를 통하여 확인 가능 합니다.<br />
							인터넷 신청 유의사항<br />
							전 강좌의 강사와 시간은 경우에 따라 변동될 수 있으며, 강사의 사정에 의해 대강 또는 불가피하게 강의일과 시간이 변경될 수 있습니다.<br />
							적정인원에 미달(정원대비 60%)될 경우 폐강될 수 있습니다. (교육비 전액 환불)<br />
							교육에 필요한 교재비, 재료비 등은 별도 개인부담입니다.<br />
							접수기간동안 1회에 한하여 수강료가 동일할 경우 과목 변경 가능하며, 6일후 정원마감과 상관없이 모든과목의 수강생모집을 종료합니다.
						</p>
						</div>
   						<div><input type="checkbox" name="agree" id="agree"> 개인정보 수집 및 이용에 동의합니다.</div> 
					<div>
						<button type="submit">확인</button>&nbsp;&nbsp;
						<button type="button">취소</button>
					</div>
				</div> 
			</div>
			</div>
			</form>
		</div>
		<div id="myModal" class="modal">		<!-- login 모달창 -->
	 	<div class="modal_content">
		 	 <div id="wrap1"> 
	 		<a href="#"><img alt="exit" id="exit" src="img/exit_btn.png" /></a>
		 	<img alt="logo" id="log_logo" src="img/logo.jpg" />
			<form action="#" id="sendlogin" name="sendlogin" method="POST">
		 	 	<div id="wrap2">
				    <input type="text" id="id" name="id" placeholder=" ID"/>
				    <input type="password" id="pw" name="pw" placeholder=" PASSWORD" />
			    </div>
			    <div id="wrap3">
			    	<button type="submit">sign in</button>
			    	<p>아직 계정이 없으십니까?&nbsp;&nbsp;<a href="join.home">가입하기</a></p>
			    </div>
 			</form>
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
</body>
</html>
	</body>
</html>