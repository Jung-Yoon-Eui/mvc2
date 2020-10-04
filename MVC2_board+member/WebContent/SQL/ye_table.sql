CREATE TABLE BOARD(
	BOARD_NUM INT,
	BOARD_NAME VARCHAR2(20),
	BOARD_PASS VARCHAR2(15),
	BOARD_SUBJECT VARCHAR2(50),
	BOARD_CONTENT VARCHAR2(2000),
	BOARD_FILE VARCHAR2(50),
	BOARD_RE_REF INT,
	BOARD_RE_LEV INT,
	BOARD_RE_SEQ INT,
	BOARD_READCOUNT INT,
	BOARD_DATE DATE,
	PRIMARY KEY(BOARD_NUM)
);

CREATE TABLE MEMBER0921(
	USER_NO NUMBER PRIMARY KEY,
	idid VARCHAR2(24) NOT NULL UNIQUE,
	pw VARCHAR2(24) NOT NULL UNIQUE,
	mail VARCHAR2(500),
	name1 VARCHAR2(10),
	birthday VARCHAR2(20),
	chk VARCHAR2(100),
	address VARCHAR2(500)
);

CREATE SEQUENCE MEM_NUM;

INSERT INTO MEMBER0921 (USER_NO, idid, pw, name1)
    VALUES (MEM_NUM.NEXTVAL,'ADMIN', 'ADMIN1234','관리자');

COMMIT;

SELECT * FROM MEMBER0921;