package com.edu.dao;

import java.util.List;

import com.edu.vo.BoardTypeVO;

/**
 * 게시판 생성관리 쿼리를 접근하기 위한 인터페이스
 * @author 은비
 *
 */
public interface IF_BoardTypeDAO {
	public List<BoardTypeVO> selectBoardType() throws Exception;
	public void insertBoardType(BoardTypeVO boardTypeVO) throws Exception;
	public BoardTypeVO readBoardType(String board_type) throws Exception;
	public void updateBoardType(BoardTypeVO boardTypeVO) throws Exception;
	public void deleteBoardType(String board_type) throws Exception;
}
