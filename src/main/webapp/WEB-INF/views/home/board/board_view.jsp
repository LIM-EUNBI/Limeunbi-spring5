<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ include file="../include/header.jsp" %>
  <!-- 메인콘텐츠 영역 -->
    <div id="container">
		<!-- 메인상단위치표시영역 -->
		<%@ include file="./board_header.jsp" %>
		<!-- //메인상단위치표시영역 -->

		<!-- 메인본문영역 -->
		<div class="bodytext_area box_inner">			
			<ul class="bbsview_list">
				<li class="bbs_title">${boardVO.title }</li>
				<li class="bbs_date">작성일 : 
				<span><fmt:formatDate pattern="yyy-MM-dd hh:mm:ss" value="${boardVO.reg_date }" /></span>
				</li>
				<li class="bbs_hit">조회수 : <span>${boardVO.view_count}</span></li>
				<li class="bbs_content">
					<div class="editer_content">
					   ${boardVO.content }
                    </div>
				</li>
				<li class="bbs_title" style="height:inherit;">첨부파일:
					<c:forEach begin="0" end="1" var="idx">
						<c:if test="${boardVO.real_file_names[idx] != null}">
							<c:url var="url" value="/download">
								<c:param name="save_file_name" value="${boardVO.save_file_names[idx]}"/>
								<c:param name="real_file_name" value="${boardVO.real_file_names[idx]}"/>
							</c:url>
							<!-- c:url로 쿼리스트링을 처리하면 한글이 인코딩되어서 전송된다. -->
							<a href="${url}"> 다운로드 
							${boardVO.real_file_names[idx]}
							</a>
							<!-- 첨부파일 jpg, jpeg, gif, png, bmp라면 img태그를 사용해서 미리보기 기능 추가 -->
							 <c:set var="fileNameArray" value="${fn:split(boardVO.save_file_names[idx],'.')}" />
		            		 <c:set var="extName" value="${fileNameArray[fn:length(fileNameArray)-1]}" />
		            		 <c:choose>
		            		 	<c:when test="${fn:containsIgnoreCase(checkImgArray,extName)}">
		            		 	<img alt="다운로드 이미지" style="width:100%;display:block;" src="/image_preview?save_file_name=${boardVO.save_file_names[idx]}">
		            		 	</c:when>
		            		 </c:choose>			
						</c:if>
					</c:forEach>
				</li>
			</ul>
			<!-- 댓글 영역 -->
			<div class="row" style="margin: 0;">
				<div class="col-md-12">
					<!-- 댓글 입력폼 -->
					<div class="card-default" style="text-align: left;">
						<div class="card-header bg-olive">
						  <h3 class="card-title">댓글쓰기</h3>
						</div>
						<div class="card-body p-0">
						  <div class="bs-stepper linear">
							<div class="bs-stepper-header" role="tablist">
							  <!-- your steps here -->
							  <div class="line"></div>
							</div>
							<div class="bs-stepper-content">
							  <!-- your steps content here -->
							  <div id="logins-part" class="content active dstepper-block" role="tabpanel" aria-labelledby="logins-part-trigger">
								<div class="form-group">
								  <label for="writer">작성자</label>
								  <input type="text" class="form-control" id="writer" placeholder="이름 혹은 닉네임을 입력해주세요.">
								</div>
								<div class="form-group">
								  <label for="reply_text">댓글 내용</label>
								  <input type="text" class="form-control" id="reply_text" placeholder="댓글을 입력해주세요.">
								</div>
								<div>
									<button type="button" class="btn bg-gradient-secondary" id="btn_reply_write">댓글 등록</button>
									<hr>
								</div>
							  </div>
							</div>
						  </div>
						</div>
						<!-- /.card-body -->
					  </div>
					<!-- // 댓글 입력폼 -->
				</div>
				<div class="col-md-12">
				<!-- The time line -->
				<div class="timeline" >
					<!-- timeline time label -->
					<div class="time-label">
						<span class="" data-toggle="collapse" href="#collapseReply" role="button" id="btn_reply_list">댓글 리스트[<span>1</span>]</span>
					</div>
					<!-- 콜랩스 시작 -->
				<div class="collapse timeline" id="collapseReply">
			<!-- /.timeline-label -->
			<!-- timeline item -->
			<!-- 댓글리스트를 자바스크립트의 빵틀(템플릿) 만들기 -->
			<!-- 고전적인 append함수를 사용하지 않고 handlebars라는 확장 프로그램을 사용하여 만들기(퍼블리셔가 만든 기존태그를 사용하여 만들수 있음) -->
			<script src="https://cdnjs.cloudflare.com/ajax/libs/handlebars.js/3.0.1/handlebars.js"></script>
			<script id="template" type="text/x-handlebars-template">
			{{#each .}}
				<div class="div_template" data-rno="{{rno}}">
					<i class="fas fa-envelope bg-blue"></i>
					<div class="timeline-item">
						<h3 class="timeline-header">{{replyer}}</h3>
						<div class="timeline-body">{{reply_text}}</div>
						<div class="timeline-footer">
						<a class="btn btn-primary btn-sm" data-toggle="modal" data-target="#modal-reply" >수정</a>
						</div>
					</div>
				</div>
			{{/each}}
			</script>

				<!-- 페이징 처리 -->
				<div class="row" >
					<ul class="pagination" style="margin: 0 auto;">
						<!-- <li class="paginate_button page-item previous disabled" id="example2_previous">
							<a href="#" aria-controls="example2" data-dt-idx="0" tabindex="0" class="page-link">Previous</a>
						</li>
						<li class="paginate_button page-item active">
							<a href="#" aria-controls="example2" data-dt-idx="1" tabindex="0" class="page-link">1</a>
						</li>
						<li class="paginate_button page-item ">
							<a href="#" aria-controls="example2" data-dt-idx="2" tabindex="0" class="page-link">2</a>
						</li>
						<li class="paginate_button page-item ">
							<a href="#" aria-controls="example2" data-dt-idx="3" tabindex="0" class="page-link">3</a>
						</li>
						<li class="paginate_button page-item ">
							<a href="#" aria-controls="example2" data-dt-idx="4" tabindex="0" class="page-link">4</a>
						</li>
						<li class="paginate_button page-item ">
							<a href="#" aria-controls="example2" data-dt-idx="5" tabindex="0" class="page-link">5</a>
						</li>
						<li class="paginate_button page-item ">
							<a href="#" aria-controls="example2" data-dt-idx="6" tabindex="0" class="page-link">6</a>
						</li>
						<li class="paginate_button page-item next" id="example2_next">
							<a href="#" aria-controls="example2" data-dt-idx="7" tabindex="0" class="page-link">Next</a>
						</li> -->
					</ul>
					<!-- 페이징 처리 끝 -->
				</div>
				<!-- 콜랩스 끝 -->
			</div>
			<!-- timeline 끝 -->
		</div>
			<p class="btn_line txt_right">
				<button type="button" id="btn_list" class="btn_bbs">목록</button>
				<button type="button" id="btn_update" class="btn_bbs">수정</button>
				<button type="button" id="btn_delete" class="btn_bbs">삭제</button>
			</p>
			<form name="hide_form" method="POST" id="hide_form" action="">
				<input type="hidden" name="bno" value="${boardVO.bno}" >
				<input type="hidden" name="page" value="${pageVO.page}" >
			</form>
			<script>
				$(document).ready(function() {
					var form = $("#hide_form");
					$("#btn_list").click(function() {
						location.replace('/home/board/board_list?page=${pageVO.page }&search_type=${pageVO.search_type}');
					});
					$("#btn_update").click(function() {
						alert('준비중');
					});
					$("#btn_delete").click(function() {
						if(confirm('정말로 삭제하시겠습니까?')){
							form.attr("action", "/home/board/board_delete");
							form.submit();
						}
					});
				});
			</script>
		</div>
		<!-- //메인본문영역 -->
	</div>
	<!-- //메인콘텐츠 영역 -->
<%@ include file="../include/footer.jsp" %>