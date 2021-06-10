package com.edu.controller;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.edu.service.IF_MemberService;
import com.edu.vo.MemberVO;
import com.edu.vo.PageVO;

/**
 * 이 클래스는 Admin 관리자단을 접근하는 Controller class
 * 변수 Object를 만들어서 jsp로 전송 + jsp에서 값을 받아서 class에서 처리
 * @author 웅비
 *
 */
@Controller
public class AdminController {
	// 컨트롤러 수정하면 자동로딩(auto컴파일)
	// 디버그용 로거객체 생성.
	private Logger logger = LoggerFactory.getLogger(AdminController.class);
	// 회원목록을 출력하는 jsp와 매핑
	@Inject
	private IF_MemberService memberService;
	@RequestMapping(value="/admin/member/member_list", method=RequestMethod.GET)
	public String selectMember(PageVO pageVO) throws Exception {
		// jsp의 검색시 search_type, search_keyword 내용이 PageVO클래스에 set된다.
		// 검색한 결과를 jsp에 보내주기.
		if(pageVO.getPage() == null) {
		pageVO.setPage(1);
		}
		//pageVO의 calcPage메서드를 실행하려면, 필수 변수값 입력
		pageVO.setQueryPerPageNum(10);	
		pageVO.setTotalCount(memberService.countMember(pageVO));
		pageVO.setPerPageNum(10);
		List<MemberVO> listMember = memberService.selectMember(pageVO);
		logger.info("디버그" + pageVO.toString());
		return "admin/member/member_list";//상대경로.
	}
	//URL요청 경로는 @RequestMapping 반드시 절대 경로로 표시
	@RequestMapping(value="/admin", method=RequestMethod.GET)
	public String admin(Model model) throws Exception { //에러 발생시 Exception을 스프링으로 보내게 됩니다.
		
		
		return "admin/home"; //리턴 경로 = 접근경로는 반드시 상대 경로로 표시(views가 최상위 폴더)
		// prefix(/WEB-INF/views/) suffix(.jsp)
	}
}
