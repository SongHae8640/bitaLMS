package com.bit.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.json.simple.JSONArray;

public class StudentDao extends Dao{


	public AttendanceDto getAttendance(String userId) {
		// Student 출석상황에 필요한 정보 가져오기
		// 입실/지각/퇴실 정보 등 status, 입퇴실시간(where오늘,시분만 가져오기)
		AttendanceDto bean = new AttendanceDto();
		String sql = "select status,to_char(checkin_time,'hh24:mi') as \"checkinTime\","
				+ "to_char(checkout_time,'hh24:mi') as \"checkoutTime\" from attendance"
				+ " where to_char(day_time)=to_char(sysdate) and std_id=?";
		try {
			openConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userId);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				bean.setStatus(rs.getString("status"));
				bean.setCheckinTime(rs.getString("checkinTime"));
				bean.setCheckoutTime(rs.getString("checkoutTime"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConnection();
		}
		return bean;
	}

	public int getTotalDays(int lectureId) {
		// 총 수업 일수를 받아오는 메서드
		String sql = "SELECT total_days FROM user01 u " + "JOIN lectureUser lu ON u.user_id = lu.user_id "
				+ "JOIN lecture l ON lu.lecture_id  = l.lecture_id " + "WHERE l.lecture_id =?";
		int totalDays = -1;

		try {
			openConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, lectureId);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				totalDays = rs.getInt("total_days");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConnection();
		}
		return totalDays;
	}

	public int getProgressDays(int lectureId) {
		// 진행중인 수업 일 수를 반환하는 메서드
		String sql = "SELECT TRUNC(sysdate)-TRUNC(start_date) as\"progressDays\" FROM lecture WHERE lecture_id = ?";
		int progressDays = -1;
		try {
			openConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, lectureId);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				progressDays = rs.getInt("progressDays");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConnection();
		}
		return progressDays;
	}

	public int getAttendanceDays(String userId) {
		// 학생의 출석일수를 반환하는 메서드(지각, 조퇴 아님)
		// 강좌를 2번 듣거나 계정 삭제 후 동일 아이디로 회원 가입한 학생일 가능성도 있기 때문에 학생의 강좌에 해당하는 기간에서 검색
		String sql  = "SELECT count(*) as\"attendanceDays\" "
				+ "FROM attendance a JOIN lectureUser lu ON a.std_id = lu.user_id "
				+ "JOIN lecture l ON l.lecture_id = lu.lecture_id "
				+ "WHERE (a.status = '출석' OR a.status='공결') "
				+ "AND (a.day_time BETWEEN l.start_date AND l.end_date) "
				+ "AND a.std_id = ?";
		int attendanceDays = -1;

		try {
			openConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userId);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				attendanceDays = rs.getInt("attendanceDays");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConnection();
		}
		return attendanceDays;
	}

	public int getNewQnaLAnswerNum(String userId) {
		// 답변이 달렸으나 학생이 확인하지 않은 QnaL의 개수를 반환 하는 메서드
		String sql = "SELECT count(*) as\"newQnaLAnswerNum\" FROM qna_l " + "WHERE answer_content is not null "
				+ "AND is_check = 0 " + "AND std_id = ?";
		int newQnaLAnswerNum = -1;

		try {
			openConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userId);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				newQnaLAnswerNum = rs.getInt("newQnaLAnswerNum");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConnection();
		}
		return newQnaLAnswerNum;
	}

	public int getTotalQnaLNum(String userId) {
		// 학생이 QnaL에 올린 문의의 개수를 반환하는 메서드
		String sql = "SELECT count(*) as\"totalQnaLNum\" FROM qna_l WHERE std_id = ?";

		int totalQnaLNum = -1;
		try {
			openConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userId);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				totalQnaLNum = rs.getInt("totalQnaLNum");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConnection();
		}
		return totalQnaLNum;
	}

	public int[] getAttendanceStatusList(String userId) {
		// 결석 공결 외출 조퇴 지각 출석
		int[] statusList = new int[5];
		String sql = "select status, count(*) as \"count\""
				+ " from attendance "
				+ "where std_id=? group by status";
		try {
			openConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userId);
			rs = pstmt.executeQuery();
			int cnt = 0;
			while (rs.next()) {
				// 출석 지각 조퇴 외출 결석
				String temp = rs.getString("status");
				if (temp != null) {
					if (temp.equals("결석")) {
						statusList[4] = rs.getInt("count");
					} else if (temp.equals("외출")) {
						statusList[3] = rs.getInt("count");
					} else if (temp.equals("조퇴")) {
						statusList[2] = rs.getInt("count");
					} else if (temp.equals("지각")) {
						statusList[1] = rs.getInt("count");
					} else if (temp.equals("공결") || temp.equals("출석")) {
						statusList[0] += rs.getInt("count");
					}
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConnection();
		}
		return statusList;
	}

	//학생이 scoreBean에 담긴 자신의 점수를 가져 올 수 있음
	public ScoreDto getScoreBean(String userId) {
		ScoreDto scoreBean = new ScoreDto();
		String sql = "SELECT name,first_score,second_score,third_score,avg_score "
					+"from user01 u JOIN score s ON s.std_id=u.user_id WHERE user_id = ?";
		try {  
			openConnection();
			System.out.println("연결");
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userId);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				scoreBean.setName(rs.getString("name"));
				scoreBean.setFirstScore(rs.getInt("first_score"));
				scoreBean.setSecondScore(rs.getInt("second_score"));
				scoreBean.setThirdScore(rs.getInt("third_score"));
				scoreBean.setAvgScore(rs.getDouble("avg_score"));
			}
			System.out.println(scoreBean.toString());
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			closeConnection();
		}
		return scoreBean;
	}

	public ArrayList<AssignmentDto> getAssignmentList(int lectureId) {
		ArrayList<AssignmentDto> list = new ArrayList<AssignmentDto>();
		String sql = "SELECT row_number() OVER(ORDER BY write_date) num,assignment_id,title,"
				+ "TO_CHAR(write_date,'yyyy-mm-dd') AS write_date  " + "FROM assignment " + "WHERE lecture_id =?";
				System.out.println("list sql="+sql);		
		try {
			openConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, lectureId);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				AssignmentDto bean = new AssignmentDto();
//				SubmsissionDto bean_s=new SubmsissionDto();
				bean.setRowNum(rs.getInt("num"));
				bean.setAssignmentId(rs.getInt("assignment_id"));
				bean.setTitle(rs.getString("title"));
				bean.setWriteDate(rs.getString("write_date"));
//				bean.setWriteDate(rs,getString(""));
//				bean_s.setIsCheck(rs.getString("is_check"));
				list.add(bean);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConnection();
		}
		return list;

	}

	public AssignmentDto getAssignment(int assignmentId) {
		AssignmentDto bean = new AssignmentDto();
		SubmissionDto bean_s = new SubmissionDto();
		String sql = "SELECT title, name,TO_CHAR(write_date,'yyyy-mm-dd') as write_date ,content "
				+ "FROM lectureUser lu " + "JOIN user01 u ON lu.user_id = u.user_id "
				+ "JOIN assignment a ON a.lecture_id = lu.lecture_id "
				+ "WHERE u.belong = 'teacher' AND a.assignment_id = ?";
		System.out.println("assignment=" + sql);
		try {
			openConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, assignmentId);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				bean.setTitle(rs.getString("title"));
				bean.setWriter(rs.getString("name"));
				bean.setWriteDate(rs.getString("write_date"));
				bean.setContent(rs.getString("content"));
				bean.setAssignmentId(assignmentId);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConnection();
		}
		return bean;
	}
	
//	public AttachedFileDto getAttachedBean(AttachedFileDto attachedBean) {
//		AttachedFileDto attachedBean=new AttachedFileDto();
//		String sql="select "
//		
//		
//		return attachedBean;
//		
//	}

	public ArrayList<SubmissionDto> getSubmissionList(int assignmentId, String userId) {
		ArrayList<SubmissionDto> list = new ArrayList<SubmissionDto>();
		String sql = "SELECT row_number() OVER(ORDER BY submit_date) num, file_name,name as std_name ,original_name,"
				+ "TO_CHAR(submit_date,'yyyy-mm-dd') as submit_date,is_check ,assignment_id"
				+ "FROM submission s JOIN user01 u ON s.std_id = u.user_id join attached_file a on a.file_id=s.file_id"
				+ " WHERE assignment_id=? and std_id=?";
		System.out.println("submission sql=" + sql);
		try {
			openConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, assignmentId);
			pstmt.setString(2, userId);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				SubmissionDto bean = new SubmissionDto();
				bean.setAssignmentId(rs.getInt("assignment_id"));
				bean.setRowNum(rs.getInt("num"));
				bean.setFileName(rs.getString("original_name"));
				bean.setStdName(rs.getString("std_name"));
				bean.setSubmitDate(rs.getString("submit_date"));
				bean.setIsCheck(rs.getString("is_check")); // submission의
															// is_check 자료형이
															// char(1)이여서 여기서
															// 오류가 날 수도?
				System.out.println("stdName=" + bean.getStdName());
				list.add(bean);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConnection();
		}

		return list;
	}

	public int insertSubmission(UserDto userBean, SubmissionDto bean_s, AttachedFileDto fileBean) {
		int result1 = 0;
		int result2 = 0;
		String sql1 = "insert into Attached_File (file_id,file_group,original_name,file_name,file_extension,ref_date,reg_id) "
				+ "values (file_id_seq.nextval,'과제',?,?,?,sysdate,?)";
		String sql2 = "INSERT INTO submission(assignment_id, std_id,submit_date,file_id,is_check) VALUES(?,?,SYSDATE,file_id_seq.currval,'0')"; // reg
																																	// id?file
																																	// group?
		System.out.println("attached sql1=" + sql1);
		System.out.println("attached sql2=" + sql2);
		try {
			openConnection();
//			conn.setAutoCommit(false);
			pstmt = conn.prepareStatement(sql1);
//			pstmt.setString(1, fileBean.getFileGroup());
			pstmt.setString(1, fileBean.getOriginalName());
			pstmt.setString(2, fileBean.getFileName());
			pstmt.setString(3, fileBean.getFileExtension());
			pstmt.setString(4, userBean.getUserId());
			System.out.println("1");
			result1 = pstmt.executeUpdate();
			System.out.println("result1=" + result1);
			System.out.println("attached sql1=" + sql1);
			if (result1 == 1) {
				pstmt = conn.prepareStatement(sql2);
				pstmt.setInt(1, bean_s.getAssignmentId());
				pstmt.setString(2, bean_s.getStdName());
				System.out.println("result2=" + result2);
				System.out.println("attached sql2=" + sql2);
				result2=pstmt.executeUpdate();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

			closeConnection();
		}

		return result2;
	}

	public int deleteSubmission(int assignmentId, String userId) {
		openConnection();

		closeConnection();
		return 0;
	}

	public int updateSubmission(String assignmentId, String userId, String fileName) {
		openConnection();

		closeConnection();
		return 0;
	}

	public ArrayList<QnaLDto> getQnaList(String userId) {
		ArrayList<QnaLDto> list = new ArrayList<QnaLDto>();
		String sql = "SELECT row_number() OVER(ORDER BY is_check , write_date desc) num ,qnal_id, u.name, std_id, type, question_content,"
				+ "title, answer_content, TO_CHAR(write_date,'YYYY-MM-DD') as \"write_date\", is_check "
				+ "FROM qna_l l "
				+ "JOIN user01 u ON l.std_id = u.user_id "
				+ "WHERE std_id = ? "
				+ "ORDER BY is_check , write_date desc";
		
		
		try {
			openConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userId);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				QnaLDto bean = new QnaLDto();
				bean.setRowNum(rs.getInt("num"));
				bean.setQnaLId(rs.getInt("qnal_id"));
				bean.setStuId(rs.getString("std_id"));
				bean.setStdName(rs.getString("name"));
				bean.setTitle(rs.getString("title"));
				bean.setType(rs.getString("type"));
				bean.setQuestionContent(rs.getString("question_content"));
				bean.setAnswerContent(rs.getString("answer_content"));
				bean.setWriteDate(rs.getString("write_date"));
				bean.setIsCheck(rs.getString("is_check"));
				list.add(bean);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			closeConnection();
		}
		return list;
	}

	
	public QnaLDto getQnaL(int qnaId) {
		QnaLDto bean = new QnaLDto();
		String sql = "SELECT row_number() OVER(ORDER BY write_date) num ,qnal_id, u.name, std_id, type, question_content,"
				+ "title, answer_content, TO_CHAR(write_date,'YYYY-MM-DD') as \"write_date\", is_check "
				+ "FROM qna_l l "
				+ "JOIN user01 u ON l.std_id = u.user_id "
				+ "WHERE qnal_id = ?";
		
		try {
			openConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, qnaId);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				bean.setRowNum(rs.getInt("num"));
				bean.setQnaLId(rs.getInt("qnal_id"));
				bean.setStuId(rs.getString("std_id"));
				bean.setStdName(rs.getString("name"));
				bean.setTitle(rs.getString("title"));
				bean.setType(rs.getString("type"));
				bean.setQuestionContent(rs.getString("question_content"));
				bean.setAnswerContent(rs.getString("answer_content"));
				bean.setWriteDate(rs.getString("write_date"));
				bean.setIsCheck(rs.getString("is_check"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			closeConnection();
		}		
		return bean;
	}

	// 학생이 질문을 올리는 메서드
	// answer_content는 입력 하지 않기 때문에 null(이후에 answer_content가 not null 이고 is_check가
	// 0인걸로 new를 확인)
	public int insertQnaL(QnaLDto qnaLBean) {
		String sql = "INSERT INTO qna_l(qnal_id,std_id, type, title, question_content, write_date, is_check) "
				+ "VALUES(qnal_id_seq.nextval,?,?,?,?,SYSDATE,0)";
		int result = -1;
		try {
			openConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, qnaLBean.getStuId());
			pstmt.setString(2, qnaLBean.getType());
			pstmt.setString(3, qnaLBean.getTitle());
			pstmt.setString(4, qnaLBean.getQuestionContent());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConnection();
		}		
		return result;
	}

	public int updateQnaL(QnaLDto qnaLBean) {
		int result = -1;
		String sql = "UPDATE qna_l SET title =? , question_content=? WHERE qnal_id = ?";
		
		try {
			openConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, qnaLBean.getTitle());
			pstmt.setString(2, qnaLBean.getQuestionContent());
			pstmt.setInt(3, qnaLBean.getQnaLId());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			closeConnection();
		}
		return result;
	}

	public int deleteQnaL(String[] qnaId) {
		// String으로 받아서 여기서 int로 변환?
		// qna삭제 배열에 하나만 들어있으면 하나만삭제, 여러개면 여러개 삭제
		openConnection();

		closeConnection();
		return 0;
	}

	public int updateAttendance(String stuId, String check) {
		// 일괄적으로 insert는 AM 6시, 출석마감은 PM 11시에 되는걸로
		String sql = "";
		int result = 0;
		if (check.equals("입실")) {
			sql = "UPDATE attendance SET status = '입실', checkin_time=sysdate WHERE std_id = ? and to_char(day_time)=to_char(sysdate)";
		} else if (check.equals("퇴실")) {
			sql = "UPDATE attendance SET status = '퇴실', checkout_time=sysdate WHERE std_id = ? and to_char(day_time)=to_char(sysdate)";
		}
		try {
			openConnection();
			conn.setAutoCommit(false);
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, stuId);
			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			try {
				conn.commit();
				conn.setAutoCommit(true);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			closeConnection();
		}
		return result;
	}
	public JSONArray getAttendanceMonthListJson(String userId, String yearMonth) {
		JSONArray jArray = new JSONArray();
		String sql = "SELECT TO_CHAR(day_time,'YYYY-MM-DD') as \"day_time\" ,status, "
				+ "TO_CHAR(checkin_time, 'HH24:MI') as \"checkin_time\", "
				+ "TO_CHAR(checkout_time,'HH24:MI') as \"checkout_time\" "
				+ "FROM attendance "
				+ "WHERE std_id=? "
				+ "AND TO_CHAR(day_time,'YYYY-MM') = ?";
		System.out.println(sql);
		
		try {
			openConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userId);
			pstmt.setString(2, yearMonth);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				AttendanceDto bean = new AttendanceDto();
				bean.setDayTime(rs.getString("day_time"));
				bean.setStatus(rs.getString("status"));
				bean.setCheckinTime(rs.getString("checkin_time"));
				bean.setCheckoutTime(rs.getString("checkout_time"));
				System.out.println(bean.toString());
				jArray.add(bean.getJsonObject());
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			closeConnection();
		}
		
		return jArray;
	}

	public JSONArray getCalendarMonthListJson(int lectureId) {
		JSONArray jArray = new JSONArray();
		String sql = "SELECT c.calendar_id, c.lecture_id, c.title, c.content,l.name, "
				+ "TO_CHAR(c.start_date,'YYYY-MM-DD HH24:MI:SS') as \"start_date\" ,TO_CHAR(c.end_date,'YYYY-MM-DD HH24:MI:SS') as \"end_date\" "
				+ "FROM calendar c JOIN lecture l ON c.lecture_id = l.lecture_id "
				+ "WHERE c.lecture_id = ? ";;	
		try {
			openConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, lectureId);
			rs = pstmt.executeQuery();	
			while(rs.next()){
				CalendarDto bean = new CalendarDto();
				bean.setCalendarId(rs.getInt("calendar_id"));
				bean.setLectureId(rs.getInt("lecture_id"));
				bean.setTitle(rs.getString("title"));
				bean.setContent(rs.getString("content"));
				bean.setStartDate(rs.getString("start_date").replaceAll(" ", "T"));
				bean.setEndDate(rs.getString("end_date").replaceAll(" ", "T"));
				bean.setLectureName(rs.getString("name"));
				jArray.add(bean.getJsonObject());
			}
				
		} catch (SQLException e) {
			System.out.println(e);
		}catch (NullPointerException e) {
			System.out.println(e);
		}finally{
			closeConnection();
		}
		return jArray;
	}
}