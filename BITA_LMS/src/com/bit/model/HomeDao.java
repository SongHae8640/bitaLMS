package com.bit.model;
import java.sql.SQLException;

public class HomeDao extends Dao{

	//홈로그인
	public UserDto login(String id, String pw){
		UserDto bean = new UserDto();
		String sql = "SELECT u.user_Id AS user_id, password, u.name AS name, "
				+ "email, phone_number, belong"
				+ ", l.name AS lectureName, "
				+ "to_char(start_date) as \"start_date\",to_char(end_date) as \"end_date\",l.lecture_id AS lecture_id"
				+ " FROM user01 u "
				+ "JOIN lectureuser lu ON u.user_id = lu.user_id "
				+ "JOIN lecture l ON lu.lecture_id = l.lecture_id "
				+ "WHERE u.user_id=? AND u.password=?";

		try {
			openConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, pw);
			rs = pstmt.executeQuery();
			if(rs.next()){
				bean.setUserId(rs.getString("user_id"));
				bean.setPassword(rs.getString("password")); 
				bean.setName(rs.getString("name"));
				bean.setEmail(rs.getString("email"));
				bean.setPhoneNumber(rs.getString("phone_number"));
				bean.setBelong(rs.getString("belong"));
				bean.setLectureName(rs.getString("lectureName"));
				bean.setStartDate(rs.getString("start_date"));
				bean.setEndDate(rs.getString("end_date"));
				bean.setLectureId(rs.getInt("lecture_id"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			closeConnection();
		}
		return bean;
	}
	public int findFile(AttachedFileDto fileBean){
		//DB에 넣으려는 파일이 존재하는지 개수출력
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
			closeConnection();
		}
		return result;
	}
	//수강신청
	public int insertApply (ApplyDto applyBean,AttachedFileDto fileBean){
		int result1 = 0;
		int result2 = 0;
		//어플라이아이디 = 시퀀스
		//apply_id apply_date lecture_id file_name user_id
		//apply,Attached_File,File_Group 에 데이터 한번에 넣기
		String sql1 = "insert into Attached_File (file_id,file_group,original_name,file_name,file_extension,ref_date,reg_id)"
					+"values (file_id_seq.nextval,?,?,?,?,sysdate,?)";
		String sql2	="insert into apply (apply_id,apply_date,lecture_id,file_id,user_id)"
					+"values (apply_id_seq.nextval,sysdate,?,file_id_seq.currval,?)";
		try {  
			openConnection();
			conn.setAutoCommit(false);
			pstmt = conn.prepareStatement(sql1);				//apply 테이블에 넣기
			pstmt.setString(1, fileBean.getFileGroup());		//Attached_File테이블에 넣기
			pstmt.setString(2, fileBean.getOriginalName());
			pstmt.setString(3, fileBean.getFileName());		
			pstmt.setString(4, fileBean.getFileExtension());
			pstmt.setString(5, applyBean.getUserId());
			result1 = pstmt.executeUpdate();
			if(result1==1){
			pstmt = conn.prepareStatement(sql2);		
			pstmt.setInt(1, applyBean.getLectureId());
			pstmt.setString(2, applyBean.getUserId());
			result2 = pstmt.executeUpdate();
			}
		} catch (SQLException e) {
			e.printStackTrace();
	        if (conn != null) {
	            try {
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
		return result2;
	}
	//회원가입
	public int insertUser (UserDto userBean){
		int result1 = 0;
		int result2 = 0;
		String sql1 ="insert into User01 (user_id,password,name,email,phone_number,inflow_path,belong)"
					+"values (?,?,?,?,?,?,?)";
		String sql2 ="insert into LectureUser (lecture_id,user_id) values (?,?)";
		try {
			openConnection();
			conn.setAutoCommit(false);
			pstmt = conn.prepareStatement(sql1);
			pstmt.setString(1,userBean.getUserId());
			pstmt.setString(2,userBean.getPassword());
			pstmt.setString(3,userBean.getName());
			pstmt.setString(4,userBean.getEmail());
			pstmt.setString(5,userBean.getPhoneNumber());
			pstmt.setString(6,userBean.getInflowPath());
			pstmt.setString(7,"before");			//수강신청등록 전 학생값=0
			result1 = pstmt.executeUpdate();
			if(result1==1) {
			pstmt = conn.prepareStatement(sql2);
			pstmt.setString(1,"0");				//강좌개설아직 안됐으니까 디폴트 0
			pstmt.setString(2,userBean.getUserId());
			result2 = pstmt.executeUpdate();
			}
		} catch (SQLException e) {
			e.printStackTrace();
	        if (conn != null) {
	            try {
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
		return result2;
	}
	//중복 아이디 체크
	public int idCheck(String id) {
		int result = 0;
		String sql = "Select count(*) as total from user01 where user_id=?";
		try { 
			openConnection();
			pstmt = conn.prepareStatement(sql);				
			pstmt.setString(1,id);
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				result=rs.getInt("total");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			closeConnection();
		}
		return result;
	}
}
