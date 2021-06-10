package com.edu.service;

import java.util.List;

import com.edu.vo.MemberVO;
import com.edu.vo.PageVO;

/**
 * 회원관리 관련된 Service의 명세를 모아놓은 인터페이스
 * @author 은비
 *
 */

public interface IF_MemberService {
	public List<MemberVO> selectMember(PageVO pageVO) throws Exception;
	
	public int countMember(PageVO pageVO) throws Exception;

	public void insertMember(MemberVO memberVO) throws Exception;

	public void deleteMember(String user_id) throws Exception;

	public MemberVO readMember(String user_id) throws Exception;

	public void updateMember(MemberVO memberOne) throws Exception;


}
