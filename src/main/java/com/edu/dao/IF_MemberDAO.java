package com.edu.dao;

import java.util.List;

import com.edu.vo.MemberVO;

/**
 * 회원관리 CRUD 메서드 명세가 포함된 인터페이스
 * @author 은비
 *
 */
public interface IF_MemberDAO {
	public List<MemberVO> selectMember() throws Exception;
}
