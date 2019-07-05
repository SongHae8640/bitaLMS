package com.bit.model;

import java.util.ArrayList;

public class StudentDao {

	public ArrayList<CalendarDto> getCalendarList(int lecture_id, String yearMonth) {
		//일정 리스트를 리턴하는 메서드
		
		return null;
	}

	public AttendanceDto getAttendance(String userId) {
		// 해당 학생의 출석 정보를 리턴하는 메서드
		
		return null;
	}

	public int getTotalDays(int lecture_id) {
		// 수업 총 일 수를 리턴하는 메서드
		return -1;
	}
	public int getProgressDays(int lecture_id) {
		// 수업 진행 현황을 리턴하는 메서드
		return -1;
	}

	public int getAttendanceDays(String userId) {
		// 출석한 일수를 리턴하는 메서드 (지각 아님 공결 포함 출석임)
		return -1;
	}

	public int getQnaNum(String userId) {
		// 해당 학생이 질문한 qna의 개수를 리턴하는 메서드
		
		return -1;
	}

	public int[] getAttendanceStatusList(String userId) {
		// 입실, 지각, 조퇴, 외출, 결석을 int형 배열로 리턴하는 메서드
		
		return null;
	}

	public ArrayList<AttendanceDto> getAttendanceMonthList(String userId, String yearMonth) {
		// 해당 년월, 사용자id 에 맞는 출석 정보를 list로 리턴하는 메서드
		// 날짜, 출석 상태, 입실시간, 퇴실시간을 list로 
		// yearMonth가 null이면 오늘 기준 월로 찾을것
		return null;
	}

	public ScoreDto getScoreBean(String userId) {
		// 각 성적은 number형이지만 평균은 소숫점으로 이루어져 있기 때문에 ,로 구분되는 String 형을 받음
		
		return null;
	}

	public ArrayList<AssignmentDto> getAssignmentList(int lecture_id) {
		// 과제 리스트를 리턴하는 메서드
		// 글 번호, 제목, 날짜, 확인 여부?? 흠 
		
		return null;
	}

	public AssignmentDto getAssignmentDetail(String userId) {
		// 과제의 상세 정보를 리턴하는 메서드
		
		return null;
	}

	public SubmsissionDto getSubmission(String assignmentId, String userId) {
		// (과제) 제출 세부 내용을 리턴하는 메서드
		
		return null;
	}

	public int insertSubmission(int assignmentId, String userId) {
		// (과제) 제출 내용을 DB에 추가하는 메서드
		return -1;
	}

	public int deleteSubmission(int assignmentId, String userId) {
		// (과제) 제출 내용을 DB에 제거하는 메서드
		return 0;
	}

	public int updateSubmission(String assignmentId, String userId,
			String fileName) {
		// (과제) 제출 파일 내용을 변경하는 메서드
		return 0;
	}

	public ArrayList<QnaLDto> getQnaList(String userId) {
		// QNA 리스트를 리턴하는 메서드(userId 에 해당하는)
		// 글 번호, 제목, 
		return null;
	}

	public int insertQnaL(String userId, String title, String type,
			String questionContent) {
		// QnaL을 추가하는 메서드. type을 통해서 reponder_id를 결정
		// ex) type이 '성적'이면 responder_id를 userId(std_id)에 해당하는 강사의 user_id로 넘김
		/// responder_id를 삭제하고 type으로만도 구분 가능할 듯 이야기 해보자!!
		return 0;
	}

	public QnaLDto getQnaBean(String qnaId) {
		// QanlBean을 리턴하는 메서드
		
		return null;
	}

	public int updateQnaL(int qnaId, String title, String type,
			String questionContent) {
		// qna를 수정하는 메서드
		return 0;
	}




	

}
