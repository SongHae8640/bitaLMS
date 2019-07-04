package com.bit.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bit.model.AdminDao;
import com.bit.model.AttendanceDto;
import com.bit.model.LectureDto;
import com.bit.model.RegisterDto;
import com.bit.model.TeacherDao;
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
				AdminDao dao = new AdminDao();
				
				if (path.equals("/main.adm")) {
					rd = req.getRequestDispatcher("admin/main_A.jsp");
					
				} else if (path.equals("/manage_lec.adm")) {
					rd = req.getRequestDispatcher("admin/manage_lec.jsp");
				} else if (path.equals("/manage_lec_detail.adm")) {
					rd = req.getRequestDispatcher("admin/manage_lec_detail.jsp");
				} else if (path.equals("/manage_stu.adm")) {
					rd = req.getRequestDispatcher("admin/manage_stu.jsp");
				} else if (path.equals("/manage_tea.adm")) {
					rd = req.getRequestDispatcher("admin/manage_tea.jsp");
				} else if (path.equals("/qna.adm")) {
					rd = req.getRequestDispatcher("admin/qna_A.jsp");
				} else if (path.equals("/qna_detail.adm")) {
					rd = req.getRequestDispatcher("admin/qna_A_detail.jsp");
				} else if (path.equals("/register.adm")) {
					ArrayList<LectureDto> LectureList = dao.getLecture();
					//Ŀ�ؼ� ���� �Ҵ� (�������ϱ�)
					dao = new AdminDao();
					System.out.println(userBean.getLecture_id());
					ArrayList<RegisterDto> RegisterList = dao.getRegister(1);
					//��Ʈ����Ʈ�� �����ϰ� jsp���������� get���� �ҷ�����
					req.setAttribute("RegisterList",RegisterList);
					req.setAttribute("LectureList",LectureList);
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
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		RequestDispatcher rd = null;
		AdminDao dao = new AdminDao();
		
		String path = req.getRequestURI().replaceAll(req.getContextPath(), "");
		System.out.println("teacherController :: path = " + path);
		
		//���� ����
		HttpSession session = req.getSession();
		UserDto userBean = (UserDto) session.getAttribute("userBean");
		
		if (path.equals("/register_detail.adm")) {
			rd = req.getRequestDispatcher("admin/register_detail.jsp");
		} else if(path.equals("/register.adm")){
			String param = (String) req.getParameter("param");
			System.out.println(param+"�Ѿ�°�");
			ArrayList<LectureDto> LectureList = dao.getLecture();
			//Ŀ�ؼ� ���� �Ҵ� (�������ϱ�)
			dao = new AdminDao();
			System.out.println(userBean.getLecture_id());
//			ArrayList<RegisterDto> RegisterList = dao.getRegister(lecidx);
			//��Ʈ����Ʈ�� �����ϰ� jsp���������� get���� �ҷ�����
//			req.setAttribute("RegisterList",RegisterList);
			req.setAttribute("LectureList",LectureList);
		}
	}

}
