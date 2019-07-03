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
	//�⼮

	// ���¹�ȣ�� �Ķ���ͷ� �ְ� �л��̸�, �⼮����, ��¥�� ���� �޴´�.
	public ArrayList<AttendanceDto> getTodayAttendance(int lectureId) {
		ArrayList<AttendanceDto> list = new ArrayList<AttendanceDto>();
		String sql ="SELECT u.name AS \"name\",a.status AS \"status\" ,a.day_time AS \"day_time\" "
				+ "FROM attendance as a "
				+ "JOIN user01 as u "
				+ "on a.std_id=u.user_id "
				+ "WHERE a.lecture_id = ? AND "
				+ "TO_Date(SYSDATE,'yyyymmdd')=TO_Date(a.day_time,'yyyymmdd') "
				+ "ORDER BY u.name";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, lectureId);
			rs = pstmt.executeQuery();
			if(rs.next()){
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
	
	//������(���)���� ���� ��ư�� Ŭ�� ������ ���Ǵ� �޼���
	// �л��� �⼮ ������ db�� �����Ѵ�.
	public void setTodayAttendance(ArrayList<AttendanceDto> list) {
		//���簡 ������ �⼮ ������ �л��� �⼮ ������ �ٸ��� ��� ���� �������
		//ex) ���� ȭ�鿡�� �ƹ��͵� ������ �־ ������ ��������, ����ȭ �Ǳ� �� �л��� �⼮�� ������� ��
		
	}
	
	// ���¹�ȣ�� ���� �Ķ���ͷ� �ְ� �л��̸�, �⼮����, ��¥�� ���� �޴´�.
	// ��� ����(����) ���������� �������� 0000-00�� ���� yyyymm ������ ���ڿ��� �޴´�.
	public ArrayList<AttendanceDto> getMonthAttendance(int lectureId, String yyyymm) {
		ArrayList<AttendanceDto> list = new ArrayList<AttendanceDto>();
		String sql ="SELECT u.name AS \"name\",a.status AS \"status\" ,a.day_time AS \"day_time\" "
				+ "FROM attendance as a "
				+ "JOIN user01 as u "
				+ "on a.std_id=u.user_id "
				+ "WHERE a.lecture_id = ? AND "
				+ "?=TO_Date(a.day_time,'yyyymmdd') "
				+ "ORDER BY u.name";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, lectureId);
			pstmt.setString(2, yyyymm);
			rs = pstmt.executeQuery();
			if(rs.next()){
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

	
	///////////////////////////////////////////
	//����
	
	//���¹�ȣ(lectureId)�� �ش��ϴ� �л����� ������ �������� �޼���
	public ArrayList<ScoreDto> getScore(int lectureId){
		ArrayList<ScoreDto> list = new ArrayList<ScoreDto>();
		String sql = "SELECT u.name, s.first_score, s.second_score,s.third_score,s.avg_score "
				+ "FROM user as a JOIN score as s "
				+ "ON s.std_id=u.user_id "
				+ "WHERE lecture_id = ? "
				+ "ORDER BY u.name";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, lectureId);
			rs = pstmt.executeQuery();
			if(rs.next()){
				ScoreDto bean = new ScoreDto();
				//��Ī�� ������� �ϴ��� ã�ƺ��� �ٽ� �Ұ�
				bean.setName(rs.getString("name"));
				bean.setFirstScore(rs.getInt("first_Score"));
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
}
