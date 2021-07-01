package com.edu.controller;

import java.util.Collection;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.edu.service.IF_MemberService;
import com.edu.vo.MemberVO;

/**
 * 스프링 시큐리티의 /login처리한 결과를 받아서 
 *  /login_success를 처리하는 클래스
 * @author 은비
 *
 */

@Controller
public class LoginController {
	@Inject
	private IF_MemberService memberService;
	
	@RequestMapping(value="/login_success", method=RequestMethod.GET)
	public String login_success(HttpServletRequest request, RedirectAttributes rdat) throws Exception{
		//request는 세션객체를 만들기 위해서(로그인 정보 유지)
		//rdat는 모델로 값을 전송할 수 없을때 사용. 
		Authentication auth = SecurityContextHolder.getContext().getAuthentication(); //ID, 암호 비교쿼리가 실행된 결과가 저장
		String userid = "";
		String levels = "";
		Boolean enabled = false;
		//principal객체는 UserDatails객체에 포함되어 있고, enabled라는 인증결과가 생성.
		Object principal = auth.getPrincipal();
		if(principal instanceof UserDetails) {
			enabled = ((UserDetails) principal).isEnabled();//true, false
			//위 인증 결과로 로그인 체크를 한다.
		}
		if(enabled) {//로그인 인증값이 true라면, 세션값-로그인ID, 권한, 회원이름 저장
			HttpSession session = request.getSession();
			Collection<? extends GrantedAuthority> authorities = auth.getAuthorities();
			//authorities에는 {"ROLE_ANONYMOUS","ROLE_USER","ROLE_ADMIN"}
			if(authorities.stream().filter(o -> o.getAuthority().equals("ROLE_ANONYMOUS")).findAny().isPresent())
			{
				levels = "ROLE_ANONYMOUS"; //로그인하지 않은 사용자
			}
			if(authorities.stream().filter(o -> o.getAuthority().equals("ROLE_USER")).findAny().isPresent())
			{
				levels = "ROLE_USER"; //로그인하지 않은 사용자
			}
			if(authorities.stream().filter(o -> o.getAuthority().equals("ROLE_ADMIN")).findAny().isPresent())
			{
				levels = "ROLE_ADMIN"; //로그인하지 않은 사용자
			}
			userid = ((UserDetails) principal).getUsername();
			//위에서 구한 변수 3개 enabled, levels, userid를 세션으로 저장
			session.setAttribute("session_enabled", enabled);
			session.setAttribute("session_levels", levels);
			session.setAttribute("session_userid", userid);
			MemberVO memberVO = memberService.readMember(userid);
			session.setAttribute("session_username", memberVO.getUser_name());
		}
		rdat.addFlashAttribute("msg", "로그인"); //로그인 성공여부를 jsp페이지로 보내주는 변수 생성
		return "redirect:/";
	}
}


