package com.edu.service;

import java.util.List;

import com.edu.vo.PageVO;
import com.edu.vo.ReplyVO;

/**
 * 댓글 DAO를 이용해서 댓글 CRUD를 구현하는 서비스 인터페이스
 * @author 은비
 *
 */

public interface IF_ReplyService {
	
	public List<ReplyVO> selectReply(PageVO pageVO) throws Exception;
	public int countReply(Integer bno) throws Exception;
	public void insertReply(ReplyVO replyVO) throws Exception;
	public void updateReply(ReplyVO replyVO) throws Exception;
	public void deleteReply(ReplyVO replyVO) throws Exception;

}
