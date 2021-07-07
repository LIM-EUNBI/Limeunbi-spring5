<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- 폰트어썸 임포트 -->
<link rel="stylesheet" href="/resources/admin/plugins/fontawesome-free/css/all.min.css">
<!-- 부트스트랩 css 코어 임포트 -->
<link rel="stylesheet" href="/resources/admin/plugins/tempusdominus-bootstrap-4/css/tempusdominus-bootstrap-4.min.css">
<!-- 부트스트랩 js 코어 임포트 -->
<script src="/resources/admin/plugins/bootstrap/js/bootstrap.bundle.min.js"></script>
<!-- AdminLTE 전용 css 임포트 -->
<link rel="stylesheet" href="/resources/admin/dist/css/adminlte.min.css">
<style>
.timeline, .card-default {
	text-align: left;
	margin: 0px 10px;
	width: 95%;
}
.bs-stepper-content {
	padding: 20px 10px;
}
</style>
<!-- 메인상단위치표시영역 -->
<div class="location_area customer">
	<div class="box_inner">
		<h2 class="tit_page">스프링 <span class="in">in</span> 자바</h2>
		<p class="location">고객센터 <span class="path">/</span> 공지사항</p>
		<ul class="page_menu clear">
			<c:forEach var="boardTypeVO" items="${listBoardTypeVO}">
			<li><a href="/home/board/board_list?board_type=${boardTypeVO.board_type}&search_keyword=" class="${boardTypeVO.board_type==session_board_type?'on':'' }">${boardTypeVO.board_name }</a></li>
			</c:forEach>
		</ul>
	</div>
</div>	
<!-- //메인상단위치표시영역 -->