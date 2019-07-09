package com.bit.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class UserDao {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@192.168.1.7:1521:xe";
	//192.168.1.7
	String user = "bita";
	String password = "bita";
	
	Connection conn;
	PreparedStatement pstmt;
	ResultSet rs;
	
	public UserDao(){
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url,user,password);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public UserDto login(String id, String pw){
		String sql = "SELECT u.user_Id AS user_id, password, u.name AS name, "
				+ "email, phone_number, belong"
				+ ", l.name AS lectureName, "
				+ "start_date,end_date,l.lecture_id AS lecture_id"
				+ " FROM user01 u "
				+ "JOIN lectureuser lu ON u.user_id = lu.user_id "
				+ "JOIN lecture l ON lu.lecture_id = l.lecture_id "
				+ "WHERE u.user_id=? AND u.password=?";
		UserDto bean = new UserDto();
		System.out.println(id+"$$"+pw);
		System.out.println(sql);
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, pw);
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				bean.setUserId(rs.getString("user_id"));
				bean.setPassword(rs.getString("password")); ///지우는게 나을까? 보안상 좋진 않은듯. 나중에 필요하지 않으면 지울것
				bean.setName(rs.getString("name"));
				bean.setEmail(rs.getString("email"));
				bean.setPhoneNumber(rs.getString("phone_number"));
				bean.setBelong(rs.getString("belong"));
//				bean.setLectureName(rs.getString("lectureName"));
//				bean.setStartDate(rs.getString("start_date"));
//				bean.setEndDate(rs.getString("end_date"));
//				bean.setLecture_id(rs.getInt("lecture_id"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			try {
				if(rs!=null)rs.close();
				if(pstmt!=null)pstmt.close();
				if(conn!=null)conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return bean;
	}
	
	//계정을 생성하는 메서드
	public int insertUser(UserDto bean){
		int result=0;
		//user_id, password, name, email, phone, inflow_path, belong
		String sql = "insert into user01"
				+ "(user_id,password,name,email,phone,inflow_path,belong)"
				+ " values(?,?,?,?,?,?,'학생')";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, bean.getUserId());
			pstmt.setString(2, bean.getPassword());
			pstmt.setString(3, bean.getName());
			pstmt.setString(4, bean.getEmail());
			pstmt.setString(5, bean.getPhoneNumber());
			pstmt.setString(6, bean.getInflowPath());
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			try {
				if(pstmt!=null)pstmt.close();
				if(conn!=null)conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return 0;
	}

	
}
