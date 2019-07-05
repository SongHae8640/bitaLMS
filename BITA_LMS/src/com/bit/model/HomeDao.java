package com.bit.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class HomeDao {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@192.168.1.7:1521:xe";
	String user = "bita";
	String password = "bita";
	
	Connection conn;
	PreparedStatement pstmt;
	ResultSet rs;
	
	//생성자 호출할 때 커넥션 연결
	public HomeDao(){
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url,user,password);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public int insertApply(ApplyDto bean){
		int result = 0;
		//어플라이아이디 = 시퀀스
		//apply_id apply_date lecture_id file_name user_id
		String sql = "insert into apply (apply_id,apply_date,lecture_id,file_name,user_id)"
		+"values (apply_id_seq.nextval,sysdate,?,?,?)";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bean.getLectureId());
			pstmt.setString(2, bean.getFileName());
			pstmt.setString(3, bean.getUserId());
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
		return result;
	}
}
