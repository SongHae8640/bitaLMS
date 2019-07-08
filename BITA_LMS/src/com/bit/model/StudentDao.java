package com.bit.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class StudentDao {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@192.168.1.7:1521:xe";
	String user = "bita";
	String password = "bita";
	
	Connection conn;
	PreparedStatement pstmt;
	ResultSet rs;
	
	public StudentDao(){
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url,user,password);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
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

	public AttendanceDto getAttendance(String userId) {
		// �ش� �л��� �⼮ ������ �����ϴ� �޼���
		
		return null;
	}

	public int getTotalDays(int lecture_id) {
		// ���� �� �� ���� �����ϴ� �޼���
		return -1;
	}
	public int getProgressDays(int lecture_id) {
		// ���� ���� ��Ȳ�� �����ϴ� �޼���
		return -1;
	}

	public int getAttendanceDays(String userId) {
		// �⼮�� �ϼ��� �����ϴ� �޼��� (���� �ƴ� ���� ���� �⼮��)
		return -1;
	}

	public int getQnaNum(String userId) {
		// �ش� �л��� ������ qna�� ������ �����ϴ� �޼���
		
		return -1;
	}

	public int[] getAttendanceStatusList(String userId) {
		// �Խ�, ����, ����, ����, �Ἦ�� int�� �迭�� �����ϴ� �޼���
		
		return null;
	}

	public ArrayList<AttendanceDto> getAttendanceMonthList(String userId, String yearMonth) {
		// �ش� ���, �����id �� �´� �⼮ ������ list�� �����ϴ� �޼���
		// ��¥, �⼮ ����, �Խǽð�, ��ǽð��� list�� 
		// yearMonth�� null�̸� ���� ���� ���� ã����
		return null;
	}

	public ScoreDto getScoreBean(String userId) {
		// �� ������ number�������� ����� �Ҽ������� �̷���� �ֱ� ������ ,�� ���еǴ� String ���� ����
		
		return null;
	}

	public ArrayList<AssignmentDto> getAssignmentList(int lecture_id) {
		// ���� ����Ʈ�� �����ϴ� �޼���
		// �� ��ȣ, ����, ��¥, Ȯ�� ����?? �� 
		
		return null;
	}

	public AssignmentDto getAssignmentDetail(String userId) {
		// ������ �� ������ �����ϴ� �޼���
		
		return null;
	}

	public SubmsissionDto getSubmissionBean(String assignmentId, String userId) {
		// (����) ���� ���� ������ �����ϴ� �޼���
		
		return null;
	}

	public int insertSubmission(int assignmentId, String userId) {
		// (����) ���� ������ DB�� �߰��ϴ� �޼���
		return -1;
	}

	public int deleteSubmission(int assignmentId, String userId) {
		// (����) ���� ������ DB�� �����ϴ� �޼���
		return 0;
	}

	public int updateSubmission(String assignmentId, String userId,
			String fileName) {
		// (����) ���� ���� ������ �����ϴ� �޼���
		return 0;
	}

	public ArrayList<QnaLDto> getQnaList(String userId) {
		// QNA ����Ʈ�� �����ϴ� �޼���(userId �� �ش��ϴ�)
		// �� ��ȣ, ����, 
		return null;
	}

	public int insertQnaL(String userId, String title, String type,
			String questionContent) {
		// QnaL�� �߰��ϴ� �޼���. type�� ���ؼ� reponder_id�� ����
		// ex) type�� '����'�̸� responder_id�� userId(std_id)�� �ش��ϴ� ������ user_id�� �ѱ�
		/// responder_id�� �����ϰ� type���θ��� ���� ������ �� �̾߱� �غ���!!
		return 0;
	}

	public QnaLDto getQnaBean(String qnaId) {
		// QanlBean�� �����ϴ� �޼���
		
		return null;
	}

	public int updateQnaL(int qnaId, String title, String type,
			String questionContent) {
		// qna�� �����ϴ� �޼���
		return 0;
	}

	public int deleteQnaL(String[] qnaId) {
		//String으로 받아서 여기서 int로 변환?
		// qna삭제 배열에 하나만 들어있으면 하나만삭제, 여러개면 여러개 삭제
		return 0;
	}

	public AssignmentDto getAssignmentBean(String assignmentId) {
		// ���� ��ü�� �����ϴ� �޼���
		return null;
	}

	public int updateAttendance(String stuId) {
		//일괄적으로 insert는 AM 6시, 출석마감은 PM 11시에 되는걸로
		//학생이 출석버튼 클릭시 시간에 맞춰 출석 처리
		return 0;
	}


}
