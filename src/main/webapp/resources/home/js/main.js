$(document).ready(function(){
    $('.to_top a').smoothScroll();
    
    // 모바일용 메뉴 보이기/숨기기 액션처리
    $('.openMOgnb').click(function(){
        // alert('메뉴 보이기 액션 클릭'); // 테스트(디버그)
        // $('.header_cont').css('display','block');
        $('.header_cont').slideDown('slow');
        $('#header').addClass("on");
        // $('.openMOgnb').click(function(){
        // 	$('.header_cont').slideUp('fast');
        // 	$('#header').removeClass('on');
        // });
    });
    $('.closePop').click(function(){
        // $('.header_cont').css('display','none');
        $('.header_cont').slideUp('fast');
        $('#header').removeClass('on');
    });

});

// PC용 대메뉴 마우스 롤오버 이벤트 만들기
// 첫번째 서브메뉴  .gnb_depth2_1 (롤아웃)
var isOver1 = false;
var isOverSub1 = false;
function goHide1() {
	if(!isOver1 && !isOverSub1) {
		// jQuery는 뒤에서 앞으로 해석된다.
		$('.gnb_depth2_1').stop().fadeOut('fast');
		// 대메뉴 중 1번의 2차 gnb_depth2_1 영역
	}
}
// 두번째 서브메뉴 .gnb_depth2_2 (롤아웃)
var isOver2 = false;
var isOverSub2 = false;
function goHide2() {
	if(!isOver2 && !isOverSub2) {
		$('.gnb_depth2_2').stop().fadeOut('fast');
	}
}

$(document).ready(function() {
	// PC용 대메뉴 롤오버 이벤트 처리
	// 대메뉴 1번의 마우스 오버 이벤트가 발생했을 때 
	// .gnb_depth2_1 서브메뉴 보이기 
	$('.openAll1').mouseover(function() {
		// header태그의 크기가 1055 이상, parseInt는 문자를 숫자로 형변환
		if(parseInt($('header').css('width')) > 1050) { 
			$('.gnb_depth2_1').fadeIn('fast');
		}
		isOver1 = true;
	});
	//.gnb_depth2_1 서브메뉴 숨기기
	$('.openAll1').mouseout(function() {
		isOver1 = false;
		setTimeout("goHide1()", 100); // 0.2초 후에 goHide1 함수실행
	});
	// 서브메뉴에 마우스 오버했을 때
	$('.gnb_depth2_1').mouseover(function() {
		isOverSub1 = true;
	});
	$('.gnb_depth2_1').mouseout(function() {
		isOverSub1 = false;
		setTimeout('goHide1()', 100);
	});

	// 대메뉴 2번의 마우스 오버 이벤트가 발생했을 때 
	// .gnb_depth2_1 서브메뉴 보이기
	$('.openAll2').mouseover(function() {
		if(parseInt($('header').css('width')) > 1050) { // header태그의 크기가 1055 이상이라면
			$('.gnb_depth2_2').fadeIn('fast');
		}
		isOver2 = true;
	});
	// .gnb_depth2_1 서브메뉴 숨기기
	$('.openAll2').mouseout(function() {
		isOver2 = false;
		setTimeout('goHide2()', 100); // 2초 후에 goHide1 함수실행
	});

	// 서브메뉴에 마우스 오버했을 때
	$('.gnb_depth2_2').mouseover(function() {
		isOverSub2 = true;
	});
	$('.gnb_depth2_2').mouseout(function() {
		isOverSub2 = false;
		setTimeout('goHide2()', 100);
	});
});

