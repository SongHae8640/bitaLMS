package com.bit.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import com.bit.model.UserDao;
import com.bit.model.UserDto;


@WebServlet("/login.bit")
public class LoginController extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String path = req.getRequestURI().replaceAll(req.getContextPath(), "");
		HttpSession session = req.getSession();
		
			if(path.equals("/login.bit")||path.equals("/index.bit")){
				//�α����������� ������ ���������� ���ٰ���
				if(session.getAttribute("userBean") == null){
					RequestDispatcher rd = req.getRequestDispatcher("index.jsp");
					rd.forward(req, resp);
				//�̹� �α����� �� �Ŀ��� �α׾ƿ��� �ؾ����� ��α����� �� �� �ִ�.
				}else{
					UserDto userBean = (UserDto) session.getAttribute("userBean");
					if(userBean.getBelong().equals("teacher")){
						resp.sendRedirect("main.tea");
					}else if(userBean.getBelong().equals("admin")){
						resp.sendRedirect("main.adm");
					}else{
						resp.sendRedirect("main.stu");
					}
				}
			}else if(path.equals("/logout.bit")){
				session.invalidate();
				resp.sendRedirect("login.bit");
			}
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String id = req.getParameter("id");
		String pw = req.getParameter("pw");
		System.out.println(id+":"+pw);
		
		UserDao dao = new UserDao();
		UserDto userBean = dao.login(id, pw);
		
		try{
		if(userBean.getBelong().equals("teacher")){
			//session
			HttpSession session = req.getSession();
			session.setAttribute("userBean", userBean);
//			session.setMaxInactiveInterval(5*60);	//���߿� �α��� ����ð��� ����Ҷ� ���, param�� ������ ��
			resp.sendRedirect("main.tea");
		}else if(userBean.getBelong().equals("admin")){
			HttpSession session = req.getSession();
			session.setAttribute("userBean", userBean);
			
			resp.sendRedirect("main.adm");
		}else if(userBean.getBelong().equals("student")){
			HttpSession session = req.getSession();
			session.setAttribute("userBean", userBean);
			
			resp.sendRedirect("main.stu");
		}
		}catch(java.lang.NullPointerException e){
			req.setAttribute("msg", "<script type=\"text/javascript\">alert('id&pw�� �ٽ� Ȯ���ϼ���');</script>");
			doGet(req, resp);
		}
	}
}