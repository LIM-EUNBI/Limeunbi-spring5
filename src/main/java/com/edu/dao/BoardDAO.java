package com.edu.dao;
/**
 * 게시물 CRUD를 구현하는 DAO 클래스
 * @author 은비
 */
import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.edu.vo.AttachVO;
import com.edu.vo.BoardVO;
import com.edu.vo.PageVO;

@Repository
public class BoardDAO implements IF_BoardDAO{
	private Logger logger = LoggerFactory.getLogger(BoardDAO.class);
	@Inject
	private SqlSession sqlSession; //sql세션템플릿에는 insert, select, update 등 DML문


	@Override
	public List<BoardVO> selectBoard(PageVO pageVO) throws Exception {
		return sqlSession.selectList("boardMapper.selectBoard", pageVO);
	}

	@Override
	public int insertBoard(BoardVO boardVO) throws Exception {
		// 반환값으로 bno를 받는다.
		sqlSession.insert("boardMapper.insertBoard", boardVO);
		logger.info("디버그: " + boardVO.getBno());
		return boardVO.getBno();
	}

	@Override
	public BoardVO readBoard(int bno) throws Exception {
		return sqlSession.selectOne("boardMapper.readBoard", bno);
	}

	@Override
	public void updateBoard(BoardVO boardVO) throws Exception {
		sqlSession.update("boardMapper.updateBoard", boardVO);
		
	}

	@Override
	public void deleteBoard(int bno) throws Exception {
		sqlSession.delete("boardMapper.deleteBoard", bno);
		
	}

	@Override
	public int countBoard(PageVO pageVO) throws Exception {
		return sqlSession.selectOne("boardMapper.countBoard", pageVO);
	}

	@Override
	public void updateViewCount(Integer bno) throws Exception {
		sqlSession.selectOne("boardMapper.updateViewCount", bno);
	}

	@Override
	public List<AttachVO> readAttach(Integer bno) throws Exception {
		return sqlSession.selectList("boardMapper.readAttach", bno);
	}

	@Override
	public void insertAttach(AttachVO attachVO) throws Exception {
		sqlSession.insert("boardMapper.insertAttach", attachVO);
		
	}

	@Override
	public void updateAttach(AttachVO attachVO) throws Exception {
		sqlSession.insert("boardMapper.updateAttach", attachVO);
		
	}

	@Override
	public void deleteAttach(String save_file_name) throws Exception {
		sqlSession.delete("boardMapper.deleteAttach", save_file_name);
		
	}

	@Override
	public void deleteAttachAll(Integer bno) throws Exception {
		sqlSession.delete("boardMapper.deleteAttachAll", bno);
	}

}
