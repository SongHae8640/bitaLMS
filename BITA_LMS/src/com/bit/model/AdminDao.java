package com.bit.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AdminDao {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:xe";
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
	
	//행정팀 학생관리 학생등록 목록 페이지
	public ArrayList<RegisterDto> getRegister() {
		
		//번호 (제목제외) ID 이름 강좌 날짜 과정, 등록인원/최대인원 불러와야함
		//제목은 name을 불러와서 프론트엔드에서 ***님의 수강신청을 붙여야함
		//수정중
		String sql ="SELECT a.apply_id AS \"num\", u.name AS \"name\" ,u.id AS \"id\", l.name AS \"lecName\""
				+ ", l.name AS \"lecName\""
				+ "FROM apply as a "
				+ "JOIN user01 as u "
				+ "on a.std_id=u.user_id "
				+ "JOIN lecture as l "
				+ "on a.std_id=u.user_id "
				+ "WHERE a.lecture_id = ? AND "
				+ "TO_Date(SYSDATE,'yyyymmdd')=TO_Date(a.day_time,'yyyymmdd') "
				+ "ORDER BY u.name";
		
		return null;
	}
}
