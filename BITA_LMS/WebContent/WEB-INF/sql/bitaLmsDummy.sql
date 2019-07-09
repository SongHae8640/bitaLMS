DELETE FROM USER01;
DELETE FROM LECTURE;
DELETE FROM LECTUREUSER;
DELETE FROM TEACHER_INFO;
DELETE FROM SCORE;
DELETE FROM ASSIGNMENT;
DELETE FROM SUBMISSION;
DELETE FROM QNA_L;
DELETE FROM CALENDAR;

--INSERT�Ҷ� �ڷ��� ������ ���� �־��� ��
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

INSERT INTO lecture(lecture_id,name,start_date,end_date,total_days,num_std,max_std,lv,content,is_close) values(-1,'����',sysdate,sysdate,0,0,0,0,'content','0');
INSERT INTO lecture(lecture_id,name,start_date,end_date,total_days,num_std,max_std,lv,content,is_close) values(0,'�����',sysdate,sysdate,0,0,0,0,'content','0');
INSERT INTO lecture(lecture_id,name,start_date,end_date,total_days,num_std,max_std,lv,content,is_close) values(1,'JAVA',sysdate,sysdate,60,0,30,3,'content','0');
INSERT INTO lecture(lecture_id,name,start_date,end_date,total_days,num_std,max_std,lv,content,is_close) values(2,'DB',sysdate,sysdate,60,0,30,3,'content','0');
INSERT INTO lecture(lecture_id,name,start_date,end_date,total_days,num_std,max_std,lv,content,is_close) values(3,'WEB',sysdate,sysdate,60,0,30,3,'content','0');

--���¸� �Է��Ҷ� LectureUser ���̺��� �߰����ٰ� 
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


--�л� ������ ���Ҷ� 
--SELECT * FROM user01 u JOIN lectureUser l ON u.user_id=l.user_id WHERE u.user_id='tea1' AND u.password='1234';
--SELECT * FROM user01 u JOIN lectureUser l ON u.user_id=l.user_id;

--Teacher_info
INSERT INTO teacher_info(info_id,type ,content,teacher_id) VALUES(1,'�з�','00���� 00�а�','tea1');

--Attendance
INSERT INTO attendance(day_time,std_id,checkin_time,checkout_time,status,lecture_id) VALUES(TO_DATE('20190701','YYYYMMDD'),'stu1',TO_DATE('20190701 09:00:11','YYYYMMDD HH24:MI:SS') ,TO_DATE('20190701 18:00:11','YYYYMMDD HH24:MI:SS') ,'�⼮',1);
INSERT INTO attendance(day_time,std_id,checkin_time,checkout_time,status,lecture_id) VALUES(TO_DATE('20190703','YYYYMMDD'),'stu1',TO_DATE('20190703 09:00:11','YYYYMMDD HH24:MI:SS') ,TO_DATE('20190703 18:00:11','YYYYMMDD HH24:MI:SS') ,'�⼮',1);
INSERT INTO attendance(day_time,std_id,checkin_time,status,lecture_id) VALUES(TO_DATE('20190703','YYYYMMDD'),'stu2',TO_DATE('20190703 09:00:11','YYYYMMDD HH24:MI:SS') , '�⼮',1);
--Score
-- null �̸� �ȵǴϱ� -1�� �־ 0���� ����
INSERT INTO score(lecture_id,std_id,first_score,second_score,third_score,avg_score) VALUES(1,'stu1',90,90,90,90);
INSERT INTO score(lecture_id,std_id,first_score,second_score,third_score,avg_score) VALUES(1,'stu2',80,90,85,85);
INSERT INTO score(lecture_id,std_id,first_score,second_score,third_score,avg_score) VALUES(1,'stu3',80,80,80,80);
INSERT INTO score(lecture_id,std_id,first_score,second_score,third_score,avg_score) VALUES(1,'stu4',70,80,90,80);
INSERT INTO score(lecture_id,std_id,first_score,second_score,third_score,avg_score) VALUES(1,'stu6',70,70,70,70);

--Assignment
INSERT INTO assignment(assignment_id, title, content, lecture_id, write_date) VALUES(1,'����1','����1 ������ �̷��ϴ�.',1,SYSDATE);
INSERT INTO assignment(assignment_id, title, content, lecture_id, write_date) VALUES(2,'����2','����2 �����̴�.',1,SYSDATE);
INSERT INTO assignment(assignment_id, title, content, lecture_id, write_date) VALUES(3,'����2','����3 ����',1,SYSDATE);

--Submission
INSERT INTO submission(assignment_id, std_id,submit_date,file_name,is_check) VALUES(1,'stu1',SYSDATE,'���ϸ�1.doc','0');
INSERT INTO submission(assignment_id, std_id,submit_date,file_name,is_check) VALUES(1,'stu2',SYSDATE,'���ϸ�2.doc','0');
INSERT INTO submission(assignment_id, std_id,submit_date,file_name,is_check) VALUES(1,'stu3',SYSDATE,'���ϸ�3.doc','0');
INSERT INTO submission(assignment_id, std_id,submit_date,file_name,is_check) VALUES(2,'stu2',SYSDATE,'���ϸ�4.doc','0');
INSERT INTO submission(assignment_id, std_id,submit_date,file_name,is_check) VALUES(3,'stu2',SYSDATE,'���ϸ�5.doc','0');

--Qna_L
--null �� ������ �ȵǴϱ� ���� ������ ��ĭ���� �ؼ� �����Ұ�
INSERT INTO qna_l(question_id,std_id, type, title, question_content, responder_id, answer_content, write_date) VALUES(1,'stu1','���ǽ�û','�������� �Դϴ�(����)','������ �߸� ���°� �����ϴ�. ���� ���ּ���.','tea1','�� �װ� �½��ϴ� �� �����ϼ���.',SYSDATE);
INSERT INTO qna_l(question_id,std_id, type, title, question_content, responder_id, answer_content, write_date) VALUES(2,'stu2','��������','�������� �Դϴ�(����)','������ �߸� ���°� �����ϴ�. ���� ���ּ���.','tea1','�� �װ� �½��ϴ� �� �����ϼ���.',SYSDATE);
INSERT INTO qna_l(question_id,std_id, type, title, question_content, responder_id, answer_content, write_date) VALUES(3,'stu3','����','�������� �Դϴ�(����)','������ �߸� ���°� �����ϴ�. ���� ���ּ���.','tea1','�� �װ� �½��ϴ� �� �����ϼ���.',SYSDATE);
INSERT INTO qna_l(question_id,std_id, type, title, question_content, responder_id, answer_content, write_date) VALUES(4,'stu1','����','�������� �Դϴ�(����)','������ �߸� ���°� �����ϴ�. ���� ���ּ���.','admin','�� �װ� �½��ϴ� �� �����ϼ���.',SYSDATE);

--Calendar
INSERT INTO calendar(calendar_id, title, content, start_date, end_date, lecture_id) VALUES(1,'cal1', 'cal1 content', SYSDATE, SYSDATE,1);
INSERT INTO calendar(calendar_id, title, content, start_date, end_date, lecture_id) VALUES(2,'cal2', 'cal2 content', SYSDATE, SYSDATE,2);
INSERT INTO calendar(calendar_id, title, content, start_date, end_date, lecture_id) VALUES(3,'cal3', 'cal3 content', SYSDATE, SYSDATE,3);
INSERT INTO calendar(calendar_id, title, content, start_date, end_date, lecture_id) VALUES(4,'cal4', 'cal4 content', SYSDATE, SYSDATE,1);
INSERT INTO calendar(calendar_id, title, content, start_date, end_date, lecture_id) VALUES(5,'cal5', 'cal5 content', SYSDATE, SYSDATE,2);
INSERT INTO calendar(calendar_id, title, content, start_date, end_date, lecture_id) VALUES(6,'cal6', 'cal6 content', SYSDATE, SYSDATE,1);

commit;