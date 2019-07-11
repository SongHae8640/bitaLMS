package com.bit.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.catalina.startup.SetContextPropertiesRule;

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
		
		System.out.println(sql);
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
		openConnection();
		
		closeConnection();
		return -1;
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
		openConnection();
		
		closeConnection();
		return -1;
	}
	public int getTotalQnaLNum(String userId){
		//학생이 QnaL에 올린 문의의 개수를 반환하는 메서드
		openConnection();
		
		closeConnection();
		return -1;
	}

	public int[] getAttendanceStatusList(String userId) {
		//결석 공결 외출 조퇴 지각 출석
		int[] statusList = new int[5];
		String sql = "select status, count(*) as \"count\""
				+ " from attendance "
				+ "where std_id=? group by status";
		System.out.println(sql);
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

	public ArrayList<AttendanceDto> getAttendanceMonthList(String userId, String yearMonth) {
		openConnection();
		
		closeConnection();
		return null;
	}

	public ScoreDto getScoreBean(String userId) {
		openConnection();
		
		closeConnection();
		return null;
	}

	public ArrayList<AssignmentDto> getAssignmentList(int lecture_id) {
		openConnection();
		
		closeConnection();
		return null;
	}

	public AssignmentDto getAssignmentDetail(String userId) {
		openConnection();
		
		closeConnection();
		return null;
	}

	public SubmsissionDto getSubmissionBean(String assignmentId, String userId) {
		openConnection();
		
		closeConnection();
		return null;
	}

	public int insertSubmission(int assignmentId, String userId) {
		openConnection();
		
		closeConnection();
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
		openConnection();
		
		closeConnection();
		return null;
	}

	public int insertQnaL(String userId, String title, String type,
			String questionContent) {
		openConnection();
		
		closeConnection();
		return 0;
	}

	public QnaLDto getQna(String qnaId) {
		openConnection();
		
		closeConnection();
		return null;
	}

	public int updateQnaL(int qnaId, String title, String type,
			String questionContent) {
		openConnection();
		
		closeConnection();
		return 0;
	}

	public int deleteQnaL(String[] qnaId) {
		//String으로 받아서 여기서 int로 변환?
		// qna삭제 배열에 하나만 들어있으면 하나만삭제, 여러개면 여러개 삭제
		openConnection();
		
		closeConnection();
		return 0;
	}

	public AssignmentDto getAssignmentBean(String assignmentId) {
		openConnection();
		
		closeConnection();
		return null;
	}

	public int updateAttendance(String stuId,String check) {
		//일괄적으로 insert는 AM 6시, 출석마감은 PM 11시에 되는걸로
		System.out.println(check);
		
		String sql = "";
		int result = 0;
		if(check.equals("입실")){
			sql = "UPDATE attendance SET status = '입실', checkin_time=sysdate WHERE std_id = ? and to_char(day_time)=to_char(sysdate)";
		}else if(check.equals("퇴실")){
			sql = "UPDATE attendance SET status = '퇴실', checkout_time=sysdate WHERE std_id = ? and to_char(day_time)=to_char(sysdate)";
		}
		
		System.out.println(sql);
		
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


}