package com.edu.dao;
/**
 * 댓글 쿼리 CRUD 인터페이스
 * @author 은비
 *
 */

import java.util.List;

import com.edu.vo.PageVO;
import com.edu.vo.ReplyVO;

public interface IF_ReplyDAO {

	public List<ReplyVO> selectReply(PageVO pageVO) throws Exception;
	public int countReply(Integer bno) throws Exception;
	public void insertReply(ReplyVO replyVO) throws Exception;
	public void updateReply(ReplyVO replyVO) throws Exception;
	public void replyCountUpdate(Integer bno, int count) throws Exception;
	public void deleteReply(ReplyVO replyVO) throws Exception;
	public void deleteReplyAll(Integer bno) throws Exception;
}
