<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/header.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!-- Content Wrapper. Contains page content -->
  <div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <div class="content-header">
      <div class="container-fluid">
        <div class="row mb-2">
          <div class="col-sm-6">
            <h1 class="m-0">${pageVO.board_type } 리스트</h1>
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
        <!-- 콘텐츠 내용 -->
        <div class="card">
          <div class="card-header">
            <h3 class="card-title" style="padding-top: 5px;">목록</h3>

            <div class="card-tools">
                <!-- 내용검색 폼-->
              <div class="input-group input-group-md">
                  <form name="form_search" action="/admin/board/board_list" method="get" class="form-horizontal">
                    <select name="search_type" class="form-control float-left" style="width: inherit;">
                        <option value="all">전체</option>
                        <option value="title">제목</option>
                        <option value="content">내용</option>
                        <input type="text" name="search_keyword" class="form-control float-left" placeholder="Search" style="width: inherit;">
                        <div class="input-group-append float-left" style="width: inherit;">
                        <button type="submit" class="btn btn-default">
                            <i class="fas fa-search"></i>
                        </button>
                    </select>
                </div>
                </form>
              </div>
             <!-- // 내용검색 폼 -->
            </div>
          </div>
          <!-- /.card-header -->
          <div class="card-body table-responsive p-0">
            <table class="table table-hover">
              <!-- 줄바꿈않할때 다음 클래스추가 text-nowrap  -->
              <thead>
                <!-- 아래 링크주소에 jsp에서 프로그램 처리 예정 -->
                <tr>
                  <th class="text-center">BNO</th>
                  <th class="text-center">BOARD_TYPE</th>
                  <th class="text-center">TITLE</th>
                  <th class="text-center">WRITER</th>
                  <th>REG_DATE</th>
                </tr>
              </thead>
              <tbody >
              <c:forEach var="boardVO" items="${listBoardVO}">
                <tr style="cursor: pointer;" onclick="location.replace('/admin/board/board_view?bno=${boardVO.bno}');">
                  <td class="text-center">${boardVO.bno }</td>
                  <td class="text-center">${boardVO.board_type }</td>
                  <td class="text-center">${boardVO.title }</td>
                  <td class="text-center">${boardVO.writer }</td>
                  <td><fmt:formatDate pattern="yyyy-MM-dd hh:mm:ss" value="${boardVO.reg_date }"/></td>
                </tr>
               </c:forEach>
              </tbody>
            </table>
          </div>
          <!-- /.card-body -->
        </div>
        <!-- //콘텐츠 내용 -->
        <!-- 페이징 처리 -->
        <div class="col-12 text-right">
          <a href="/admin/board/board_insert" class="btn btn-primary mb-3">글쓰기</a>
          <ul class="pagination justify-content-center">
              <li class="paginate_button page-item previous ${pageVO.prev==false?'disabled':''}" id="example2_previous">
                <a href="/admin/board/board_list?page=${pageVO.startPage-1}&search_type=${pageVO.search_type}&search_keyword=${pageVO.search_keyword}" aria-controls="example2" data-dt-idx="0" tabindex="0" class="page-link">Previous</a>
              </li>
              <c:forEach begin="${pageVO.startPage}" end="${pageVO.endPage }" step="1" var="idx">
              <li class="paginate_button page-item ${pageVO.page==idx?'active':''}">
                <a href="/admin/board/board_list?page=${idx}&search_type=${pageVO.search_type}&search_keyword=${pageVO.search_keyword}" aria-controls="example2" data-dt-idx="1" tabindex="0" class="page-link">${idx }</a>
              </li>
            	</c:forEach>
              <li class="paginate_button page-item next ${pageVO.next==false?'disabled':''}" id="example2_next">
                <a href="/admin/board/board_list?page=${pageVO.endPage+1}&search_type=${pageVO.search_type}&search_keyword=${pageVO.search_keyword}" aria-controls="example2" data-dt-idx="7" tabindex="0" class="page-link">Next</a>
              </li>
          </ul>
        </div>
        <!-- //페이징 처리 -->
      </div><!-- /.container-fluid -->
    </section>
    <!-- /.content -->

<%@ include file="../include/footer.jsp" %>