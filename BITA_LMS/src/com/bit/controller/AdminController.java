package com.bit.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("*.adm")
public class AdminController extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		RequestDispatcher rd= null;
		
		String path = req.getRequestURI().replaceAll(req.getContextPath(), "");
		System.out.println("teacherController :: path = "+path);
		try {
			if(path.equals("/main.adm")){
				rd = req.getRequestDispatcher("admin/main_A.jsp");
				rd.forward(req, resp);
			} else if (path.equals("/manage_lec.adm")) {
				rd = req.getRequestDispatcher("admin/manage_lec.jsp");
				rd.forward(req, resp);
			} else if (path.equals("/manage_stu.adm")) {
				rd= req.getRequestDispatcher("admin/manage_stu.jsp");
				rd.forward(req, resp);
			} else if (path.equals("/manage_tea.adm")) {
				rd= req.getRequestDispatcher("admin/manage_tea.jsp");
				rd.forward(req, resp);
			} else if (path.equals("/qna.adm")) {
				rd= req.getRequestDispatcher("admin/qna_A.jsp");
				rd.forward(req, resp);
			}else if (path.equals("/register.adm")) {
				rd= req.getRequestDispatcher("admin/register.jsp");
				rd.forward(req, resp);
			} else {
				System.out.println("¿À·ù");
			}
		} catch (java.lang.NullPointerException e) {
			resp.sendRedirect("login.bit");
		}
		
	}
	
}
