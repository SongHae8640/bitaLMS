package com.bit.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;


@WebServlet("/upload.bit")
public class UploadController extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		RequestDispatcher rd = req.getRequestDispatcher("student/assignment_S_detail.jsp");
		rd.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		int maxsize=1024*1024*10;//10mb;
		String path="C:\\java\\BITA_LMS\\WebContent\\upload";
		DefaultFileRenamePolicy dfrp = null;
		dfrp=new DefaultFileRenamePolicy();
		
		MultipartRequest mr = null;
		mr=new MultipartRequest(req, path,maxsize,"utf-8",dfrp);
		
		System.out.println(path);
		System.out.println(mr.getParameter("id"));
		String origin=mr.getOriginalFileName("myfile");
		System.out.println(origin);
		String rename=mr.getFilesystemName("myfile");
		
		
		req.setAttribute("fname", rename);
		
//		RequestDispatcher rd = req.getRequestDispatcher("down.jsp");
//		rd.forward(req, resp);
	}
}




















