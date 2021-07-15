package com.edu.controller;

import java.io.File;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.edu.dao.BoardDAO;
import com.edu.service.IF_BoardService;
import com.edu.service.IF_BoardTypeService;
import com.edu.service.IF_MemberService;
import com.edu.util.CommonUtil;
import com.edu.vo.AttachVO;
import com.edu.vo.BoardTypeVO;
import com.edu.vo.BoardVO;
import com.edu.vo.MemberVO;
import com.edu.vo.PageVO;

/**
 * 이 클래스는 Admin 관리자단을 접근하는 Controller class
 * 디스패처 서블렛(게이트웨이) 클래스는 톰캣이 실행될 때 제일 먼저 실행되는 클래스
 * 디스패처 서블렛으로 컨트롤러에 있는 모든 맵핑을 읽는다.
 * 변수 Object를 만들어서 jsp로 전송 + jsp에서 값을 받아서 class에서 처리
 * views가 최상위 루트
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
	@Inject
	private IF_BoardTypeService boardTypeService;
	@Inject
	private IF_BoardService boardService;
	@Inject
	private CommonUtil commonUtil;
	@Inject
	private BoardDAO boardDAO;
	
	// ************************ 게시물 관리 ***************************
	@RequestMapping(value="/admin/board/board_insert", method=RequestMethod.POST)
	public String board_insert(@RequestParam("file")MultipartFile[] files,BoardVO boardVO) throws Exception{
		// 신규 등록이라서 기존 첨부파일 불러오는 로직은 필요없음.
		String[] save_file_names = new String[files.length];
		String[] real_file_names = new String[files.length];
		int idx = 0; //첨부파일이 1개 이상일 때 반복문에 사용할 변수
		for(MultipartFile file:files) {
			if(file.getOriginalFilename() != "") {
				save_file_names[idx] = commonUtil.fileUpload(file); // 실제파일 저장 
				real_file_names[idx] = file.getOriginalFilename(); // UI용 파일이름
			}
			idx++;
		}
		// jsp폼에서 보내온 boardVO.값에 아래 임시 변수값을 저장.
		boardVO.setSave_file_names(save_file_names);
		boardVO.setReal_file_names(real_file_names);
		boardService.insertBoard(boardVO); // DB에 저장하는 서비스 호출
		return "redirect:/admin/board/board_list";
	}
	
	@RequestMapping(value="/admin/board/board_insert_form", method=RequestMethod.GET)
	public String board_insert_form(@ModelAttribute("pageVO")PageVO pageVO) throws Exception{
		if(pageVO.getPage() == null) {
			pageVO.setPage(1);
		}
		return "admin/board/board_insert";
	}
	@RequestMapping(value="/admin/board/board_update", method=RequestMethod.POST)
	public String board_update(@RequestParam("file") MultipartFile[] files,PageVO pageVO, BoardVO boardVO) throws Exception{
		
		// 기존 등록된 첨부파일 목록 구하기
		List<AttachVO> delFiles = boardService.readAttach(boardVO.getBno());
		String[] save_file_names = new String[files.length];
		String[] real_file_names = new String[files.length];
		int idx = 0; // jsp폼에서 보내온 파일에 대한 인덱스 초기값 변수.
		for(MultipartFile file:files) {
			if(file.getOriginalFilename() != "") { // 전송된 첨부파일이 있다면 실행
				int sun = 0; // DB테이블에 저장된 순서에 대한 인덱스 초기값 변수
				// jsp에서 기존에 1번 위치에 기존파일이 있으면 기존 파일을 지우고 신규파일 업로드
				for(AttachVO file_name:delFiles) { // 기존 파일을 가져와서 반복
					if(idx == sun) {
						File target = new File(commonUtil.getUploadPath(),file_name.getSave_file_name());
						if(target.exists()) {
							target.delete();
							boardDAO.deleteAttach(file_name.getSave_file_name());
						}
					}
					sun++;
				} 
				save_file_names[idx] = commonUtil.fileUpload(file); // jsp에서 전송파일
				real_file_names[idx] = file.getOriginalFilename(); //UI용 이름 임시저장
			} else {
				save_file_names[idx] = null;
				real_file_names[idx] = null;
			}
			idx++;
		}// for 종료
		boardVO.setSave_file_names(save_file_names);
		boardVO.setReal_file_names(real_file_names);
		// 시큐어 코딩 추가
		String rawContent = boardVO.getContent();
		String secContent = commonUtil.unscript(rawContent);
		boardVO.setContent(secContent);
		
		String rawTitle = boardVO.getTitle();
		String secTitle = commonUtil.unscript(rawTitle);
		boardVO.setTitle(secTitle);
		
		boardService.updateBoard(boardVO);
		
		String qString = "bno="+boardVO.getBno()+"&page="+pageVO.getPage()+"&search_type="+pageVO.getSearch_type();
		return "redirect:/admin/board/board_view?"+qString;
	}
	// 게시물 수정폼은 URL로 접근
	@RequestMapping(value="/admin/board/board_update_form", method=RequestMethod.GET)
	public String board_update_form(Model model,@ModelAttribute("pageVO")PageVO pageVO, @RequestParam("bno")Integer bno) throws Exception{
		// 첨부파일용 save_file_names, real_file_names 배열값을 구해서 boardVO입력
		BoardVO boardVO = new BoardVO();
		boardVO = boardService.readBoard(bno);
		// 첨부파일 배열 추가
		List<AttachVO> listAttach = boardService.readAttach(bno);
		String[] save_file_names = new String[listAttach.size()];
		String[] real_file_names = new String[listAttach.size()];
		int idx = 0;
		for(AttachVO file_name:listAttach) {
			save_file_names[idx] = file_name.getSave_file_name();
			real_file_names[idx] = file_name.getReal_file_name();
			idx += 1;
		}
		boardVO.setSave_file_names(save_file_names);
		boardVO.setReal_file_names(real_file_names);
		model.addAttribute("boardVO", boardVO);
		return "admin/board/board_update";
	}
	// 게시물 삭제는 POST방식
	@RequestMapping(value="/admin/board/board_delete", method=RequestMethod.POST)
	public String board_delete(@RequestParam("bno")Integer bno, PageVO pageVO) throws Exception {
		logger.info("디버그 전역업로드 경로 : " + commonUtil.getUploadPath());
		// 첨부파일이 있는 경우, 먼저 삭제해주는 코드
		// 기존 등록된 첨부파일 폴더에서 삭제할 UUID(고유식별값)이름을 추출.
		List<AttachVO> delFiles = boardService.readAttach(bno);
		boardService.deleteBoard(bno);
		// 물리적 파일삭제 처리
		for(AttachVO file_name:delFiles) {
			// File클래스는 파일위치, 삭제할 파일명
			File target = new File(commonUtil.getUploadPath(), file_name.getSave_file_name());
				if(target.exists()) {
					target.delete();
				}
		}
		String qString = "page="+pageVO.getPage()+"&search_type="+pageVO.getSearch_type();
		return "redirect:/admin/board/board_list?"+qString;
	}
	@RequestMapping(value="/admin/board/board_view", method=RequestMethod.GET)
	public String board_view(@RequestParam("bno")Integer bno,@ModelAttribute("pageVO")PageVO pageVO, Model model) throws Exception {
		BoardVO boardVO = boardService.readBoard(bno);
		
		// 첨부파일 데이터
		List<AttachVO> files = boardService.readAttach(bno);
		String[] save_file_names = new String[files.size()];
		String[] real_file_names = new String[files.size()];
		int cnt = 0;
		// Attach 테이블 안의 해당 bno 게시물의 첨부파일 이름을 파싱
		for(AttachVO file_name:files) {
			save_file_names[cnt] = file_name.getSave_file_name();
			real_file_names[cnt] = file_name.getReal_file_name();
			cnt += 1;
		}
		boardVO.setSave_file_names(save_file_names);
		boardVO.setReal_file_names(real_file_names);
		model.addAttribute("boardVO", boardVO);
		// 업로드한 데이터가 이미지인지 아닌지 체크
		model.addAttribute("checkImgArray", commonUtil.getCheckImgArray());
		return "admin/board/board_view";
	}
	// 게시물 목록은 폼으로 접근하지 않고 URL로 접근하기 때문에 GET방식으로 메소드 설정.
	@RequestMapping(value="/admin/board/board_list", method=RequestMethod.GET)
	public String board_list(@ModelAttribute("pageVO")PageVO pageVO, Model model) throws Exception{
		// 게시판 타입이 null일때 기본값으로 notice를 추가
		if(pageVO.getBoard_type() == null) {
			pageVO.setBoard_type("notice");
		}
		// 페이징 처리를 위한 기본값 생성
		if(pageVO.getPage() == null) {
			pageVO.setPage(1);
		}
		pageVO.setPerPageNum(5);
		pageVO.setQueryPerPageNum(5);
		pageVO.setTotalCount(boardService.countBoard(pageVO));
		model.addAttribute("listBoardVO", boardService.selectBoard(pageVO));
		return "admin/board/board_list";
	}
	
	
	// ******************** 게시판 생성관리 **********************
	// jsp에서 게시판 생성관리에 Get/Post 접근할때 URL을 bbs_type으로 지정한다.
	@RequestMapping(value="/admin/bbs_type/bbs_type_list", method=RequestMethod.GET)
	public String selectBoardTypeList(Model model) throws Exception{ // 목록
		//model.addAttribute("listBoardTypeVO", boardTypeService.selectBoardType());
		return "admin/bbs_type/bbs_type_list";
	}
	
	@RequestMapping(value="/admin/bbs_type/bbs_type_insert", method=RequestMethod.GET)
	public String inserBoardTypeForm() throws Exception{ // 입력폼
		return "admin/bbs_type/bbs_type_insert";
	}
	@RequestMapping(value="/admin/bbs_type/bbs_type_insert", method=RequestMethod.POST)
	public String inserBoardType(BoardTypeVO boardTypeVO) throws Exception{ // 입력처리
		// 위 insert에서 넘어온 값을 boardTypeVO에 자동으로 담긴다. 단, 폼name과 VO 멤버변수명이 동일해야한다.
		boardTypeService.insertBoardType(boardTypeVO);
		return "redirect:/admin/bbs_type/bbs_type_list";
	}
	// 게시판 생성관리는 사용자단에서 사용하지 않기 때문에  Read, Update를 같이 구현
	@RequestMapping(value="/admin/bbs_type/bbs_type_update", method=RequestMethod.GET)
	public String updateBoardTypeForm(@RequestParam("board_type")String board_type, Model model) throws Exception{ // 수정폼
		model.addAttribute("boardTypeVO",boardTypeService.readBoardType(board_type));
		return "admin/bbs_type/bbs_type_update";
	}
	@RequestMapping(value="/admin/bbs_type/bbs_type_update", method=RequestMethod.POST)
	public String updateBoardType(BoardTypeVO boardTypeVO) throws Exception{ // 수정처리
		boardTypeService.updateBoardType(boardTypeVO);
		return "redirect:/admin/bbs_type/bbs_type_update?board_type="+boardTypeVO.getBoard_type();
	}
	@RequestMapping(value="/admin/bbs_type/bbs_type_delete", method=RequestMethod.POST)
	public String deleteBoardType(@RequestParam("board_type")String board_type) throws Exception{ // 삭제처리
		boardTypeService.deleteBoardType(board_type);
		return "redirect:/admin/bbs_type/bbs_type_list";
	}
	
	
	// *********************** 회원관리 ****************************
	// 회원 신규등록 처리하는 서비스를 호출하는 URL
	@RequestMapping(value="/admin/member/member_insert", method=RequestMethod.POST)
	public String insertMember(HttpServletRequest request,MultipartFile file, MemberVO memberVO, PageVO pageVO) throws Exception{
		if(!file.getOriginalFilename().isEmpty()) {
			commonUtil.profile_upload(memberVO.getUser_id(), request, file);
		}
		BCryptPasswordEncoder pwEncoder = new BCryptPasswordEncoder();
		String raw_pw = memberVO.getUser_pw();
		String encPw = pwEncoder.encode(raw_pw);
		memberVO.setUser_pw(encPw);
		memberService.insertMember(memberVO);
		return "redirect:/admin/member/member_list";
	}
	// 회원 신규등록
	@RequestMapping(value="/admin/member/member_insert_form", method=RequestMethod.GET)
	public String insertMemberForm(@ModelAttribute("pageVO")PageVO pageVO) throws Exception{
	
		return "admin/member/member_insert";
	}
	
	
	// 수정처리를 호출 = DB변경처리
	@RequestMapping(value="/admin/member/member_update", method=RequestMethod.POST)
	public String updateMember(MultipartFile file, HttpServletRequest request, MemberVO memberVO, PageVO pageVO) throws Exception{
		//프로필 이미지 처리
		if(!file.getOriginalFilename().isEmpty()) {
			String user_id = memberVO.getUser_id();
			commonUtil.profile_upload(user_id, request, file);
		}
		// 수정처리 이후 본인페이지에 있습니다.
		// 업데이트 쿼리서비스 호출하기 전 스프링시큐리티 암호화 적용합니다.
		String rawPw = memberVO.getUser_pw();
		if(!rawPw.isEmpty()) { //수정폼에서 암호 입력값이 있을때만 해당 코드 실행.
			BCryptPasswordEncoder pwEncoder = new BCryptPasswordEncoder();
			String encPw = pwEncoder.encode(rawPw);
			memberVO.setUser_pw(encPw);
		}
		memberService.updateMember(memberVO);
		String qString = "user_id="+memberVO.getUser_id()+"&page="+pageVO.getPage()+"&search_type="+pageVO.getSearch_type();
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
	public String deleteMember(HttpServletRequest request, MemberVO memberVO) throws Exception {
		logger.info("디버그: " + memberVO.toString());
		//MemberVO memberVO는 클래스형 변수: String user_id 스트링형 변수 같은 방식.
		String user_id = memberVO.getUser_id();
		//이 메서드는 회원상세보기페이지에서 삭제버튼을 클릭시 전송받은 memberVO값을 이용해서 삭제를 구현(아래)
		memberService.deleteMember(user_id);
		//DB레코드 삭제 후 프로필 이미지가 있으면 삭제
		commonUtil.profile_delete(user_id, request); //경로때문에 request 추가
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
		PageVO pageVO = new PageVO();
		pageVO.setQueryPerPageNum(4);
		pageVO.setPage(1);
		List<MemberVO> latestMembers = memberService.selectMember(pageVO);
		model.addAttribute("latestMembers", latestMembers);
		return "admin/home"; //리턴 경로 = 접근경로는 반드시 상대 경로로 표시(views가 최상위 폴더)
		// prefix(/WEB-INF/views/) suffix(.jsp)
	}
	//메인페이지 또는 대시보드에 최신 테이블리스트를 출력하는 방법 2가지(위, model사용
	//아래, @import방식 : 최신 게시물 용도로 사용/ 페이지 안에서 컴파일된 다른 페이지를 불러올 수 있다.
	@RequestMapping(value="/admin/latest/latest_board", method=RequestMethod.GET)
	public String latest_board(Model model, @RequestParam(value="board_type", required=false) String board_type, @RequestParam(value="board_name", required=false) String board_name) throws Exception{
		PageVO pageVO = new PageVO();
		pageVO.setQueryPerPageNum(5);
		pageVO.setPage(1);
		pageVO.setBoard_type(board_type);
		List<BoardVO> latestBoard = boardService.selectBoard(pageVO);
		model.addAttribute("board_name", board_name);
		model.addAttribute("board_type", board_type);
		model.addAttribute("latestBoard", latestBoard);
		return "admin/latest/latest_board";
	}
}
