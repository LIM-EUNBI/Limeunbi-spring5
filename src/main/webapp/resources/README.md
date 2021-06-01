#### 작업예정
- UI디자인 끝
- UI구현 시작 -> 스프링 프로젝트 시작 (이클립스(전자정부표준프레임워크의 개발환경), 자바 + 오라클 + 스프링)
- UI구현 -> 위에서 제작한 UI디자인 이용해서 프로그램을 입힌다.
- 스프링 (MVC)웹 프로젝트 만들예정. 2달간 진행
- 위 스프링 프로젝트에서 자바 기초도 실습예정 -> htmlUI 만들것을 jsp 변경작업 


#### 작업 환경
- 전자정부 표준프레임워크 개발업체 : 삼성SDS, LG CNS, SK C&C
- egov: 자바 기반 => JDK(Java Delvelopement Kit 자바개바로한경)설치 확인
- JDK path 설정
- cmd에 java -version 
- 자바는 8버전부터 비용이 발생이 되어 오픈 JDK 사용
- 라이브서버(아파치) : VS Code에서 HTML 결과를 확인하는 라이브 서버.(localhost:5500)
- 톰캣(jsp 해석) : 이클립스에서 웹프로그램 결과를 확인하는 라이브 서버.(localhost:8080)
- ㄴ> 자바소스(.java(앱), .jsp(웹)) -> 컴파일(라인단위X-인터프리터X) -> 
class 파일(DB자료가 동적으로 입출력 됨) -> html 번역 (WAS-톰캣) -> 크롬(IE) 화면에 렌더링 결과

- netstat -a -o 사용중인 모든 포트 찾기
- netstat -nao | findstr 0000 특정 포트 찾기
- control + shift + Esc(작업 관리자) - 세부 정보에서 PID 번호로 확인

#### 부트스트랩
- 용어 : grid, modal, xs, sm, md, la, bs-부트스트랩, fa-폰트 어썸
- 폰트어썸(아이콘 웹폰트) : 아이콘을 확대해도 깨지지 않는다(백터방식).
- 부트스트랩 그리드 레이아웃 : 화면을 12개의 컬럼으로 분리해서 크기를 반응형으로 만듬.
- row(가로줄), col(세로칸), col-md-12(화면 가로크기를 12로 지정 - 100%)
- 반응형 화면크기 : xs(-750px)-엑스트라 스몰, sm(750px-)- 스몰, md(970px-)- 미디엄, lg(1170px-) -라지
- col-6(화면 가로크기를 50%로 지정)

- 팝업창 : 모달창(modal(필수창) : 작업 후 다음으로 가능), 모달리스창(modalless : 작업하고 상관없이 다음창으로 이동 가능)
- collapse : 부트스트랩4


#### 이클립스

#### 20210601(화) 작업
- ERD기준으로 게시판UI 마무리
- 이클립스로 작업한 html내용을 -> resource 폴더(admin, home, root파일)로 배치
- vs code -> 이클립스에서 작업 시작.
- 스프링 작업의 시작.

#### 20210531(월) 작업
- ERD기준으로 게시판UI 수정

#### 20210528(금) 작업
- 관리자단 admin/board_write.html, board_view.html, 댓글 (O)
- 대시보드 home.html (최근 회원가입 정보, 최근 게시물 정보)
- 관리자단 AdminLTE적용 - 스프링시간 | UI디자인 시간 선택 후 아래 작업진행 예정
- 이클립스로 작업한 html 내용을 -> resources 폴더(admin, home, root 파일까지)로 배치
- 스프링 작업의 시작

- 뒤로가기 허용하면 게시물 테러가 가능.
- 뒤로가기 허용하지 않게 페이지를 이동하는 방식(JS에서는 location.replace)
- 뒤로가기 허용하는 방식(JS에서는 location.href='')
- 데이터를 전송할 때 사용문장 = 쿼리스트링(QueryString) : ?키=값&키=값

#### 20210527(목) 작업
- 관리자단 AdminLTE적용.(회원가입CRUD(스프링), 게시판 생성관리(스프링), 게시판CRUD, 대시보드)
- 관리자단 기본 틀(템플릿) : 공통 UI부분 작업 (O)
- Ctrl + K + 0 : 코드 축소
- Ctrl + K + J : 코드 펼침
- board_list.html 작업 (O)


#### 20210526(수) 작업
- 반응형 서브페이지들(로그인, 회원가입, 마이페이지)

- 유효성 검사 : validation 입력값이 제대로 된 값인지 확인하는 과정
- HTML5부터는 유효성 검사가 내장되어 있어 JS로 빈칸인지 코딩할 필요가 없다.
- ㄴ> required, email, password, number(숫자체크)

- 검색엔진 최적화 : 의미있는 태그를 사용했는가? header, section, footer,article 등등 html5부터 만들어진 태그를 사용하면 구글검색에 노출이 잘된다.
- 기획 시, 메인1 CSS 네이밍, 서브1 CSS 네이밍 -> 기반으로 퍼블리셔, 프론트 개발자 CSS나 HTML작업 


#### 20210525(화) 작업
- board_view.htl 댓글 AdminLTE3이 페이징 UI 추가
- css : 계층 : 조부 > 부> 본인(timeline) > 자식(collapse) > 손자(time-label) > 증손자
- 이클립스 자바 패키지 만든 후 HelloJava 클래스 파일 생성


#### 20210524(월) 작업
- board_view.html 댓글 UI디자인(부트스트랩) 추가
- 반응형 서브페이지(로그인, 회원가입, 마이페이지)
- 이클립스 다이나믹 웹프로젝트(jsp만드는 방식) 만든 후 사용자단 UI실행하고 삭제
- history (jsp(1세대 2000년 게시판) -> 서블렛(2세대 2005년 servelet게시판) -> 스트러츠(3세대 2010년대 프레임워크) -> 스프링(4세대 2013년대 프레임워크))
- 시간 형식 : date(년월일), dateTime(년월일시분초), timestamp(년월일시분초)
- timestamp는 1970년 1월 1일 부터 현재까지의 초를 계산한 결과값



#### 20210521(금) 작업
- 모바일 게시판페이지(CRUD) CSS - Create(Update) = board_write.html
- 반응형 서브페이지(로그인, 회원가입, 마이페이지)

- 글쓰기(부트스트랩) : 첨부파일 부분, 내용입력 부분 웹 에디터 추가.
- 부트스트랩(adminLTE) : 제이쿼리 기반의 UI 템플릿(프레임워크)
- AdminLTE : dist(디스트리뷰트 - 배포), pages(더미데이터), plugins(서머노트 등..의 확장프로그램)
- 대시보드파일 : index.html, index2.html, index3.html
- board_write.html 파일에 bootstrap 코어 적용 + 서머노트 plugin 임포트
- board_view.html 댓글 UI디자인(부트스트랩) 추가
- 관리자단 AdminLTE 적용(회원관리CRUD, 게시판CRUD, 대시보드)
- 이클립스 헬로월드 실습 (다이나믹 웹프로젝트(jsp) 만든 후 사용자단 UI 실행 후 삭제)

#### 20210520(목) 작업
- 모바일, 태블릿, PC 게시판페이지 CSS (o)
- 모바일, 태블릿, PC 게시판 뷰 CSS (o)

- href(헤르프) : Hypertext reference (웹문서 참조)
- 정적(static) 콘텐츠 : HTML, CSS, js
- 동적(dynamic) 콘텐츠 : jsp(java 스프링), python(파이썬 장고), PHP, C#(닷넷), Nodejs(익스프레스)
- 바인딩 : 정적인 컨텐츠에 동적인 데이터를 넣어주는 것
- 게시물 타이틀 문자 넘치는 부분은 CSS 처리. (추후 jsp 프로그램으로 처리예정)
- jre : Java Runtime Environment(자바실행환경) - 실행할때만 사용.(따로 설정 안해줘도 된다.)
- jdk : 개발하고 실행할 때



#### 20210518(화) 작업
- jQuery 코어 임포트 후 사용자가 지정한 js파일 임포트 해야한다.
- CSS파일도 reset.css, mobile.css 임포트 이후에 사용자가 지정한 css파일 임포트 해야한다.

- 태블릿 메인 CSS 스타일 , PC용 메인 CSS 스타일 (o)
- 반응형 페이지의 핵심기술은 미디어쿼리 명령어 사용
- @media 미디어타입(screen, print 등등 = all) and (min-width: 801px) { 스타일 구현 내용 }
- PC용 대메뉴 롤오버 이벤트 처리 (o)

#### 20210517(월) 작업
- jQuery JSON 데이터 파싱 (o)
- 외부 data.js파일에서 json데이터를 저장한 후 html에서 불러와서 파싱 (o)
- 외부 사이트에서 제공하는(RestAPI서버) json데이터를 html에서 불러와서 파싱 (o)
- RestAPI서버 중 코로나19 확진자 데이터를 받아서 html에서 파싱
- 파싱 : 데이터를 분해해서 화면에 뿌려주는 작업
- RestAPI서버 주소(빅데이터) : https://coroname.me/getdata

- 메인페이지에 자바스크립트(jQuery) 적용. (o)
- 메뉴, 슬라이드 이미지 3개 처리 (o)
- 보통 이미지 슬라이드 처리는 외부 라이브러리 사용(니보 슬라이드, 캐로셀 슬라이드)
- 외부 라이브러리 사용 안하고 우리가 만들기 (o)

#### 20210514(금) 작업
- 사용자단 모바일 메인페이지 footer영역 CSS 입히기 (o)
- top 상단으로 이동처리 (o)
- 과제물 제출 (o)
- 메인페이지에서 자바스크립트(jQuery)적용 - 메뉴, 슬라이드 이미지 처리, top 상단 이동 (o)

#### 20210513(목) 작업내역
- 원격지원으로 팀뷰어에서 애니데스크로 바꿈
- 오늘부터는 모바일 메인 페이지 1개 만들어서 과제물로 제출
- -> 추후에 스프링에서 프로그램 입히는 소스로 사용하게 될 예정
- jQuery 코어 다운받기 : min버전(압축-속도빠름), 일반버전(개발)
- 작업폴더를 나누는 이유 : 시청, 관공서, 대학, 기업의 웹프로그램(사이트)를 제작할 때,
- 보통 1년간 무상 유지보수가 된다.
- 그 이후 유지보수 비용 보통 2천, 리뉴얼 4천만원의 비용이 책정된다.
- 리뉴얼할 때 덮어씌우는 방식이 아니라 home2022 즉, 새로운 폴더에 작업을 하게된다.

- 픽사베이에서 받은 이미지 3개 : 로고, 슬라이드 이미지, 이미지없음 이미지 총 3개
- 로고 : https://pixabay.com/ko/illustrations/%EB%A7%A4%EB%8B%AC%EB%A0%A4%EB%8A%94-%EA%B3%A0%EC%96%91%EC%9D%B4-%EB%82%99%EC%96%91-%EA%B3%A0%EC%96%91%EC%9D%B4-4794472/

- 슬라이드 커피 : https://pixabay.com/ko/photos/%EC%BB%A4%ED%94%BC-%EC%88%98%EC%B2%A9-%EB%82%98%EB%AC%B4-%EC%98%A4%EB%A0%8C%EC%A7%80-%EC%9D%BC-1276784/

- 슬라이드 3
- https://pixabay.com/ko/illustrations/%EC%BB%A4%ED%94%BC-%EC%BB%A4%ED%94%BC-%EC%BD%A9-%EC%BD%A9-%EC%BB%A4%ED%94%BC%EC%9E%94-1390800/

- 이미지없음 : 
https://pixabay.com/ko/vectors/%EC%B9%B4%EB%A9%94%EB%9D%BC-%EC%82%AC%EC%A7%84-%EC%9D%B4%EB%AF%B8%EC%A7%80-1085705/

- 슬라이드 여분
https://www.freepik.com/free-vector/watercolour-brushstrokes-background_13067993.htm#page=8&query=cute&position=21#position=21&page=8&query=cute

- 슬라이드 1 
https://www.freepik.com/free-photo/front-view-funny-cute-dog-concept_11524378.htm#page=11&query=cute&position=17#position=17&page=11&query=cute

- 슬라이드 4
https://www.freepik.com/free-vector/cartoon-hello-summer-illustration_13722519.htm#page=1&query=summer&position=15#position=15&page=1&query=summer

#### 20210512(수) 작업내역
- git clone으로 프로젝트를 가져온 경우 아래 내용을 추가해 줘야된다.
- git config --list 확인에서 user.name, user.email 없으면 추가해야된다.
- git config --local user.name
- git config --local user.email
- 프로젝트를 1명이 제작하는 경우가 없기 때문에, 2명이상 일때 소스 수정한 사람 확인용 정보

- html5.html, css.html, js.html (jQuery 기본구조만)까지 실습.
- jQuery 마무리 작업은 다음주에 실습할 예정

#### 20210511(화) 작업내역
- 서버(응답하는 프로그램 = response) = 아파치, 톰캣 서버
- 클라이언트(요청하는 프로그램 = request) = 웹 브라우저
- HTML은 마크업이 태그로 구성되어있다. <의미있는 태그>.. 등

- http://127.0.0.1:5500(포트8080|5050|6000)등...
- URL 경로(path) : /루트, /test/html5.html

- pc의 네트워크 내부주소(공통) : 127.0.0.1 == localhost
- 고유주소 : yahoo.com(도메인) == 74.6.143.25
- IP주소 버전 : IPv4, IPv6 

- HTML도 버전이 있다. : HTML5, HTML4.01(이전)

- lorem ipsum 영어 URL 주소 : https://loremipsum.io/
- lorem ipsum 한글 URL 주소 : http://guny.kr/stuff/klorem/#/table-html


#### 20210510(월) 작업내역
- 개발PC(html) 와 깃 저장소와 연결시킵니다. 초기에 연결시 아래와 같은 
  문제점이 나올 수 있습니다.

- 레포지토리(저장소) 초기화: git init 또는 VS code에서 레포지토리초기화 버튼 이후
- Git에서 'user.name' 및 'user.email'을 구성하라고 떠요!(해결책)
- git config --local user.name 이름
- git config --local user.email 이메일
- git git remote add origin https://github.com/사용자저장소/사용자저장소.git
- 작업결과는 .git 폴더안의 config 파일에 저장됩니다.

- 이후 VS code 프로그램에서 아래 처럼 작업 하시면 됩니다.

- 업로드절차: 1. 커밋(commit) 2. 푸시(push)
- 다운로드절차: 1. 풀(pull) : 교실에서 작업한 결과를 집에서 이어서 작업할 상황
- 주의) 다른 신규 PC에서 작업시 아래 명령어로 깃내용을 새로 가져옵니다.(아래)
- git clone https://github.com/학생저장소/학생저장소.github.io.git

- 안정된 이후 기존 사용자 폴더에서는 작업 시작전에
- git pull 로 어제 작업한 내용을 최신버전으로 업데이트 합니다.

- 포트의 역할이 트렌드로 많이 사용됩니다.
- 포트(port): 포트번호로 서비스를 만드는것이 트렌드
- 이전에는 80포트에 모든 서비스 묶어놓았습니다.
- 모든서비스를 개별로 분리하는 트렌드가 있습니다.: 마이크로서비스라고 합니다. == RestAPI로 구현이 됩니다.
- 도메인(예, https://naver.com:1451241/네이버 인증서비스 개발)
- 외부 인원(네이버직원아닌) 위 포트기준으로 제작한 서비스를 갖다가 사용

- html : Hyter Text MarkUp Language 태그를 사용하는 언어
- md : MarkDown Language 태그를 사용하지 않는 언어

