<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/header.jsp" %>
<style>
.tit_page a::before {
	content:'|';
	margin-right: 10px;
} 
</style>
	<!-- 메인 컨텐츠 영역 -->
   <div id="container">
		<!-- 메인상단위치표시영역 -->
		<div class="location_area customer">
			<div class="box_inner">
				<h2 class="tit_page">페이지 오류 <a href="${session_prevPage}">이전 페이지로 이동하기</a></h2>
				<p class="location">고객센터 <span class="path">/</span> 에러 페이지</p>
				<ul class="page_menu clear">
						<li style="text-align:left">
						${exception.getMessage() }
						</li>
				</ul>
			</div>
		</div>	
		<!-- //메인상단위치표시영역 -->

		<!-- 메인 본문 영역 -->
		<div class="bodytext_area box_inner" >
		<ul>
			<c:forEach var="stack" items="${exception.getStackTrace()}">
				<li style="text-align:left; padding:10px 40px;">
					${stack.toString() }
				</li>
			</c:forEach>
			</ul>
		</div>
		<!-- //메인 본문 영역 -->
	</div>
	<!-- //메인 컨텐츠 영역 -->


<%@ include file="../include/footer.jsp" %>
