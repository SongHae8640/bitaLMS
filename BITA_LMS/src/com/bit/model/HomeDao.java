package com.bit.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class HomeDao extends Dao{
	
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
			closeConnection();
		}
		return result;
	}
}
