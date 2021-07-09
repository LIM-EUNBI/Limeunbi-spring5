package com.edu.aop;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.FlashMap;
import org.springframework.web.servlet.FlashMapManager;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.RequestContextUtils;

import com.edu.service.IF_BoardService;
import com.edu.service.IF_BoardTypeService;
import com.edu.vo.BoardTypeVO;
import com.edu.vo.BoardVO;
import com.edu.vo.PageVO;

/**
 * AOP기능 중 @Aspect와 @ControllerAdvice 구현한 클래스
 * @author 은비
 *
 */

// AOP기능 추가 = board_type값을 항상 가져가도록 처리.
// Aspect로 AOP를 구현할때는 포인트컷이 필요하다. (@Around)
@Aspect 
@ControllerAdvice
public class AspectAdvice {
	private Logger logger = LoggerFactory.getLogger(AspectAdvice.class);

	@Inject
	private IF_BoardTypeService boardTypeService;
	@Inject
	private IF_BoardService boardService;
	
	//본인글만 수정, 삭제하는 기능 구현(admin은 제외) (조인포인트 사용)
	@Around("execution(* com.edu.controller.HomeController.board_delete(..)) || execution(* com.edu.controller.HomeController.board_update*(..))")
	public Object check_role(ProceedingJoinPoint pjp) throws Throwable{
		//이전 페이지 url, 세션값을 가져오기 위해서 request 필요
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
		//사용할 변수 초기화
		String user_id = null; //게시물 작성자 ID
		BoardVO boardVO = null; //boardVO.getWriter()
		logger.info("디버그 호출된 메서드명은 : "+pjp.getSignature().getName());
		//향상된 for문을 이용해서 매개변수 중 bno, boardVO 2가지를 체크해서 user_id를 구한다.
		for(Object object: pjp.getArgs() ) {
			//메서드의 매개변수가 BoardVO, bno일때만 로직을 실행하기 위해서
			if(object instanceof BoardVO) {
				user_id = ((BoardVO) object).getWriter();
			}
			if(object instanceof Integer) {
				boardVO = boardService.readBoard((int) object);
				user_id = boardVO.getWriter();
			}
		}
		if(request != null) {
			HttpSession session = request.getSession();
			
			if(!user_id.equals(session.getAttribute("session_userid")) && "ROLE_USER".equals(session.getAttribute("session_levels"))) {
				FlashMap flashMap = new FlashMap();
				flashMap.put("msgError", "게시물은 본인글만 수정/삭제 가능합니다.");
				FlashMapManager flashMapManager = RequestContextUtils.getFlashMapManager(request);
				flashMapManager.saveOutputFlashMap(flashMap, request, null);
				
				return "redirect:"+request.getHeader("Referer");
			}
		}
		
		Object result = pjp.proceed(); //실제 조인포인트가 이코드에서 실행된다.
		return result;
	}
	// 컨트롤러에서 Exception이 발생했을 때, 에러 메세지를 개발자가 작성한 jsp화면에 뿌려주는 기능을 하는 메서드
	@ExceptionHandler(Exception.class)
	public ModelAndView errorModelAndView(Exception e, HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView();
		
		//이전페이지로 돌아가기용 버튼 생성
		String referer = request.getHeader("Referer"); //크롬>네트워크>파일>Referer>이전페이지 url 존재
		request.getSession().setAttribute("session_prevPage", referer); //prevPage 세션변수 사용
		
		modelAndView.addObject("exception", e);//jsp이름, 데이터 객체
		modelAndView.setViewName("home/error/error_spring");
		return modelAndView;
	}
	
	// @Before + @After = @Around (포인트컷 전,후 모든 메서드 ↓)------------------------------------------
	@Around("execution(* com.edu.controller.*Controller.*(..))") 
	// @Around는 콜백함수 매개변수로 조인포인트 객체를 필수로 받는다.
	public Object sessionManager(ProceedingJoinPoint pjp) throws Throwable {
		logger.info("디버그19:");
		// board_type 변수값을 세션에 저장
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
		// 일반적인 컨트롤러에서는 매개변수 HttpServletRequest를 사용가능
		// 컨트롤러 클래스에서 매개변수로 받을값(board_type) < pageVO
		PageVO pageVO = null;
		String board_type = null; // jsp에서 전송되는 값을 세션변수값 임시로 저장, 목적은 세션변수 발생조건으로 사용
		String search_keyword = null; // 한글 검색시 IE에서 400에러 발생
		// 조인포인트 리스트의 객체의 메서드를 뽑아낸다.
		for(Object object:pjp.getArgs()) {
			if(object instanceof PageVO) { //AOP실행 메서드 중 매개변수 PageVO pageVO 객체 판단
				// PageVO 사용하는 서비스에만 적용.
				pageVO = (PageVO) object;
				board_type = pageVO.getBoard_type();
				search_keyword = pageVO.getSearch_keyword();
			}
		}
			if(request != null) { // jsp에서 Get,Post 있을때
				// 세션값을 발생시킴
				HttpSession session = request.getSession(); //PC가 스프링 접근시 세션객체
				if(search_keyword != null) { // 검색어가 발생하면 최초로 세션을 만든다.
					session.setAttribute("session_search_keyword", search_keyword);
				}
				if(session.getAttribute("session_search_keyword") != null) {
					// 세션값이 있다면.
					search_keyword = (String)session.getAttribute("session_search_keyword");
					if(pageVO != null) { // Set할때 pageVO가 null이면 에러 발생하기 때문에 추가
						pageVO.setSearch_keyword(search_keyword);
					}
				}
				if(board_type != null) {
					session.setAttribute("session_board_type", board_type);
				}
				if(session.getAttribute("session_board_type") != null) {
					board_type = (String) session.getAttribute("session_board_type");
					if(pageVO != null) { // pageVO가 값이 있을때만 아래코드 실행
					pageVO.setBoard_type(board_type); }// 여기서 항상 값을 가져가도록 구현.
				}
			}
		logger.info("debug19: "+ board_type);
		// Aspect - 포인트컷(Around) - 조인포인트(메서드) - 매개변수로 구현한 결과를 return
		Object result = pjp.proceed();
		return result;
	}
	

	//ControllerAdvice를 이용해서 컨트롤러의 모든 메서드 호출 가능--------------------------------------------------
	@ModelAttribute("listBoardTypeVO")
	// 컨트롤러로 입출력되는 메서드가 실행 전에 값을 생성해서 모델객체에 담아서 jsp로 보내준다.
	public List<BoardTypeVO> listBoardTypeVO() throws Exception{
		return boardTypeService.selectBoardType();
	}
}
