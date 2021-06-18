package com.edu.dao;

import java.util.List;

import com.edu.vo.AttachVO;
import com.edu.vo.BoardVO;
import com.edu.vo.PageVO;

/**
 * boardMapper.xml을 접근하기 위한 인터페이스
 * @author 은비
 *
 */

public interface IF_BoardDAO {
	// 게시물 CRUD
	public List<BoardVO> selectBoard(PageVO pageVO) throws Exception;
	public int insertBoard(BoardVO boardVO) throws Exception;
	public BoardVO readBoard(int bno) throws Exception;
	public void updateBoard(BoardVO boardVO) throws Exception;
	public void deleteBoard(int bno) throws Exception;
	public int countBoard(PageVO pageVO) throws Exception; // 페이징 없이 검색된 게시물 갯수
	public void updateViewCount(Integer bno) throws Exception; // 조회수
	// 첨부파일 CRUD
	public List<AttachVO> readAttach(Integer bno) throws Exception;
	public void insertAttach(AttachVO attachVO) throws Exception;
	public void updateAttach(AttachVO attachVO) throws Exception;
	public void deleteAttach(String save_file_name) throws Exception;
	public void deleteAttachAll(Integer bno) throws Exception;
}
