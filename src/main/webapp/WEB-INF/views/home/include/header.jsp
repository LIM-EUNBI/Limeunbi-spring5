<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="ko">
<head>
<title> 메인화면 </title>
<meta name="viewport" content="width=device-width, initial-scale=1, minimum-scale=1, maximum-scale=1">
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<!-- jQuery코어 임포트 -->
<script src="/resources/home/js/jquery-3.6.0.js"></script>
<!-- 상단 바로가기 링크시 부드럽게 상단으로 이동하는 js 임포트 -->
<script src="/resources/home/js/jquery.smooth-scroll.min.js"></script>
<!-- 화면을 초기화시키는 스타일 임포트 : 크로스브라우징을 처리하기 위해서-->
<!-- 크롬, 익스플로러, 엣지, 사파리, 파이어폭스 h1, p, ul, div 태그 크기가
     조금씩 다 다르다. 작업한 결과가 모든 브라우저에서 똑같이 보이게 하기 위해서 reset.css을 임포트 -->
<link rel="stylesheet" href="/resources/home/css/reset.css">

<!-- 사용자 정의형 css, script 추가 -->
<link rel="stylesheet" href="/resources/home/css/mobile.css">
<link rel="stylesheet" href="/resources/home/css/tablet.css">
<link rel="stylesheet" href="/resources/home/css/board.css">
<link rel="stylesheet" href="/resources/home/css/pc.css">
<script src="/resources/home/js/main.js"></script>
<script src="/resources/home/js/slidemain.js"></script> <!-- 메인 슬라이드 코어 임포트 -->
<style>
@media all and (min-width: 801px) {

}
/* PC용 메인페이지 스타일 지정 */
@media all and (min-width: 1066px) {
}
</style>
<script>
//공통으로 사용하는 변수 : 로그인 성공, 게시물 등록/수정/삭제 성공메세지
if("${msg}" != ""){
	alert("${msg} 되었습니다.");
}
</script>

</head>
<body>
<!-- 헤더에서 푸터까지 -->
<div id="wrap">
	<!-- 헤더상단메뉴영역영역 -->
	<header id="header">
		<div class="header_area box_inner clear">
			<!-- 상단로고영역 --> 
			<h1><a href="/">스프링 in 자바</a></h1>
			<!-- //상단로고영역 -->
			
			<!-- 상단메뉴메뉴영역 -->
			<p class="openMOgnb">
                <a href="#"><b class="hdd">메뉴열기</b> 
                <span></span><span></span><span></span></a>
            </p>
			<div class="header_cont">
				<ul class="util clear">
				<c:choose>
					<c:when test="${session_enabled eq 'true' }">
					<!-- 로그인 후 보이는 메뉴(아래) -->
					<li style="color:#fff;font-size: 14px;">${session_username}님 환영합니다.</li>
					<li><a href="/member/mypage_form">마이페이지</a></li>
					<li><a href="/logout">로그아웃</a></li>
					<c:if test="${session_levels eq 'ROLE_ADMIN' }">
					<li><a href="/admin">AdminLTE</a></li>
					</c:if>
					</c:when>
				<c:otherwise>
					<li><a href="/login_form">로그인</a></li>
					<li><a href="/join_form">회원가입</a></li>
				</c:otherwise>
				</c:choose>
				</ul>	
				<nav>
				<ul class="gnb clear">
					<li><a href="/resources/home/index.html" target="_blank" class="openAll1">샘플홈페이지</a>

                        <div class="gnb_depth gnb_depth2_1">
                            <ul class="submenu_list">
                                <li><a href="/resources/home/index.html" target="_blank">반응형홈페이지</a></li>
                            </ul>
                        </div>
					</li>
					<li><a href="/home/board/board_list?board_type=notice&search_keyword=" class="openAll2">커뮤니티</a>
				        <div class="gnb_depth gnb_depth2_2">
                            <ul class="submenu_list">
                            	<c:forEach var="boardTypeVO" items="${listBoardTypeVO }">
                                <li><a href="/home/board/board_list?board_type=${boardTypeVO.board_type }&search_keyword=">${boardTypeVO.board_name }</a></li>
                                </c:forEach>
                            </ul>
                        </div>
					</li>
				</ul>
                </nav>
				<p class="closePop"><a href="javascript:;">닫기</a></p>
			</div>
			<!-- //상단메뉴메뉴영역 -->
		</div>
	</header>
	<!-- //헤더상단메뉴영역영역 -->
	