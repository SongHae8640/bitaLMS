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

	///////////////////////////////////////////
	//�⼮

	// ���¹�ȣ�� �Ķ���ͷ� �ְ� �л��̸�, �⼮����, ��¥�� ���� �޴´�.
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
				bean.setDayTime(rs.getString("day_time"));
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
	
	
	// ���¹�ȣ�� ���� �Ķ���ͷ� �ְ� �л��̸�, �⼮����, ��¥�� ���� �޴´�.
	// ��� ����(����) ���������� �������� 0000-00�� ���� yyyymm ������ ���ڿ��� �޴´�.
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
				bean.setDayTime(rs.getString("day_time"));
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
	
	/// �Խ� ��ư�� ������ �ٷ� �̺�Ʈ�� �߻� ��ų ������? ���������̶� �̾߱� �Ұ�

	
	///////////////////////////////////////////
	//����
	
	//���¹�ȣ(lectureId)�� �ش��ϴ� �л����� ������ �������� �޼���
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
				//��Ī�� ������� �ϴ��� ã�ƺ��� �ٽ� �Ұ�
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
		String sql = "SELECT row_number() OVER(ORDER BY write_date) num,assignment_id,title,"
				+ "TO_CHAR(write_date,'yyyy-mm-dd') AS write_date  "
				+ "FROM assignment "
				+ "WHERE lecture_id =?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, lectureId);
			rs = pstmt.executeQuery();
			while(rs.next()){
				AssignmentDto bean = new AssignmentDto();
				bean.setRowNum(rs.getInt("num"));
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
	
	public AssignmentDto getAssignment(int assignmentId) {
		AssignmentDto bean = new AssignmentDto();
		String sql = "SELECT title, name,TO_CHAR(write_date,'yyyy-mm-dd') as write_date ,content "
				+ "FROM lectureUser lu "
				+ "JOIN user01 u ON lu.user_id = u.user_id "
				+ "JOIN assignment a ON a.lecture_id = lu.lecture_id "
				+ "WHERE u.belong = 'teacher' AND a.assignment_id = ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, assignmentId);
			rs = pstmt.executeQuery();
			if(rs.next()){
				bean.setTitle(rs.getString("title"));
				bean.setWriter(rs.getString("name"));
				bean.setWriteDate(rs.getString("write_date"));
				bean.setContent(rs.getString("content"));
				bean.setAssignmentId(assignmentId);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				if(pstmt!=null)pstmt.close();
				if(conn!=null)conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return bean;
	}

	public ArrayList<SubmsissionDto> getSubmissionList(int assignmentId) {
		ArrayList<SubmsissionDto> list = new ArrayList<SubmsissionDto>();
		String sql = "SELECT row_number() OVER(ORDER BY submit_date) num, file_name,name as std_name ,"
				+ "TO_CHAR(submit_date,'yyyy-mm-dd') as submit_date,is_check "
				+ "FROM submission s JOIN user01 u ON s.std_id = u.user_id "
				+ "WHERE assignment_id=?";
		
		try {
			//getAssignmentDetail ���� conn�� close �ϱ� ������ ���� ����
			Class.forName(driver);
			conn = DriverManager.getConnection(url,user,password);
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, assignmentId);
			rs = pstmt.executeQuery();
			while(rs.next()){
				SubmsissionDto bean = new SubmsissionDto();
				bean.setRowNum(rs.getInt("num"));
				bean.setFileName(rs.getString("file_name"));
				bean.setStdName(rs.getString("std_name"));
				bean.setSubmitDate(rs.getString("submit_date"));
				bean.setIsCheck(rs.getString("is_check"));	//submission�� is_check �ڷ����� char(1)�̿��� ���⼭ ������ �� ����?
				list.add(bean);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
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

	public ArrayList<QnaLDto> getQnaLList(String teacherId) {
		ArrayList<QnaLDto> list = new ArrayList<QnaLDto>();
		String sql = "SELECT row_number() OVER(ORDER BY write_date) num, title, name as std_name,"
				+ "TO_CHAR(write_date,'yyyy-mm-dd') as write_date ,answer_content, type "
				+ "FROM qna_l ql JOIN user01 u ON ql.std_id = u.user_id "
				+ "WHERE responder_id = ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, teacherId);
			rs = pstmt.executeQuery();
			while(rs.next()){
				QnaLDto bean = new QnaLDto();
				bean.setRowNum(rs.getInt("num"));
				bean.setTitle(rs.getString("title"));
				bean.setStdName(rs.getString("std_name"));
				bean.setWriteDate(rs.getString("write_date"));
				bean.setIsRespon(rs.getString("answer_content"));	//�̰� ��������?
				bean.setType(rs.getString("type"));
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

	public int insertAssignment(String title, String content, int lecture_id) {
		// assignmnet_id �� seq, write_date�� SYSDATE �� INSERT
		return 0;
	}



	public int updateAssignment(String title, String content, String assingmentId) {
		// assignmentId�� �����ϰ� title, content�� ���� ����
		return 0;
	}

	public int getAssignmentDelete(int assignmentId) {
		// ���� ��ȣ�� �ش� ���� ����
		return 0;
	}

	public QnaLDto getQnaL(int qnaLId) {
		// 1:1���Ƿ� �ش� ���� ���� �ҷ�����
		
		return null;
	}

	public int updateQnaLAnswer(String answerContent, int  qnaLId) {
		// 1:1문의에 있는 Answer update
		return 0;
	}

	public int insertAttendanceCheckin(String stdId) {
		//attendance table에 row를 생성하면서 SYSDATE 기준으로  입실(checkin)값을 넣는 메서드 

		
		return 0;
	}

	public int updateAttendanceCheckout(String stdId) {
		// �⼮���� �ش�  �л��� checkout �ð��� SYSDATE�� update
		return 0;
	}

	public int insertCalendar(String startDate, String endDate, String title,
			String content, int lectureId) {
		// lecture_id�� �ش��ϴ� ���� �߰�
		///end_date�� � ���� �־�� ���� ���
		return 0;
	}

	public ArrayList<CalendarDto> getCalendarMonthList(int lectureId, String yearMonth){
		ArrayList<CalendarDto> list = new ArrayList<CalendarDto>();
		
		String sql = "";
		if(yearMonth==null){
			//int calendarId, lectureId;
			//String title, content, startDate, endDate;
			sql = "select calendar_id,lecture_id,title,start_date,end_date from calendar where calendar_id=to_number(to_char(sysdate,'mm')";
		}else{
			sql = "select calendar_id,lecture_id,title,start_date,end_date from calendar where calendar_id=?";
		}
			try {
				pstmt = conn.prepareStatement(sql);
				rs = pstmt.executeQuery();
				
				while(rs.next()){
					CalendarDto bean = new CalendarDto();		
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
	
	public ArrayList<CalendarDto> getCalendarDayList(int lectureId, String yearMonthDay){
		
		return null;
	}

	public int getStuNum(int lectureId) {
		//학생수 반환 메서드
		
		return -1;
	}

	public int getCheckinNum(int lectureId) {
		// 체크인(입실)한 학생수 반환 메서드
		
		return -1;
	}

	public int getSubmissionNum(int lectureId) {
		// 과제 제출(submission)한 학생수 반환 메서드
		// 가장 최근에 낸 과제(assingment)에 제출한 
		
		return -1;
	}

	public int getTotalDays(int lectureId) {
		//  해당 강좌의 총일수 반환 메서드
		return -1;
	}

	public int getProgressDays(int lectureId) {
		// SYSDATE 기준으로 수업 진행 일수를 반환하는 메서드
		return -1;
	}
}
