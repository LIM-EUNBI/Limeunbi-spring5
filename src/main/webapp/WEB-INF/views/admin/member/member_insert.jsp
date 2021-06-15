<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="f" %>
<%@ include file="../include/header.jsp" %>
  

  <!-- Content Wrapper. Contains page content -->
  <div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <div class="content-header">
      <div class="container-fluid">
        <div class="row mb-2">
          <div class="col-sm-6">
            <h1 class="m-0">회원정보 등록</h1>
          </div><!-- /.col -->
          <div class="col-sm-6">
            <ol class="breadcrumb float-sm-right">
              <li class="breadcrumb-item"><a href="#">Home</a></li>
              <li class="breadcrumb-item active">관리자 관리</li>
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
              <h3 class="card-title">등록</h3>
            </div>
            <!-- /.card-header -->
            <!-- form start -->
            <!-- 첨부파일을 전송할때는 enctype 필수! 없으면 첨부파일 전송 X -->
            <form name="form_write" method="POST" action="/admin/member/member_insert" enctype="multipart/form-data">
              <div class="card-body">
                
                <div class="form-group">
                  <label for="user_id">회원ID  
                  <button class="btn btn-xs btn-secondary" type="button" id="btn_id_check">중복체크</button>
                  </label>
                  <input value="" name="user_id" type="text" class="form-control" id="user_id" placeholder="회원 ID를 입력해주세요." required>
                </div>
                  <div class="form-group">
                  <label for="user_pw">비밀번호</label>
                  <input value="" type="password" name="user_pw" id="user_pw" class="form-control" placeholder="회원 비밀번호를 입력해주세요." required>
                </div>
                <div class="form-group">
                  <label for="user_name">회원 이름</label>
                  <input value="" type="text" name="user_name" id="user_name" class="form-control" placeholder="회원 이름을 입력해주세요." required>
                </div>
                <div class="form-group">
                  <label for="email">이메일</label>
                  <input value="" name="email" type="email" class="form-control" id="email" placeholder="이메일을 입력해주세요." required>
                </div>
                <div class="form-group">
                  <label for="point">포인트</label>
                  <input value="0" type="number" name="point" id="point" class="form-control" placeholder="포인트를 입력해주세요." required>
                </div>
                <div class="form-group">
                  <label for="enabled">로그인 여부</label>
                  <select name="enabled" id="enabled" class="form-control"> <!-- label의 for와 바인딩되는게 id이기 때문에 id도 설정 -->
                    <option value="1" selected>허용</option>
                    <option value="0">금지</option>
                    </select>
                  </div>
                  <div class="form-group">
                  <label for="levels">권한</label>
                  <select name="levels" id="levels" class="form-control"> <!-- label의 for와 바인딩되는게 id이기 때문에 id도 설정 -->
                    <option value="ROLE_USER" selected>사용자</option>
                    <option value="ROLE_ADMIN">관리자</option>
                    </select>
                  </div>
              </div>
              <!-- /.card-body -->
              <div class="card-footer text-right">
                <button id="btn_insert" type="submit" class="btn btn-primary" disabled>등록</button>
                <button type="button" class="btn btn-secondary" id="btn_list">목록</button> 
              </div>
              <input name="page" type="hidden" value="${pageVO.page }">
              <input name="search_type" type="hidden" value="${pageVO.search_type }"> 
              <input name="search_keyword" type="hidden" value="${pageVO.search_keyword }">
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

<!-- jQuery사용 관리자단 (jQuery코어가 하단에 있기 때문에 footer보다 아래에 코드작성)-->
<script>
$(document).ready(function(){
	// RestAPI서버에서 ID중복체크
	$("#btn_id_check").click(function() {
		var user_id = $("#user_id").val();
		$.ajax({
			type:'get',
			url:"/id_check?user_id="+user_id,//RestAPI서버(스프링 클래스)
			dataType:'text', // 결과값을 받을 때, text, json, xml중 선택
			success:function(result) {
				if(result=="0"){ // 중복 아이디가 없다면 정상진행
					$("#btn_insert").attr("disabled", false);
					alert('사용 가능한 아이디 입니다.');
				}
				if(result=="1"){ // 중복 아이디가 있다면 진행중지
					$("#btn_insert").attr("disabled", true);
					alert('올바르지 않거나 중복된 ID가 존재합니다. 다시 입력해주세요.');
				}
			},
			error:function(){
				alert('RestAPI 서버가 작동하지 않습니다.');
			}
		});
	});
	
	$("#btn_list").click(function() {
		var form_write = $("form[name='form_write']");
		form_write.attr("action","/admin/member/member_list");
		form_write.attr("method", "get");
		form_write.submit();
	});
});

</script>
