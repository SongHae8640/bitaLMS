<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="upload.bit" method="post" enctype="multipart/form-data" onsubmit="return formCheck();">

		title : <input type="text" name="title" /><br/>

		writer : <input type="text" name="writer" /><br/>

		content : <textarea rows="10" cols="20" name="content"></textarea><br/>

		file : <input type="file" name="filename"><br/>

		<input type="submit"/>

	</form> 
	
</body>
</html>