package com.edu.aop;

import java.util.List;

import javax.inject.Inject;

import org.aspectj.lang.annotation.Aspect;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.edu.service.IF_BoardTypeService;
import com.edu.vo.BoardTypeVO;

/**
 * AOP기능 중 @Aspect와 @ControllerAdvice 구현한 클래스
 * @author 은비
 *
 */

@Aspect
@ControllerAdvice
public class AspectAdvice {
	@Inject
	private IF_BoardTypeService boardTypeService;
	
	//ControllerAdvice를 이용해서 컨트롤러의 모든 메서드 호출 가능
	@ModelAttribute("listBoardTypeVO")
	// 컨트롤러로 입출력되는 메서드가 실행 전에 값을 생성해서 모델객체에 담아서 jsp로 보내준다.
	public List<BoardTypeVO> listBoardTypeVO() throws Exception{
		return boardTypeService.selectBoardType();
	}
}
