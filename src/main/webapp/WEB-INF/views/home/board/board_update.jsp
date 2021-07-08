<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/header.jsp" %>
<!-- 부트스트랩 css 코어 임포트 -->
<link rel="stylesheet" href="/resources/admin/plugins/tempusdominus-bootstrap-4/css/tempusdominus-bootstrap-4.min.css">
<!-- 부트스트랩 js 코어 임포트 -->
<script src="/resources/admin/plugins/bootstrap/js/bootstrap.bundle.min.js"></script>
<!-- AdminLTE 전용 css 임포트 -->
<link rel="stylesheet" href="/resources/admin/dist/css/adminlte.min.css">
<!-- 첨부파일 부트스트랩 js 코어 -->
<script src="/resources/admin/plugins/bs-custom-file-input/bs-custom-file-input.min.js"></script>    
<!-- 서머노트 웹 에디터 임포트 css, js-->
<link rel="stylesheet" href="/resources/admin/plugins/summernote/summernote-bs4.min.css">
<link rel="stylesheet" href="/resources/admin/plugins/summernote/summernote.css">  
<script src="/resources/admin/plugins/summernote/summernote.js"></script>
<!-- 서머노트 웹 에디터 실행 -->
<script>
    $(document).ready(function() {
        // $('#content_lbl').summernote({  }); 기본실행. 기본실행을 개발자가 커스터마이징 
        $('#content_lbl').summernote({
            height: 200,
            lang: 'ko-kR',
            placeholder: '글 내용을 입력해 주세요.',            
            toolbar: [
                ['fontname', ['fontname']],
                ['fontsize', ['fontsize']],
                ['style',['bold','italic','underline']],
                ['color',['forecolor','color']],
                ['table',['table']],
                ['para',['ul','ol','paragraph']],
                ['height',['height']],
                ['insert',['link','video']],
                ['view', ['fullscreen','help']]
            ],
            fontNames: ['Arial', 'Arial Black', 'Nanum Gothic', '맑은 고딕', '궁서', '굴림', '1훈떡볶이', 'Noto Sans KR'],
            fontSizes: ['8', '10', '12', '14', '16', '18', '20', '22', '24', '26', '28', '30'],
            fontNamesIgnoreCheck: ['Noto Sans KR']
        });
    });
</script>
<!-- 첨부파일 확장프로그램 실행 -->
<script>
    $(document).ready(function() {
        bsCustomFileInput.init();
    });
</script>
 <!-- 메인콘텐츠 영역 -->
    <div id="container">
		<!-- 메인상단위치표시영역 -->
		<%@ include file="./board_header.jsp" %>
		<!-- //메인상단위치표시영역 -->

		<!-- 메인본문영역 -->
		<div class="bodytext_area box_inner">
			<!-- 폼영역 -->
			<form method="POST" name="board_update" action="/home/board/board_update" class="appForm" enctype="multipart/form-data">
				<fieldset>
					<legend>상담문의 입력 양식</legend>
					<p class="info_pilsoo pilsoo_item">필수입력</p>
					<ul class="app_list">
						<li class="clear">
							<label for="title_lbl" class="tit_lbl pilsoo_item">제목</label>
							<div class="app_content"><input value="${boardVO.title }" type="text" name="title" class="w100p" id="title_lbl" placeholder="제목을 입력해주세요" required/></div>
						</li>
						<li class="clear">
							<label for="content_lbl" class="tit_lbl pilsoo_item">내용</label>
							<div class="app_content">
								<textarea name="content" id="content_lbl" class="w100p" placeholder="내용을 입력해주세요." required>${boardVO.content }</textarea></div>
						</li>
						<li class="clear">
							<label for="name_lbl" class="tit_lbl pilsoo_item">작성자명</label>
							<div class="app_content"><input readonly value="${boardVO.writer}" type="text" name="writer" class="w100p" id="name_lbl" placeholder="이름을 입력해주세요" required/></div>
						</li>
						<li class="clear">
						<label for="file_lbl" class="tit_lbl">첨부파일</label>
						<c:forEach begin="0" end="1" step="1" var="idx">
						<div class="custom-file" style="width:96%;margin:0 2%;">
							<input type="file" name="file" class="custom-file-input" id="customFile_${idx}">
							<label class="custom-file-label" for="customFile" style="color:#999;">파일첨부${idx}</label>
							<div class="text-right">
							<c:if test="${!empty boardVO.save_file_names[idx]}">
								<c:url var="url" value="/download">
			            			<c:param name="save_file_name" value="${boardVO.save_file_names[idx]}" />
			            			<c:param name="real_file_name" value="${boardVO.real_file_names[idx]}"></c:param>
			            		</c:url>
			            		<!-- 위 처럼 c:url로 쿼리스트링을 처리하면 한글이 인코딩되어서 전송됨 -->
			            		<input type="hidden" name="delete_file_name" value="${boardVO.save_file_names[idx]}">
								<a href="${url}">${boardVO.real_file_names[idx]}</a>
								<button type="button" class="btn btn-info btn_file_delete">삭제</button>
							</c:if>
							</div>
						</div>
						<br><br>				
						<div style="height:10px;"></div>
						</c:forEach>					
					</li>
						<script>
					$(document).ready(function(){
						$(".btn_file_delete").click(function(){
							//alert("삭제버튼 확인");화면이 바뀌지 않고, 현재 선택한 파일만 삭제하는 경우
							var this_btn = $(this);//2개이상인 버튼에서 선택한 버튼을 구별하는 용도 this를 사용
							var delete_file_name = this_btn.parent().find("input[name='delete_file_name']").val();
							if(delete_file_name == "") {//!= 테스트OK -> == 변경
								alert("선택한 파일이 없습니다.");
								return true; 
							}//아래 내용이 진행 않되고 종료함.
							$.ajax({
								url:"/file_delete?save_file_name="+delete_file_name,
								type:"post",
								dataType:"text",//결과를 받는 데이터형태
								success:function(result){
									if(result=="success"){
										alert("선택하신 첨부파일이 삭제 되었습니다");
										this_btn.parents(".custom-file").remove();
									}
								},
								error:function() {
									alert("RestAPI서버가 작동하지 않습니다.");
								}
							});
						});
					});
					</script>
					</ul>
					<p class="btn_line">
					<button type="submit" class="btn_baseColor">등록</button>
					<a href="/home/board/board_list" class="btn_baseColor">목록</a>
					</p>	
				</fieldset>
				<input name="board_type" value="${session_board_type}" type="hidden">
				<input name="page" value="${pageVO.page}" type="hidden">
				<input name="bno" value="${boardVO.bno }" type="hidden">
			</form>
			<!-- //폼영역 -->
		</div>
		<!-- //메인본문영역 -->
	</div>
	<!-- //메인콘텐츠 영역 -->


<%@ include file="../include/footer.jsp" %>