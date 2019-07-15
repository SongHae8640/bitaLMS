<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link href="https://fonts.googleapis.com/css?family=Noto+Sans+KR:100,300,400,500,700,900&display=swap&subset=korean" rel="stylesheet">
<style type="text/css">
body table{
	width:400px;
	border:none;
}
body table td{
	border:none;
	height:20px;
}
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
	var id = $('#name').text();
	
	$(document).ready(function(){
		ajaxCall();
		
		$('#checkbtn').click(ajaxBtnCall);
	});
	
	var ajaxCall = function() {
		//var id = $('#name').text();
		$.when($.ajax({ 
			url: "callAttendance.stu", 
			dataType: "json", 
			data: {id:id},
			type: "post",
			success: function(data){
				if(data.status!='null'){
					$("#status").text(data.status);
				}else{
					$("#status").text('입실전');
				}
				$("#name").text(data.name+"님");
				if(data.checkinTime!='null'){
					$("#checkTime").text("입실:"+data.checkinTime+" / "+"퇴실:");	
					if(data.checkoutTime!='null'){
						var temp = $("#checkTime").text()
						$("#checkTime").text(temp+data.checkoutTime);	
					}
				}
				$("label[for='lecName']").next().html('<span>'+data.lecName+' 과정</span>');
				$("label[for='lecPeriod']").next().html('<span>'+data.startDate+' ~ '+data.endDate+'</span>')
			},error : function(){
                alert("통신실패");
            }
		})).done(function() {
			if($('#status').text()=="입실전"){
				$('#checkbtn').text('입실');
			}else if($('#status').text()=="입실"){
				$('#checkbtn').text('퇴실');
			}else if($('#status').text()=="퇴실"){
				$('#checkbtn').hide();
			}
		});
	}
	
	var ajaxBtnCall = function() {
		//var id = $('#name').text();
		var btn = $('#checkbtn').text();
		$.when($.ajax({
			url: "callAttendanceBtn.stu", 
			dataType: "json", 
			data: {btn:btn},
			type: "post",
			success: function(data){
				if(data.status!='null'){
					$("#status").text(data.status);
					if(data.checkinTime!='null'){
						$("#checkTime").text("입실:"+data.checkinTime+" / "+"퇴실:");	
						if(data.checkoutTime!='null'){
							var temp = $("#checkTime").text()
							$("#checkTime").text(temp+data.checkoutTime);	
						}
					}
				}
			},error : function(){
                alert("통신실패");
            }
		})).done(function() {
			if($('#status').text()=="입실"){
				$('#checkbtn').text('퇴실');
			}else if($('#status').text()=="퇴실"){
				$('#checkbtn').hide();
			}
		});
	}
	
</script>

</head>
<body>
	<form>
					<table border="1">
						<tr>
							<td rowspan="4" class="circle" id="status">입실전</td>
							<td id="name"></td>
						</tr>
						<tr>
							<td>환영합니다!</td>
						</tr>
						<tr>
							<td id="checkTime">입실:/퇴실:</td>
						</tr>
						<tr>
							<td>
							<button type="button" id="checkbtn">입실</button>
							</td>
						</tr>
						<tr>
							<td colspan="2"><label for="lecName">강좌명 : </label><span></span></td>
						</tr>
						<tr>
							<td colspan="2"><label for="lecPeriod">강좌기간 :</label> <span></span></td>
						</tr>

					</table>
				</form>
</body>
</html>