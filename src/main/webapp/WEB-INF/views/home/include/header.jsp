<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
$(document).ready(function() {
	// 메인 슬라이드 실행 부분을 분리
	// 함수 호출(실행)
	slideAuto = setTimeout("play_w('right')", 3000); // 3초 후에 play_w 실행
	var slidePlayHide = setTimeout(function() {
		$('.rollplay').css('display','none');
	}, 3000); // 3초 후에 rollplay클래스 플레이버튼 영역을 숨김

	$('.rollstop').click(function() {
		// this : 클릭한 본인 태그
		$(this).hide(); // 현재 stop버튼을 숨김
		$('.rollplay').css('display','inline-block');
		if(slideAuto) {clearTimeout(slideAuto)};
	});
	$('.rollplay a').click(function() {
		$(this).parent().hide();
		$('.rollstop').css('display','inline-block');
		play_w('right');
	});
	$('.rollingbtn li.seq a').each(function(index) {
		$(this).click(function() {
			$('.rollplay').hide();
			$('.rollstop').css('display','inline-block');
			if(slideAuto) {clearTimeout(slideAuto);}
			play_w(index);
		});
	});
});
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
					<li><a href="/login_form">로그인</a></li>
					<li><a href="/join_form">회원가입</a></li>
					<!-- 로그인 후 보이는 메뉴(아래) -->
					<li><a href="#">OOO님 환영합니다.</a></li>
					<li><a href="mypage.html">마이페이지</a></li>
					<li><a href="/admin">AdminLTE</a></li>
				</ul>	
				<nav>
				<ul class="gnb clear">
					<li><a href="board_list.html" class="openAll1">샘플홈페이지</a>

                        <div class="gnb_depth gnb_depth2_1">
                            <ul class="submenu_list">
                                <li><a href="board_list.html">반응형홈페이지</a></li>
                            </ul>
                        </div>
					</li>
					<li><a href="board_list.html" class="openAll2">커뮤니티</a>
				        <div class="gnb_depth gnb_depth2_2">
                            <ul class="submenu_list">
                                <li><a href="board_list.html">공지사항</a></li>
                                <li><a href="board_list.html">갤러리게시판</a></li>
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
	