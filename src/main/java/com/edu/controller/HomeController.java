package com.edu.controller;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//외부 라이브러리(모듈) 사용 = import
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.edu.service.IF_BoardService;
import com.edu.service.IF_MemberService;
import com.edu.vo.BoardVO;
import com.edu.vo.MemberVO;
import com.edu.vo.PageVO;

/**
 * 이 클래스는 MVC웹프로젝트를 최초로 생성시 자동으로 생성되는 클래스
 * 웹브라우저의 요청사항을 view단(jsp)에 연결시켜주는 클래스 @Controller.
 * 스프링에서 관리하는 클래스를 스프링빈(콩) = 스프링빈을 명시 @Controller 애노테이션
 * Beans(콩들) 그래프로 이 프로젝트의 규모를 확인가능.
 * 스프링이 관리하는 클래스=스프링빈은 파일아이콘에 S가 붙습니다. 
 */


@Controller
public class HomeController {
	//스프링빈(클래스) 에서는 로거로 디버그를 합니다.=로거객체를 만듭니다.
	// 로그중 slf4j(Spring Log For Java)
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	@Inject
	private IF_MemberService memberService;
	@Autowired
	private IF_BoardService boardService;
	/**
	 * 사용자요청(웹브라우저)을 받아서=@RequestMapping인테페이스를 사용해서 메서드명을 스프링이 구현합니다.
	 *  ,router(루트rootX)
	 * return 값으로 view(jsp)를 선택해서 작업한 결과를 변수로 담아서 화면에 전송 후 결과를 표시(렌더링) 합니다.
	 * 폼(자료)전송시 post(자료숨김), get(자료노출-URL쿼리스트링?있는자료전송)
	 */
	
	//게시물 리스트 페이지 호출
	@RequestMapping(value="/home/board/board_list", method=RequestMethod.GET)
	public String board_list(Model model, @ModelAttribute("pageVO") PageVO pageVO) throws Exception{
		
		if(pageVO.getPage() == null) {
			pageVO.setPage(1);
		}
		pageVO.setQueryPerPageNum(5);
		pageVO.setPerPageNum(5);
		int totalCount = boardService.countBoard(pageVO);
		pageVO.setTotalCount(totalCount);//prev, next 변수값이 생성
		List<BoardVO> boardList = boardService.selectBoard(pageVO);
		model.addAttribute("boardList", boardList);
		return "home/board/board_list";
	}
	
	//404파일 에러 처리 GET호출 추가
	@RequestMapping(value="/home/error/error_404", method=RequestMethod.GET)
	public String error_404(HttpServletRequest request, Model model) {
		model.addAttribute("prevPage", request.getHeader("Referer"));
		return "home/error/error_404";
	}
	 
	// 회원가입 -----------------------------------------------------------------------
	@RequestMapping(value="/join", method=RequestMethod.POST)
	public String join(MemberVO memberVO, RedirectAttributes rdat) throws Exception{
		// jsp폼에서 levels를 ROLE_ADMIN으로 누군가 강제로 넣을 가능성이 있기 때문에 컨트롤러에서 set해준다.
		memberVO.setLevels("ROLE_USER");
		memberVO.setEnabled(true);
		memberVO.setPoint(0);

		String raw_pw = memberVO.getUser_pw();
		BCryptPasswordEncoder pwEncoder = new BCryptPasswordEncoder();
		String encPw = pwEncoder.encode(raw_pw);
		memberVO.setUser_pw(encPw);

		memberService.insertMember(memberVO);
		rdat.addFlashAttribute("msg", "회원가입이 ");
		return "redirect:/login_form";
	}
	
	// 회원가입폼 호출 GET
	@RequestMapping(value="/join_form", method=RequestMethod.GET)
	public String join_form() throws Exception{
		return "home/join";
	}
	
	// 회원 탈퇴 -----------------------------------------------------------------------
	@RequestMapping(value="/member/mypage_leave", method=RequestMethod.POST)
	public String mypage_leave(MemberVO memberVO, RedirectAttributes rdat) throws Exception{
		memberService.updateMember(memberVO);
		rdat.addFlashAttribute("msg", "회원 탈퇴");
		return "redirect:/logout";
	}
	
	
	// 마이페이지 정보 수정 -----------------------------------------------------------------------
	// 마이페이지 회원정보 수정 POST 처리 후 msg를 히든값으로 jsp로 전송.
	@RequestMapping(value="/member/mypage", method=RequestMethod.POST)
	public String mypage(MemberVO memberVO, RedirectAttributes rdat) throws Exception{
		// 암호화 인코딩처리
		if(!memberVO.getUser_pw().isEmpty()) {
			BCryptPasswordEncoder pwEncoder = new BCryptPasswordEncoder();
			String raw_pw = memberVO.getUser_pw();
			String encPw = pwEncoder.encode(raw_pw);
			memberVO.setUser_pw(encPw);
		}
		memberService.updateMember(memberVO);
		rdat.addFlashAttribute("msg", "회원 정보가 수정");
		return "redirect:/member/mypage_form";
	}
	// 마이페이지폼 호출, 회원 수정폼-----------------------------------------------------------------------
	@RequestMapping(value="/member/mypage_form", method=RequestMethod.GET)
	public String mypage_form(HttpServletRequest request, Model model) throws Exception{ //request.발생된 세션 가져오기
		//로그인 한 사용자 세션을 session_userid로 memberService의 readMember를 호출하면 됨.
		HttpSession session = request.getSession();
		String user_id = (String) session.getAttribute("session_userid");
		model.addAttribute("memberVO", memberService.readMember(user_id));
		return "home/member/mypage";
	}
	// 사용자단 로그인폼 호출-----------------------------------------------------------------------
	@RequestMapping(value="/login_form", method=RequestMethod.GET)
	public String login_form() throws Exception{
		return "home/login";
	}
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String homepage(Model model) { //콜백메스드,자동실행됨.
		String jspVar = "@서비스(DB)에서 처리한 결과";
		model.addAttribute("jspObject", jspVar);
		//home.jsp파일로 자료를 전송(스프링)하는 기능= model인터페이스 객체(스프링이처리)에 내용만 채우면됨
		logger.info("디버그 스프링 로고 사용 :" + jspVar); // System.out 대신 logger 객체를 사용.
		return "home/index";//확장자가 생략 .jsp가 생략되어 있음.
	}
}