package com.edu.dao;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.edu.vo.BoardTypeVO;

/**
 * 게시판 생성관리 인터페이스를 구현하는 클래스
 * sqlsession 템플릿을 통해 매퍼쿼리를 실행
 * @author 은비
 *
 */
@Repository
public class BoardTypeDAO implements IF_BoardTypeDAO {
	@Inject //자바8부터 사용 가능한 어노테이션
	private SqlSession sqlSession;

	@Override
	public List<BoardTypeVO> selectBoardType() throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectList("boardTypeMapper.selectBoardType");
	}

	@Override
	public void insertBoardType(BoardTypeVO boardTypeVO) throws Exception {
		// TODO Auto-generated method stub
		sqlSession.insert("boardTypeMapper.inserBoardType", boardTypeVO);
		
	}

	@Override
	public BoardTypeVO readBoardType(String board_type) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("boardTypeMapper.readBoardType", board_type);
	}

	@Override
	public void updateBoardType(BoardTypeVO boardTypeVO) throws Exception {
		// TODO Auto-generated method stub
		sqlSession.update("boardTypeMapper.updateBoardType", boardTypeVO);
	}

	@Override
	public void deleteBoardType(String board_type) throws Exception {
		// TODO Auto-generated method stub
		sqlSession.delete("boardTypeMapper.deleteBoardType", board_type);
	}
	
}
