<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="js/jquery-1.12.4.js"></script>
<script type="text/javascript">
	var btn,file,xhr,formData;
	$(document).ready(function(){
		$.ajax({
			url : "callAttendance.stu", // test.jsp 에서 받아옴
            dataType :"json", // 데이터타입을 json 으로 받아옴
            success : function(data) {
                console.log(data.status);
            },
            error : function(e) {
                console.log(e.responseText);
            }
		});
		
		$("#checkin").click(function(){
			$.ajax({
				url:'callAttendance.stu',
				method:'post',
				data:formData,
				contentType:false,
				processData:false,
				success:function(){
					alert('업로드 성공');
				},
	            error : function(e) {
	                console.log(e.responseText);
	            }
			});
		});
		
		$("#checkout").click(function(){
			$.ajax({
				url:'callAttendance.stu',
				method:'post',
				data:formData,
				contentType:false,
				processData:false,
				success:function(){
					alert('업로드 성공');
				}
			});
		});
	});
</script>
</head>
<body>
	<form>
					<table border="1">
						<tr>
							<td rowspan="3"><img src=""/></td>
							<td id="name"></td>
							<td rowspan="3">입실횟수</td>
							<td rowspan="3">지각</td>
							<td rowspan="3">조퇴</td>
							<td rowspan="3">외출</td>
							<td rowspan="3">결성</td>
						</tr>
						<tr>
							<td>시간</td>
						</tr>
						<tr>
							<td>
							<button id="checkin">입실</button>
							<button id="checkout">퇴실</button>
							</td>
						</tr>
						<tr>
							<td colspan="2"><label for="branch">강좌명 :</label> <span>JAVA 과정</span></td>
							<td rowspan="2" colspan="5"><label for="today_q">문의현황 </label> <span> ?? / 30</span></td>
						</tr>
						<tr>
							<td colspan="2"><label for="branch">강좌기간 :</label> <span>0000-00-00~0000-00-00</span></td>
						</tr>

					</table>
				</form>
</body>
</html>