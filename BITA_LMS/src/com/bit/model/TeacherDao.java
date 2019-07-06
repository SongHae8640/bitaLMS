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
			//getAssignmentDetail 에서 conn를 close 하기 때문에 새로 연결
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
				bean.setIsCheck(rs.getString("is_check"));	//submission의 is_check 자료형이 char(1)이여서 여기서 오류가 날 수도?
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
				bean.setIsRespon(rs.getString("answer_content"));	///이게 맞으련지?
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
		// assignmnet_id 는 seq, write_date는 SYSDATE 로 INSERT
		return 0;
	}



	public int updateAssignment(String title, String content, String assingmentId) {
		// assignmentId로 접근하고 title, content의 내용 수정
		return 0;
	}

	public int deleteAssignment(int assignmentId) {
		// 과제 번호로 해당 과제 삭제	
		return 0;
	}

	public QnaLDto getQnaL(int qnaLId) {
		// 1:1문의로 해당 세부 내용 불러오기
		
		return null;
	}

	public int updateQnaLAnswer(String answerContent, int  qnaLId) {
		// 1:1문의에 answer_content(대답 내용) 추가하기(DB상에서는 qna_l에 있는 row UPDATE)
		return 0;
	}

	public int insertAttendanceCheckin(String stdId) {
		//attendance table에 row를 생성하면서 SYSDATE 기준으로  입실(checkin)값을 넣는 메서드 

		
		return 0;
	}

	public int updateAttendanceCheckout(String stdId) {
		// 출석에서 해당  학생의 checkout 시간을 SYSDATE로 update
		return 0;
	}

	public int insertCalendar(String startDate, String endDate, String title,
			String content, int lectureId) {
		// lecture_id에 해당하는 일정 추가
		///end_date에 어떤 값을 넣어야 할지 고민
		return 0;
	}

	public ArrayList<CalendarDto> getCalendarList(int lectureId, String yearMonth) {
		//메인 화면에서 달력에 들어갈 일정 리스트를 가져오는 메서드
		//yearMonth가 null이면(달력 월 이동으로 들어온 경우가 아닌 경우) sysdate로, null이 아니면 해당 년월로
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
