package com.bit.model;

import java.util.ArrayList;

public class StudentDao {

	public ArrayList<CalendarDto> getCalendarList(int lecture_id, String yearMonth) {
		//���� ����Ʈ�� �����ϴ� �޼���
		
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

	public SubmsissionDto getSubmission(String assignmentId, String userId) {
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




	

}
