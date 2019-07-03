package com.bit.model;

import java.sql.Connection;
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
	//싸그리 다 불러와서 필요한것만 뽑아서 쓰기
	public ArrayList<LectureDto> getLecture() {
		ArrayList<LectureDto> list = new ArrayList<LectureDto>();
		
		String sql ="";
		
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()){
				LectureDto bean = new LectureDto();
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
	public ArrayList<RegisterDto> getRegister(int lectureId) {
		
		ArrayList<RegisterDto> list = new ArrayList<RegisterDto>();
		
		//번호 (제목제외) ID 이름 강좌 날짜 소속을 불러와야함
		//제목은 name을 불러와서 프론트엔드에서 ***님의 수강신청을 붙여야함
		//조건은 콤보박스로 강좌명에 따라 화면표시
		//그리고 어플라이한 사람만 보여야함 belong=before
		//수정중
		String sql ="SELECT a.apply_id AS \"num\", u.name AS \"name\" ,u.id AS \"id\", l.name AS \"lecName\""
				+ ", a.apply_date AS \"applyDate\", u.belong AS \"belong\""
				+ "FROM lecture as l "
				+ "JOIN user01 as u "
				+ "on a.user_id=u.user_id "
				+ "JOIN apply as a "
				+ "on a.lecture_id=l.lecture_id "
				+ "WHERE a.lecture_id = ? AND "
				+ "u.belong=\"before\""
				+ "ORDER BY a.apply_date";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, lectureId);
			rs = pstmt.executeQuery();
			if(rs.next()){
				RegisterDto bean = new RegisterDto();
				bean.setApplyDate(rs.getDate("applyDate"));
				bean.setId(rs.getString("id"));
				bean.setLecName(rs.getString("lecName"));
				bean.setMaxStd(rs.getInt("maxStd"));
				bean.setName(rs.getString("name"));
				bean.setNum(rs.getInt("num"));
				bean.setNumStd(rs.getInt("numStd"));
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
		
		//제대로 전송됐는지 안됐는지만 int값으로 리턴
		return 0;
	}
	
	//행정팀 학생관리 상세페이지
	public ArrayList<RegisterDto> DetailRegister() {
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
	
	
}
