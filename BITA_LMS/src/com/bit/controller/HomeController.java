package com.bit.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bit.model.ApplyDto;
import com.bit.model.HomeDao;
import com.bit.model.UserDao;
import com.bit.model.UserDto;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

@WebServlet("*.home")
public class HomeController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		RequestDispatcher rd = null;
		
		String path = req.getRequestURI().replaceAll(req.getContextPath(), "");
		System.out.println("HomeController(doGet) :: path = " + path);
		
		// 세션 저장
		HttpSession session = req.getSession();
	
		if(path.equals("/main.home")){
			rd = req.getRequestDispatcher("home/main_H.jsp");
		}else if(path.equals("/join.home")){
			rd = req.getRequestDispatcher("home/join_H.jsp");
		}else if(path.equals("/apply.home")){
			rd = req.getRequestDispatcher("home/apply_H.jsp");	
		}
		
		rd.forward(req, resp);
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		RequestDispatcher rd = null;
		HttpSession session = req.getSession();
		int result = 0;
		
		String path = req.getRequestURI().replaceAll(req.getContextPath(), "");
		System.out.println("HomeController(doPost) :: path = " + path);
		
		if(path.equals("/login.home")){
			
			String id = req.getParameter("id");
			String pw = req.getParameter("pw");
			HomeDao dao = new HomeDao();
			UserDto userBean = dao.login(id, pw);
			String name = userBean.getName();
			String message = "";
			
			try{
				System.out.println(userBean.getUserId());
				if(userBean.getUserId().equals(id) && userBean.getPassword().equals(pw) ){//로그인성공
					//session
					session.setAttribute("userBean", userBean);
					message = name + "님 환영합니다.";
	//				session.setMaxInactiveInterval(5*60);	//나중에 로그인 만료시간을 사용할때 사용, param의 단위는 초
					
				}else{//로그인실패
				req.setAttribute("errmsg", "<script type=\"text/javascript\">alert('id&pw를 다시 확인하세요');</script>");
				}
				//데이터 저장 화면에 출력해줘야해
				  req.setAttribute("message", message);
				  System.out.println(message);
		          rd = req.getRequestDispatcher("/home/login_result_H.jsp");
		          
				
			}catch(java.lang.NullPointerException e){
				req.setAttribute("errmsg", "<script type=\"text/javascript\">alert('id&pw를 다시 확인하세요');</script>");
			}
			rd.forward(req, resp);
		}else if(path.equals("/logout.home")){
			session.invalidate();//로그아웃-invalidate는 세션자체가 갱신됨
			 rd = req.getRequestDispatcher("/home/logout_result_H.jsp");
			 rd.forward(req, resp); 
		}else if(path.equals("/join.home")){
			//getParam으로 회원가입에 필요한 내용 체크 및 저장
			//전부 체크 성공 시 저장, 하나라도 안 맞으면 다시 돌려보내기
			
			//useDto생성
			ApplyDto applyBean = new ApplyDto();
//			userBean.setUserId(userId); 값 초기화 (다른 값들도)
			HomeDao dao = new HomeDao();
			result = dao.insertUser(applyBean);
			
			//정상적으로 회원 가입 한 것만
			if(result ==1) {
				session.setAttribute("applyBean", applyBean);
				resp.sendRedirect("home/login_H.jsp");
			}else {
				//result 값에 따라 오류 메세지 
				req.setAttribute("msg", "<script type=\"text/javascript\">alert('result값에따라 문구 바꾸기');</script>");
				doGet(req, resp);
			}
			
		}else if(path.equals("/apply.home")) {
			//파일이 저장될 서버의 경로
			// String savePath = "D:/Projects/workspace/projectName/WebContent/save";
			String savePath = req.getServletContext().getRealPath("save");
			int sizeLimit = 1024*1024*15;
			//  ↓ request 객체,               ↓ 저장될 서버 경로,       ↓ 파일 최대 크기,    ↓ 인코딩 방식,       ↓ 같은 이름의 파일명 방지 처리
			// (HttpServletRequest request, String saveDirectory, int maxPostSize, String encoding, FileRenamePolicy policy)
			// 아래와 같이 MultipartRequest를 생성만 해주면 파일이 업로드 된다.(파일 자체의 업로드 완료)
			MultipartRequest multi = new MultipartRequest(req, savePath, sizeLimit, "utf-8", new DefaultFileRenamePolicy());
			
			// --------------------------아래는 전송 받은 데이터들을 DB테이블에 저장시키기 위한 작업들이다.-------------------
			//getParam으로 수강에 필요한 내용 저장 
			
			UserDto userBean = (UserDto)session.getAttribute("userBean");	//로그인한 세션 받아오기-여기서 아이디 받아와야 함  
			String lectureId =multi.getParameter("lecture_Id"); 		//강좌번호  
			System.out.println(lectureId);
			int param = Integer.parseInt(lectureId); 				
			String userId = userBean.getUserId();	//수강신청한 회원의 Id 
			System.out.println(userId);
			String fileName =multi.getFilesystemName("user_apply"); 	//첨부파일이름  
			// 업로드한 파일의 전체 경로를 DB에 저장하기 위함
			String fileFullPath = savePath + "/" + fileName;

			//ApplyDto생성 후 applyBean에 데이터 담기
			ApplyDto applyBean = new ApplyDto(); 					 
			applyBean.setLectureId(param);  
			applyBean.setFileName(fileFullPath);	//파일이름=파일전체경로
			applyBean.setUserId(userId);   
			
			HomeDao dao = new HomeDao();  
			result = dao.insertApply(applyBean);	//insert수행 메소드 사용,매개변수로 applyBean넣으면 int타입 결과 나옴 
			System.out.println("수강신청결과"+result);
			
			//정상적으로 수강신청 한 것만
			if(result ==1) {
				req.setAttribute("msg", "<script type=\"text/javascript\">alert('수강신청이 성공적으로 완료되었습니다.');</script>");
				resp.sendRedirect("home/login_H.jsp");
				rd.forward(req, resp);
			}else {
			//result 값에 따라 오류 메세지 
				req.setAttribute("errmsg", "<script type=\"text/javascript\">alert('다시시도해주세요');</script>");
				doGet(req, resp);
			}
			
	}

	}
}