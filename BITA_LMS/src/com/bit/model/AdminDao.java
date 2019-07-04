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
	
	public static void main(String[] args) {
		AdminDao dao = new AdminDao();
		dao.getRegister();
	}
	
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
	public ArrayList<LectureDto> getLecture() {
		return null;
	}
	
	//������ �л����� �л���� ��� ������
	public ArrayList<RegisterDto> getRegister() {
		
		//��ȣ (��������) ID �̸� ���� ��¥ ����, ����ο�/�ִ��ο�, �Ҽ��� �ҷ��;���
		//������ name�� �ҷ��ͼ� ����Ʈ���忡�� ***���� ������û�� �ٿ�����
		//������ �޺��ڽ��� ���¸� ���� ȭ��ǥ��
		//�׸��� ���ö����� ����� �������� belong=before
		//������
		String sql ="SELECT a.apply_id AS \"num\", u.name AS \"name\" ,u.id AS \"id\", l.name AS \"lecName\""
				+ ", a.apply_date AS \"applyDate\", l.num_std AS \"numStd\", l.max_std AS \"maxStd\", u.belong AS \"belong\""
				+ "FROM lecture as l "
				+ "JOIN user01 as u "
				+ "on a.user_id=u.user_id "
				+ "JOIN apply as a "
				+ "on a.lecture_id=l.lecture_id "
				+ "WHERE a.lecture_id = ? AND "
				+ "u.belong=\"before\""
				+ "ORDER BY a.apply_date";
		
		System.out.println(sql);
		return null;
	}
}
