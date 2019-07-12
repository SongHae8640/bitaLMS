<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link href="https://fonts.googleapis.com/css?family=Noto+Sans+KR:100,300,400,500,700,900&display=swap&subset=korean" rel="stylesheet">
<style type="text/css">
.circle{
	width:100px;
	height:100px;
	border-radius:50%;
	background-color:lightblue;
	border:none;
	font-size:16pt;
	font-weight:700;
	color:white;
	text-align:center;
	line-height:100px;
}
</style>
<script type="text/javascript" src="js/jquery-1.12.4.js"></script>
<script type="text/javascript">
	
	$(document).ready(function(){
		var id = $('#name').text();
		
		$('#checkin').click(function() {
			//var id = $('#name').text();
			$.ajax({ 
				url: "callAttendance.test", 
				dataType: "json", 
				data: {id:id},
				type: "post",
				success: function(data){ 
					var obj = data;
					
				},error : function(){
	                alert("통신실패");
	            }
			});
		});
		
		if($('#checkin').text()=='입실전'){
			$('#checkin').
		}
	});
	
	
	
	
</script>

</head>
<body>
	<form>
					<table border="1">
						<tr>
							<td rowspan="4" class="circle">입실</td>
							<td id="name">stu1님</td>
						</tr>
						<tr>
							<td>환영합니다!</td>
						</tr>
						<tr>
							<td>입실:/퇴실:</td>
						</tr>
						<tr>
							<td>
							<button type="button" id="checkin">입실</button>
							<button type="button" id="checkout">퇴실</button>
							</td>
						</tr>
						<tr>
							<td colspan="2"><label for="branch">강좌명 :</label> <span>JAVA 과정</span></td>
						</tr>
						<tr>
							<td colspan="2"><label for="branch">강좌기간 :</label> <span>0000-00-00~0000-00-00</span></td>
						</tr>

					</table>
				</form>
</body>
</html>