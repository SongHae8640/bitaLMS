DELETE FROM USER01;
DELETE FROM LECTURE;
DELETE FROM LECTUREUSER;
DELETE FROM TEACHER_INFO;
DELETE FROM SCORE;
DELETE FROM ASSIGNMENT;
DELETE FROM SUBMISSION;
DELETE FROM QNA_L;
DELETE FROM CALENDAR;

--INSERT할때 자료명과 순서를 같이 넣어줄 것
INSERT INTO user01(user_id,password,name,email,phone_number,inflow_path,belong) values('tea1','1234', 'teacher1', '', '', '', 'teacher');
INSERT INTO user01(user_id,password,name,email,phone_number,inflow_path,belong) values('tea2','1234', 'teacher2', '', '', '', 'teacher');
INSERT INTO user01(user_id,password,name,email,phone_number,inflow_path,belong) values('tea3','1234', 'teacher3', '', '', '', 'teacher');
INSERT INTO user01(user_id,password,name,email,phone_number,inflow_path,belong) values('admin','1234', 'admin1', '', '', '', 'admin');
INSERT INTO user01(user_id,password,name,email,phone_number,inflow_path,belong) values('stu1','1234', 'student1', '', '', '', 'student');
INSERT INTO user01(user_id,password,name,email,phone_number,inflow_path,belong) values('stu2','1234', 'student2', '', '', '', 'student');
INSERT INTO user01(user_id,password,name,email,phone_number,inflow_path,belong) values('stu3','1234', 'student3', '', '', '', 'student');
INSERT INTO user01(user_id,password,name,email,phone_number,inflow_path,belong) values('stu4','1234', 'student4', '', '', '', 'student');
INSERT INTO user01(user_id,password,name,email,phone_number,inflow_path,belong) values('stu5','1234', 'student5', '', '', '', 'student');
INSERT INTO user01(user_id,password,name,email,phone_number,inflow_path,belong) values('stu6','1234', 'student6', '', '', '', 'student');
INSERT INTO user01(user_id,password,name,email,phone_number,inflow_path,belong) values('bef1','1234', 'before1', '', '', '', 'before');
INSERT INTO user01(user_id,password,name,email,phone_number,inflow_path,belong) values('bef2','1234', 'before2', '', '', '', 'before');
INSERT INTO user01(user_id,password,name,email,phone_number,inflow_path,belong) values('bef3','1234', 'before3', '', '', '', 'before');
INSERT INTO user01(user_id,password,name,email,phone_number,inflow_path,belong) values('bef4','1234', 'before4', '', '', '', 'before');

INSERT INTO lecture(lecture_id,name,start_date,end_date,total_days,num_std,max_std,lv,content,is_close) values(-1,'직원',sysdate,sysdate,0,0,0,0,'content','0');
INSERT INTO lecture(lecture_id,name,start_date,end_date,total_days,num_std,max_std,lv,content,is_close) values(0,'등록전',sysdate,sysdate,0,0,0,0,'content','0');
INSERT INTO lecture(lecture_id,name,start_date,end_date,total_days,num_std,max_std,lv,content,is_close) values(1,'JAVA',sysdate,sysdate,60,0,30,3,'content','0');
INSERT INTO lecture(lecture_id,name,start_date,end_date,total_days,num_std,max_std,lv,content,is_close) values(2,'DB',sysdate,sysdate,60,0,30,3,'content','0');
INSERT INTO lecture(lecture_id,name,start_date,end_date,total_days,num_std,max_std,lv,content,is_close) values(3,'WEB',sysdate,sysdate,60,0,30,3,'content','0');

--강좌를 입력할때 LectureUser 테이블에도 추가해줄것 
INSERT INTO lectureUser(lecture_id, user_id) values(1,'tea1');
INSERT INTO lectureUser(lecture_id, user_id) values(2,'tea2');
INSERT INTO lectureUser(lecture_id, user_id) values(3,'tea3');
INSERT INTO lectureUser(lecture_id, user_id) values(1,'stu1');
INSERT INTO lectureUser(lecture_id, user_id) values(1,'stu2');
INSERT INTO lectureUser(lecture_id, user_id) values(1,'stu3');
INSERT INTO lectureUser(lecture_id, user_id) values(1,'stu4');
INSERT INTO lectureUser(lecture_id, user_id) values(1,'stu5');
INSERT INTO lectureUser(lecture_id, user_id) values(1,'stu6');
INSERT INTO lectureUser(lecture_id, user_id) values(-1,'admin');
INSERT INTO lectureUser(lecture_id, user_id) values(0,'bef1');
INSERT INTO lectureUser(lecture_id, user_id) values(0,'bef2');
INSERT INTO lectureUser(lecture_id, user_id) values(0,'bef3');
INSERT INTO lectureUser(lecture_id, user_id) values(0,'bef4');


--학생 정보를 구할때 
--SELECT * FROM user01 u JOIN lectureUser l ON u.user_id=l.user_id WHERE u.user_id='tea1' AND u.password='1234';
--SELECT * FROM user01 u JOIN lectureUser l ON u.user_id=l.user_id;

--Teacher_info
INSERT INTO teacher_info(info_id,type ,content,teacher_id) VALUES(1,'학력','00대학 00학과','tea1');

--Attendance
INSERT INTO attendance(day_time,std_id,checkin_time,checkout_time,status,lecture_id) VALUES(TO_DATE('20190701','YYYYMMDD'),'stu1',TO_DATE('20190701 09:00:11','YYYYMMDD HH24:MI:SS') ,TO_DATE('20190701 18:00:11','YYYYMMDD HH24:MI:SS') ,'출석',1);
INSERT INTO attendance(day_time,std_id,checkin_time,checkout_time,status,lecture_id) VALUES(TO_DATE('20190703','YYYYMMDD'),'stu1',TO_DATE('20190703 09:00:11','YYYYMMDD HH24:MI:SS') ,TO_DATE('20190703 18:00:11','YYYYMMDD HH24:MI:SS') ,'출석',1);
INSERT INTO attendance(day_time,std_id,checkin_time,status,lecture_id) VALUES(TO_DATE('20190703','YYYYMMDD'),'stu2',TO_DATE('20190703 09:00:11','YYYYMMDD HH24:MI:SS') , '출석',1);
--Score
-- null 이면 안되니까 -1을 넣어서 0점과 구분
INSERT INTO score(lecture_id,std_id,first_score,second_score,third_score,avg_score) VALUES(1,'stu1',90,90,90,90);
INSERT INTO score(lecture_id,std_id,first_score,second_score,third_score,avg_score) VALUES(1,'stu2',80,90,85,85);
INSERT INTO score(lecture_id,std_id,first_score,second_score,third_score,avg_score) VALUES(1,'stu3',80,80,80,80);
INSERT INTO score(lecture_id,std_id,first_score,second_score,third_score,avg_score) VALUES(1,'stu4',70,80,90,80);
INSERT INTO score(lecture_id,std_id,first_score,second_score,third_score,avg_score) VALUES(1,'stu6',70,70,70,70);

--Assignment
INSERT INTO assignment(assignment_id, title, content, lecture_id, write_date) VALUES(1,'과제1','과제1 내용을 이러하다.',1,SYSDATE);
INSERT INTO assignment(assignment_id, title, content, lecture_id, write_date) VALUES(2,'과제2','과제2 내용이다.',1,SYSDATE);
INSERT INTO assignment(assignment_id, title, content, lecture_id, write_date) VALUES(3,'과제2','과제3 내용',1,SYSDATE);

--Submission
INSERT INTO submission(assignment_id, std_id,submit_date,file_name,is_check) VALUES(1,'stu1',SYSDATE,'파일명1.doc','0');
INSERT INTO submission(assignment_id, std_id,submit_date,file_name,is_check) VALUES(1,'stu2',SYSDATE,'파일명2.doc','0');
INSERT INTO submission(assignment_id, std_id,submit_date,file_name,is_check) VALUES(1,'stu3',SYSDATE,'파일명3.doc','0');
INSERT INTO submission(assignment_id, std_id,submit_date,file_name,is_check) VALUES(2,'stu2',SYSDATE,'파일명4.doc','0');
INSERT INTO submission(assignment_id, std_id,submit_date,file_name,is_check) VALUES(3,'stu2',SYSDATE,'파일명5.doc','0');

--Qna_L
--null 을 넣으면 안되니까 질문 생성시 빈칸으로 해서 생성할것
INSERT INTO qna_l(question_id,std_id, type, title, question_content, responder_id, answer_content, write_date) VALUES(1,'stu1','의의신청','성적문의 입니다(제목)','성적이 잘못 나온것 같습니다. 수정 해주세요.','tea1','네 그게 맞습니다 더 공부하세요.',SYSDATE);
INSERT INTO qna_l(question_id,std_id, type, title, question_content, responder_id, answer_content, write_date) VALUES(2,'stu2','성적문의','성적문의 입니다(제목)','성적이 잘못 나온것 같습니다. 수정 해주세요.','tea1','네 그게 맞습니다 더 공부하세요.',SYSDATE);
INSERT INTO qna_l(question_id,std_id, type, title, question_content, responder_id, answer_content, write_date) VALUES(3,'stu3','강사','성적문의 입니다(제목)','성적이 잘못 나온것 같습니다. 수정 해주세요.','tea1','네 그게 맞습니다 더 공부하세요.',SYSDATE);
INSERT INTO qna_l(question_id,std_id, type, title, question_content, responder_id, answer_content, write_date) VALUES(4,'stu1','행정','성적문의 입니다(제목)','성적이 잘못 나온것 같습니다. 수정 해주세요.','admin','네 그게 맞습니다 더 공부하세요.',SYSDATE);

--Calendar
INSERT INTO calendar(calendar_id, title, content, start_date, end_date, lecture_id) VALUES(1,'cal1', 'cal1 content', SYSDATE, SYSDATE,1);
INSERT INTO calendar(calendar_id, title, content, start_date, end_date, lecture_id) VALUES(2,'cal2', 'cal2 content', SYSDATE, SYSDATE,2);
INSERT INTO calendar(calendar_id, title, content, start_date, end_date, lecture_id) VALUES(3,'cal3', 'cal3 content', SYSDATE, SYSDATE,3);
INSERT INTO calendar(calendar_id, title, content, start_date, end_date, lecture_id) VALUES(4,'cal4', 'cal4 content', SYSDATE, SYSDATE,1);
INSERT INTO calendar(calendar_id, title, content, start_date, end_date, lecture_id) VALUES(5,'cal5', 'cal5 content', SYSDATE, SYSDATE,2);
INSERT INTO calendar(calendar_id, title, content, start_date, end_date, lecture_id) VALUES(6,'cal6', 'cal6 content', SYSDATE, SYSDATE,1);

commit;