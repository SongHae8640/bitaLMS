package com.bit.model;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class HomeDao extends Dao{

	//홈로그인
	public UserDto login(String id, String pw){
		String sql = "select user_id,password,name,email,phone_number,belong from User01 where user_id=? and password=?";
		UserDto bean = new UserDto();
		
		try {
			openConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,id);
			pstmt.setString(2,pw);
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				bean.setUserId(rs.getString("user_id"));
				bean.setPassword(rs.getString("password")); ///지우는게 나을까? 보안상 좋진 않은듯. 나중에 필요하지 않으면 지울것
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
	
	public int insertApply(ApplyDto bean){
		int result = 0;
		//어플라이아이디 = 시퀀스
		//apply_id apply_date lecture_id file_name user_id
		String sql = "insert into apply (apply_id,apply_date,lecture_id,file_name,user_id)"
		+"values (apply_id_seq.nextval,sysdate,?,?,?)";
		
		try {
			openConnection();
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
	public int insertUser(ApplyDto bean){
		int result = 0;
		//어플라이아이디 = 시퀀스
		//apply_id apply_date lecture_id file_name user_id
		String sql = "insert into apply (apply_id,apply_date,lecture_id,file_name,user_id)"
		+"values (apply_id_seq.nextval,sysdate,?,?,?)";
		
		try {
			openConnection();
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
