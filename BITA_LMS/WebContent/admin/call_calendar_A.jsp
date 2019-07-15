<%@page import="com.bit.model.LectureDto"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
	<meta charset='utf-8' />
	<link href='css/fullcalendar.min.css' rel='stylesheet' />
	<link href='css/fullcalendar.print.min.css' rel='stylesheet' media='print' />
	<script src='js/moment.min.js'></script>
	<script src='js/jquery-1.12.4.js'></script>
	<script src='js/jquery.bpopup.min.js'></script>
	<script src='js/fullcalendar.js'></script>
	<script src='js/fullcalendar_ko.js'></script>

	<script>
		var addCalBtn;
		var saveCalBtn;

		//호출 시점 날짜
		var date = new Date();
		var yearMonthDay = date.getFullYear() + "-" + leadingZeros(date.getMonth() + 1, 2) + "-" + leadingZeros(date.getDate(), 2);

		var calendarEvent;
		var callCalendarUrl = "callCalendar.adm?idx=0";
		
		var test;


		$(document).ready(function() {

			//fullCalendar 가져오기
			$('#calendar').fullCalendar({
				defaultDate: "2019-07-10",
				lang: "ko",
				editable: true,
				eventLimit: true,
				displayEventTime: false,
				events: {
					url: callCalendarUrl,
					error: function() {
						console.log("fullcalendar 가져오기 실패")
					},
					success: function(calData) {
						console.log("fullcalendar 가져오기 성공")
						console.log(calData);
					}
				},

				//드래그 했을때 이벤트
				eventDrop: function(event, delta, revertFunc) {
					calendarEvent = event;
					$.ajax({
						type: 'POST',
						url: "calendar_update.adm",
						data: {
							calendarId: event.id + "",
							title: event.title,
							lectureId: event.lectureId + "",
							lectureName: event.lectureName,
							startDate: event.start.format() + "",
							endDate: event.end.format() + "",
							content: event.content
						},
						cache: false,
						async: false,
						error: function() {
							alert('수정 내용이 정상적으로 저장되지 않았습니다.');
						},
						success: function() {
							$('#calendar').fullCalendar('refetchEvents');
						}
					})
				},

				//클릭 했을때 이벤트
				eventClick: function(calEvent, jsEvent, view) {
					calendarEvent = calEvent;
					console.log(calEvent);
					console.log('일정 상세')
					var htmlsContents = "";
					htmlsContents += "<div><a>강좌번호</a> <div> " + calEvent.lectureId + "</div><br>";
					htmlsContents += "<div><div> 날짜 </div><div> " + calEvent.start.format().substring(0, 10) + " ~ " + calEvent.end.format().substring(0, 10) + " </div> </div><br>";
					htmlsContents += "<div><div> 제목 </div><div> " + calEvent.title + " </div></div><br>";
					htmlsContents += "<div><div> 내용 </div> <div>" + calEvent.content + " </div></div>";
					htmlsContents += "<div><button id='edite_calendar_Btn' onclick='editeCalendar()'>수정</button><button id='delete_calendar_Btn' onclick='deleteCalendar()'>삭제</button></div>";
					openPopup("일정 추가", htmlsContents, 400);

					return false;

				}

			});



			//추가하기 버튼
			addCalBtn = document.getElementById('add_calendar_Btn');
			addCalBtn.addEventListener('click', function(event) {
				addCalendar();
			});


		});
		
		
		//콤보 박스에서 lecture를 변경하였을때 
		function changeLecture() {
			var select = $("select[name=lecture_name]");
			var lectureId = $(select[0]).children("option:selected")[0].value;
			var callCalendarUrl = "callCalendar.adm?idx="+lectureId;
			console.log(callCalendarUrl);
			$('#calendar').fullCalendar('refetchEvents');
	    }

		function editeCalendar() {
			closeMessage('winAlert');
			var calEvent = calendarEvent;
			console.log(calEvent);
			var htmlsContents = "";
			htmlsContents += "<div><a>강좌번호</a><input type='text' id='calendar_lecture_id' value='" + calEvent.lectureId + "'></div><br>";
			
			htmlsContents += "<div><div>날짜</div><div><input type='text' id='calendar_start_date' value='" + calEvent.start.format().substring(0, 10) + "'> ~ <input type='text' id='calendar_end_date' value='" + calEvent.end.format().substring(0, 10) + "'></div></div><br>";
			htmlsContents += "<div><div>제목</div><div><input type='text' id='calendar_title' value='" + calEvent.title + "'></div></div><br>";
			htmlsContents += "<div><div>내용</div><div><input type='text' id='calendar_content' value='" + calEvent.content + "'></div></div>";
			
			htmlsContents += "<div><button id='update_calendar_Btn' onclick='updateCalendar()'>저장</button><button  onclick='closeMessage(\"winAlert\")'>취소</button></div>";
			openPopup("일정 수정", htmlsContents, 400);

			return false;

		}


		//삭제 버튼과 연동 시킬것
		function deleteCalendar() {
			var calEvent = calendarEvent;
			console.log(calEvent);
			//확인 메세지
			if (!confirm("일정 : " + calEvent.title + "을 삭제하시겠습니까?")) {
				return false;
			}

			//내용을 다 입력 했을때 통신 시작
			$.ajax({
				type: 'POST',
				url: "calendar_delete.adm",
				data: {
					calendarId: calEvent.id + "",
				},
				cache: false,
				async: false,
				error: function() {
					alert('삭제되지 않았습니다.');
				},
				success: function() {
					closeMessage('winAlert');
					$('#calendar').fullCalendar('refetchEvents');
				}
			});

			return false;

		}


		function addCalendar() {
			console.log('일정 추가')
			
			var htmlsContents = "";
			htmlsContents += "<div><a>강좌이름</a><div>"+$('#lecture_list').html()+"</div><br>";
			htmlsContents += "<div><div>날짜</div><div><input type='text' id='calendar_start_date' value=''>-<input type='text' id='calendar_end_date' value=''></div></div><br>";
			htmlsContents += "<div><div>제목</div><div><input type='text' id='calendar_title' value=''></div></div><br>";
			htmlsContents += "<div><div>내용</div><div><input type='text' id='calendar_content' value=''></div></div>";
			htmlsContents += "<div><button id='save_calendar_Btn' onclick=\"saveCalendar()\">저장하기</button></div>"
			openPopup("일정 추가", htmlsContents, 400);

			return false;
		}

		function openPopup(subject, contents, widths) {
			$('#alert_subject').html(subject);
			$('#alert_contents').html(contents);
			openMessage('winAlert', widths);
		}

		function openMessage(IDS, widths) {
			$('#' + IDS).css('width', widths + "px");
			$('#' + IDS).bPopup();
		}

		function closeMessage(IDS) {
			$('#' + IDS).bPopup().close();
		}

		function saveCalendar() {
			console.log("일정 저장하기");
			
			saveCalBtn = document.getElementById('save_calendar_Btn');
			//입력값 검사 
			
			var select = $("select[name=lecture_name]")[1];
			console.log(select);
			var lectureId = $(select[0]).children("option:selected")[0].value;
			var startDate = $('#calendar_start_date').val();
			var endDate = $('#calendar_end_date').val();
			var title = $('#calendar_title').val();
			var content = $('#calendar_content').val();

			if (lectureId = "0") {
				alert('강좌명을 입력해 주세요.');
				return false;
			}
			if (!startDate) {
				alert('시작 날짜 입력해 주세요.');
				return false;
			}
			if (!endDate) {
				alert('종료 날짜 입력해 주세요.');
				return false;
			}
			if (!title) {
				alert('일정명 입력해 주세요.');
				return false;
			}
			if (!content) {
				alert('일정 내용 입력해 주세요.');
				return false;
			}

			//내용을 다 입력 했을때 통신 시작
			$.ajax({
				type: 'POST',
				url: "calendar_insert.adm",
				data: {
					lectureId: lectureId,
					startDate: startDate + "",
					endDate: endDate + "",
					title: title,
					content: content
				},
				cache: false,
				async: false,
				error: function() {
					alert('저장되지 않았습니다.');
				},
				success: function() {
					closeMessage('winAlert');
					console.log('정상 저장되었습니다.');
					$('#calendar').fullCalendar('refetchEvents');
				}
			});


		}

		function leadingZeros(n, digits) {
			var zero = '';
			n = n.toString();

			if (n.length < digits) {
				for (var i = 0; i < digits - n.length; i++)
					zero += '0';
			}
			return zero + n;
		}

	</script>
	<style>
		body {
			margin: 40px 10px;
			padding: 0;
			font-family: "Lucida Grande", Helvetica, Arial, Verdana, sans-serif;
			font-size: 14px;
		}

		#calendar {
			max-width: 900px;
			margin: 0 auto;
		}

	</style>
</head>

<body>





	
	<div id="lecture_list">
		<select name="lecture_name" onchange = "changeLecture()">
			<option value="0" selected="selected">전체</option>
			<%
			ArrayList<LectureDto> lectureList = (ArrayList<LectureDto>)session.getAttribute("arrangeLectureList");
				if(lectureList !=null){
					for(LectureDto bean : lectureList){
						if(bean.getLectureId()>0){
			%>
			<option value="<%=bean.getLectureId()%>"><%=bean.getName()%></option>
			<%
						}
					}
				}
			%>
		</select>
	</div>
	
	<div id='calendar'></div>
	
	<div id='day_calender'>
		
		<!--여기에 당일 일정 리스트가 내용이 들어감  -->
		<button id="add_calendar_Btn">일정 등록</button>
	</div>

	<div class="box box-success" id="winAlert" style="width: 500px; background-color: white; display: none">
		<div class="box-header with-border">
			<h3 class="box-title" id="alert_subject" style="background-color: white"></h3>
		</div>
		<div class="box-body" id="alert_contents" style="font-size: 15px; background-color: white">

		</div>
	</div>


</body>

</html>
