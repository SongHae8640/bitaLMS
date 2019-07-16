package com.bit.model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;


import org.json.simple.JSONArray;

public class TeacherDao extends Dao{
	private int result;

	//번호 (제목제외) ID 이름 강좌 날짜 과정, 등록인원/최대인원
	//이름,버튼(jquery에서),상태,아이디(hidden) where 오늘 날짜일때, 강좌아이디가 해당 아이디일 때
	public ArrayList<AttendanceDto> getTodayAttendance(int lectureId) {
		ArrayList<AttendanceDto> list = new ArrayList<AttendanceDto>();
		String sql ="SELECT name,std_id,status,day_time "
				+ "FROM attendance a "
				+ "JOIN user01 u "
				+ "ON a.std_id = u.user_id "
				+ "WHERE a.lecture_id=? "
//				+ "AND TO_CHAR(To_date('2019-07-09'),'yyyymmdd')=TO_CHAR(a.day_time,'yyyymmdd')";
				+ "AND TO_CHAR(SYSDATE,'yyyymmdd')=TO_CHAR(a.day_time,'yyyymmdd')"
				+ "ORDER BY name";

		try {
			openConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, lectureId);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				AttendanceDto bean = new AttendanceDto();
				bean.setDayTime(rs.getString("day_time"));
				bean.setName(rs.getString("name"));
				bean.setStatus(rs.getString("status"));
				bean.setStdId(rs.getString("std_id"));
				list.add(bean);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConnection();
		}
		return list;
	}

	// 강좌번호와 월을 파라미터로 주고 학생이름, 출석상태, 날짜를 리턴 받는다.
	// 출결 관리(월별) 페이지에서 보여지는 0000-00의 값을 yyyymm 형태의 문자열로 받는다.
	public ArrayList<AttendanceDto> getMonthAttendance(int lectureId,
			String yyyymm) {
		openConnection();
		ArrayList<AttendanceDto> list = new ArrayList<AttendanceDto>();
		String sql="";
		
		sql = "select u.name, a.std_id, to_char(a.day_time,'dd') as \"day\","
					+ " SUBSTR(a.status, 1, 1) as \"status\" from attendance a inner join user01 u on u.user_id=a.std_id"
					+ " where to_date(trunc(day_time,'mm'))=to_date(?) and a.lecture_id=?"
					+ " order by u.name, a.day_time";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, yyyymm+"-01");
			pstmt.setInt(2, lectureId);
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				AttendanceDto bean = new AttendanceDto();
				bean.setName(rs.getString("name"));
				bean.setDayTime(rs.getString("day"));
				bean.setStatus(rs.getString("status"));
				list.add(bean);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			closeConnection();
		}
		return list;
	}

	// / 입실 버튼을 누르면 바로 이벤트를 발생 시킬 것인지? 도영이형이라 이야기 할것

	// /////////////////////////////////////////
	// 성적

	// 강좌번호(lectureId)에 해당하는 학생들의 성적을 가져오는 메서드
	public ArrayList<ScoreDto> getScoreList(int lectureId) {
		ArrayList<ScoreDto> list = new ArrayList<ScoreDto>();
		String sql = "SELECT name, first_score, second_score,third_score,avg_score "
				+ "FROM score s JOIN user01 u "
				+ "ON s.std_id=u.user_id "
				+ "WHERE lecture_id = ? " + "ORDER BY name";
		try { 
			openConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, lectureId);		
			rs = pstmt.executeQuery();
			while(rs.next()) {   
				ScoreDto bean = new ScoreDto();
				bean.setName(rs.getString("name"));
				bean.setFirstScore(rs.getInt("first_score"));
				bean.setSecondScore(rs.getInt("second_score"));
				bean.setThirdScore(rs.getInt("third_score"));
				bean.setAvgScore(rs.getDouble("avg_score"));
				list.add(bean);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConnection();
		}
		return list; 
	}

	public int insertScore(String[] name_arr,int[] score_num_arr,int lectureId,int count) {
		String sql=""; //몇번째 시험점수를 입력하느냐에따라 경우가 나뉨
		String name="";
		int score=0;
		int result=0;
		if(count==1) {
			sql = "UPDATE score a SET a.first_score=?"
				+"WHERE a.std_id in(SELECT b.user_id FROM user01 b WHERE lecture_id=? and name=?)";
		}else if(count==2) {
			sql = "UPDATE score a SET a.second_score=?"
					+"WHERE a.std_id in(SELECT b.user_id FROM user01 b WHERE lecture_id=? and name=?)";
		}else if(count==3) {
			sql = "UPDATE score a SET a.third_score=?"
					+"WHERE a.std_id in(SELECT b.user_id FROM user01 b WHERE lecture_id=? and name=?)";
		}
		try { 
			openConnection();
			conn.setAutoCommit(false);
			for(int i = 0;i<score_num_arr.length;i++) {
				name=name_arr[i];
				score=score_num_arr[i];
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, score);
			pstmt.setInt(2,lectureId);
			pstmt.setString(3,name); 
			result = pstmt.executeUpdate();
			}
		} catch (SQLException e) {
			e.printStackTrace();
	        if (conn != null) {
	            try {
	                conn.rollback();
	            } catch(SQLException excep) {
	            	e.printStackTrace();
	            }
	        }
			} finally {
				closeConnection();
			}
			return result;
		}
	
	public int updateAvgscore(String[] name_arr,int[] score_num_arr1,int[] score_num_arr2, int[] score_num_arr3, int lectureId) {
		String name="";
		int score1=0;
		int score2=0;
		int score3=0;
		int count = 3;
		double avg = 0;
		int total = 0;
		int result=0;
		String sql = "UPDATE score a SET a.avg_score=?"
					+"WHERE a.std_id in(SELECT b.user_id FROM user01 b WHERE lecture_id=? and name=?)";
		try {
			openConnection();
			conn.setAutoCommit(false);
			for(int i = 0;i<name_arr.length;i++) {
				score1=score_num_arr1[i];
				score2=score_num_arr2[i];
				score3=score_num_arr3[i];
				name=name_arr[i];
				total=score1+score2+score3;
				if(score1==0){
					count+=-1;
				}else if(score2==0){
					count+=-1;
				}else if(score3==0){
					count+=-1;
				}
				avg=total/count;
				pstmt = conn.prepareStatement(sql);
				pstmt.setDouble(1,avg);
				pstmt.setInt(2,lectureId);
				pstmt.setString(3, name);
				result = pstmt.executeUpdate();
			}	
	} catch (SQLException e) {
		e.printStackTrace();
        if (conn != null) {
            try {
                conn.rollback();
            } catch(SQLException excep) {
            	e.printStackTrace();
            }
	        }
		} finally{
			closeConnection();
			try {
				conn.setAutoCommit(true);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
			return result;
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
				bean.setRowNum(rs.getInt("num"));
				bean.setAssignmentId(rs.getInt("assignment_id"));
				bean.setTitle(rs.getString("title"));
				bean.setWriteDate(rs.getString("write_date"));
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
				bean.setAssignmentId(assignmentId);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConnection();
		}
		return bean;
	}

	public ArrayList<SubmissionDto> getSubmissionList(int assignmentId) {
		ArrayList<SubmissionDto> list = new ArrayList<SubmissionDto>();
		String sql = "SELECT row_number() OVER(ORDER BY submit_date) num, file_name,name as std_name,"
				+ "TO_CHAR(submit_date,'yyyy-mm-dd') as submit_date,is_check,std_id "
				+ "FROM submission s JOIN user01 u ON s.std_id = u.user_id join attached_file a on a.file_id=s.file_id"
				+ " WHERE assignment_id=?";
		try {
			openConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, assignmentId);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				SubmissionDto bean = new SubmissionDto();
				bean.setRowNum(rs.getInt("num"));
				bean.setFileName(rs.getString("file_name"));
				bean.setStdName(rs.getString("std_name"));
				bean.setSubmitDate(rs.getString("submit_date"));
				bean.setIsCheck(rs.getString("is_check")); 
				list.add(bean);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConnection();
		}

		return list;
	}

	public ArrayList<QnaLDto> getQnaLList(int lectureId) {
		ArrayList<QnaLDto> list = new ArrayList<QnaLDto>();
		String sql = "SELECT row_number() OVER(ORDER BY write_date) num ,q.qnal_id, u.name, type, "
				+ "q.title, TO_CHAR(write_date,'YYYY-MM-DD') as \"write_date\", is_check "
				+ "FROM lecture l "
				+ "JOIN lectureUser lu ON l.lecture_id = lu.lecture_id "
				+ "JOIN qna_l q ON lu.user_id = q.std_id "
				+ "JOIN user01 u ON q.std_id = u.user_id "
				+ "WHERE l.lecture_id =? AND (q.type='성적문의' OR q.type='강사' OR q.type='이의신청')";

		try {
			openConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, lectureId);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				QnaLDto bean = new QnaLDto();
				bean.setRowNum(rs.getInt("num"));
				bean.setQnaLId(rs.getInt("qnal_id"));;
				bean.setStdName(rs.getString("name"));
				bean.setTitle(rs.getString("title"));
				bean.setType(rs.getString("type"));
				bean.setWriteDate(rs.getString("write_date"));
				bean.setIsCheck(rs.getString("is_check"));
				list.add(bean);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConnection();
		}

		return list;
	}

	public int insertAssignment(AssignmentDto assignmentBean) {
		int result=0;
		// assignmnet_id 는 seq, write_date는 SYSDATE 로 INSERT
		String sql = "insert into assignment(assignment_id, title, content, lecture_id, write_date)";
		sql += "values(assignment_id_seq.nextval,?,?,?,SYSDATE)";
		result = -1;
		try{
			openConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, assignmentBean.getTitle());
			pstmt.setString(2, assignmentBean.getContent());
			pstmt.setInt(3, assignmentBean.getLectureId());
			result=pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			closeConnection();
			
		}		
		return result;
	}

	public int updateAssignment(String title, String content,
			String assingmentId) {
		// assignmentId로 접근하고 title, content의 내용 수정
		openConnection();

		closeConnection();
		return 0;
	}

	public int deleteAssignment(int assignmentId) {
		// 과제 번호로 해당 과제 삭제
		int result1=0;
		int result2=0;
		String sql="delete from submission where assignment_id=? ";
		String sql1="delete from assignment where assignment_id=?";
		try {
			conn.setAutoCommit(false);
			openConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, assignmentId);
			result1=pstmt.executeUpdate();
			if(result1==1) {
				pstmt = conn.prepareStatement(sql1);
				pstmt.setInt(1, assignmentId);
				result2=pstmt.executeUpdate();
			}else{
				pstmt = conn.prepareStatement(sql1);
				pstmt.setInt(1, assignmentId);
				result2=pstmt.executeUpdate();
			}
			conn.commit();
			
		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				conn.setAutoCommit(true);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			closeConnection();			
		}
		return result2;
	}

	// 1:1문의로 해당 세부 내용 불러오기
	public QnaLDto getQnaL(int qnaLId) {
		QnaLDto bean = new QnaLDto();
		String sql ="SELECT qnal_id, u.name, std_id, type, question_content, title, "
				+ "answer_content, TO_CHAR(write_date,'YYYY-MM-DD') as \"write_date\", is_check "
				+ "FROM qna_l q "
				+ "JOIN user01 u ON q.std_id = u.user_id "
				+ "WHERE q.qnal_id = ? ";
		
		try{
			openConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, qnaLId);
			rs = pstmt.executeQuery();
			if(rs.next()) {
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
			e.printStackTrace();
		}finally{
			closeConnection();
		}
		return bean;
	}

	public int updateQnaL(QnaLDto qnaLBean) {
		
		// 1:1문의에 answer_content(대답 내용) 추가하기(DB상에서는 qna_l에 있는 row UPDATE)
		int result = -1;
		String sql = "UPDATE qna_l SET answer_content = ?, is_check='1' WHERE qnal_id =?";
		try{
			openConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, qnaLBean.getAnswerContent());
			pstmt.setInt(2, qnaLBean.getQnaLId());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			closeConnection();
		}
		return result;
	}


	public int updateAttendance(String stdId, String btn) {
		int result = 0;
		String sql="";
		if(btn.equals("checkin")){
			sql = "UPDATE attendance SET status = '입실', checkin_time = sysdate ";
		}else if(btn.equals("checkout")){
			sql = "UPDATE attendance SET status = '퇴실', checkout_time = sysdate ";
		}
		sql += "WHERE std_id = ? and to_Char(day_time,'yyyymmdd')=to_Char(sysdate,'yyyymmdd')";
		
		try {
			openConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, stdId);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			closeConnection();
		}
		
		return result;

	}


	public ArrayList<CalendarDto> getCalendarMonthList(int lectureId,
			String yearMonth) {
		ArrayList<CalendarDto> list = new ArrayList<CalendarDto>();

		String sql = "";
		if (yearMonth == null) {
			// int calendarId, lectureId;
			// String title, content, startDate, endDate;
			sql = "select calendar_id,lecture_id,title,start_date,end_date from calendar where calendar_id=to_number(to_char(sysdate,'mm'))";
		} else {
			sql = "select calendar_id,lecture_id,title,start_date,end_date from calendar where calendar_id=?";
		}
		try {
			openConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				CalendarDto bean = new CalendarDto();
				list.add(bean);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConnection();
		}
		return list;
	}


	public int getStuNum(int lectureId) {
		// 학생수 반환 메서드
		openConnection();

		closeConnection();
		return -1;
	}

	public int getCheckinNum(int lectureId) {
		// 체크인(입실)한 학생수 반환 메서드
		openConnection();

		closeConnection();
		return -1;
	}

	public int getSubmissionIscheck(int assignmentId,String stdId) {
		//과제 확인 메서드
		SubmissionDto bean_s=new SubmissionDto();
		String sql="update submission set is_check='1' where assignment_id=? and std_id=?";
		try {
			openConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, assignmentId);
			pstmt.setString(2, stdId);
			result=pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			closeConnection();			
		}
		return result;
	}
	
	public SubmissionDto getSubmissionNum(int lectureId) {
		// 과제 제출(submission)한 학생수 반환 메서드
		// 가장 최근에 낸 과제(assingment)에 제출한



		SubmissionDto bean =new SubmissionDto();
		ArrayList<SubmissionDto> list=new ArrayList<SubmissionDto>();
		openConnection();
		String sql="SELECT row_number() OVER(ORDER BY write_date) num, title, name as std_name,"
				+ "TO_CHAR(write_date,'yyyy-mm-dd') as write_date ,answer_content, type "
				+ "FROM qna_l ql JOIN user01 u ON ql.std_id = u.user_id "
				+ "WHERE responder_id = ?";	//수정해야함 
		try {
			openConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, "num");
			rs=pstmt.executeQuery();
			if(rs.next()){
				/*
				 * bean.setNum(rs.getInt("num")); bean.setSub(rs.getString("sub"));
				 * bean.setUnum(rs.getInt("unum")); bean.setName(rs.getString("name"));
				 * bean.setNalja(rs.getDate("nalja")); bean.setPay(rs.getInt("pay"));
				 */			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			closeConnection();
		}
		return bean;
		
		
	}

	public int getTotalDays(int lectureId) {
		// 해당 강좌의 총일수 반환 메서드
		openConnection();

		closeConnection();
		return -1;
	}

	public int getProgressDays(int lectureId) {
		// SYSDATE 기준으로 수업 진행 일수를 반환하는 메서드
		openConnection();

		closeConnection();
		return -1;
	}

	public int getLecture(String lectureId) {

		openConnection();

		closeConnection();

		return -1;

	}

	public int getQnaLNum(String userId) {

		openConnection();

		closeConnection();
		return -1;
	}

	public int getAssignmentDelete(int assignmentId) {
		// TODO Auto-generated method stub
		return 0;
	}


	public String getMonthAttendanceJson(ArrayList<AttendanceDto> monthAttendance) {
		JSONArray jArray = new JSONArray();
		if (monthAttendance != null) { 
            for (int i = 0; i < monthAttendance.size(); i++) {
                JSONObject jObject = new JSONObject();//배열 내에 들어갈 json
                jObject.put("name", monthAttendance.get(i).getName());
                jObject.put("dayTime", monthAttendance.get(i).getDayTime());
                jObject.put("status", monthAttendance.get(i).getStatus());
                jArray.add(jObject);
            }
        	} 
		
		return jArray.toJSONString();
  }
	public JSONArray getCalendarMonthListJson(int lectureId) {
		JSONArray jArray = new JSONArray();
		String sql = "SELECT c.calendar_id, c.lecture_id, c.title, c.content,l.name, "
				+ "TO_CHAR(c.start_date,'YYYY-MM-DD HH24:MI:SS') as \"start_date\" ,TO_CHAR(c.end_date,'YYYY-MM-DD HH24:MI:SS') as \"end_date\" "
				+ "FROM calendar c JOIN lecture l ON c.lecture_id = l.lecture_id "
				+ "WHERE c.lecture_id = ? ";
//				+ "AND TO_CHAR(c.start_date,'YYYY-MM') = ?";	
		try {
			openConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, lectureId);
//			pstmt.setString(2, yearMonth);
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
		}catch (NullPointerException e) {
		}finally{
			closeConnection();
		}
		return jArray;
	}

	public int insertCalendar(CalendarDto calendarBean) {
		String sql ="INSERT INTO calendar(calendar_id, title, content, start_date, end_date, lecture_id) "
				+ "VALUES(calendar_id_seq.NEXTVAL,?, ?, TO_DATE(?,'YYYY-MM-DD HH24:MI:SS'), TO_DATE(?,'YYYY-MM-DD HH24:MI:SS'),?)";
		int result = -1;
					
		try {
			openConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, calendarBean.getTitle());
			pstmt.setString(2, calendarBean.getContent());
			pstmt.setString(3, calendarBean.getStartDate());
			pstmt.setString(4, calendarBean.getEndDate());
			pstmt.setInt(5, calendarBean.getLectureId());	
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
				e.printStackTrace();

		}finally{
			closeConnection();
		}
		return result;
	}

	public int updateCalendar(CalendarDto calendarBean) {
		String sql =" UPDATE calendar "
				+ "SET title=?, content=?,  "
				+ "start_date= TO_DATE(?,'YYYY-MM-DD HH24:MI:SS'), "
				+ "end_date = TO_DATE(?, 'YYYY-MM-DD HH24:MI:SS') "
				+ "WHERE calendar_id = ?";
		int result = -1;					
		try {
			openConnection();
			pstmt = conn.prepareStatement(sql); 
			pstmt.setString(1, calendarBean.getTitle());
			pstmt.setString(2, calendarBean.getContent());
			pstmt.setString(3, calendarBean.getStartDate());
			pstmt.setString(4, calendarBean.getEndDate());
			pstmt.setInt(5, calendarBean.getCalendarId());	
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
				e.printStackTrace();
		}finally{
			closeConnection();
		}
		return result;
	}

	//메인페이지 달력 일정 삭제하기
	public int deleteCalendar(int calendarId){
		String sql ="DELETE FROM Calendar WHERE calendar_id = ?";
		int result = -1;
					
		try {
			openConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, calendarId);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
				e.printStackTrace();
		}finally{
			closeConnection();
		}
		return 0;
	}
	public int deleteQnaL(int qnaLId) {
		String sql = "DELETE FROM qna_l WHERE qnal_id = ?";
		int result = -1;
		try{
			openConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, qnaLId);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			closeConnection();
		}
		return result;
	}

}
