package com.edu.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.edu.service.IF_MemberService;
import com.edu.vo.MemberVO;
import com.github.scribejava.core.model.OAuth2AccessToken;

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
	@Inject
	private NaverLoginController naverLoginController;
	
	//네이버 인증체크 후 이동할 URL = 콜백 URL
	@RequestMapping(value="/naver_callback", method={RequestMethod.GET, RequestMethod.POST})
	public String login_callback(@RequestParam(required=false) String code, @RequestParam String state, HttpSession session, Model model, RedirectAttributes rdat) throws IOException, ParseException{
		//로그인 취소 버튼을 눌렀을 때,
		if(code == null) {
			rdat.addFlashAttribute("msgError", "네이버 로그인을 취소하였습니다.");
			return "redirect:/login";
		}
		OAuth2AccessToken oauthToken; //인증에 사용되는 토큰 객체 생성.
		oauthToken = naverLoginController.getAccessToken(session, code, state);
		//네이버로 로그인 한 사용자 profile을 가져온다.
		String profile = naverLoginController.getUserProfile(oauthToken);
		//위 정보를 json데이터로 파싱
		JSONParser parser = new JSONParser();
		Object obj = parser.parse(profile);
		JSONObject jsonObj = (JSONObject) obj;
		JSONObject response_obj = (JSONObject) jsonObj.get("response");
		String status = (String) jsonObj.get("message"); //인증 성공여부 확인 
		//최종적으로 출력된 response_obj를 파싱
		String username = (String) response_obj.get("name");
		String useremail = (String) response_obj.get("email");
		if(status.equals("success")) { //네이버 인증처리 결과가 success면
			//인증성공 후 스프링 시큐리티의 ROLE_USER권한을 받아야 insert, member, update, delete에 접근 가능
			List<SimpleGrantedAuthority> authorities = new ArrayList<>();
			authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
			Authentication authentication = new UsernamePasswordAuthenticationToken(useremail, null, authorities);
			SecurityContextHolder.getContext().setAuthentication(authentication); //위에서 발생한 인증토큰을 시큐리티 클래스에 저장
			session.setAttribute("session_enabled", true);
			session.setAttribute("session_userid", useremail);
			session.setAttribute("session_levels", "ROLE_USER");
			session.setAttribute("session_username", username);
			session.setAttribute("session_login_type", "sns"); //마이페이지 안보이게 처리
			rdat.addFlashAttribute("msg", "네이버 로그인"); //출력결과 : 네이버 아이디
		}
		else {
			rdat.addFlashAttribute("msgError", "네이버 로그인 실패하였습니다.");
			return "redirect:/login_form";
		}
		return "redirect:/"; //로그인 성공했다면 /로 이동
	}

	//HomeController에 있던 /login_form을 네이버 로그인 URL 생성때문에 여기로 이동
	@RequestMapping(value="/login_form", method=RequestMethod.GET)
	public String login_form(Model model, HttpSession session) throws Exception{
		//세션 : 서버에 클라이언트 접속정보를 저장하는 공간
		String url = "";
		url = naverLoginController.getAuthorizationUrl(session);
		model.addAttribute("url", url);
		return "home/login";
	}
	
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


