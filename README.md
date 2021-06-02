#### 작업 예정
- 스프링 프로젝트 순서
- 1. JUnit -> 마이바티스(DB핸들링) -> 페이징 기능 -> 검색 기능 -> 첨부파일 기능 -> 스프링 시큐리티(로그인 인증) 
- 2. 댓글 처리(RestAPI생성) -> 네이버 아이디 로그인(외부 API사용) -> 헤로쿠 클라우드 배포
- 3. 문서 작업(화면 기획서,화면 설계서)

#### 20210603(목) 작업
- JUnit 테스트 : 오라클 연동한 후 회원관리 CRUB 테스트 진행
- 오라클 실습 예정 : https://wikidocs.net/3910


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
- 클래스 변수, 인스턴스 변수 설명 = https://itmining.tistory.com/20
- 상수를 만들 때 : final int MAX = 10;

- 추상화(Abstract) : 오프라인 업무 -> 대표업무만 뽑아낸 클래스 = 추상클래스
- 추상화 설명 = https://limkydev.tistory.com/188 (사용법)
- https://victorydntmd.tistory.com/117
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

- 아스키, 유니코드 https://whatisthenext.tistory.com/103
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
- 인터프리터 https://colab.research.google.com/drive/1wyL8Fg3-l8PKpmqbBCxd_ewBGDKdTzuw


 