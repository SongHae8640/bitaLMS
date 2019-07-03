package com.bit.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class UserDao {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@192.168.1.7:1521:xe";
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
		String sql = "SELECT * FROM user01 u JOIN lectureUser l "
				+ "ON u.user_id=l.user_id "
				+ "WHERE u.user_id=? AND u.password=?";
		UserDto bean = new UserDto();
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, pw);
			rs = pstmt.executeQuery();
			
			if(rs.next()){;
				bean.setUserId(rs.getString("user_id"));
				bean.setPassword(rs.getString("password")); //나중에 패스워스 수정을 사용할 수도 있으니, 안쓸꺼면 지우고
				bean.setName(rs.getString("name"));
				bean.setEmail(rs.getString("email"));
				bean.setPhoneNumber(rs.getString("phone_number"));
				bean.setBelong(rs.getString("belong"));
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
}
