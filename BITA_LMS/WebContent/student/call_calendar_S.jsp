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


		$(document).ready(function() {

			//fullCalendar 가져오기
			$('#calendar').fullCalendar({
				defaultDate: "2019-07-10",
				lang: "ko",
				editable: true,
				eventLimit: true,
				displayEventTime: false,
				disableDragging: false,
				events: {
					url: "callCalendar.stu",
					error: function() {
						console.log("fullcalendar 가져오기 실패")
					},
					success: function(calData) {
						console.log(calData);
					}
				},


				//클릭 했을때 이벤트
				eventClick: function(calEvent, jsEvent, view) {
					calendarEvent = calEvent;
					console.log(calEvent);
					var htmlsContents = "";
					htmlsContents += "<div><a>강좌이름</a> <div> " + calEvent.lectureName + "</div><br>";
					htmlsContents += "<div><div> 날짜 </div><div> " + calEvent.start.format().substring(0, 10) + " ~ " + calEvent.end.format().substring(0, 10) + " </div> </div><br>";
					htmlsContents += "<div><div> 제목 </div><div> " + calEvent.title + " </div></div><br>";
					htmlsContents += "<div><div> 내용 </div> <div>" + calEvent.content + " </div></div>";
					openPopup("일정 상세", htmlsContents, 400);
					return false;

				},
				viewRender :function(view, element){
					console.log(view, element);
				}

			});



			//추가하기 버튼
			addCalBtn = document.getElementById('add_calendar_Btn');
			addCalBtn.addEventListener('click', function(event) {
				addCalendar();
			});



		});

	

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







	<div id='calendar'></div>
	<div id='day_calender'>
		<!--여기에 당일 일정 리스트가 내용이 들어감  -->
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