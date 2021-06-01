var slideNum = 0; // 슬라이드 인덱스 초기값
var slideAuto = null; // 슬라이드 자동실행 변수 flag(on | off)
// 함수 선언
function play_w(directw) { // 좌우로 슬라이드되는 함수 선언
	if(directw == "right") {
		slideNum += 1; // 슬라이드 번호 증가
		if(slideNum > 2) { slideNum = 0; } // 슬라이드 이미지가 3개일 때로 고정.
	}
	else if(directw == "left"){
		slideNum -= 1;	// 슬라이드 번호 감소
		if(slideNum < 0) { slideNum = 2; } // 순서대로 무한반복을 명시
	}
	else {
		slideNum = directw;
	}

	// li태그에서 클래스가 seq인것 3개를 each함수로 반복(3번)
	// 모든 슬라이드 버튼을 작은 원으로 바꾸는 기능
	$(".rollingbtn").find('li.seq a').each(function(){
		$('li.seq a img').attr('src', $('li.seq a img').attr('src').replace('_on.png', '_off.png'));
	});
	// 아래 결과는 현재 선택된 슬라이드 이미지만 길쭉한 원으로 바꿔주는 기능
	$('li.butt' + slideNum + ' a img').attr('src', $('li.butt' + slideNum + ' a img').attr('src').replace('_off.png', '_on.png'));
	// slideNum 조건 실행 -> 아래 3가지 중 1가지는 항상 실행됨
	if(slideNum == 0) {
		// 슬라이드 인덱스가 0번일 때 li태그를 1초간 서서히 사라지게(투명도 0) 처리
		$('.viewImgList li.imglist1').animate({'opacity': 0}, 2000);
		$('.viewImgList li.imglist2').animate({'opacity': 0}, 2000);
		$('.viewImgList li.imglist0').animate({'opacity': 1}, 2000);
	} else if(slideNum == 1) {
		$('.viewImgList li.imglist0').animate({'opacity': 0}, 2000);
		$('.viewImgList li.imglist2').animate({'opacity': 0}, 2000);
		$('.viewImgList li.imglist1').animate({'opacity': 1}, 2000);

	} else if(slideNum == 2) {
		$('.viewImgList li.imglist0').animate({'opacity': 0}, 2000);
		$('.viewImgList li.imglist1').animate({'opacity': 0}, 2000);
		$('.viewImgList li.imglist2').animate({'opacity': 1}, 2000);
	}
	if(slideAuto) { // true일 때 자동 슬라이드, false일 때 멈춤
		clearTimeout(slideAuto); // play_w 함수를 실행 중지
	}
	slideAuto = setTimeout("play_w('right')", 3000); // 3초 단위로 play_w('right') 실행
} // play_w 함수 끝