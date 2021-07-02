<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/header.jsp" %>
<!-- html5가 아닌 JS로 유효성 검사 코어 임포트 -->
<script src="/resources/home/js/jquery.validate.js"></script>
<script src="/resources/home/js/additional-methods.js"></script>
<link rel="stylesheet" href="/resources/home/css/board.css">
<style>
/* 모바일용 마이페이지 스타일 */
.myinfo {
	width: 170px;
	height: 70px;
	line-height: 70px;
	text-align: center;
	font-size: 18px;
	background: #000;
	color: #fff;
}
.radio_area {
	padding-left: 5px;
    padding-top: 5px;
	padding: 10px;
	box-sizing: border-box;
}
.radio_area label {display: none;}
.gender {
	padding: 4px 10px;
    font-size: 14px;
}

/* // 모바일용 마이페이지 스타일 */
@media all and (min-width: 801px) {
}
/* PC용 메인페이지 스타일 지정 */
@media all and (min-width: 1066px) {
}
</style>
<script>
// 회원가입 유효성 검사 체크
$(document).ready(function() {
	/*$(".appForm").validate({
		rules: {
			password: "required",
			password_chk: {
				equalTo: "#password_lbl"
			}
		}
	}); */
	$.extend($.validator.messages, {
		required: "필수 항목 입니다.",
		email: "유효하지 않는 Email주소 입니다.",
		digits: "숫자만 입력 가능합니다.",
		equalTo: "비밀번호가 일치하지 않습니다."
	});
});
</script>

<!-- 메인콘텐츠영역 -->
<div id="container">
	<!-- 메인상단위치표시영역 -->
	<div class="location_area customer">
		<div class="box_inner">
			<h2 class="tit_page">스프링 <span class="in">in</span> 자바</h2>
			<p class="location">고객센터 <span class="path">/</span> 개인정보 수정</p>
			<ul class="page_menu clear">
				<li><a href="#" class="on">개인정보 수정</a></li>
			</ul>
		</div>
	</div>	
	<!-- //메인상단위치표시영역 -->

	<!-- 메인본문영역 -->
	<div class="bodytext_area box_inner">
		<div class="myinfo">내 정보</div>
		<!-- 폼영역 -->
		<form method="POST" name="join_form" action="/member/mypage" class="appForm">
			<fieldset>
				<legend>회원가입폼</legend>
				<p class="info_pilsoo pilsoo_item">필수입력</p>
				<ul class="app_list">
					<li class="clear">
						<label for="user_id" class="tit_lbl pilsoo_item">ID</label>
						<div class="app_content"><input value="${memberVO.user_id}" type="text" name="user_id" class="w100p" id="user_id" readonly ></div>
					</li>
					<li class="clear">
						<label for="password_lbl" class="tit_lbl pilsoo_item">비밀번호</label>
						<div class="app_content"><input type="password" name="user_pw" class="w100p" id="password_lbl" placeholder="비밀번호를 입력해주세요" /></div>
					</li>
					<li class="clear">
						<label for="user_name_lbl" class="tit_lbl pilsoo_item">이름</label>
						<div class="app_content"><input value="${memberVO.user_name }" type="text" name="user_name" class="w100p" id="user_name_lbl" placeholder="사용자 이름을 입력해주세요."/></div>
					</li>
					<li class="clear">
						<label for="email_lbl" class="tit_lbl pilsoo_item">이메일</label>
						<div class="app_content"><input value="${memberVO.email }" type="text" name="email" class="w100p" id="email_lbl" placeholder="이메일을 입력해주세요."/></div>
					</li>
					<li class="clear">
						<label for="point_lbl" class="tit_lbl pilsoo_item">포인트</label>
						<div class="app_content"><input value="${memberVO.point }" type="digits" name="point" class="w100p" id="point_lbl" readonly/></div>
					</li>
					<li class="clear">
						<label for="gender_lbl" class="tit_lbl pilsoo_item">로그인여부</label>
						<div class="app_content radio_area">
							<select name="enabled" class="gender" required>
								<option value="${memberVO.enabled}">${memberVO.enabled=='true'?'허용':'금지'}</option>
							</select>
						</div>
					</li>
					<li class="clear">
						<label for="gender_lbl" class="tit_lbl pilsoo_item">권한여부</label>
						<div class="app_content radio_area">
							<select name="levels" class="gender" required>
								<option value="${memberVO.levels}">${memberVO.levels}</option>
							</select>
						</div>
					<li class="clear">
						<label for="agree_lbl" class="tit_lbl pilsoo_item">개인정보활용동의</label>
						<div class="app_content checkbox_area"><input type="checkbox"" name="agree" class="css-checkbox" id="agree_lbl" required checked/>
						<label for="agree_lbl" class="agree">동의함</label>
						</div>
					</li>
				</ul>
				<p class="btn_line">
				<button type="submit" class="btn_baseColor" style="cursor:pointer;">정보수정</button>
				<button type="button" class="btn_baseColor" id="btn_leave" style="cursor:pointer;">회원탈퇴</button>

				</p>	
			</fieldset>
		</form>
		<!-- //폼영역 -->
	</div>
	<!-- //메인본문영역 -->
</div>
<!-- //메인콘텐츠영역 -->

<%@ include file="../include/footer.jsp" %>
<script>
$(document).ready(function() {
	$("#btn_leave").click(function() {
		if(confirm('정말로 탈퇴하시겠습니까?')){
		var form_leave = $("form[name='join_form']");
		$("option:eq(0)", "select[name='enabled']").val("false");
		//$("select[name='enabled']").html("<option value='false'>탈퇴</option>");
		// alert($("select[name='enabled']").val());
		form_leave.attr("action","/member/mypage_leave"); // 크롬 오동작으로 추가한 코드
		form_leave.submit();
		//location.replace('/logout');
		}
	});
});
</script>