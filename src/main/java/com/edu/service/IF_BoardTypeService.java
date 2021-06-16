package com.edu.service;

import java.util.List;

import com.edu.vo.BoardTypeVO;

/**
 * DAO클래스에 접근하는 인터페이스
 * @author 은비
 *
 */
public interface IF_BoardTypeService {
	public List<BoardTypeVO> selectBoardType() throws Exception;
	public void insertBoardType(BoardTypeVO boardTypeVO) throws Exception;
	public BoardTypeVO readBoardType(String board_type) throws Exception;
	public void updateBoardType(BoardTypeVO boardTypeVO) throws Exception;
	public void deleteBoardType(String board_type) throws Exception;
}
