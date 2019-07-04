package com.bit.model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AdminDao {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@192.168.1.7:1521:xe";
	String user = "bita";
	String password = "bita";
	
	Connection conn;
	PreparedStatement pstmt;
	ResultSet rs;
	
	public static void main(String[] args) {
		AdminDao dao = new AdminDao();
		dao.getRegister();
	}
	
	//생성자 호출할 때 커넥션 연결
	public AdminDao(){
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url,user,password);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	//행정팀 강좌목록 불러오기
	public ArrayList<LectureDto> getLecture() {
		ArrayList<LectureDto> list = new ArrayList<LectureDto>();
		
		String sql ="SELECT lecture_id, name, TO_CHAR(start_date,'yyyymmdd') as \"endDate\", TO_CHAR(end_date,'yyyymmdd') as \"startDate\", num_std, total_days, max_std, lv, content, is_close, file_name from lecture";
		
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()){
				LectureDto bean = new LectureDto();
				bean.setContent(rs.getString("content"));
				bean.setEndDate(rs.getString("endDate"));
				bean.setFileName(rs.getString("file_name"));
				bean.setIsClose(rs.getString("is_close"));
				bean.setLectureID(rs.getInt("lecture_id"));
				bean.setLv(rs.getInt("lv"));
				bean.setMaxStd(rs.getInt("max_std"));
				bean.setName(rs.getString("name"));
				bean.setNumStd(rs.getInt("num_std"));
				bean.setStartDate(rs.getString("startDate"));
				bean.setTotalDays(rs.getInt("total_days"));
				list.add(bean);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				if(rs!=null)rs.close();
				if(pstmt!=null)pstmt.close();
				if(conn!=null)conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return list;
	}
	
	//행정팀 학생관리 학생등록 목록 페이지
	public ArrayList<RegisterDto> getRegister() {
		
		//번호 (제목제외) ID 이름 강좌 날짜 과정, 등록인원/최대인원, 소속을 불러와야함
		//제목은 name을 불러와서 프론트엔드에서 ***님의 수강신청을 붙여야함
		//조건은 콤보박스로 강좌명에 따라 화면표시
		//그리고 어플라이한 사람만 보여야함 belong=before
		//SELECT apply_id as "num", u.name AS "name" ,u.user_id AS "id", l.name AS "lecName", TO_CHAR(a.apply_date,'yyyymmdd') AS "applyDate", u.belong AS "belong" FROM apply a INNER JOIN user01 u on a.user_id=u.user_id INNER JOIN lecture l on l.lecture_id = a.lecture_id
		//WHERE a.lecture_id = 1 ORDER BY a.apply_date;
		String sql = "SELECT apply_id as \"num\", u.name as \"name\", u.user_id AS \"id\", l.name AS \"lecName\", "
		+"TO_CHAR(a.apply_date,'yyyymmdd') AS \"applyDate\", u.belong AS \"belong\" "
		+"FROM apply a INNER JOIN user01 u on a.user_id=u.user_id "
		+"INNER JOIN lecture l on l.lecture_id = a.lecture_id "
		+"ORDER BY apply_id desc";
		//WHERE a.lecture_id = ? 
		System.out.println(sql);
		try {
			pstmt = conn.prepareStatement(sql);
//			pstmt.setInt(1, lectureId);
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				RegisterDto bean = new RegisterDto();
				bean.setApplyDate(rs.getString("applyDate"));
				bean.setId(rs.getString("id"));
				bean.setLecName(rs.getString("lecName"));
				bean.setName(rs.getString("name"));
				bean.setNum(rs.getInt("num"));
				list.add(bean);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				if(rs!=null)rs.close();
				if(pstmt!=null)pstmt.close();
				if(conn!=null)conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}
	
	//행정팀 학생관리 수강생으로 등록
	public int CheckRegister() {
		//해당 값들 인자로 받아와서 insert
		
		//제대로 전송됐는지 안됐는지만 int값으로 리턴
		return 0;
	}
	
	//행정팀 학생관리 상세페이지
	public ArrayList<RegisterDto> DetailRegister() {
		//선택된 학생 인자값으로 받아오기
		
		
		String sql = "SELECT";
		
		return null;
	}
	
	//행정팀 학생관리 상세페이지 수정
	public int UpdateRegister() {
		//제대로 전송됐는지 안됐는지만 int값으로 리턴
		return 0;
	}
	
	//행정팀 학생관리 상세페이지 삭제
	public int DeleteRegister() {
		//제대로 전송됐는지 안됐는지만 int값으로 리턴
		return 0;
	}
	
	//행정팀 수강생관리 목록형
	
	
	//행정팀 수강생관리 월별
	
	
	//행정팀 강좌관리 목록페이지
	
	//행정팀 강좌관리 상세페이지
	
	//행정팀 강사관리 목록페이지
	
	//행정팀 강사관리 상세페이지

}
