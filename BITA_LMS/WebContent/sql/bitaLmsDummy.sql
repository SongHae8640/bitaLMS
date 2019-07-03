DELETE FROM USER01;
DELETE FROM LECTURE;
DELETE FROM LECTUREUSER;

--INSERT할때 자료명과 순서를 같이 넣어줄 것
INSERT INTO user01(user_id,password,name,email,phone_number,inflow_path,belong) values('tea1','1234', 'teacher1', '', '', '', 'teacher');
INSERT INTO user01(user_id,password,name,email,phone_number,inflow_path,belong) values('tea2','1234', 'teacher2', '', '', '', 'teacher');
INSERT INTO user01(user_id,password,name,email,phone_number,inflow_path,belong) values('tea3','1234', 'teacher3', '', '', '', 'teacher');

INSERT INTO user01(user_id,password,name,email,phone_number,inflow_path,belong) values('stu1','1234', 'student1', '', '', '', 'student');
INSERT INTO user01(user_id,password,name,email,phone_number,inflow_path,belong) values('stu2','1234', 'student2', '', '', '', 'student');
INSERT INTO user01(user_id,password,name,email,phone_number,inflow_path,belong) values('stu3','1234', 'student3', '', '', '', 'student');
INSERT INTO user01(user_id,password,name,email,phone_number,inflow_path,belong) values('stu4','1234', 'student4', '', '', '', 'student');
INSERT INTO user01(user_id,password,name,email,phone_number,inflow_path,belong) values('stu5','1234', 'student5', '', '', '', 'student');
INSERT INTO user01(user_id,password,name,email,phone_number,inflow_path,belong) values('stu6','1234', 'student6', '', '', '', 'student');

INSERT INTO lecture(lecture_id,name,start_date,end_date,total_days,num_std,max_std,lv,content,file_name,is_close) values(1,'JAVA',sysdate,sysdate,60,0,30,3,'content','filename','a');
INSERT INTO lecture(lecture_id,name,start_date,end_date,total_days,num_std,max_std,lv,content,file_name,is_close) values(2,'DB',sysdate,sysdate,60,0,30,3,'content','filename','a');
INSERT INTO lecture(lecture_id,name,start_date,end_date,total_days,num_std,max_std,lv,content,file_name,is_close) values(3,'WEB',sysdate,sysdate,60,0,30,3,'content','filename','a');

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

--Attendance
INSERT INTO attendance(day_time,std_id,checkin_time,checkout_time,status,lecture_id) VALUES(TO_DATE('20190701','YYYYMMDD'),'stu1', , ,'출석',1);

commit;

--학생 정보를 구할때 
--SELECT * FROM user01 u JOIN lectureUser l ON u.user_id=l.user_id WHERE u.user_id='tea1' AND u.password='1234';





--