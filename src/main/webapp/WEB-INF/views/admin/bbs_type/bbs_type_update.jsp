<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="../include/header.jsp" %>

<!-- Content Wrapper. Contains page content -->
  <div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <div class="content-header">
      <div class="container-fluid">
        <div class="row mb-2">
          <div class="col-sm-6">
            <h1 class="m-0">게시판 생성 / 관리</h1>
          </div><!-- /.col -->
          <div class="col-sm-6">
            <ol class="breadcrumb float-sm-right">
              <li class="breadcrumb-item"><a href="#">Home</a></li>
              <li class="breadcrumb-item active">{게시판변수명}</li>
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
              <h3 class="card-title">등록/수정</h3>
            </div>
            <!-- /.card-header -->
            <!-- form start -->
            <!-- 첨부파일을 전송할때는 enctype 필수! 없으면 첨부파일 전송 X -->
            <form name="form_write" action="board_write.html" enctype="multipart/form-data">
              <div class="card-body">
                <div class="form-group">
                  <label for="board_type">게시판타입</label>
                  <input value="${boardTypeVO.board_type }" name="board_type" type="text" class="form-control" id="board_type" placeholder="게시판 타입을 입력해주세요." required>
                </div>
                <div class="form-group">
                  <label for="board_name">게시판 이름</label>
                  <input value="${boardTypeVO.board_name }" name="board_name" type="text" id="board_name" class="form-control" placeholder="게시판명을 입력해주세요." required>
                </div>
                <div class="form-group">
                  <label for="board_sun">출력 순서</label>
                  <input value="${boardTypeVO.board_sun }" name="board_sun" type="text" class="form-control" id="board_sun" placeholder="출력 순서를 입력해주세요." required>
                </div>
              </div>
              <!-- /.card-body -->
              <div class="card-footer text-right">
                <button type="submit" class="btn btn-primary">등록</button>
                <a href="board_list.html" class="btn btn-secondary">목록</a>
              </div>
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