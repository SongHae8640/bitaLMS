package com.bit.model;



import java.sql.Date;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class AdminDao extends Dao {
	
	//메인페이지 달력 가져오기
	//다 가져와서 목록까지 저장하고 있다가 뿌려주기
	//제이쿼리로 숨겼다가 달력에 일 클릭하면 나타나는걸루
	//월별 수강생관리 페이지 월은 ?idx=""로 받아오기
	//제일 처음 접근일 때는 sysdate로 가져오기
	//날짜이동버튼을 누르면 제이쿼리에서 2019-07에서 -1을 하든 +1을 하든 해서 idx값으로 넘겨주기	
	public JSONArray getCalendarMonthListJson() {
		JSONArray jArray = new JSONArray();
		String sql = "SELECT c.calendar_id, c.lecture_id, c.title, c.content,l.name, "
				+ "TO_CHAR(c.start_date,'YYYY-MM-DD HH24:MI:SS') as \"start_date\" ,TO_CHAR(c.end_date,'YYYY-MM-DD HH24:MI:SS') as \"end_date\" "
				+ "FROM calendar c JOIN lecture l ON c.lecture_id = l.lecture_id ";
		try {
			openConnection();
			pstmt = conn.prepareStatement(sql);
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
	
	public JSONArray getCalendarMonthListJson(int lectureId) {
		JSONArray jArray = new JSONArray();
		String sql = "SELECT c.calendar_id, c.lecture_id, c.title, c.content,l.name, "
				+ "TO_CHAR(c.start_date,'YYYY-MM-DD HH24:MI:SS') as \"start_date\" ,TO_CHAR(c.end_date,'YYYY-MM-DD HH24:MI:SS') as \"end_date\" "
				+ "FROM calendar c JOIN lecture l ON c.lecture_id = l.lecture_id "
				+ "WHERE c.lecutre_id = ? ";
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
		}catch (NullPointerException e) {
		}finally{
			closeConnection();
		}
		return jArray;
	}
	
	public ArrayList<CalendarDto> getCalendarDayList(String yearMonthDay){
		
		try{
			openConnection();
		}finally{
			closeConnection();
		}
		return null;
	}
	
	
	//메인페이지 달력 상세 가져오기
	//상세를 누르면 모달창이 생성되게
	public CalendarDto getCalendar(String calendarId){
		openConnection();
		try{
			
		}finally{
			closeConnection();
		}

		return null;
	}
	
	//메인페이지 달력 일정 추가하기
	//추가를 누르면 추가모달창이 생성되게
	public int insertCalendar(CalendarDto calendarBean){
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
		return 0;
	}
	
	//메인페이지 달력 일정 수정하기
	//수정을 누르면 상세 모달창은 숨겨지고 수정모달창이 생성되게
	public int updateCalendar(CalendarDto calendarBean){
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
	
	//메인페이지 유저정보랑 신청현황 문의현황 가져오기
	//신청현황은 그냥 신청온거(현재 apply테이블에 있는 로우 카운트수/apply_id의 max값)
	//문의현황은 총문의수(count)-답변달린거/총문의수(count)
	public UserDto getUser(String userId){
		openConnection();
		try{
			
		}finally{
			closeConnection();
		}
		return null;
	}
	
	//콤보박스 정렬 및 수강인원/최대인원 가져오기
	//목록이나 상세에서는 조인이나 조건문을 통해 필요한 것들만 가져오게 되므로 따로 빼준다
	//20190709 am 11:23 되는지 확인
	public ArrayList<LectureDto> getArrangeLectureList() {
		openConnection();
		ArrayList<LectureDto> list = new ArrayList<LectureDto>();
		
		String sql = "select lecture_id,name,num_std,max_std from lecture";
		
		try {
			openConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				LectureDto bean = new LectureDto();
				bean.setLectureId(rs.getInt("lecture_id"));
				bean.setName(rs.getString("name"));
				bean.setNumStd(rs.getInt("num_std"));
				bean.setMaxStd(rs.getInt("max_std"));			
				list.add(bean);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			closeConnection();
		}
		return list;
	}
	
	//행정팀 강좌목록 불러오기
	//싸그리 다 불러와서 필요한것만 뽑아서 쓰기
	//
	//20190709 am 11:23 되는지 확인
	public ArrayList<LectureDto> getLectureList() {
		openConnection();
		ArrayList<LectureDto> list = new ArrayList<LectureDto>();
		
		//번호/강좌명/강사명/개강일
		String sql = "select l.lecture_id,l.name,u.name as \"teaName\", "
				+ "TO_CHAR(start_date,'yyyymmdd') as \"startDate\" from lecture l "
				+ "JOIN lectureuser lu on lu.lecture_id=l.lecture_id "
				+ "JOIN user01 u on u.user_id=lu.user_id "
				+ "where u.belong='teacher' and l.lecture_id>0";
		
		try {
			openConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				LectureDto bean = new LectureDto();
				bean.setLectureId(rs.getInt("lecture_id"));
				bean.setName(rs.getString("name"));
				bean.setStartDate(rs.getString("startDate"));
				bean.setTeaName(rs.getString("teaName"));
				list.add(bean);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			closeConnection();
		}
		return list;
	}
	
	//강좌 상세정보페이지
	//20190709 pm 06:15 되는지 확인
	public LectureDto getLecture(int lectureId) {
		openConnection();
		LectureDto bean = new LectureDto();
		
		//sql문 수정해야할 수도
		//select l.lecture_id,num_std,total_days,max_std,lv,l.name,u.name as "username",start_date,end_date,content,file_name,is_close from lecture l JOIN user01 u on u.belong='teacher' where l.lecture_id=1;
		String sql = "select l.lecture_id as \"lecNum\",num_std,total_days,max_std,"
				+ "lv,l.name,u.name as \"username\",to_char(start_date, 'yyyy-mm-dd') as \"startDate\", "
				+ "to_char(end_date, 'yyyy-mm-dd') as \"endDate\", "
				//filename을 filenum으로 바꿔야
				+ "content,file_id,is_close, "
				+ "(select (TRUNC(sysdate) - TRUNC(start_date)) from lecture where lecture_id=?) as \"progressDays\""
				+ " from lecture l JOIN lectureuser lu on lu.lecture_id=l.lecture_id"
				+ " JOIN user01 u on u.user_id=lu.user_id"
				+ " where l.lecture_id=? and u.belong='teacher'";
		

		try {
			openConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, lectureId);
			pstmt.setInt(2, lectureId);
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				bean.setLectureId(rs.getInt("lecNum"));
				bean.setNumStd(rs.getInt("num_std"));
				bean.setTotalDays(rs.getInt("total_days"));
				bean.setMaxStd(rs.getInt("max_std"));
				bean.setName(rs.getString("name"));
				bean.setTeaName(rs.getString("username"));
				bean.setStartDate(rs.getString("startDate"));
				bean.setEndDate(rs.getString("endDate"));
				bean.setProgressDays(rs.getInt("progressDays"));
				bean.setContent(rs.getString("content"));
//				bean.setFileId(rs.getInt("file_id"));
//				bean.setIsClose(rs.getString("is_close"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			closeConnection();
		}
		return bean;
	}
	
	//행정팀 강좌관리 강좌 추가 페이지(POST방식)
	//넘어올 값 강좌명,강사명,교육기간(시작일,종료일),교육수준,최대인원,강좌내용,파일이름
	//결과값 int로 전송되어 제대로 입력되었는지 확인 가능
	public int insertLecture(LectureDto lectureBean, AttachedFileDto fileBean, AttachedFileDto fileBean2) {
		int idx=0;
		//file 테이블에 추가
		String sql1 = "insert into Attached_File (file_id,file_group,original_name,file_name,file_extension,ref_date,reg_id)"
				+"values (file_id_seq.nextval,?,?,?,?,sysdate,?)";
		
		//file 테이블에 추가하고 select로 현재 아이디 받아오기
		String sql2 = "select file_id_seq.currval as \"file_id\" from attached_file";
		
		//total_days 검색
		String sql3 = "SELECT (TO_DATE(?) - TO_DATE(?)) as \"totalDays\"  FROM DUAL";
				
		//강좌 추가
		String sql4 = "insert into lecture(lecture_id, name, start_date, end_date, total_days, max_std, lv, content, file_id, curri_id) "
				+ "values(lecture_id_seq.nextval,?,to_date(?),to_date(?),?,?,?,?,?,?)";
//		String sql4 = "insert into lecture(lecture_id, name, start_date, end_date, total_days, max_std, lv, content) "
//				+ "values(lecture_id_seq.nextval,?,to_date(?),to_date(?),?,?,?,?)";
		
		//현재 lecture current_val 검색
		String sql5 = "select lecture_id_seq.currval as \"lectureId\" from lecture";
		
		//lectureuser 변경
		String sql6 = "update lectureuser set lecture_id=? where user_id=?";
		
		try{
			openConnection();
			conn.setAutoCommit(false);
			
			pstmt = conn.prepareStatement(sql1);
			pstmt.setString(1, fileBean.getFileGroup());
			pstmt.setString(2, fileBean.getOriginalName());
			pstmt.setString(3, fileBean.getFileName());
			pstmt.setString(4, fileBean.getFileExtension());
			pstmt.setString(5, fileBean.getRegId());
			pstmt.executeUpdate();
			
			pstmt = conn.prepareStatement(sql2);
			rs = pstmt.executeQuery();
			if(rs.next()){
				lectureBean.setFileId(rs.getInt("file_id"));
			}
			
			pstmt = conn.prepareStatement(sql1);
			pstmt.setString(1, fileBean2.getFileGroup());
			pstmt.setString(2, fileBean2.getOriginalName());
			pstmt.setString(3, fileBean2.getFileName());
			pstmt.setString(4, fileBean2.getFileExtension());
			pstmt.setString(5, fileBean2.getRegId());
			pstmt.executeUpdate();
			
			pstmt = conn.prepareStatement(sql2);
			rs = pstmt.executeQuery();
			if(rs.next()){
				lectureBean.setFileId(rs.getInt("file_id"));
			}
			
			pstmt = conn.prepareStatement(sql3);
			pstmt.setString(1, lectureBean.getEndDate());
			pstmt.setString(2, lectureBean.getStartDate());
			rs = pstmt.executeQuery();
			if(rs.next()){
				lectureBean.setTotalDays(rs.getInt("totalDays"));
			}
			
			//name, start_date, end_date, total_days, max_std, lv, content, file_id, curri_id
			pstmt = conn.prepareStatement(sql4);
			pstmt.setString(1, lectureBean.getName());
			pstmt.setString(2, lectureBean.getStartDate());
			pstmt.setString(3, lectureBean.getEndDate());
			pstmt.setInt(4, lectureBean.getTotalDays());
			pstmt.setInt(5, lectureBean.getMaxStd());
			pstmt.setInt(6, lectureBean.getLv());
			pstmt.setString(7, lectureBean.getContent());
			pstmt.setInt(8, lectureBean.getFileId());
			pstmt.setInt(9, lectureBean.getCurriId());
			pstmt.executeUpdate();
			
			pstmt = conn.prepareStatement(sql5);
			rs = pstmt.executeQuery();
			if(rs.next()){
				idx = rs.getInt("lectureId");
			}
			
			pstmt = conn.prepareStatement(sql6);
			pstmt.setInt(1, idx);
			pstmt.setString(2, lectureBean.getTeaId());
			pstmt.executeUpdate();
			
			conn.commit();
		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}finally{
			try {
				conn.setAutoCommit(true);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			closeConnection();
		}
		return idx;
	}
	
	//행정팀 강좌관리 강좌 수정 페이지(POST방식)
	//넘어올 값 커리큘럼이미지,강좌명,강사명,교육기간,교육수준,최대인원,강좌내용,첨부파일을 수정가능
	//결과값 int로 전송되어 제대로 입력되었는지 확인 가능
	public int updateLecture(LectureDto lectureBean, AttachedFileDto fileBean, AttachedFileDto fileBean2) {
		
		int idx = 0;
		//file 테이블 수정
		String sql1 = "update Attached_File set file_name=?, file_extension=?, ref_date=sysdate where "
				+ "file_id=?";
			
		//강좌 수정
		String sql2 = "update lecture set name=?, start_date=?, end_date=?, total_days=?, max_std=?, lv=?, content=? "
				+ "where lecture_id=?";
		
		//lectureuser 변경
		String sql3 = "update lectureuser set lecture_id=? where user_id=?";
			
		try{
			openConnection();
			conn.setAutoCommit(false);
			
			pstmt = conn.prepareStatement(sql1);
			pstmt.setString(1, fileBean.getFileName());
			pstmt.setString(2, fileBean.getFileExtension());
			pstmt.setInt(3, lectureBean.getFileId());
			pstmt.executeUpdate();
			
			pstmt = conn.prepareStatement(sql1);
			pstmt.setString(1, fileBean.getFileName());
			pstmt.setString(2, fileBean.getFileExtension());
			pstmt.setInt(3, lectureBean.getCurriId());
			pstmt.executeUpdate();
			
			//name, start_date, end_date, total_days, max_std, lv, content, file_id, curri_id
			pstmt = conn.prepareStatement(sql2);
			pstmt.setString(1, lectureBean.getName());
			pstmt.setString(2, lectureBean.getStartDate());
			pstmt.setString(3, lectureBean.getEndDate());
			pstmt.setInt(4, lectureBean.getTotalDays());
			pstmt.setInt(5, lectureBean.getMaxStd());
			pstmt.setInt(6, lectureBean.getLv());
			pstmt.setString(7, lectureBean.getContent());
			pstmt.setInt(8, lectureBean.getLectureId());
			pstmt.executeUpdate();
			
			pstmt = conn.prepareStatement(sql3);
			pstmt.setInt(1, lectureBean.getLectureId());
			pstmt.setString(2, lectureBean.getTeaId());
			pstmt.executeUpdate();
			
			conn.commit();
		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}finally{
			try {
				conn.setAutoCommit(true);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			closeConnection();
		}
		return idx;
	}
	
	//행정팀 강좌관리 삭제 기능
	public int deleteLecture(int lectureId) {
		//제대로 전송됐는지 안됐는지만 int값으로 리턴
		int result = 0;
		String sql = "delete from lecture where lecture_id=?";
		
		try{
			openConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, lectureId);
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			closeConnection();
		}
		return result;
	}
	
	//행정팀 학생관리 학생등록 목록 페이지
	//20190709 am 9:45 나오는거 확인 완료
	public ArrayList<RegisterDto> getRegisterList() {
		openConnection();
		ArrayList<RegisterDto> list = new ArrayList<RegisterDto>();
		
		//번호 (제목제외) ID 이름 강좌 날짜 소속을 불러와야함
		//제목은 name을 불러와서 프론트엔드에서 ***님의 수강신청을 붙여야함
		//조건은 콤보박스로 강좌명에 따라 화면표시
		//그리고 어플라이한 사람만 보여야함 belong=before

		//SELECT apply_id as "num", u.name AS "name" ,u.user_id AS "id", l.name AS "lecName", TO_CHAR(a.apply_date,'yyyymmdd') AS "applyDate", u.belong AS "belong" FROM apply a INNER JOIN user01 u on a.user_id=u.user_id INNER JOIN lecture l on l.lecture_id = a.lecture_id
		//WHERE a.lecture_id = 1 ORDER BY a.apply_date;
		String sql = "SELECT apply_id as \"num\", u.name as \"name\", u.user_id AS \"id\", l.name AS \"lecName\", "
		+"TO_CHAR(a.apply_date,'yyyy-mm-dd') AS \"applyDate\", u.belong AS \"belong\" "
		+"FROM apply a INNER JOIN user01 u on a.user_id=u.user_id "
		+"INNER JOIN lecture l on l.lecture_id = a.lecture_id "
		+"ORDER BY apply_id desc";
//		+"WHERE a.lecture_id = ?"; 
		
		try {
			openConnection();
			pstmt = conn.prepareStatement(sql);
//			pstmt.setInt(1, lectureId);
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				RegisterDto bean = new RegisterDto();
				bean.setApplyDate(rs.getString("applyDate"));
				bean.setUserId(rs.getString("id"));
				bean.setLecName(rs.getString("lecName"));
				bean.setUserName(rs.getString("name"));
				bean.setRowNum(rs.getInt("num"));
				list.add(bean);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			closeConnection();
		}
		return list;
	}
	
	//행정팀 학생관리 학생등록 상세 페이지
	//20190709 pm 12:43 나오는거 확인 완료
	public RegisterDto getRegister(int registerId) {
		//학생목록에서 num를 idx로 받아 해당 num의 수강신청한 내용을 볼 수 있게
		//제목/작성자아이디/제출일/이름/강좌/연락처/파일
		openConnection();
		String sql = "SELECT u.name as \"name\", u.user_id AS \"id\", l.name AS \"lecName\", a.apply_id, "
				+"TO_CHAR(a.apply_date,'yyyy-mm-dd') AS \"applyDate\", a.file_id, u.phone_number "
				+"FROM apply a INNER JOIN user01 u on a.user_id=u.user_id "
				+"INNER JOIN lecture l on l.lecture_id = a.lecture_id "
				+"WHERE apply_id = ?";
		
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, registerId);
			rs = pstmt.executeQuery();
			
			RegisterDto bean = new RegisterDto();
			
			if(rs.next()){
				bean.setApplyDate(rs.getString("applyDate"));
				bean.setApplyId(rs.getInt("apply_id"));
				bean.setUserId(rs.getString("id"));
				bean.setLecName(rs.getString("lecName"));
				bean.setUserName(rs.getString("name"));
				bean.setFileId(rs.getInt("file_id"));
				bean.setPhoneNumber(rs.getString("phone_number"));
				return bean;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			closeConnection();
		}
		
		return null;
	}
	
	//행정팀 학생관리 학생등록 상세페이지 삭제
	public int deleteRegister(int ApplyId) {
		//제대로 전송됐는지 안됐는지만 int값으로 리턴
		int result = 0;
		String sql = "delete from apply where apply_id=?";
		
		try{
			openConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, ApplyId);
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			closeConnection();
		}
		return result;
	}
	
	//행정팀 학생관리 수강생으로 등록(user테이블 update), 해당 user_id로 된 apply테이블의 정보를 삭제
	//등록 후 목록으로 바로
	//2019-07-12 완료
	public int updateRegister(String userId,String lecName) {
		int result = 1;
		int lecture_id = 999999999;
		//해당 값들 인자로 받아와서 user01에서 belong을 update
		//lectureuser에서 lecture_id 업데이트
		//apply table 해당 학생 아이디의 apply 삭제
		String sql = "select lecture_id from lecture where name=? and num_std<max_std";
		try {
			openConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, lecName);
			rs = pstmt.executeQuery();
			if(rs.next()){
				lecture_id=rs.getInt("lecture_id");
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}finally{
			closeConnection();
		}
		
		String sql1 = "update user01 set belong='student' where user_id=?";
		String sql2 = "update lectureUser set lecture_id = ? where user_id=?";
		String sql3 = "update lecture set num_std = num_std+1 where lecture_id=?";
		String sql4 = "delete from apply where user_id=?";
		//String sql5 = "insert into score(lecture_id,std_id) values(?,?)";
		
		//제대로 전송됐는지 안됐는지만 int값으로 리턴
		try {
			openConnection();
			conn.setAutoCommit(false);
			pstmt = conn.prepareStatement(sql1);
			pstmt.setString(1, userId);
			pstmt.addBatch();
			pstmt = conn.prepareStatement(sql2);
			pstmt.setInt(1, lecture_id);
			pstmt.setString(2, userId);
			pstmt.addBatch();
			pstmt = conn.prepareStatement(sql3);
			pstmt.setInt(1, lecture_id);
			pstmt.addBatch();
			//계속 4번째 쿼리가 실행안돼서 한번 더 넣어줌
			pstmt.executeBatch();

			pstmt = conn.prepareStatement(sql4);
			pstmt.setString(1, userId);
			pstmt.addBatch();
			pstmt.executeBatch();
			
//			pstmt = conn.prepareStatement(sql5);
//			pstmt.setInt(1, lecture_id);
//			pstmt.setString(2, userId);
//			pstmt.addBatch();
//			pstmt.executeBatch();
			
			pstmt.clearBatch();
            conn.commit();
			
		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}finally{
			try {
				conn.setAutoCommit(true);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			closeConnection();
		}
		
		return result;
	}
	
	
	//행정팀 수강생관리 목록형
	//20190709 pm 04:05 나오는거 확인 완료
	public ArrayList<UserDto> getManageStu() {
		openConnection();
		//번호/이름/총 출석 수/총 강좌일 수/출석상태/강좌
		//이름순 정렬
		//totalDays, attendanceDays, attendanceStatus
		
		ArrayList<UserDto> list = new ArrayList<UserDto>();
		
		String sql = "select ROW_NUMBER() OVER(ORDER BY lu.lecture_id, u.name) NUM, u.name as \"userName\", u.user_id, "
				+ "l.total_days AS \"totalDays\", l.name as \"lecName\", a.status, t1.* "
				+ "from user01 u inner join lectureuser lu on u.user_id = lu.user_id "
				+ "inner join lecture l on l.lecture_id = lu.lecture_id "
				+ "inner join attendance a on a.std_id=u.user_id "
				+ "left join (SELECT std_id, count(status) as \"count\" FROM attendance "
				+ "where status='출석' or status='공결' GROUP BY std_id) t1 on a.std_id=t1.std_id "
				//날짜는 오늘 기준
				+ "where u.belong='student' and a.day_time>to_date(to_char(sysdate))";
		
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				//번호/이름/총 출석 수/총 강좌일 수/출석상태/강좌
				UserDto bean = new UserDto();
				bean.setRowNum(rs.getInt("num"));
				bean.setName(rs.getString("userName"));
				bean.setUserId(rs.getString("user_id"));
				bean.setTotalDays(rs.getInt("totalDays"));
				bean.setAttendanceDays(rs.getInt("count"));
				bean.setAttendanceStatus(rs.getString("status"));
				bean.setLectureName(rs.getString("lecName"));
				list.add(bean);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			closeConnection();
		}
		return list;
	}
	
	//행정팀 수강생관리 월별
	//우리 월별 출석도 ajax로 하기로 했나~?
	public ArrayList<AttendanceDto> getManageStuMonth(String yyyymm) {
		openConnection();
		ArrayList<AttendanceDto> list = new ArrayList<AttendanceDto>();
		String sql="";
		if(yyyymm==null){
			//null인 경우 sysdate로
			sql = "select u.name, a.std_id, to_char(a.day_time,'dd') as \"day\","
					+ " SUBSTR(a.status, 1, 1) as \"status\", l.name as \"lecName\" from attendance a inner join user01 u on u.user_id=a.std_id"
					+ " inner join lecture l on a.lecture_id=l.lecture_id"
					+ " where to_date(trunc(day_time,'mm'))=to_date(trunc(sysdate,'mm'))"
					+ " order by u.name, a.day_time";
		}else{
			sql = "select u.name, a.std_id, to_char(a.day_time,'dd') as \"day\","
					+ " SUBSTR(a.status, 1, 1) as \"status\", l.name as \"lecName\" from attendance a inner join user01 u on u.user_id=a.std_id"
					+ " inner join lecture l on a.lecture_id=l.lecture_id"
					+ " where to_date(trunc(day_time,'mm'))=to_date(?)"
					+ " order by u.name, a.day_time";
		}
		
		try {
			pstmt = conn.prepareStatement(sql);
			if(yyyymm!=null){
				pstmt.setString(1, yyyymm+"-01");
			}
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				AttendanceDto bean = new AttendanceDto();
				bean.setName(rs.getString("name"));
				bean.setLecName(rs.getString("lecName"));
				bean.setStdId(rs.getString("std_id"));
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
	
	//행정팀 수강생 삭제, 강사 삭제
	//연관된 데이터를 다 삭제하기 위해서는 on delete cascade 해줘야된다 (외래키로 받는 테이블에)
	public int deleteUser(String[] userId) {
		String sql = "";
		int result = 0;
		
		try{
			openConnection();
			conn.setAutoCommit(false);
			for(int i=0; i<userId.length; i++){
				sql = "delete from user01 where user_id=?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, userId[i]);
				pstmt.executeUpdate();
				result++;
			}
			
			conn.commit();
			
		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException f) {
				e.printStackTrace();
			}
			e.printStackTrace();
		}finally{
			try {
				conn.setAutoCommit(true);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			closeConnection();
		}
		
		return result;
	}
	
	//행정팀 강사관리 목록
	public ArrayList<TeacherDto> getTeacherList() {
		openConnection();
		ArrayList<TeacherDto> list = new ArrayList<TeacherDto>();
		
		//번호, 사진(은 파일불러오는거에서 불러오기), 이름, 맡은 강좌, 학력, 강좌 시작일
		//파일은 file_group/path/file_name.file_extension
		String sql = "SELECT ROW_NUMBER() OVER(ORDER BY start_date, u.name) NUM, u.name, l.name as \"lecName\", "
				+ "ti.type, ti.content, u.user_id, "
				+ "to_char(l.start_date, 'yyyy-mm-dd') as \"startDate\" "
				+ "from user01 u inner join teacher_info ti on ti.teacher_id=u.user_id "
				+ "inner join lectureuser lu on lu.user_id=u.user_id "
				+ "inner join lecture l on lu.lecture_id=l.lecture_id "
				+ "where u.belong='teacher' and type='학력'";
		
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				TeacherDto bean = new TeacherDto();
				bean.setRowNum(rs.getInt("num"));
				bean.setName(rs.getString("name"));
				bean.setLecName(rs.getString("lecName"));
				bean.setType(rs.getString("type"));
				bean.setContent(rs.getString("content"));
				bean.setStartDate(rs.getString("startDate"));
				bean.setTeacherId(rs.getString("user_id"));
				list.add(bean);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			closeConnection();
		}
		return list;
	}
	
	//맡은반 없는 강사만 뽑아오기 (콤보박스)
		public ArrayList<TeacherDto> getComboTeacherList() {
			openConnection();
			ArrayList<TeacherDto> list = new ArrayList<TeacherDto>();
			
			//파일은 file_group/path/file_name.file_extension
			String sql = "SELECT u.name, u.user_id "
					+ "from user01 u "
					+ "inner join lectureuser lu on lu.user_id=u.user_id "
					+ "inner join lecture l on lu.lecture_id=l.lecture_id "
					+ "where u.belong='teacher' and lu.lecture_id<=0";
			
			try {
				pstmt = conn.prepareStatement(sql);
				rs = pstmt.executeQuery();
				
				while(rs.next()){
					TeacherDto bean = new TeacherDto();
					bean.setName(rs.getString("name"));
					bean.setTeacherId(rs.getString("user_id"));
					list.add(bean);
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			}finally{
				closeConnection();
			}
			return list;
		}
	
	//행정팀 파일 입력하기 여러개일때
	public int insertFile(ArrayList<AttachedFileDto> list){
		int result1 = 0;
		int result2 = 0;
		String sql = "Select count(*) as total from Attached_File where original_name=?";
		String sql1 ="insert into File_Group (file_group,path) values (?,?)";
		String sql2 = "insert into Attached_File (file_id,file_group,original_name,file_name,file_extension,ref_date,reg_id)"
				+"values (file_id_seq.nextval,?,?,?,?,sysdate,?)";
		
		
		try { 
			openConnection();
			conn.setAutoCommit(false);
			for(int i=0; i<list.size(); i++){
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, list.get(i).getOriginalName());
				result1 = pstmt.executeUpdate();
				if(result1>0){
					list.get(i).setFileName(list.get(i).getFileName()+result1);
				}
				pstmt = conn.prepareStatement(sql1);
				pstmt.setString(1, list.get(i).getFileGroup());
				pstmt.setString(2, list.get(i).getPath());
				result2 += pstmt.executeUpdate();
				pstmt = conn.prepareStatement(sql2);
				pstmt.setString(1, list.get(i).getFileGroup());
				pstmt.setString(2, list.get(i).getOriginalName());
				pstmt.setString(3, list.get(i).getFileName());
				pstmt.setString(4, list.get(i).getFileExtension());
				pstmt.setString(5, list.get(i).getRegId());
				result2 += pstmt.executeUpdate();
			}
			
			conn.commit();
		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally{
			try {
				conn.setAutoCommit(true);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			closeConnection();
		}
		return result2;
		
	}
	
	//행정팀 강사 프로필 파일 불러오기(상세)
	public AttachedFileDto getProfileFile(String userId){
		AttachedFileDto bean = new AttachedFileDto();
		String sql = "Select fg.path, af.file_name, af.file_extension "
				+ "from attached_file af inner join file_group fg on fg.file_group = af.file_group "
				+ "where af.reg_id=? and af.file_group='profile'";
		
		try { 
			openConnection();

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userId);
			rs = pstmt.executeQuery();
			if(rs.next()){
				bean.setPath(rs.getString("path"));
				bean.setFileName(rs.getString("file_name"));
				bean.setFileExtension(rs.getString("file_extension"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			closeConnection();
		}
		return bean;
	}
	
	//행정팀 강사 프로필 파일 불러오기(목록)
	//좀 이따가 하자
	public AttachedFileDto getProfileFileList(String userId){
		AttachedFileDto bean = new AttachedFileDto();
		String sql = "Select fg.path, af.file_name, af.file_extension "
				+ "from attached_file af inner join file_group fg on fg.file_group = af.file_group "
				+ "inner join user01"
				+ "where af.file_group='profile'";
		
		try { 
			openConnection();

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userId);
			rs = pstmt.executeQuery();
			if(rs.next()){
				bean.setPath(rs.getString("path"));
				bean.setFileName(rs.getString("file_name"));
				bean.setFileExtension(rs.getString("file_extension"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			closeConnection();
		}
		return bean;
	}
	
	
	//행정팀 강사관리 상세
	//강사 명으로 접근해야 하기 때문에 user_id의 자료형인 String형으로 수정
	//이름, 맡은 강좌,학력,타입, 콘텐츠(있는거 싹다),이메일,연락처
	public ArrayList<TeacherDto> getTeacher(String userId) {
		ArrayList<TeacherDto> list = new ArrayList<TeacherDto>(); 
		String sql = "Select u.name, l.name as \"lecName\", ti.type, ti.content, u.email, u.phone_number, u.user_id "
				+ "from user01 u inner join lectureuser lu on lu.user_id=u.user_id "
				+ "inner join lecture l on l.lecture_id=lu.lecture_id "
				+ "inner join teacher_info ti on ti.teacher_id=u.user_id "
				+ "where u.user_id=? order by ti.type";
		
		try { 
			openConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userId);
			rs = pstmt.executeQuery();
			
			TeacherDto bean = new TeacherDto();
			while(rs.next()){
				bean = new TeacherDto();
				bean.setName(rs.getString("name"));
				bean.setTeacherId(rs.getString("user_id"));
				bean.setLecName(rs.getString("lecName"));
				bean.setType(rs.getString("type"));
				bean.setContent(rs.getString("content"));
				bean.setEmail(rs.getString("email"));
				bean.setPhoneNumber(rs.getString("phone_number"));
				list.add(bean);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			closeConnection();
		}
		return list;
	}
	
	//행정팀 강사관리 추가
	//teacherInfo 객체를 만드는게 좋으려나....
	public int insertTeacher(TeacherDto teacherBean, String[] career, String[] book, String[] certificate, AttachedFileDto fileBean) {
		int result=0;
		int result1 = 0; 
		int result2 = 0;
		//user 테이블에 추가
		String sql1 = "insert into user01(user_id,password,name,email,phone_number,belong) values(?,?,?,?,?,'teacher')";
		//lectureuser 테이블에 추가 (강좌가 할당되지 않았으므로 lecture_id는 -1)
		String sql2 = "insert into lectureuser(lecture_id,user_id) values(0,?)";
		
		//파일추가
		String sql6 = "insert into Attached_File (file_id,file_group,original_name,file_name,file_extension,ref_date,reg_id)"
				+"values (file_id_seq.nextval,?,?,?,?,sysdate,?)";
		
		//teacherInfo 테이블에 추가 (여러개있으면 여러번 추가)
		String sql3 = "insert into teacher_info(info_id,type,content,teacher_id) values(info_id_seq.nextval,?,?,?)";
		
		try{
			openConnection();
			conn.setAutoCommit(false);
			pstmt = conn.prepareStatement(sql1);
			pstmt.setString(1, teacherBean.getTeacherId());
			pstmt.setString(2, teacherBean.getPassword());
			pstmt.setString(3, teacherBean.getName());
			pstmt.setString(4, teacherBean.getEmail());
			pstmt.setString(5, teacherBean.getPhoneNumber());
			result = pstmt.executeUpdate();
			
			pstmt = conn.prepareStatement(sql2);
			pstmt.setString(1, teacherBean.getTeacherId());
			pstmt.executeUpdate();
			
			pstmt = conn.prepareStatement(sql6);
			pstmt.setString(1, fileBean.getFileGroup());
			pstmt.setString(2, fileBean.getOriginalName());
			pstmt.setString(3, fileBean.getFileName());
			pstmt.setString(4, fileBean.getFileExtension());
			pstmt.setString(5, fileBean.getRegId());
			result2 += pstmt.executeUpdate();
			
			pstmt = conn.prepareStatement(sql3);
			pstmt.setString(1, "학력");
			pstmt.setString(2, teacherBean.getContent());
			pstmt.setString(3, teacherBean.getTeacherId());
			pstmt.executeUpdate();
			
			for(int i=0; i<career.length; i++){
				pstmt = conn.prepareStatement(sql3);
				pstmt.setString(1, "경력사항");
				pstmt.setString(2, career[i]);
				pstmt.setString(3, teacherBean.getTeacherId());
				pstmt.executeUpdate();
			}
			for(int i=0; i<book.length; i++){
				pstmt = conn.prepareStatement(sql3);
				pstmt.setString(1, "저서");
				pstmt.setString(2, book[i]);
				pstmt.setString(3, teacherBean.getTeacherId());
				pstmt.executeUpdate();
			}
			for(int i=0; i<certificate.length; i++){
				pstmt = conn.prepareStatement(sql3);
				pstmt.setString(1, "자격");
				pstmt.setString(2, certificate[i]);
				pstmt.setString(3, teacherBean.getTeacherId());
				pstmt.executeUpdate();
			}
			
			conn.commit();
		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}finally{
			try {
				conn.setAutoCommit(true);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			closeConnection();
		}
		return result+result2;
	}
	
	
	//행정팀 강사관리 수정
	//강사 수정할 때는 update
	//파일Db도 업데이트 reg_id가 해당 강사의 아이디이고 group이 profile일때
	public int updateTeacher(TeacherDto teacherBean, String[] career, String[] book, String[] certificate, AttachedFileDto fileBean) {
		int result=0;
		int result1 = 0; 
		int result2 = 0;
		
		//user업데이트
		//강사명, 이메일, 전화번호
		String sql1 = "update user01 set name=?, email=?, phone_number=? where user_id=?";
		
		//파일 업데이트
		//original_name,file_name,file_extension,ref_date 업데이트
		String sql2 = "update attached_file set original_name=?, file_name=?, file_extension=?, "
				+ "ref_date=sysdate where reg_id=? and file_group='profile'";
		
		//teacherInfo 수정 info id를 어떻게...?...있는거는 할당해도 추가를 하면 어떻게...?
		//그냥 삭제하고 다시 넣는게 깔끔할 것 같다.
		//학력, 경력, 저서, 자격
		String sql3 = "delete from teacher_info where teacher_id=?";
		String sql4 = "insert into teacher_info(info_id,type,content,teacher_id) values(info_id_seq.nextval,?,?,?)";
		
		try{	
			openConnection();
			conn.setAutoCommit(false);
			pstmt = conn.prepareStatement(sql1);
			pstmt.setString(1, teacherBean.getName());
			pstmt.setString(2, teacherBean.getEmail());
			pstmt.setString(3, teacherBean.getPhoneNumber());
			pstmt.setString(4, teacherBean.getTeacherId());
			result = pstmt.executeUpdate();
			
			pstmt = conn.prepareStatement(sql2);
			pstmt.setString(1, fileBean.getOriginalName());
			pstmt.setString(2, fileBean.getFileName());
			pstmt.setString(3, fileBean.getFileExtension());
			pstmt.setString(4, fileBean.getRegId());
			result2 += pstmt.executeUpdate();
			
			pstmt = conn.prepareStatement(sql3);
			pstmt.setString(1, teacherBean.getTeacherId());
			pstmt.executeUpdate();
			
			pstmt = conn.prepareStatement(sql4);
			pstmt.setString(1, "학력");
			pstmt.setString(2, teacherBean.getContent());
			pstmt.setString(3, teacherBean.getTeacherId());
			pstmt.executeUpdate();
			
			for(int i=0; i<career.length; i++){
				pstmt = conn.prepareStatement(sql4);
				pstmt.setString(1, "경력사항");
				pstmt.setString(2, career[i]);
				pstmt.setString(3, teacherBean.getTeacherId());
				pstmt.executeUpdate();
			}
			for(int i=0; i<book.length; i++){
				pstmt = conn.prepareStatement(sql4);
				pstmt.setString(1, "저서");
				pstmt.setString(2, book[i]);
				pstmt.setString(3, teacherBean.getTeacherId());
				pstmt.executeUpdate();
			}
			for(int i=0; i<certificate.length; i++){
				pstmt = conn.prepareStatement(sql4);
				pstmt.setString(1, "자격");
				pstmt.setString(2, certificate[i]);
				pstmt.setString(3, teacherBean.getTeacherId());
				pstmt.executeUpdate();
			}
			
			conn.commit();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			closeConnection();
		}
		return 0;
	}
	
	//행정팀 큐엔에이 목록
	//번호, 제목, 작성자, 작성일, 답변여부, 분류
	public ArrayList<QnaLDto> getQnaLList() {
		openConnection();
		ArrayList<QnaLDto> list = new ArrayList<QnaLDto>();
		String sql = "SELECT row_number() OVER(ORDER BY write_date) num ,qnal_id, u.name as \"name\", std_id, type, question_content,"
				+ "title, answer_content, TO_CHAR(write_date,'YYYY-MM-DD') as \"write_date\", is_check "
				+ "FROM qna_l q "
				+ "JOIN user01 u ON q.std_id = u.user_id "
				+ "WHERE type = '행정' "
				+ "ORDER BY is_check , write_date desc";
		
		
		try {
			openConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				QnaLDto bean = new QnaLDto();
				bean.setRowNum(rs.getInt("num"));
				bean.setQnaLId(rs.getInt("qnal_id"));
				bean.setStuId(rs.getString("std_id"));
				bean.setStdName(rs.getString("name"));
				bean.setTitle(rs.getString("title"));
				bean.setWriteDate(rs.getString("write_date"));
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
	
	//행정팀 큐엔에이 상세
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			closeConnection();
		}
		return bean;
	}
	
	
	
	//행정팀 큐엔에이 답변등록
	public int updateQnaL(QnaLDto qnaLBean) {
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
	//행정팀 큐엔에이 삭제
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
	

	
	//매일(수업이 있는) 오전 6시 모든 학생의 출석 row를 생성
	//checkin, checkout 모두 null
	public ArrayList<Integer> insertAttendanceAll() {
		ArrayList<String> stuList = new ArrayList<String>();
		ArrayList<Integer> resultList = new ArrayList<Integer>();
		int result = -1;
		
		//교육 기간 중에 있는 모든 학생의 id를 모음
		String selectStuSql = "SELECT u.user_id "
				+ "FROM user01 u "
				+ "JOIN lectureUser lu ON u.user_id = lu.user_id "
				+ "JOIN lecture l ON lu.lecture_id = l.lecture_id "
				+ "WHERE u.belong = 'student' "
				+ "AND (SYSDATE BETWEEN l.start_date AND l.end_date)";
		
		//출석 테이블에 당일 row를 삽입
		// 그냥 sysdate로 하면 시간에 따라 중복 삽입이 가능하니 년월일로 만듬
		String insertAttendanceSql = "INSERT INTO attendance(day_time,std_id) VALUES(TO_DATE(TO_CHAR(SYSDATE,'YYYY-MM-DD'),'YYYY-MM-DD'), ?)";
		try {
			openConnection();
			conn.setAutoCommit(false);
			pstmt = conn.prepareStatement(selectStuSql);
			rs = pstmt.executeQuery();
			while(rs.next()){
				stuList.add(rs.getString("user_id"));
			}
			closeConnection();
			openConnection();
			for(int idx = 0 ; idx <stuList.size() ; idx ++){
				pstmt = conn.prepareStatement(insertAttendanceSql);
				pstmt.setString(1, stuList.get(idx));
				result = pstmt.executeUpdate();
			}
			conn.commit();
		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		}finally{
			try {
				conn.setAutoCommit(true);
				closeConnection();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		return resultList;
	}
	
	//매일(수업이 있는) 오후 11시 모든 학생의 출석 상태를 수정
	//checkinTime과 checkoutTime을 비교해서 출석,지각, 결석, 조퇴 중 하나로 변경
	public int updateAttendanceAll(String updateDay) {
		//오후 11시 기준으로 출석이 있는 강좌를 듣는 학생들
		String selectStuSql = " SELECT a.std_id, TO_CHAR(checkin_time, 'HH24:MI:SS') as \"checkin_time\" ,TO_CHAR(checkout_time, 'HH24:MI:SS') as \"checkout_time\" "
				+ "FROM user01 u "
				+ "JOIN lectureUser lu ON u.user_id = lu.user_id "
				+ "JOIN lecture l ON l.lecture_id = lu.lecture_id "
				+ "JOIN attendance a ON a.std_id = u.user_id "
				+ "WHERE u.belong = 'student' "
				+ "AND (SYSDATE BETWEEN l.start_date AND l.end_date)";
		
		//
		String updateStatusSql = "UPDATE attendance SET status=? "
				+ "WHERE (? = TO_CHAR(day_time,'YYYY-MM-DD')) "
				+ "AND std_id =?";
		
		
		ArrayList<AttendanceDto> attendanceList = new ArrayList<AttendanceDto>(); 
		ArrayList<Integer> resultList = new ArrayList<Integer>();
		String status= "";
		
		
		
		try {
			openConnection();
			conn.setAutoCommit(false);
			pstmt = conn.prepareStatement(selectStuSql);
			rs = pstmt.executeQuery();
			while(rs.next()){
				AttendanceDto bean = new AttendanceDto();
				bean.setStdId(rs.getString("std_id"));
				bean.setCheckinTime(rs.getString("checkin_time"));
				bean.setCheckoutTime(rs.getString("checkout_time"));
				
				pstmt = conn.prepareStatement(updateStatusSql);
				status = calculateStatus(bean);
				pstmt.setString(1, status);
				pstmt.setString(2, updateDay);
				pstmt.setString(3, bean.getStdId());
				resultList.add(pstmt.executeUpdate());
			}
		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}catch (ParseException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}finally{
			try {
				conn.setAutoCommit(true);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				closeConnection();
			}
			
		}
		
		
		return -1;
	}

	private String calculateStatus(AttendanceDto bean) throws ParseException {
		//체크인 또는 체크아웃을 하지 않은 경우 -> 결석
		if((bean.getCheckinTime() ==null) || (bean.getCheckoutTime() ==null)){
			return "결석";
		}
		SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
		Date checkinTime = (Date) dateFormat.parse(bean.getCheckinTime());
		Date checkoutTime = (Date) dateFormat.parse(bean.getCheckoutTime());
		
		boolean isLate = false;
		boolean isEarly = false;
		
		String status ="출석";
		
		//지각 check
		if(checkinTime.getHours() >9){
			if(checkinTime.getMinutes()>40){
				isLate = true;
			}
		}
		
		//조퇴 check
		if(checkoutTime.getHours() < 18){
			isEarly = true;
		}
		
		if(isLate &&isEarly){
			status = "결석";
		}else if(isLate){
			status = "지각";
		}else if(isEarly){
			status = "조퇴";
		}
		return status;
	}

	//출석업데이트
	public int updateManageStuMonth(String yyyymm, String[] userId,
			String[] status) {
		int result = 0;
		ArrayList<String> sql = new ArrayList<String>();
		for(int i=0; i<status.length; i++){
			if(!status[i].equals("#")){
				if(status[i].equals("공")){
					//만약 총 60개, 6명이면 10개당
					int num = i/((status.length)/(userId.length));
					int dayNum = i+1;
					if(num!=0){
						dayNum = (i+1)%(num*((status.length)/(userId.length)));						
					}
					sql.add("UPDATE attendance SET status = '공결' "
							+ "WHERE std_id = '"+userId[num]+"' and "
									+ "trunc(day_time,'dd')=trunc(to_date('"+yyyymm+"-"+dayNum+"'),'dd')");
					
				}
			}
		}
		
		try{
			openConnection();
			conn.setAutoCommit(false);
			for(int i=0; i<sql.size(); i++){
				pstmt = conn.prepareStatement(sql.get(i));
				result += pstmt.executeUpdate();
			}
			
			if(result>0){
				conn.commit();				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				conn.setAutoCommit(true);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			closeConnection();
		}
		return result;
	}
	
	public String getManageStuMonthJson(ArrayList<AttendanceDto> list){
		JSONArray jArray = new JSONArray();
		if (list != null) { 
            for (int i = 0; i < list.size(); i++) {
                JSONObject jObject = new JSONObject();//배열 내에 들어갈 json
                jObject.put("id", list.get(i).getStdId());
                jObject.put("name", list.get(i).getName());
                jObject.put("lecName", list.get(i).getLecName());
                jObject.put("dayTime", list.get(i).getDayTime());
                jObject.put("status", list.get(i).getStatus());
                jArray.add(jObject);
            }
        	} 
		
		return jArray.toJSONString();
	}

}