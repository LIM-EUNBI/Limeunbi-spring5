package com.edu.controller;
/**
 * 네이버RESTAPI서버 URL을 생성하는 기능의 클래스
 * @author 은비
 */

import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;

import com.edu.util.NaverLoginApi;
import com.github.scribejava.core.builder.ServiceBuilder;
import com.github.scribejava.core.oauth.OAuth20Service;

//로컬과 헤로쿠의 인증 ID와 secret를 소스에서 변경하는것보다 전역변수로 만드는게 편하기 때문에.
@PropertySource("classpath:properties/sns.properties")
@Controller
public class NaverLoginController {
	@Value("${SnsClientID}") //스프링 빈의 전역변수를 가져올 때, @Resource
	private String CLIENT_ID;
	@Value("${SnsClientSecret}")
	private String CLIENT_SECRET;
	@Value("${SnsCallbackUrl}")
	private String REDIRECT_URL; //인증 성공 후 이동할 URL
	//아래는 네이버에서 지정한 상수값을 사용해야 한다.
	private final static String SESSION_STATE = "oauth_state";
	// 네이버 이름+이메일 정보등을 가져올수 있는 RestAPI 
	private final static String PROFILE_API_URL = "https://openapi.naver.com/v1/nid/me"; 
	//네이버에서 제공하는 인증 URL구하는 메서드(사용자 로그인폼에 $url로 제공)
	public String getAuthorizationUrl(HttpSession session) {
		// 세션에 유효성 검증을 위하여 난수를 생성
		String state = generateRandomString();
		//생성한 난수 state값을 session변수에 저장
		setSession(session,state);
		//Scribe가입을 담당하는 외부모듈에서 제공하는 인증 URL생성
		OAuth20Service oauthService = new ServiceBuilder().apiKey(CLIENT_ID).apiSecret(CLIENT_SECRET)
				.callback(REDIRECT_URL).state(state).build(NaverLoginApi.instance());
		return oauthService.getAuthorizationUrl();
	}
	private String generateRandomString() {

		return UUID.randomUUID().toString();
	}
	private void setSession(HttpSession session, String state) {
		// httpSession 클래스에 데이터를 저장
		session.setAttribute(SESSION_STATE, state);
	}
}
