package com.edu.service;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.edu.dao.IF_MemberDAO;
import com.edu.vo.MemberVO;
import com.edu.vo.PageVO;

/**
 * 회원관리 서비스 인터페이스를 구현하는 클래스
 * 스프링 빈으로 등록하려면 @Service 명시
 * @author 임은비
 *
 */
@Service
public class MemberService implements IF_MemberService {
	@Inject
	private IF_MemberDAO memberDAO;

	Logger logger = LoggerFactory.getLogger(MemberService.class);
	
	//헤로쿠 클라우드에 30분 휴면상태를 깨우는 기능 추가
	public void herokuJobMethod() throws Exception{
		//우리나라 월-금 오전 8-23시까지(미국시간 0-14) 헤로쿠앱에 20분 간격으로 접근
		String urlStr = "https://Limeunbi-spring5.herokuapp.com/";
		URL url = new URL(urlStr);
		HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();//접속객체 만들기
		urlConnection.setUseCaches(false); //접속 시 캐시사용없이 무조건 새로고침
		urlConnection.setReadTimeout(10000); //접속 대기시간을 10초로 제한
		//20분마다 접속이 되는지 개발자가 확인하는 코드
		if(urlConnection != null && urlConnection.getResponseCode()==HttpURLConnection.HTTP_OK) {
			logger.info("헤로쿠 앱이 활성화 상태입니다.");
		}else {
			logger.info("헤로쿠 앱이 비활성화 상태입니다.");
		}
	}
	
	
	@Override
	public List<MemberVO> selectMember(PageVO pageVO) throws Exception {
		// 인터페이스에서 상속받은 메서드를 구현
		
		return memberDAO.selectMember(pageVO);
	}

	@Override
	public int countMember(PageVO pageVO) throws Exception {
		return memberDAO.countMember(pageVO);
	}

	@Override
	public void insertMember(MemberVO memberVO) throws Exception {
		// 클래스 상단에서 인젝션으로 주입받은 DAO객체를 사용.
		memberDAO.insertMember(memberVO);
		
	}

	@Override
	public void deleteMember(String user_id) throws Exception {
		// 클래스 상단에서 인젝션으로 주입받은 DAO객체를 사용.
		memberDAO.deleteMember(user_id);		
	}

	@Override
	public MemberVO readMember(String user_id) throws Exception {
		return memberDAO.readMember(user_id);
	}

	@Override
	public void updateMember(MemberVO memberOne) throws Exception {
		// DAO호출
		memberDAO.updateMember(memberOne);
		
	}

}
