package com.edu.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.edu.dao.IF_BoardDAO;
import com.edu.vo.AttachVO;
import com.edu.vo.BoardVO;
import com.edu.vo.PageVO;

/**
 * DAO메서드를 호출하는 클래스
 * @author PC User
 *
 */

@Service
public class BoardService implements IF_BoardService{
	@Inject
	private IF_BoardDAO boardDAO;
	
	
	@Override
	public List<BoardVO> selectBoard(PageVO pageVO) throws Exception {
		// 1개호출
		return boardDAO.selectBoard(pageVO);
	}

	@Transactional
	@Override
	public void insertBoard(BoardVO boardVO) throws Exception {
		// 첨부파일 있으면 첨부파일 insertAttach -> 게시물 insertBoard 실행
		int bno = boardDAO.insertBoard(boardVO);
		// 첨부파일이 1개 이상일 때
		String[] save_file_names = boardVO.getSave_file_names(); // 폴더에 저장용 파일명
		String[] real_file_names = boardVO.getReal_file_names(); // UI용 배열 파일명
		if(save_file_names == null) { return;}
		
		int index = 0;
		String real_file_name = ""; // UI용 1개 파일명
		AttachVO attachVO = new AttachVO();
		for(String save_file_name : save_file_names) { // 첨부파일 갯수만큼 반복
			if(save_file_name != null) {
			real_file_name = real_file_names[index];
			attachVO.setBno(bno);
			attachVO.setReal_file_name(real_file_name);
			attachVO.setSave_file_name(save_file_name);
			boardDAO.insertAttach(attachVO);
			}
			index++;
		}
	}
	
	@Transactional
	@Override
	public BoardVO readBoard(int bno) throws Exception {
		// 게시물 상세보기 readBoard + updateViewCount 2개의 메서드가 필요
		BoardVO boardVO = boardDAO.readBoard(bno);
		boardDAO.updateViewCount(bno);
		return boardVO;
	}

	@Transactional
	@Override
	public void updateBoard(BoardVO boardVO) throws Exception {
		// 첨부파일 처리 포함 게시물 업데이트 2개의 메서드 실행이 필요
		boardDAO.updateBoard(boardVO);
		// 첨부파일 DB처리
		int bno = boardVO.getBno();
		String[] save_file_names = boardVO.getSave_file_names();
		String[] real_file_names = boardVO.getReal_file_names();
		if(save_file_names == null) { return; }
		AttachVO attachVO = new AttachVO();
		int index = 0;
		String real_file_name = "";
		for(String save_file_name : save_file_names) {
			if(save_file_name != null) { // null이 들어가지 않게 하기 위해서 추가
			real_file_name = real_file_names[index];
			attachVO.setBno(bno);
			attachVO.setSave_file_name(save_file_name);
			attachVO.setReal_file_name(real_file_name);
			boardDAO.updateAttach(attachVO);
			}
			index++;
		}
	}
	
	@Transactional
	@Override
	public void deleteBoard(int bno) throws Exception {
		// 3개의 메서드가 실행(댓글, 첨부파일 삭제 후 게시물 삭제)
		// 트랜잭션 : 첨부파일 삭제 후 게시물 삭제하려는데 에러가 떠서 삭제가 안될 때 
		// 이와같은 상황을 방지하는 목적의 기능으로 @Transactional 어노테이션을 사용
		boardDAO.deleteAttachAll(bno);
		boardDAO.deleteBoard(bno);
	}

	@Override
	public int countBoard(PageVO pageVO) throws Exception {
		// TODO 페이징 처리시 PageVO의 totalCount 변수에 사용될 값을 리턴값으로 받는다.
		return boardDAO.countBoard(pageVO);
	}

	@Override
	public List<AttachVO> readAttach(Integer bno) throws Exception {
		return boardDAO.readAttach(bno);
	}

}
