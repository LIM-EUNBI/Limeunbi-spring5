#### SQL문법
- @Transectional // 스프링에서 에러 발생시 update 부분이 롤백.
-
#### 20210605(금)
* CASE문
- SELECT
- CASE WHEN comm IS NULL THEN 0
- WHEN comm = 0 THEN 100
- WHEN comm > 0 THEN comm
- END "CASE 출력문" FROM emp;

* CREATE (테이블 생성), 다른 테이블 복사
- CREATE TABLE dept02 AS SELECT * FROM dept WHERE 1=0;

* INSERT (row 추가)
- INSERT INTO dept02 (deptno, dname, loc) values(10, '개발부서', '천안');

* DELETE 
- DELETE FROM dept02 WHERE deptno = 10;
- DELECT FROM dept02 WHERE deptno > 0;

* DROP (테이블을 없애는 명령)
- DROP TABLE dept02;

* UPDATE 테이블명 SET 필드명 = '수정할 값' WHERE
- UPDATE emp01 SET ENAME = 'EUNBI' WHERE empno = 7839;
- UPDATE emp01 SET sal = sal*1.1; // 직원들 연봉을 10프로 인상
- UPDATE emp01 SET hiredate = sysdate; // 입사일을 현재 날짜로 변경
-
-
* JOIN
- 오라클 조인
- SELECT * FROM emp, dept WHERE emp.deptno = dept.deptno;
- SELECT dept.dname, emp.* FROM emp, dept WHERE emp.deptno = dept.deptno AND emp.ename = 'SCOTT';
- 표준조인 (ANSI)
- SELECT * FROM emp e JOIN dept d ON e.deptno = d.deptno;
- SELECT d.dname, e.* FROM emp e JOIN dept d ON e.deptno = d.deptno WHERE e.ename = 'SCOTT';
- tbl_board 테이블과 tbl_reply 테이블 조인
- FROM tbl_board BOD 
- INNER  JOIN tbl_reply REP ON BOD.bno = REP.bno
- GROUP BY BOD.bno, title, writer, BOD.reg_date, view_count
- ORDER BY BOD.bno;
-
-

* 그룹 함수
- SELECT ename, ROUND(sal,-3) FROM emp01;
- SELECT SUM(sal) FROM emp;
- SELECT round(AVG(sal), 2) FROM emp;
- SELECT max(sal), min(sal) FROM emp;
- SELECT COUNT(*) FROM emp WHERE sal >= (SELECT ROUND(AVG(sal)) FROM emp);
- SELECT deptno, SUM(sal) FROM emp GROUP BY deptno ORDER BY SUM(sal) DESC;
-
- CREAT TABLE
- CREATE TABLE TBL_MEMBER_DEL
- (
- USER_ID VARCHAR2(50) PRIMARY KEY,
- USER_PW VARCHAR2(255)
- );
- 
- ALTER 
- ALTER TABLE dept MODIFY(deptno NUMBER(4)); // number 길이를 4로 변경
- ALTER TABLE RENAME COLUMN tbl_member email TO user_email; // 테이블 컬럼명 변경




#### 20210604(목)
* sqldeveloper 깔끔하게 쿼리문 정리 control + F7

* DESC (테이블 구조 확인) DESC dept;

* concat (컬럼값 뒤에 붙이기), 레포트 작성 시 많이 사용된다.
- ex) SELECT concat(deptno,'번') AS "번호" FROM dept;

* DUAL 가상 테이블 이름. 테이블이 없는 내용을 select를 할 때
- ex) SELECT '임은비' AS "이름" FROM dual;
- SELECT 3+5 AS "3 더하기 8은" FROM dual;

* 레코드(row) : 컬럼(필드field)들로 구성
- SELECT concat(count(*),'명') AS "연봉이 2000이상인 직원" 
- FROM emp WHERE sal > 2000;

* 큰따옴표(필드명), 작은 따옴표(문자열 출력)

* (NOT) BETWEEN, (NOT) IN
- SELECT * FROM emp WHERE sal BETWEEN 1000 AND 2000;
- SELECT * FROM emp WHERE comm IN (300,500,1400);

* (NOT) LIKE
- SELECT * FROM emp WHERE LIKE 'F%';

* NULL(IS NULL, IS NOT NULL)
- SELECT * FROM emp WHERE comm IS NULL;

* NVL(Null VaLue) NULL값을 다른 값으로 지정
- SELECT E.*,NVL(comm,0) FROM emp E;

* NVL2(필드명, NULL이 아닐 때, NULL일 때)
- SELECT E.*,NVL(comm,100,0) FROM emp E;

* DECODE (NVL, NVL2와 똑같은 기능)
- SELECT DECODE(comm,null,"n","y"), E.* FROM emp E;

* ORDER BY
- SELECT * FROM emp ORDER BY comm;

* limit는 MYSQL에서만 적용
 
 * 서브쿼리
- SELECT ROWNUM, E.* FROM (
SELECT * FROM emp ORDER BY sal DESC
) E WHERE ROWNUM = 1;

* DISTINCT 중복값 제거 
- SELECT DISTINCT(deptno) FROM emp;

* (중요!)문자열을 연결할 때 CONTCAT함수 외에 || 파이프라인 2개를 겹쳐서 구현
SELECT ename ||' is a '|| job AS "문자열 연결" FROM emp;


* 함수 
- ABS(절대값), Floor(1.5 = 1) <-> Ceil
- ROUND(반올림), TRUNC(버리는 함수), MOD(나머지 함수)
- UPPER(대문자), LOWER(소문자), LENGTH(길이), INSTR(문자의 위치를 구하는 함수)
- SUBSTR(매개변수로 입력한 숫자위치 만큼 추출), LPAD/RPAD(left,right padding)
- LTRIM/RTRIM(left,right의 값을 입력한 갯수만큼 자르는 함수)

* 날짜 함수(SYSDATA - 오라클 전용 함수로써, 게시물 입력시간, 회원등록 시간 등, systimestamp - 밀리초까지 저장) to_char() 날짜를 문자열로 변환 
- SELECT to_char(systimestamp,'yyyy-mm-dd hh24:mi:ss:ff') FROM DUAL;

* 날짜 더하기 빼기
- SELECT sysdate + 1 FROM dual;
- SELECT sysdate - 1 FROM DUAL;

* 회원가입 후 6개월동안 비밀번호 수정이 없었을 때 회원에게 공지 서비스 처리
- SELECT * FROM
- TBL_MEMBER
- WHERE UPDATE_DATE < ADD_MONTHS(SYSDATE,-6);





