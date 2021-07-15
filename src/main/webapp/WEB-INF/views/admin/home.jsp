<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@include file="./include/header.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!-- --------- 이후는 메인 컨텐츠 부분 ----------- -->
  <!-- Content Wrapper. Contains page content -->
  <div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <div class="content-header">
      <div class="container-fluid">
        <div class="row mb-2">
          <div class="col-sm-6">
            <h1 class="m-0">관리</h1>
          </div><!-- /.col -->
          <div class="col-sm-6">
            <ol class="breadcrumb float-sm-right">
              <li class="breadcrumb-item"><a href="#">Home</a></li>
              <li class="breadcrumb-item active">Dashboard v1</li>
            </ol>
          </div><!-- /.col -->
        </div><!-- /.row -->
      </div><!-- /.container-fluid -->
    </div>
    <!-- /.content-header -->

    <!-- Main content -->
    <section class="content">
      <div class="container-fluid">
        <!-- 최근 등록한 회원 목록 -->
        <div class="card">
          <div class="card-header">
            <h3 class="card-title">최근 등록 회원</h3>

            <div class="card-tools">
              <button type="button" class="btn btn-tool" data-card-widget="collapse">
                <i class="fas fa-minus"></i>
              </button>
              <button type="button" class="btn btn-tool" data-card-widget="remove">
                <i class="fas fa-times"></i>
              </button>
            </div>
          </div>
          <!-- /.card-header -->
          <div class="card-body p-0" style="display: block;">
            <ul class="users-list clearfix">
            <!--최신 등록한 회원정보 4명씩 출력-->
            <c:forEach var="memberVO" items="${latestMembers}" >
              <li style="cursor: pointer;" onclick="location.replace('/admin/member/member_view?user_id=${memberVO.user_id}&page=1')">
                <img style="width:120px; height:120px;" onerror="this.src='/resources/admin/dist/img/default-150x150.png'" src="/resources/profile/${memberVO.user_id}.png" alt="User Image">
                <a class="users-list-name" href="#">${memberVO.user_name}</a>
                <span class="users-list-date">
                	<fmt:formatDate pattern="yyyy-MM-dd hh:mm:ss" value="${memberVO.reg_date }"/>
                </span>
              </li>
            </c:forEach>
            </ul>
            <!-- /.users-list -->
          </div>
          <!-- /.card-body -->
          <div class="card-footer text-center" style="display: block;">
            <a href="/admin/member/member_list">View All Users</a>
          </div>
          <!-- /.card-footer -->
        </div>
        <!-- // 최근 등록한 회원 목록 -->
        <!-- 최근 등록된 게시물 -->
        <c:forEach var="boardTypeVO" items="${listBoardTypeVO}">
 			<c:import url="/admin/latest/latest_board?board_type=${boardTypeVO.board_type}&board_name=${boardTypeVO.board_name }" />
 		</c:forEach>
        <!-- // 최근 등록된 게시물 -->
      </div><!-- /.container-fluid -->
    </section>
    <!-- /.content -->
  </div>
  <!-- /.content-wrapper -->
<!--  -------  여기까지 메인 컨텐츠 부분 ---------- -->  
  
 <%@include file="./include/footer.jsp" %>
