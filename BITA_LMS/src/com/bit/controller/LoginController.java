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


@WebServlet("*.bit")
public class LoginController extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String path = req.getRequestURI().replaceAll(req.getContextPath(), "");
		HttpSession session = req.getSession();
		System.out.println("LoginController(doGet) :: ");
		
			if(path.equals("/login.bit")||path.equals("/index.bit")){
				//로그인페이지는 세션이 없을때에만 접근가능
				if(session.getAttribute("userBean") != null){
					session.invalidate();
					RequestDispatcher rd = req.getRequestDispatcher("index.jsp");
					rd.forward(req, resp);
				}
				if(session.getAttribute("userBean") == null){
					RequestDispatcher rd = req.getRequestDispatcher("index.jsp");
					rd.forward(req, resp);
				//�씠誘� 濡쒓렇�씤�쓣 �븳 �썑�뿉�뒗 濡쒓렇�븘�썐�쓣 �빐�빞吏�留� �옱濡쒓렇�씤�쓣 �븷 �닔 �엳�떎.
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
		
		System.out.println("LoginController(doPost) :: userBean="+userBean.toString());
		try{
		if(userBean.getBelong().equals("teacher")){
			//session
			HttpSession session = req.getSession();
			session.setAttribute("userBean", userBean);
//			session.setMaxInactiveInterval(5*60);	//�굹以묒뿉 濡쒓렇�씤 留뚮즺�떆媛꾩쓣 �궗�슜�븷�븣 �궗�슜, param�쓽 �떒�쐞�뒗 珥�
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
			req.setAttribute("msg", "<script type=\"text/javascript\">alert('id&pw瑜� �떎�떆 �솗�씤�븯�꽭�슂');</script>");
			doGet(req, resp);
		}
	}
}