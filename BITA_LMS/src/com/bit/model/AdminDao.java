package com.bit.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AdminDao {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@192.168.1.7:1521:xe";
	String user = "bita";
	String password = "bita";
	
	Connection conn;
	PreparedStatement pstmt;
	ResultSet rs;
	
	//������ ȣ���� �� Ŀ�ؼ� ����
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
	
	//������ ���¸�� �ҷ�����
	//�α׸� �� �ҷ��ͼ� �ʿ��Ѱ͸� �̾Ƽ� ����
	public ArrayList<LectureDto> getLecture() {
		ArrayList<LectureDto> list = new ArrayList<LectureDto>();
		
		String sql ="";
		
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()){
				LectureDto bean = new LectureDto();
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
	
	//������ �л����� �л���� ��� ������
	public ArrayList<RegisterDto> getRegister(int lectureId) {
		
		ArrayList<RegisterDto> list = new ArrayList<RegisterDto>();
		
		//��ȣ (��������) ID �̸� ���� ��¥ �Ҽ��� �ҷ��;���
		//������ name�� �ҷ��ͼ� ����Ʈ���忡�� ***���� ������û�� �ٿ�����
		//������ �޺��ڽ��� ���¸� ���� ȭ��ǥ��
		//�׸��� ���ö����� ����� �������� belong=before
		//������
		String sql ="SELECT a.apply_id AS \"num\", u.name AS \"name\" ,u.id AS \"id\", l.name AS \"lecName\""
				+ ", a.apply_date AS \"applyDate\", u.belong AS \"belong\""
				+ "FROM lecture as l "
				+ "JOIN user01 as u "
				+ "on a.user_id=u.user_id "
				+ "JOIN apply as a "
				+ "on a.lecture_id=l.lecture_id "
				+ "WHERE a.lecture_id = ? AND "
				+ "u.belong=\"before\""
				+ "ORDER BY a.apply_date";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, lectureId);
			rs = pstmt.executeQuery();
			if(rs.next()){
				RegisterDto bean = new RegisterDto();
				bean.setApplyDate(rs.getDate("applyDate"));
				bean.setId(rs.getString("id"));
				bean.setLecName(rs.getString("lecName"));
				bean.setMaxStd(rs.getInt("maxStd"));
				bean.setName(rs.getString("name"));
				bean.setNum(rs.getInt("num"));
				bean.setNumStd(rs.getInt("numStd"));
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
	
	//������ �л����� ���������� ���
	public int CheckRegister() {
		
		//����� ���۵ƴ��� �ȵƴ����� int������ ����
		return 0;
	}
	
	//������ �л����� ��������
	public ArrayList<RegisterDto> DetailRegister() {
		return null;
	}
	
	//������ �л����� �������� ����
	public int UpdateRegister() {
		//����� ���۵ƴ��� �ȵƴ����� int������ ����
		return 0;
	}
	
	//������ �л����� �������� ����
	public int DeleteRegister() {
		//����� ���۵ƴ��� �ȵƴ����� int������ ����
		return 0;
	}
	
	//������ ���������� �����
	
	
	//������ ���������� ����
	
	
}
