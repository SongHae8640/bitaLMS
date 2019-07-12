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
	width: 800px;
	height:700px;
	margin: 0px auto;
	}
	#content #con_form1{ 
	width: 800px;
	margin: 30px auto;
	}
	#content #con_form2{
	width: 360px;
	height:223px;
	float: left;
	line-height: 0px;
	}
	#content #con_form2 div{
	border: 1px solid white;
	height: 50px;
	}
	#content #con_form2 div div{
	border: 1px solid white;
	height: 30px;
	background-color: rgb(200,200,200);
	}
	#content #con_form2 div div a{
	text-decoration: none;
	color: black;
	line-height: 22px;
	}
	#content #con_form2 div #plus_btn{
	width: 30px;
	height: 30px;
	float: right;
	position:relative;
	top: -30px;
	}
	#content #con_form3{
	width: 360px;
	height:223px;
	float: right;
	line-height: 0px;
	}
	#content #con_form3 div{
	border: 1px solid white;
	height: 50px;
	}
	#content #con_form3 div div{
	border: 1px solid white;
	height: 30px;
	background-color: rgb(200,200,200);
	} 
	#content #con_form3 div div a{
	text-decoration: none;
	color: black;
	line-height: 22px;
	}
	#content #con_form3 div #plus_btn{
	width: 30px;
	height: 30px;
	float: right;
	position:relative;
	top: -30px;
	}
	#bxslider img{
	height: 300px;
	max-width: auto;
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
		$('#bxslider').bxSlider({
			hideControlOnEnd: true,
		    minSlides:1,
		    maxSlides:1,
		    slideWidth:800,
		    pager:true,
		    auto:true,
		    autoHover:true,
		    controls:true
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
		<div id="con_form1">
			   <div id="bxslider">
	               <img  src="img/slider01.png"alt="b"/>
	               <img  src="img/slider02.png" alt="b"/>
	               <img  src="img/slider03.png" alt="b"/>
	               <img  src="img/slider04.png" alt="b"/>
	               <img  src="img/slider05.png" alt="b"/>
	           </div>
		</div>
		<div id="con_form2">
			<div><h3><span>공지사항</span></h3><a href ="#"><img src="img/puls_btn.png" id="plus_btn" /></a></div>
			<div>
				<div><span><a href ="#">공지사항내용</a></span></div>
				<div><span><a href ="#">공지사항내용</a></span></div>
				<div><span><a href ="#">공지사항내용</a></span></div>
				<div><span><a href ="#">공지사항내용</a></span></div>
				<div><span><a href ="#">공지사항내용</a></span></div>
				<div><span><a href ="#">공지사항내용</a></span></div>
			</div>
		</div>
		<div id="con_form3">
			<div><h3><span>수강후기</span></h3><a href ="#"><img src="img/puls_btn.png" id="plus_btn" /></a></div>
			<div>
				<div><span><a href ="#">수강후기내용</a></span></div>
				<div><span><a href ="#">수강후기내용</a></span></div>
				<div><span><a href ="#">수강후기내용</a></span></div>
				<div><span><a href ="#">수강후기내용</a></span></div>
				<div><span><a href ="#">수강후기내용</a></span></div>
				<div><span><a href ="#">수강후기내용</a></span></div>
			</div>
		</div>
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