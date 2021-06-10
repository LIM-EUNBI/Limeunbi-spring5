package com.edu.service;

import java.util.List;

import javax.inject.Inject;

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
