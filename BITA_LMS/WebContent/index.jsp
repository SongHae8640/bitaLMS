<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link type="text/css" rel="stylesheet" href="../css/frame.css" />
<style type="text/css">
	
</style>
<script type="text/javascript" src="../js/jquery-1.12.4.js"></script>
<script type="text/javascript">
	$(document).ready(function(){
		$('button[type=button]').click(function(){
			window.location.href='join.jsp';
		});
	});
</script>
</head>
<body>
	<div>
		<div id="header">
			<h1>비트교육센터</h1>
		</div>
		<div id="content">
			<h2>로그인페이지</h2>
			<form action="login.bit" method="post">
				<div>
					<lable for="id">id</lable>
					<input type="text" name="id" id="id" />
				</div>
				<div>
					<lable for="pw">pw</lable>
					<input type="password" name="pw" id="pw" />
				</div>
				<div>
					<button type="submit">로그인</button>
					<button type="reset">취소</button>
					<button type="button">회원가입</button>
				</div>
			</form>
		</div>
		<div id="footer">
			Copyright &copy; 비트캠프 All rights reserved.
		</div>
	</div>
	<%
	//ui를 깨지지 않게 하려면 항상 body 닫기 전에 넣는 것이 좋다. 로딩하다가 출력되기 때문
	Object obj = request.getAttribute("errmsg");
	if(obj!=null)out.println(obj);
	%>
</body>
</html>