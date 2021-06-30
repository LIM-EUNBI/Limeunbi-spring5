<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!--  ------- 여기부터는 푸터 부분 ------------- --> 
  <footer class="main-footer">
    <strong>Copyright &copy; 2014-2021 <a href="https://adminlte.io">AdminLTE.io</a>.</strong>
    All rights reserved.
    <div class="float-right d-none d-sm-inline-block">
      <b>Version</b> 3.1.0
    </div>
  </footer>

  <!-- Control Sidebar 오른쪽 메뉴 클릭시 나오는 내용  -->
  <aside class="control-sidebar control-sidebar-dark">
    <!-- demo.js을 사용하지 않고 직접 로그아웃 버튼만 나오게 처리 -->
    <div class="text-center mt-3">
    <h5>로그아웃</h5><hr class="mb-2"/>
    <button class="btn-primary" id="btn_logout" type="button">로그아웃</button>
  </div>
  </aside>
  <!-- /.control-sidebar -->
</div>
<!-- ./wrapper -->

<!-- jQuery -->
<script src="/resources/admin/plugins/jquery/jquery.min.js"></script>
<!-- jQuery UI 1.11.4 -->
<script src="/resources/admin/plugins/jquery-ui/jquery-ui.min.js"></script>
<!-- Resolve conflict in jQuery UI tooltip with Bootstrap tooltip -->
<script>
  $.widget.bridge('uibutton', $.ui.button)
</script>
<!-- Bootstrap 4 코어 -->
<script src="/resources/admin/plugins/bootstrap/js/bootstrap.bundle.min.js"></script>
<!-- Tempusdominus Bootstrap 4 댓글 스타일에 필요-->
<!-- <script src="/resources/admin/plugins/tempusdominus-bootstrap-4/js/tempusdominus-bootstrap-4.min.js"></script> -->
<!-- Summernote -->
<script src="/resources/admin/plugins/summernote/summernote-bs4.min.js"></script>
<!-- overlayScrollbars -->
<script src="/resources/admin/plugins/overlayScrollbars/js/jquery.overlayScrollbars.min.js"></script>
<!-- AdminLTE App -->
<script src="/resources/admin/dist/js/adminlte.js"></script>
<!-- AdminLTE for demo purposes 오른쪽 메뉴 - 로그아웃으로 사용 -->
<!-- <script src="/resources/admin/dist/js/demo.js"></script> -->
<!-- AdminLTE dashboard demo 대시보드 전용 코어 -->
<!-- <script src="/resources/admin/dist/js/pages/dashboard.js"></script> -->
</body>
</html>
<style>
.sidebar-dark-primary .nav-sidebar>.nav-item>.nav-link.active, .sidebar-light-primary .nav-sidebar>.nav-item>.nav-link.active
{background-color: #fff; color:#000}
</style>
<script>
/* 왼쪽 메뉴 선택시 active 부트 스트랩 클래스를 동적으로 추가하는 코드 */
$(document).ready(function(){
	//현재 선택한 url값을 기준으로 지정
	var current = location.pathname; //현재 url경로
	var current2 = current.split("/")[2];
	/* alert(current2); */
	$(".nav-treeview li a").each(function(){
		if($(this).attr('href').indexOf(current2) != -1){
			if(current2 != "board"){
			$(this).addClass("active");}
			
		} else{
			$(this).removeClass("active");
		}
	});
	
});
</script>
    