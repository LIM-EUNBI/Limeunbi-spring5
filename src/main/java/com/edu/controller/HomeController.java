package com.edu.controller;

import java.io.File;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.edu.dao.BoardDAO;
import com.edu.service.IF_BoardService;
import com.edu.service.IF_MemberService;
import com.edu.util.CommonUtil;
import com.edu.vo.AttachVO;
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
	@Inject
	private CommonUtil commonUtil;
	@Inject
	private BoardDAO boardDAO;
	/**
	 * 사용자요청(웹브라우저)을 받아서=@RequestMapping인테페이스를 사용해서 메서드명을 스프링이 구현합니다.
	 *  ,router(루트rootX)
	 * return 값으로 view(jsp)를 선택해서 작업한 결과를 변수로 담아서 화면에 전송 후 결과를 표시(렌더링) 합니다.
	 * 폼(자료)전송시 post(자료숨김), get(자료노출-URL쿼리스트링?있는자료전송)
	 */
	
	//게시물 수정 처리 POST 추가
	@RequestMapping(value="/home/board/board_update",method=RequestMethod.POST)
	public String board_update(HttpServletRequest request,@RequestParam("file")MultipartFile[] files,PageVO pageVO,BoardVO boardVO,RedirectAttributes rdat) throws Exception {
		//로그인한 세션ID와 게시물의 boardVO.writer사용자와 비교해서 같으면 계속, 다르면 진행X
		/*HttpSession session = request.getSession();
		if(!boardVO.getWriter().equals(session.getAttribute("session_userid"))) {
			rdat.addFlashAttribute("msgError","본인글만 수정할 수 있습니다.");
			//request객체에 이전페이지 URL("Referer")로 존재하고, URL이 이전페이지로 이동
			return "redirect:"+request.getHeader("Referer");
		}*/
		//첨부파일 처리, delFiles만드는 이유는 첨부파일은 수정시, 기존파일 삭제 후 입력해야 하기 때문에
		List<AttachVO> delFiles = boardService.readAttach(boardVO.getBno());
		//폼에서 전송받은 첨부파일 files 가로배치로 만들기 위해서 배열변수 생성
		String[] real_file_names = new String[files.length];//전송된 files없다면 null이 들어감.
		String[] save_file_names = new String[files.length];
		int index = 0;
		for(MultipartFile file:files) {
			//배열 인덱스 위치에 따라서 전송받은 파일과 기존파일과 인덱스를 비교해서 삭제 후 저장 처리
			if(file.getOriginalFilename() != "") {
				int sun = 0;//아래 for문을 위한 초기변수 생성
				for(AttachVO delfile:delFiles) {
					if(index==sun) {
						File target = new File(commonUtil.getUploadPath(),delfile.getSave_file_name());//저장소에 저장된 UUID파일명을 타겟으로 지정.
						if(target.exists()) {
							target.delete();//실제 파일이 지워짐:신규파일을 덮어 쓰려고 지움.
							boardDAO.deleteAttach(delfile.getSave_file_name());//save_file_name이 UUID로서 PK값임.
						}
					}
					sun = sun + 1;//기존파일 삭제할 인덱스 1씩 증가
				}
				//신규파일 저장처리, 물리적으로 저장소 저장
				String save_file_name = commonUtil.fileUpload(file);//저장소에 저장후 UUID파일명을 반환
				save_file_names[index] = save_file_name;
				real_file_names[index] = file.getOriginalFilename();//UI용 파일명.
			} else {
				save_file_names[index] = null;
				real_file_names[index] = null;
			}
			index = index + 1;//신규파일 등록 인덱스 1씩 증가
		}
		boardVO.setSave_file_names(save_file_names);
		boardVO.setReal_file_names(real_file_names);
		//시큐어 코딩처리
		String rawTitle = boardVO.getTitle();
		String rawContent = boardVO.getContent();
		boardVO.setTitle(commonUtil.unscript(rawTitle));
		boardVO.setContent(commonUtil.unscript(rawContent));
		//게시판테이블 처리
		boardService.updateBoard(boardVO);
		rdat.addFlashAttribute("msg", "게시물이 수정");//출력메세지: 게시물 수정 이(가) 성공~
		return "redirect:/home/board/board_view?bno="+boardVO.getBno()+"&page="+pageVO.getPage();//수정하고 뷰페이지로 이동
	}

	//게시물 수정 폼 호출 GET 추가
	@RequestMapping(value="/home/board/board_update_form", method=RequestMethod.GET)
	public String board_update_form(RedirectAttributes rdat, HttpServletRequest request,Model model, @ModelAttribute("pageVO")PageVO pageVO, @RequestParam("bno")Integer bno) throws Exception{
	
		//1개의 레코드만 서비스로 호출, 첨부파일은 세로데이터를 가로데이터로 변경 후 boardVO 담아서 전송
		BoardVO boardVO = new BoardVO();
		boardVO = boardService.readBoard(bno);
		//로그인한 세션ID와 게시물의 boardVO.writer사용자와 비교해서 같으면 계속, 다르면 진행X
		/* HttpSession session = request.getSession();
		if(!boardVO.getWriter().equals(session.getAttribute("session_userid"))) {
			rdat.addFlashAttribute("msgError","본인글만 수정할 수 있습니다.");
			return "redirect:"+request.getHeader("Referer");
		}*/
		//save_file_names, real_file_names 가상필드값을 채운다.
		List<AttachVO> fileList = boardService.readAttach(bno);
		int index = 0;
		String[] save_file_names = new String[fileList.size()];
		String[] real_file_names = new String[fileList.size()];
		for(AttachVO file:fileList) {
			save_file_names[index] = file.getSave_file_name();
			real_file_names[index] = file.getReal_file_name();
			index++;
		}
		boardVO.setReal_file_names(real_file_names);
		boardVO.setSave_file_names(save_file_names);
		model.addAttribute("boardVO", boardVO);
		
		return "home/board/board_update";
	}
	
	//게시물 삭제 처리 호출 POST 추가 --------------------------------------------------------
	@RequestMapping(value="/home/board/board_delete", method=RequestMethod.POST)
	public String board_delete(PageVO pageVO,RedirectAttributes rdat, HttpServletRequest request,@RequestParam("bno")Integer bno) throws Exception{
		/*BoardVO boardVO = boardService.readBoard(bno);
		//로그인한 세션ID와 게시물의 boardVO.writer사용자와 비교해서 같으면 계속, 다르면 진행X
		HttpSession session = request.getSession();
		if(!boardVO.getWriter().equals(session.getAttribute("session_userid"))) {
			rdat.addFlashAttribute("msgError","본인글만 삭제할 수 있습니다.");
			return "redirect:"+request.getHeader("Referer");
		}*/
		
		//부모레코드 삭제 전 삭제할 파일들 변수로 임시저장
		List<AttachVO> delFiles = boardService.readAttach(bno);
		//테이블 1개 레코드 삭제처리
		boardService.deleteBoard(bno); 
		//첨부파일 있으면 삭제
		for(AttachVO file:delFiles) {//향상된 for문에서 조건문
			//File 클래스는 객체를 생성할 때 생성자의 매개변수로(경로, 파일명)
			File target = new File(commonUtil.getUploadPath(), file.getSave_file_name());
			if(target.exists()) { //파일이 존재하면 삭제
				target.delete();//물리적인 파일 삭제				
			}
		}
		rdat.addFlashAttribute("msg", "삭제가 완료");
		return "redirect:/home/board/board_list";
	}
	//게시물 상세보기  ----------------------------------------------------------------
	@RequestMapping(value="/home/board/board_view", method=RequestMethod.GET)
	public String board_view(Model model, @ModelAttribute("pageVO") PageVO pageVO,@RequestParam("bno")Integer bno) throws Exception {
		//첨부파일 가져오기
		List<AttachVO> listAttachVO = boardService.readAttach(bno);
		//첨부파일이 있다면 save_file_names, real_file_names 2개를 만듬
		String[] save_file_names = new String[listAttachVO.size()];
		String[] real_file_names = new String[listAttachVO.size()];
		int index = 0;
		for(AttachVO file:listAttachVO) {//세로데이터를 가로데이터로 변경처리
			save_file_names[index] = file.getSave_file_name();
			real_file_names[index] = file.getReal_file_name();
			index++;
		}
		BoardVO boardVO = boardService.readBoard(bno);//1개 레코드 입력됨.
		boardVO.setSave_file_names(save_file_names);
		boardVO.setReal_file_names(real_file_names);
		//dB테이블 데이터 가져오기
		model.addAttribute("boardVO", boardVO);
		model.addAttribute("checkImgArray", commonUtil.getCheckImgArray());
		return "home/board/board_view";//.jsp생략
	}
	
	//게시물 등록 처리 호출 POST 추가 -------------------------------------------------------
	@RequestMapping(value="/home/board/board_insert",method=RequestMethod.POST)
	public String board_insert(RedirectAttributes rdat, @RequestParam("file")MultipartFile[] files,BoardVO boardVO) throws Exception {
		//첨부파일 처리
		String[] save_file_names = new String[files.length];
		String[] real_file_names = new String[files.length];
		int index = 0;//위 String[]배열의 인덱스 값으로 사용할 변수선언
		for(MultipartFile file:files) {
			//첨부파일이 존재하면 실행조건
			if(file.getOriginalFilename()!="") {
				real_file_names[index] = file.getOriginalFilename();
				save_file_names[index] = commonUtil.fileUpload(file);//UUID를 반환
			}
			index++;
		}
		//Attach테이블에 insert할 첨부파일 가상변수값을 입력
		boardVO.setSave_file_names(save_file_names);
		boardVO.setReal_file_names(real_file_names);
		//타이틀,content 내용 시큐어코딩 처리(아래4줄)
		String rawTitle = boardVO.getTitle();
		String rawContent = boardVO.getContent();
		boardVO.setTitle(commonUtil.unscript(rawTitle));
		boardVO.setContent(commonUtil.unscript(rawContent));
		//DB테이블 처리
		boardService.insertBoard(boardVO);
		rdat.addFlashAttribute("msg", "게시물 등록");//출력:게시물 등록 이(가) 성공~
		return "redirect:/home/board/board_list";
	}
	//게시물 등록 폼 호출
	@RequestMapping(value="/home/board/board_insert_form", method=RequestMethod.GET)
	public String board_insert_form() throws Exception{
		return "home/board/board_insert";
	}
	//게시물 리스트 페이지 호출 -------------------------------------------------------------
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
	
	//404파일 에러 처리 GET호출 추가 ----------------------------------------------------
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
//		return "home/join";
		return "join.tiles";
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
	/*
	 * @RequestMapping(value="/login_form", method=RequestMethod.GET) public String
	 * login_form(Model model) throws Exception{ model.addAttribute("url", null);
	 * return "home/login"; }
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String homepage(Model model) throws Exception{ //콜백메스드,자동실행됨.
		/*
		 * String jspVar = "@서비스(DB)에서 처리한 결과"; model.addAttribute("jspObject", jspVar);
		 * //home.jsp파일로 자료를 전송(스프링)하는 기능= model인터페이스 객체(스프링이처리)에 내용만 채우면됨
		 * logger.info("디버그 스프링 로고 사용 :" + jspVar); // System.out 대신 logger 객체를 사용.
		 */		
		PageVO pageVO = new PageVO();
		pageVO.setPage(1);
		pageVO.setQueryPerPageNum(3); //겔러리3개
		pageVO.setBoard_type("gallery");
		//첨부파일
		List<BoardVO> latestGallery = boardService.selectBoard(pageVO);
		for(BoardVO boardVO:latestGallery) { //리스트형 객체를 한개씩 뽑아서 1개 레코드에 입력
			List<AttachVO> attachVO = boardService.readAttach(boardVO.getBno());
			if(attachVO.size() > 0) {
				String[] save_file_names = new String[attachVO.size()];
				save_file_names[0] = attachVO.get(0).getSave_file_name();
				boardVO.setSave_file_names(save_file_names);
			}
		}
		model.addAttribute("latestGallery", latestGallery); //겔러리 최근 게시물
		
		pageVO.setQueryPerPageNum(5);//공지사항 5개
		pageVO.setBoard_type("notice");
		model.addAttribute("latestNotice", boardService.selectBoard(pageVO)); //공지사항 최근 게시물
		
//		return "home/index";//확장자가 생략 .jsp가 생략되어 있음.
		return "index.tiles";
	}
}