--TEACHER
--lecture_id 가 결합된 테이블
SELECT u.user_id, u.name, lu.lecture_id FROM lectureUser lu JOIN user01 u ON lu.user_id = u.user_id;

--teacher 리스트
SELECT u.user_id, u.name, lu.lecture_id FROM lectureUser lu JOIN user01 u ON lu.user_id = u.user_id WHERE belong = 'teacher';

--teacher > assignment > getAssignmentDetail
SELECT title, name,TO_CHAR(write_date,'yyyy-mm-dd') as write_date ,content  FROM lectureUser lu JOIN user01 u ON lu.user_id = u.user_id JOIN assignment a ON a.lecture_id = lu.lecture_id WHERE u.belong = 'teacher';

--teacher > assignment > getSubmissionList
SELECT row_number() OVER(ORDER BY submit_date) num, file_name,name as std_name ,TO_CHAR(submit_date,'yyyy-mm-dd') as submit_date,is_check FROM submission s JOIN user01 u ON s.std_id = u.user_id WHERE assignment_id=1;

--teacher > qna_l > getQnaList : 번호, 제목, 작성자, 작성일, 답변여부(answer_content의 값 유무로), 분류
--강사가 자신에게 해당하는 qna를 찾는 방법은 responder_id으로 해서 자신만 접근하게 하고(강좌번호로 할까 고민도 함)
--반면 행정에서 찾을때는 type으로 해서 행정 3명 모두 접근 가능하게 할것 
SELECT row_number() OVER(ORDER BY write_date) num, title, name as std_name,TO_CHAR(write_date,'yyyy-mm-dd'),answer_content, type FROM qna_l ql JOIN user01 u ON ql.std_id = u.user_id WHERE responder_id = 'tea1';


--User
SELECT u.user_Id AS user_id, password, u.name AS name, email,phone_number,belong, l.name AS lectureName,start_date,end_date,l.lecture_id AS lecture_id FROM user01 u JOIN lectureuser lu ON u.user_id = lu.user_id JOIN lecture l ON lu.lecture_id = l.lecture_id WHERE u.user_id='tea1' AND u.password='1234';


--admin
