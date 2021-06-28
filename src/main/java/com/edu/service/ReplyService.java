package com.edu.service;
/**
 * 댓글 DAO의 CRUD를 실행하는 서비스 클래스
 * @author 은비
 */

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.edu.dao.IF_ReplyDAO;
import com.edu.vo.PageVO;
import com.edu.vo.ReplyVO;

@Service
public class ReplyService implements IF_ReplyService {
	@Inject
	private IF_ReplyDAO replyDAO;
	
	@Override
	public List<ReplyVO> selectReply(PageVO pageVO, Integer bno) throws Exception {
		// TODO Auto-generated method stub
		return replyDAO.selectReply(pageVO, bno);
	}

	@Override
	public int countReply(Integer bno) throws Exception {
		// TODO Auto-generated method stub
		return replyDAO.countReply(bno);
	}

	@Transactional // All or NotAll
	@Override
	public void insertReply(ReplyVO replyVO) throws Exception {
		// TODO 2개의 DAO 실행
		replyDAO.insertReply(replyVO);
		replyDAO.replyCountUpdate(replyVO.getBno(), 1);
	}

	@Override
	public void updateReply(ReplyVO replyVO) throws Exception {
		// TODO Auto-generated method stub
		replyDAO.updateReply(replyVO);
	}

	@Transactional
	@Override
	public void deleteReply(ReplyVO replyVO) throws Exception {
		// TODO Auto-generated method stub
		replyDAO.deleteReply(replyVO);
		replyDAO.replyCountUpdate(replyVO.getBno(), -1);
	}

}
