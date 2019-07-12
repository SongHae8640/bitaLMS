<%@page import="com.bit.model.QnaLDto"%>
<%@page import="com.bit.model.LectureDto"%>
<%@page import="com.bit.model.AdminDao"%>
<%@page import="com.bit.model.TeacherDto"%>
<%@page import="com.bit.model.TeacherDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	TeacherDao dao_t = new TeacherDao();
	AdminDao dao_a=new AdminDao();
	TeacherDto bean_t = (TeacherDto) session.getAttribute("bean_t");
	LectureDto bean_l=(LectureDto)session.getAttribute("bean_l");
	QnaLDto bean_q=(QnaLDto)session.getAttribute("bean_q");

	
	int lecName=-1;
	int totalyDays = -1;
	int totalQnaLNum =-1;
	int getSubmission = -1;
	int progessDays = -1; //수업진행현황
	
	System.out.println(bean_t.toString());
	System.out.println(bean_l.toString());
	
	lecName=dao_t.getLecture(bean_t.getTeacherId());
	totalyDays = dao_t.getTotalDays(bean_l.getLectureID());
	totalQnaLNum=dao_a.getQnaLNum(bean_t.getTeacherId());
	getSubmission=dao_t.getSubmissionNum(bean_l.getLectureID());
	progessDays = dao_t.getProgressDays(bean_l.getLectureID());
	
	
%>