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
		System.out.println("LoginController(doGet) :: ");
		
			if(path.equals("/login.bit")||path.equals("/index.bit")){
				if(session.getAttribute("userBean") != null){ 
					session.invalidate();
					RequestDispatcher rd = req.getRequestDispatcher("index.jsp"); 
					rd.forward(req, resp); 
				}
				//로그인페이지는 세션이 없을때에만 접근가능
				if(session.getAttribute("userBean") == null){
					RequestDispatcher rd = req.getRequestDispatcher("index.jsp");
					rd.forward(req, resp);
				//이미 로그인을 한 후에는 로그아웃을 해야지만 재로그인을 할 수 있다.
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
		
		UserDao dao = new UserDao();
		UserDto userBean = dao.login(id, pw);
		System.out.println("값확인"+dao.login(id, pw));
		System.out.println("LoginController(doPost) :: userBean="+userBean.toString());
		try{
		if(userBean.getBelong().equals("teacher")){
			//session
			HttpSession session = req.getSession();
			session.setAttribute("userBean", userBean);
//			session.setMaxInactiveInterval(5*60);	//나중에 로그인 만료시간을 사용할때 사용, param의 단위는 초
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
			req.setAttribute("msg", "<script type=\"text/javascript\">alert('id&pw를 다시 확인하세요');</script>");

			doGet(req, resp);
		}
	}
}