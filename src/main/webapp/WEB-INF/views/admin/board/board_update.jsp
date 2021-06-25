<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/header.jsp" %>

 <!-- Content Wrapper. Contains page content -->
  <div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <div class="content-header">
      <div class="container-fluid">
        <div class="row mb-2">
          <div class="col-sm-6">
            <h1 class="m-0">${boardVO.board_type} 수정</h1>
          </div><!-- /.col -->
          <div class="col-sm-6">
            <ol class="breadcrumb float-sm-right">
              <li class="breadcrumb-item"><a href="#">Home</a></li>
              <li class="breadcrumb-item active">${boardVO.board_type }</li>
            </ol>
          </div><!-- /.col -->
        </div><!-- /.row -->
      </div><!-- /.container-fluid -->
    </div>
    <!-- /.content-header -->

    <!-- Main content -->
    <section class="content">
      <div class="container-fluid">
          <!-- 글쓰기 폼 -->
          <div class="card card-primary">
            <div class="card-header">
              <h3 class="card-title">글쓰기</h3>
            </div>
            <!-- /.card-header -->
            <!-- form start -->
            <!-- 첨부파일을 전송할때는 enctype 필수! 없으면 첨부파일 전송 X -->
            <form name="form_write" method="post" action="/admin/board/board_update" enctype="multipart/form-data">
              <div class="card-body">
                <div class="form-group">
                  <label for="board_type">게시판 타입</label>
                  <select name="board_type" class="form-control">
                    <c:forEach var="boardTypeVO" items="${listBoardTypeVO }">
                    <option ${boardVO.board_type==boardTypeVO.board_type?'selected':'' } value="${boardTypeVO.board_type}">${boardTypeVO.board_name }</option>
                    </c:forEach>
                    </select>
                  </div>
                    <div class="form-group">
                <label for="title">글제목</label>
                <input value="${boardVO.title}" name="title" type="text" class="form-control" id="title" placeholder="제목을 입력해 주세요" required>
              </div>
              <div class="form-group">
                <label for="content">글내용</label>
                <textarea name="content" id="content" class="form-control" placeholder="내용을 입력해주세요.">${boardVO.content}</textarea>
              </div>
              <div class="form-group">
                <label for="writer">작성자</label>
                <input value="${boardVO.writer}" name="writer" type="text" class="form-control" id="writer" placeholder="작성자를 입력해 주세요" required>
              </div>
              <div class="form-group">
                <label for="exampleInputFile">첨부파일</label>
                <c:forEach var="idx" begin="0" end="1">
                <div class="input-group div_file_delete">
                <!-- 위 div_file_delete 영역이름은 첨부파일을 개별 Ajax 삭제할때 필요 -->
                  <div class="custom-file">
                    <input name="file" type="file" class="custom-file-input" id="file_${idx}"><!-- id는 식별자0,1,2,3... -->
                    <label class="custom-file-label" for="file_${idx}">파일선택</label>
                  </div>
                  <!-- 기존 업로드된 파일을 수정폼에 보여주기, 삭제버튼 필요(아래) -->
                  <c:if test="${boardVO.save_file_names[idx] != null}">
                  	<p class="text-muted">
                  	 <c:url value="/download" var="url">
	                    	<c:param name="save_file_name" value="${boardVO.save_file_names[idx]}" />
	                    	<c:param name="real_file_name" value="${boardVO.real_file_names[idx]}" />
	                    </c:url>
	                    <a href="${url }" >
	                   <%--  <a href='<c:url value="/download?save_file_name=&real_file_name=" />'> --%>
	                    ${boardVO.real_file_names[idx]}
	                    </a>
                  	<%-- <a href="/download?save_file_name=${boardVO.save_file_names[idx]}&real_file_name=${boardVO.real_file_names[idx]}">
                  	${boardVO.real_file_names[idx]}
                  	</a> --%>
                  	&nbsp;<button type="button" class="btn btn-info btn_file_delete">삭제</button>
                  	<input type="hidden" name="save_file_name" value="${boardVO.save_file_names[idx]}">
                  	</p>
                  </c:if>
                </div>
                <div class="mb-2"></div>
                
                </c:forEach>
                </div>
              </div>
              <!-- /.card-body -->
              <div class="card-footer text-right">
                <button type="submit" class="btn btn-primary">수정</button>
                <a href="/admin/board/board_view?bno=${boardVO.bno}&page=${pageVO.page}&search_type=${pageVO.search_type}" class="btn btn-secondary">이전</a>
              </div>
              <input name="page" value="${pageVO.page}" type="hidden">
            <input name="search_type" value="${pageVO.search_type}" type="hidden">
            <%--세션으로 구현 <input name="search_keyword" value="${pageVO.search_keyword}" type="hidden"> --%>
            <input name="bno" value="${boardVO.bno}" type="hidden">
            </form>
          </div>
          <!-- //글쓰기 폼 -->
        <!-- //콘텐츠 내용 -->
      </div><!-- /.container-fluid -->
    </section>
    <!-- /.content -->
  </div>
  <!-- /.content-wrapper -->

<%@ include file="../include/footer.jsp" %>
<!-- 첨부파일 부트스트랩 js 코어 -->
<script src="/resources/admin/plugins/bs-custom-file-input/bs-custom-file-input.min.js"></script>    
<script>
  $(document).ready(function() {
      bsCustomFileInput.init();
  });
</script>
<!-- 서머노트 웹 에디터 실행 -->
<!-- 파일 삭제버튼 -->
<script>
$(document).ready(function() {
	$('.btn_file_delete').click(function() {
	if(confirm('선택한 첨부파일을 삭제하시겠습니까?')){
		var click_element = $(this); //현재 클릭한 객체
		var save_file_name = click_element.parent().find('input[name=save_file_name]').val();
		$.ajax({
			type:'post',
			url:'/file_delete?save_file_name='+save_file_name,
			dataType:'text', // 반환받는 데이터 형식
			success:function(result) {
				if(result=="success") {
					click_element.parents(".div_file_delete").remove();
				}
			}, 
			error:function(){
				alert('RestAPI서버가 작동하지 않습니다.');
			}
		});
	}
	});
});
</script>
<script>
  $(document).ready(function() {
      // $('#content_lbl').summernote({  }); 기본실행. 기본실행을 개발자가 커스터마이징 
      $('#content').summernote({
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
      $("form[name='form_write']").on('submit',function(e) {
    	  if($('#content').summernote('isEmpty')){
    		  alert('내용을 입력해주세요.');
    		  e.preventDefault(); // submit이 되지않게 해당코드 추가
    	  }
      });
  });
</script>