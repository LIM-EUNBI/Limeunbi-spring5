package com.edu.service;

import java.util.List;

import com.edu.vo.AttachVO;
import com.edu.vo.BoardVO;
import com.edu.vo.PageVO;

/**
 * DAO를 호출하는 서비스
 * @author 은비
 *
 */
public interface IF_BoardService {
	// 게시물 CRUD
	public List<BoardVO> selectBoard(PageVO pageVO) throws Exception;
	public void insertBoard(BoardVO boardVO) throws Exception;
	public BoardVO readBoard(int bno) throws Exception;
	public void updateBoard(BoardVO boardVO) throws Exception;
	public void deleteBoard(int bno) throws Exception;
	public int countBoard(PageVO pageVO) throws Exception; // 페이징 없이 검색된 게시물 갯수
	// 첨부파일 CRUD
	public List<AttachVO> readAttach(Integer bno) throws Exception;
}
