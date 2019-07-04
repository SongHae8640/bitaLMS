package com.bit.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class TeacherDao {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@192.168.1.7:1521:xe";
	String user = "bita";
	String password = "bita";
	
	Connection conn;
	PreparedStatement pstmt;
	ResultSet rs;
	
	public TeacherDao(){
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url,user,password);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	//이건 머지?
	public int attendance(String id, String pw){
		String sql = "select belong from bitauser where user_id=? and password=?";
		int result =0;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, pw);
			rs = pstmt.executeQuery();
			if(rs.next()){
				result= rs.getInt("num");
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
		return result;
	}

	///////////////////////////////////////////
	//출석

	// 강좌번호를 파라미터로 주고 학생이름, 출석상태, 날짜를 리턴 받는다.
	public ArrayList<AttendanceDto> getTodayAttendance(int lectureId) {
		ArrayList<AttendanceDto> list = new ArrayList<AttendanceDto>();
		String sql ="SELECT day_time,name,std_id,status,lecture_id "
				+ "FROM attendance a "
				+ "JOIN user01 u "
				+ "ON a.std_id = u.user_id "
				+ "WHERE a.lecture_id=? "
				+ "AND TO_CHAR(SYSDATE,'yyyymmdd')=TO_CHAR(a.day_time,'yyyymmdd')";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, lectureId);
			rs = pstmt.executeQuery();
			System.out.println("getTodayAttendance :: lectureId = " + lectureId);
			while(rs.next()){
				System.out.println("bean +1");
				AttendanceDto bean = new AttendanceDto();
				bean.setDayTime(rs.getDate("day_time"));
				bean.setName(rs.getString("name"));
				bean.setStatus(rs.getString("status"));
				bean.setLectureId(lectureId);
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
	
	//출결관리(목록)에서 저장 버튼을 클릭 했을때 사용되는 메서드
	// 학생의 출석 정보를 db에 저장한다.
	public void setTodayAttendance(ArrayList<AttendanceDto> list) {
		//강사가 저장한 출석 정보와 학생의 출석 정보가 다를때 어떻게 할지 맞춰야함
		//ex) 강사 화면에는 아무것도 안찍혀 있어서 지각을 눌렀으나, 동기화 되기 전 학생이 출석을 찍은경우 등
		
	}
	
	// 강좌번호와 월을 파라미터로 주고 학생이름, 출석상태, 날짜를 리턴 받는다.
	// 출결 관리(월별) 페이지에서 보여지는 0000-00의 값을 yyyymm 형태의 문자열로 받는다.
	public ArrayList<AttendanceDto> getMonthAttendance(int lectureId, String yyyymm) {
		ArrayList<AttendanceDto> list = new ArrayList<AttendanceDto>();
		String sql ="SELECT name, status, day_time "
				+ "FROM attendance a "
				+ "JOIN user01  u "
				+ "on a.std_id=u.user_id "
				+ "WHERE a.lecture_id = ? AND "
				+ "?=TO_CHAR(day_time,'yyyymm') "
				+ "ORDER BY name";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, lectureId);
			pstmt.setString(2, yyyymm);
			rs = pstmt.executeQuery();
			while(rs.next()){
				AttendanceDto bean = new AttendanceDto();
				bean.setDayTime(rs.getDate("day_time"));
				bean.setName(rs.getString("name"));
				bean.setStatus(rs.getString("stutus"));
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
	
	/// 입실 버튼을 누르면 바로 이벤트를 발생 시킬 것인지? 도영이형이라 이야기 할것

	
	///////////////////////////////////////////
	//성적
	
	//강좌번호(lectureId)에 해당하는 학생들의 성적을 가져오는 메서드
	public ArrayList<ScoreDto> getScoreList(int lectureId){
		ArrayList<ScoreDto> list = new ArrayList<ScoreDto>();
		String sql = "SELECT name, first_score, second_score,third_score,avg_score "
				+ "FROM user01 u JOIN score s "
				+ "ON s.std_id=u.user_id "
				+ "WHERE lecture_id = ? "
				+ "ORDER BY u.name";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, lectureId);
			rs = pstmt.executeQuery();
			while(rs.next()){
				ScoreDto bean = new ScoreDto();
				//별칭을 지어줘야 하는지 찾아보고 다시 할것
				bean.setName(rs.getString("name"));
				bean.setFirstScore(rs.getInt("first_score"));
				bean.setSecondScore(rs.getInt("second_score"));
				bean.setThirdScore(rs.getInt("third_score"));
				bean.setAvgScore(rs.getDouble("avg_score"));
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

	public ArrayList<AssignmentDto> getAssignmentList(int lectureId) {
		ArrayList<AssignmentDto> list = new ArrayList<AssignmentDto>();
		String sql = "SELECT rownum,assignment_id,title,TO_CHAR(write_date,'yyyy-mm-dd') AS write_date  FROM assignment "
				+ "WHERE lecture_id =?";
//				+ "ORDER BY write_date";	//이걸 쓰면 더 이상해 지네? 나중에 더미 데이터가 더 생기면 해볼것
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, lectureId);
			rs = pstmt.executeQuery();
			while(rs.next()){
				AssignmentDto bean = new AssignmentDto();
				bean.setRowNum(rs.getInt("rownum"));
				bean.setAssignmentId(rs.getInt("assignment_id"));
				bean.setTitle(rs.getString("title"));
				bean.setWriteDate(rs.getString("write_date"));
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
	
	public AssignmentDto getAssignmentDetail(int assignmentId) {
		AssignmentDto bean = new AssignmentDto();
		String sql = "SELECT rownum,assignment_id,title,TO_CHAR(write_date,'yyyy-mm-dd') AS write_date  FROM assignment "
				+ "WHERE lecture_id =?";

		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, assignmentId);
			rs = pstmt.executeQuery();
			if(rs.next()){
				bean.setRowNum(rs.getInt("rownum"));
				bean.setAssignmentId(rs.getInt("assignment_id"));
				bean.setTitle(rs.getString("title"));
				bean.setWriteDate(rs.getString("write_date"));
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
		return bean;
	}
}
