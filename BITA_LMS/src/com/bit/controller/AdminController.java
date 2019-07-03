package com.bit.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bit.model.UserDto;

@WebServlet("*.adm")
public class AdminController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		RequestDispatcher rd = null;

		String path = req.getRequestURI().replaceAll(req.getContextPath(), "");
		System.out.println("teacherController :: path = " + path);
		
		//���� ����
		HttpSession session = req.getSession();
		UserDto userBean = (UserDto) session.getAttribute("userBean");
				
		try {
			if (userBean.getBelong().equals("admin")) {
				if (path.equals("/main.adm")) {
					rd = req.getRequestDispatcher("admin/main_A.jsp");
				} else if (path.equals("/manage_lec.adm")) {
					rd = req.getRequestDispatcher("admin/manage_lec.jsp");
				} else if (path.equals("/manage_stu.adm")) {
					rd = req.getRequestDispatcher("admin/manage_stu.jsp");
				} else if (path.equals("/manage_tea.adm")) {
					rd = req.getRequestDispatcher("admin/manage_tea.jsp");
				} else if (path.equals("/qna.adm")) {
					rd = req.getRequestDispatcher("admin/qna_A.jsp");
				} else if (path.equals("/register.adm")) {
					rd = req.getRequestDispatcher("admin/register.jsp");
					
				} else if (path.equals("/register_detail.adm")) {
					rd = req.getRequestDispatcher("admin/register_detail.jsp");
				} else {
					System.out.println("���������ʴ�������");
				}
			}else {
				//teacher�� student�������� �����Ϸ��� �ϸ� �� ��������
				req.getRequestDispatcher("login.bit");
			}
			rd.forward(req, resp);
		} catch (java.lang.NullPointerException e) {
			resp.sendRedirect("login.bit");
		}

	}

}
