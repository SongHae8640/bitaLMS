<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
		<div id="header">
			<h1>비트교육센터</h1>
		</div>
		<div id="menu">
		<%
			String root = request.getContextPath();
		%>
			<ul>
				<li><a href="<%=root %>/stumanage.bit">출결관리</a></li>
				<li><a href="<%=root %>/qna.bit">1:1문의</a></li>
			</ul>
		</div>
		<div id="content">
		<h1>강사 메인 페이지</h1>
		</div>
		<div id="footer">
			Copyright &copy; 비트캠프 All rights reserved.
		</div>
</body>
</html>