<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/header.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
    <!-- 메인콘텐츠영역 -->
    <div id="container">
		<!-- 메인상단위치표시영역 -->
		<%@ include file="./board_header.jsp" %>
		<!-- //메인상단위치표시영역 -->

		<!-- 메인본문영역 -->
		<div class="bodytext_area box_inner">
			<!-- 검색폼영역 -->
			<form id="search_form" name="search_form" action="/home/board/board_list" class="minisrch_form">
				<fieldset>
					<legend>검색</legend>
					<input value="${session_search_keyword}" name="search_keyword" type="text" class="tbox" title="검색어를 입력해주세요" placeholder="검색어를 입력해주세요">
					<button class="btn_srch">검색</button>
				</fieldset>
				<input type="hidden" name="search_type" value="all">
			</form>
			<!-- //검색폼영역 -->
			
			<!-- 게시물리스트영역 -->
			<table class="bbsListTbl" summary="번호,제목,조회수,작성일 등을 제공하는 표">
				<caption class="hdd">공지사항  목록</caption>
				<thead>
					<tr>
						<th scope="col">번호</th>
						<th scope="col">제목</th>
						<th scope="col">조회수</th>
						<th scope="col">작성일</th>
					</tr>
				</thead>
				<tbody>
				<c:forEach var="boardVO" items="${boardList}" varStatus="status">
					<tr>
						<!--  -->
						<td>
						${pageVO.totalCount - (pageVO.page*pageVO.queryPerPageNum)+pageVO.queryPerPageNum-status.index}
						</td>
						<td class="tit_notice"><a href="/home/board/board_view?bno=${boardVO.bno}&page=${pageVO.page}&search_type=${pageVO.search_type}">
							${boardVO.title }
						</a> </td>
						<td>${boardVO.view_count }</td>
						<td>
						<fmt:formatDate pattern="yyyy-MM-dd" value="${boardVO.reg_date }" />
						</td>
					</tr>
				</c:forEach>
				</tbody>
			</table>
			<!-- //게시물리스트영역 -->
			<style>
				.disabled {
					pointer-events:none;
					cursor:defalut;
					opacity:0.5;
				}
			</style>
			<!-- 페이징처리영역 -->
			<div class="pagination justify-content-center">
				<c:set var="disabled" value="${pageVO.prev?'':'disabled' }" />
				<!-- <a href="javascript:;" class="firstpage  pbtn"><img src="img/btn_firstpage.png" alt="첫 페이지로 이동"></a> -->
				<a href="/home/board/board_list?page=${pageVO.startPage-1}&search_type=${pageVO.search_type}" class="prevpage pbtn ${disabled }"><img src="/resources/home/img/btn_prevpage.png" alt="이전 페이지로 이동"></a>
				
				<c:forEach begin="${pageVO.startPage}" end="${pageVO.endPage}" step="1" var="index">
				<a href="/home/board/board_list?page=${index}&search_type=${pageVO.search_type}"><span class="pagenum ${index==pageVO.page?'currentpage':'' }">${index}</span></a>
				</c:forEach>
				
				<c:set var="disabled" value="${pageVO.next?'':'disabled' }" />
				<a href="href="/home/board/board_list?page="${pageVO.endPage+1}&search_type=${pageVO.search_type}" class="nextpage  pbtn ${disabled} }"><img src="/resources/home/img/btn_nextpage.png" alt="다음 페이지로 이동"></a>
				<!-- <a href="javascript:;" class="lastpage  pbtn"><img src="img/btn_lastpage.png" alt="마지막 페이지로 이동"></a> -->
			</div>
			<!-- //페이징처리영역 -->
			<p class="btn_line">
			<!-- 등록버튼은 로그인한 사용자만 보이도록 처리 -->
			<c:if test="${session_enabled }">
				<!-- 게시판이 공지사항일때는 관리자만 글쓰기 가능하게 처리 -->
				<c:choose>
					<c:when test="${session_levels eq 'ROLE_ADMIN' }">
						<a href="/home/board/board_insert_form" class="btn_baseColor">등록</a>
					</c:when>
					<c:otherwise>
						<c:if test="${session_board_type ne 'notice'}">
							<a href="/home/board/board_insert_form" class="btn_baseColor">등록</a>
						</c:if>
					</c:otherwise>
				</c:choose>
				
			</c:if>
			</p>
		</div>
		<!-- //메인본문영역 -->
	</div>
	<!-- //메이콘텐츠영역 -->

<%@ include file="../include/footer.jsp" %>