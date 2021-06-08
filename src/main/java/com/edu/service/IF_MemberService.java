package com.edu.service;

import java.util.List;

import com.edu.vo.MemberVO;

/**
 * 회원관리 관련된 Service의 명세를 모아놓은 인터페이스
 * @author 은비
 *
 */

public interface IF_MemberService {
	public List<MemberVO> selectMember() throws Exception;
}
