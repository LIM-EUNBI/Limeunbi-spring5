package com.edu.controller;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.edu.service.IF_MemberService;
import com.edu.vo.MemberVO;
import com.edu.vo.PageVO;

/**
 * 이 클래스는 Admin 관리자단을 접근하는 Controller class
 * 변수 Object를 만들어서 jsp로 전송 + jsp에서 값을 받아서 class에서 처리
 * @author 은비
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
	
	// 수정처리를 호출 = DB변경처리
	@RequestMapping(value="/admin/member/member_update", method=RequestMethod.POST)
	public String updateMember(MemberVO memberVO, PageVO pageVO) throws Exception{
		// 수정처리 이후 본인페이지에 있습니다.
		// 업데이트 쿼리서비스 호출하기 전 스프링시큐리티 암호화 적용합니다.
		String rawPw = memberVO.getUser_pw();
		if(!rawPw.isEmpty()) { //수정폼에서 암호 입력값이 있을때만 해당 코드 실행.
			BCryptPasswordEncoder pwEncoder = new BCryptPasswordEncoder();
			String encPw = pwEncoder.encode(rawPw);
			memberVO.setUser_pw(encPw);
		}
		memberService.updateMember(memberVO);
		String qString = "user_id="+memberVO.getUser_id()+"&page="+pageVO.getPage()+"&search_type="+pageVO.getSearch_type()+
				"&search_keyword="+pageVO.getSearch_keyword();
		return "redirect:/admin/member/member_update_form?"+qString;
	}
	// 수정폼을 호출 = 화면에 출력만(렌더링)
	@RequestMapping(value="/admin/member/member_update_form", method=RequestMethod.GET)
	public String updateMemberForm(MemberVO memberVO, Model model, @ModelAttribute("pageVO")PageVO pageVO) throws Exception {
		// 수정폼에 pageVO, memberVO 2개의 데이터 객체를 jsp로 보낸다.
		MemberVO member = memberService.readMember(memberVO.getUser_id());
		
		// 회원의 레코드를 model과 @ModelAttribute에 담아서 jsp로 보낸다.
		model.addAttribute("memberVO", member);
		return "admin/member/member_update";
	}
	@RequestMapping(value="/admin/member/member_delete", method=RequestMethod.POST)
	public String deleteMember(MemberVO memberVO) throws Exception {
		logger.info("디버그: " + memberVO.toString());
		//MemberVO memberVO는 클래스형 변수: String user_id 스트링형 변수 같은 방식.
		String user_id = memberVO.getUser_id();
		//이 메서드는 회원상세보기페이지에서 삭제버튼을 클릭시 전송받은 memberVO값을 이용해서 삭제를 구현(아래)
		memberService.deleteMember(user_id);
		return "redirect:/admin/member/member_list";
	}
	@RequestMapping(value="/admin/member/member_view", method=RequestMethod.GET)
	public String viewMemberForm(Model model,@RequestParam("user_id")String user_id,@ModelAttribute("pageVO")PageVO pageVO) throws Exception {
		/**
		 * 리스트 페이지에서 상세보기로 이동할 때 보여주는 메서드
		 * JUnit에서 테스트 했던 readMember 이용.
		 * @RequestParam 인터페이스를 이용해서 파라미터값을 받는다.
		 */
		
		// memberVO 1개의 레코드를 model을 이용해서 member_view.jsp로 보낸다.
		model.addAttribute("memberVO", memberService.readMember(user_id));
		
		return "admin/member/member_view";
	}
	
	@RequestMapping(value="/admin/member/member_list", method=RequestMethod.GET)
	public String selectMember(@ModelAttribute("pageVO")PageVO pageVO, Model model) throws Exception {
		// 이 메서드는 2개 객체를 JSP로 보내는 기능을 수행. 
		// memberList, pageVO객체 생성 -> model 통해서 jsp로 전송
		
		// jsp의 검색시 search_type, search_keyword 내용이 PageVO클래스에 set된다.
		// 검색한 결과를 jsp에 보내주기.
		if(pageVO.getPage() == null) {
		pageVO.setPage(1);
		}
		//pageVO의 calcPage메서드를 실행하려면, 필수 변수값 입력
		pageVO.setQueryPerPageNum(5);	
		pageVO.setPerPageNum(5); // UI하단에 보여줄 페이지번호 갯수
		pageVO.setTotalCount(memberService.countMember(pageVO));
		List<MemberVO> listMember = memberService.selectMember(pageVO);
		logger.info("디버그" + pageVO.toString());
		model.addAttribute("listMember", listMember);
//		model.addAttribute("pageVO", pageVO); //나중에 @ModelAttribute로 사용.
		return "admin/member/member_list";//상대경로.
	}
	//URL요청 경로는 @RequestMapping 반드시 절대 경로로 표시
	@RequestMapping(value="/admin", method=RequestMethod.GET)
	public String admin(Model model) throws Exception { //에러 발생시 Exception을 스프링으로 보내게 됩니다.
		
		
		return "admin/home"; //리턴 경로 = 접근경로는 반드시 상대 경로로 표시(views가 최상위 폴더)
		// prefix(/WEB-INF/views/) suffix(.jsp)
	}
}
