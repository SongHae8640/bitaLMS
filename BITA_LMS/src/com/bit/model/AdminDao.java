package com.bit.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AdminDao {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:xe";
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
	
	//������ �л����� �л���� ��� ������
	public ArrayList<RegisterDto> getRegister() {
		
		//��ȣ (��������) ID �̸� ���� ��¥ ����, ����ο�/�ִ��ο� �ҷ��;���
		//������ name�� �ҷ��ͼ� ����Ʈ���忡�� ***���� ������û�� �ٿ�����
		//������
		String sql ="SELECT a.apply_id AS \"num\", u.name AS \"name\" ,u.id AS \"id\", l.name AS \"lecName\""
				+ ", l.name AS \"lecName\""
				+ "FROM apply as a "
				+ "JOIN user01 as u "
				+ "on a.std_id=u.user_id "
				+ "JOIN lecture as l "
				+ "on a.std_id=u.user_id "
				+ "WHERE a.lecture_id = ? AND "
				+ "TO_Date(SYSDATE,'yyyymmdd')=TO_Date(a.day_time,'yyyymmdd') "
				+ "ORDER BY u.name";
		
		return null;
	}
}
