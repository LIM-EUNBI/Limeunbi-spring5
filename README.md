#### 20210526(수) 작업



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


 