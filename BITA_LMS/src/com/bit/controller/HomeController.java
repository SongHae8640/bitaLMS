package com.bit.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.bit.model.ApplyDto;
import com.bit.model.AttachedFileDto;
import com.bit.model.HomeDao;
import com.bit.model.UserDto;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

@WebServlet("*.home")
public class HomeController extends HttpServlet {

	private static final String ApplyDto = null;

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
			String id = req.getParameter("id");
			rd = req.getRequestDispatcher("home/join_H.jsp");
		}else if(path.equals("/apply.home")){
			rd = req.getRequestDispatcher("home/apply_H.jsp");	
		}else if(path.equals("/idcheck.home")){
			rd = req.getRequestDispatcher("home/idCheck_H.jsp");
		}
		
		if(rd!=null){
			rd.forward(req, resp);
		}

	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		RequestDispatcher rd = null;
		HttpSession session = req.getSession();
		HomeDao dao = new HomeDao();
		int result = 0;
		String path = req.getRequestURI().replaceAll(req.getContextPath(), "");
		System.out.println("HomeController(doPost) :: path = " + path);
		
		if(path.equals("/login.home")){
			
			String id = req.getParameter("id");
			String pw = req.getParameter("pw");
			UserDto userBean = dao.login(id, pw);
			String name = userBean.getName();
			String message = "";
			
		try{
			System.out.println(userBean.getUserId());
			if(userBean.getUserId().equals(id) && userBean.getPassword().equals(pw) ){//로그인성공
				//session
				session.setAttribute("userBean", userBean);
				session.setMaxInactiveInterval(5*60);	//나중에 로그인 만료시간을 사용할때 사용, param의 단위는 초
				doGet(req, resp);
			}
			}catch(java.lang.NullPointerException e){
				req.setAttribute("errmsg", "<script type=\"text/javascript\">alert('id&pw를 다시 확인하세요');</script>");
			}
		}else if(path.equals("/logout.home")){
			session.invalidate();//로그아웃-invalidate는 세션자체가 갱신됨
		
		}else if(path.equals("/idcheck.home")){
			String msg ="";
			String id = req.getParameter("id");
			System.out.println("id"+id); 
			result = dao.idCheck(id);
			System.out.println("중복확인result값 "+result);
			if(result>0){
				msg = "사용할 수 없는 ID입니다";
			}else{ 
				msg = "사용가능한 ID입니다";
			}
			req.setAttribute("id", id);
			req.setAttribute("msg", msg);
			doGet(req, resp);
		
		}else if(path.equals("/join.home")){
			//getParam으로 회원가입에 필요한 내용 체크 및 저장
			//전부 체크 성공 시 저장, 하나라도 안 맞으면 다시 돌려보내기
			//ApplyDto생성
			req.setCharacterEncoding("UTF-8");
			resp.setContentType("text/html;charset=UTF-8");
			String user_id = req.getParameter("id");
			System.out.println("user_id"+user_id);
			String password = req.getParameter("pw");
			String name = req.getParameter("name");
			String email = req.getParameter("email");
			String phone_num = req.getParameter("phone_num");
			System.out.println(phone_num);
			String belong="-1";			//등록 전 학생 default값 -1
			String[] inflow_path_num = req.getParameterValues("inflow_path");	//유입경로(중복O)
			String ect = req.getParameter("ect");
			String inflow_path = "";
			String total="";
			for(int i=0;i<inflow_path_num.length;i++) {
				inflow_path = inflow_path_num[i];
				if(inflow_path.equals("1")) {
					inflow_path = "지인소개";
				}else if(inflow_path.equals("2")) {
					inflow_path = "인터넷 검색";
				}else if(inflow_path.equals("3")) {
					inflow_path = "블로그/카페";
				}else if(inflow_path.equals("4")) {
					inflow_path = "커뮤니티";  
				}else if(inflow_path.equals("5")) {
					inflow_path = "학원생 추천";
				}else if(inflow_path.equals("6")) {
					inflow_path = ect;
				}	
				total+=inflow_path+",";
			} 
			System.out.println("1");
			int Idx = total.lastIndexOf(","); total = total.substring(0, Idx);
			UserDto userBean = new UserDto();
			userBean.setUserId(user_id);
			userBean.setPassword(password);
			userBean.setName(name);
			userBean.setEmail(email);
	 		userBean.setPhoneNumber(phone_num);
			userBean.setInflowPath(total);
			userBean.setBelong(belong);
			result = dao.insertUser(userBean);
			System.out.println(result);
			//정상적으로 회원 가입 한 것만
			if(result ==1) {
				System.out.println("회원가입성공");
				req.setAttribute("errmsg", "<script type=\"text/javascript\">alert('가입을 축하드립니다.');</script>");
				rd = req.getRequestDispatcher("home/main_H.jsp");
				rd.forward(req, resp);
			}else {
				//result 값에 따라 오류 메세지 
				req.setAttribute("errmsg", "<script type=\"text/javascript\">alert('회원가입실패');</script>");
				doGet(req, resp);
			}
			 
		}else if(path.equals("/apply.home")) {
			//수강신청 등록하면 여기로 옴 getPost()
			//파일이 저장될 서버의 경로
			// String savePath = "D:/Projects/workspace/projectName/WebContent/save";
			String savePath = req.getServletContext().getRealPath("save");
			System.out.println("여기저장"+savePath);
			int sizeLimit = 1024*1024*15;
			//  ↓ request 객체,               ↓ 저장될 서버 경로,       ↓ 파일 최대 크기,    ↓ 인코딩 방식,       ↓ 같은 이름의 파일명 방지 처리
			// (HttpServletRequest request, String saveDirectory, int maxPostSize, String encoding, FileRenamePolicy policy)
			// 아래와 같이 MultipartRequest를 생성만 해주면 파일이 업로드 된다.(파일 자체의 업로드 완료)
			MultipartRequest multi = new MultipartRequest(req, savePath, sizeLimit, "utf-8", new DefaultFileRenamePolicy());
			
			// --------------------------아래는 전송 받은 데이터들을 DB테이블에 저장시키기 위한 작업들이다.-------------------
			//getParam으로 수강에 필요한 내용 저장 
			UserDto userBean = (UserDto)session.getAttribute("userBean");	//로그인한 세션 받아오기-여기서 아이디 받아와야 함  
			String userId = userBean.getUserId();							//수강신청한 회원의 Id 
			System.out.println(userId);
			String lectureId =multi.getParameter("lecture_Id"); 			//강좌번호  
			System.out.println("lectureId(자바1,디비2,웹3)"+lectureId);
			int param = Integer.parseInt(lectureId); 				
			String fullfileName = multi.getOriginalFileName("user_apply");		
			int Idx = fullfileName .lastIndexOf(".");								
			String oriFileName = fullfileName.substring(0, Idx);
			String fileExtend = fullfileName.substring(fullfileName.lastIndexOf(".")+1);//확장자이름 
			// 업로드한 파일의 전체 경로를 DB에 저장하기 위함
			System.out.println("oriFileName: 사용자가 올리는 파일이름 "+oriFileName);
			System.out.println("fileExtend: 확장자이름 "+fileExtend);
			
			//ApplyDto생성 후 applyBean에 데이터 담기 
			ApplyDto applyBean = new ApplyDto(); 
			applyBean.setLectureId(param);   
			applyBean.setUserId(userId);   
			
			//AttechedFileDto생성 후 fileBean에 데이터 담기
			AttachedFileDto fileBean = new AttachedFileDto();
			fileBean.setFileGroup("수강신청");	
			fileBean.setOriginalName(oriFileName);		//사용자가 올린 파일의 original name
			fileBean.setFileExtension(fileExtend);		//파일의 확장자
			fileBean.setRegId(userId);					//파일 올린 회원의 id
	
			//HomeDao 인스턴스화해서 메소드 사용
			//중복파일 검사 후 이름 바꿔서 fileBean에 담기
			int count = dao.findFile(fileBean);
			System.out.println("count: 중복되는 파일 수 "+count);
			if(count>0){
				String fileName = oriFileName+"("+count+")";
				fileBean.setFileName(fileName);	//중복파일이름 생성~~!
				System.out.println("fileName: "+fileName);
			}else{
				String fileName = oriFileName;
				fileBean.setFileName(fileName);
				System.out.println("fileName: "+fileName);
			}
			//파일업로드한거 insert 
			result = dao.insertApply(applyBean,fileBean);	 
			System.out.println("수강신청결과"+result); 
			
			//정상적으로 수강신청 한 것만
			if(result ==1) {
				req.setAttribute("errmsg", "<script type=\"text/javascript\">alert('수강신청이 성공적으로 완료되었습니다.');</script>");
				doGet(req, resp);
			}else {
			//result 값에 따라 오류 메세지 
				req.setAttribute("errmsg", "<script type=\"text/javascript\">alert('다시시도해주세요');</script>");
				doGet(req, resp);
			}
			
	}

	}
}