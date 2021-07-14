package com.edu.util;

import com.github.scribejava.core.builder.api.DefaultApi20;

public class NaverLoginApi extends DefaultApi20{

	@Override
	public String getAccessTokenEndpoint() {
		// Endpoint는 네이버의 Rest-Api URL을 명시
		// 엑세스 체크용 토큰 구하기 RestAPI URL
		return "https://nid.naver.com/oauth2.0/token?grant_type=authorization_code";
	}

	@Override
	protected String getAuthorizationBaseUrl() {
		// 네이버 인증체크 하는 URL
		return "https://nid.naver.com/oauth2.0/authorize";
	}
	
	//싱글톤 인스턴스 객체를 생성하기 위해서 인스턴스 홀더 클래스를 생성
	private static class InstanceHolder {
		private static final NaverLoginApi INSTANCE = new NaverLoginApi();
	}
	public static NaverLoginApi instance() {
		// 싱글톤으로 객체 생성
		return InstanceHolder.INSTANCE;
	}

	 
}
