
DROP TABLE Attendance CASCADE CONSTRAINTS PURGE;



DROP TABLE Apply CASCADE CONSTRAINTS PURGE;



DROP TABLE Calendar CASCADE CONSTRAINTS PURGE;



DROP TABLE Score CASCADE CONSTRAINTS PURGE;



DROP TABLE Lecture_review CASCADE CONSTRAINTS PURGE;



DROP TABLE Teacher_Info CASCADE CONSTRAINTS PURGE;



DROP TABLE Submission CASCADE CONSTRAINTS PURGE;



DROP TABLE Assignment CASCADE CONSTRAINTS PURGE;



DROP TABLE Data_room CASCADE CONSTRAINTS PURGE;



DROP TABLE Qna_H CASCADE CONSTRAINTS PURGE;



DROP TABLE Notice CASCADE CONSTRAINTS PURGE;



DROP TABLE Qna_L CASCADE CONSTRAINTS PURGE;



DROP TABLE LectureUser CASCADE CONSTRAINTS PURGE;



DROP TABLE Lecture CASCADE CONSTRAINTS PURGE;



DROP TABLE Attached_File CASCADE CONSTRAINTS PURGE;



DROP TABLE User01 CASCADE CONSTRAINTS PURGE;



DROP TABLE File_Group CASCADE CONSTRAINTS PURGE;



CREATE TABLE Apply
(
	apply_date            DATE  NULL ,
	apply_id              NUMBER  NOT NULL ,
	lecture_id            NUMBER  NULL ,
	user_id               VARCHAR2(20)  NULL ,
	file_id               NUMBER  NULL 
);



CREATE UNIQUE INDEX XPKApply ON Apply
(apply_id  ASC);



ALTER TABLE Apply
	ADD CONSTRAINT  XPKApply PRIMARY KEY (apply_id);



CREATE TABLE Assignment
(
	assignment_id         NUMBER  NOT NULL ,
	title                 VARCHAR2(100)  NULL ,
	content               VARCHAR2(1000)  NULL ,
	lecture_id            NUMBER  NULL ,
	write_date            DATE  NULL 
);



CREATE UNIQUE INDEX XPKAssignment ON Assignment
(assignment_id  ASC);



ALTER TABLE Assignment
	ADD CONSTRAINT  XPKAssignment PRIMARY KEY (assignment_id);



CREATE TABLE Attached_File
(
	file_id               NUMBER  NOT NULL ,
	file_group            VARCHAR2(100)  NULL ,
	original_name         VARCHAR2(4000)  NULL ,
	file_name             VARCHAR2(4000)  NULL ,
	file_extension        VARCHAR2(20)  NULL ,
	ref_date              DATE  NULL ,
	reg_id                VARCHAR2(20)  NULL 
);



CREATE UNIQUE INDEX XPKFile ON Attached_File
(file_id  ASC);



ALTER TABLE Attached_File
	ADD CONSTRAINT  XPKFile PRIMARY KEY (file_id);



CREATE TABLE Attendance
(
	day_time              DATE  NOT NULL ,
	checkin_time          DATE  NULL ,
	checkout_time         DATE  NULL ,
	status                VARCHAR2(10)  NULL ,
	lecture_id            NUMBER  NULL ,
	std_id                VARCHAR2(20)  NOT NULL 
);



CREATE UNIQUE INDEX XPKAttendance ON Attendance
(day_time  ASC,std_id  ASC);



ALTER TABLE Attendance
	ADD CONSTRAINT  XPKAttendance PRIMARY KEY (day_time,std_id);



CREATE TABLE Calendar
(
	calendar_id           NUMBER  NOT NULL ,
	title                 VARCHAR2(100)  NULL ,
	content               VARCHAR2(4000)  NULL ,
	start_date            DATE  NULL ,
	lecture_id            NUMBER  NULL ,
	end_date              DATE  NULL 
);



CREATE UNIQUE INDEX XPKCalendar ON Calendar
(calendar_id  ASC);



ALTER TABLE Calendar
	ADD CONSTRAINT  XPKCalendar PRIMARY KEY (calendar_id);



CREATE TABLE Data_room
(
	data_id               NUMBER  NOT NULL ,
	title                 VARCHAR2(100)  NULL ,
	content               VARCHAR2(1000)  NULL ,
	views                 NUMBER  NULL ,
	write_date            DATE  NULL ,
	writer                VARCHAR2(20)  NULL ,
	file_id               NUMBER  NULL 
);



CREATE UNIQUE INDEX XPKData_romm ON Data_room
(data_id  ASC);



ALTER TABLE Data_room
	ADD CONSTRAINT  XPKData_romm PRIMARY KEY (data_id);



CREATE TABLE File_Group
(
	file_group            VARCHAR2(20)  NOT NULL ,
	path                  VARCHAR2(4000)  NULL 
);



CREATE UNIQUE INDEX XPKFile_Group ON File_Group
(file_group  ASC);



ALTER TABLE File_Group
	ADD CONSTRAINT  XPKFile_Group PRIMARY KEY (file_group);



CREATE TABLE Lecture
(
	lecture_id            NUMBER  NOT NULL ,
	name                  VARCHAR2(20)  NULL ,
	start_date            DATE  NULL ,
	end_date              DATE  NULL ,
	num_std               NUMBER  NULL ,
	total_days            NUMBER  NULL ,
	max_std               NUMBER  NULL ,
	lv                    NUMBER  NULL ,
	content               VARCHAR2(4000)  NULL ,
	is_close              VARCHAR2(1)  NULL ,
	curri_id              NUMBER  NULL ,
	file_id               NUMBER  NULL 
);



CREATE UNIQUE INDEX XPKLecture ON Lecture
(lecture_id  ASC);



ALTER TABLE Lecture
	ADD CONSTRAINT  XPKLecture PRIMARY KEY (lecture_id);



CREATE TABLE Lecture_review
(
	review_id             NUMBER  NOT NULL ,
	title                 VARCHAR2(100)  NULL ,
	write_date            DATE  NULL ,
	content               VARCHAR2(4000)  NULL ,
	views                 NUMBER  NULL ,
	writer                VARCHAR2(20)  NULL 
);



CREATE UNIQUE INDEX XPKLecture_review ON Lecture_review
(review_id  ASC);



ALTER TABLE Lecture_review
	ADD CONSTRAINT  XPKLecture_review PRIMARY KEY (review_id);



CREATE TABLE LectureUser
(
	lecture_id            NUMBER  NOT NULL ,
	user_id               VARCHAR2(20)  NOT NULL 
);



CREATE UNIQUE INDEX XPKLectureUser ON LectureUser
(lecture_id  ASC,user_id  ASC);



ALTER TABLE LectureUser
	ADD CONSTRAINT  XPKLectureUser PRIMARY KEY (lecture_id,user_id);



CREATE TABLE Notice
(
	notice_id             NUMBER  NOT NULL ,
	title                 VARCHAR2(100)  NULL ,
	write_date            DATE  NULL ,
	views                 NUMBER  NULL ,
	content               VARCHAR2(4000)  NULL ,
	writer                VARCHAR2(20)  NULL 
);



CREATE UNIQUE INDEX XPKNotice ON Notice
(notice_id  ASC);



ALTER TABLE Notice
	ADD CONSTRAINT  XPKNotice PRIMARY KEY (notice_id);



CREATE TABLE Qna_H
(
	qnaH_id               NUMBER  NOT NULL ,
	title                 VARCHAR2(100)  NULL ,
	qusetion_content      VARCHAR2(4000)  NULL ,
	answer_content        VARCHAR2(4000)  NULL ,
	password              NUMBER(4)  NULL ,
	is_public             VARCHAR2(1)  NULL ,
	views                 NUMBER  NULL ,
	write_date            DATE  NULL ,
	std_id                VARCHAR2(20)  NULL 
);



CREATE UNIQUE INDEX XPKQNA_H ON Qna_H
(qnaH_id  ASC);



ALTER TABLE Qna_H
	ADD CONSTRAINT  XPKQNA_H PRIMARY KEY (qnaH_id);



CREATE TABLE Qna_L
(
	qnaL_id               NUMBER  NOT NULL ,
	title                 VARCHAR2(100)  NULL ,
	question_content      VARCHAR2(1000)  NULL ,
	write_date            DATE  NULL ,
	answer_content        VARCHAR2(1000)  NULL ,
	type                  VARCHAR2(20)  NULL ,
	std_id                VARCHAR2(20)  NULL ,
	is_check              VARCHAR2(1)  NULL 
);



CREATE UNIQUE INDEX XPKQNA_L ON Qna_L
(qnaL_id  ASC);



ALTER TABLE Qna_L
	ADD CONSTRAINT  XPKQNA_L PRIMARY KEY (qnaL_id);



CREATE TABLE Score
(
	lecture_id            NUMBER  NOT NULL ,
	first_score           NUMBER(3)  NULL ,
	second_score          NUMBER(3)  NULL ,
	third_score           NUMBER(3)  NULL ,
	std_id                VARCHAR2(20)  NOT NULL ,
	avg_score             NUMBER(6,2)  NULL 
);



CREATE UNIQUE INDEX XPKScore ON Score
(lecture_id  ASC,std_id  ASC);



ALTER TABLE Score
	ADD CONSTRAINT  XPKScore PRIMARY KEY (lecture_id,std_id);



CREATE TABLE Submission
(
	assignment_id         NUMBER  NOT NULL ,
	submit_date           DATE  NULL ,
	is_check              VARCHAR2(1)  NULL ,
	std_id                VARCHAR2(20)  NOT NULL ,
	file_id               NUMBER  NULL 
);



CREATE UNIQUE INDEX XPKSubmission ON Submission
(assignment_id  ASC,std_id  ASC);



ALTER TABLE Submission
	ADD CONSTRAINT  XPKSubmission PRIMARY KEY (assignment_id,std_id);



CREATE TABLE Teacher_Info
(
	info_id               NUMBER  NOT NULL ,
	type                  VARCHAR2(20)  NULL ,
	content               VARCHAR2(1000)  NULL ,
	teacher_id            VARCHAR2(20)  NULL 
);



CREATE UNIQUE INDEX XPKTeacher_info ON Teacher_Info
(info_id  ASC);



ALTER TABLE Teacher_Info
	ADD CONSTRAINT  XPKTeacher_info PRIMARY KEY (info_id);



CREATE TABLE User01
(
	user_id               VARCHAR2(20)  NOT NULL ,
	password              VARCHAR2(20)  NULL ,
	name                  VARCHAR2(20)  NULL ,
	email                 VARCHAR2(100)  NULL ,
	phone_number          VARCHAR2(20)  NULL ,
	inflow_path           VARCHAR2(100)  NULL ,
	belong                VARCHAR2(20)  NULL 
);



CREATE UNIQUE INDEX XPKUser01 ON User01
(user_id  ASC);



ALTER TABLE User01
	ADD CONSTRAINT  XPKUser01 PRIMARY KEY (user_id);



ALTER TABLE Apply
	ADD (CONSTRAINT  R_13 FOREIGN KEY (user_id) REFERENCES User01(user_id) ON DELETE CASCADE);



ALTER TABLE Apply
	ADD (CONSTRAINT  R_14 FOREIGN KEY (lecture_id) REFERENCES Lecture(lecture_id) ON DELETE CASCADE);



ALTER TABLE Apply
	ADD (CONSTRAINT  R_63 FOREIGN KEY (file_id) REFERENCES Attached_File(file_id) ON DELETE CASCADE);



ALTER TABLE Assignment
	ADD (CONSTRAINT  R_38 FOREIGN KEY (lecture_id) REFERENCES Lecture(lecture_id) ON DELETE CASCADE);



ALTER TABLE Attached_File
	ADD (CONSTRAINT  R_59 FOREIGN KEY (file_group) REFERENCES File_Group(file_group) ON DELETE CASCADE);



ALTER TABLE Attached_File
	ADD (CONSTRAINT  R_64 FOREIGN KEY (reg_id) REFERENCES User01(user_id) ON DELETE CASCADE);



ALTER TABLE Attendance
	ADD (CONSTRAINT  R_11 FOREIGN KEY (std_id) REFERENCES User01(user_id));



ALTER TABLE Attendance
	ADD (CONSTRAINT  R_25 FOREIGN KEY (lecture_id) REFERENCES Lecture(lecture_id) ON DELETE CASCADE);



ALTER TABLE Calendar
	ADD (CONSTRAINT  R_24 FOREIGN KEY (lecture_id) REFERENCES Lecture(lecture_id) ON DELETE CASCADE);



ALTER TABLE Data_room
	ADD (CONSTRAINT  R_39 FOREIGN KEY (writer) REFERENCES User01(user_id) ON DELETE CASCADE);



ALTER TABLE Data_room
	ADD (CONSTRAINT  R_61 FOREIGN KEY (file_id) REFERENCES Attached_File(file_id) ON DELETE CASCADE);



ALTER TABLE Lecture
	ADD (CONSTRAINT  R_62 FOREIGN KEY (curri_id) REFERENCES Attached_File(file_id) ON DELETE CASCADE);



ALTER TABLE Lecture
	ADD (CONSTRAINT  R_66 FOREIGN KEY (file_id) REFERENCES Attached_File(file_id) ON DELETE CASCADE);



ALTER TABLE Lecture_review
	ADD (CONSTRAINT  R_30 FOREIGN KEY (writer) REFERENCES User01(user_id) ON DELETE CASCADE);



ALTER TABLE LectureUser
	ADD (CONSTRAINT  R_51 FOREIGN KEY (lecture_id) REFERENCES Lecture(lecture_id));



ALTER TABLE LectureUser
	ADD (CONSTRAINT  R_52 FOREIGN KEY (user_id) REFERENCES User01(user_id));



ALTER TABLE Notice
	ADD (CONSTRAINT  R_42 FOREIGN KEY (writer) REFERENCES User01(user_id) ON DELETE CASCADE);



ALTER TABLE Qna_H
	ADD (CONSTRAINT  R_40 FOREIGN KEY (std_id) REFERENCES User01(user_id) ON DELETE CASCADE);



ALTER TABLE Qna_L
	ADD (CONSTRAINT  R_45 FOREIGN KEY (std_id) REFERENCES User01(user_id) ON DELETE CASCADE);



ALTER TABLE Score
	ADD (CONSTRAINT  R_16 FOREIGN KEY (std_id) REFERENCES User01(user_id));



ALTER TABLE Score
	ADD (CONSTRAINT  R_17 FOREIGN KEY (lecture_id) REFERENCES Lecture(lecture_id));



ALTER TABLE Submission
	ADD (CONSTRAINT  R_27 FOREIGN KEY (assignment_id) REFERENCES Assignment(assignment_id));



ALTER TABLE Submission
	ADD (CONSTRAINT  R_28 FOREIGN KEY (std_id) REFERENCES User01(user_id));



ALTER TABLE Submission
	ADD (CONSTRAINT  R_60 FOREIGN KEY (file_id) REFERENCES Attached_File(file_id) ON DELETE CASCADE);



ALTER TABLE Teacher_Info
	ADD (CONSTRAINT  R_36 FOREIGN KEY (teacher_id) REFERENCES User01(user_id) ON DELETE CASCADE);



CREATE  TRIGGER tI_Apply BEFORE INSERT ON Apply for each row
-- ERwin Builtin 2019년 7월 15일 월요일 오후 5:27:08
-- INSERT trigger on Apply 
DECLARE NUMROWS INTEGER;
BEGIN
    /* ERwin Builtin 2019년 7월 15일 월요일 오후 5:27:08 */
    /* User01  Apply on child insert set null */
    /* ERWIN_RELATION:CHECKSUM="0003124a", PARENT_OWNER="", PARENT_TABLE="User01"
    CHILD_OWNER="", CHILD_TABLE="Apply"
    P2C_VERB_PHRASE="R/13", C2P_VERB_PHRASE="", 
    FK_CONSTRAINT="R_13", FK_COLUMNS="user_id" */
    UPDATE Apply
      SET
        /* %SetFK(Apply,NULL) */
        Apply.user_id = NULL
      WHERE
        NOT EXISTS (
          SELECT * FROM User01
            WHERE
              /* %JoinFKPK(:%New,User01," = "," AND") */
              :new.user_id = User01.user_id
        ) 
        /* %JoinPKPK(Apply,:%New," = "," AND") */
         and Apply.apply_id = :new.apply_id;

    /* ERwin Builtin 2019년 7월 15일 월요일 오후 5:27:08 */
    /* Lecture  Apply on child insert set null */
    /* ERWIN_RELATION:CHECKSUM="00000000", PARENT_OWNER="", PARENT_TABLE="Lecture"
    CHILD_OWNER="", CHILD_TABLE="Apply"
    P2C_VERB_PHRASE="R/14", C2P_VERB_PHRASE="", 
    FK_CONSTRAINT="R_14", FK_COLUMNS="lecture_id" */
    UPDATE Apply
      SET
        /* %SetFK(Apply,NULL) */
        Apply.lecture_id = NULL
      WHERE
        NOT EXISTS (
          SELECT * FROM Lecture
            WHERE
              /* %JoinFKPK(:%New,Lecture," = "," AND") */
              :new.lecture_id = Lecture.lecture_id
        ) 
        /* %JoinPKPK(Apply,:%New," = "," AND") */
         and Apply.apply_id = :new.apply_id;

    /* ERwin Builtin 2019년 7월 15일 월요일 오후 5:27:08 */
    /* Attached_File  Apply on child insert set null */
    /* ERWIN_RELATION:CHECKSUM="00000000", PARENT_OWNER="", PARENT_TABLE="Attached_File"
    CHILD_OWNER="", CHILD_TABLE="Apply"
    P2C_VERB_PHRASE="R/63", C2P_VERB_PHRASE="", 
    FK_CONSTRAINT="R_63", FK_COLUMNS="file_id" */
    UPDATE Apply
      SET
        /* %SetFK(Apply,NULL) */
        Apply.file_id = NULL
      WHERE
        NOT EXISTS (
          SELECT * FROM Attached_File
            WHERE
              /* %JoinFKPK(:%New,Attached_File," = "," AND") */
              :new.file_id = Attached_File.file_id
        ) 
        /* %JoinPKPK(Apply,:%New," = "," AND") */
         and Apply.apply_id = :new.apply_id;


-- ERwin Builtin 2019년 7월 15일 월요일 오후 5:27:08
END;
/

CREATE  TRIGGER tU_Apply AFTER UPDATE ON Apply for each row
-- ERwin Builtin 2019년 7월 15일 월요일 오후 5:27:08
-- UPDATE trigger on Apply 
DECLARE NUMROWS INTEGER;
BEGIN
  /* ERwin Builtin 2019년 7월 15일 월요일 오후 5:27:08 */
  /* User01  Apply on child update no action */
  /* ERWIN_RELATION:CHECKSUM="00033380", PARENT_OWNER="", PARENT_TABLE="User01"
    CHILD_OWNER="", CHILD_TABLE="Apply"
    P2C_VERB_PHRASE="R/13", C2P_VERB_PHRASE="", 
    FK_CONSTRAINT="R_13", FK_COLUMNS="user_id" */
  SELECT count(*) INTO NUMROWS
    FROM User01
    WHERE
      /* %JoinFKPK(:%New,User01," = "," AND") */
      :new.user_id = User01.user_id;
  IF (
    /* %NotnullFK(:%New," IS NOT NULL AND") */
    :new.user_id IS NOT NULL AND
    NUMROWS = 0
  )
  THEN
    raise_application_error(
      -20007,
      'Cannot update Apply because User01 does not exist.'
    );
  END IF;

  /* ERwin Builtin 2019년 7월 15일 월요일 오후 5:27:08 */
  /* Lecture  Apply on child update no action */
  /* ERWIN_RELATION:CHECKSUM="00000000", PARENT_OWNER="", PARENT_TABLE="Lecture"
    CHILD_OWNER="", CHILD_TABLE="Apply"
    P2C_VERB_PHRASE="R/14", C2P_VERB_PHRASE="", 
    FK_CONSTRAINT="R_14", FK_COLUMNS="lecture_id" */
  SELECT count(*) INTO NUMROWS
    FROM Lecture
    WHERE
      /* %JoinFKPK(:%New,Lecture," = "," AND") */
      :new.lecture_id = Lecture.lecture_id;
  IF (
    /* %NotnullFK(:%New," IS NOT NULL AND") */
    :new.lecture_id IS NOT NULL AND
    NUMROWS = 0
  )
  THEN
    raise_application_error(
      -20007,
      'Cannot update Apply because Lecture does not exist.'
    );
  END IF;

  /* ERwin Builtin 2019년 7월 15일 월요일 오후 5:27:08 */
  /* Attached_File  Apply on child update no action */
  /* ERWIN_RELATION:CHECKSUM="00000000", PARENT_OWNER="", PARENT_TABLE="Attached_File"
    CHILD_OWNER="", CHILD_TABLE="Apply"
    P2C_VERB_PHRASE="R/63", C2P_VERB_PHRASE="", 
    FK_CONSTRAINT="R_63", FK_COLUMNS="file_id" */
  SELECT count(*) INTO NUMROWS
    FROM Attached_File
    WHERE
      /* %JoinFKPK(:%New,Attached_File," = "," AND") */
      :new.file_id = Attached_File.file_id;
  IF (
    /* %NotnullFK(:%New," IS NOT NULL AND") */
    :new.file_id IS NOT NULL AND
    NUMROWS = 0
  )
  THEN
    raise_application_error(
      -20007,
      'Cannot update Apply because Attached_File does not exist.'
    );
  END IF;


-- ERwin Builtin 2019년 7월 15일 월요일 오후 5:27:08
END;
/


CREATE  TRIGGER tI_Assignment BEFORE INSERT ON Assignment for each row
-- ERwin Builtin 2019년 7월 15일 월요일 오후 5:27:08
-- INSERT trigger on Assignment 
DECLARE NUMROWS INTEGER;
BEGIN
    /* ERwin Builtin 2019년 7월 15일 월요일 오후 5:27:08 */
    /* Lecture  Assignment on child insert set null */
    /* ERWIN_RELATION:CHECKSUM="0001060f", PARENT_OWNER="", PARENT_TABLE="Lecture"
    CHILD_OWNER="", CHILD_TABLE="Assignment"
    P2C_VERB_PHRASE="R/38", C2P_VERB_PHRASE="", 
    FK_CONSTRAINT="R_38", FK_COLUMNS="lecture_id" */
    UPDATE Assignment
      SET
        /* %SetFK(Assignment,NULL) */
        Assignment.lecture_id = NULL
      WHERE
        NOT EXISTS (
          SELECT * FROM Lecture
            WHERE
              /* %JoinFKPK(:%New,Lecture," = "," AND") */
              :new.lecture_id = Lecture.lecture_id
        ) 
        /* %JoinPKPK(Assignment,:%New," = "," AND") */
         and Assignment.assignment_id = :new.assignment_id;


-- ERwin Builtin 2019년 7월 15일 월요일 오후 5:27:08
END;
/

CREATE  TRIGGER tD_Assignment AFTER DELETE ON Assignment for each row
-- ERwin Builtin 2019년 7월 15일 월요일 오후 5:27:08
-- DELETE trigger on Assignment 
DECLARE NUMROWS INTEGER;
BEGIN
    /* ERwin Builtin 2019년 7월 15일 월요일 오후 5:27:08 */
    /* Assignment  Submission on parent delete restrict */
    /* ERWIN_RELATION:CHECKSUM="0000f859", PARENT_OWNER="", PARENT_TABLE="Assignment"
    CHILD_OWNER="", CHILD_TABLE="Submission"
    P2C_VERB_PHRASE="R/27", C2P_VERB_PHRASE="", 
    FK_CONSTRAINT="R_27", FK_COLUMNS="assignment_id" */
    SELECT count(*) INTO NUMROWS
      FROM Submission
      WHERE
        /*  %JoinFKPK(Submission,:%Old," = "," AND") */
        Submission.assignment_id = :old.assignment_id;
    IF (NUMROWS > 0)
    THEN
      raise_application_error(
        -20001,
        'Cannot delete Assignment because Submission exists.'
      );
    END IF;


-- ERwin Builtin 2019년 7월 15일 월요일 오후 5:27:08
END;
/

CREATE  TRIGGER tU_Assignment AFTER UPDATE ON Assignment for each row
-- ERwin Builtin 2019년 7월 15일 월요일 오후 5:27:08
-- UPDATE trigger on Assignment 
DECLARE NUMROWS INTEGER;
BEGIN
  /* ERwin Builtin 2019년 7월 15일 월요일 오후 5:27:08 */
  /* Assignment  Submission on parent update restrict */
  /* ERWIN_RELATION:CHECKSUM="000239c1", PARENT_OWNER="", PARENT_TABLE="Assignment"
    CHILD_OWNER="", CHILD_TABLE="Submission"
    P2C_VERB_PHRASE="R/27", C2P_VERB_PHRASE="", 
    FK_CONSTRAINT="R_27", FK_COLUMNS="assignment_id" */
  IF
    /* %JoinPKPK(:%Old,:%New," <> "," OR ") */
    :old.assignment_id <> :new.assignment_id
  THEN
    SELECT count(*) INTO NUMROWS
      FROM Submission
      WHERE
        /*  %JoinFKPK(Submission,:%Old," = "," AND") */
        Submission.assignment_id = :old.assignment_id;
    IF (NUMROWS > 0)
    THEN 
      raise_application_error(
        -20005,
        'Cannot update Assignment because Submission exists.'
      );
    END IF;
  END IF;

  /* ERwin Builtin 2019년 7월 15일 월요일 오후 5:27:08 */
  /* Lecture  Assignment on child update no action */
  /* ERWIN_RELATION:CHECKSUM="00000000", PARENT_OWNER="", PARENT_TABLE="Lecture"
    CHILD_OWNER="", CHILD_TABLE="Assignment"
    P2C_VERB_PHRASE="R/38", C2P_VERB_PHRASE="", 
    FK_CONSTRAINT="R_38", FK_COLUMNS="lecture_id" */
  SELECT count(*) INTO NUMROWS
    FROM Lecture
    WHERE
      /* %JoinFKPK(:%New,Lecture," = "," AND") */
      :new.lecture_id = Lecture.lecture_id;
  IF (
    /* %NotnullFK(:%New," IS NOT NULL AND") */
    :new.lecture_id IS NOT NULL AND
    NUMROWS = 0
  )
  THEN
    raise_application_error(
      -20007,
      'Cannot update Assignment because Lecture does not exist.'
    );
  END IF;


-- ERwin Builtin 2019년 7월 15일 월요일 오후 5:27:08
END;
/


CREATE  TRIGGER tI_Attached_File BEFORE INSERT ON Attached_File for each row
-- ERwin Builtin 2019년 7월 15일 월요일 오후 5:27:08
-- INSERT trigger on Attached_File 
DECLARE NUMROWS INTEGER;
BEGIN
    /* ERwin Builtin 2019년 7월 15일 월요일 오후 5:27:08 */
    /* File_Group  Attached_File on child insert set null */
    /* ERWIN_RELATION:CHECKSUM="00021514", PARENT_OWNER="", PARENT_TABLE="File_Group"
    CHILD_OWNER="", CHILD_TABLE="Attached_File"
    P2C_VERB_PHRASE="R/59", C2P_VERB_PHRASE="", 
    FK_CONSTRAINT="R_59", FK_COLUMNS="file_group" */
    UPDATE Attached_File
      SET
        /* %SetFK(Attached_File,NULL) */
        Attached_File.file_group = NULL
      WHERE
        NOT EXISTS (
          SELECT * FROM File_Group
            WHERE
              /* %JoinFKPK(:%New,File_Group," = "," AND") */
              :new.file_group = File_Group.file_group
        ) 
        /* %JoinPKPK(Attached_File,:%New," = "," AND") */
         and Attached_File.file_id = :new.file_id;

    /* ERwin Builtin 2019년 7월 15일 월요일 오후 5:27:08 */
    /* User01  Attached_File on child insert set null */
    /* ERWIN_RELATION:CHECKSUM="00000000", PARENT_OWNER="", PARENT_TABLE="User01"
    CHILD_OWNER="", CHILD_TABLE="Attached_File"
    P2C_VERB_PHRASE="R/64", C2P_VERB_PHRASE="", 
    FK_CONSTRAINT="R_64", FK_COLUMNS="reg_id" */
    UPDATE Attached_File
      SET
        /* %SetFK(Attached_File,NULL) */
        Attached_File.reg_id = NULL
      WHERE
        NOT EXISTS (
          SELECT * FROM User01
            WHERE
              /* %JoinFKPK(:%New,User01," = "," AND") */
              :new.reg_id = User01.user_id
        ) 
        /* %JoinPKPK(Attached_File,:%New," = "," AND") */
         and Attached_File.file_id = :new.file_id;


-- ERwin Builtin 2019년 7월 15일 월요일 오후 5:27:08
END;
/

CREATE  TRIGGER tD_Attached_File AFTER DELETE ON Attached_File for each row
-- ERwin Builtin 2019년 7월 15일 월요일 오후 5:27:08
-- DELETE trigger on Attached_File 
DECLARE NUMROWS INTEGER;
BEGIN
    /* ERwin Builtin 2019년 7월 15일 월요일 오후 5:27:08 */
    /* Attached_File  Submission on parent delete set null */
    /* ERWIN_RELATION:CHECKSUM="00040e16", PARENT_OWNER="", PARENT_TABLE="Attached_File"
    CHILD_OWNER="", CHILD_TABLE="Submission"
    P2C_VERB_PHRASE="R/60", C2P_VERB_PHRASE="", 
    FK_CONSTRAINT="R_60", FK_COLUMNS="file_id" */
    UPDATE Submission
      SET
        /* %SetFK(Submission,NULL) */
        Submission.file_id = NULL
      WHERE
        /* %JoinFKPK(Submission,:%Old," = "," AND") */
        Submission.file_id = :old.file_id;

    /* ERwin Builtin 2019년 7월 15일 월요일 오후 5:27:08 */
    /* Attached_File  Data_room on parent delete set null */
    /* ERWIN_RELATION:CHECKSUM="00000000", PARENT_OWNER="", PARENT_TABLE="Attached_File"
    CHILD_OWNER="", CHILD_TABLE="Data_room"
    P2C_VERB_PHRASE="R/61", C2P_VERB_PHRASE="", 
    FK_CONSTRAINT="R_61", FK_COLUMNS="file_id" */
    UPDATE Data_room
      SET
        /* %SetFK(Data_room,NULL) */
        Data_room.file_id = NULL
      WHERE
        /* %JoinFKPK(Data_room,:%Old," = "," AND") */
        Data_room.file_id = :old.file_id;

    /* ERwin Builtin 2019년 7월 15일 월요일 오후 5:27:08 */
    /* Attached_File  Lecture on parent delete set null */
    /* ERWIN_RELATION:CHECKSUM="00000000", PARENT_OWNER="", PARENT_TABLE="Attached_File"
    CHILD_OWNER="", CHILD_TABLE="Lecture"
    P2C_VERB_PHRASE="R/62", C2P_VERB_PHRASE="", 
    FK_CONSTRAINT="R_62", FK_COLUMNS="curri_id" */
    UPDATE Lecture
      SET
        /* %SetFK(Lecture,NULL) */
        Lecture.curri_id = NULL
      WHERE
        /* %JoinFKPK(Lecture,:%Old," = "," AND") */
        Lecture.curri_id = :old.file_id;

    /* ERwin Builtin 2019년 7월 15일 월요일 오후 5:27:08 */
    /* Attached_File  Apply on parent delete set null */
    /* ERWIN_RELATION:CHECKSUM="00000000", PARENT_OWNER="", PARENT_TABLE="Attached_File"
    CHILD_OWNER="", CHILD_TABLE="Apply"
    P2C_VERB_PHRASE="R/63", C2P_VERB_PHRASE="", 
    FK_CONSTRAINT="R_63", FK_COLUMNS="file_id" */
    UPDATE Apply
      SET
        /* %SetFK(Apply,NULL) */
        Apply.file_id = NULL
      WHERE
        /* %JoinFKPK(Apply,:%Old," = "," AND") */
        Apply.file_id = :old.file_id;

    /* ERwin Builtin 2019년 7월 15일 월요일 오후 5:27:08 */
    /* Attached_File  Lecture on parent delete set null */
    /* ERWIN_RELATION:CHECKSUM="00000000", PARENT_OWNER="", PARENT_TABLE="Attached_File"
    CHILD_OWNER="", CHILD_TABLE="Lecture"
    P2C_VERB_PHRASE="R/66", C2P_VERB_PHRASE="", 
    FK_CONSTRAINT="R_66", FK_COLUMNS="file_id" */
    UPDATE Lecture
      SET
        /* %SetFK(Lecture,NULL) */
        Lecture.file_id = NULL
      WHERE
        /* %JoinFKPK(Lecture,:%Old," = "," AND") */
        Lecture.file_id = :old.file_id;


-- ERwin Builtin 2019년 7월 15일 월요일 오후 5:27:08
END;
/

CREATE  TRIGGER tU_Attached_File AFTER UPDATE ON Attached_File for each row
-- ERwin Builtin 2019년 7월 15일 월요일 오후 5:27:08
-- UPDATE trigger on Attached_File 
DECLARE NUMROWS INTEGER;
BEGIN
  /* Attached_File  Submission on parent update set null */
  /* ERWIN_RELATION:CHECKSUM="0006a791", PARENT_OWNER="", PARENT_TABLE="Attached_File"
    CHILD_OWNER="", CHILD_TABLE="Submission"
    P2C_VERB_PHRASE="R/60", C2P_VERB_PHRASE="", 
    FK_CONSTRAINT="R_60", FK_COLUMNS="file_id" */
  IF
    /* %JoinPKPK(:%Old,:%New," <> "," OR ") */
    :old.file_id <> :new.file_id
  THEN
    UPDATE Submission
      SET
        /* %SetFK(Submission,NULL) */
        Submission.file_id = NULL
      WHERE
        /* %JoinFKPK(Submission,:%Old," = ",",") */
        Submission.file_id = :old.file_id;
  END IF;

  /* Attached_File  Data_room on parent update set null */
  /* ERWIN_RELATION:CHECKSUM="00000000", PARENT_OWNER="", PARENT_TABLE="Attached_File"
    CHILD_OWNER="", CHILD_TABLE="Data_room"
    P2C_VERB_PHRASE="R/61", C2P_VERB_PHRASE="", 
    FK_CONSTRAINT="R_61", FK_COLUMNS="file_id" */
  IF
    /* %JoinPKPK(:%Old,:%New," <> "," OR ") */
    :old.file_id <> :new.file_id
  THEN
    UPDATE Data_room
      SET
        /* %SetFK(Data_room,NULL) */
        Data_room.file_id = NULL
      WHERE
        /* %JoinFKPK(Data_room,:%Old," = ",",") */
        Data_room.file_id = :old.file_id;
  END IF;

  /* Attached_File  Lecture on parent update set null */
  /* ERWIN_RELATION:CHECKSUM="00000000", PARENT_OWNER="", PARENT_TABLE="Attached_File"
    CHILD_OWNER="", CHILD_TABLE="Lecture"
    P2C_VERB_PHRASE="R/62", C2P_VERB_PHRASE="", 
    FK_CONSTRAINT="R_62", FK_COLUMNS="curri_id" */
  IF
    /* %JoinPKPK(:%Old,:%New," <> "," OR ") */
    :old.file_id <> :new.file_id
  THEN
    UPDATE Lecture
      SET
        /* %SetFK(Lecture,NULL) */
        Lecture.curri_id = NULL
      WHERE
        /* %JoinFKPK(Lecture,:%Old," = ",",") */
        Lecture.curri_id = :old.file_id;
  END IF;

  /* Attached_File  Apply on parent update set null */
  /* ERWIN_RELATION:CHECKSUM="00000000", PARENT_OWNER="", PARENT_TABLE="Attached_File"
    CHILD_OWNER="", CHILD_TABLE="Apply"
    P2C_VERB_PHRASE="R/63", C2P_VERB_PHRASE="", 
    FK_CONSTRAINT="R_63", FK_COLUMNS="file_id" */
  IF
    /* %JoinPKPK(:%Old,:%New," <> "," OR ") */
    :old.file_id <> :new.file_id
  THEN
    UPDATE Apply
      SET
        /* %SetFK(Apply,NULL) */
        Apply.file_id = NULL
      WHERE
        /* %JoinFKPK(Apply,:%Old," = ",",") */
        Apply.file_id = :old.file_id;
  END IF;

  /* Attached_File  Lecture on parent update set null */
  /* ERWIN_RELATION:CHECKSUM="00000000", PARENT_OWNER="", PARENT_TABLE="Attached_File"
    CHILD_OWNER="", CHILD_TABLE="Lecture"
    P2C_VERB_PHRASE="R/66", C2P_VERB_PHRASE="", 
    FK_CONSTRAINT="R_66", FK_COLUMNS="file_id" */
  IF
    /* %JoinPKPK(:%Old,:%New," <> "," OR ") */
    :old.file_id <> :new.file_id
  THEN
    UPDATE Lecture
      SET
        /* %SetFK(Lecture,NULL) */
        Lecture.file_id = NULL
      WHERE
        /* %JoinFKPK(Lecture,:%Old," = ",",") */
        Lecture.file_id = :old.file_id;
  END IF;

  /* ERwin Builtin 2019년 7월 15일 월요일 오후 5:27:08 */
  /* File_Group  Attached_File on child update no action */
  /* ERWIN_RELATION:CHECKSUM="00000000", PARENT_OWNER="", PARENT_TABLE="File_Group"
    CHILD_OWNER="", CHILD_TABLE="Attached_File"
    P2C_VERB_PHRASE="R/59", C2P_VERB_PHRASE="", 
    FK_CONSTRAINT="R_59", FK_COLUMNS="file_group" */
  SELECT count(*) INTO NUMROWS
    FROM File_Group
    WHERE
      /* %JoinFKPK(:%New,File_Group," = "," AND") */
      :new.file_group = File_Group.file_group;
  IF (
    /* %NotnullFK(:%New," IS NOT NULL AND") */
    :new.file_group IS NOT NULL AND
    NUMROWS = 0
  )
  THEN
    raise_application_error(
      -20007,
      'Cannot update Attached_File because File_Group does not exist.'
    );
  END IF;

  /* ERwin Builtin 2019년 7월 15일 월요일 오후 5:27:08 */
  /* User01  Attached_File on child update no action */
  /* ERWIN_RELATION:CHECKSUM="00000000", PARENT_OWNER="", PARENT_TABLE="User01"
    CHILD_OWNER="", CHILD_TABLE="Attached_File"
    P2C_VERB_PHRASE="R/64", C2P_VERB_PHRASE="", 
    FK_CONSTRAINT="R_64", FK_COLUMNS="reg_id" */
  SELECT count(*) INTO NUMROWS
    FROM User01
    WHERE
      /* %JoinFKPK(:%New,User01," = "," AND") */
      :new.reg_id = User01.user_id;
  IF (
    /* %NotnullFK(:%New," IS NOT NULL AND") */
    :new.reg_id IS NOT NULL AND
    NUMROWS = 0
  )
  THEN
    raise_application_error(
      -20007,
      'Cannot update Attached_File because User01 does not exist.'
    );
  END IF;


-- ERwin Builtin 2019년 7월 15일 월요일 오후 5:27:08
END;
/


CREATE  TRIGGER tI_Attendance BEFORE INSERT ON Attendance for each row
-- ERwin Builtin 2019년 7월 15일 월요일 오후 5:27:08
-- INSERT trigger on Attendance 
DECLARE NUMROWS INTEGER;
BEGIN
    /* ERwin Builtin 2019년 7월 15일 월요일 오후 5:27:08 */
    /* User01  Attendance on child insert restrict */
    /* ERWIN_RELATION:CHECKSUM="0002061d", PARENT_OWNER="", PARENT_TABLE="User01"
    CHILD_OWNER="", CHILD_TABLE="Attendance"
    P2C_VERB_PHRASE="R/11", C2P_VERB_PHRASE="", 
    FK_CONSTRAINT="R_11", FK_COLUMNS="std_id" */
    SELECT count(*) INTO NUMROWS
      FROM User01
      WHERE
        /* %JoinFKPK(:%New,User01," = "," AND") */
        :new.std_id = User01.user_id;
    IF (
      /* %NotnullFK(:%New," IS NOT NULL AND") */
      
      NUMROWS = 0
    )
    THEN
      raise_application_error(
        -20002,
        'Cannot insert Attendance because User01 does not exist.'
      );
    END IF;

    /* ERwin Builtin 2019년 7월 15일 월요일 오후 5:27:08 */
    /* Lecture  Attendance on child insert set null */
    /* ERWIN_RELATION:CHECKSUM="00000000", PARENT_OWNER="", PARENT_TABLE="Lecture"
    CHILD_OWNER="", CHILD_TABLE="Attendance"
    P2C_VERB_PHRASE="R/25", C2P_VERB_PHRASE="", 
    FK_CONSTRAINT="R_25", FK_COLUMNS="lecture_id" */
    UPDATE Attendance
      SET
        /* %SetFK(Attendance,NULL) */
        Attendance.lecture_id = NULL
      WHERE
        NOT EXISTS (
          SELECT * FROM Lecture
            WHERE
              /* %JoinFKPK(:%New,Lecture," = "," AND") */
              :new.lecture_id = Lecture.lecture_id
        ) 
        /* %JoinPKPK(Attendance,:%New," = "," AND") */
         and Attendance.day_time = :new.day_time AND
        Attendance.std_id = :new.std_id;


-- ERwin Builtin 2019년 7월 15일 월요일 오후 5:27:08
END;
/

CREATE  TRIGGER tU_Attendance AFTER UPDATE ON Attendance for each row
-- ERwin Builtin 2019년 7월 15일 월요일 오후 5:27:08
-- UPDATE trigger on Attendance 
DECLARE NUMROWS INTEGER;
BEGIN
  /* ERwin Builtin 2019년 7월 15일 월요일 오후 5:27:08 */
  /* User01  Attendance on child update restrict */
  /* ERWIN_RELATION:CHECKSUM="000208e7", PARENT_OWNER="", PARENT_TABLE="User01"
    CHILD_OWNER="", CHILD_TABLE="Attendance"
    P2C_VERB_PHRASE="R/11", C2P_VERB_PHRASE="", 
    FK_CONSTRAINT="R_11", FK_COLUMNS="std_id" */
  SELECT count(*) INTO NUMROWS
    FROM User01
    WHERE
      /* %JoinFKPK(:%New,User01," = "," AND") */
      :new.std_id = User01.user_id;
  IF (
    /* %NotnullFK(:%New," IS NOT NULL AND") */
    
    NUMROWS = 0
  )
  THEN
    raise_application_error(
      -20007,
      'Cannot update Attendance because User01 does not exist.'
    );
  END IF;

  /* ERwin Builtin 2019년 7월 15일 월요일 오후 5:27:08 */
  /* Lecture  Attendance on child update no action */
  /* ERWIN_RELATION:CHECKSUM="00000000", PARENT_OWNER="", PARENT_TABLE="Lecture"
    CHILD_OWNER="", CHILD_TABLE="Attendance"
    P2C_VERB_PHRASE="R/25", C2P_VERB_PHRASE="", 
    FK_CONSTRAINT="R_25", FK_COLUMNS="lecture_id" */
  SELECT count(*) INTO NUMROWS
    FROM Lecture
    WHERE
      /* %JoinFKPK(:%New,Lecture," = "," AND") */
      :new.lecture_id = Lecture.lecture_id;
  IF (
    /* %NotnullFK(:%New," IS NOT NULL AND") */
    :new.lecture_id IS NOT NULL AND
    NUMROWS = 0
  )
  THEN
    raise_application_error(
      -20007,
      'Cannot update Attendance because Lecture does not exist.'
    );
  END IF;


-- ERwin Builtin 2019년 7월 15일 월요일 오후 5:27:08
END;
/


CREATE  TRIGGER tI_Calendar BEFORE INSERT ON Calendar for each row
-- ERwin Builtin 2019년 7월 15일 월요일 오후 5:27:08
-- INSERT trigger on Calendar 
DECLARE NUMROWS INTEGER;
BEGIN
    /* ERwin Builtin 2019년 7월 15일 월요일 오후 5:27:08 */
    /* Lecture  Calendar on child insert set null */
    /* ERWIN_RELATION:CHECKSUM="0000f544", PARENT_OWNER="", PARENT_TABLE="Lecture"
    CHILD_OWNER="", CHILD_TABLE="Calendar"
    P2C_VERB_PHRASE="R/24", C2P_VERB_PHRASE="", 
    FK_CONSTRAINT="R_24", FK_COLUMNS="lecture_id" */
    UPDATE Calendar
      SET
        /* %SetFK(Calendar,NULL) */
        Calendar.lecture_id = NULL
      WHERE
        NOT EXISTS (
          SELECT * FROM Lecture
            WHERE
              /* %JoinFKPK(:%New,Lecture," = "," AND") */
              :new.lecture_id = Lecture.lecture_id
        ) 
        /* %JoinPKPK(Calendar,:%New," = "," AND") */
         and Calendar.calendar_id = :new.calendar_id;


-- ERwin Builtin 2019년 7월 15일 월요일 오후 5:27:08
END;
/

CREATE  TRIGGER tU_Calendar AFTER UPDATE ON Calendar for each row
-- ERwin Builtin 2019년 7월 15일 월요일 오후 5:27:08
-- UPDATE trigger on Calendar 
DECLARE NUMROWS INTEGER;
BEGIN
  /* ERwin Builtin 2019년 7월 15일 월요일 오후 5:27:08 */
  /* Lecture  Calendar on child update no action */
  /* ERWIN_RELATION:CHECKSUM="0000fdde", PARENT_OWNER="", PARENT_TABLE="Lecture"
    CHILD_OWNER="", CHILD_TABLE="Calendar"
    P2C_VERB_PHRASE="R/24", C2P_VERB_PHRASE="", 
    FK_CONSTRAINT="R_24", FK_COLUMNS="lecture_id" */
  SELECT count(*) INTO NUMROWS
    FROM Lecture
    WHERE
      /* %JoinFKPK(:%New,Lecture," = "," AND") */
      :new.lecture_id = Lecture.lecture_id;
  IF (
    /* %NotnullFK(:%New," IS NOT NULL AND") */
    :new.lecture_id IS NOT NULL AND
    NUMROWS = 0
  )
  THEN
    raise_application_error(
      -20007,
      'Cannot update Calendar because Lecture does not exist.'
    );
  END IF;


-- ERwin Builtin 2019년 7월 15일 월요일 오후 5:27:08
END;
/


CREATE  TRIGGER tI_Data_room BEFORE INSERT ON Data_room for each row
-- ERwin Builtin 2019년 7월 15일 월요일 오후 5:27:08
-- INSERT trigger on Data_room 
DECLARE NUMROWS INTEGER;
BEGIN
    /* ERwin Builtin 2019년 7월 15일 월요일 오후 5:27:08 */
    /* User01  Data_room on child insert set null */
    /* ERWIN_RELATION:CHECKSUM="0001faec", PARENT_OWNER="", PARENT_TABLE="User01"
    CHILD_OWNER="", CHILD_TABLE="Data_room"
    P2C_VERB_PHRASE="R/39", C2P_VERB_PHRASE="", 
    FK_CONSTRAINT="R_39", FK_COLUMNS="writer" */
    UPDATE Data_room
      SET
        /* %SetFK(Data_room,NULL) */
        Data_room.writer = NULL
      WHERE
        NOT EXISTS (
          SELECT * FROM User01
            WHERE
              /* %JoinFKPK(:%New,User01," = "," AND") */
              :new.writer = User01.user_id
        ) 
        /* %JoinPKPK(Data_room,:%New," = "," AND") */
         and Data_room.data_id = :new.data_id;

    /* ERwin Builtin 2019년 7월 15일 월요일 오후 5:27:08 */
    /* Attached_File  Data_room on child insert set null */
    /* ERWIN_RELATION:CHECKSUM="00000000", PARENT_OWNER="", PARENT_TABLE="Attached_File"
    CHILD_OWNER="", CHILD_TABLE="Data_room"
    P2C_VERB_PHRASE="R/61", C2P_VERB_PHRASE="", 
    FK_CONSTRAINT="R_61", FK_COLUMNS="file_id" */
    UPDATE Data_room
      SET
        /* %SetFK(Data_room,NULL) */
        Data_room.file_id = NULL
      WHERE
        NOT EXISTS (
          SELECT * FROM Attached_File
            WHERE
              /* %JoinFKPK(:%New,Attached_File," = "," AND") */
              :new.file_id = Attached_File.file_id
        ) 
        /* %JoinPKPK(Data_room,:%New," = "," AND") */
         and Data_room.data_id = :new.data_id;


-- ERwin Builtin 2019년 7월 15일 월요일 오후 5:27:08
END;
/

CREATE  TRIGGER tU_Data_room AFTER UPDATE ON Data_room for each row
-- ERwin Builtin 2019년 7월 15일 월요일 오후 5:27:08
-- UPDATE trigger on Data_room 
DECLARE NUMROWS INTEGER;
BEGIN
  /* ERwin Builtin 2019년 7월 15일 월요일 오후 5:27:08 */
  /* User01  Data_room on child update no action */
  /* ERWIN_RELATION:CHECKSUM="00022192", PARENT_OWNER="", PARENT_TABLE="User01"
    CHILD_OWNER="", CHILD_TABLE="Data_room"
    P2C_VERB_PHRASE="R/39", C2P_VERB_PHRASE="", 
    FK_CONSTRAINT="R_39", FK_COLUMNS="writer" */
  SELECT count(*) INTO NUMROWS
    FROM User01
    WHERE
      /* %JoinFKPK(:%New,User01," = "," AND") */
      :new.writer = User01.user_id;
  IF (
    /* %NotnullFK(:%New," IS NOT NULL AND") */
    :new.writer IS NOT NULL AND
    NUMROWS = 0
  )
  THEN
    raise_application_error(
      -20007,
      'Cannot update Data_room because User01 does not exist.'
    );
  END IF;

  /* ERwin Builtin 2019년 7월 15일 월요일 오후 5:27:08 */
  /* Attached_File  Data_room on child update no action */
  /* ERWIN_RELATION:CHECKSUM="00000000", PARENT_OWNER="", PARENT_TABLE="Attached_File"
    CHILD_OWNER="", CHILD_TABLE="Data_room"
    P2C_VERB_PHRASE="R/61", C2P_VERB_PHRASE="", 
    FK_CONSTRAINT="R_61", FK_COLUMNS="file_id" */
  SELECT count(*) INTO NUMROWS
    FROM Attached_File
    WHERE
      /* %JoinFKPK(:%New,Attached_File," = "," AND") */
      :new.file_id = Attached_File.file_id;
  IF (
    /* %NotnullFK(:%New," IS NOT NULL AND") */
    :new.file_id IS NOT NULL AND
    NUMROWS = 0
  )
  THEN
    raise_application_error(
      -20007,
      'Cannot update Data_room because Attached_File does not exist.'
    );
  END IF;


-- ERwin Builtin 2019년 7월 15일 월요일 오후 5:27:08
END;
/


CREATE  TRIGGER tD_File_Group AFTER DELETE ON File_Group for each row
-- ERwin Builtin 2019년 7월 15일 월요일 오후 5:27:08
-- DELETE trigger on File_Group 
DECLARE NUMROWS INTEGER;
BEGIN
    /* ERwin Builtin 2019년 7월 15일 월요일 오후 5:27:08 */
    /* File_Group  Attached_File on parent delete set null */
    /* ERWIN_RELATION:CHECKSUM="0000ce14", PARENT_OWNER="", PARENT_TABLE="File_Group"
    CHILD_OWNER="", CHILD_TABLE="Attached_File"
    P2C_VERB_PHRASE="R/59", C2P_VERB_PHRASE="", 
    FK_CONSTRAINT="R_59", FK_COLUMNS="file_group" */
    UPDATE Attached_File
      SET
        /* %SetFK(Attached_File,NULL) */
        Attached_File.file_group = NULL
      WHERE
        /* %JoinFKPK(Attached_File,:%Old," = "," AND") */
        Attached_File.file_group = :old.file_group;


-- ERwin Builtin 2019년 7월 15일 월요일 오후 5:27:08
END;
/

CREATE  TRIGGER tU_File_Group AFTER UPDATE ON File_Group for each row
-- ERwin Builtin 2019년 7월 15일 월요일 오후 5:27:08
-- UPDATE trigger on File_Group 
DECLARE NUMROWS INTEGER;
BEGIN
  /* File_Group  Attached_File on parent update set null */
  /* ERWIN_RELATION:CHECKSUM="0001049a", PARENT_OWNER="", PARENT_TABLE="File_Group"
    CHILD_OWNER="", CHILD_TABLE="Attached_File"
    P2C_VERB_PHRASE="R/59", C2P_VERB_PHRASE="", 
    FK_CONSTRAINT="R_59", FK_COLUMNS="file_group" */
  IF
    /* %JoinPKPK(:%Old,:%New," <> "," OR ") */
    :old.file_group <> :new.file_group
  THEN
    UPDATE Attached_File
      SET
        /* %SetFK(Attached_File,NULL) */
        Attached_File.file_group = NULL
      WHERE
        /* %JoinFKPK(Attached_File,:%Old," = ",",") */
        Attached_File.file_group = :old.file_group;
  END IF;


-- ERwin Builtin 2019년 7월 15일 월요일 오후 5:27:08
END;
/


CREATE  TRIGGER tI_Lecture BEFORE INSERT ON Lecture for each row
-- ERwin Builtin 2019년 7월 15일 월요일 오후 5:27:08
-- INSERT trigger on Lecture 
DECLARE NUMROWS INTEGER;
BEGIN
    /* ERwin Builtin 2019년 7월 15일 월요일 오후 5:27:08 */
    /* Attached_File  Lecture on child insert set null */
    /* ERWIN_RELATION:CHECKSUM="0002059c", PARENT_OWNER="", PARENT_TABLE="Attached_File"
    CHILD_OWNER="", CHILD_TABLE="Lecture"
    P2C_VERB_PHRASE="R/62", C2P_VERB_PHRASE="", 
    FK_CONSTRAINT="R_62", FK_COLUMNS="curri_id" */
    UPDATE Lecture
      SET
        /* %SetFK(Lecture,NULL) */
        Lecture.curri_id = NULL
      WHERE
        NOT EXISTS (
          SELECT * FROM Attached_File
            WHERE
              /* %JoinFKPK(:%New,Attached_File," = "," AND") */
              :new.curri_id = Attached_File.file_id
        ) 
        /* %JoinPKPK(Lecture,:%New," = "," AND") */
         and Lecture.lecture_id = :new.lecture_id;

    /* ERwin Builtin 2019년 7월 15일 월요일 오후 5:27:08 */
    /* Attached_File  Lecture on child insert set null */
    /* ERWIN_RELATION:CHECKSUM="00000000", PARENT_OWNER="", PARENT_TABLE="Attached_File"
    CHILD_OWNER="", CHILD_TABLE="Lecture"
    P2C_VERB_PHRASE="R/66", C2P_VERB_PHRASE="", 
    FK_CONSTRAINT="R_66", FK_COLUMNS="file_id" */
    UPDATE Lecture
      SET
        /* %SetFK(Lecture,NULL) */
        Lecture.file_id = NULL
      WHERE
        NOT EXISTS (
          SELECT * FROM Attached_File
            WHERE
              /* %JoinFKPK(:%New,Attached_File," = "," AND") */
              :new.file_id = Attached_File.file_id
        ) 
        /* %JoinPKPK(Lecture,:%New," = "," AND") */
         and Lecture.lecture_id = :new.lecture_id;


-- ERwin Builtin 2019년 7월 15일 월요일 오후 5:27:08
END;
/

CREATE  TRIGGER tD_Lecture AFTER DELETE ON Lecture for each row
-- ERwin Builtin 2019년 7월 15일 월요일 오후 5:27:08
-- DELETE trigger on Lecture 
DECLARE NUMROWS INTEGER;
BEGIN
    /* ERwin Builtin 2019년 7월 15일 월요일 오후 5:27:08 */
    /* Lecture  Apply on parent delete set null */
    /* ERWIN_RELATION:CHECKSUM="00053cda", PARENT_OWNER="", PARENT_TABLE="Lecture"
    CHILD_OWNER="", CHILD_TABLE="Apply"
    P2C_VERB_PHRASE="R/14", C2P_VERB_PHRASE="", 
    FK_CONSTRAINT="R_14", FK_COLUMNS="lecture_id" */
    UPDATE Apply
      SET
        /* %SetFK(Apply,NULL) */
        Apply.lecture_id = NULL
      WHERE
        /* %JoinFKPK(Apply,:%Old," = "," AND") */
        Apply.lecture_id = :old.lecture_id;

    /* ERwin Builtin 2019년 7월 15일 월요일 오후 5:27:08 */
    /* Lecture  Score on parent delete restrict */
    /* ERWIN_RELATION:CHECKSUM="00000000", PARENT_OWNER="", PARENT_TABLE="Lecture"
    CHILD_OWNER="", CHILD_TABLE="Score"
    P2C_VERB_PHRASE="R/17", C2P_VERB_PHRASE="", 
    FK_CONSTRAINT="R_17", FK_COLUMNS="lecture_id" */
    SELECT count(*) INTO NUMROWS
      FROM Score
      WHERE
        /*  %JoinFKPK(Score,:%Old," = "," AND") */
        Score.lecture_id = :old.lecture_id;
    IF (NUMROWS > 0)
    THEN
      raise_application_error(
        -20001,
        'Cannot delete Lecture because Score exists.'
      );
    END IF;

    /* ERwin Builtin 2019년 7월 15일 월요일 오후 5:27:08 */
    /* Lecture  Calendar on parent delete set null */
    /* ERWIN_RELATION:CHECKSUM="00000000", PARENT_OWNER="", PARENT_TABLE="Lecture"
    CHILD_OWNER="", CHILD_TABLE="Calendar"
    P2C_VERB_PHRASE="R/24", C2P_VERB_PHRASE="", 
    FK_CONSTRAINT="R_24", FK_COLUMNS="lecture_id" */
    UPDATE Calendar
      SET
        /* %SetFK(Calendar,NULL) */
        Calendar.lecture_id = NULL
      WHERE
        /* %JoinFKPK(Calendar,:%Old," = "," AND") */
        Calendar.lecture_id = :old.lecture_id;

    /* ERwin Builtin 2019년 7월 15일 월요일 오후 5:27:08 */
    /* Lecture  Attendance on parent delete set null */
    /* ERWIN_RELATION:CHECKSUM="00000000", PARENT_OWNER="", PARENT_TABLE="Lecture"
    CHILD_OWNER="", CHILD_TABLE="Attendance"
    P2C_VERB_PHRASE="R/25", C2P_VERB_PHRASE="", 
    FK_CONSTRAINT="R_25", FK_COLUMNS="lecture_id" */
    UPDATE Attendance
      SET
        /* %SetFK(Attendance,NULL) */
        Attendance.lecture_id = NULL
      WHERE
        /* %JoinFKPK(Attendance,:%Old," = "," AND") */
        Attendance.lecture_id = :old.lecture_id;

    /* ERwin Builtin 2019년 7월 15일 월요일 오후 5:27:08 */
    /* Lecture  Assignment on parent delete set null */
    /* ERWIN_RELATION:CHECKSUM="00000000", PARENT_OWNER="", PARENT_TABLE="Lecture"
    CHILD_OWNER="", CHILD_TABLE="Assignment"
    P2C_VERB_PHRASE="R/38", C2P_VERB_PHRASE="", 
    FK_CONSTRAINT="R_38", FK_COLUMNS="lecture_id" */
    UPDATE Assignment
      SET
        /* %SetFK(Assignment,NULL) */
        Assignment.lecture_id = NULL
      WHERE
        /* %JoinFKPK(Assignment,:%Old," = "," AND") */
        Assignment.lecture_id = :old.lecture_id;

    /* ERwin Builtin 2019년 7월 15일 월요일 오후 5:27:08 */
    /* Lecture  LectureUser on parent delete restrict */
    /* ERWIN_RELATION:CHECKSUM="00000000", PARENT_OWNER="", PARENT_TABLE="Lecture"
    CHILD_OWNER="", CHILD_TABLE="LectureUser"
    P2C_VERB_PHRASE="R/51", C2P_VERB_PHRASE="", 
    FK_CONSTRAINT="R_51", FK_COLUMNS="lecture_id" */
    SELECT count(*) INTO NUMROWS
      FROM LectureUser
      WHERE
        /*  %JoinFKPK(LectureUser,:%Old," = "," AND") */
        LectureUser.lecture_id = :old.lecture_id;
    IF (NUMROWS > 0)
    THEN
      raise_application_error(
        -20001,
        'Cannot delete Lecture because LectureUser exists.'
      );
    END IF;


-- ERwin Builtin 2019년 7월 15일 월요일 오후 5:27:08
END;
/

CREATE  TRIGGER tU_Lecture AFTER UPDATE ON Lecture for each row
-- ERwin Builtin 2019년 7월 15일 월요일 오후 5:27:08
-- UPDATE trigger on Lecture 
DECLARE NUMROWS INTEGER;
BEGIN
  /* Lecture  Apply on parent update set null */
  /* ERWIN_RELATION:CHECKSUM="00085d36", PARENT_OWNER="", PARENT_TABLE="Lecture"
    CHILD_OWNER="", CHILD_TABLE="Apply"
    P2C_VERB_PHRASE="R/14", C2P_VERB_PHRASE="", 
    FK_CONSTRAINT="R_14", FK_COLUMNS="lecture_id" */
  IF
    /* %JoinPKPK(:%Old,:%New," <> "," OR ") */
    :old.lecture_id <> :new.lecture_id
  THEN
    UPDATE Apply
      SET
        /* %SetFK(Apply,NULL) */
        Apply.lecture_id = NULL
      WHERE
        /* %JoinFKPK(Apply,:%Old," = ",",") */
        Apply.lecture_id = :old.lecture_id;
  END IF;

  /* ERwin Builtin 2019년 7월 15일 월요일 오후 5:27:08 */
  /* Lecture  Score on parent update restrict */
  /* ERWIN_RELATION:CHECKSUM="00000000", PARENT_OWNER="", PARENT_TABLE="Lecture"
    CHILD_OWNER="", CHILD_TABLE="Score"
    P2C_VERB_PHRASE="R/17", C2P_VERB_PHRASE="", 
    FK_CONSTRAINT="R_17", FK_COLUMNS="lecture_id" */
  IF
    /* %JoinPKPK(:%Old,:%New," <> "," OR ") */
    :old.lecture_id <> :new.lecture_id
  THEN
    SELECT count(*) INTO NUMROWS
      FROM Score
      WHERE
        /*  %JoinFKPK(Score,:%Old," = "," AND") */
        Score.lecture_id = :old.lecture_id;
    IF (NUMROWS > 0)
    THEN 
      raise_application_error(
        -20005,
        'Cannot update Lecture because Score exists.'
      );
    END IF;
  END IF;

  /* Lecture  Calendar on parent update set null */
  /* ERWIN_RELATION:CHECKSUM="00000000", PARENT_OWNER="", PARENT_TABLE="Lecture"
    CHILD_OWNER="", CHILD_TABLE="Calendar"
    P2C_VERB_PHRASE="R/24", C2P_VERB_PHRASE="", 
    FK_CONSTRAINT="R_24", FK_COLUMNS="lecture_id" */
  IF
    /* %JoinPKPK(:%Old,:%New," <> "," OR ") */
    :old.lecture_id <> :new.lecture_id
  THEN
    UPDATE Calendar
      SET
        /* %SetFK(Calendar,NULL) */
        Calendar.lecture_id = NULL
      WHERE
        /* %JoinFKPK(Calendar,:%Old," = ",",") */
        Calendar.lecture_id = :old.lecture_id;
  END IF;

  /* Lecture  Attendance on parent update set null */
  /* ERWIN_RELATION:CHECKSUM="00000000", PARENT_OWNER="", PARENT_TABLE="Lecture"
    CHILD_OWNER="", CHILD_TABLE="Attendance"
    P2C_VERB_PHRASE="R/25", C2P_VERB_PHRASE="", 
    FK_CONSTRAINT="R_25", FK_COLUMNS="lecture_id" */
  IF
    /* %JoinPKPK(:%Old,:%New," <> "," OR ") */
    :old.lecture_id <> :new.lecture_id
  THEN
    UPDATE Attendance
      SET
        /* %SetFK(Attendance,NULL) */
        Attendance.lecture_id = NULL
      WHERE
        /* %JoinFKPK(Attendance,:%Old," = ",",") */
        Attendance.lecture_id = :old.lecture_id;
  END IF;

  /* Lecture  Assignment on parent update set null */
  /* ERWIN_RELATION:CHECKSUM="00000000", PARENT_OWNER="", PARENT_TABLE="Lecture"
    CHILD_OWNER="", CHILD_TABLE="Assignment"
    P2C_VERB_PHRASE="R/38", C2P_VERB_PHRASE="", 
    FK_CONSTRAINT="R_38", FK_COLUMNS="lecture_id" */
  IF
    /* %JoinPKPK(:%Old,:%New," <> "," OR ") */
    :old.lecture_id <> :new.lecture_id
  THEN
    UPDATE Assignment
      SET
        /* %SetFK(Assignment,NULL) */
        Assignment.lecture_id = NULL
      WHERE
        /* %JoinFKPK(Assignment,:%Old," = ",",") */
        Assignment.lecture_id = :old.lecture_id;
  END IF;

  /* ERwin Builtin 2019년 7월 15일 월요일 오후 5:27:08 */
  /* Lecture  LectureUser on parent update restrict */
  /* ERWIN_RELATION:CHECKSUM="00000000", PARENT_OWNER="", PARENT_TABLE="Lecture"
    CHILD_OWNER="", CHILD_TABLE="LectureUser"
    P2C_VERB_PHRASE="R/51", C2P_VERB_PHRASE="", 
    FK_CONSTRAINT="R_51", FK_COLUMNS="lecture_id" */
  IF
    /* %JoinPKPK(:%Old,:%New," <> "," OR ") */
    :old.lecture_id <> :new.lecture_id
  THEN
    SELECT count(*) INTO NUMROWS
      FROM LectureUser
      WHERE
        /*  %JoinFKPK(LectureUser,:%Old," = "," AND") */
        LectureUser.lecture_id = :old.lecture_id;
    IF (NUMROWS > 0)
    THEN 
      raise_application_error(
        -20005,
        'Cannot update Lecture because LectureUser exists.'
      );
    END IF;
  END IF;

  /* ERwin Builtin 2019년 7월 15일 월요일 오후 5:27:08 */
  /* Attached_File  Lecture on child update no action */
  /* ERWIN_RELATION:CHECKSUM="00000000", PARENT_OWNER="", PARENT_TABLE="Attached_File"
    CHILD_OWNER="", CHILD_TABLE="Lecture"
    P2C_VERB_PHRASE="R/62", C2P_VERB_PHRASE="", 
    FK_CONSTRAINT="R_62", FK_COLUMNS="curri_id" */
  SELECT count(*) INTO NUMROWS
    FROM Attached_File
    WHERE
      /* %JoinFKPK(:%New,Attached_File," = "," AND") */
      :new.curri_id = Attached_File.file_id;
  IF (
    /* %NotnullFK(:%New," IS NOT NULL AND") */
    :new.curri_id IS NOT NULL AND
    NUMROWS = 0
  )
  THEN
    raise_application_error(
      -20007,
      'Cannot update Lecture because Attached_File does not exist.'
    );
  END IF;

  /* ERwin Builtin 2019년 7월 15일 월요일 오후 5:27:08 */
  /* Attached_File  Lecture on child update no action */
  /* ERWIN_RELATION:CHECKSUM="00000000", PARENT_OWNER="", PARENT_TABLE="Attached_File"
    CHILD_OWNER="", CHILD_TABLE="Lecture"
    P2C_VERB_PHRASE="R/66", C2P_VERB_PHRASE="", 
    FK_CONSTRAINT="R_66", FK_COLUMNS="file_id" */
  SELECT count(*) INTO NUMROWS
    FROM Attached_File
    WHERE
      /* %JoinFKPK(:%New,Attached_File," = "," AND") */
      :new.file_id = Attached_File.file_id;
  IF (
    /* %NotnullFK(:%New," IS NOT NULL AND") */
    :new.file_id IS NOT NULL AND
    NUMROWS = 0
  )
  THEN
    raise_application_error(
      -20007,
      'Cannot update Lecture because Attached_File does not exist.'
    );
  END IF;


-- ERwin Builtin 2019년 7월 15일 월요일 오후 5:27:08
END;
/


CREATE  TRIGGER tI_Lecture_review BEFORE INSERT ON Lecture_review for each row
-- ERwin Builtin 2019년 7월 15일 월요일 오후 5:27:08
-- INSERT trigger on Lecture_review 
DECLARE NUMROWS INTEGER;
BEGIN
    /* ERwin Builtin 2019년 7월 15일 월요일 오후 5:27:08 */
    /* User01  Lecture_review on child insert set null */
    /* ERWIN_RELATION:CHECKSUM="0000fd0b", PARENT_OWNER="", PARENT_TABLE="User01"
    CHILD_OWNER="", CHILD_TABLE="Lecture_review"
    P2C_VERB_PHRASE="R/30", C2P_VERB_PHRASE="", 
    FK_CONSTRAINT="R_30", FK_COLUMNS="writer" */
    UPDATE Lecture_review
      SET
        /* %SetFK(Lecture_review,NULL) */
        Lecture_review.writer = NULL
      WHERE
        NOT EXISTS (
          SELECT * FROM User01
            WHERE
              /* %JoinFKPK(:%New,User01," = "," AND") */
              :new.writer = User01.user_id
        ) 
        /* %JoinPKPK(Lecture_review,:%New," = "," AND") */
         and Lecture_review.review_id = :new.review_id;


-- ERwin Builtin 2019년 7월 15일 월요일 오후 5:27:08
END;
/

CREATE  TRIGGER tU_Lecture_review AFTER UPDATE ON Lecture_review for each row
-- ERwin Builtin 2019년 7월 15일 월요일 오후 5:27:08
-- UPDATE trigger on Lecture_review 
DECLARE NUMROWS INTEGER;
BEGIN
  /* ERwin Builtin 2019년 7월 15일 월요일 오후 5:27:08 */
  /* User01  Lecture_review on child update no action */
  /* ERWIN_RELATION:CHECKSUM="00010cc1", PARENT_OWNER="", PARENT_TABLE="User01"
    CHILD_OWNER="", CHILD_TABLE="Lecture_review"
    P2C_VERB_PHRASE="R/30", C2P_VERB_PHRASE="", 
    FK_CONSTRAINT="R_30", FK_COLUMNS="writer" */
  SELECT count(*) INTO NUMROWS
    FROM User01
    WHERE
      /* %JoinFKPK(:%New,User01," = "," AND") */
      :new.writer = User01.user_id;
  IF (
    /* %NotnullFK(:%New," IS NOT NULL AND") */
    :new.writer IS NOT NULL AND
    NUMROWS = 0
  )
  THEN
    raise_application_error(
      -20007,
      'Cannot update Lecture_review because User01 does not exist.'
    );
  END IF;


-- ERwin Builtin 2019년 7월 15일 월요일 오후 5:27:08
END;
/


CREATE  TRIGGER tI_LectureUser BEFORE INSERT ON LectureUser for each row
-- ERwin Builtin 2019년 7월 15일 월요일 오후 5:27:08
-- INSERT trigger on LectureUser 
DECLARE NUMROWS INTEGER;
BEGIN
    /* ERwin Builtin 2019년 7월 15일 월요일 오후 5:27:08 */
    /* Lecture  LectureUser on child insert restrict */
    /* ERWIN_RELATION:CHECKSUM="0001f89e", PARENT_OWNER="", PARENT_TABLE="Lecture"
    CHILD_OWNER="", CHILD_TABLE="LectureUser"
    P2C_VERB_PHRASE="R/51", C2P_VERB_PHRASE="", 
    FK_CONSTRAINT="R_51", FK_COLUMNS="lecture_id" */
    SELECT count(*) INTO NUMROWS
      FROM Lecture
      WHERE
        /* %JoinFKPK(:%New,Lecture," = "," AND") */
        :new.lecture_id = Lecture.lecture_id;
    IF (
      /* %NotnullFK(:%New," IS NOT NULL AND") */
      
      NUMROWS = 0
    )
    THEN
      raise_application_error(
        -20002,
        'Cannot insert LectureUser because Lecture does not exist.'
      );
    END IF;

    /* ERwin Builtin 2019년 7월 15일 월요일 오후 5:27:08 */
    /* User01  LectureUser on child insert restrict */
    /* ERWIN_RELATION:CHECKSUM="00000000", PARENT_OWNER="", PARENT_TABLE="User01"
    CHILD_OWNER="", CHILD_TABLE="LectureUser"
    P2C_VERB_PHRASE="R/52", C2P_VERB_PHRASE="", 
    FK_CONSTRAINT="R_52", FK_COLUMNS="user_id" */
    SELECT count(*) INTO NUMROWS
      FROM User01
      WHERE
        /* %JoinFKPK(:%New,User01," = "," AND") */
        :new.user_id = User01.user_id;
    IF (
      /* %NotnullFK(:%New," IS NOT NULL AND") */
      
      NUMROWS = 0
    )
    THEN
      raise_application_error(
        -20002,
        'Cannot insert LectureUser because User01 does not exist.'
      );
    END IF;


-- ERwin Builtin 2019년 7월 15일 월요일 오후 5:27:08
END;
/

CREATE  TRIGGER tU_LectureUser AFTER UPDATE ON LectureUser for each row
-- ERwin Builtin 2019년 7월 15일 월요일 오후 5:27:08
-- UPDATE trigger on LectureUser 
DECLARE NUMROWS INTEGER;
BEGIN
  /* ERwin Builtin 2019년 7월 15일 월요일 오후 5:27:08 */
  /* Lecture  LectureUser on child update restrict */
  /* ERWIN_RELATION:CHECKSUM="0001fc30", PARENT_OWNER="", PARENT_TABLE="Lecture"
    CHILD_OWNER="", CHILD_TABLE="LectureUser"
    P2C_VERB_PHRASE="R/51", C2P_VERB_PHRASE="", 
    FK_CONSTRAINT="R_51", FK_COLUMNS="lecture_id" */
  SELECT count(*) INTO NUMROWS
    FROM Lecture
    WHERE
      /* %JoinFKPK(:%New,Lecture," = "," AND") */
      :new.lecture_id = Lecture.lecture_id;
  IF (
    /* %NotnullFK(:%New," IS NOT NULL AND") */
    
    NUMROWS = 0
  )
  THEN
    raise_application_error(
      -20007,
      'Cannot update LectureUser because Lecture does not exist.'
    );
  END IF;

  /* ERwin Builtin 2019년 7월 15일 월요일 오후 5:27:08 */
  /* User01  LectureUser on child update restrict */
  /* ERWIN_RELATION:CHECKSUM="00000000", PARENT_OWNER="", PARENT_TABLE="User01"
    CHILD_OWNER="", CHILD_TABLE="LectureUser"
    P2C_VERB_PHRASE="R/52", C2P_VERB_PHRASE="", 
    FK_CONSTRAINT="R_52", FK_COLUMNS="user_id" */
  SELECT count(*) INTO NUMROWS
    FROM User01
    WHERE
      /* %JoinFKPK(:%New,User01," = "," AND") */
      :new.user_id = User01.user_id;
  IF (
    /* %NotnullFK(:%New," IS NOT NULL AND") */
    
    NUMROWS = 0
  )
  THEN
    raise_application_error(
      -20007,
      'Cannot update LectureUser because User01 does not exist.'
    );
  END IF;


-- ERwin Builtin 2019년 7월 15일 월요일 오후 5:27:08
END;
/


CREATE  TRIGGER tI_Notice BEFORE INSERT ON Notice for each row
-- ERwin Builtin 2019년 7월 15일 월요일 오후 5:27:08
-- INSERT trigger on Notice 
DECLARE NUMROWS INTEGER;
BEGIN
    /* ERwin Builtin 2019년 7월 15일 월요일 오후 5:27:08 */
    /* User01  Notice on child insert set null */
    /* ERWIN_RELATION:CHECKSUM="0000dd34", PARENT_OWNER="", PARENT_TABLE="User01"
    CHILD_OWNER="", CHILD_TABLE="Notice"
    P2C_VERB_PHRASE="R/42", C2P_VERB_PHRASE="", 
    FK_CONSTRAINT="R_42", FK_COLUMNS="writer" */
    UPDATE Notice
      SET
        /* %SetFK(Notice,NULL) */
        Notice.writer = NULL
      WHERE
        NOT EXISTS (
          SELECT * FROM User01
            WHERE
              /* %JoinFKPK(:%New,User01," = "," AND") */
              :new.writer = User01.user_id
        ) 
        /* %JoinPKPK(Notice,:%New," = "," AND") */
         and Notice.notice_id = :new.notice_id;


-- ERwin Builtin 2019년 7월 15일 월요일 오후 5:27:08
END;
/

CREATE  TRIGGER tU_Notice AFTER UPDATE ON Notice for each row
-- ERwin Builtin 2019년 7월 15일 월요일 오후 5:27:08
-- UPDATE trigger on Notice 
DECLARE NUMROWS INTEGER;
BEGIN
  /* ERwin Builtin 2019년 7월 15일 월요일 오후 5:27:08 */
  /* User01  Notice on child update no action */
  /* ERWIN_RELATION:CHECKSUM="0000f88d", PARENT_OWNER="", PARENT_TABLE="User01"
    CHILD_OWNER="", CHILD_TABLE="Notice"
    P2C_VERB_PHRASE="R/42", C2P_VERB_PHRASE="", 
    FK_CONSTRAINT="R_42", FK_COLUMNS="writer" */
  SELECT count(*) INTO NUMROWS
    FROM User01
    WHERE
      /* %JoinFKPK(:%New,User01," = "," AND") */
      :new.writer = User01.user_id;
  IF (
    /* %NotnullFK(:%New," IS NOT NULL AND") */
    :new.writer IS NOT NULL AND
    NUMROWS = 0
  )
  THEN
    raise_application_error(
      -20007,
      'Cannot update Notice because User01 does not exist.'
    );
  END IF;


-- ERwin Builtin 2019년 7월 15일 월요일 오후 5:27:08
END;
/


CREATE  TRIGGER tI_Qna_H BEFORE INSERT ON Qna_H for each row
-- ERwin Builtin 2019년 7월 15일 월요일 오후 5:27:08
-- INSERT trigger on Qna_H 
DECLARE NUMROWS INTEGER;
BEGIN
    /* ERwin Builtin 2019년 7월 15일 월요일 오후 5:27:08 */
    /* User01  Qna_H on child insert set null */
    /* ERWIN_RELATION:CHECKSUM="0000f35f", PARENT_OWNER="", PARENT_TABLE="User01"
    CHILD_OWNER="", CHILD_TABLE="Qna_H"
    P2C_VERB_PHRASE="R/40", C2P_VERB_PHRASE="", 
    FK_CONSTRAINT="R_40", FK_COLUMNS="std_id" */
    UPDATE Qna_H
      SET
        /* %SetFK(Qna_H,NULL) */
        Qna_H.std_id = NULL
      WHERE
        NOT EXISTS (
          SELECT * FROM User01
            WHERE
              /* %JoinFKPK(:%New,User01," = "," AND") */
              :new.std_id = User01.user_id
        ) 
        /* %JoinPKPK(Qna_H,:%New," = "," AND") */
         and Qna_H.qnaH_id = :new.qnaH_id;


-- ERwin Builtin 2019년 7월 15일 월요일 오후 5:27:08
END;
/

CREATE  TRIGGER tU_Qna_H AFTER UPDATE ON Qna_H for each row
-- ERwin Builtin 2019년 7월 15일 월요일 오후 5:27:08
-- UPDATE trigger on Qna_H 
DECLARE NUMROWS INTEGER;
BEGIN
  /* ERwin Builtin 2019년 7월 15일 월요일 오후 5:27:08 */
  /* User01  Qna_H on child update no action */
  /* ERWIN_RELATION:CHECKSUM="0000fabe", PARENT_OWNER="", PARENT_TABLE="User01"
    CHILD_OWNER="", CHILD_TABLE="Qna_H"
    P2C_VERB_PHRASE="R/40", C2P_VERB_PHRASE="", 
    FK_CONSTRAINT="R_40", FK_COLUMNS="std_id" */
  SELECT count(*) INTO NUMROWS
    FROM User01
    WHERE
      /* %JoinFKPK(:%New,User01," = "," AND") */
      :new.std_id = User01.user_id;
  IF (
    /* %NotnullFK(:%New," IS NOT NULL AND") */
    :new.std_id IS NOT NULL AND
    NUMROWS = 0
  )
  THEN
    raise_application_error(
      -20007,
      'Cannot update Qna_H because User01 does not exist.'
    );
  END IF;


-- ERwin Builtin 2019년 7월 15일 월요일 오후 5:27:08
END;
/


CREATE  TRIGGER tI_Qna_L BEFORE INSERT ON Qna_L for each row
-- ERwin Builtin 2019년 7월 15일 월요일 오후 5:27:08
-- INSERT trigger on Qna_L 
DECLARE NUMROWS INTEGER;
BEGIN
    /* ERwin Builtin 2019년 7월 15일 월요일 오후 5:27:08 */
    /* User01  Qna_L on child insert set null */
    /* ERWIN_RELATION:CHECKSUM="0000e32c", PARENT_OWNER="", PARENT_TABLE="User01"
    CHILD_OWNER="", CHILD_TABLE="Qna_L"
    P2C_VERB_PHRASE="R/45", C2P_VERB_PHRASE="", 
    FK_CONSTRAINT="R_45", FK_COLUMNS="std_id" */
    UPDATE Qna_L
      SET
        /* %SetFK(Qna_L,NULL) */
        Qna_L.std_id = NULL
      WHERE
        NOT EXISTS (
          SELECT * FROM User01
            WHERE
              /* %JoinFKPK(:%New,User01," = "," AND") */
              :new.std_id = User01.user_id
        ) 
        /* %JoinPKPK(Qna_L,:%New," = "," AND") */
         and Qna_L.qnaL_id = :new.qnaL_id;


-- ERwin Builtin 2019년 7월 15일 월요일 오후 5:27:08
END;
/

CREATE  TRIGGER tU_Qna_L AFTER UPDATE ON Qna_L for each row
-- ERwin Builtin 2019년 7월 15일 월요일 오후 5:27:08
-- UPDATE trigger on Qna_L 
DECLARE NUMROWS INTEGER;
BEGIN
  /* ERwin Builtin 2019년 7월 15일 월요일 오후 5:27:08 */
  /* User01  Qna_L on child update no action */
  /* ERWIN_RELATION:CHECKSUM="00010065", PARENT_OWNER="", PARENT_TABLE="User01"
    CHILD_OWNER="", CHILD_TABLE="Qna_L"
    P2C_VERB_PHRASE="R/45", C2P_VERB_PHRASE="", 
    FK_CONSTRAINT="R_45", FK_COLUMNS="std_id" */
  SELECT count(*) INTO NUMROWS
    FROM User01
    WHERE
      /* %JoinFKPK(:%New,User01," = "," AND") */
      :new.std_id = User01.user_id;
  IF (
    /* %NotnullFK(:%New," IS NOT NULL AND") */
    :new.std_id IS NOT NULL AND
    NUMROWS = 0
  )
  THEN
    raise_application_error(
      -20007,
      'Cannot update Qna_L because User01 does not exist.'
    );
  END IF;


-- ERwin Builtin 2019년 7월 15일 월요일 오후 5:27:08
END;
/


CREATE  TRIGGER tI_Score BEFORE INSERT ON Score for each row
-- ERwin Builtin 2019년 7월 15일 월요일 오후 5:27:08
-- INSERT trigger on Score 
DECLARE NUMROWS INTEGER;
BEGIN
    /* ERwin Builtin 2019년 7월 15일 월요일 오후 5:27:08 */
    /* User01  Score on child insert restrict */
    /* ERWIN_RELATION:CHECKSUM="0001f51b", PARENT_OWNER="", PARENT_TABLE="User01"
    CHILD_OWNER="", CHILD_TABLE="Score"
    P2C_VERB_PHRASE="R/16", C2P_VERB_PHRASE="", 
    FK_CONSTRAINT="R_16", FK_COLUMNS="std_id" */
    SELECT count(*) INTO NUMROWS
      FROM User01
      WHERE
        /* %JoinFKPK(:%New,User01," = "," AND") */
        :new.std_id = User01.user_id;
    IF (
      /* %NotnullFK(:%New," IS NOT NULL AND") */
      
      NUMROWS = 0
    )
    THEN
      raise_application_error(
        -20002,
        'Cannot insert Score because User01 does not exist.'
      );
    END IF;

    /* ERwin Builtin 2019년 7월 15일 월요일 오후 5:27:08 */
    /* Lecture  Score on child insert restrict */
    /* ERWIN_RELATION:CHECKSUM="00000000", PARENT_OWNER="", PARENT_TABLE="Lecture"
    CHILD_OWNER="", CHILD_TABLE="Score"
    P2C_VERB_PHRASE="R/17", C2P_VERB_PHRASE="", 
    FK_CONSTRAINT="R_17", FK_COLUMNS="lecture_id" */
    SELECT count(*) INTO NUMROWS
      FROM Lecture
      WHERE
        /* %JoinFKPK(:%New,Lecture," = "," AND") */
        :new.lecture_id = Lecture.lecture_id;
    IF (
      /* %NotnullFK(:%New," IS NOT NULL AND") */
      
      NUMROWS = 0
    )
    THEN
      raise_application_error(
        -20002,
        'Cannot insert Score because Lecture does not exist.'
      );
    END IF;


-- ERwin Builtin 2019년 7월 15일 월요일 오후 5:27:08
END;
/

CREATE  TRIGGER tU_Score AFTER UPDATE ON Score for each row
-- ERwin Builtin 2019년 7월 15일 월요일 오후 5:27:08
-- UPDATE trigger on Score 
DECLARE NUMROWS INTEGER;
BEGIN
  /* ERwin Builtin 2019년 7월 15일 월요일 오후 5:27:08 */
  /* User01  Score on child update restrict */
  /* ERWIN_RELATION:CHECKSUM="0001ed14", PARENT_OWNER="", PARENT_TABLE="User01"
    CHILD_OWNER="", CHILD_TABLE="Score"
    P2C_VERB_PHRASE="R/16", C2P_VERB_PHRASE="", 
    FK_CONSTRAINT="R_16", FK_COLUMNS="std_id" */
  SELECT count(*) INTO NUMROWS
    FROM User01
    WHERE
      /* %JoinFKPK(:%New,User01," = "," AND") */
      :new.std_id = User01.user_id;
  IF (
    /* %NotnullFK(:%New," IS NOT NULL AND") */
    
    NUMROWS = 0
  )
  THEN
    raise_application_error(
      -20007,
      'Cannot update Score because User01 does not exist.'
    );
  END IF;

  /* ERwin Builtin 2019년 7월 15일 월요일 오후 5:27:08 */
  /* Lecture  Score on child update restrict */
  /* ERWIN_RELATION:CHECKSUM="00000000", PARENT_OWNER="", PARENT_TABLE="Lecture"
    CHILD_OWNER="", CHILD_TABLE="Score"
    P2C_VERB_PHRASE="R/17", C2P_VERB_PHRASE="", 
    FK_CONSTRAINT="R_17", FK_COLUMNS="lecture_id" */
  SELECT count(*) INTO NUMROWS
    FROM Lecture
    WHERE
      /* %JoinFKPK(:%New,Lecture," = "," AND") */
      :new.lecture_id = Lecture.lecture_id;
  IF (
    /* %NotnullFK(:%New," IS NOT NULL AND") */
    
    NUMROWS = 0
  )
  THEN
    raise_application_error(
      -20007,
      'Cannot update Score because Lecture does not exist.'
    );
  END IF;


-- ERwin Builtin 2019년 7월 15일 월요일 오후 5:27:08
END;
/


CREATE  TRIGGER tI_Submission BEFORE INSERT ON Submission for each row
-- ERwin Builtin 2019년 7월 15일 월요일 오후 5:27:08
-- INSERT trigger on Submission 
DECLARE NUMROWS INTEGER;
BEGIN
    /* ERwin Builtin 2019년 7월 15일 월요일 오후 5:27:08 */
    /* Assignment  Submission on child insert restrict */
    /* ERWIN_RELATION:CHECKSUM="00034c8e", PARENT_OWNER="", PARENT_TABLE="Assignment"
    CHILD_OWNER="", CHILD_TABLE="Submission"
    P2C_VERB_PHRASE="R/27", C2P_VERB_PHRASE="", 
    FK_CONSTRAINT="R_27", FK_COLUMNS="assignment_id" */
    SELECT count(*) INTO NUMROWS
      FROM Assignment
      WHERE
        /* %JoinFKPK(:%New,Assignment," = "," AND") */
        :new.assignment_id = Assignment.assignment_id;
    IF (
      /* %NotnullFK(:%New," IS NOT NULL AND") */
      
      NUMROWS = 0
    )
    THEN
      raise_application_error(
        -20002,
        'Cannot insert Submission because Assignment does not exist.'
      );
    END IF;

    /* ERwin Builtin 2019년 7월 15일 월요일 오후 5:27:08 */
    /* User01  Submission on child insert restrict */
    /* ERWIN_RELATION:CHECKSUM="00000000", PARENT_OWNER="", PARENT_TABLE="User01"
    CHILD_OWNER="", CHILD_TABLE="Submission"
    P2C_VERB_PHRASE="R/28", C2P_VERB_PHRASE="", 
    FK_CONSTRAINT="R_28", FK_COLUMNS="std_id" */
    SELECT count(*) INTO NUMROWS
      FROM User01
      WHERE
        /* %JoinFKPK(:%New,User01," = "," AND") */
        :new.std_id = User01.user_id;
    IF (
      /* %NotnullFK(:%New," IS NOT NULL AND") */
      
      NUMROWS = 0
    )
    THEN
      raise_application_error(
        -20002,
        'Cannot insert Submission because User01 does not exist.'
      );
    END IF;

    /* ERwin Builtin 2019년 7월 15일 월요일 오후 5:27:08 */
    /* Attached_File  Submission on child insert set null */
    /* ERWIN_RELATION:CHECKSUM="00000000", PARENT_OWNER="", PARENT_TABLE="Attached_File"
    CHILD_OWNER="", CHILD_TABLE="Submission"
    P2C_VERB_PHRASE="R/60", C2P_VERB_PHRASE="", 
    FK_CONSTRAINT="R_60", FK_COLUMNS="file_id" */
    UPDATE Submission
      SET
        /* %SetFK(Submission,NULL) */
        Submission.file_id = NULL
      WHERE
        NOT EXISTS (
          SELECT * FROM Attached_File
            WHERE
              /* %JoinFKPK(:%New,Attached_File," = "," AND") */
              :new.file_id = Attached_File.file_id
        ) 
        /* %JoinPKPK(Submission,:%New," = "," AND") */
         and Submission.assignment_id = :new.assignment_id AND
        Submission.std_id = :new.std_id;


-- ERwin Builtin 2019년 7월 15일 월요일 오후 5:27:08
END;
/

CREATE  TRIGGER tU_Submission AFTER UPDATE ON Submission for each row
-- ERwin Builtin 2019년 7월 15일 월요일 오후 5:27:08
-- UPDATE trigger on Submission 
DECLARE NUMROWS INTEGER;
BEGIN
  /* ERwin Builtin 2019년 7월 15일 월요일 오후 5:27:08 */
  /* Assignment  Submission on child update restrict */
  /* ERWIN_RELATION:CHECKSUM="00034775", PARENT_OWNER="", PARENT_TABLE="Assignment"
    CHILD_OWNER="", CHILD_TABLE="Submission"
    P2C_VERB_PHRASE="R/27", C2P_VERB_PHRASE="", 
    FK_CONSTRAINT="R_27", FK_COLUMNS="assignment_id" */
  SELECT count(*) INTO NUMROWS
    FROM Assignment
    WHERE
      /* %JoinFKPK(:%New,Assignment," = "," AND") */
      :new.assignment_id = Assignment.assignment_id;
  IF (
    /* %NotnullFK(:%New," IS NOT NULL AND") */
    
    NUMROWS = 0
  )
  THEN
    raise_application_error(
      -20007,
      'Cannot update Submission because Assignment does not exist.'
    );
  END IF;

  /* ERwin Builtin 2019년 7월 15일 월요일 오후 5:27:08 */
  /* User01  Submission on child update restrict */
  /* ERWIN_RELATION:CHECKSUM="00000000", PARENT_OWNER="", PARENT_TABLE="User01"
    CHILD_OWNER="", CHILD_TABLE="Submission"
    P2C_VERB_PHRASE="R/28", C2P_VERB_PHRASE="", 
    FK_CONSTRAINT="R_28", FK_COLUMNS="std_id" */
  SELECT count(*) INTO NUMROWS
    FROM User01
    WHERE
      /* %JoinFKPK(:%New,User01," = "," AND") */
      :new.std_id = User01.user_id;
  IF (
    /* %NotnullFK(:%New," IS NOT NULL AND") */
    
    NUMROWS = 0
  )
  THEN
    raise_application_error(
      -20007,
      'Cannot update Submission because User01 does not exist.'
    );
  END IF;

  /* ERwin Builtin 2019년 7월 15일 월요일 오후 5:27:08 */
  /* Attached_File  Submission on child update no action */
  /* ERWIN_RELATION:CHECKSUM="00000000", PARENT_OWNER="", PARENT_TABLE="Attached_File"
    CHILD_OWNER="", CHILD_TABLE="Submission"
    P2C_VERB_PHRASE="R/60", C2P_VERB_PHRASE="", 
    FK_CONSTRAINT="R_60", FK_COLUMNS="file_id" */
  SELECT count(*) INTO NUMROWS
    FROM Attached_File
    WHERE
      /* %JoinFKPK(:%New,Attached_File," = "," AND") */
      :new.file_id = Attached_File.file_id;
  IF (
    /* %NotnullFK(:%New," IS NOT NULL AND") */
    :new.file_id IS NOT NULL AND
    NUMROWS = 0
  )
  THEN
    raise_application_error(
      -20007,
      'Cannot update Submission because Attached_File does not exist.'
    );
  END IF;


-- ERwin Builtin 2019년 7월 15일 월요일 오후 5:27:08
END;
/


CREATE  TRIGGER tI_Teacher_Info BEFORE INSERT ON Teacher_Info for each row
-- ERwin Builtin 2019년 7월 15일 월요일 오후 5:27:08
-- INSERT trigger on Teacher_Info 
DECLARE NUMROWS INTEGER;
BEGIN
    /* ERwin Builtin 2019년 7월 15일 월요일 오후 5:27:08 */
    /* User01  Teacher_Info on child insert set null */
    /* ERWIN_RELATION:CHECKSUM="000105f9", PARENT_OWNER="", PARENT_TABLE="User01"
    CHILD_OWNER="", CHILD_TABLE="Teacher_Info"
    P2C_VERB_PHRASE="R/36", C2P_VERB_PHRASE="", 
    FK_CONSTRAINT="R_36", FK_COLUMNS="teacher_id" */
    UPDATE Teacher_Info
      SET
        /* %SetFK(Teacher_Info,NULL) */
        Teacher_Info.teacher_id = NULL
      WHERE
        NOT EXISTS (
          SELECT * FROM User01
            WHERE
              /* %JoinFKPK(:%New,User01," = "," AND") */
              :new.teacher_id = User01.user_id
        ) 
        /* %JoinPKPK(Teacher_Info,:%New," = "," AND") */
         and Teacher_Info.info_id = :new.info_id;


-- ERwin Builtin 2019년 7월 15일 월요일 오후 5:27:08
END;
/

CREATE  TRIGGER tU_Teacher_Info AFTER UPDATE ON Teacher_Info for each row
-- ERwin Builtin 2019년 7월 15일 월요일 오후 5:27:08
-- UPDATE trigger on Teacher_Info 
DECLARE NUMROWS INTEGER;
BEGIN
  /* ERwin Builtin 2019년 7월 15일 월요일 오후 5:27:08 */
  /* User01  Teacher_Info on child update no action */
  /* ERWIN_RELATION:CHECKSUM="000116c6", PARENT_OWNER="", PARENT_TABLE="User01"
    CHILD_OWNER="", CHILD_TABLE="Teacher_Info"
    P2C_VERB_PHRASE="R/36", C2P_VERB_PHRASE="", 
    FK_CONSTRAINT="R_36", FK_COLUMNS="teacher_id" */
  SELECT count(*) INTO NUMROWS
    FROM User01
    WHERE
      /* %JoinFKPK(:%New,User01," = "," AND") */
      :new.teacher_id = User01.user_id;
  IF (
    /* %NotnullFK(:%New," IS NOT NULL AND") */
    :new.teacher_id IS NOT NULL AND
    NUMROWS = 0
  )
  THEN
    raise_application_error(
      -20007,
      'Cannot update Teacher_Info because User01 does not exist.'
    );
  END IF;


-- ERwin Builtin 2019년 7월 15일 월요일 오후 5:27:08
END;
/


CREATE  TRIGGER tD_User01 AFTER DELETE ON User01 for each row
-- ERwin Builtin 2019년 7월 15일 월요일 오후 5:27:08
-- DELETE trigger on User01 
DECLARE NUMROWS INTEGER;
BEGIN
    /* ERwin Builtin 2019년 7월 15일 월요일 오후 5:27:08 */
    /* User01  Attendance on parent delete restrict */
    /* ERWIN_RELATION:CHECKSUM="000a296d", PARENT_OWNER="", PARENT_TABLE="User01"
    CHILD_OWNER="", CHILD_TABLE="Attendance"
    P2C_VERB_PHRASE="R/11", C2P_VERB_PHRASE="", 
    FK_CONSTRAINT="R_11", FK_COLUMNS="std_id" */
    SELECT count(*) INTO NUMROWS
      FROM Attendance
      WHERE
        /*  %JoinFKPK(Attendance,:%Old," = "," AND") */
        Attendance.std_id = :old.user_id;
    IF (NUMROWS > 0)
    THEN
      raise_application_error(
        -20001,
        'Cannot delete User01 because Attendance exists.'
      );
    END IF;

    /* ERwin Builtin 2019년 7월 15일 월요일 오후 5:27:08 */
    /* User01  Apply on parent delete set null */
    /* ERWIN_RELATION:CHECKSUM="00000000", PARENT_OWNER="", PARENT_TABLE="User01"
    CHILD_OWNER="", CHILD_TABLE="Apply"
    P2C_VERB_PHRASE="R/13", C2P_VERB_PHRASE="", 
    FK_CONSTRAINT="R_13", FK_COLUMNS="user_id" */
    UPDATE Apply
      SET
        /* %SetFK(Apply,NULL) */
        Apply.user_id = NULL
      WHERE
        /* %JoinFKPK(Apply,:%Old," = "," AND") */
        Apply.user_id = :old.user_id;

    /* ERwin Builtin 2019년 7월 15일 월요일 오후 5:27:08 */
    /* User01  Score on parent delete restrict */
    /* ERWIN_RELATION:CHECKSUM="00000000", PARENT_OWNER="", PARENT_TABLE="User01"
    CHILD_OWNER="", CHILD_TABLE="Score"
    P2C_VERB_PHRASE="R/16", C2P_VERB_PHRASE="", 
    FK_CONSTRAINT="R_16", FK_COLUMNS="std_id" */
    SELECT count(*) INTO NUMROWS
      FROM Score
      WHERE
        /*  %JoinFKPK(Score,:%Old," = "," AND") */
        Score.std_id = :old.user_id;
    IF (NUMROWS > 0)
    THEN
      raise_application_error(
        -20001,
        'Cannot delete User01 because Score exists.'
      );
    END IF;

    /* ERwin Builtin 2019년 7월 15일 월요일 오후 5:27:08 */
    /* User01  Submission on parent delete restrict */
    /* ERWIN_RELATION:CHECKSUM="00000000", PARENT_OWNER="", PARENT_TABLE="User01"
    CHILD_OWNER="", CHILD_TABLE="Submission"
    P2C_VERB_PHRASE="R/28", C2P_VERB_PHRASE="", 
    FK_CONSTRAINT="R_28", FK_COLUMNS="std_id" */
    SELECT count(*) INTO NUMROWS
      FROM Submission
      WHERE
        /*  %JoinFKPK(Submission,:%Old," = "," AND") */
        Submission.std_id = :old.user_id;
    IF (NUMROWS > 0)
    THEN
      raise_application_error(
        -20001,
        'Cannot delete User01 because Submission exists.'
      );
    END IF;

    /* ERwin Builtin 2019년 7월 15일 월요일 오후 5:27:08 */
    /* User01  Lecture_review on parent delete set null */
    /* ERWIN_RELATION:CHECKSUM="00000000", PARENT_OWNER="", PARENT_TABLE="User01"
    CHILD_OWNER="", CHILD_TABLE="Lecture_review"
    P2C_VERB_PHRASE="R/30", C2P_VERB_PHRASE="", 
    FK_CONSTRAINT="R_30", FK_COLUMNS="writer" */
    UPDATE Lecture_review
      SET
        /* %SetFK(Lecture_review,NULL) */
        Lecture_review.writer = NULL
      WHERE
        /* %JoinFKPK(Lecture_review,:%Old," = "," AND") */
        Lecture_review.writer = :old.user_id;

    /* ERwin Builtin 2019년 7월 15일 월요일 오후 5:27:08 */
    /* User01  Teacher_Info on parent delete set null */
    /* ERWIN_RELATION:CHECKSUM="00000000", PARENT_OWNER="", PARENT_TABLE="User01"
    CHILD_OWNER="", CHILD_TABLE="Teacher_Info"
    P2C_VERB_PHRASE="R/36", C2P_VERB_PHRASE="", 
    FK_CONSTRAINT="R_36", FK_COLUMNS="teacher_id" */
    UPDATE Teacher_Info
      SET
        /* %SetFK(Teacher_Info,NULL) */
        Teacher_Info.teacher_id = NULL
      WHERE
        /* %JoinFKPK(Teacher_Info,:%Old," = "," AND") */
        Teacher_Info.teacher_id = :old.user_id;

    /* ERwin Builtin 2019년 7월 15일 월요일 오후 5:27:08 */
    /* User01  Data_room on parent delete set null */
    /* ERWIN_RELATION:CHECKSUM="00000000", PARENT_OWNER="", PARENT_TABLE="User01"
    CHILD_OWNER="", CHILD_TABLE="Data_room"
    P2C_VERB_PHRASE="R/39", C2P_VERB_PHRASE="", 
    FK_CONSTRAINT="R_39", FK_COLUMNS="writer" */
    UPDATE Data_room
      SET
        /* %SetFK(Data_room,NULL) */
        Data_room.writer = NULL
      WHERE
        /* %JoinFKPK(Data_room,:%Old," = "," AND") */
        Data_room.writer = :old.user_id;

    /* ERwin Builtin 2019년 7월 15일 월요일 오후 5:27:08 */
    /* User01  Qna_H on parent delete set null */
    /* ERWIN_RELATION:CHECKSUM="00000000", PARENT_OWNER="", PARENT_TABLE="User01"
    CHILD_OWNER="", CHILD_TABLE="Qna_H"
    P2C_VERB_PHRASE="R/40", C2P_VERB_PHRASE="", 
    FK_CONSTRAINT="R_40", FK_COLUMNS="std_id" */
    UPDATE Qna_H
      SET
        /* %SetFK(Qna_H,NULL) */
        Qna_H.std_id = NULL
      WHERE
        /* %JoinFKPK(Qna_H,:%Old," = "," AND") */
        Qna_H.std_id = :old.user_id;

    /* ERwin Builtin 2019년 7월 15일 월요일 오후 5:27:08 */
    /* User01  Notice on parent delete set null */
    /* ERWIN_RELATION:CHECKSUM="00000000", PARENT_OWNER="", PARENT_TABLE="User01"
    CHILD_OWNER="", CHILD_TABLE="Notice"
    P2C_VERB_PHRASE="R/42", C2P_VERB_PHRASE="", 
    FK_CONSTRAINT="R_42", FK_COLUMNS="writer" */
    UPDATE Notice
      SET
        /* %SetFK(Notice,NULL) */
        Notice.writer = NULL
      WHERE
        /* %JoinFKPK(Notice,:%Old," = "," AND") */
        Notice.writer = :old.user_id;

    /* ERwin Builtin 2019년 7월 15일 월요일 오후 5:27:08 */
    /* User01  Qna_L on parent delete set null */
    /* ERWIN_RELATION:CHECKSUM="00000000", PARENT_OWNER="", PARENT_TABLE="User01"
    CHILD_OWNER="", CHILD_TABLE="Qna_L"
    P2C_VERB_PHRASE="R/45", C2P_VERB_PHRASE="", 
    FK_CONSTRAINT="R_45", FK_COLUMNS="std_id" */
    UPDATE Qna_L
      SET
        /* %SetFK(Qna_L,NULL) */
        Qna_L.std_id = NULL
      WHERE
        /* %JoinFKPK(Qna_L,:%Old," = "," AND") */
        Qna_L.std_id = :old.user_id;

    /* ERwin Builtin 2019년 7월 15일 월요일 오후 5:27:08 */
    /* User01  LectureUser on parent delete restrict */
    /* ERWIN_RELATION:CHECKSUM="00000000", PARENT_OWNER="", PARENT_TABLE="User01"
    CHILD_OWNER="", CHILD_TABLE="LectureUser"
    P2C_VERB_PHRASE="R/52", C2P_VERB_PHRASE="", 
    FK_CONSTRAINT="R_52", FK_COLUMNS="user_id" */
    SELECT count(*) INTO NUMROWS
      FROM LectureUser
      WHERE
        /*  %JoinFKPK(LectureUser,:%Old," = "," AND") */
        LectureUser.user_id = :old.user_id;
    IF (NUMROWS > 0)
    THEN
      raise_application_error(
        -20001,
        'Cannot delete User01 because LectureUser exists.'
      );
    END IF;

    /* ERwin Builtin 2019년 7월 15일 월요일 오후 5:27:08 */
    /* User01  Attached_File on parent delete set null */
    /* ERWIN_RELATION:CHECKSUM="00000000", PARENT_OWNER="", PARENT_TABLE="User01"
    CHILD_OWNER="", CHILD_TABLE="Attached_File"
    P2C_VERB_PHRASE="R/64", C2P_VERB_PHRASE="", 
    FK_CONSTRAINT="R_64", FK_COLUMNS="reg_id" */
    UPDATE Attached_File
      SET
        /* %SetFK(Attached_File,NULL) */
        Attached_File.reg_id = NULL
      WHERE
        /* %JoinFKPK(Attached_File,:%Old," = "," AND") */
        Attached_File.reg_id = :old.user_id;


-- ERwin Builtin 2019년 7월 15일 월요일 오후 5:27:08
END;
/

CREATE  TRIGGER tU_User01 AFTER UPDATE ON User01 for each row
-- ERwin Builtin 2019년 7월 15일 월요일 오후 5:27:08
-- UPDATE trigger on User01 
DECLARE NUMROWS INTEGER;
BEGIN
  /* ERwin Builtin 2019년 7월 15일 월요일 오후 5:27:08 */
  /* User01  Attendance on parent update restrict */
  /* ERWIN_RELATION:CHECKSUM="000b4007", PARENT_OWNER="", PARENT_TABLE="User01"
    CHILD_OWNER="", CHILD_TABLE="Attendance"
    P2C_VERB_PHRASE="R/11", C2P_VERB_PHRASE="", 
    FK_CONSTRAINT="R_11", FK_COLUMNS="std_id" */
  IF
    /* %JoinPKPK(:%Old,:%New," <> "," OR ") */
    :old.user_id <> :new.user_id
  THEN
    SELECT count(*) INTO NUMROWS
      FROM Attendance
      WHERE
        /*  %JoinFKPK(Attendance,:%Old," = "," AND") */
        Attendance.std_id = :old.user_id;
    IF (NUMROWS > 0)
    THEN 
      raise_application_error(
        -20005,
        'Cannot update User01 because Attendance exists.'
      );
    END IF;
  END IF;

  /* User01  Apply on parent update set null */
  /* ERWIN_RELATION:CHECKSUM="00000000", PARENT_OWNER="", PARENT_TABLE="User01"
    CHILD_OWNER="", CHILD_TABLE="Apply"
    P2C_VERB_PHRASE="R/13", C2P_VERB_PHRASE="", 
    FK_CONSTRAINT="R_13", FK_COLUMNS="user_id" */
  IF
    /* %JoinPKPK(:%Old,:%New," <> "," OR ") */
    :old.user_id <> :new.user_id
  THEN
    UPDATE Apply
      SET
        /* %SetFK(Apply,NULL) */
        Apply.user_id = NULL
      WHERE
        /* %JoinFKPK(Apply,:%Old," = ",",") */
        Apply.user_id = :old.user_id;
  END IF;

  /* ERwin Builtin 2019년 7월 15일 월요일 오후 5:27:08 */
  /* User01  Score on parent update restrict */
  /* ERWIN_RELATION:CHECKSUM="00000000", PARENT_OWNER="", PARENT_TABLE="User01"
    CHILD_OWNER="", CHILD_TABLE="Score"
    P2C_VERB_PHRASE="R/16", C2P_VERB_PHRASE="", 
    FK_CONSTRAINT="R_16", FK_COLUMNS="std_id" */
  IF
    /* %JoinPKPK(:%Old,:%New," <> "," OR ") */
    :old.user_id <> :new.user_id
  THEN
    SELECT count(*) INTO NUMROWS
      FROM Score
      WHERE
        /*  %JoinFKPK(Score,:%Old," = "," AND") */
        Score.std_id = :old.user_id;
    IF (NUMROWS > 0)
    THEN 
      raise_application_error(
        -20005,
        'Cannot update User01 because Score exists.'
      );
    END IF;
  END IF;

  /* ERwin Builtin 2019년 7월 15일 월요일 오후 5:27:08 */
  /* User01  Submission on parent update restrict */
  /* ERWIN_RELATION:CHECKSUM="00000000", PARENT_OWNER="", PARENT_TABLE="User01"
    CHILD_OWNER="", CHILD_TABLE="Submission"
    P2C_VERB_PHRASE="R/28", C2P_VERB_PHRASE="", 
    FK_CONSTRAINT="R_28", FK_COLUMNS="std_id" */
  IF
    /* %JoinPKPK(:%Old,:%New," <> "," OR ") */
    :old.user_id <> :new.user_id
  THEN
    SELECT count(*) INTO NUMROWS
      FROM Submission
      WHERE
        /*  %JoinFKPK(Submission,:%Old," = "," AND") */
        Submission.std_id = :old.user_id;
    IF (NUMROWS > 0)
    THEN 
      raise_application_error(
        -20005,
        'Cannot update User01 because Submission exists.'
      );
    END IF;
  END IF;

  /* User01  Lecture_review on parent update set null */
  /* ERWIN_RELATION:CHECKSUM="00000000", PARENT_OWNER="", PARENT_TABLE="User01"
    CHILD_OWNER="", CHILD_TABLE="Lecture_review"
    P2C_VERB_PHRASE="R/30", C2P_VERB_PHRASE="", 
    FK_CONSTRAINT="R_30", FK_COLUMNS="writer" */
  IF
    /* %JoinPKPK(:%Old,:%New," <> "," OR ") */
    :old.user_id <> :new.user_id
  THEN
    UPDATE Lecture_review
      SET
        /* %SetFK(Lecture_review,NULL) */
        Lecture_review.writer = NULL
      WHERE
        /* %JoinFKPK(Lecture_review,:%Old," = ",",") */
        Lecture_review.writer = :old.user_id;
  END IF;

  /* User01  Teacher_Info on parent update set null */
  /* ERWIN_RELATION:CHECKSUM="00000000", PARENT_OWNER="", PARENT_TABLE="User01"
    CHILD_OWNER="", CHILD_TABLE="Teacher_Info"
    P2C_VERB_PHRASE="R/36", C2P_VERB_PHRASE="", 
    FK_CONSTRAINT="R_36", FK_COLUMNS="teacher_id" */
  IF
    /* %JoinPKPK(:%Old,:%New," <> "," OR ") */
    :old.user_id <> :new.user_id
  THEN
    UPDATE Teacher_Info
      SET
        /* %SetFK(Teacher_Info,NULL) */
        Teacher_Info.teacher_id = NULL
      WHERE
        /* %JoinFKPK(Teacher_Info,:%Old," = ",",") */
        Teacher_Info.teacher_id = :old.user_id;
  END IF;

  /* User01  Data_room on parent update set null */
  /* ERWIN_RELATION:CHECKSUM="00000000", PARENT_OWNER="", PARENT_TABLE="User01"
    CHILD_OWNER="", CHILD_TABLE="Data_room"
    P2C_VERB_PHRASE="R/39", C2P_VERB_PHRASE="", 
    FK_CONSTRAINT="R_39", FK_COLUMNS="writer" */
  IF
    /* %JoinPKPK(:%Old,:%New," <> "," OR ") */
    :old.user_id <> :new.user_id
  THEN
    UPDATE Data_room
      SET
        /* %SetFK(Data_room,NULL) */
        Data_room.writer = NULL
      WHERE
        /* %JoinFKPK(Data_room,:%Old," = ",",") */
        Data_room.writer = :old.user_id;
  END IF;

  /* User01  Qna_H on parent update set null */
  /* ERWIN_RELATION:CHECKSUM="00000000", PARENT_OWNER="", PARENT_TABLE="User01"
    CHILD_OWNER="", CHILD_TABLE="Qna_H"
    P2C_VERB_PHRASE="R/40", C2P_VERB_PHRASE="", 
    FK_CONSTRAINT="R_40", FK_COLUMNS="std_id" */
  IF
    /* %JoinPKPK(:%Old,:%New," <> "," OR ") */
    :old.user_id <> :new.user_id
  THEN
    UPDATE Qna_H
      SET
        /* %SetFK(Qna_H,NULL) */
        Qna_H.std_id = NULL
      WHERE
        /* %JoinFKPK(Qna_H,:%Old," = ",",") */
        Qna_H.std_id = :old.user_id;
  END IF;

  /* User01  Notice on parent update set null */
  /* ERWIN_RELATION:CHECKSUM="00000000", PARENT_OWNER="", PARENT_TABLE="User01"
    CHILD_OWNER="", CHILD_TABLE="Notice"
    P2C_VERB_PHRASE="R/42", C2P_VERB_PHRASE="", 
    FK_CONSTRAINT="R_42", FK_COLUMNS="writer" */
  IF
    /* %JoinPKPK(:%Old,:%New," <> "," OR ") */
    :old.user_id <> :new.user_id
  THEN
    UPDATE Notice
      SET
        /* %SetFK(Notice,NULL) */
        Notice.writer = NULL
      WHERE
        /* %JoinFKPK(Notice,:%Old," = ",",") */
        Notice.writer = :old.user_id;
  END IF;

  /* User01  Qna_L on parent update set null */
  /* ERWIN_RELATION:CHECKSUM="00000000", PARENT_OWNER="", PARENT_TABLE="User01"
    CHILD_OWNER="", CHILD_TABLE="Qna_L"
    P2C_VERB_PHRASE="R/45", C2P_VERB_PHRASE="", 
    FK_CONSTRAINT="R_45", FK_COLUMNS="std_id" */
  IF
    /* %JoinPKPK(:%Old,:%New," <> "," OR ") */
    :old.user_id <> :new.user_id
  THEN
    UPDATE Qna_L
      SET
        /* %SetFK(Qna_L,NULL) */
        Qna_L.std_id = NULL
      WHERE
        /* %JoinFKPK(Qna_L,:%Old," = ",",") */
        Qna_L.std_id = :old.user_id;
  END IF;

  /* ERwin Builtin 2019년 7월 15일 월요일 오후 5:27:08 */
  /* User01  LectureUser on parent update restrict */
  /* ERWIN_RELATION:CHECKSUM="00000000", PARENT_OWNER="", PARENT_TABLE="User01"
    CHILD_OWNER="", CHILD_TABLE="LectureUser"
    P2C_VERB_PHRASE="R/52", C2P_VERB_PHRASE="", 
    FK_CONSTRAINT="R_52", FK_COLUMNS="user_id" */
  IF
    /* %JoinPKPK(:%Old,:%New," <> "," OR ") */
    :old.user_id <> :new.user_id
  THEN
    SELECT count(*) INTO NUMROWS
      FROM LectureUser
      WHERE
        /*  %JoinFKPK(LectureUser,:%Old," = "," AND") */
        LectureUser.user_id = :old.user_id;
    IF (NUMROWS > 0)
    THEN 
      raise_application_error(
        -20005,
        'Cannot update User01 because LectureUser exists.'
      );
    END IF;
  END IF;

  /* User01  Attached_File on parent update set null */
  /* ERWIN_RELATION:CHECKSUM="00000000", PARENT_OWNER="", PARENT_TABLE="User01"
    CHILD_OWNER="", CHILD_TABLE="Attached_File"
    P2C_VERB_PHRASE="R/64", C2P_VERB_PHRASE="", 
    FK_CONSTRAINT="R_64", FK_COLUMNS="reg_id" */
  IF
    /* %JoinPKPK(:%Old,:%New," <> "," OR ") */
    :old.user_id <> :new.user_id
  THEN
    UPDATE Attached_File
      SET
        /* %SetFK(Attached_File,NULL) */
        Attached_File.reg_id = NULL
      WHERE
        /* %JoinFKPK(Attached_File,:%Old," = ",",") */
        Attached_File.reg_id = :old.user_id;
  END IF;


-- ERwin Builtin 2019년 7월 15일 월요일 오후 5:27:08
END;
/

