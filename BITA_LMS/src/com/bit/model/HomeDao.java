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
	public int findFile(AttachedFileDto fileBean){
		//DB에 넣으려는 파일이 존재하는지..개수출력..
		int result = 0;
		String sql = "Select count(*) as total from Attached_File where original_name=?";
		try { 
			openConnection();
			pstmt = conn.prepareStatement(sql);				//apply 테이블에 넣기
			pstmt.setString(1,fileBean.getOriginalName());
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				result=rs.getInt("total");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			try {
				closeConnection();
				if(pstmt!=null)pstmt.close();
				if(conn!=null)conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	
	public int insertApply(ApplyDto applyBean,AttachedFileDto fileBean,UserDto userBean){
		int result1 = 0;
		int result2 = 0;
		int result3 = 0;
		//어플라이아이디 = 시퀀스
		//apply_id apply_date lecture_id file_name user_id
		//apply,Attached_File,File_Group 에 데이터 한번에 넣기
		System.out.println(applyBean.toString());
		System.out.println(fileBean.toString());
		String sql1 = "insert into Attached_File (file_id,file_group,original_name,file_name,file_extension,ref_date,reg_id)"
					+"values (file_id_seq.nextval,?,?,?,?,sysdate,?)";
		String sql2	="insert into apply (apply_id,apply_date,lecture_id,file_id,user_id)"
					+"values (apply_id_seq.nextval,sysdate,?,file_id_seq.currval,?)";
		String sql3 ="insert into File_Group (file_group,path)"
					+"values (?,?)";
		try {  
			openConnection();
			conn.setAutoCommit(false);
			pstmt = conn.prepareStatement(sql1);				//apply 테이블에 넣기
			pstmt.setString(1, fileBean.getFileGroup());	//Attached_File테이블에 넣기
			pstmt.setString(2, fileBean.getOriginalName());
			pstmt.setString(3, fileBean.getFileName());		//중복시..?물어보기
			pstmt.setString(4, fileBean.getFileExtension());
			pstmt.setString(5, userBean.getUserId());
			result1 = pstmt.executeUpdate();
			System.out.println("result1 "+result1);
			if(result1==1){
			pstmt = conn.prepareStatement(sql2);		
			pstmt.setInt(1, applyBean.getLectureId());
			pstmt.setString(2, applyBean.getUserId());
			System.out.println("result2 "+result2);
			if(result2==1){
			result2 = pstmt.executeUpdate();
			pstmt = conn.prepareStatement(sql3);		
			pstmt.setString(2, fileBean.getPath());
			result3 = pstmt.executeUpdate();
			System.out.println("result3"+result3);
			}
			}
		} catch (SQLException e) {
			e.printStackTrace();
	        if (conn != null) {
	            try {
	                System.err.print("Transaction is being rolled back");
	                conn.rollback();
	            } catch(SQLException excep) {
	            	e.printStackTrace();
	            }
	        }
		} finally{
			try {
				conn.setAutoCommit(true);
				if(pstmt!=null)pstmt.close();
				if(conn!=null)conn.close();
				closeConnection();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result3;
	}
	public int insertUser(ApplyDto bean){
		int result = 0;
		//어플라이아이디 = 시퀀스
		//apply_id apply_date lecture_id file_name user_id
		String sql = "insert into apply (apply_id,apply_date,lecture_id,file_id,user_id)"
		+"values (apply_id_seq.nextval,sysdate,?,?,?)";
		
		try {
			openConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bean.getLectureId());
			pstmt.setInt(2, bean.getFileId());
			pstmt.setString(3, bean.getUserId());
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			try {
				closeConnection();
				if(pstmt!=null)pstmt.close();
				if(conn!=null)conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
}