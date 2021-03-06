<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="./include/header.jsp"%>
<script>
//로그인 실패 전용 메세지
if("${param.msg}" == "fail"){
	alert("로그인 실패입니다. 다시 입력해주세요.");
}
</script>
<link rel="stylesheet" href="/resources/home/css/board.css">
 <!-- 로그인 컨텐츠 -->
    <div id="container">
		<!-- 메인상단위치표시영역 -->
		<div class="location_area customer">
			<div class="box_inner">
				<h2 class="tit_page">스프링 <span class="in">in</span> 자바</h2>
				<p class="location">고객센터 <span class="path">/</span> 로그인</p>
				<ul class="page_menu clear">
					<li><a href="#" class="on">로그인</a></li>
				</ul>
			</div>
		</div>	
		<!-- //메인상단위치표시영역 -->

		<!-- 메인본문영역 -->
		<div class="bodytext_area box_inner">
			<!-- 폼영역 -->
			<form method="POST" name="login_form" action="/login" class="appForm">
				<fieldset>
					<legend>로그인폼</legend>
					<p class="info_pilsoo pilsoo_item">필수입력</p>
					<ul class="app_list">
						<li class="clear">
							<label for="id_lbl" class="tit_lbl pilsoo_item">아이디</label>
							<div class="app_content"><input type="text" name="user_id" class="w100p" id="id_lbl" placeholder="아이디를 입력해주세요" required autofocus/></div>
						</li>
						<li class="clear">
							<label for="password_lbl" class="tit_lbl pilsoo_item">암호</label>
							<div class="app_content"><input type="password" name="user_pw" class="w100p" id="password_lbl" placeholder="암호를 입력해주세요" required/></div>
						</li>

					</ul>
					<p class="btn_line">
					<button type="submit" class="btn_baseColor" >로그인</button>
					<button type="button" class="btn_baseColor" style="background-color:#19ce60;min-width:150px;" id="btn_naver_login">네이버 로그인</button>
					</p>	
				</fieldset>
			</form>
			<!-- //폼영역 -->
    </div>
<%@ include file="./include/footer.jsp"%>
<script type="text/javascript" src="https://static.nid.naver.com/js/naverLogin_implicit-1.0.2.js" charset="utf-8"></script>
<script>
$(document).ready(function() {
	$("#btn_naver_login").click(function() {
		location.replace("${url}"); //login컨트롤러에서 model로 받은 $url변수값이 필요
	});
});
</script>