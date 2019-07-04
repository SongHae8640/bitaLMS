package com.bit.model;

import java.sql.Connection;
import java.sql.Date;
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
		ArrayList<LectureDto> list = new ArrayList<LectureDto>();
		
		String sql ="SELECT lecture_id, name, TO_CHAR(start_date,'yyyymmdd') as \"endDate\", TO_CHAR(end_date,'yyyymmdd') as \"startDate\", num_std, total_days, max_std, lv, content, is_close, file_name from lecture";
		
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()){
				LectureDto bean = new LectureDto();
				bean.setContent(rs.getString("content"));
				bean.setEndDate(rs.getString("endDate"));
				bean.setFileName(rs.getString("file_name"));
				bean.setIsClose(rs.getString("is_close"));
				bean.setLectureID(rs.getInt("lecture_id"));
				bean.setLv(rs.getInt("lv"));
				bean.setMaxStd(rs.getInt("max_std"));
				bean.setName(rs.getString("name"));
				bean.setNumStd(rs.getInt("num_std"));
				bean.setStartDate(rs.getString("startDate"));
				bean.setTotalDays(rs.getInt("total_days"));
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
	public ArrayList<RegisterDto> getRegister() {
		
		//��ȣ (��������) ID �̸� ���� ��¥ ����, ����ο�/�ִ��ο�, �Ҽ��� �ҷ��;���
		//������ name�� �ҷ��ͼ� ����Ʈ���忡�� ***���� ������û�� �ٿ�����
		//������ �޺��ڽ��� ���¸� ���� ȭ��ǥ��
		//�׸��� ���ö����� ����� �������� belong=before
		//SELECT apply_id as "num", u.name AS "name" ,u.user_id AS "id", l.name AS "lecName", TO_CHAR(a.apply_date,'yyyymmdd') AS "applyDate", u.belong AS "belong" FROM apply a INNER JOIN user01 u on a.user_id=u.user_id INNER JOIN lecture l on l.lecture_id = a.lecture_id
		//WHERE a.lecture_id = 1 ORDER BY a.apply_date;
		String sql = "SELECT apply_id as \"num\", u.name as \"name\", u.user_id AS \"id\", l.name AS \"lecName\", "
		+"TO_CHAR(a.apply_date,'yyyymmdd') AS \"applyDate\", u.belong AS \"belong\" "
		+"FROM apply a INNER JOIN user01 u on a.user_id=u.user_id "
		+"INNER JOIN lecture l on l.lecture_id = a.lecture_id "
		+"ORDER BY apply_id desc";
		//WHERE a.lecture_id = ? 
		System.out.println(sql);
		try {
			pstmt = conn.prepareStatement(sql);
//			pstmt.setInt(1, lectureId);
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				RegisterDto bean = new RegisterDto();
				bean.setApplyDate(rs.getString("applyDate"));
				bean.setId(rs.getString("id"));
				bean.setLecName(rs.getString("lecName"));
				bean.setName(rs.getString("name"));
				bean.setNum(rs.getInt("num"));
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
		//�ش� ���� ���ڷ� �޾ƿͼ� insert
		
		//����� ���۵ƴ��� �ȵƴ����� int������ ����
		return 0;
	}
	
	//������ �л����� ��������
	public ArrayList<RegisterDto> DetailRegister() {
		//���õ� �л� ���ڰ����� �޾ƿ���
		
		
		String sql = "SELECT";
		
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
	
	
	//������ ���°��� ���������
	
	//������ ���°��� ��������
	
	//������ ������� ���������
	
	//������ ������� ��������

}
