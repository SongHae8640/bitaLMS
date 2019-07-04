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
		return null;
	}
	
	//행정팀 학생관리 학생등록 목록 페이지
	public ArrayList<RegisterDto> getRegister() {
		
		//번호 (제목제외) ID 이름 강좌 날짜 과정, 등록인원/최대인원, 소속을 불러와야함
		//제목은 name을 불러와서 프론트엔드에서 ***님의 수강신청을 붙여야함
		//조건은 콤보박스로 강좌명에 따라 화면표시
		//그리고 어플라이한 사람만 보여야함 belong=before
		//수정중
		String sql ="SELECT a.apply_id AS \"num\", u.name AS \"name\" ,u.id AS \"id\", l.name AS \"lecName\""
				+ ", a.apply_date AS \"applyDate\", l.num_std AS \"numStd\", l.max_std AS \"maxStd\", u.belong AS \"belong\""
				+ "FROM lecture as l "
				+ "JOIN user01 as u "
				+ "on a.user_id=u.user_id "
				+ "JOIN apply as a "
				+ "on a.lecture_id=l.lecture_id "
				+ "WHERE a.lecture_id = ? AND "
				+ "u.belong=\"before\""
				+ "ORDER BY a.apply_date";
		
		System.out.println(sql);
		return null;
	}
}
