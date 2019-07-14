﻿package com.bit.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.json.simple.JSONArray;

public class StudentDao extends Dao{

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
				openConnection();
				pstmt = conn.prepareStatement(sql);
				rs = pstmt.executeQuery();
				
				while(rs.next()){
					CalendarDto bean = new CalendarDto();		
					list.add(bean);
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			}finally{
				closeConnection();
			}
			return list;
	}
	
	public ArrayList<CalendarDto> getCalendarDayList(int lectureId, String yearMonthDay){
		openConnection();
		
		closeConnection();
		return null;
	}

	public AttendanceDto getAttendance(String userId) {
		//Student 출석상황에 필요한 정보 가져오기
		//입실/지각/퇴실 정보 등 status, 입퇴실시간(where오늘,시분만 가져오기)
		AttendanceDto bean = new AttendanceDto();
		String sql = "select status,to_char(checkin_time,'hh24:mi') as \"checkinTime\","
				+ "to_char(checkout_time,'hh24:mi') as \"checkoutTime\" from attendance"
				+ " where to_char(day_time)=to_char(sysdate) and std_id=?";
		
		try {
			openConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userId);
			rs = pstmt.executeQuery();
			if(rs.next()){
				bean.setStatus(rs.getString("status"));
				bean.setCheckinTime(rs.getString("checkinTime"));
				bean.setCheckoutTime(rs.getString("checkoutTime"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			closeConnection();
		}
		return bean;
	}

	public int getTotalDays(int lectureId) {
		// 총 수업 일수를 받아오는 메서드
		String sql = "SELECT total_days FROM user01 u "
				+ "JOIN lectureUser lu ON u.user_id = lu.user_id "
				+ "JOIN lecture l ON lu.lecture_id  = l.lecture_id "
				+ "WHERE l.lecture_id =?";
		int totalDays = -1;
		
		try {
			openConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, lectureId);
			rs = pstmt.executeQuery();
			if(rs.next()){
				totalDays = rs.getInt("total_days");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
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
			if(rs.next()){
				progressDays = rs.getInt("progressDays");
			}
		
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			closeConnection();
		}
		return progressDays;
	}

	public int getAttendanceDays(String userId) {
		// 학생의 출석일수를 반환하는 메서드(지각, 조퇴 아님)
		String sql  = "SELECT count(*) as\"attendanceDays\" FROM attendance WHERE std_id = ?";
		int attendanceDays = -1;
		
		try {
			openConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userId);
			rs = pstmt.executeQuery();
			if(rs.next()){
				attendanceDays = rs.getInt("attendanceDays");
			}
		
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			closeConnection();
		}
		return attendanceDays;
	}

	public int getNewQnaLAnswerNum(String userId) {
		// 답변이 달렸으나 학생이 확인하지 않은 QnaL의 개수를 반환 하는 메서드
		String sql = "SELECT count(*) as\"newQnaLAnswerNum\" FROM qna_l "
				+ "WHERE answer_content is not null "
				+ "AND is_check = 0 "
				+ "AND std_id = ?";
		int newQnaLAnswerNum = -1;
		
		try {
			openConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userId);
			rs = pstmt.executeQuery();
			if(rs.next()){
				newQnaLAnswerNum = rs.getInt("newQnaLAnswerNum");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			closeConnection();
		}
		return newQnaLAnswerNum;
	}
	public int getTotalQnaLNum(String userId){
		//학생이 QnaL에 올린 문의의 개수를 반환하는 메서드
		String sql = "SELECT count(*) as\"totalQnaLNum\" FROM qna_l WHERE std_id = ?";
		
		int totalQnaLNum = -1;
		try {
			openConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userId);
			rs = pstmt.executeQuery();
			if(rs.next()){
				totalQnaLNum = rs.getInt("totalQnaLNum");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			closeConnection();
		}
		return totalQnaLNum;
	}

	public int[] getAttendanceStatusList(String userId) {
		//결석 공결 외출 조퇴 지각 출석
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
			while(rs.next()){
				//출석 지각 조퇴 외출 결석
				String temp = rs.getString("status");
				if(temp!=null){
					if(temp.equals("결석")){
						statusList[4] = rs.getInt("count");
					}else if(temp.equals("외출")){
						statusList[3] = rs.getInt("count");
					}else if(temp.equals("조퇴")){
						statusList[2] = rs.getInt("count");
					}else if(temp.equals("지각")){
						statusList[1] = rs.getInt("count");
					}else if(temp.equals("공결")||temp.equals("출석")){
						statusList[0] += rs.getInt("count");
					}
				}
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			closeConnection();
		}
		return statusList;
	}

	public ScoreDto getScoreBean(String userId) {
		openConnection();
	
		closeConnection();
		return null;
	}


	public ArrayList<AssignmentDto> getAssignmentList(int lectureId) {
		ArrayList<AssignmentDto> list = new ArrayList<AssignmentDto>();
		String sql = "SELECT row_number() OVER(ORDER BY write_date) num,assignment_id,title,"
				+ "TO_CHAR(write_date,'yyyy-mm-dd') AS write_date  "
				+ "FROM assignment " + "WHERE lecture_id =?";

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
		String sql = "SELECT title, name,TO_CHAR(write_date,'yyyy-mm-dd') as write_date ,content "
				+ "FROM lectureUser lu "
				+ "JOIN user01 u ON lu.user_id = u.user_id "
				+ "JOIN assignment a ON a.lecture_id = lu.lecture_id "
				+ "WHERE u.belong = 'teacher' AND a.assignment_id = ?";
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
				bean.setAssignmentId(Integer.parseInt(rs.getString("user_id")));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConnection();
		}
		
		return bean;
	}

	public SubmsissionDto getSubmissionBean(int assignmentId, String userId) {
		openConnection();
		
		closeConnection();
		return null;
	}

	public int insertSubmission(int assignmentId, String userId) {
		openConnection();
		String sql="insert into Attached_File values(\"김코난\",\"파일그룹\",?,?,\"2019-08-01\",\"2019-08-02\",\"stu1\")"; //reg id?file group?

		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, "file_id");//int로 받아야함
			pstmt.setString(2, "file_group");
			pstmt.setString(3, "original_name");
			pstmt.setString(4, "file_name");
			pstmt.setString(5, "file_extension");
			pstmt.setString(6, "ref_date");
			pstmt.setString(7, "reg_id");
			int result=pstmt.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {

			closeConnection();
		}

		return -1;
	}

	public int deleteSubmission(int assignmentId, String userId) {
		openConnection();
		
		closeConnection();
		return 0;
	}

	public int updateSubmission(String assignmentId, String userId,
			String fileName) {
		openConnection();
		
		closeConnection();
		return 0;
	}

	public ArrayList<QnaLDto> getQnaList(String userId) {
		ArrayList<QnaLDto> list = new ArrayList<QnaLDto>();
		String sql = "SELECT row_number() OVER(ORDER BY write_date) num ,qnal_id, u.name, std_id, type, question_content,"
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

	//학생이 질문을 올리는 메서드
	//answer_content는 입력 하지 않기 때문에 null(이후에 answer_content가 not null 이고 is_check가 0인걸로 new를 확인)
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
		}finally{
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
		//String으로 받아서 여기서 int로 변환?
		// qna삭제 배열에 하나만 들어있으면 하나만삭제, 여러개면 여러개 삭제
		openConnection();
		
		closeConnection();
		return 0;
	}


	public int updateAttendance(String stuId,String check) {
		//일괄적으로 insert는 AM 6시, 출석마감은 PM 11시에 되는걸로
		
		String sql = "";
		int result = 0;
		if(check.equals("입실")){
			sql = "UPDATE attendance SET status = '입실', checkin_time=sysdate WHERE std_id = ? and to_char(day_time)=to_char(sysdate)";
		}else if(check.equals("퇴실")){
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
		}finally{
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
				+ "WHERE std_id=? AND "
				+ "TO_CHAR(day_time,'YYYY-MM') = ?";
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





}