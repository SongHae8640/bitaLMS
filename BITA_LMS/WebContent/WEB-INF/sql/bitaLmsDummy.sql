DELETE FROM USER01;
DELETE FROM LECTURE;
DELETE FROM LECTUREUSER;
DELETE FROM TEACHER_INFO;
DELETE FROM SCORE;
DELETE FROM ASSIGNMENT;
DELETE FROM SUBMISSION;
DELETE FROM QNA_L;
DELETE FROM CALENDAR;
DELETE FROM ATTACHED_FILE;
DELETE FROM FILE_GROUP;
DELETE FROM APPLY;

--INSERT�Ҷ� �ڷ��� �� ���� �־��� ��
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

INSERT INTO lecture(lecture_id,name,start_date,end_date,total_days,num_std,max_std,lv,content,is_close) values(-1,'행정',sysdate,sysdate,0,0,0,0,'content','0');
INSERT INTO lecture(lecture_id,name,start_date,end_date,total_days,num_std,max_std,lv,content,is_close) values(0,'등록전',sysdate,sysdate,0,0,0,0,'content','0');
INSERT INTO lecture(lecture_id,name,start_date,end_date,total_days,num_std,max_std,lv,content,is_close) values(1,'JAVA',sysdate,sysdate,60,0,30,3,'content','0');
INSERT INTO lecture(lecture_id,name,start_date,end_date,total_days,num_std,max_std,lv,content,is_close) values(2,'DB',sysdate,sysdate,60,0,30,3,'content','0');
INSERT INTO lecture(lecture_id,name,start_date,end_date,total_days,num_std,max_std,lv,content,is_close) values(3,'WEB',sysdate,sysdate,60,0,30,3,'content','0');

--���� �Է��Ҷ� LectureUser ���̺��� �߰����ٰ� 
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

--학생의 강좌를 입력
--UPDATE 


--�л� ����� ���Ҷ� 
--SELECT * FROM user01 u JOIN lectureUser l ON u.user_id=l.user_id WHERE u.user_id='tea1' AND u.password='1234';
--SELECT * FROM user01 u JOIN lectureUser l ON u.user_id=l.user_id;

--Teacher_info
INSERT INTO teacher_info(info_id,type ,content,teacher_id) VALUES(1,'학력','세종대학교 컴퓨터공학과','tea1');
INSERT INTO teacher_info(info_id,type ,content,teacher_id) VALUES(2,'학력','건국대학교 문화콘텐츠학과','tea2');
INSERT INTO teacher_info(info_id,type ,content,teacher_id) VALUES(3,'학력','고려대학교 컴퓨터공학과','tea3');

--Attendance
INSERT INTO attendance(day_time,std_id,checkin_time,checkout_time,status,lecture_id) VALUES(TO_DATE('20190701','YYYYMMDD'),'stu1',TO_DATE('20190701 09:00:11','YYYYMMDD HH24:MI:SS') ,TO_DATE('20190701 18:00:11','YYYYMMDD HH24:MI:SS') ,'�⼮',1);
INSERT INTO attendance(day_time,std_id,checkin_time,checkout_time,status,lecture_id) VALUES(TO_DATE('20190703','YYYYMMDD'),'stu1',TO_DATE('20190703 09:00:11','YYYYMMDD HH24:MI:SS') ,TO_DATE('20190703 18:00:11','YYYYMMDD HH24:MI:SS') ,'�⼮',1);
INSERT INTO attendance(day_time,std_id,checkin_time,status,lecture_id) VALUES(TO_DATE('20190703','YYYYMMDD'),'stu2',TO_DATE('20190703 09:00:11','YYYYMMDD HH24:MI:SS') , '�⼮',1);
--Score
-- null �̸� �ȵǴϱ� -1� �־ 0��� ����
INSERT INTO score(lecture_id,std_id,first_score,second_score,third_score,avg_score) VALUES(1,'stu1',90,90,90,90);
INSERT INTO score(lecture_id,std_id,first_score,second_score,third_score,avg_score) VALUES(1,'stu2',80,90,85,85);
INSERT INTO score(lecture_id,std_id,first_score,second_score,third_score,avg_score) VALUES(1,'stu3',80,80,80,80);
INSERT INTO score(lecture_id,std_id,first_score,second_score,third_score,avg_score) VALUES(1,'stu4',70,80,90,80);
INSERT INTO score(lecture_id,std_id,first_score,second_score,third_score,avg_score) VALUES(1,'stu6',70,70,70,70);

--Assignment

INSERT INTO assignment(assignment_id, title, content, lecture_id, write_date) VALUES(1,'과제1','과제1 내용을 이러하다.',1,SYSDATE);
INSERT INTO assignment(assignment_id, title, content, lecture_id, write_date) VALUES(2,'과제2','과제2 내용이다.',1,SYSDATE);
INSERT INTO assignment(assignment_id, title, content, lecture_id, write_date) VALUES(3,'과제2','과제3 내용',1,SYSDATE);
INSERT INTO assignment(assignment_id, title, content, lecture_id, write_date) VALUES(assignment_id_seq.NEXTVAL,'과제다!!','과제!! 내용!!',1,SYSDATE);

--Submission
INSERT INTO submission(assignment_id, std_id,submit_date,is_check) VALUES(1,'stu1',SYSDATE,'0');
INSERT INTO submission(assignment_id, std_id,submit_date,is_check) VALUES(1,'stu2',SYSDATE,'0');
INSERT INTO submission(assignment_id, std_id,submit_date,is_check) VALUES(1,'stu3',SYSDATE,'0');
INSERT INTO submission(assignment_id, std_id,submit_date,is_check) VALUES(2,'stu2',SYSDATE,'0');
INSERT INTO submission(assignment_id, std_id,submit_date,is_check) VALUES(3,'stu2',SYSDATE,'0');
INSERT INTO submission(assignment_id, std_id,submit_date,is_check) VALUES(3,'stu2',SYSDATE,'264');


--Qna_L
--null 을 넣으면 안되니까 질문 생성시 빈칸으로 해서 생성할것
INSERT INTO qna_l(qnal_id,std_id, type, title, question_content, responder_id, answer_content, write_date, is_check) VALUES(1,'stu1','의의신청','성적문의 입니다(제목)','성적이 잘못 나온것 같습니다. 수정 해주세요.','tea1','네 그게 맞습니다 더 공부하세요.',SYSDATE,1);
INSERT INTO qna_l(qnal_id,std_id, type, title, question_content, responder_id, answer_content, write_date, is_check) VALUES(2,'stu2','성적문의','성적문의 입니다(제목)','성적이 잘못 나온것 같습니다. 수정 해주세요.','tea1','네 그게 맞습니다 더 공부하세요.',SYSDATE,0);
INSERT INTO qna_l(qnal_id,std_id, type, title, question_content, responder_id, answer_content, write_date, is_check) VALUES(3,'stu3','강사','성적문의 입니다(제목)','성적이 잘못 나온것 같습니다. 수정 해주세요.','tea1','네 그게 맞습니다 더 공부하세요.',SYSDATE,0);
INSERT INTO qna_l(qnal_id,std_id, type, title, question_content, responder_id, answer_content, write_date, is_check) VALUES(4,'stu1','행정','성적문의 입니다(제목)','성적이 잘못 나온것 같습니다. 수정 해주세요.','admin','네 그게 맞습니다 더 공부하세요.',SYSDATE,1);

--Calendar
INSERT INTO calendar(calendar_id, title, content, start_date, end_date, lecture_id) VALUES(calendar_id_seq.NEXTVAL,'java 개강', '개강이다!', TO_DATE('2019-07-01 00:00:00','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2019-07-01 23:59:00','YYYY-MM-DD HH24:MI:SS'),1);
INSERT INTO calendar(calendar_id, title, content, start_date, end_date, lecture_id) VALUES(calendar_id_seq.NEXTVAL,'학원 점검', '000준비 할것', TO_DATE('2019-07-15 00:00:00','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2019-07-16 23:59:00','YYYY-MM-DD HH24:MI:SS'),2);
INSERT INTO calendar(calendar_id, title, content, start_date, end_date, lecture_id) VALUES(calendar_id_seq.NEXTVAL,'2차 발표', '발표 기대하겠습니다.', TO_DATE('2019-07-16 00:00:00','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2019-07-17 23:59:00','YYYY-MM-DD HH24:MI:SS'),1);
INSERT INTO calendar(calendar_id, title, content, start_date, end_date, lecture_id) VALUES(calendar_id_seq.NEXTVAL,'web개강', '개강이다!', TO_DATE('2019-07-25 00:00:00','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2019-07-25 23:59:00','YYYY-MM-DD HH24:MI:SS'),3);
INSERT INTO calendar(calendar_id, title, content, start_date, end_date, lecture_id) VALUES(calendar_id_seq.NEXTVAL,'1차 시험', 'java, sql 시험 있습니다.', TO_DATE('2019-07-25 00:00:00','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2019-07-26 23:59:00','YYYY-MM-DD HH24:MI:SS'),1);
INSERT INTO calendar(calendar_id, title, content, start_date, end_date, lecture_id) VALUES(calendar_id_seq.NEXTVAL,'DB반 종강', '수고하셨습니다.', TO_DATE('2019-07-30 00:00:00','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2019-07-30 23:59:00','YYYY-MM-DD HH24:MI:SS'),1);
INSERT INTO calendar(calendar_id, title, content, start_date, end_date, lecture_id) VALUES(calendar_id_seq.NEXTVAL,'중복!', '삼계탕 먹으로 갑시다', TO_DATE('2019-07-27 00:00:00','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2019-07-27 23:59:00','YYYY-MM-DD HH24:MI:SS'),1);

--apply
INSERT INTO apply(apply_date,apply_id,lecture_id,user_id,file_id) VALUES(sysdate,1,1,'bef1',1);
INSERT INTO apply(apply_date,apply_id,lecture_id,user_id,file_id) VALUES(sysdate,2,1,'bef2',2);
INSERT INTO apply(apply_date,apply_id,lecture_id,user_id,file_id) VALUES(sysdate,3,1,'bef3',3);

--이거 다 버리고
ALTER TABLE Apply DROP CONSTRAINT R_13;
ALTER TABLE Apply
	ADD (CONSTRAINT  R_13 FOREIGN KEY (user_id) REFERENCES User01(user_id) ON DELETE cascade);


ALTER TABLE Apply DROP CONSTRAINT R_14;
ALTER TABLE Apply
	ADD (CONSTRAINT  R_14 FOREIGN KEY (lecture_id) REFERENCES Lecture(lecture_id) ON DELETE cascade);


ALTER TABLE Apply DROP CONSTRAINT R_63;
ALTER TABLE Apply
	ADD (CONSTRAINT  R_63 FOREIGN KEY (file_id) REFERENCES Attached_File(file_id) ON DELETE cascade);


ALTER TABLE Assignment DROP CONSTRAINT R_38;
ALTER TABLE Assignment
	ADD (CONSTRAINT  R_38 FOREIGN KEY (lecture_id) REFERENCES Lecture(lecture_id) ON DELETE cascade);


ALTER TABLE Attached_File DROP CONSTRAINT R_59;
ALTER TABLE Attached_File
	ADD (CONSTRAINT  R_59 FOREIGN KEY (file_group) REFERENCES File_Group(file_group) ON DELETE cascade);


ALTER TABLE Attached_File DROP CONSTRAINT R_64;
ALTER TABLE Attached_File
	ADD (CONSTRAINT  R_64 FOREIGN KEY (reg_id) REFERENCES User01(user_id) ON DELETE cascade);


ALTER TABLE Attendance DROP CONSTRAINT R_11;
ALTER TABLE Attendance
	ADD (CONSTRAINT  R_11 FOREIGN KEY (std_id) REFERENCES User01(user_id) ON DELETE cascade);


ALTER TABLE Attendance DROP CONSTRAINT R_25;
ALTER TABLE Attendance
	ADD (CONSTRAINT  R_25 FOREIGN KEY (lecture_id) REFERENCES Lecture(lecture_id) ON DELETE cascade);


ALTER TABLE Calendar DROP CONSTRAINT R_24;
ALTER TABLE Calendar
	ADD (CONSTRAINT  R_24 FOREIGN KEY (lecture_id) REFERENCES Lecture(lecture_id) ON DELETE cascade);


ALTER TABLE Data_room DROP CONSTRAINT R_39;
ALTER TABLE Data_room
	ADD (CONSTRAINT  R_39 FOREIGN KEY (writer) REFERENCES User01(user_id) ON DELETE cascade);


ALTER TABLE Data_room DROP CONSTRAINT R_61;
ALTER TABLE Data_room
	ADD (CONSTRAINT  R_61 FOREIGN KEY (file_id) REFERENCES Attached_File(file_id) ON DELETE cascade);


ALTER TABLE Lecture DROP CONSTRAINT R_62;
ALTER TABLE Lecture
	ADD (CONSTRAINT  R_62 FOREIGN KEY (file_id) REFERENCES Attached_File(file_id) ON DELETE cascade);


ALTER TABLE Lecture_review DROP CONSTRAINT R_30;
ALTER TABLE Lecture_review
	ADD (CONSTRAINT  R_30 FOREIGN KEY (writer) REFERENCES User01(user_id) ON DELETE cascade);


ALTER TABLE LectureUser DROP CONSTRAINT R_51;
ALTER TABLE LectureUser
	ADD (CONSTRAINT  R_51 FOREIGN KEY (lecture_id) REFERENCES Lecture(lecture_id) ON DELETE cascade);


ALTER TABLE LectureUser DROP CONSTRAINT R_52;
ALTER TABLE LectureUser
	ADD (CONSTRAINT  R_52 FOREIGN KEY (user_id) REFERENCES User01(user_id) ON DELETE cascade);


ALTER TABLE Notice DROP CONSTRAINT R_42;
ALTER TABLE Notice
	ADD (CONSTRAINT  R_42 FOREIGN KEY (writer) REFERENCES User01(user_id) ON DELETE cascade);


ALTER TABLE Qna_H DROP CONSTRAINT R_40;
ALTER TABLE Qna_H
	ADD (CONSTRAINT  R_40 FOREIGN KEY (std_id) REFERENCES User01(user_id) ON DELETE cascade);


ALTER TABLE Qna_L DROP CONSTRAINT R_45;
ALTER TABLE Qna_L
	ADD (CONSTRAINT  R_45 FOREIGN KEY (std_id) REFERENCES User01(user_id) ON DELETE cascade);


ALTER TABLE Qna_L DROP CONSTRAINT R_49;
ALTER TABLE Qna_L
	ADD (CONSTRAINT  R_49 FOREIGN KEY (responder_id) REFERENCES User01(user_id) ON DELETE cascade);


ALTER TABLE Score DROP CONSTRAINT R_16;
ALTER TABLE Score
	ADD (CONSTRAINT  R_16 FOREIGN KEY (std_id) REFERENCES User01(user_id) ON DELETE cascade);


ALTER TABLE Score DROP CONSTRAINT R_17;
ALTER TABLE Score
	ADD (CONSTRAINT  R_17 FOREIGN KEY (lecture_id) REFERENCES Lecture(lecture_id) ON DELETE cascade);


ALTER TABLE Submission DROP CONSTRAINT R_27;
ALTER TABLE Submission
	ADD (CONSTRAINT  R_27 FOREIGN KEY (assignment_id) REFERENCES Assignment(assignment_id) ON DELETE cascade);


ALTER TABLE Submission DROP CONSTRAINT R_28;
ALTER TABLE Submission
	ADD (CONSTRAINT  R_28 FOREIGN KEY (std_id) REFERENCES User01(user_id) ON DELETE cascade);


ALTER TABLE Submission DROP CONSTRAINT R_60;
ALTER TABLE Submission
	ADD (CONSTRAINT  R_60 FOREIGN KEY (file_id) REFERENCES Attached_File(file_id) ON DELETE cascade);



ALTER TABLE Teacher_Info DROP CONSTRAINT R_36;
ALTER TABLE Teacher_Info
	ADD (CONSTRAINT  R_36 FOREIGN KEY (teacher_id) REFERENCES User01(user_id) ON DELETE cascade);
--delete

--파일그룹 미리 만들기
insert into File_Group (file_group,path) values ('profile','C:\java\workspace2\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\BITA_LMS\save\profile');
insert into File_Group (file_group,path) values ('lecture','C:\java\workspace2\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\BITA_LMS\save\lecture');


commit;