<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<style type="text/css">
body {
	top-margin:0; 
	bottom-margin:0; 
	left-margin:0; 
	right-margin:0;
	width: 300px;
	text-align: center;
}  
#content{
	width:300px;
	margin: 0 auto;
}
#id{    
	width:120px;
}
#confirm_btn{
	margin-left: 10px;
}
h3{
	padding:5px;
	margin: 0 auto;
}
hr{
	size: 1px;
	width:250px;
	float: left;
	position: relative; 
	left:25px;
	margin-top: 0px;
}
#msg{
	color: rgb(250,0,0);
}
</style>
<script type="text/javascript" src="js/jquery-1.12.4.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		
		$("#confirm_btn").click(function(){
			
			if($("#id").val() == ""){
// 		        $("#content").append("<span>아이디를 입력해주세요</span>");
		        $("#id").focus();
		        return; 
		        location.reload();
		      }
			$("#target").submit();
		});
		$("#use_btn").click(function(){
			$("#insert_id").submit();
			window.close();
		}); 
		$("#cancle_btn").click(function(){
			window.close();
		});
	}); 
</script>  
</head>
<body>
	<div id="content">
	<div>
		<h3><span>ID 중복확인</span></h3>
		<hr> 
	</div>
	<form id="target" action="idcheck.home" method="post">
		<div>
		<%
	String id = (String)request.getAttribute("id");
	if(id==null){
	%>
		<form id="insert_id" method="get" action="join.home">
		<input type="text" name="id" id="id" placeholder="ID를 입력해주세요"/><button type="button" id="confirm_btn">중복확인</button>
		</form>
	<%
	}else{
	%>	
		<form id="insert_id" method="get" action="join.home">
		<input type="text" name="id" id="id" value="<%=id%>"/><button type="button" id="confirm_btn">중복확인</button>
		</form>
	<%
	}
	%>
		</div>	
	</form>
	<div>
	<%
	String msg = (String)request.getAttribute("msg");
	if(msg==null){
	%>
		<span id="msg"></span>
	<%
	}else{
	%>
		<span id="msg"><%=msg %></span>
	<%
	} 
	%>
	</div>
	<br /><br /><br />
	<div>
		<button type="button" id="cancle_btn">취소</button>
		<button type="button" id="use_btn">사용하기</button>
	</div>
	</div>
</body>
</html>