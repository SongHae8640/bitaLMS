<%@page import="com.bit.model.ApplyDto"%>
<%@page import="com.bit.model.AdminDao"%>
<%@page import="com.bit.model.UserDao"%>
<%@page import="com.bit.model.UserDto"%>
<%@page import="com.bit.model.StudentDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	AdminDao dao_a=new AdminDao();
	UserDao dao = new UserDao();
	UserDto userBean = (UserDto) session.getAttribute("userBean");
	ApplyDto bean_a= (ApplyDto)session.getAttribute("bean_a");
	
	String point;
	int belong=-1;
	int applyList = -1;
	int totalQnaLNum = -1;
	
	System.out.println();	
	
	point = "비트교육센터 안양지점";
	belong = dao.getBelong(userBean.getUserId()); 
	applyList=dao_a.getRegisterNum(userBean.getUserId());	
	totalQnaLNum=dao_a.getQnaLNum(userBean.getUserId());
	
	
	
%>
