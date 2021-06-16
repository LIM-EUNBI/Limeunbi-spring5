package com.edu.service;
/**
 * DAO클래스에 접근하기 위한 서비스 클래스
 * @author 은비
 */
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.edu.dao.IF_BoardTypeDAO;
import com.edu.vo.BoardTypeVO;

@Service // 스프링빈으로 등록
public class BoardTypeService implements IF_BoardTypeService{
	@Inject
	private IF_BoardTypeDAO boardTypeDAO;
	
	@Override
	public List<BoardTypeVO> selectBoardType() throws Exception {
		// TODO DAO클래스 객체를 이용해서 메서드를 호출
		return boardTypeDAO.selectBoardType();
	}

	@Override
	public void insertBoardType(BoardTypeVO boardTypeVO) throws Exception {
		// TODO DAO클래스 객체를 이용해서 메서드를 호출
		boardTypeDAO.insertBoardType(boardTypeVO);
	}

	@Override
	public BoardTypeVO readBoardType(String board_type) throws Exception {
		// TODO DAO클래스 객체를 이용해서 메서드를 호출
		return boardTypeDAO.readBoardType(board_type);
	}

	@Override
	public void updateBoardType(BoardTypeVO boardTypeVO) throws Exception {
		// TODO DAO클래스 객체를 이용해서 메서드를 호출
		boardTypeDAO.updateBoardType(boardTypeVO); 
	}

	@Override
	public void deleteBoardType(String board_type) throws Exception {
		// TODO DAO클래스 객체를 이용해서 메서드를 호출
		boardTypeDAO.deleteBoardType(board_type);
	}

}
