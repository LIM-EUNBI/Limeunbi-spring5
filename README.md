#### 작업 예정
- 스프링 프로젝트 순서
- 1. JUnit -> 마이바티스(DB핸들링) -> 페이징 기능 -> 검색 기능 -> 첨부파일 기능 -> 스프링 시큐리티(로그인 인증) 
- 2. 댓글 처리(RestAPI생성) -> 네이버 아이디 로그인(외부 API사용) -> 헤로쿠 클라우드 배포
- 3. 문서 작업(화면 기획서,화면 설계서)

#### 작업 환경
- 스프링에서 오라클 연동 순서 :
- 1 : jdbc(Java DataBase Connection) 확장 모듈 pom에 추가
- 2 : 오라클 접속 드라이버 확장 모듈을 직접 jar 파일을 추가

- root-context.xml 파일에 오라클 커넥션 빈(스프링 클래스) 추가
- 스프링이 관리하는 클래스를 추가하는 방법 : @Controller, @Repository 등 or xml에 빈을 추가
-
- JUnit에서 SQL로그 상황이 나오게 하는 드라이버를 pom.xml에 추가.
- src/main/resources에 log4jdbc.log4j2.properties 파일 추가

- 마이바티스 추가
- 1. pom.xml에 마이바티스 모듈 추가
- 2. 마이바티스 설정파일 추가 root-context.xml에 빈 추가 /main/resources에 mapper폴더 생성 후 mapper폴더 안에 oracle, mysql 폴더 생성



#### 공부 자료
- 오라클 문법 : https://wikidocs.net/3910
- 인터프리터 https://colab.research.google.com/drive/1wyL8Fg3-l8PKpmqbBCxd_ewBGDKdTzuw
- 클래스 변수, 인스턴스 변수 설명 = https://itmining.tistory.com/20
- 추상화 설명 = https://limkydev.tistory.com/188 (사용법)
- https://victorydntmd.tistory.com/117
- 아스키, 유니코드 https://whatisthenext.tistory.com/103
- 시노님 설명 https://mine-it-record.tistory.com/68
- @어노테이션 속성들에 대한 설명 https://sarc.io/index.php/development/1139-requestmapping

#### 20210616(수) 작업
- 스프링의 AOP기능 구현
- BoardTypeVO 클래스 생성 → boardTypeMapper 파일 → DAO인터페이스, DAO클래스 → 
- service인터페이스, service클래스 → adminController → jsp화면
- 


#### 20210615(화) 작업
- insert 구현
- 아이디 중복체크(필수) 버튼으로 이벤트 처리 - RestAPI 
- ㄴ> src/main/jave에 com.edu.util 패키지 생성
- servlet.context.xml 파일에 com.edu.util 빈 추가
- CommonUtil 구현, singleton설명
- ID중복체크 (O), 시큐리티 (O)


#### 20210614(월) 작업
- member_view에서 삭제버튼 구현(adminController에 추가)
- multipart(첨부파일기능) -> commons.fileupload pom.xml에 추가, servlet에 beans추가

- update 구현 (adminController에 추가-updateMember, updateMemberForm 2개 추가)
- member_update (O)
- 한글깨짐 UTF-8유니코드 인코딩파일 web.xml에 추가
- update 시큐리티 코드 추가


#### 20210611(금) 작업
- model을 이용해서 결과를 JSP로 구현(JSTL로 구현)
- jstl 태그 임포트(taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c") 후
- 회원 리스트(member_list.jsp에서) 바인딩.
- (taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c") timestamp변환 태그 임포트
- listmember 빈값일 때 if문 사용해서 조회된 값이 없습니다. 출력.
- 해킹 방지를 막기 위해서 c:out 코드를 추가
- member_list에서 페이징처리 부분 코드 추가, 'active' 구현
- keyword, type으로 검색해도 페이징 처리 될 수 있도록 코드 추가
- @ModelAttribute("pageVO") 추가
- prev, next 코드 추가 (관리자단 회원목록 처리 마무리O)

- 관리자 회원관리 CRUD화면 JSP처리 시작
- member_view 파일 생성 후 Controller에 viewMemberForm 메서드 추가.
- list목록에서 view 페이지로 이동 후 목록을 눌렀을 때 page, search_keyword등 파라미터 그대로 전달, 
- 즉 목록을 눌러도 페이징 초기화가 아닌 내가 머물렀던 페이징 그대로 구현.


#### 20210610(목) 작업
- readMember 추가
- pom.xml파일에 스프링 시큐리티 추가해준 뒤 , properties에 <spring.security.version>5.2.3.RELEASE</spring.security.version> 추가.
- updateMember 추가 (시큐리티 코드 추가)

- 컨트롤러를 이용해서 관리자단 회원관리 화면 JSP 만들기 시작.
- 컨트롤러에 selectMember ReqeustMapping 추가.
- WEB-INF admin 폴더에 member/member_list.jsp 추가
- resources/admin/board_list wrapper부분 복사해서 WEB-INF admin 폴더에 member/member_list.jsp 붙여넣기


#### 20210609(수) 작업
- 페이징 코드 작성 완료 후 mapper에 select문 추가
- memberDAO 인터페이스에 추가 후 MemberDAO 클래스에서 countMember 재정의
- DataSourceTest 파일에서 구현.
- search_keyword(원래는 mapper-DAO-service-junit 순으로 코딩했는데 반대로 코딩)
- <![CDATA[]]> : 태그 안쪽에 부등호를 사용하기 위해서 문자열 변환 태그
- memberMapper 파일에 서브쿼리문, if문 작성 후
- 공통쿼리를 sqlWhere문으로 따로 뺀 뒤, include로 sqlWhere 사용.
- insert, delete까지 추가.


#### 20210608(화) 작업
- mapper/oracle 폴더에 IF_MemberMapper.xml 파일 생성.
- com.edu.dao에 MemberDAO 인터페이스 추가 후 MemberDAO 클래스 생성.
- ㄴ> *인터페이스 만드는 목적 : 복잡한 구현클래스를 간단하게 구조화 시켜서 개발자가 관리하기 편하게 정리하는 역할.
- com.edu.service 패키지 생성 후 IF_MemberService 인터페이스 추가 후 MemberService 클래스 생성
- root-context에서 Namespace에서 context 추가하고
- com.edu.dao, com.edu.service 빈으로 위치 지정
- jUnit에서 위 작업한 내용을 CRUD 테스트
- 페이징 구현하기전 쿼리로 테스트
- 페이징에 사용될 변수들 (queryStartNo, queryPerPageNum, page, perPageNum, startPage, endPage)
- 검색에 사용되는 변수 (search_keyword, search_type)
- 
SELECT TB.* FROM
(
    SELECT ROWNUM AS RNUM, TA.* FROM
    (
        SELECT * FROM tbl_member
        ORDER BY reg_date DESC
    ) TA WHERE ROWNUM <= (2*5)+5
) TB WHERE TB.RNUM > 2*5
-- 현재 페이지수의 변수 a*b: 0*10, 1*10, 2*10, 3*10, 4*10.... : page*b
-- 1페이지당 보여줄 ROW b: 10, 20, 30 : queryPerPageNum

- com.edu.vo에 PageVO 클래스 생성
 

- 스프링코딩 작업순서 1부터6까지(아래)
- ERD를 기준으로 VO클래스를 생성.
- M-V-C 사이에 데이터를 입출력하는 임시저장 공간(VO클래스-멤버변수+Get/Set메서드) 생성
- 보통 ValueObject클래스는 DB테이블과 1:1로 매칭이 됩니다.
- 매퍼쿼리(마이바티스사용)를 만듭니다.(VO사용해서쿼리생성).
- DAO(데이터엑세스오브젝트,DTO)클래스를 생성(SqlSession사용쿼리실행).*오늘 Sql세션은 root-context에 빈으로 만들었습니다.(1개)
- 스프링 부트(간단한 프로젝트)에서는 4번 Service클래스가 없이 바로 컨트롤러로 이동합니다.
- Service(서비스)클래스생성(서비스에 쿼리결과를 담아 놓습니다.)(1개)
- Controller(컨트롤러)클래스생성(서비스결과를 JSP로 보냅니다.)
- JSP(View파일) 생성(컨트롤러의Model객체를 JSTL을 이용해 화면에 뿌려 줍니다.)

#### 20210607(월) 작업
- 오라클 DB관리 Application Express -> administration -> 로그인 (admin/초기 비밀번호)Apmsetup1!-system 비밀번호와는 별개 -> 
- 오라클 접속 오류 17002 해결
- https://velog.io/@qwerspace/%EC%98%A4%EB%9D%BC%ED%81%B4-%EC%97%90%EB%9F%AC-%EC%97%85%EC%B2%B4-%EC%BD%94%EB%93%9C-17002

- XE2 workspace 만들고 삭제 -> sqldeveloper에서 system으로 접속해서
- SELECT * FROM all_users; 쿼리문 실행해보면 xe2가 아직도 존재하는 점 확인
- DROP USER XE2 CASCADE; 실행문으로 삭제.
- 오라클은 스프링과 같은 방식 패키지명안에 함수, 프로시저(오라클 프로그램)를 만들 수 있다.
- 패키지명이 길어서 사용시 개발자에게 부담이 되기 때문에 시노님(동의어)를 사용.

- 시퀀스 : DB에서 기본으로 MAX값을 구해서 + 1을 해준다. 고유번호가 중복되지 않고
-       자동으로 1증가해준다. (지금 하는 프로젝트에서는 게시물SEQ_BNO, 댓글 시퀀스SEQ_RNO)
-
- DB프로그램 방식(프로시저)로 더미 데이터 추가(회원관리 100명), 게시판(공지사항 50, 갤러리 50)
- 프로시저 실행 : CALL 프로시저명 (값);
- 마이바티스(mybatis) : 스프링에서 쿼리를 관리 (pom.xml에 마이바티스 모듈 추가)

- DataSourceTest에 selectMember 메서드 추가
- main/java에 com.edu.vo 패키지 생성 후 MemberVO 클래스 생성.
- ㄴ> lombok.jar를 사용해서 getter,setter를 생략할 수도 있다.

#### 20210604(금) 작업
- JUnit에서 SQL로그 상황이 나오게 하는 드라이버를 pom.xml에 추가.
- src/main/resources에 log4jdbc.log4j2.properties 파일 추가
-
- 오라클일 때 : localhost:1521/XE 접속 URL끝의 XE 서비스 ID명
- 오라클은 사용자명이 테이블 스키마명(DB명)
- Mysql : localhost:3306/XE URL끝의 XE 스키마명(DB명)
- 오라클 실습


#### 20210603(목) 작업
- JUnit 테스트 : 오라클 연동한 후 회원관리 CRUB 테스트 진행
- 오라클 실습 


#### 20210602(수) 작업
- 일반 홈페이지(ex: cafe24)-URL직접 접근이 가능(보안 위험), MVC웹 프로그램-URL직접 접근이 불가능(관공서, 은행, 대학에서 주사용)
- MVC프로젝트에서도 resources폴더는 직접 접근이 가능.(run as)- resources 폴더 외에 다른 폴더는 Controller로 접근 가능.
- Controller클래스 + home/index.jsp 한쌍. (index.jsp에서 사용 가능)
- 안드로이드 앱 = 액티비티(java) + 레이아웃.xml(화면) 한쌍
- 개발 순서 : ERD제작 -> html제작 -> jsp제작
- admin 폴더 만든 이후 header, footer 분해 작업
- Junit(Java Unit Test) : 자바 단위 테스트(서비스 프로그램 만들기 전 프로토타입 제작) - CRUD 작업 후 본격작업 시작
- 로거 : debug > info > warn > error > fatal
- 개발할 때 : debug
- 납품할 때 : warn

- 패키지와 예외처리 실습
- 예외처리 : 에러 발생 시 프로그램이 중지되는 것을 방지해서 프로그램을 계속 사용할 수 있도록 처리
- Throwable : 오브젝트가 실행 시 에러가 발생하면, 예외내용을 보낼때 사용하는 클래스.
- 스프링에서는 예외정보를 스프링으로 보낸다.
- 자바앱에서는 예외정보를 개발자가 처리한다.



#### 20210601(화) 작업
- VS code에서 작업한 html을 이클립스에 배치
- resources 폴더에 static 컨텐츠(html, css, js, font) 배치
- 일괄 바꾸기 "/home -> "/resources/home
-         '/home -> '/resources/home

- 스프링이 관리하는 클래스(빈)의 종류 3가지 (개발자가 만든 클래스 상단에 입력)
- 1. @Controller 클래스 빈 : 라우터 역할 메서드를 만듬
- 2. @Service 클래스 빈 	: 비지니스로직 메서드를 만듬
- 3. @Repository 클래스 빈 : Model처리 메서드를 만듬
- 스프링 기능 = DI(객체생성-의존성주입), AOP, IoC(제어의 역전- 메모리 관리)
- 주석 > 디버그 > 변수 > 메서드 > 클래스 > MVC
- 디버그 방법 : 자바(System.out을 이용해서 콘솔창에 출력)
- 스프링에서는 logger를 사용해서 디버그 내용 출력

- Controller 클래스에서 생성한 변수 사용(자바) model객체 전송(스프링) -> 출력
- 출력하는 방식 : EL방식(${변수}), JSTL(*표준 - 출력, 반복, 조건 등 다양한 방식)

- 프로젝트의 버전을 업 -? 외부이브러리 부분의 버전을 올림.
- 메이븐 외부 라이브러리 https://mvnrepository.com/artifact/junit/junit

-에러 : 404(file not found 경로이상), 500(자바프로그램 에러)


#### 20210531(월) 작업
- 오라클 웹용 관리프로그램에서 XE라는 테이블스페이스를 XE사용자로 추가.
- 비밀번호는 apmsetup으로 통일
- EntityRelationDiagram(ERD-객체관계그림) : Entity = 테이블
- 데이터 모델  : Model Object를 형상화 시킨것을 모델이라고 함.(데이터 베이스)
- MVC 스프링 프로젝트에서 M이 Model 이고, 스프링 프로젝트 구성중에 DB를 가리킴.
- V는 View이고 jsp를  말한다. C는 Controller이고 클래스를 말한다.
- RDBMS : RelationDataBaseManagementSystem (관계형 데이터 베이스 관리 시스템)

- 테이블 구성 : 필드(컬럼, 열) = 테이블의 멤버변수(자바클래스의 멤버변수)
- 필드 구성 : PK(PrimaryKey) = 주키, 기본키, 고유키 = 테이블영역에서 고유한 ID를 말한다(중복되지 않는 값)
-          ㄴ> 로그인 ID, 이메일주소, 주민번호 등...
- 필드 데이트 타입 : timestamp(년월일시분초), Date(년월일까지)
- ERD에서 Relation 생성 : 게시판 타입 테이블(부)과 게시물 관리 테이블(자식)의 관계를 생성
- 부자의 관계는 부모의 PK를 기준, 자식에게는 FK(ForeignKey)
- BOARD 테이블과 REPLY테이블은 1:N 관계 = 게시물 1개에 댓글이 여러개 달릴 수 있음.

- 스프링 시큐리티 : 1. 로그인 인증(ENABLED) : AUTHENTICATION, 2. 권한 체크(LEVELS) : AUTHORITY (관리자-어드민 폴더 접근 가능)
- ERD를 물리 테이블로 변경 (forward engineering)
- 물리테이블을 ERD로 만드는 것 (reverse engineering)
- 데이터 딕셔너리를 모델과 동기화 : 자료사전을(데이터의 정보) DB테이블과 동기화
- 데이터 딕셔너리는 메타 데이터와 동일 : 컨텐츠는 없고, 구조, 구성 정보만 존재
- 토드(SQL디벨로퍼와 비슷한 상용)에서는 버튼으로 포워드 엔지니어링 처리 가능
- 무료인 SQL디벨로퍼에서는 버튼으로 포워드 엔지니어링이 처리가 불가하기 때문에, DDL을 실행해서 포워드 엔지니어링 처리 
- MYSQL은 무료이지만, 버튼으로 포워드 엔지니어링 처리 가능.


- SQL쿼리문 :
- DDL문(Data Definition Language)- 데이터 정의 언어(create table문 등),
- DCL문(Data Control Language) - 데이터 제어 언어(commit, rollback), 
- DML문(Data Manufacture Language)- 데이터 조작 언어(CRUD)


#### 20210528(금) 작업
- 자바앱에서는 객체를 생성 후 사용이 끝나면, 메모리에서 삭제하는 명령어 필수.
- 스프링에서는 내장된 가비지컬렉터(쓰레기수집가)가 자동실행 사용하지 않는 객체를 자동으로 지워줌.
- 위와 같이 개발자가 하던 메모리 관리를 스프링이 대신 = IoC(Inversion Of Control)

- SQL Developer 프로그램으로 ERD 다이어그램으로 프로젝트 구조제작
- EntityRelationDiagram : 테이블관계도형,
- ㄴ> 게시판테이블(부모) - 댓글 테이블(자식) - 첨부파일 테이블
- 프로젝트 진행 : 발주(클라이언트) -> 수주(개발사) -> 디자인UI(클라이언트와 개발사의 협의)
- -> ERD(설계) -> 코딩시작(UI + ERD)
- ERD에서 SQL쿼리가 생성 -> 물리테이블 생성.
- StructuredQueryLanguage: 구조화된 질의 언어(오라클 서버)
- QueryString : URL에서 전송하는 값(서버에 질의문)을 문장으로 저장하는 내용 ?로 시작

- extends 관계 클래스에서 this.(현재 클래스), super.(부모 클래스)
- 다형성? 메서드 @오버라이드(상속), 메서드 오버로드(동일 함수명의 파라미터의 개수, 종류 틀린 메서드) 구현.
- 오버라이드 : 상속 받아서 재정의 메서드 @오버라이드 = 다형성 구현


#### 20210527(목) 작업
- 인스턴스(클라우드 주로 사용) = 오브젝트(자바) = 객체 = 실행중인 클래스
- 클래스 자료형은 멤버변수, 멤버메서드, 서브클래스 등등 포함할 수 있다. = C언어 구조체
- 접근 제어자 : 
- public(패키지 위치에 상관없이 접근 - 제일 많이 사용)
- ㄴ> public 클래스 안에서 멤버변수는 private을 제일 많이 사용.(보안성)
- ㄴ> public 클래스 안에서 멤버 메서드는 public을 많이 사용.
- abstract클래스(추상클래스) : 구현내용 없이, 메서드명만 있는 클래스.
- ㄴ> 메서드명만 있기 때문에(구현내용x), 객체로 만들 수 없는 클래스.
- ㄴ> 부모로써 상속만 가능, 재사용 가능. 프로그램을 구조화 시킬 때 필요.

- 스프링에서는 클래스 extends 상속보다는 인터페이스 implements를 더 많이 사용한다.

- 이클립스에서 커밋 혹은 푸시가 안될 때, 푸시창에서
- Force overwrite branch in remote if it exists and has diverged를 체크해준다.


#### 20210526(수) 작업
- 변하지 않는 변수 = 상수 = PI = 3.141592
- 보통 상수는 클래스의 제일 상단에 위치
- 기본형(정수) : byte<short<int<long, 10L(롱타입변수)
- 기본형(실수) : float<double<long, 12.34f(실수형)
- 기본형(문자형 '') : char
- 문자형에서 유니코드 \u숫자 
- 기본형(불린형) : boolean

- 참조형(대문자로 시작) : String(문자열)

- 클래스 변수 : 클래스가 메모리에 올라갈 때,
- 인스턴스 변수 : 인스턴스가 생성될 때

- 상수를 만들 때 : final int MAX = 10;

- 추상화(Abstract) : 오프라인 업무 -> 대표업무만 뽑아낸 클래스 = 추상클래스

- 클래스는 멤버변수 + 멤버메서드(실행) + 서브클래스
- 객체와 클래스 : 클래스형 자료를 실행 가능하게 만든 것을 Object라고 한다.
- OOP(Object Oriented Programing) : 자바를 객체지향(클래스를 실행 가능하게 하는) 프로그래밍
- 붕어빵틀 : 붕어빵 => 붕어빵이 구어져 나오면 오브젝트(객체 = 인스턴스 = 실행 가능한 클래스)


#### 20210525(화) 작업
- 스프링MVC프로젝트 : ModelViewController (MVC구조 - 웹프로그램구조)
- src/test/java폴더 : 테스트 작업 폴더
- src/main/java폴더 : 소스 작업 폴더
- javac HelloWorld.java -encoding UTF-8 
- 자바 컴파일러로 실행한 결과 - HelloWorld.class 실행파일이 생성됨
- 주의점) 클래스 파일은 실행을 패키지 루트에서 해야한다.
- kr.or.test패키지 root폴더 src/test/java 폴더에서 실행
- -> java kr.or.test.HelloWorld 클래스를 실행
- 이클립스 나오기 25년전에는 javac 컴파일에서 직접 class프로그램을 만들었다.
- 지금은 이클립스에서 바로 실행.
- javac : 자바앱 컴파일러 -> 클래스 실행파일을 만듬.class (자바환경JVM에서 실행)
- 메이븐(Maven) : 웹프로그램 실행파일을 만듬.war (톰캣에서 실행)
- 메이븐이 컴파일할 때, 자바소스만 컴파일하는게 아니라 외부 라이브러리도 가져와서 바인딩해준다.
- 이것을 패키징 이라고 한다. = .war(와르)파일로 패키징된다.
- 메이븐이 관리하는 외부 라이브러리파일(lib) 목록을 저장하는 파일 - pom.xml
- pom.xml에서 자바버전을 1.6 -> 1.8로 변경 후 메이븐을 업데이트 합니다.
- 업데이트 한 후 maven-update 해줘야 한다. (offline체크해제)
- 외부 라이브러리 파일을 참조하는 방식을 Dependency
- jar파일 : JavaARchive = jar 자바클래스를 패키징한 파일


- 자바 기초 실습
- 자바스크립트는 함수기반의 프로그래밍
- 자바는 객체지향 프로그래밍. (object oriented program)
- 오브젝트(객체) = 실행 가능한 class(클래스)


- 아스키코드 IoT에서 데이터 전송시 필수로 확인해야 한다. 0을 전송 -> 48값을 받는다.
- IoT프로그램시 하드웨어 값을 주고 받을때 if(var1 == 48) {구현 내용}
- 공유기가 하위 가질 수 있는 사설 IP는 공유기 자기IP + 255(여유분) = 256개 인터넷이 가능.
- 영어 인코딩은 아스키코드로 다 표현가능
- 한글 인코딩, 중국어 / 일본어 인코딩 등등은 아스키코드로 다 표현 못해서 유니코드 등장
- Hex(16진수), Dec(10진수), Char(문자) = 127개 = 아스키 문자의 크기(컴-사람 1:1매칭)
- 아스키코드 - 7비트코드(127글자) -> ANSI코드 - 8비트(256글자) -> UniCode(65536글자)-UTF8
- Oct(8진수), Bit(2진수)
- UTF8mb4(이모지까지 포함 : 이모티콘 + 이미지)
- 자바언어를 한다는 것은 컴파일 후 실행. -> 실습 예정
- 자바스크립트(파이썬)는 그냥 실행해서 프로그램이 만들어진다.(인터프리터) -> 실습 예정



 