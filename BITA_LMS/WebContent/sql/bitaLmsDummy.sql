--INSERT할때 자료명과 순서를 같이 넣어줄 것

INSERT INTO user01(user_id,password,name,email,phone_number,inflow_path,belong) values('tea1','1234', 'teacher1', '', '', '', 'teacher');
INSERT INTO user01(user_id,password,name,email,phone_number,inflow_path,belong) values('tea2','1234', 'teacher2', '', '', '', 'teacher');
INSERT INTO user01(user_id,password,name,email,phone_number,inflow_path,belong) values('tea3','1234', 'teacher3', '', '', '', 'teacher');

INSERT INTO lecture(lecture_id,name,start_date,end_date,total_days,num_std,max_std,lv,content,file_name,is_close) values(1,'JAVA',sysdate,sysdate,60,0,30,3,'content','filename','a');
INSERT INTO lecture(lecture_id,name,start_date,end_date,total_days,num_std,max_std,lv,content,file_name,is_close) values(2,'DB',sysdate,sysdate,60,0,30,3,'content','filename','a');
INSERT INTO lecture(lecture_id,name,start_date,end_date,total_days,num_std,max_std,lv,content,file_name,is_close) values(3,'WEB',sysdate,sysdate,60,0,30,3,'content','filename','a');

INSERT INTO lectureUser(lecture_id, user_id) values(1,'tea1');
INSERT INTO lectureUser(lecture_id, user_id) values(2,'tea1');
INSERT INTO lectureUser(lecture_id, user_id) values(3,'tea1');
commit;

SELECT u.name, u.password,l.lecture_id FROM user01 u JOIN lectureUser l ON u.user_id=l.user_id WHERE ;