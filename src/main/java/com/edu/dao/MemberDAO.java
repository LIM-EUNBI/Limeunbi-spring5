package com.edu.dao;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.edu.vo.MemberVO;
import com.edu.vo.PageVO;

/**
 * IF_MemberDAO 인터페이스를 구현하는 클래스
 * implements 키워드로 상속을 받는다.
 * @author 은비
 *
 */
@Repository // DAO기능의 구현클래스는 스프링빈 등록이 필요해서 @Repository 사용
public class MemberDAO implements IF_MemberDAO {
	@Inject
	private SqlSession sqlSession;
	
	
	@Override //부모 인터페이스의 메서드를 상속해서 재정의.
	public List<MemberVO> selectMember(PageVO pageVO) throws Exception {
		// Sqlsession의 메서드를 이용해서 mapper 쿼리 사용.
		List<MemberVO> listMember = sqlSession.selectList("memberMapper.selectMember", pageVO);
		return listMember;
	}


	@Override // 전체 회원의 카운터
	public int countMember() throws Exception {
		// 하나의 값을 뽑아올때 selectOne을 사용.
		int totalCount = sqlSession.selectOne("memberMapper.countMember");
		return totalCount;
	}


	@Override
	public void insertMember(MemberVO memberVO) throws Exception {
		// sqlSession빈의 메서드를 이용해서 매퍼 쿼리를 실행.
		sqlSession.insert("memberMapper.insertMember", memberVO);
		
	}


	@Override
	public void deleteMember(String user_id) throws Exception {
		sqlSession.delete("memberMapper.deleteMember", user_id);
		
	}
	

}
