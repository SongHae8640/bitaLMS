<<<<<<< HEAD
<<<<<<< HEAD
=======

>>>>>>> song2
=======

>>>>>>> d0180b1a7f4bdedaefd73083ad7f4d199bb34232
<%@page import="com.bit.model.UserDto"%>
<%@page import="com.bit.model.StudentDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	StudentDao dao = new StudentDao();
	UserDto userBean = (UserDto) session.getAttribute("userBean");
	int attendanceDays=-1;
	int totalyDays = -1;
	int newAnswerNum = -1;
	int totalQnaLNum = -1;
	int progressDays = -1;
	
	
	attendanceDays = dao.getAttendanceDays(userBean.getUserId());
	totalyDays = dao.getTotalDays(userBean.getLectureId()); 
	newAnswerNum = dao.getNewQnaLAnswerNum(userBean.getUserId()); 
	totalQnaLNum = dao.getTotalQnaLNum(userBean.getUserId()); 
	progressDays = dao.getProgressDays(userBean.getLectureId());
	
%>

[
{"attendanceDays":<%=attendanceDays %>,"totalyDays":<%=totalyDays %>,"newAnswerNum":<%=newAnswerNum %>,"totalQnaLNum":<%=totalQnaLNum %>,"progressDays":<%=progressDays %>}
]

