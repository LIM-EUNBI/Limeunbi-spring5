package com.edu.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.edu.dao.IF_MemberDAO;
import com.edu.vo.MemberVO;

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
	
	@Override
	public List<MemberVO> selectMember() throws Exception {
		// 인터페이스에서 상속받은 메서드를 구현
		
		return memberDAO.selectMember();
	}

}
