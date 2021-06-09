package com.edu.dao;

import java.util.List;

import com.edu.vo.MemberVO;
import com.edu.vo.PageVO;

/**
 * 회원관리 CRUD 메서드 명세가 포함된 인터페이스
 * @author 은비
 *
 */
public interface IF_MemberDAO {
	public List<MemberVO> selectMember(PageVO pageVO) throws Exception;
	
	// 회원의 전체 명수
	public int countMember() throws Exception;

	public void insertMember(MemberVO memberVO) throws Exception;

	public void deleteMember(String user_id) throws Exception;
}
